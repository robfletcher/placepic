<!doctype html>
<html>
	<head>
		<title>Image upload</title>
	</head>
    <body>
	
		<header>
			<h1>Image upload</h1>
		</header>
		
		<% include '/WEB-INF/includes/nav.gtpl' %>
		
		<fieldset>
	        <legend>Please select an image file (JPG or PNG)</legend>
	        <form action="${blobstore.createUploadUrl('/upload.groovy')}" method="post" enctype="multipart/form-data">
	            <input type="file" name="image">
	            <input type="submit" value="Submit">
	        </form>
		</fieldset>
		
    </body>
</html>