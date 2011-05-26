
// routes for the blobstore service example
get "/upload",  forward: "/upload.gtpl"
get "/success", forward: "/success.gtpl"
get "/failure", forward: "/failure.gtpl"
get "/image/@width/@height", forward: "/image.groovy?width=@width&height=@height",
	validate: { width.isInteger() && width.toInteger() > 0 && height.isInteger() && height.toInteger() > 0 }
get "/info", forward: "/info.gtpl"

get "/favicon.ico", redirect: "/images/gaelyk-small-favicon.png"