get "/image/@width/@height", forward: "/image.groovy?width=@width&height=@height",
	validate: { width.isInteger() && width.toInteger() > 0 && height.isInteger() && height.toInteger() > 0 }

get "/upload",  forward: "/upload.gtpl"
get "/list", forward: "/list.gtpl"
get "/delete/@id", forward: "/delete.groovy?id=@id"

get "/favicon.ico", redirect: "/images/gaelyk-small-favicon.png"