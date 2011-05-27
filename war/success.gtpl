<% import placepic.ImageHelper %>
<!doctype html>
<html>
	<head>
		<title>Image upload</title>
	</head>
    <body>
		<header>
	        <h1>Image upload</h1>
			<h2>Success!</h1>
		</header>
		<%
			def image = ImageHelper.get(datastore, params.id)
		%>
		<section class="image-data">
	        <dl>
	            <dt>File name</dt><dd>${image.filename}</dd>
	            <dt>Content type</dt><dd>${image.contentType}</dd>
	            <dt>Size</dt><dd>${image.width} x ${image.height}</dd>
	        </dl>
			<img src="/image/320/200?id=${params.id}" alt="${image.filename}">
		</section>
		<nav>
			<a href="/upload">Upload another&hellip;</a>
		</nav>
    </body>
</html>

