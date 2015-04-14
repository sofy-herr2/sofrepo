package onbrand;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import onbrand.tools.DriverManager;

public class CommonCode {

	// ------------------------------------------------------GIVEN----------------------------------------------//
	
	//Given Login to the system "username" "password"
	@Given("^Login to the system \"([^\"]*)\" \"([^\"]*)\"$")
	public void Login_to_the_system(String username, String password) throws Throwable {

		DriverManager.driver.navigate().to(Login.baseURL);
		
		DriverManager.driver.findElement(By.id("email")).sendKeys(username);
		DriverManager.driver.findElement(By.id("password")).sendKeys(password);
		
		DriverManager.driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertTrue(DriverManager.driver.getTitle().contains("Dashboard | Home"));
		
	}
	
	
	
	// ------------------------------------------------------WHEN----------------------------------------------//
			
    //When Navigate to "tab" tab
	@When("^Navigate to \"([^\"]*)\" tab$")
	public void Navigate_to_tab(String tab) throws Throwable {
		
		CommonFunctions.click(LocatorType.LinkedText,  tab);		
	}
	
	//When Add taxonomy "Parent"
    //| child 1 |
    //| child 2 |
    //| child n |
	@When("^Add taxonomy \"([^\"]*)\"$")
	public void Add_taxonomy(String parent, DataTable dataTable) throws Throwable {
		
		CommonFunctions.doubleClick(LocatorType.LinkedText, parent);			
		java.util.List<java.util.List<String>> tableData = dataTable.raw();	
							
		for (int rowCount = 0; rowCount < tableData.size(); rowCount++) {
			
			CommonFunctions.click(LocatorType.Xpath, "//div//a[.='"+tableData.get(rowCount).get(0)+"']/preceding-sibling::input[@type='checkbox']");	
		}		
	}
	
	/**
	 * Click some menu
	 * @param nameMenu
	 * @throws Throwable
	 */
	@When("^Navigate to \"([^\"]*)\" menu$")
	public void Navigate_to_menu(String nameMenu) throws Throwable {
		VerificationFunctions.verifyElementsVisible(LocatorType.LinkedText, nameMenu);
		CommonFunctions.click(LocatorType.LinkedText, nameMenu);
		CommonFunctions.waitForActiveTab();
	}
	
	//When Upload file "C:\test\path...."
	@When("^Upload file \"([^\"]*)\"$")
	public void Upload_file(String file) throws Throwable {

		Navigation.switchToFrame("popupIframe");		
		CommonFunctions.browseFile(By.xpath("//input[@type='file']"), file);
		//CommonFunctions.browserWait(10L);
		By addButton = By.xpath("//input[contains(@class,'green') and @value='Add']");
		CommonFunctions.waitUntilElementToBeclickable(addButton);
		CommonFunctions.click(addButton);
		Navigation.switchtoDefaultFrame();
		Navigation.refreshPage();
		CommonFunctions.waitForLoadPage();		
	}
	
	// ------------------------------------------------------THEN----------------------------------------------//
	
	//Then Verify taxonomy "Parent"
    //| child 1 |
    //| child 2 |
    //| child n |
	@Then("^Verify taxonomy \"([^\"]*)\"$")
	public void Verify_taxonomy(String parent, DataTable dataTable) throws Throwable {
		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div//a[.='"+parent+"']/preceding-sibling::input[@type='checkbox'and @checked='checked']");
		java.util.List<java.util.List<String>> tableData = dataTable.raw();	
				
		for (int rowCount = 0; rowCount < tableData.size(); rowCount++) {
			
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div//a[.='"+tableData.get(rowCount).get(0)+"']/preceding-sibling::input[@type='checkbox'and @checked='checked']");
				
		}
	}
	
	// Navigate to admin section 
	@When("^Navigate to admin section$")
	public void Navigate_to_admin_section() throws Throwable {
	   Navigation.goToCMS();
	}
	
	// User logs out from the system
	//Then Logout from the system
	@Then("^Logout from the system$")
	public void Logout_from_the_system() throws Throwable {
			
			CommonFunctions.click(LocatorType.Xpath, "//li[@class='user']/a");
			CommonFunctions.click(LocatorType.Xpath,"//*[contains(@class,'brandOption last')]/a[contains(text(),'Logout')]");
			VerificationFunctions.verifyURL("/login/");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//a[@class='backToLogin']");			    
	}
	
	/**
	 * Verify all the components above tabs section
	 * * @throws Throwable
	 */
	@Then("Verify \"([^\"]*)\" page \"([^\"]*)\"$")
	public void Verify_page(String nameTab, String createButton) throws Throwable {
		
		try {
			//Validate that the main page elements are visible
			//Wait for the page to be displayed
			CommonFunctions.waitForElement(LocatorType.Xpath, "//h1[@id='title'][contains(text(),'"+nameTab+"')]", 10);
			
			//Verify Search section and Reset buttons are present
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[@id='ajaxSearchInput']");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Search')]");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Search");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Reset");
			
			if(createButton.equals("true"))
				VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Create New')]");
		} catch (Exception e) {
			Assert.fail("There is some item missing in Brand Assets page");
		}
		
	}
	
    public List<String> getTabNames() {
        List<String> tabs = new ArrayList<String>();
        List<WebElement> tabNames = DriverManager.driver.findElements(By.cssSelector(".nav li"));

        for (WebElement tab : tabNames)
               tabs.add(tab.getText());
        return tabs;  
    }
    
    /**
	 * Verifying all the components under each tab
	 */
	@And("Verify tab components$")
	public void Verify_tab_components() {
		List<String> tabList=getTabNames();
				
		String classTab="";
		
		for (int i = 0; i < tabList.size(); i++) {
			Assert.assertEquals(true, DriverManager.driver.findElement(By.xpath("//li[contains(text(),'"+tabList.get(i)+"')]")).isDisplayed());
			CommonFunctions.click(LocatorType.Xpath, "//li[contains(text(),'"+tabList.get(i)+"')]");
			classTab= CommonFunctions.getClassActiveTab();
					
			try {
				Locator locator=Locator.byCss("."+classTab+" .toggle");
				
				//Verify Sort list section
				VerificationFunctions.verifyElementVisible(LocatorType.cssSelector, "."+classTab+" .sortablePlugin>label");
				VerificationFunctions.verifyElementVisible(LocatorType.cssSelector, "."+classTab+" .sortablePlugin>select");
				
				//Verify Dropdown and Displaying sections 
				VerificationFunctions.verifyElementVisible(LocatorType.cssSelector, "."+classTab+" .sortable.down");
				VerificationFunctions.verifyElementVisible(LocatorType.cssSelector, "."+classTab+" .PropertiesPlugin>h3");
				
				//Verify Items per page section
				VerificationFunctions.verifyElementVisible(LocatorType.cssSelector, "."+classTab+" .itemsPerPage>label");
				VerificationFunctions.verifyElementVisible(LocatorType.cssSelector, "."+classTab+" .itemsPerPage div[class*='itemCount']");
						
				if (locator.isPresent()) {
					VerificationFunctions.verifyElementVisible(LocatorType.cssSelector, "."+classTab+" .toggle div[class*='Grid']");
					VerificationFunctions.verifyElementVisible(LocatorType.cssSelector, "."+classTab+" .toggle div[class*='List']");					
				}
				
				if(DriverManager.getCurrentUrl().contains("brand_assets")){
					VerificationFunctions.verifyElementVisible(LocatorType.cssSelector, "."+classTab+" .bulkedit_stackcount");
				}
				
			} catch (Exception e) {
				Assert.fail("There is some item missing in "+tabList.get(i)+" tab");
			}		
		}		
	}
	
	/**
	 * Verify that some element is displayed in the list
	 * @param name
	 * @throws Throwable
	 */
	@Then("Verify \"([^\"]*)\" item$")
	public void Verify_item(String name) throws Throwable{		
		
		try {	    	
    			//Look for the item
				//VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'"+name+"')]");
				CommonFunctions.waitForActiveTab();
				VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'"+name+"')]");
				Assert.assertTrue(true);
				
    		} catch (Exception e) {
    			//Catching the exception and displaying an appropriate error
    			Assert.fail("The item "+name+" was not displayed");
    			throw(e);
    		}			    		    	
	}
	
	/**
	 * Verify that element is not visible on the list
	 * @param name
	 * @throws Throwable
	 */
	@Then("Verify \"([^\"]*)\" item is not displayed$")
	public void Verify_item_is_not_displayed(String name) throws Throwable{		
		SupportingFile sf=new SupportingFile();
		sf.Set_the_items_per_page_option("45");
		
		CommonFunctions.waitForActiveTab();  
		String nameList[];		
		
	  try {	   	
		  //Look for the item(s)
		  if(name.contains(",")){
				
				Select_the_view("List"); 
				nameList=name.split(",");
							
				for (int i = 0; i < nameList.length; i++) {
					VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath, "//a[contains(text(),'"+nameList[i]+"')]");					
				}
		  }else{
			VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath, "//a[contains(text(),'"+name+"')]");
		  }    		
    		Assert.assertTrue(true);    		
    	} catch (Exception e) {
    		//Catching the exception and displaying an appropriate error
    		Assert.fail("The item "+name+" is still displayed");
    		throw(e);
    	}				    	
	}
	
	/**
	 * Select the grid or list view
	 * @throws Throwable
	 */
	@When("^Select the \"([^\"]*)\" view$")
	public void Select_the_view(String listMode)     throws Throwable {
		String locator = "." + CommonFunctions.getClassActiveTab() + " .toggle div[class*='" + listMode + "']";
		WebElement view = DriverManager.driver.findElement(By.cssSelector(locator));
		if (!view.getAttribute("class").contains("Active")) {
			CommonFunctions.click(LocatorType.cssSelector, locator);
		}
		CommonFunctions.waitUntilCompleteLoading();
	}

	/**
	 * Click on an specific items per page option
	 * @throws Throwable
	 */
	@When("^Set the items per page option to (\\d+)$")
	public void Set_the_items_per_page_option_to(int itemPerPage) throws Throwable {
		String locator = "//li[@class='" + CommonFunctions.getClassActiveTab() + "']//div[@class='itemsPerPage']//div[text()='" + itemPerPage +"']";
		WebElement item = DriverManager.driver.findElement(By.xpath(locator));
		if (!item.getAttribute("class").contains("Active"))
			CommonFunctions.click(LocatorType.Xpath, locator);
		CommonFunctions.waitUntilCompleteLoading();
	}
}
