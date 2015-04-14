package onbrand.tools;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverHelper {
	private static DriverHelper instance = new DriverHelper();
	private WebDriver driver;

	private DriverHelper() {
		this.driver = null;
	}

	public static DriverHelper getInstance() {
		return instance;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriverWait waitFor(long timeout) {
		return new WebDriverWait(driver, timeout);
	}
}

