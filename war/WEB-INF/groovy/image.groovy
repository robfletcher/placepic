import com.google.appengine.api.blobstore.*

int width = Math.abs(params.width.toInteger())
int height = Math.abs(params.height.toInteger())

def left = 0.0
def top = 0.0
def right = 1.0
def bottom = 1.0

BlobKey blob = new BlobKey(params.key)
def image = blob.image

image.transform {
	crop 0.0, 0.0, 1.0, 1.0
}

log.info "Opened image $blob.info.filename with height $image.height and width $image.width"
def originalAspect = image.height / image.width
def desiredAspect = height / width
def cropPercent = (desiredAspect - originalAspect) / 2
if (cropPercent > 0) {
	left += cropPercent
	right -= cropPercent
} else {
	top += Math.abs(cropPercent)
	bottom -= Math.abs(cropPercent)
}

log.info "Aspect: $originalAspect -> $desiredAspect, crop: $cropPercent, l: $left, r: $right, t: $top, b: $bottom"

image.transform {
	crop left, top, right, bottom
}

image.transform {
	resize width, height
	feeling lucky
}

response.contentType = blob.info.contentType
sout << image.imageData