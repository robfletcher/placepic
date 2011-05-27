<nav>
	<a href="/">Home</a>
	<% if (user) { %>
		<% if (users.isUserAdmin()) { %>
			<a href="/admin/list">Image list</a>
			<a href="/admin/upload">Upload an image</a>
		<% } %>
		<a href="${users.createLogoutURL('/')}">Log out<a>
	<% } else { %>
		<a href="${users.createLoginURL('/')}">Log in</a>
	<% } %>
</nav>
