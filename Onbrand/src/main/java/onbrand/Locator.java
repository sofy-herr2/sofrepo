package onbrand;

import onbrand.tools.DriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Locator {

    private WebDriver webDriver;
    private WebElement webElement;
    private Locator(){    
    	webDriver = DriverManager.driver;
        webElement = null;

    }  

    private WebDriver getDriver() {
        return webDriver;
    }

    public WebElement getElement() {
        return webElement;
    }

    public boolean isPresent() {
        return webElement != null;
    }


    public Locator find(By by) {
        if (webElement != null) {
            try {
                webElement = webElement.findElement(by);
            }catch( Exception e ) {
                webElement = null;
            }
        }
        return this;
    }

    public static Locator by(By by) {
        Locator locator = new Locator();
        try {
            locator.webElement =  locator.getDriver().findElement(by);
        } catch(Exception e) {

        }
        return locator;
    }

    public static Locator byId(String id) {
        return Locator.by(By.id( id ));
    }

    public static Locator byCss(String css) {
        return Locator.by(By.cssSelector(css));
    }

    public static Locator byXPath(String xpath) {
        return Locator.by(By.xpath( xpath ));
    }

    public boolean hasClass(String className) {
        return webElement  != null && webElement.getAttribute("class").contains(className);
    }
    
  

}
