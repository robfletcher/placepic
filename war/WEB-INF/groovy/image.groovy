import com.google.appengine.api.blobstore.*
import com.google.appengine.api.datastore.*
import static com.google.appengine.api.datastore.FetchOptions.Builder.*

int width = Math.abs(params.width.toInteger())
int height = Math.abs(params.height.toInteger())

def left = 0.0
def top = 0.0
def right = 1.0
def bottom = 1.0

def blob
if (params.key) {
	blob = new BlobKey(params.key)
} else {
	def query = new Query("image")
	def preparedQuery = datastore.prepare(query)
	def entities = preparedQuery.asList(withLimit(1))
	blob = new BlobKey(entities[0].blobKey)
}

def image = blob.image

// this is a no-op transform required so we can read image data
image.crop 0.0, 0.0, 1.0, 1.0

def originalAspect = image.height / image.width
def desiredAspect = height / width

// resize to a square that will overflow the desired size
if (originalAspect < desiredAspect) {
    // original is more landscape than desired
    int resizeWidth = Math.ceil((image.width / image.height) * height)
    image.resize(resizeWidth, height)

    left = (1 - (width / image.width)) / 2
   right = 1 - left
} else {
    // original is more portrait than desired
    int resizeHeight = Math.ceil((image.height / image.width) * width)
    image.resize(width, resizeHeight)

    top = (1 - (height / image.height)) / 2
    bottom = 1 - top
}

image.transform {
    crop left, top, right, bottom
	feeling lucky
}

response.contentType = blob.info.contentType
sout << image.imageData