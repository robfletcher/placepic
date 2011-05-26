<% import com.google.appengine.api.blobstore.BlobKey %>
<html>
    <body>
        <h1>Success</h1>
        <% def blob = new BlobKey(params.key) %>
        <dl>
            <dt>File name</dt><dd>${blob.filename}</dd>
            <dt>Content type</dt><dd>${blob.contentType}</dd>
            <dt>Creation date</dt><dd>${blob.creation}</dd>
            <dt>Size</dt><dd>${blob.size}</dd>
        </dl>
		<img src="/image/480/320?key=${params.key}" alt="${blob.filename}">
    </body>
</html>

