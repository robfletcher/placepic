import com.google.appengine.api.blobstore.*
import com.google.appengine.api.datastore.*
import static com.google.appengine.api.datastore.FetchOptions.Builder.*
import static com.google.appengine.api.datastore.Query.FilterOperator.*
import placepic.ImageHelper

int width = Math.abs(params.width.toInteger())
int height = Math.abs(params.height.toInteger())

def entity = params.id ? ImageHelper.get(datastore, params.id) : ImageHelper.random(datastore)

if (entity) {
	def image = entity.blob.image

	def left = 0.0
	def top = 0.0
	def right = 1.0
	def bottom = 1.0

	def originalAspect = entity.height / entity.width
	def desiredAspect = height / width

	// resize to a square that will overflow the desired size
	if (originalAspect < desiredAspect) {
	    // original is more landscape than desired
	    int resizeWidth = Math.ceil((entity.width / entity.height) * height)
	    image.resize(resizeWidth, height)

	    left = (1 - (width / image.width)) / 2
	   right = 1 - left
	} else {
	    // original is more portrait than desired
	    int resizeHeight = Math.ceil((entity.height / entity.width) * width)
	    image.resize(width, resizeHeight)

	    top = (1 - (height / image.height)) / 2
	    bottom = 1 - top
	}

	image.transform {
	    crop left, top, right, bottom
		feeling lucky
	}

	response.contentType = entity.contentType
	sout << image.imageData
} else {
	response.sendError 404, "Image not found"
}