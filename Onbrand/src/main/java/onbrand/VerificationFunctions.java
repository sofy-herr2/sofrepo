package onbrand;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.Select;

import onbrand.WebDriverWaitByLocator;
import onbrand.CommonFunctions;
import onbrand.tools.DriverManager;

import org.testng.asserts.SoftAssert;

public class VerificationFunctions {

/*LIST OF FUNCTIONS 	
-------------------

verifyDropListOptionCss
verifyElementVisible
verifyElementsVisible
verifyElementNotVisible
verifyPageTitle
verifyText
verifyURL


*/ 
    public static  SoftAssert myAssertion;
	

	protected void beforeTest() {
		myAssertion = new SoftAssert();
		
	}
	
	protected void afterTest() {

		myAssertion.assertAll();
	
	}
	
	
	//Verify Selected Element on a DropDown List using CSS Selector	
	public static void verifyDropListOptionCss( String csspath, String option ) {

		try {						
			Select select = new Select(DriverManager.driver.findElement(By.cssSelector(csspath)));
			
			//myAssertion.assertEquals(select.selectByVisibleText(option), expected);
			for ( WebElement options : select.getOptions()) {
			options.getText().contains(option);
			System.out.println(options.getText());
			//LogUtils.detailLog( "passed ---  verifyDropListOption" );
			}
					
		} 
			catch( WebDriverException | AssertionError e ) {
			//LogUtils.detailLog( "failed ----  verifyDropListOptionCss" );
			//LogUtils.fileWriter( "Verify Droplist ---- No Element ---" + csspath, e );
			myAssertion.fail( "Failed to Verify DropListOption  - "+ option);
		}
	}
	
	
   //verifying visible element
	public static void verifyElementVisible(LocatorType locatorType, String locator){
			
		
				
				By by = WebDriverWaitByLocator.getBy(locatorType, locator);
				WebElement visibleElement = (new WebDriverWait(DriverManager.driver, 10)).until(ExpectedConditions.presenceOfElementLocated( by ));
	            Boolean elementVisible = visibleElement.isDisplayed();
	            Assert.assertTrue(elementVisible);
				
		
					
		}
	
	//verifying visible elements
	public static void verifyElementsVisible( LocatorType locatorType, String locator ){

		/*try {
			By by = WebDriverWaitByLocator.getBy(locatorType, locator);
			List<WebElement> allElements = TestHooks.driver.findElements(by);
			List<WebElement> wait = new WebDriverWait(TestHooks.driver, 10).until(ExpectedConditions.visibilityOfAllElements(allElements));
			
			myAssertion.assertTrue(wait.containsAll(allElements), "element["+locator+"] should be visible");

			//LogUtils.detailLog( "passed --- Element Visible --- " + locator );
		} //catch(AssertionError |  NoSuchElementException | WebDriverException e ) {
		catch (Exception e){
			//LogUtils.fileWriter( e );
			//LogUtils.detailLog( "failed ---- verifyElementsVisible ---- " + locator );
			Assert.fail( "Failed to Verify Element Visible - "+ locator);
		}*/
		
		By by = WebDriverWaitByLocator.getBy(locatorType, locator);
		List<WebElement> visibleElements = DriverManager.driver.findElements(by);
		List<WebElement> wait = new WebDriverWait(DriverManager.driver, 10).until(ExpectedConditions.visibilityOfAllElements(visibleElements));
		
	}

	
	// verifying element not visible on page	
	public static void verifyElementNotVisible( LocatorType locatorType, String locator ) {

		//try
		//{
			By by = WebDriverWaitByLocator.getBy( locatorType, locator );
			Boolean notVisible = new WebDriverWait( DriverManager.driver, 10 ).until(ExpectedConditions.invisibilityOfElementLocated( by ));
			Assert.assertTrue(notVisible);
			//myAssertion.assertTrue(notVisible,"Not Visible");
			//LogUtils.detailLog( "Passed ---- verifyElementNotVisible ---" +locator );
		 //}
		
		//catch( Exception e /*WebDriverException e*/ )
		// {
			//LogUtils.fileWriter( "Element Visible", e );
			//LogUtils.detailLog( "fail ---- verifyElementNotVisible" );
			//myAssertion.fail( "Element "+ locator +" is Visible ", e );
			
		//  } 
		//catch( AssertionError e ) 
		//{
			//LogUtils.fileWriter( "Element Visible", e );
			//LogUtils.detailLog( "fail ---- verifyElementNotVisible" );
			//myAssertion.fail( "Failed " + "Element "+ locator +" is Visible ");
		//}
	}
	
	//verifying text
		public static void verifyText( LocatorType locatorType, String locator, String attribute, String value ) {

			By by = WebDriverWaitByLocator.getBy(locatorType, locator);

			WebElement textNode = DriverManager.driver.findElement(by);
			Assert.assertTrue(textNode.getAttribute(attribute).contains(value));
		}
		
		/**
		 * Verify Current URL to specified String
		 * */
		public static void verifyURL( String url ) {
			
			CommonFunctions.browserWait(5);
			Properties prop = new Properties();

			try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {prop.load(input);
			
			String siteUrl = prop.getProperty("siteUrl", "").trim()+url;
			String currentUrl= DriverManager.driver.getCurrentUrl();
			Assert.assertEquals(currentUrl,siteUrl, "URL Not The Same" );
			//LogUtils.detailLog( "passed ----"+ "Current Page URL "+ Context.getCurrentUrl());
				
			} catch (IOException|AssertionError e) {
			//LogUtils.detailLog( "failed ---- verifyURL" );
			//LogUtils.fileWriter( e );
			//myAssertion.fail( "Failed to Verify URL  - "+ url);
			//myAssertion.assertAll();
			}		
		}
		
		/**
		 * Verify Page Title
		 * */
		public static void verifyPageTitle( String title ) {

			/*try {
				
				String a = TestHooks.driver.getTitle();
				System.out.println("page title Is------"+a);
				System.out.println("title Is------"+title);
				
				myAssertion.assertEquals(a, title );
				
			} catch( AssertionError e ) {
			
				myAssertion.fail( "Failed to Verify Page Title  - "+ title);
			}*/
			
			Boolean wait = new WebDriverWait(DriverManager.driver, 10).until(ExpectedConditions.titleContains(title));
			Assert.assertTrue(wait);
			
		}
		
		
	
	
}
