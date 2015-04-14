package onbrand;

import java.net.MalformedURLException;
import onbrand.tools.DriverManager;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class TestHooks {
	String USERNAME ="quality4", AUTOMATE_KEY="fMEiyiwqvz1xVj1q7FgG";

	String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";

	String currentDir =System.getProperty("user.home");
	
	@Before
	public void setUp() throws MalformedURLException {
		DriverManager.getInstance();
   }

	@After
	public  void tearDown() {
		DriverManager.getInstance().quitDriver();
	}
	
	

}
