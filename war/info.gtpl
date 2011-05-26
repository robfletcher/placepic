<% import com.google.appengine.api.datastore.Query %>
<!doctype html>
<html>
	<head>
		<title>Image status</title>
	</head>
	<body>
		<h1>Image status</h1>
		<ul>
			<%
			def query = new Query("image")
			def preparedQuery = datastore.prepare(query)
			for (def itr = preparedQuery.asIterator(); itr.hasNext(); ) {
			%>
			<li>${itr.next().blob.filename}</li>
			<% } %>
		</ul>
	</body>
</html>