<!doctype html>
<html>
    <head>
        <title>Placeosaurus</title>
        
        <link rel="shortcut icon" href="/images/gaelyk-small-favicon.png" type="image/png">
        <link rel="icon" href="/images/gaelyk-small-favicon.png" type="image/png">
        
        <link rel="stylesheet" type="text/css" href="/css/main.css"/>
    </head>
    <body>
	
        <header>
			<h1>Placeosaurus</h1>
        </header>

		<% include '/WEB-INF/includes/nav.gtpl' %>
		
        <section id="main">
			<h1>Welcome</h1>
			<p>
			    This is a simple image service inspired by <a href="http://placekitten.com/">placekitten</a> and <a href="http://placehold.it/">placehold.it</a> written using <a href="http://gaelyk.appspot.com">Gaelyk</a>.
			</p>
			<p>
				Simply use a URL in the format <code>${request.scheme}://${request.serverName}/image/<em>width</em>/<em>height</em></code>, (<em>e.g.</em> <code><a href="/image/480/320">${request.scheme}://${request.serverName}/image/480/320</a></code>) to get a placeholder image.
			</p>
        </section>

		<section id="examples">
			<img src="/image/175/325">
			<img src="/image/180/120">
			<img src="/image/180/200">
			<img src="/image/360/100">
		</section>
		
    </body>
</html>