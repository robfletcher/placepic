<nav>
	<a href="/">Home</a>
	<% if (user) { %>
		<% if (users.isUserAdmin()) { %>
			<a href="/list">Image list</a>
			<a href="/upload">Upload an image</a>
		<% } %>
		<a href="${users.createLogoutURL('/')}">Log out<a>
	<% } else { %>
		<a href="${users.createLoginURL('/')}">Log in</a>
	<% } %>
</nav>
