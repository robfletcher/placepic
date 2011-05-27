<% import placepic.ImageHelper %>
<!doctype html>
<html>
	<head>
		<title>Image status</title>
	</head>
	<body>
		<h1>Image status</h1>
		<ul>
			<% ImageHelper.each(datastore) { entity -> %>
				<li>
					<img src="/image/180/101?id=${entity.key.id}" alt="${entity.filename}">
					<span class="filename">${entity.filename}</span>
					<span class="dimensions">${entity.width}x${entity.height}</span>
					<span class="content-type">${entity.contentType}</span>
					<a href="/delete/${entity.key.id}">delete</a>
				</li>
			<% } %>
		</ul>
		<nav>
			<a href="/upload">Upload another&hellip;</a>
		</nav>
	</body>
</html>