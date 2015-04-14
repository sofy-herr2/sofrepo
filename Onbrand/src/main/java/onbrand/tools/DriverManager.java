package onbrand.tools;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;


public class DriverManager {

	   private static DriverManager instance = null;
	   private String browser = "Firefox";	
	   public static WebDriver driver;
	
	   public static String loginURL = "http://devpointuat.northplains.com/login/";
	   public static String baseURL = "http://devpointuat.northplains.com";
	   
	
	    private DriverManager() {
	        initializeWebDriver();
	    }
	    
	    public static DriverManager getInstance() {
	        if (instance == null || driver == null) {
	            instance = new DriverManager();
	        }
	        return instance;
	    }
	
	    public void quitDriver() {
	        try {
	    		driver.close();
	    		driver.quit();
	            
	        } catch (Exception e) {
	        }
	        driver = null;
	    }
	
	    private void initializeWebDriver() {
	        if (browser.equals("Firefox")) {
	        driver = new FirefoxDriver();
	    } else if (browser.equals("Chrome")) {
	        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
	        driver = new ChromeDriver();
	    } else if (browser.equals("IE")) {
	        System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
	        driver = new InternetExplorerDriver();
	    } else if (browser.equals("Safari")) {
	        driver = new SafariDriver();
	    }
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);        
		DriverHelper.getInstance().setDriver(driver);
	    
	}
	
	public static String getCurrentUrl() {
		
		String fullUrl = driver.getCurrentUrl();
		try {
			URL url = new URL(fullUrl);
			return url.getFile();
		} catch (Exception e) {
			return "";
		}
	}
	    
	   
	
}
