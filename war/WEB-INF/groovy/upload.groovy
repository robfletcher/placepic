import placepic.ImageHelper

def blobs = blobstore.getUploadedBlobs(request)
def blob = blobs["image"]

if (!blob) {
	response.sendError 500, "Upload failed"
} else if (blob.info.contentType in ["image/png", "image/jpeg"]) {
	def entity = ImageHelper.createFromBlob(datastore, blob)
	redirect "/list"
} else {
	response.sendError 415, "Invalid file type $blob.info.contentType"
}
