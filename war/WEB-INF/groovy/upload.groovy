import com.google.appengine.api.datastore.Entity

def blobs = blobstore.getUploadedBlobs(request)
def blob = blobs["image"]

if (!blob) {
	response.sendError 500, "Upload failed"
} else if (blob.info.contentType in ["image/png", "image/jpeg"]) {
	def entity = new Entity("image")
	entity.blobKey = blob.keyString
	entity.save()
	redirect "/success?key=${blob.keyString}"
} else {
	response.sendError 415, "Invalid file type $blob.info.contentType"
}
