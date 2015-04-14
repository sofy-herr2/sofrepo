package onbrand;


import org.junit.Assert;
import org.ocpsoft.prettytime.nlp.PrettyTimeParser;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import onbrand.tools.DriverManager;
import onbrand.tools.SortEntry;

/*LIST OF FUNCTIONS 	
-------------------
browseFile
browserWait
checkBox
clearAndSendKeys
clearText
click
confirmAlert
doubleClick
getAttributeValue
getBy
getItemID
hoverMouse
javaScriptExe
pressKey
rightClick
selectDropList
setText
sleep
waitForElement
uncheckBox
*/


public class CommonFunctions {
	
	public static  SoftAssert myAssertion;
	

	protected void beforeTest() {
		myAssertion = new SoftAssert();
		
	}
	
	protected void afterTest() {

		myAssertion.assertAll();
	
	}
	
	/**
	 * Upload File by sending filePath as a String Parameter using Xpath
	 * */
	public static void browseFile( By locator, String filepath ) {

		try {
							
			WebElement browsefile = DriverManager.driver.findElement(locator);			
			browsefile.sendKeys(filepath);			
			//myAssertion.assertNotNull( filepath + browsefile );
			//LogUtils.detailLog( "passed" );

		    }
	    catch( NoSuchElementException | AssertionError | WebDriverException e ) {
			//LogUtils.detailLog( "failed ---- browseFile" );
			//LogUtils.fileWriter( e );
			myAssertion.fail( "Failed to Locate upload Button  - "+ filepath);
		}
	}

  	 public static void browserWait( long time ) {

		try {		
			DriverManager.driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
			DriverManager.driver.manage().timeouts().setScriptTimeout(time, TimeUnit.SECONDS);
			DriverManager.driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
			//LogUtils.detailLog( "passed -- browserWait" );
				
		} 
			
		catch( WebDriverException e ) {
			//LogUtils.fileWriter( e );
			//LogUtils.detailLog( "failed ---- browserWait" );
			myAssertion.fail( "Browser failed to Wait - "+ time);
		}
	 }
		 
	 public static void clearAndSendKeys(By by, String value) {
			
		try {
					
			WebElement element = DriverManager.driver.findElement(by);
			element = new WebDriverWait(DriverManager.driver, 10).until(ExpectedConditions.presenceOfElementLocated(by));
			element.clear();
			element.sendKeys(value);
			//LogUtils.detailLog( "passed ---- clearAndSendKeys----" +value);
		} 
				
		catch (WebDriverException e) {
			//LogUtils.fileWriter( e );
			//LogUtils.detailLog( "failed ---- clearAndSendKeys ---" );
			myAssertion.fail( "Failed to clear" + value);
		}
				
			DriverManager.driver.manage().getCookies();
	 }
			 
	public static void clearText(LocatorType locatorType, String locator) {

		By by = WebDriverWaitByLocator.getBy(locatorType, locator);

		WebElement element = DriverManager.driver.findElement(by);
		element = (new WebDriverWait(DriverManager.driver, 10)).until(ExpectedConditions.presenceOfElementLocated(by));
		element.clear();	
			
	}
	
	public static void click (LocatorType type, String locator) {

	
			
			By by = WebDriverWaitByLocator.getBy(type, locator);
			WebElement visibleElement = (new WebDriverWait(DriverManager.driver, 10)).until(ExpectedConditions.presenceOfElementLocated( by ));
			Boolean elementVisible = visibleElement.isDisplayed();
            Assert.assertTrue(elementVisible);
			
            WebElement wait = new WebDriverWait(DriverManager.driver, 10).until(ExpectedConditions.visibilityOf(visibleElement));
			
			Actions scrollToElement = new Actions(DriverManager.driver);
			scrollToElement.moveToElement(wait).perform();				
			visibleElement.click();

  }
	
	
	public static void doubleClick( LocatorType type, String locator ) {
		By by = WebDriverWaitByLocator.getBy(type,locator);
		
		//try {
			WebElement element = DriverManager.driver.findElement(by);
			Actions doubleclick = new Actions( DriverManager.driver );
			doubleclick.doubleClick(element).perform();
			//myAssertion.assertNotSame( element.getLocation(), element.getLocation() );
			//LogUtils.detailLog( "passed" );

		//} catch( NoSuchElementException | AssertionError e ) {
			//LogUtils.detailLog( "failed ---- doubleClick" );
			//LogUtils.fileWriter( e );
			//myAssertion.fail( "Failed to doubleClick  - "+ locator);
		//}
	}
	
	
	
	 public static String getItemID() {
		 
  		String itemURl = DriverManager.getCurrentUrl(), exactItemID;
		exactItemID = itemURl.substring(itemURl.indexOf("item"),itemURl.lastIndexOf('/'));

		System.out.println("ItemID Is------"+exactItemID);
		return exactItemID;
     }
		
	 
		
	/**
	 * hover mouse to a WebElement using Xpath Atrribute
	 * */
	public static void hoverMouse( String attribute ) {
		try {
			WebElement element = Locator.byXPath( attribute ).getElement();
			Actions clickHover = new Actions( DriverManager.driver );
			clickHover.moveToElement( element ).perform();
			myAssertion.assertNotSame( element.getLocation(), element.getLocation() );
			//LogUtils.detailLog( "passed" );
		} 
		
		catch( NoSuchElementException | AssertionError e ) {
			//LogUtils.detailLog( "failed ---- hoverMouse" );
			//LogUtils.fileWriter( e );
			myAssertion.fail( "Failed to HoverMouse to  - "+ attribute);
		}
	}
	
	 /* Execute Javascript code snippet by parsing code as string */
	 public static void javaScriptExe( String jscode ) {
		try {
			JavascriptExecutor js = ( JavascriptExecutor ) DriverManager.driver;
			js.executeScript( jscode );
			//LogUtils.detailLog( "JavaScript Code ---- Executed" );

		} catch( Exception e ) {

			//LogUtils.fileWriter( "Error with JavaScript Code", e );
			myAssertion.fail( "Failed to execute JavaScript");
		}
	 }
	 	 
	 public static void pressKey (String keyPosition){

		try 
		{
			Keys keys = null;
				
			switch (keyPosition) {
			case "Up":
				keys = Keys.ARROW_UP;
				break;
			case "Down":
				keys = Keys.ARROW_DOWN;
				break;
			case "Left":
				keys = Keys.ARROW_LEFT;
				break;
			case "Right":
				keys = Keys.ARROW_RIGHT;
				break;
			case "Delete":
				keys = Keys.DELETE;
				break;
			case "Enter":
				keys = Keys.ENTER;
				break;
			case "Control":
				keys = Keys.CONTROL;
				break;
			case "Escape":
				keys = Keys.ESCAPE;
				break;
			}

			Actions Keybutton = new Actions(DriverManager.driver);
			Keybutton.sendKeys(keys).perform();

			//LogUtils.detailLog( "passed" );
		} 
			
		catch (Exception e) {
			// need to fill the exception part here
		}
	 }
	 
		/**
		 * Select an Option from a drop-list using Xpath Attribute
		 * */
		public static void selectDropList( String xpath, String Option ) {

			try {
				Select select = new Select( Locator.byXPath( xpath ).getElement());
				select.selectByVisibleText( Option );
				//LogUtils.detailLog( "passed --- selectDropList" );

			} catch( WebDriverException e ) {
				//LogUtils.detailLog( "failed ---- selectDropList" );
				//LogUtils.fileWriter( e );
				myAssertion.fail( "Failed to Select  - "+ Option);
			}
		}
	 
	 public static void setText( LocatorType locatorType, String locator, String Text ) {

			try {
				waitForElement(locatorType, locator, 10);
				By by = WebDriverWaitByLocator.getBy( locatorType, locator);
				WebElement TextElement =  new WebDriverWait(DriverManager.driver, 5).until(ExpectedConditions.visibilityOfElementLocated(by));
				TextElement.clear();	
				TextElement.sendKeys( Text );

				//LogUtils.detailLog( "passed" );
				//LogUtils.detailLog( "setText ------" + Text );
			    }
			catch (NoSuchElementException e) {
				//LogUtils.detailLog( "failed ---- setText : " + locator );
				//LogUtils.fileWriter( e );
			} 
			
			/*catch ( Exception e1 /*WebDriverException e) {
				//LogUtils.detailLog( "failed ---- setText---" + locator );
				//LogUtils.fileWriter( "ELement --" + locator, e );
				myAssertion.fail( "Failed to Set Text On - "+locator + Text );
			}*/
		}
	 
	 public static void sleep(int time){
  		 try {
  			 Thread.sleep(time);
  			//LogUtils.detailLog( "Thread Sleep " + time +" Milliseconds");

		} catch (Exception e) {
			//LogUtils.fileWriter( "Thread Sleep", e );
			//LogUtils.detailLog( "fail ---- Sleep" );
			myAssertion.fail("Failed to Sleep");
		}
  	 }
		
	 /**
		 * Wait for an Element to load in a page by specifying Attribute, Value
		 * and waiting time
		 */
		public static void waitForElement( LocatorType type, String elementValue, long time ) {

			try {
				
				DriverManager.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				By by=  WebDriverWaitByLocator.getBy(type, elementValue);
				new WebDriverWait( DriverManager.driver, time).until( ExpectedConditions.visibilityOfElementLocated( by ) );

				//LogUtils.detailLog( "passed" );

			} catch( NoSuchElementException e ) {
				//LogUtils.fileWriter( "Element Not Found", e );
				//LogUtils.detailLog( "failed ---- waitForElement" );

			} //catch( TimeoutException e ) {
				//LogUtils.fileWriter( "Timeout Exceeded", e );
				//LogUtils.detailLog( "failed ---- waitForElement" );
			//} 
		catch( WebDriverException e ) {
				//LogUtils.fileWriter( e );
				//LogUtils.detailLog( "failed ---- waitForElement" );
				myAssertion.fail( "Failed to wait for Element  - "+ elementValue);
			}
		}
	
		
		/**
		 * Right-Click an Element using Locator Type and Attribute
		 * */
		public static void rightClick( LocatorType type, String locator ) {
			
			try {			
				By by = getBy(type, locator);
				
				WebElement element = DriverManager.driver.findElement(by);
				
				Actions rightclick = new Actions( DriverManager.driver );
				rightclick.moveToElement( element ).perform();
				rightclick.contextClick( element ).perform();
				//LogUtils.detailLog( "passed" );

			} catch( WebDriverException | NoSuchElementException e ) {
				//LogUtils.detailLog( "failed ---- rightClick" );
				//LogUtils.fileWriter( e );
				myAssertion.fail( "Failed to rightClick - "+ locator);
			}
		}
		
		private static By getBy(LocatorType type, String locator) {

			By by= null;
			switch( type ) {
			case LinkedText:
				by =  By.linkText( locator );
				break;
			case Name:
				by =  By.name( locator );
				break;
			case Xpath:
				by =  By.xpath( locator );
				break;
			case className:
				by =  By.className( locator );
				break;
			case cssSelector:
				by =  By.cssSelector( locator );
				break;
			case id:
				by =  By.id( locator );
				break;
			case partialLinkedText:
				by =  By.partialLinkText( locator );
				break;
			case TagName:
				by =  By.tagName( locator );
				break;
			}

			return by;
		}
		
		
		public static void confirmAlert( String confirmation ) {
			try {
				switch( confirmation ) {
				case "Yes" :
					DriverManager.driver.switchTo().alert().accept();
					
					break;
				case  "No":
					DriverManager.driver.switchTo().alert().dismiss(); 
					
					break;
				case "Cancel":
					DriverManager.driver.switchTo().alert().dismiss();
				
					break;
				case "OK":
					DriverManager.driver.switchTo().alert().accept();
					
				}
			} 
			catch( NoAlertPresentException e ) {
				
			    System.out.println( "No Alert Present" );
				myAssertion.fail( "No Alert Present -" +confirmation);
			}
		}
		
		
		
		public static String getAttributeValue(By locator, String attribute) {

			try {

				WebElement textNode = Locator.by(locator).getElement();

				//Assert.assertTrue( textNode.getAttribute( "value" ).equals( value ) );
				//System.out.println( textNode.getAttribute( "class" ) );
				return textNode.getAttribute( attribute ) ;

			} catch( AssertionError | NoSuchElementException e ) {
				return null;
			}
		}
		
		/**
		 * Check a check-box using id of the Checkbox
		 * */

		public static void checkBox(LocatorType locatorType, String locator ) {
			try {
				
				By by = getBy(locatorType, locator);
				WebElement checkBox = DriverManager.driver.findElement(by);
				
				if( checkBox.isSelected()!=true ) {
					checkBox.click();
				}
				//LogUtils.detailLog( "passed" );
			} catch( AssertionError | WebDriverException e ) {
				//LogUtils.fileWriter( e );
				//LogUtils.detailLog( "failed ----checkBox" );
				myAssertion.fail( "Failed to checkBox  - "+ locator);
			}
		}
		
		/**
		 * Uncheck a checkbox using id of the checkBox
		 * */
		public static void uncheckBox( LocatorType locatorType, String locator) {
		
			try {
				By by = getBy(locatorType, locator);
				WebElement checkBox = DriverManager.driver.findElement(by);
				
				/*if( checkBox.isSelected() ) {
					checkBox.click();
					myAssertion.assertFalse( checkBox.isSelected() );
				}
				if( !checkBox.isSelected() ) {
					myAssertion.assertFalse( checkBox.isSelected() );
					//LogUtils.detailLog( "passed" );
				}*/
				if( checkBox.isSelected()!=false ) {
					checkBox.click();
				}
			} 
			
			/* catch(WebDriverException | NoSuchElementException | AssertionError e ) {
				//LogUtils.fileWriter( e );
				//LogUtils.detailLog( "failed ----uncheckBox" +locator);
			}*/
			catch( AssertionError | WebDriverException e ) {
				//LogUtils.fileWriter( e );
				//LogUtils.detailLog( "failed ----checkBox" );
				myAssertion.fail( "Failed to checkBox  - "+ locator);
			}
			
		}

		/**
		 * Verify if a list of data is sorted ascendent or descendent
		 * */
		public static boolean isDataSorted(String sortBy, String sortOrder, List<SortEntry> entries) {
			if ((sortOrder.compareTo("up")) == 0 && (sortBy.compareTo("Name")) == 0)
				return isSortedInAscendentOrderAsString(entries, sortBy);
			else if ((sortOrder.compareTo("down")) == 0 && (sortBy.compareTo("Name")) == 0)
				return isSortedInDescendentOrderAsString(entries, sortBy);
			else if ((sortOrder.compareTo("up")) == 0 && (sortBy.compareTo("Last updated")) == 0)
				return isSortedInAscendentOrderAsPrettyDate(entries, sortBy);
			else if ((sortOrder.compareTo("down")) == 0 && (sortBy.compareTo("Last updated")) == 0)
				return isSortedInDescendentOrderAsPrettyDate(entries, sortBy);
			else if ((sortOrder.compareTo("up")) == 0 && (sortBy.compareTo("ID")) == 0)
				return isSortedInAscendentOrderAsInteger(entries, sortBy);
			else if ((sortOrder.compareTo("down")) == 0 && (sortBy.compareTo("ID")) == 0)
				return isSortedInDescendentOrderAsInteger(entries, sortBy);
			return false;
		}

		/**
		 * Verify if a SortEntry list is sorted ascendent as a string
		 * */
		public static boolean isSortedInAscendentOrderAsString(List<SortEntry> entries, String attributeName) {
			boolean f = false;
			String lastValue = null;
			for (SortEntry entry: entries) {
				if (!f) {
					f = true;
					lastValue = entry.getAttribute(attributeName); 
				} else {
					if ((entry.getAttribute(attributeName).compareToIgnoreCase(lastValue)) < 0)
						return false;
					lastValue = entry.getAttribute(attributeName);
				}
			}
			return true;
		}

		/**
		 * Verify if a SortEntry list is sorted ascendent as an integer
		 * */
		public static boolean isSortedInAscendentOrderAsInteger(List<SortEntry> entries, String attributeName) {
			boolean f = false;
			Integer lastIntValue = 0;
			Integer currentIntValue;
			for (SortEntry entry: entries) {
				if (!f) {
					f = true;
					lastIntValue = Integer.parseInt(entry.getAttribute(attributeName));
				} else {
					currentIntValue = Integer.parseInt(entry.getAttribute(attributeName));
					if ((currentIntValue.compareTo(lastIntValue)) < 0)
						return false;
					lastIntValue = currentIntValue;
				}
			}
			return true;
		}

		/**
		 * Verify if a SortEntry list is sorted ascendent as a pretty date
		 * */
		public static boolean isSortedInAscendentOrderAsPrettyDate(List<SortEntry> entries, String attributeName) {
			boolean f = false;
			Date lastDateValue = null;
			Date currentDateValue = null;
			for (SortEntry entry: entries) {
				if (!f) {
					f = true;
					lastDateValue = new PrettyTimeParser().parse(entry.getAttribute(attributeName)).get(0);
				} else {
					currentDateValue = new PrettyTimeParser().parse(entry.getAttribute(attributeName)).get(0);
					if ((currentDateValue.compareTo(lastDateValue)) < 0)
						return false;
					lastDateValue = currentDateValue;
				}
			}
			return true;
		}

		/**
		 * Verify if a SortEntry list is sorted descendent as a string
		 * */
		public static boolean isSortedInDescendentOrderAsString(List<SortEntry> entries, String attributeName) {
			boolean f = false;
			String lastValue = null;
			for (SortEntry entry: entries) {
				if (!f) {
					f = true;
					lastValue = entry.getAttribute(attributeName); 
				} else {
					if ((entry.getAttribute(attributeName).compareToIgnoreCase(lastValue)) > 0)
						return false;
					lastValue = entry.getAttribute(attributeName);
				}
			}
			return true;
		}

		/**
		 * Verify if a SortEntry list is sorted descendent as an integer
		 * */
		public static boolean isSortedInDescendentOrderAsInteger(List<SortEntry> entries, String attributeName) {
			boolean f = false;
			Integer lastIntValue = 0;
			Integer currentIntValue;
			for (SortEntry entry: entries) {
				if (!f) {
					f = true;
					lastIntValue = Integer.parseInt(entry.getAttribute(attributeName));
				} else {
					currentIntValue = Integer.parseInt(entry.getAttribute(attributeName));
					if ((currentIntValue.compareTo(lastIntValue)) > 0)
						return false;
					lastIntValue = currentIntValue;
				}
			}
			return true;
		}

		/**
		 * Verify if a SortEntry list is sorted descendent as a pretty date
		 * */
		public static boolean isSortedInDescendentOrderAsPrettyDate(List<SortEntry> entries, String attributeName) {
			boolean f = false;
			Date lastDateValue = null;
			Date currentDateValue = null;
			for (SortEntry entry: entries) {
				if (!f){
					f = true;
					lastDateValue = new PrettyTimeParser().parse(entry.getAttribute(attributeName)).get(0);
				} else {
					currentDateValue = new PrettyTimeParser().parse(entry.getAttribute(attributeName)).get(0);
					if (currentDateValue.getTime() > lastDateValue.getTime() + 100)
						return false;
					lastDateValue = currentDateValue;
				}
			}
			return true;
		}

		/**
		 * Click on a sortable element to sort ascendent or descendent
		 * */
		public static void setSortableUpOrDown(String sortOrder, String locator) {
			WebElement sortUp = DriverManager.driver.findElement(By.xpath(locator));
			if (!sortUp.getAttribute("class").contains(sortOrder))
				CommonFunctions.click(LocatorType.Xpath, locator);
		}
		
		/**
		 * Scroll Element Into View
		 * */
		public static void scrollIntoView( String xpath ) {

			try {
				WebElement scroll = Locator.byXPath( xpath ).getElement();
				Actions scrollView = new Actions( DriverManager.driver );
				scrollView.moveToElement( scroll ).perform();
				//LogUtils.detailLog( "passed" );

			} catch( NoSuchElementException e ) {
				//LogUtils.detailLog( "failed ---- scrollIntoView" );
				//LogUtils.fileWriter( e );
				myAssertion.fail( "Failed to ScrollIntoView  - "+ xpath);
			}
		}
		
		/**
		 * Method to get the name of active view asset i.e. Grid or list
		 * */
		public static String getNameOfActiveViewAssets() {
			String activeViewName = DriverManager.driver.findElement(By.cssSelector("#" + getClassActiveTab() + " .toggle [class *= 'Active']")).getText();

			return activeViewName;
		}

		/**
		 * Method to get the locator for the asset Names  according to the active view
		 * */
		public static By getItemNameLocatorOfActiveView() {
			String activeViewName = getNameOfActiveViewAssets();
			By activeViewLocator;
			if (activeViewName.equals("Grid")) {
				//get the locator for asset Name(title) according to active Grid view
				activeViewLocator = By.cssSelector("#" + getClassActiveTab() + " .assets a.title");

			} else {
				//get the locator for asset Name(title) according to active List view
				activeViewLocator = By.cssSelector("#" + getClassActiveTab() + " .ajaxTable a.edit");
			}

			return activeViewLocator;
		}

		/**
		 * Get elements count by Selenium criteria
		 * */
		public static int getElementsCount(By criteria)	{
			return DriverManager.driver.findElements(criteria).size();				
		}

		/**
		 * Method to calculate the number total of pagination according to total items and selected item per page
		 * @param itemPerPage
		 * */
		public static int calculateNumberOfPagination(int itemPerPage, int totalCreateElements) {
			int numberPagination = totalCreateElements  / itemPerPage;
			if (totalCreateElements % itemPerPage > 0) {
				return numberPagination + 1;
			} else {
				return numberPagination;
			}
		}

		/**
		 * Method to get the active(selected) item per page option
		 * @param pagination i.e top or bottom
		 * */
		public static int getActiveItemPerPageOption() {
			WebElement  activeElemItemPerPage = DriverManager.driver.findElement(By.cssSelector("." + getClassActiveTab() + " .itemsPerPage [class *= 'Active']"));
			return Integer.parseInt(activeElemItemPerPage.getText());
		}

		/**
		 * Method to do click on pagination number option , if it is not active yet
		 * @param activePaginationLocator
		 * @param numberPaginationLocator
		 * */
		public static void clickOnPaginationNumberOption(By activePaginationLocator, String numberPaginationLocator) {
			WebElement activePaginationElement = DriverManager.driver.findElement(activePaginationLocator);
			WebElement numberPaginationElement = DriverManager.driver.findElement(By.xpath(numberPaginationLocator));
			if (!activePaginationElement.getText().equals(numberPaginationElement.getText())) {
				click(LocatorType.Xpath, numberPaginationLocator);
				waitUntilCompleteLoading();
			}
		}
		
		/**
		 * Method to calculate the number of item name that start with some prefix
		 * */
		public static int calculateNumberItemNameStartWithPrefix(String prefixName, List<String> itemNames) {
			int matchCounter = 0; 
			for (String itemName: itemNames) { 
				if (itemName.startsWith(prefixName)){ 
					matchCounter++; 
				} 
			}
			return matchCounter;
		}
		
		/**
		 * Method to calculate the number of item name that have their name equal than some prefix
		 * */
		public static int calculateNumberItemNameEqualToPrefix(String prefixName, List<String> itemNames) {
			int matchCounter = 0; 
			for (String itemName: itemNames) { 
				if (itemName.equals(prefixName)) { 
					matchCounter++; 
				} 
			}
			return matchCounter;
		}
		
		/**
		 * Method to get the total elements  created
		 * @param tabName i.e. Search,Draft,Rejected
		 * 
		 * */
		public static int getTotalCreateElements() {
			WebElement displayingElement = DriverManager.driver.findElement(By.cssSelector("#" + getClassActiveTab() + " .PropertiesPlugin h3"));
			String displayingText = displayingElement.getText();
			String labels[] = displayingText.split("of");
			int totalDisplayElements = Integer.valueOf(labels[1].trim());
			return totalDisplayElements;
		}
		
		/**
		 * This method gets the class name of the active tab in the list container
		 * @throws Throwable
		 */
		public static String getClassActiveTab() {
			return DriverManager.driver.findElement(By.cssSelector("#listContainer li[style*='block;']")).getAttribute("class");
		}
		
		
		/**
		 * Wait for an Element is present on the DOM of a page,regardless of being displayed or not.
		 * @param locatorType
		 * @param elementValue
		 * @param time
		 */
		public static void waitForElementPresent( LocatorType type, String elementValue, long time ) {
			try {
				
				By by=  WebDriverWaitByLocator.getBy(type, elementValue);
				new WebDriverWait( DriverManager.driver, time).until( ExpectedConditions.presenceOfElementLocated(by) );

			} catch( WebDriverException e ) {
				myAssertion.fail( "Failed to wait for Element present - "+ elementValue);
			}
		}

		/**
		 * Wait until that the loading icon disappear
		 */
		public static void waitUntilCompleteLoading() {
			waitForElementPresent(LocatorType.cssSelector, "#loading[style*='none']", 10);
		}
		
		/**
		 * Wait until that the processing progress bar disappear when selecting current versions
		 */
		public static void waitUntilCompleteProcessing() {
			waitForElementPresent(LocatorType.cssSelector, "#processing[style*='none']", 10);
		}
		
        /**
        * Wait until that the processing progress bar is completed after some actions like save
        */
        public static void waitForActiveTab() {
        	CommonFunctions.waitForElement(LocatorType.cssSelector, "#listContainer li[style*='block;']", 10);
        }
		
		/**
		 * Method to get locator of active pagination option according the pagination bar position i.e. next, previous, 1,2 ..
		 * @param paginaitonBarPosition i.e. header, footer 
		 * */
		public static By getActivePaginationOptionLocator(String paginationBarPosition) {
			return By.cssSelector("." + getClassActiveTab() + " [class *='" + paginationBarPosition + "'] .pagination .active");
		}

		/**
		 * Method to get string locator of a number pagination option according the pagination bar position i.e. 1,2 ..
		 * @param numberPagination
		 * @param paginaitonBarPosition i.e. header, footer
		 * */
		public static String getNumberPaginationOptionLocator(int numberPagination, String paginationBarPosition) {
			return "//div[@id = '" + getClassActiveTab()+ "']//div[contains(@class, '" + paginationBarPosition + "')]//ul[@class='pagination']//li[./text()= '" + numberPagination + "']";
		}

		/**
		 * Method to get locator of previous pagination option according the pagination bar position
		 * @param paginaitonBarPosition i.e. header, footer
		 * */
		public static By getPreviousPaginationOptionLocator(String paginationBarPosition) {
			return By.cssSelector("." + getClassActiveTab() + " [class *='" + paginationBarPosition + "'] .pagination li[class ^= 'previous']");
		}

		/**
		 * Method to get locator of next pagination option according the pagination bar position
		 * @param paginaitonBarPosition i.e. header, footer
		 * 
		 * */
		public static By getNextPaginationOptionLocator(String paginationBarPosition) {
			return By.cssSelector("." + getClassActiveTab() + " [class *='" + paginationBarPosition + "'] .pagination li[class ^= 'next']");
		}

		/**
		 * Method to do click over some element according to given locator
		 * @param locator
		 * 
		 * */
		public static void click (By locator) {
			WebElement visibleElement = (new WebDriverWait(DriverManager.driver, 10)).until(ExpectedConditions.presenceOfElementLocated(locator));
			Boolean elementVisible = visibleElement.isDisplayed();
			Assert.assertTrue(elementVisible);

			WebElement wait = new WebDriverWait(DriverManager.driver, 10).until(ExpectedConditions.visibilityOf(visibleElement));

			Actions scrollToElement = new Actions(DriverManager.driver);
			scrollToElement.moveToElement(wait).perform();				
			visibleElement.click();
		}
		
		/**
		 * Wait until a element is clickable
		 * */
		public static void waitUntilElementToBeclickable (By element) {
			WebDriverWait wait = new WebDriverWait(DriverManager.driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}

		/**
		 * Wait until the load page is ready
		 * */
		public static void waitForLoadPage() {
			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
				}
			};
			WebDriverWait wait = new WebDriverWait(DriverManager.driver, 10);
			wait.until(pageLoadCondition);
		}

		/**
		 * Method to get a list with the names of created items with an specific prefix
		 * @param prefix  i.e. "sf.item" or "ssi.item" or "brand.asset"
		 * @param itemNamesLocator
		 * */
		public static List<String> getListOfItemNamesWithPrefix(String prefix, By itemNamesLocator) {
			List<WebElement> elementItems = DriverManager.driver.findElements(itemNamesLocator);
			CommonFunctions.sleep(1000);
			List<String> itemNames = new ArrayList<String>();
			for (WebElement elementItem:elementItems) {
				if (elementItem.getText().startsWith(prefix)) {
					itemNames.add(elementItem.getText());
				}
			}
			return itemNames;
		}
		
		/**
		 * Method to get a webElement list of pagination options acording to pagination bar position
		 * @param paginationBarPosition i.e. header or footer
		 * */
		public static List<WebElement> getListOfPaginationOptions(String paginationBarPosition) {
			String activeTab = CommonFunctions.getClassActiveTab();
			return DriverManager.driver.findElements(By.cssSelector("." + activeTab + " [class*='" + paginationBarPosition + "'] .pagination li"));
		}
		
		/**
		 * Method to create a string list in order to compare with the number pagination options list
		 * @param totalNumberOfPagination
		 * */
		public static List<String> createNumberPaginationOptionList(int totalNumberOfPagination) {
			List<String> paginationList = new ArrayList<String>();

			if (totalNumberOfPagination > 8){ //have ellipsis
				for (int i = 1; i <=5; i++) {
					paginationList.add(i + "");
				}
				paginationList.add("...");
				paginationList.add((totalNumberOfPagination-1) + "");
				paginationList.add(totalNumberOfPagination + "");
			} else {
				for (int i = 1; i <=totalNumberOfPagination; i++) {
					paginationList.add(i + "");
				}
			}
			return paginationList;
		}
		
		/**
		 * Method to select a button on Overlay class:
		 * Reject, Cancel, Save, Publish, Re-Publish, Publish and Email Watchers
		 * Archive, Delete, Save Changes, Approve, Deactivate
		 * @param buttonName
		 */
		public static void clickOnOverlayButton(String buttonName){
			try {
				CommonFunctions.click(By.xpath(".//div[@id='overlay']//a[contains(text(),'"+buttonName+"')]"));
				CommonFunctions.waitForActiveTab();
			} catch (Exception e) {
				Assert.fail("The button " +buttonName+" doesn't exist");
			}			
		}
}
