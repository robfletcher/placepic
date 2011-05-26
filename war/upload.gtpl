<html>
    <body>
        <h1>Please upload an image file</h1>
        <form action="${blobstore.createUploadUrl('/upload.groovy')}" method="post" enctype="multipart/form-data">
            <input type="file" name="image">
            <input type="submit" value="Submit">
        </form>
    </body>
</html>