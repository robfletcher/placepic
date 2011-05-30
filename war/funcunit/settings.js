FuncUnit = {
	// the list of browsers that selenium runs tests on
	browsers: ["*firefox", "*safari", "*googlechrome"],
	
	// the root for all paths in the tests, defaults to filesystem
	jmvcRoot: "http://localhost:8000/",
	
	// the number of milliseconds between Selenium commands, "slow" is 500 ms
	speed: null, //"slow"
}