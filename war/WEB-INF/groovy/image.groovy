import com.google.appengine.api.blobstore.*
import com.google.appengine.api.datastore.*
import static com.google.appengine.api.datastore.FetchOptions.Builder.*
import static com.google.appengine.api.datastore.Query.FilterOperator.*
import placepic.ImageHelper

int width = Math.abs(params.width.toInteger())
int height = Math.abs(params.height.toInteger())

def entity = params.id ? ImageHelper.get(datastore, params.id) : ImageHelper.random(datastore)

if (entity) {
	def image = ImageHelper.resizeTo(entity, width, height)
	response.contentType = entity.contentType
	sout << image.imageData
} else {
	response.sendError 404, "Image not found"
}