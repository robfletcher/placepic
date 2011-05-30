var ensureLoggedOut = function() {
	var logoutLink = S("nav a:contains('Log out')");
	if (logoutLink.size() > 0) {
		logoutLink.click();
		S("nav a:contains('Log in')").visible();
	}
}

module("placepic", {
	setup: function() {
		S.open("/");
	},
	teardown: function() {
		ensureLoggedOut();
	}
})

test("Login link is visible", function() {
	ok(S("nav a:contains('Log in')").visible(), "Log in link should be visible");
});

test("Logging in as a non-admin user does not display admin functions", function() {
	S("nav a:contains('Log in')").click();
	S("#email").visible(function() {
		S(":submit[value='Log In']").click();
		S("nav a:contains('Log out')").visible(function() {
			equal(S("nav a:contains('Image list')").size(), 0, "'Image list' should not be present");
			equal(S("nav a:contains('Upload an image')").size(), 0, "'Upload an image' should not be present");
		});
	});
});

test("Logging in as an admin user displays admin functions", function() {
	S("nav a:contains('Log in')").click();
	S("#email").visible(function() {
		S("#isAdmin").click();
		S(":submit[value='Log In']").click();
		S("nav a:contains('Log out')").visible(function() {
			ok(S("nav a:contains('Image list')").visible(), "'Image list' should be present");
			ok(S("nav a:contains('Upload an image')").visible(), "'Upload an image' should be present");
		});
	});
});