<% import com.google.appengine.api.blobstore.BlobKey %>
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
        <% def blob = new BlobKey(params.key) %>
		<section class="image-data">
	        <dl>
	            <dt>File name</dt><dd>${blob.filename}</dd>
	            <dt>Content type</dt><dd>${blob.contentType}</dd>
	            <dt>Creation date</dt><dd>${blob.creation}</dd>
	            <dt>Size</dt><dd>${blob.size}</dd>
	        </dl>
			<img src="/image/480/320?key=${params.key}" alt="${blob.filename}">
		</section>
    </body>
</html>

