get "/@width/@height", forward: "/image.groovy?width=@width&height=@height",
	cache: 24.hours,
	validate: { width.isInteger() && width.toInteger() > 0 && height.isInteger() && height.toInteger() > 0 }

get "/admin/upload",  forward: "/upload.gtpl"
get "/admin/list", forward: "/list.gtpl"
get "/admin/delete/@id", forward: "/delete.groovy?id=@id"

get "/favicon.ico", redirect: "/images/gaelyk-small-favicon.png"