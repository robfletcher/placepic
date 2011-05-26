import com.google.appengine.api.datastore.*
import static com.google.appengine.api.datastore.FetchOptions.Builder.*
import static com.google.appengine.api.datastore.Query.FilterOperator.*

def blobs = blobstore.getUploadedBlobs(request)
def blob = blobs["image"]

if (!blob) {
	response.sendError 500, "Upload failed"
} else if (blob.info.contentType in ["image/png", "image/jpeg"]) {
	def query = new Query("image")
	def preparedQuery = datastore.prepare(query)
	def first = preparedQuery.countEntities(withLimit(1)) == 0
	
	def entity = new Entity("image")
	entity.blob = blob
	entity.rnd = first ? Integer.MAX_VALUE : new Random().nextInt()
	entity.save()
	redirect "/success?key=${blob.keyString}"
} else {
	response.sendError 415, "Invalid file type $blob.info.contentType"
}
