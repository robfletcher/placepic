import com.google.appengine.api.blobstore.*

int width = Math.abs(params.width.toInteger())
int height = Math.abs(params.height.toInteger())

def left = 0.0
def top = 0.0
def right = 1.0
def bottom = 1.0

BlobKey blob = new BlobKey(params.key)
def image = blob.image

// this is a no-op transform required so we can read image data
image.crop 0.0, 0.0, 1.0, 1.0

log.info "Opened image $blob.info.filename which is $image.width x $image.height"

def originalAspect = image.height / image.width
def desiredAspect = height / width

log.info "original $originalAspect, required: $desiredAspect"

// resize to a square that will overflow the desired size
if (originalAspect < desiredAspect) {
    // original is more landscape than desired
    int resizeWidth = (image.width / image.height) * height
    log.info "more landscape than I want: resizing to $resizeWidth x $height"
    image.resize(resizeWidth, height)

    left = 1 - (width / resizeWidth)
    left /= 2
    right = 1 - left
} else {
    // original is more portrait than desired
    int resizeHeight = (image.height / image.width) * width
    log.info "more landscape than I want: resizing to $width x $resizeHeight"
    image.resize(width, resizeHeight)

    top = 1 - (height / resizeHeight)
    top /= 2
    bottom = 1 - top
}

log.info "Aspect: $originalAspect -> $desiredAspect, l: $left, r: $right, t: $top, b: $bottom"

image.transform {
    crop left, top, right, bottom
	feeling lucky
}

response.contentType = blob.info.contentType
sout << image.imageData