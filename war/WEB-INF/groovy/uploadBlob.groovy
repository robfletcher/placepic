def blobs = blobstore.getUploadedBlobs(request)
def blobKey = blobs["image"]

if (blobKey) {
	redirect "/image/500/500?key=${blobKey.keyString}"	
} else {
	redirect "/failure"
}
