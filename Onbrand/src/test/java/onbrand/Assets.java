package onbrand;



import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.Given;
import onbrand.LocatorType;
import onbrand.Navigation;
import onbrand.CommonFunctions;
import onbrand.VerificationFunctions;
import onbrand.tools.DriverManager;

public class Assets  {
	 

	

	@Then("^Verify the fields in assets search page$")
	public void Verify_the_fields_in_assets_search_page() throws Throwable {
		
		//verify the assets page is opened on clicking on assets menu, verify the title of the page
		CommonFunctions.browserWait(5L);
		Boolean assetsPageIsDisplayed = new WebDriverWait(DriverManager.driver,10).until(ExpectedConditions.titleContains("Assets"));
		Assert.assertTrue(assetsPageIsDisplayed);
		//if above assertion is failed then exit the function
	
		//verify the submenus "search", "collections" and "upload"	
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Search");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Collections");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Upload");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "textField");
		// verify the search icon near the search text field
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//form[label[contains(text(),'Search Assets')]]/input[@type='submit']");
		//verify the results label
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h2[contains(text(),'results')]");
		//verify the save search label and it is in disabled mode
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//a[@class='saveSearchAction inactive' and contains(text(),'save')]");
		//verify the select / deselect link
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//a[@class='selectDeselect' and contains(text(),'Select')]");
		// verify Items on Asset Page and Buttons
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//li[contains(@class,'files')]");		
		// verify Checkbox on Assets
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//li[contains(@class,'files')]//input[@type='checkbox']");
		// verify Download Button on Asset
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//li[contains(@class,'files')]//li[contains(@id,'download')]");
		// verify Add to Basket Icon on Asset
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//li[contains(@class,'files')]//a[contains(@class,'addBasket')]");
		// verfiy Add to Watchlist Icon on Asset
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//li[contains(@class,'files')]//a[contains(@class,'addFav')]");
		// All Tab, Assets Tab
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//li[contains(@class,'first')]//a[contains(text(),'All')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//li[contains(@class,'active last')]//a[contains(text(),'Assets')]");
		// verify Sidebar Basket and basket should be empty
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//*[@class='basket']//a[contains(text(),'Basket (')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//p[contains(text(),'Your basket is empty')]");		
		//verify sort by label and the various SortBy Options
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//*[@class='sort']//a[contains(text(),'Sort')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//H4[.='Relevance']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//H4[.='Last Updated']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//H4[.='Last Created']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//H4[.='Size']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//H4[.='Rating']");
	
		
	}
	
	@Then("^Verify the fields in collections page$")
	public void Verify_the_fields_in_collections_page() throws Throwable {
		
		//verify the collection sections
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//*[@id='myCollectionsContainer']/h3[contains(text(),'My Collections')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//*[@id='sharedCollectionsContainer']/h3[contains(text(),'Invited Collections')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//*[@id='publicCollectionsContainer']/h3[contains(text(),'Public Collections')]");
		//verify the new collection button
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//*[@id='myCollectionsContainer']/h5/a[contains(text(),'New')]");
	  
	}
	
	@Then("^Verify the fields in upload page$")
	public void Verify_the_fields_in_upload_page() throws Throwable {
		
		//verify the upload page sections
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//h1[.='Upload']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[@id='metadataRequired']/h3");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//*[@id='awaitingApproval']/h3");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//*[@id='rejected']/h3");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//*[@id='approved']/h3");
		//verify the upload button
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//input[@value='+ Upload Assets']");
	   
	}

	@Then("^Verify the fields in metadata required page \"([^\"]*)\"$")
	public void Verify_the_fields_in_metadata_required_page(String filePath) throws Throwable {
		 
		//verify the fields present in the metadata required page
		CommonFunctions.browserWait(5L);
		CommonFunctions.click(LocatorType.LinkedText, filePath);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h2[contains(text(),'Edit')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//label[contains(text(),'Name')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//label[contains(text(),'Description')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//label[contains(text(),'Keywords')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//label[contains(text(),'Client')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//*[@id='usageRights']/h3[contains(text(),'Usage')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//label[contains(text(),'Restrictions')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//label[contains(text(),'expiry')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//*[@id='hasExpiryDate']");
		VerificationFunctions.verifyElementVisible(LocatorType.id,"item_name");
		VerificationFunctions.verifyElementVisible(LocatorType.id,"item_description");
		VerificationFunctions.verifyElementVisible(LocatorType.id,"item_keywords");
		//verificationFunctions.verifyElementVisible(LocatorType.Xpath,"//select[@id='ITEM_ATT31_0']/option[contains(text(),'None')]|.//select[@id='ITEM_ATT31_0']/option[contains(text(),'Limited')]|.//select[@id='ITEM_ATT31_0']/option[contains(text(),'Unlimited')]");
		VerificationFunctions.verifyDropListOptionCss("select[name=ITEM_ATT31_0]", "None" );
		VerificationFunctions.verifyDropListOptionCss("select[name=ITEM_ATT31_0]", "Limited" );
		VerificationFunctions.verifyDropListOptionCss("select[name=ITEM_ATT31_0]", "Unlimited" );
		CommonFunctions.selectDropList("//*[@id='ITEM_ATT31_0']", "Unlimited");
		
		/* Select select = new Select(TestHooks.driver.findElement(By.id("ITEM_ATT31_0")));
		select.selectByValue("Limited");
		
		List<WebElement> dropdownlist = select.getOptions();
		for(WebElement option: dropdownlist){
			
			System.out.println("dropdown value is " + option.getText());			
		}*/
		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[@value='Save & Approve']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[@value='Save & Send for Approval']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[@value='Save']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[@value='Cancel without saving']");
		
		//verify the mandatory fields present in the metadata required page
		
		//name field is mandatory, do not enter the name and click on save to verify the error message
		CommonFunctions.clearText(LocatorType.id, "item_name");
		CommonFunctions.click(LocatorType.Xpath, "//*[@value='Save & Approve']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@class='errorContainerClass']/strong[contains(text(),'field')]");
		Navigation.refreshPage();
		CommonFunctions.clearText(LocatorType.id, "item_name");
		CommonFunctions.click(LocatorType.Xpath, "//*[@value='Save & Send for Approval']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@class='errorContainerClass']/strong[contains(text(),'field')]");
		Navigation.refreshPage();
		CommonFunctions.clearText(LocatorType.id, "item_name");
		CommonFunctions.click(LocatorType.Xpath, "//*[@value='Save']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@class='errorContainerClass']/strong[contains(text(),'field')]");
				
		//description field is mandatory, do not enter the description and click on save to verify the error message
		Navigation.gotoURL("/files/upload/");
		CommonFunctions.browserWait(5L);
		CommonFunctions.click(LocatorType.LinkedText, filePath);
		CommonFunctions.clearText(LocatorType.id, "item_description");
		CommonFunctions.click(LocatorType.Xpath, "//*[@value='Save & Approve']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@class='errorContainerClass']/strong[contains(text(),'field')]");
		Navigation.refreshPage();
		CommonFunctions.clearText(LocatorType.id, "item_description");
		CommonFunctions.click(LocatorType.Xpath, "//*[@value='Save & Send for Approval']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@class='errorContainerClass']/strong[contains(text(),'field')]");
		Navigation.refreshPage();
		CommonFunctions.clearText(LocatorType.id, "item_description");
		CommonFunctions.click(LocatorType.Xpath, "//*[@value='Save']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@class='errorContainerClass']/strong[contains(text(),'field')]");
		
		//expiry date is mandatory, check the expiry date and and click on save to verify the error message
		Navigation.gotoURL("/files/upload/");
		CommonFunctions.browserWait(5L);
		CommonFunctions.click(LocatorType.LinkedText, filePath);
		CommonFunctions.checkBox(LocatorType.id, "hasExpiryDate");
		CommonFunctions.click(LocatorType.Xpath, "//*[@value='Save & Approve']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@class='errorContainerClass']/strong[contains(text(),'An expiry date is required.')]");
		Navigation.refreshPage();
		CommonFunctions.checkBox(LocatorType.id, "hasExpiryDate");
		CommonFunctions.click(LocatorType.Xpath, "//*[@value='Save & Send for Approval']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@class='errorContainerClass']/strong[contains(text(),'An expiry date is required.')]");
		Navigation.refreshPage();
		CommonFunctions.checkBox(LocatorType.id, "hasExpiryDate");
		CommonFunctions.click(LocatorType.Xpath, "//*[@value='Save']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@class='errorContainerClass']/strong[contains(text(),'An expiry date is required.')]");		
			
	}


	@Then("^Click on asset \"([^\"]*)\"$")
	public void Click_on_asset(String assetName) throws Throwable {
		
		CommonFunctions.click(LocatorType.LinkedText, "Assets");
		//javaScriptExe("window.scrollBy(0,290);");
		DriverManager.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		CommonFunctions.click(LocatorType.Xpath, "//div//h3//strong[contains(node(),'"+assetName+"')]");

	}
	
	@Then("^Search for an asset and click on asset \"([^\"]*)\"$")
	public void Search_for_an_asset_and_click_on_asset(String assetName) throws Throwable {
	    
		// Search for an Asset Using Name
		Navigation.gotoURL("/files/");
		WebElement searchField = DriverManager.driver.findElement(By.id("textField"));
		searchField.sendKeys(assetName);
		searchField.submit();
		searchField.sendKeys(assetName);
		CommonFunctions.pressKey("Escape");
		CommonFunctions.pressKey("Enter");
		CommonFunctions.browserWait(10L);			
		// Verify Name, Results and Search search option
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//*[@class='resultBar']/h2[contains(text(),'results for')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//*[contains(node(),'save search')]");		

		// Scroll Into View Using JS//wait
		CommonFunctions.javaScriptExe("window.scrollBy(0,290);");
		CommonFunctions.javaScriptExe("window.scrollBy(100,600);");
		DriverManager.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String assetname = assetName.substring(0, assetName.length()-1);
		System.out.println("answer "+ assetname);
		CommonFunctions.browserWait(10L);

		// Click Asset
		CommonFunctions.click(LocatorType.Xpath,"//*[contains(@class,'files')]//div//h3//strong[contains(node(),'"+assetname+"')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//*[@id='assetBar']/h2[contains(text(),'"+assetname+"')]");

	}
	
	@Then("^Search for an asset \"([^\"]*)\"$")
	public void Search_for_an_asset(String assetName) throws Throwable {
		
		// Search for an Asset Using Name
		Navigation.gotoURL("/files/");
		WebElement searchField = DriverManager.driver.findElement(By.id("textField"));
		searchField.sendKeys(assetName);
		searchField.submit();
		searchField.sendKeys(assetName);
		CommonFunctions.pressKey("Escape");
		CommonFunctions.pressKey("Enter");
								
		// Verify Name, Results and Search search option
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//*[@class='resultBar']/h2[contains(text(),'results for')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//*[contains(node(),'save search')]");		

		// Scroll Into View Using JS//wait
		CommonFunctions.javaScriptExe("window.scrollBy(0,290);");
		CommonFunctions.javaScriptExe("window.scrollBy(100,600);");
		DriverManager.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//String assetname = assetName.substring(0, assetName.length()-1);
		//System.out.println("answer "+ assetname);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div//h3//strong[contains(node(),'"+assetName+"')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div//h3//strong[contains(node(),'"+assetName+"')]");

	    
	}


	@Then("^Search for a non existing asset with name \"([^\"]*)\"$")
	public void Search_for_a_non_existing_asset_with_name(String assetName) throws Throwable {
		
		// Search for an Asset that does not exist Using Name
		Navigation.gotoURL("/files/");
		CommonFunctions.sleep(2000);
		CommonFunctions.clearAndSendKeys(By.id("textField"), assetName);
		CommonFunctions.pressKey("Enter");
		CommonFunctions.click(LocatorType.Xpath,"//div[contains(@class,'fullFluid')]//input[2]");
		CommonFunctions.browserWait(10L);
		
		// Verify No Results and Search search option
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, assetName);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//*[contains(node(),'0 results for ')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//*[contains(node(),'save search')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//P[contains(text(),'Sorry, no assets results were found for ')]");

	}
	
	@Then("^Search for an asset \"([^\"]*)\" and reset$")
	public void Search_for_an_asset_and_reset(String assetName) throws Throwable {
		
		// Search for an Asset Using Name
		Navigation.gotoURL("/files/");
		WebElement searchField = DriverManager.driver.findElement(By.id("textField"));
		searchField.sendKeys(assetName);
		searchField.submit();
		searchField.sendKeys(assetName);
		CommonFunctions.pressKey("Escape");
		CommonFunctions.pressKey("Enter");
						
		// Verify Name, Results and Search search option
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//*[@class='resultBar']/h2[contains(text(),'results for')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//*[contains(node(),'save search')]");		

		// Scroll Into View Using JS//wait
		CommonFunctions.javaScriptExe("window.scrollBy(0,290);");
		CommonFunctions.javaScriptExe("window.scrollBy(100,600);");
		DriverManager.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String assetname = assetName.substring(0, assetName.length()-1);
		System.out.println("answer "+ assetname);
		
		//verify the results
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//*[contains(@class,'files')]//div//h3//strong[contains(node(),'"+assetname+"')]");
		//reset the results
		CommonFunctions.click(LocatorType.Xpath, "//li[@class='reset']/a[contains(text(),'reset')]");
		VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath, "//li[@class='reset']/a[contains(text(),'reset')]");
	  
	}
	
	@Then("^Search for an asset \"([^\"]*)\" and remove query$")
	public void Search_for_an_asset_and_remove_query(String assetName) throws Throwable {
	   
		// Search for an Asset Using Name
		Navigation.gotoURL("/files/");
		WebElement searchField = DriverManager.driver.findElement(By.id("textField"));
		searchField.sendKeys(assetName);
		searchField.submit();
		searchField.sendKeys(assetName);
		CommonFunctions.pressKey("Escape");
		CommonFunctions.pressKey("Enter");
								
		// Verify Name, Results and Search search option
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//*[@class='resultBar']/h2[contains(text(),'results for')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//*[contains(node(),'save search')]");		

		// Scroll Into View Using JS//wait
		CommonFunctions.javaScriptExe("window.scrollBy(0,290);");
		CommonFunctions.javaScriptExe("window.scrollBy(100,600);");
		DriverManager.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String assetname = assetName.substring(0, assetName.length()-1);
		System.out.println("answer "+ assetname);
		
		//verify the results
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//*[contains(@class,'files')]//div//h3//strong[contains(node(),'"+assetname+"')]");
		CommonFunctions.browserWait(5L);
		//reset the results
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//li//a[contains(@rel,'query')]|a[contains(@title,'Remove')]");
		CommonFunctions.click(LocatorType.Xpath, "//li//a[contains(@rel,'query')]|a[contains(@title,'Remove')]//em[.='query:']");
		VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath, "//li[@class='reset']/a[contains(text(),'reset')]");
	}

	
	@Then("^Search for page facet \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void Search_for_page_facet(String parentFacet, String childFacet, String assetName) throws Throwable {	
	
		// CLick a page Facet and Verify 
		Navigation.gotoURL("/files/");	
		// look for the child facet under the parent facet, if the child facet is not present then click on view all link
		try{
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//li[@class='closed']/h3/a[.='"+parentFacet+"']");
			CommonFunctions.click(LocatorType.Xpath, "verificationFunctions.verifyElementVisible(LocatorType.Xpath,	");
			
		}
		catch(WebDriverException e){
		try{
			 CommonFunctions.click(LocatorType.Xpath, "//li//a[@rel='"+childFacet+"']//h4[contains(text(),'"+childFacet+"')]");
			
		}catch(WebDriverException e1){
			
			CommonFunctions.click(LocatorType.Xpath, "//li[h3/a[contains(text(),'"+parentFacet+"')]]/ul/li[@class='last']/h4/a[contains(text(),'View')]");
			CommonFunctions.click(LocatorType.Xpath, "//div[@class='facetDialog']//ul[@class='list']/li/a[@title='add "+childFacet+"']/h4/span[contains(text(),'"+childFacet+"')]");
			
		}
		finally{
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//*[contains(@class,'files')]//div//h3//strong[contains(node(),'"+assetName+"')]");
		}
		}
	    //commonFunctions.click(LocatorType.Xpath, "//li[h3/a[contains(text(),'"+parentFacet+"')]]/ul/li[@class='last']/h4/a[contains(text(),'View')]");
	    //Boolean result =  verificationFunctions.isElementExist(LocatorType.Xpath, "//div[@class='facetDialog']//ul[@class='list']/li/a[@title='add "+childFacet+"']/h4/span[contains(text(),'"+childFacet+"')]");
	    //System.out.println("result is " + result);
	    //commonFunctions.click(LocatorType.Xpath, "//div[@class='facetDialog']//ul[@class='list']/li/a[@title='add "+childFacet+"']/h4/span[contains(text(),'"+childFacet+"')]");
	    //verificationFunctions.verifyElementVisible(LocatorType.Xpath,"//*[contains(@class,'files')]//div//h3//strong[contains(node(),'"+assetName+"')]");    
	    
	}
	

	@Then("^Reset the page facet search \"([^\"]*)\" \"([^\"]*)\"$")
	public void Reset_the_page_facet_search(String parentFacet, String childFacet) throws Throwable {
	
		CommonFunctions.click(LocatorType.Xpath, "//li[@class='active last']/a[@rel='"+childFacet+"']/h4[.='"+childFacet+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//li[h3/a[contains(text(),'"+parentFacet+"')]]/ul/li[@class='last']/h4/a[contains(text(),'View')]");
    
	}

	//Add keyword to Asset and save
	//then search for the asset by clicking on keyword facet 
	//Asset Name remove filter after search that's the problem
	@Then("^Add keywords to an asset \"([^\"]*)\" \"([^\"]*)\"$")
	public void Add_keywords_to_an_asset(String assetName, String keywords) throws Throwable {
		//Search an Asset Edit and Add Keywords
		Navigation.gotoURL("/files/");
		CommonFunctions.sleep(1000);
		CommonFunctions.clearAndSendKeys(By.id("textField"), assetName);
		CommonFunctions.pressKey("Enter");
		CommonFunctions.click(LocatorType.Xpath,"//div[contains(@class,'fullFluid')]//input[2]");
		CommonFunctions.browserWait(10L);
		//commonFunctions.click(LocatorType.Xpath, "//h3/strong[.='"+assetName+"']");
		CommonFunctions.click(LocatorType.Xpath,"//*[contains(@class,'files')]//div//h3//strong[contains(node(),'"+assetName+"')]");
		CommonFunctions.click(LocatorType.LinkedText, "edit");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//span[@class='title'][text()='"+assetName+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "item_keywords");
		CommonFunctions.clearAndSendKeys(By.id("item_keywords"), keywords);
		CommonFunctions.click(LocatorType.LinkedText, "Save");
		CommonFunctions.sleep(1000);
	    
	}
	

	@Then("^Save a search \"([^\"]*)\" \"([^\"]*)\"$")
	public void Save_a_search(String name, String keywords) throws Throwable {

		//Search Asset and Save Search
		CommonFunctions.click(LocatorType.LinkedText, "Assets");
		CommonFunctions.sleep(3000);
		CommonFunctions.clearAndSendKeys(By.id("textField"), keywords);
		CommonFunctions.click(LocatorType.Xpath,"//div[contains(@class,'fullFluid')]//input[2]");
		CommonFunctions.click(LocatorType.Xpath, "//a[contains(node(),'save search')]");
		CommonFunctions.clearAndSendKeys(By.id("title"), name);
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Save Search']");
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Go to Saved Searches']");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, name);
		
	}
	
	@Then("^Delete the saved search \"([^\"]*)\"$")
	public void Delete_the_saved_search(String name) throws Throwable {
		
		//Delete a Saved Search
		Navigation.gotoURL("/home/saved_searches/");
		CommonFunctions.click(LocatorType.Xpath, "//*[@class='remove']");
		VerificationFunctions.verifyElementNotVisible(LocatorType.LinkedText, name);
	}
	
	@Then("^Verify the asset view page \"([^\"]*)\"$")
	public void Verify_the_asset_view_page(String assetName) throws Throwable {
		
		// Verify Buttons
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//button[contains(node(),'Add to Watchlist')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//button[contains(node(),'Share Asset')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//button[contains(node(),'Link Asset')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//button[contains(node(),'Add to Watchlist')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//button[contains(node(),'Add to Basket')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//button[contains(node(),'Download Now')]");

		// Verify Asset Name, Edit Link and Full Screen Button
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h2[contains(node(),'"+ assetName + "')]");	
		try{
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@id='assetDetails']//div[@label='Image Information' and @class='open']");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//li[@class='fullscreen']//a[contains(text(),'fullscreen')]");
			CommonFunctions.click(LocatorType.Xpath,"//li[@class='fullscreen']//a[contains(text(),'fullscreen')]");	
		}
		catch(WebDriverException e1){
					
		}
		
		CommonFunctions.pressKey("Escape");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "edit");

		// Verify File info, Collection, Articles and Linked Assets Tabs
		VerificationFunctions.verifyElementVisible(LocatorType.id, "assetDetails");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "tab_file_info");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "tabCollection");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "tabRelatedArticles");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "tab_linked");
				

		// Verify File Info Tab (Details, Metadata, Image Information and Rating)
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Details");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Metadata");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Image Information");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Rating");

		// Verify Details
		try{			
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@id='assetDetails']//div[@label='Details' and @class='open']");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dt[contains(node(),'File Name')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//dt[contains(node(),'Size')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//dt[contains(node(),'File Type')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dt[contains(node(),'Restrictions Type')]");			
		}
		catch(WebDriverException e2){
			CommonFunctions.click(LocatorType.Xpath,"//*[@id='assetDetails']//a[contains(text(),'Details')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dt[contains(node(),'File Name')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//dt[contains(node(),'Size')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//dt[contains(node(),'File Type')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dt[contains(node(),'Restrictions Type')]");			
		}
		
		// Verify Metadata
		try{
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@id='assetDetails']//div[@label='Metadata' and @class='open']");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dt[contains(node(),'Description')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dt[contains(node(),'Creation Date')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dt[contains(node(),'Creator')]");
		}
		catch(WebDriverException e3){
			CommonFunctions.click(LocatorType.Xpath,"//*[@id='assetDetails']//a[contains(text(),'Metadata')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dt[contains(node(),'Description')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dt[contains(node(),'Creation Date')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dt[contains(node(),'Creator')]");			
		}
		
		// Verify Image Information
		try{
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@id='assetDetails']//div[@label='Image Information' and @class='open']");
			try{
				VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@id='assetDetails']/div[contains(@label,'Details')]/span/dl[dt[contains(text(),'File Type')]]/dd[contains(text(),'image')]");
				VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dt[contains(node(),'Color Space')]");
				VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dt[contains(node(),'Image Width')]");
				VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dt[contains(node(),'Image Height')]");
				VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dt[contains(node(),'X Resolution')]");
				VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dt[contains(node(),'Y Resolution')]");
			}
			catch(WebDriverException e4){				
				VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath,"//dt[contains(node(),'Color Space')]");
				VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath,"//dt[contains(node(),'Image Width')]");
				VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath,"//dt[contains(node(),'Image Height')]");
				VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath,"//dt[contains(node(),'X Resolution')]");
				VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath,"//dt[contains(node(),'Y Resolution')]");
			}			
		}
		
		catch(WebDriverException e2){
			CommonFunctions.click(LocatorType.Xpath,"//*[@id='assetDetails']//a[contains(text(),'Image Information')]");
			try{
				VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@id='assetDetails']/div[contains(@label,'Details')]/span/dl[dt[contains(text(),'File Type')]]/dd[contains(text(),'image')]");
				VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dt[contains(node(),'Color Space')]");
				VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dt[contains(node(),'Image Width')]");
				VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dt[contains(node(),'Image Height')]");
				VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dt[contains(node(),'X Resolution')]");
				VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dt[contains(node(),'Y Resolution')]");
			}
			catch(WebDriverException e5){				
				VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath,"//dt[contains(node(),'Color Space')]");
				VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath,"//dt[contains(node(),'Image Width')]");
				VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath,"//dt[contains(node(),'Image Height')]");
				VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath,"//dt[contains(node(),'X Resolution')]");
				VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath,"//dt[contains(node(),'Y Resolution')]");
			}			
	
		}		

		// Verify Rating
		try{
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@id='assetDetails']//div[@label='Rating' and @class='open']");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dt[contains(node(),'Average')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//dt[contains(node(),'Your Rating')]");			
		}
		catch(WebDriverException e6){
			CommonFunctions.click(LocatorType.Xpath,"//*[@id='assetDetails']//a[contains(text(),'Rating')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dt[contains(node(),'Average')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//dt[contains(node(),'Your Rating')]");
		}
	}
	

	@Then("^Share the searched asset with the user \"([^\"]*)\"$")
	public void Share_the_searched_asset_with_the_user(String userEmail) throws Throwable {
		
		//click on share asset button
		CommonFunctions.click(LocatorType.Xpath, "//button[contains(node(),'Share Asset')]");

		// Verify Pop Title, Fields and Buttons
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//h2[contains(node(),'Share Asset')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "userSelected");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "customMessage");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//button[contains(node(),'Cancel')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//button[contains(node(),'OK')]");

		// Enter User Email and Send
		CommonFunctions.clearAndSendKeys(By.id("userSelected"), userEmail);
		CommonFunctions.pressKey("Enter");
		CommonFunctions.click(LocatorType.Xpath, "//button[contains(node(),'OK')]");

		// Verify Email Sent
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//h2[contains(node(),'Email Sent')]");
	    
	}
	
	@Then("^Create a collection \"([^\"]*)\" \"([^\"]*)\"$")
	public void Create_a_collection(String name, String privacy) throws Throwable {
		
		// Goto Collections
		Navigation.gotoURL("/files/collections/");
		CommonFunctions.click(LocatorType.LinkedText, "Collections");
		CommonFunctions.click(LocatorType.LinkedText,"+ New Collection");
		// Create Collection
		CommonFunctions.setText(LocatorType.id, "newCollectionName", name);
		CommonFunctions.click(LocatorType.Xpath, "//input[contains(@value,'" + privacy + "')]");
		CommonFunctions.click(LocatorType.Xpath, "//button[contains(node(),'Create')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//h3[contains(text(),'My Collections')]");
		
		// Verify Created Collection
		CommonFunctions.click(LocatorType.Xpath,"//div[@id='myCollections']//h3[contains(node(),'" +name+ "')]");

		// Verify Collection Page
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//h1/span[contains(text(),'"+ name + "')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//h1/em/a[contains(text(),'edit')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div/p/em/a[contains(text(),'edit')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "assetsContainer");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "supportingFilesContainer");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "collectionCommentsContainer");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "+ New Asset");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "+ New Supporting File");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//button[contains(@class,'red')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//button[contains(@class,'blue')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//h3[contains(text(),'Created by')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//h3[contains(text(),'Privacy')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='private']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='public']");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Feedback");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//input[contains(@class,'save')]");
		   
	} 
	
	
	@Then("^Add asset to collection \"([^\"]*)\" \"([^\"]*)\"$")
	public void Add_asset_to_collection(String asset, String collection) throws Throwable {
		
		// Goto Asset and Add to Collection
		CommonFunctions.click(LocatorType.Xpath,"//button[contains(node(),'Add to Collection')]");

		// Verify Add Asset to Collection Popup
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//h2[contains(node(), 'Add Asset to Collection')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//h3[contains(node(), 'My Collections')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@class='dialogPopup']//button[@type='button']");
		CommonFunctions.click(LocatorType.Xpath, "//div//h3[contains(text(),'" + collection	+ "')]");
		CommonFunctions.click(LocatorType.Xpath, "//*[@id='tabCollection']/a");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div//h3[contains(text(),'"+ collection + "')]");

		// Verify Asset Added to Collection aaa
		Navigation.gotoURL("/files/collections/");
		CommonFunctions.click(LocatorType.Xpath,"//div[@id='myCollections']//h3[contains(node(),'" +collection+"')]");
		CommonFunctions.browserWait(5L);
        VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div//h3[contains(text(),'"+asset+"')]");
	    
	}
	
	@Then("^Add asset to existing collection from search results \"([^\"]*)\" \"([^\"]*)\"$")
	public void Add_asset_to_existing_collection_from_search_results(String asset, String collection) throws Throwable {
	    
		//click on the checkbox
		CommonFunctions.click(LocatorType.Xpath, "//input[@type='checkbox']");
		CommonFunctions.click(LocatorType.Xpath, "//a[@class='addAllCollection']");
		//commonFunctions.browserWait(10L);
		CommonFunctions.sleep(1000);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@id='myCollectionsContainer']//*[@id='myCollections']/div[1]/ul/li[2]/a/h3['Assets Collection']");
		CommonFunctions.click(LocatorType.Xpath,"//div[@id='myCollections']//h3[contains(node(),'" +collection+"')]");
	    VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div[@class='popupDialog']//h2[contains(node(),'1 Asset')]");
	    VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div[@class='popupDialog']//div//input[@value='Go to Collection']");
	    VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div[@class='popupDialog']//div//input[@value='OK']");
	    CommonFunctions.click(LocatorType.Xpath, "//div[@class='popupDialog']//div//input[@value='Go to Collection']");
	    CommonFunctions.browserWait(5L);
        VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div//h3[contains(text(),'"+asset+"')]");	    
		
	}

	@Then("^Add asset to new collection from search results \"([^\"]*)\" \"([^\"]*)\"$")
	public void Add_asset_to_new_collection_from_search_results(String asset, String name) throws Throwable {
		
		//click on the checkbox
		CommonFunctions.click(LocatorType.Xpath, "//input[@type='checkbox']");
		CommonFunctions.click(LocatorType.Xpath, "//a[@class='addAllCollection']");
		CommonFunctions.click(LocatorType.Xpath, "//*[@id='tab_create_collection']/a");
		
		// Verify Create new Collection Pop Fields and Labels
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//label[contains(text(),'Title')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "item_name");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//label[contains(text(),'Description')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "ITEM_ATT47_0");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//label[contains(text(),'Privacy')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//input[contains(@value,'private')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//input[contains(@value,'public')]");
		
		// Verify Buttons
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//input[@value='Create']");

		// Verify Mandatory Field Error
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Create']");
		VerificationFunctions.verifyText(LocatorType.className, "error", "textContent","required");
		
		//create a new collection
		// Create Collection
		CommonFunctions.setText(LocatorType.id, "item_name", name);
		//commonFunctions.click(LocatorType.Xpath, "//input[contains(@value,'" + privacy + "')]");
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Create']");
		 VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div[@class='popupDialog']//h2[contains(node(),'1 Asset')]");
		 VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div[@class='popupDialog']//div//input[@value='Go to Collection']");
		 VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div[@class='popupDialog']//div//input[@value='OK']");
		 CommonFunctions.click(LocatorType.Xpath, "//div[@class='popupDialog']//div//input[@value='Go to Collection']");
		 CommonFunctions.browserWait(5L);
	     VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div//h3[contains(text(),'"+asset+"')]");	    
		
	   
	}
	
	@Then("^Remove asset from collection \"([^\"]*)\" \"([^\"]*)\"$")
	public void Remove_asset_from_collection(String asset, String collection) throws Throwable {
		
		//Goto Collections and Select Collection to be Remove
		Navigation.gotoURL("/files/collections/");
		CommonFunctions.click(LocatorType.Xpath,"//div[@id='myCollections']//h3[contains(node(),'"+collection+"')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div//h3[contains(text(),'"+ asset+"')]");
		CommonFunctions.hoverMouse("//a[@title='"+asset+"']/div/img[contains(@src,'preview')]");
		CommonFunctions.click(LocatorType.Xpath, "//a[contains(@title,'remove')]");
		VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath, "//a[contains(@title,'"+asset+"')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//div/h3[contains(text(),'Assets (0)')]");
	    
	}

	@Then("^Delete the collection \"([^\"]*)\"$")
	public void Delete_the_collection(String name) throws Throwable {
		
		// Goto Collection
		Navigation.gotoURL("/files/collections/");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//h3[contains(text(),'My Collections')]");
		//verificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//h3[contains(text(),'"+name+"')]");
		CommonFunctions.click(LocatorType.Xpath,"//div[@id='myCollections']//h3[contains(node(),'"+name+"')]");

		// Delete Collection and Confirm
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//button[contains(@class,'red')]");
		CommonFunctions.click(LocatorType.Xpath, "//button[contains(@class,'red')]");
		CommonFunctions.click(LocatorType.Xpath,"//div[@class='dialogPopup']//button[contains(node(),'Delete')]");
		VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath,"//div[@id='myCollections']//h3[contains(node(),'"+name+"')]");
		   
	}
	
	@Then("^Add asset to watchlist \"([^\"]*)\"$")
	public void Add_asset_to_watchlist(String assetName) throws Throwable {
		
		//Add Asset to Watchlist
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//Button[.='Add to Watchlist']");
		CommonFunctions.click(LocatorType.Xpath,"//Button[.='Add to Watchlist']");
		
		//Verify Asset Watched Star and Remove From Watchlist
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//Button[.='Remove from Watchlist']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//*[contains(@class,'watched')]");
		
		//Verify Asset Added to Watchlist
		CommonFunctions.click(LocatorType.Xpath, "//li[@class='user']/a");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText,"Watchlist");
		CommonFunctions.click(LocatorType.LinkedText,"Watchlist");
		VerificationFunctions.verifyURL("/home/favorites/");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, assetName);
		   
	}
	
	@Then("^Add asset to watchlist from results page \"([^\"]*)\"$")
	public void Add_asset_to_watchlist_from_results_page(String assetName) throws Throwable {
		
		//Add Asset to watchlist
		CommonFunctions.click(LocatorType.Xpath, "//a[@class='addFavorite' and 'Add to Watchlist']");
		//Verify Asset Added to Watchlist
		CommonFunctions.click(LocatorType.Xpath, "//li[@class='user']/a");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText,"Watchlist");
		CommonFunctions.click(LocatorType.LinkedText,"Watchlist");
		VerificationFunctions.verifyURL("/home/favorites/");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, assetName);
	    
	}


	@Then("^Remove asset from watchlist \"([^\"]*)\"$")
	public void Remove_asset_from_watchlist(String assetName) throws Throwable {
		
		// Remove an Asset from Watchlist
		Navigation.gotoURL("/home/favorites/");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//tbody/tr//a[contains(text(),'" + assetName + "')]");
		CommonFunctions.click(LocatorType.Xpath, "//tbody/tr//a[contains(text(),'"+assetName+"')]");
		CommonFunctions.click(LocatorType.Xpath,"//button[contains(node(),'Remove from Watchlist')]");
		Navigation.gotoURL("/home/favorites/");
		CommonFunctions.waitForElement(LocatorType.Xpath, "//h2[contains(text(),'Watchlist')]",5);
		VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath,"//tbody/tr//a[contains(text(),'" + assetName + "')]");
	  
	}
	
	@Then("^Remove asset from watchlist from results page \"([^\"]*)\"$")
	public void Remove_asset_from_watchlist_from_results_page(String assetName) throws Throwable {
		
		// Remove an Asset from Watchlist
		Navigation.gotoURL("/home/favorites/");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//tbody/tr//a[contains(text(),'" + assetName + "')]");
		Search_for_an_asset(assetName);
		//remove asset from watchlisst
		CommonFunctions.click(LocatorType.Xpath, "//a[@class='removeFavorite' and 'Remove from Watchlist']");
		Navigation.gotoURL("/home/favorites/");
		CommonFunctions.waitForElement(LocatorType.Xpath, "//h2[contains(text(),'Watchlist')]",5);
		VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath,"//tbody/tr//a[contains(text(),'" + assetName + "')]");
		
		
	
	}


	
	@Then("^Link an asset \"([^\"]*)\" \"([^\"]*)\"$")
	public void Link_an_asset(String asset, String assetToLink) throws Throwable {
		
		//Link an asset, verify linked assets tab
				
		CommonFunctions.click(LocatorType.Xpath, "//button[contains(node(),'Link Assets')]");

		// Verify Pop Title, Fields and Buttons
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//h2[contains(node(),'Add Assets')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "assetSelected");
		VerificationFunctions.verifyElementVisible(LocatorType.className, "checkBoxHolder");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "allSelected");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//button[contains(node(),'Cancel')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//button[contains(node(),'Add Selected')]");

		// Verify Asset Not Visible in List of Assets to be Linked
		VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath, "//h3[contains(node(),'"+asset+"')]");

		// Verify Error Message Select at least 1 asset
		CommonFunctions.click(LocatorType.Xpath, "//button[contains(node(),'Add Selected')]");
		VerificationFunctions.verifyText(LocatorType.Xpath, "//span[contains(node(),'Select at')]","textContent","Select at least 1 asset");

		// Enter Asset Name to be linked
		CommonFunctions.setText(LocatorType.id, "assetSelected", assetToLink);
		CommonFunctions.pressKey("Enter");
		CommonFunctions.click(LocatorType.Xpath,"//ul[contains(@class, 'gridList')]/li[a[@title='"+assetToLink+"']]/ol//input");
		CommonFunctions.click(LocatorType.Xpath, "//button[contains(node(),'Add Selected')]");

		// Verify Asset Linking
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h3[contains(node(),'"+assetToLink+"')]");
	   
	}

	@Then("^Remove the linked asset \"([^\"]*)\"$")
	public void Remove_the_linked_asset(String linkedAsset) throws Throwable {
		
		// Remove an Asset Link
		DriverManager.driver.navigate().refresh();
		CommonFunctions.click(LocatorType.LinkedText, "Linked Assets");
		CommonFunctions.browserWait(5L);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h3[contains(node(),'"+ linkedAsset + "')]");
		CommonFunctions.hoverMouse("//div[@id='assetLinked']//h3");
		CommonFunctions.click(LocatorType.Xpath, "//*[contains(@class,'remove')]");
		VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath, "//h3[contains(node(),'"+ linkedAsset + "')]");
		VerificationFunctions.verifyText(LocatorType.Xpath, "//div[4]//h3","textContent", "No Results");
	   
	}
	
	@Then("^Add the asset to basket \"([^\"]*)\"$")
	public void Add_the_asset_to_basket(String arg1) throws Throwable {
		
		// Add to basket and verify element in basket
		CommonFunctions.click(LocatorType.Xpath, "//button[.='Add to Basket']");
		CommonFunctions.click(LocatorType.Xpath, "//li[@class='user']/a");
		CommonFunctions.click(LocatorType.Xpath,"//*[contains(@class,'brandOption')]/a[contains(text(),'Basket (')]");
		VerificationFunctions.verifyDropListOptionCss("li.orderOptions select", "Original*");
		VerificationFunctions.verifyDropListOptionCss("li.orderOptions select", "Mid Res");
		VerificationFunctions.verifyDropListOptionCss("li.orderOptions select", "Low Res");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Order Now']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Empty']");
	    
	}

	@Then("^Remove the asset from basket \"([^\"]*)\"$")
	public void Remove_the_asset_from_basket(String asset) throws Throwable {
		
		// Hover to an Asset and Remove from Basket
		Navigation.gotoURL("/home/basket");
		VerificationFunctions.verifyURL("/home/basket/");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//strong[contains(text(),'"+asset+"')]");
		WebElement removeElement = DriverManager.driver.findElement(By.xpath("//strong[contains(text(),'"+asset+"')]"));
		WebElement removeIcon = DriverManager.driver.findElement(By.xpath("//li[1]/a[contains(@class,'remove')]"));
		Actions hoverAndRemove = new Actions(DriverManager.driver);
		hoverAndRemove.moveToElement(removeElement).click(removeIcon).perform();
	   
	}
	
	@Then("^Add multiple assets to basket \"([^\"]*)\" \"([^\"]*)\"$")
	public void Add_multiple_assets_to_basket(String assetName, String assetName2) throws Throwable {
		
		// Add multiple assets to basket
		//Navigation.gotoURL("/files/");
		//verificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[.='"+assetName+"']/ancestor-or-self::li/ol/li[@class='basket']");
		//verificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[.='"+assetName2+"']/ancestor-or-self::li/ol/li[@class='basket']");
				
		//Add Asset and Verify In Basket
		//commonFunctions.click(LocatorType.Xpath,"//*[@class='addBasket']");
		CommonFunctions.click(LocatorType.Xpath, "//*[.='"+assetName+"']/ancestor-or-self::li/ol/li[@class='basket']/*[@class='addBasket']");
		CommonFunctions.click(LocatorType.Xpath, "//*[.='"+assetName2+"']/ancestor-or-self::li/ol/li[@class='basket']/*[@class='addBasket']");
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath,	"//li[@class='basketItem']");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText,"empty");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//input[@value='Order assets']");
				
		//Goto Basket and Verify Assets
		Navigation.gotoURL("/home/basket/");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//strong[text()='"+assetName+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//strong[text()='"+assetName2+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//input[@value='Order Now']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//input[@value='Empty']");
	   
	}

	@Then("^Empty the basket$")
	public void Empty_the_basket() throws Throwable {
		
		// Goto Basket and Empty Basket
		Navigation.gotoURL("/home/basket");
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Empty']");
		VerificationFunctions.verifyText(LocatorType.Xpath, "//*[@class='empty']","textContent","Your basket is empty.");
	   
	}
	
	
	@Then("^Click on upload assets button$")
	public void Click_on_upload_assets_button() throws Throwable {
		
		Navigation.gotoURL("/files/upload/");
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='+ Upload Assets']");
	   
	}

	@Then("^Upload an asset and approve \"([^\"]*)\"$")
	public void Upload_an_asset_and_approve(String filePath) throws Throwable {
	    
	    CommonFunctions.browserWait(5L);
		CommonFunctions.click(LocatorType.LinkedText, filePath);
				
		// Verify Editing Title and Mandatory Fields
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h2[contains(node(),'Editing')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "item_name");
		VerificationFunctions.verifyElementVisible(LocatorType.id,"item_description");
		VerificationFunctions.verifyElementVisible(LocatorType.className, "valid_mandatory");
				
		CommonFunctions.click(LocatorType.Xpath, "//input[contains(@value,'Save & Approve')]");
		DriverManager.driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
		DriverManager.driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@id='approved']//div//strong[contains(text(),'"+filePath+"')]");
			
   
	}

	@Then("^Edit the asset restriction \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void Edit_the_asset_restriction(String assetName, String restriction, String condition) throws Throwable {
		
		//Verify Restrictions Label and Options
		//Apply Restrictions and Verify on Asset on Page
		//Search Asset Uncheck Expiry Date Box and Save
		Search_for_an_asset_and_click_on_asset(assetName);		
		CommonFunctions.click(LocatorType.LinkedText,"edit");
		//verifyElementVisible(LocatorType.Xpath, "//span[@class='title'][text()='"+assetName+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[.='Core Info']");
		CommonFunctions.javaScriptExe("window.scrollBy(100,700);");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//H2[contains(text(),'Usage Rights')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[text()='Restrictions Type']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Select/Option[.='None']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Select/Option[.='Limited']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Select/Option[.='Unlimited']");
						
		if(restriction.equals("None")) {
					
		CommonFunctions.selectDropList("//Select","None");
		
		}
		
		if(restriction.equals("Limited")){
				
		CommonFunctions.selectDropList("//Select","Limited");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//Label[.='Conditions']/parent::li//textarea");
		CommonFunctions.click(LocatorType.LinkedText, "Save");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dfn[contains(text(),'This field is required when usage type is ')]");
		CommonFunctions.clearAndSendKeys(By.xpath("//Label[.='Conditions']/parent::li//textarea"), condition);	
				
		}
		
		if(restriction.equals("Unlimited")){
			
		CommonFunctions.selectDropList("//Select","Unlimited");	
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//Label[.='Conditions']/parent::li//textarea");}
				
		//get ItemID, Save and Verify Restriction on Asset Page
		String itemid = CommonFunctions.getItemID();
		CommonFunctions.click(LocatorType.LinkedText, "Save");	
		CommonFunctions.sleep(2000);
				
		//Verify  Asset View Page 
		Navigation.gotoURL("/files/asset/"+itemid);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//H2[contains(text(),'"+assetName+"')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//dt[text()='Restrictions Type']/parent::dl/dd[.='"+restriction+"']");
				
		//Verify on Search Page
		CommonFunctions.click(LocatorType.LinkedText,"Assets");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Usage Rights Type");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//H4[contains(text(),'"+restriction+"')]");
			
    
	}


	@Then("^Upload an asset and send for approval \"([^\"]*)\"$")
	public void Upload_an_asset_and_send_for_approval(String filePath) throws Throwable {
		
		CommonFunctions.browserWait(5L);
		CommonFunctions.click(LocatorType.LinkedText, filePath);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h2[contains(node(),'Editing')]");		
		CommonFunctions.click(LocatorType.Xpath,"//input[contains(@value,'Save & Send for Approval')]");
		DriverManager.driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
		DriverManager.driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@id='awaitingApproval']//div//strong[contains(text(),'"+filePath+"')]");
		
	}
	
	@Then("^Approve the asset \"([^\"]*)\"$")
	public void Approve_the_asset(String asset) throws Throwable {
		
		//Goto Admin Page and Verify Tabs
		Navigation.gotoURL("/admin/brand_admin/files/brand_assets/");
		VerificationFunctions.verifyElementVisible(LocatorType.id,"ajaxSearchInput");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText,"Search");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText,"Reset");
		VerificationFunctions.verifyElementVisible(LocatorType.className,"draft_state");
		VerificationFunctions.verifyElementVisible(LocatorType.className,"awaiting_approval_state");
		VerificationFunctions.verifyElementVisible(LocatorType.className,"published_state");
		VerificationFunctions.verifyElementVisible(LocatorType.className,"rejected_state");
		VerificationFunctions.verifyElementVisible(LocatorType.className,"archived_state");
		VerificationFunctions.verifyElementVisible(LocatorType.className,"expired_state");
		VerificationFunctions.verifyElementVisible(LocatorType.className,"sortablePlugin");
		VerificationFunctions.verifyElementVisible(LocatorType.className,"itemsPerPage");
				
		//Search for Asset and Approve
		CommonFunctions.click(LocatorType.className,"awaiting_approval_state");
		CommonFunctions.clearAndSendKeys(By.id("ajaxSearchInput"), asset);
		CommonFunctions.click(LocatorType.LinkedText,"Search");
		CommonFunctions.browserWait(5L);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div/a[text()='"+asset+"']");
		CommonFunctions.click(LocatorType.LinkedText,"Reset");
		CommonFunctions.click(LocatorType.className,"awaiting_approval_state");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div/a[text()='"+asset+"']");
		CommonFunctions.click(LocatorType.Xpath,"//div/a[text()='"+asset+"']");
		CommonFunctions.click(LocatorType.LinkedText,"Publish");
		CommonFunctions.click(LocatorType.className,"awaiting_approval_state");
		VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath,"//div/a[text()='"+asset+"']");
		CommonFunctions.click(LocatorType.className,"published_state");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div/a[text()='"+asset+"']");
	    
	}
	
	@Then("^Bulkupload asset \"([^\"]*)\"$")
	public void Bulkupload_asset(String filePath) throws Throwable {
		
		CommonFunctions.browserWait(5L);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@id='metadataRequired']//div//strong[contains(text(),'"+filePath+"')]");
	    
	}
	
	@Then("^Approve the bulk uploaded assets$")
	public void Approve_the_bulk_uploaded_assets() throws Throwable {
		
		Navigation.gotoURL("/files/upload/");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//h1[contains(text(),'Upload')]");
		VerificationFunctions.verifyElementVisible(LocatorType.className, "selectDeselect");
		CommonFunctions.click(LocatorType.className, "selectDeselect");
		VerificationFunctions.verifyElementsVisible(LocatorType.LinkedText, "Bulk Edit");
		CommonFunctions.click(LocatorType.LinkedText, "Bulk Edit");	
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h2[contains(node(),'Editing')]");
		CommonFunctions.click(LocatorType.Xpath, "//input[contains(@value,'Save & Approve')]");
		DriverManager.driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
		DriverManager.driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
	    
	}
	

	@Then("^Send those bulk uploaded assets to approve$")
	public void Send_those_bulk_uploaded_assets_to_approve() throws Throwable {
		
		Navigation.gotoURL("/files/upload/");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//h1[contains(text(),'Upload')]");
		VerificationFunctions.verifyElementVisible(LocatorType.className, "selectDeselect");
		CommonFunctions.click(LocatorType.className, "selectDeselect");
		VerificationFunctions.verifyElementsVisible(LocatorType.className, "bulkEdit");
		VerificationFunctions.verifyElementsVisible(LocatorType.LinkedText, "Bulk Edit");
		CommonFunctions.click(LocatorType.LinkedText, "Bulk Edit");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h2[contains(node(),'Editing')]");		
		CommonFunctions.click(LocatorType.Xpath, "//input[contains(@value,'Save & Send for Approval')]");
		DriverManager.driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
		DriverManager.driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		CommonFunctions.sleep(3000);
		
	}
	
	@Then("^Approve the bulk uploaded assets from admin page \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void Approve_the_bulk_uploaded_assets_from_admin_page(String asset1, String asset2, String asset3, String asset4) throws Throwable {
	    
		// Goto Admin Page and Verify Tabs
		Navigation.gotoURL("/admin/brand_admin/files/brand_assets/");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "ajaxSearchInput");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Search");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Reset");
		VerificationFunctions.verifyElementVisible(LocatorType.className, "draft_state");
		VerificationFunctions.verifyElementVisible(LocatorType.className, "awaiting_approval_state");
		VerificationFunctions.verifyElementVisible(LocatorType.className, "published_state");
		VerificationFunctions.verifyElementVisible(LocatorType.className, "rejected_state");
		VerificationFunctions.verifyElementVisible(LocatorType.className, "archived_state");
		VerificationFunctions.verifyElementVisible(LocatorType.className, "expired_state");

		// Verify and Select Assets to BulkEdit
		CommonFunctions.sleep(1000);
		CommonFunctions.click(LocatorType.className, "awaiting_approval_state");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div/a[text()='"+asset1+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div/a[text()='"+asset2+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div/a[text()='"+asset3+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div/a[text()='"+asset4+"']");

		CommonFunctions.click(LocatorType.Xpath, "//ul[@class='content']//ul//div[a[text()='"+asset1+"']]//parent::div//span[@class='checkbox']//input");
		CommonFunctions.click(LocatorType.Xpath, "//ul[@class='content']//ul//div[a[text()='"+asset2+"']]//parent::div//span[@class='checkbox']//input");
		CommonFunctions.click(LocatorType.Xpath, "//ul[@class='content']//ul//div[a[text()='"+asset3+"']]//parent::div//span[@class='checkbox']//input");
		CommonFunctions.click(LocatorType.Xpath, "//ul[@class='content']//ul//div[a[text()='"+asset4+"']]//parent::div//span[@class='checkbox']//input");
		
		//Verify Bulk Edit and Click
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//li[@class='awaiting_approval_state']//div[@class='bulkedit_stackcount']");
		CommonFunctions.click(LocatorType.Xpath, "//li[@class='awaiting_approval_state']//div[@class='bulkedit_stackcount']");
		
		// Verify Elements in Bulk Edit Stack
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//li[contains(@class,'item')][text()='"+asset1+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//li[contains(@class,'item')][text()='"+asset2+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//li[contains(@class,'item')][text()='"+asset3+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//li[contains(@class,'item')][text()='"+asset4+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Bulk Edit");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Clear All");
		
		//Verify Tabs
		CommonFunctions.click(LocatorType.LinkedText, "Bulk Edit");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//span[text()='Basket Items']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//span[text()='Core Info']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//span[text()='Taxonomy']");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Cancel");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Save");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Publish");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Reject");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Archive");
		
		//Verify Items in Basket
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//td[text()='"+asset1+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//td[text()='"+asset2+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//td[text()='"+asset3+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//td[text()='"+asset4+"']");
		
		//Verify Core Info Tab
		CommonFunctions.click(LocatorType.Xpath, "//span[text()='Core Info']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h2[contains(text(),'Core Info')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "item_name");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "item_description");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "item_keywords");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[text()='Rejection Comment']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h2[contains(text(),'Usage Rights')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[text()='Restrictions Type']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//option[@value='None']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//option[@value='Limited']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//option[@value='Unlimited']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[text()='Expiry Date Required']");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "hasExpiryDate");
		
		//Verify Taxonomy Tab and All Taxonomies 
		CommonFunctions.click(LocatorType.Xpath, "//span[text()='Taxonomy']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[text()='Add Taxonomies']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[text()='Remove Taxonomies']");
		CommonFunctions.click(LocatorType.LinkedText, "Publish");
		CommonFunctions.sleep(4000);
		
		//Verify Items have been Approved Successfully
		CommonFunctions.click(LocatorType.className, "published_state");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div/a[text()='"+asset1+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div/a[text()='"+asset2+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div/a[text()='"+asset3+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div/a[text()='"+asset4+"']");

	}


	@Then("^Reject the asset \"([^\"]*)\" \"([^\"]*)\"$")
	public void Reject_the_asset(String assetName, String rejectReason) throws Throwable {
		
		//Navigate to Admin Section and Select Awaiting Approval Tab
		Navigation.gotoURL("/admin/brand_admin/files/brand_assets/");
		VerificationFunctions.verifyURL("/admin/brand_admin/files/brand_assets/");
		VerificationFunctions.verifyElementVisible(LocatorType.className, "awaiting_approval_state");
		CommonFunctions.click(LocatorType.className, "awaiting_approval_state");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, assetName);
		CommonFunctions.click(LocatorType.LinkedText, assetName);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//span[contains(@title,'Workflow Status Awaiting Approval')][.='Awaiting Approval']");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Cancel");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Save");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Publish");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Delete");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Reject");
				
		//Reject and Verify Mandatory Reject Comment
		CommonFunctions.click(LocatorType.LinkedText, "Reject");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//dfn[.='Please enter a rejection comment']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[.='Rejection Comment']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[.='Rejection Comment']/parent::li//textarea");
		CommonFunctions.clearAndSendKeys(By.xpath("//Label[.='Rejection Comment']/parent::li//textarea"), rejectReason);
		CommonFunctions.click(LocatorType.LinkedText, "Reject");

		//Verify Asset has been Rejected and No Longer in Awaiting Approval Tab
		VerificationFunctions.verifyElementVisible(LocatorType.className, "awaiting_approval_state");
		CommonFunctions.click(LocatorType.className, "awaiting_approval_state");
		VerificationFunctions.verifyElementNotVisible(LocatorType.LinkedText, assetName);
		VerificationFunctions.verifyElementVisible(LocatorType.className, "rejected_state");
		CommonFunctions.click(LocatorType.className, "rejected_state");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, assetName);
		CommonFunctions.click(LocatorType.LinkedText, assetName);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//span[contains(@title,'Workflow Status Rejected')][.='Rejected']");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Cancel");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Save");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Delete");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Archive");
	   
	}

	@Then("^Publish the rejected asset \"([^\"]*)\"$")
	public void Publish_the_rejected_asset(String assetName) throws Throwable {
		
		//Navigate to Admin Section and Select Rejected  Tab and Archive
		Navigation.gotoURL("/admin/brand_admin/files/brand_assets/");
		VerificationFunctions.verifyURL("/admin/brand_admin/files/brand_assets/");
		VerificationFunctions.verifyElementVisible(LocatorType.className, "rejected_state");
		CommonFunctions.click(LocatorType.className, "rejected_state");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, assetName);
		CommonFunctions.click(LocatorType.LinkedText, assetName);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//span[contains(@title,'Workflow Status Rejected')][.='Rejected']");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Cancel");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Save");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Archive");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Delete");
		CommonFunctions.click(LocatorType.LinkedText, "Archive");
				
		//Verify Asset has been archived and no Longer Rejected
		VerificationFunctions.verifyElementVisible(LocatorType.className, "rejected_state");
		CommonFunctions.click(LocatorType.className, "rejected_state");
		VerificationFunctions.verifyElementNotVisible(LocatorType.LinkedText, assetName);
		VerificationFunctions.verifyElementVisible(LocatorType.className, "archived_state");
		CommonFunctions.click(LocatorType.className, "archived_state");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, assetName);
		CommonFunctions.click(LocatorType.LinkedText, assetName);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//span[contains(@title,'Workflow Status Archived')][.='Archived']");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Cancel");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Save");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Re-Publish");
		CommonFunctions.click(LocatorType.LinkedText, "Re-Publish");	
				
		//Verify Asset has been Re-Published and No Longer in Archived Tab
		VerificationFunctions.verifyElementVisible(LocatorType.className, "archived_state");
		CommonFunctions.click(LocatorType.className, "archived_state");
		VerificationFunctions.verifyElementNotVisible(LocatorType.LinkedText, assetName);
		VerificationFunctions.verifyElementVisible(LocatorType.className, "published_state");
		CommonFunctions.click(LocatorType.className, "published_state");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, assetName);
		CommonFunctions.click(LocatorType.LinkedText, assetName);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//span[contains(@title,'Workflow Status Published')][.='Published']");
	 
	}

	@Then("^Delete the rejected asset \"([^\"]*)\"$")
	public void Delete_the_rejected_asset(String assetName) throws Throwable {
		
		//Verify Asset in Rejected Tab and Go to Asset
		Navigation.gotoURL("/admin/brand_admin/files/brand_assets/");
		VerificationFunctions.verifyURL("/admin/brand_admin/files/brand_assets/");
		VerificationFunctions.verifyElementVisible(LocatorType.className, "rejected_state");
		CommonFunctions.click(LocatorType.className, "rejected_state");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, assetName);
		CommonFunctions.click(LocatorType.LinkedText, assetName);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//span[contains(@title,'Workflow Status Rejected')][.='Rejected']");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Cancel");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Save");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Archive");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Delete");
		
		//Delete and Verify Asset No longer Exists in Rejected Tab
		CommonFunctions.click(LocatorType.LinkedText, "Delete");
		CommonFunctions.confirmAlert("OK");
		VerificationFunctions.verifyElementVisible(LocatorType.className, "rejected_state");
		CommonFunctions.click(LocatorType.className, "rejected_state");
		VerificationFunctions.verifyElementNotVisible(LocatorType.LinkedText, assetName);
	    
	}

	@Then("^Order and asset \"([^\"]*)\" \"([^\"]*)\"$")
	public void Order_and_asset(String arg1, String size) throws Throwable {
		
		//Search Asset, Add to Basket and Order
		CommonFunctions.click(LocatorType.Xpath, "//Select/Option[.='"+size+"']");
		CommonFunctions.click(LocatorType.Xpath, "//Input[@value='Order Now']");
		
		//Verify Order in Orders Page and Remove
		Navigation.gotoURL("home/orders");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(node(),'zip')]");
		CommonFunctions.hoverMouse("//a[contains(node(),'zip')]");
		CommonFunctions.click(LocatorType.Xpath, "//a[contains(node(),'zip')]/following::a[@class='remove']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[.='You have no orders.']");
	   
	}

	@Then("^Order the restricted asset \"([^\"]*)\" \"([^\"]*)\"$")
	public void Order_the_restricted_asset(String assetName, String size) throws Throwable {
		
		//Search Asset, Add to Basket and Order
		Search_for_an_asset_and_click_on_asset(assetName);		
		Add_the_asset_to_basket(assetName);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[.='"+assetName+"']/ancestor-or-self::li/ol/li/input");
		CommonFunctions.click(LocatorType.Xpath, "//*[.='"+assetName+"']/ancestor-or-self::li/ol/li/input");
		CommonFunctions.click(LocatorType.Xpath, "//Select/Option[.='"+size+"']");
		CommonFunctions.browserWait(30L);
		CommonFunctions.click(LocatorType.Xpath, "//Input[@value='Order Now']");
						
		//Verify Order in Orders Page and Remove
		Navigation.gotoURL("/home/orders");
		CommonFunctions.browserWait(5L);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(node(),'zip')]");
		CommonFunctions.hoverMouse("//a[contains(node(),'zip')]");
		CommonFunctions.click(LocatorType.Xpath, "//a[contains(node(),'zip')]/following::a[@class='remove']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[.='You have no orders.']");
		
	  
	}

	@Then("^Verify download resolutions for image asset \"([^\"]*)\"$")
	public void Verify_download_resolutions_for_image_asset(String assetName) throws Throwable {
		
		Search_for_an_asset_and_click_on_asset(assetName);		
		CommonFunctions.click(LocatorType.Xpath, "//button[contains(node(),'Download')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//Button[.='Cancel']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//Button[.='Original']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//Button[.='Mid Res']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//Button[.='Low Res']");
	}
	
	@Then("^Download the asset original resolution \"([^\"]*)\" \"([^\"]*)\"$")
	public void Download_the_asset_original_resolution(String assetName, String quality) throws Throwable {
		
		// Download an Asset by specifying quality(Original)
		Search_for_an_asset_and_click_on_asset(assetName);
		CommonFunctions.click(LocatorType.Xpath, "//button[contains(node(),'Download')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//Button[.='Cancel']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//Button[.='Original']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//Button[.='Mid Res']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//Button[.='Low Res']");
		CommonFunctions.click(LocatorType.Xpath,"//Button[.='"+quality+"']");
	   
	}

	@Then("^Download the asset Mid resolution \"([^\"]*)\" \"([^\"]*)\"$")
	public void Download_the_asset_Mid_resolution(String assetName, String quality) throws Throwable {
		
		// Download an Asset by specifying quality(Mid Res)
		Search_for_an_asset_and_click_on_asset(assetName);
		CommonFunctions.click(LocatorType.Xpath, "//button[contains(node(),'Download')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//Button[.='Cancel']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//Button[.='Original']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//Button[.='Mid Res']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//Button[.='Low Res']");
		CommonFunctions.click(LocatorType.Xpath,"//Button[.='"+quality+"']");
	   
	}

	@Then("^Download the asset Low resolution \"([^\"]*)\" \"([^\"]*)\"$")
	public void Download_the_asset_Low_resolution(String assetName, String quality) throws Throwable {
		
		// Download an Asset by specifying quality(Low Res)
		Search_for_an_asset_and_click_on_asset(assetName);
		CommonFunctions.click(LocatorType.Xpath, "//button[contains(node(),'Download')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//Button[.='Cancel']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//Button[.='Original']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//Button[.='Mid Res']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//Button[.='Low Res']");
		CommonFunctions.click(LocatorType.Xpath,"//Button[.='"+quality+"']");
	   
	}

	@Then("^Download the restricted asset low resolution \"([^\"]*)\" \"([^\"]*)\"$")
	public void Download_the_restricted_asset_low_resolution(String assetName, String quality) throws Throwable {
		
		//Download Asset with Usage Rights Restrictions
		Search_for_an_asset_and_click_on_asset(assetName);
		CommonFunctions.click(LocatorType.Xpath, "//button[contains(node(),'Download')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//Button[.='Cancel']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//Button[.='Original']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//Button[.='Mid Res']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//Button[.='Low Res']");
		VerificationFunctions.verifyElementVisible(LocatorType.id,"accept");
		CommonFunctions.click(LocatorType.id,"accept");
		CommonFunctions.click(LocatorType.Xpath,"//Button[.='"+quality+"']");
				    
	}
	
	@Then("^Download the asset from search results page \"([^\"]*)\" \"([^\"]*)\"$")
	public void Download_the_asset_from_search_results_page(String assetName, String quality) throws Throwable {
		
		Search_for_an_asset(assetName); 		
		CommonFunctions.click(LocatorType.Xpath, "//a[@title='Download Now']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//input[@value='Cancel']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//input[@value='Original']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//input[@value='Mid Res']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//input[@value='Low Res']");		
		CommonFunctions.click(LocatorType.Xpath,"//input[@value='"+quality+"']");	   
		
	}
	
	@Then("^Edit a collection \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void Edit_a_collection(String collection, String name, String description, String privacy) throws Throwable {
		
		// Goto Collection and Verify Edit Links
		Navigation.gotoURL("/files/collections/");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@id='myCollections']//h3[contains(node(),'"+collection+"')]");
		CommonFunctions.click(LocatorType.Xpath,"//div[@id='myCollections']//h3[contains(node(),'"+collection+"')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h1/span[contains(text(),'"+ collection + "')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//h1/em/a[contains(text(),'edit')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//div/p/em/a[contains(text(),'edit')]");

		// Verify and Edit Collection Name
		CommonFunctions.click(LocatorType.Xpath, "//h1/em/a[contains(text(),'edit')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//div//h1/button[contains(text(),'Save')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//div//h1/button[contains(text(),'cancel')]");
		CommonFunctions.clearAndSendKeys(By.xpath("//h1/input"), name);
		CommonFunctions.click(LocatorType.Xpath, "//div[1]//h1/button[contains(text(),'Save')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h1/span[contains(text(),'"+ name + "')]");

		// Verify and Edit Description
		CommonFunctions.click(LocatorType.Xpath, "//div/p/em/a[contains(text(),'edit')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//div//p/button[contains(text(),'Save')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//div//p/button[contains(text(),'cancel')]");
		CommonFunctions.clearAndSendKeys(By.xpath("//div//p/textarea"), description);
		CommonFunctions.browserWait(5L);
		CommonFunctions.click(LocatorType.Xpath, "//div//p/button[contains(text(),'Save')]");

		// Verify and Edit Collection Type
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='private']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='public']");
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='" + privacy + "']");
		CommonFunctions.browserWait(5L);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//h2[contains(node(),'Are you sure?')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//button[contains(text(),'Change Privacy')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//button[contains(text(),'Cancel')]");
		CommonFunctions.click(LocatorType.Xpath, "//button[contains(text(),'Change Privacy')]");
		CommonFunctions.browserWait(5L);
	    
	}
	
	@Then("^Edit a collection by adding asset \"([^\"]*)\" \"([^\"]*)\"$")
	public void Edit_a_collection_by_adding_asset(String collection, String assetToAdd) throws Throwable {
		
		// Goto Collection and Verify Edit Links
		Navigation.gotoURL("/files/collections/");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//div[@id='myCollections']//h3[contains(node(),'"+collection+"')]");
		CommonFunctions.click(LocatorType.Xpath,"//div[@id='myCollections']//h3[contains(node(),'"+collection+"')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h1/span[contains(text(),'"+collection+"')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//h1/em/a[contains(text(),'edit')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div/p/em/a[contains(text(),'edit')]");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "+ New Asset");

		// Add New Asset and Verify Popup
		CommonFunctions.click(LocatorType.LinkedText, "+ New Asset");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//h2[contains(node(),'Add Assets to Collection')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "textField");
		VerificationFunctions.verifyElementVisible(LocatorType.className, "checkBoxHolder");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//button[contains(node(),'Cancel')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//button[contains(node(),'Add to Collection')]");

		// Enter Asset Name to be Linked
		CommonFunctions.setText(LocatorType.id, "textField", assetToAdd);
		CommonFunctions.pressKey("Enter");
		CommonFunctions.click(LocatorType.Xpath,"//ul[contains(@class, 'gridList')]/li[a[@title='"+assetToAdd+"']]/ol//input");
		CommonFunctions.click(LocatorType.Xpath,"//button[contains(node(),'Add to Collection')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h3[contains(text(),'Test')]");

		// Verify Download, Add to Basket and Add to Watchlist Icons
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//li[@class='download']");
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//li[@class='basket']");
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//li[@class='watchlist']");
	    
	}
	

	@Then("^Add comment \"([^\"]*)\" \"([^\"]*)\"$")
	public void Add_comment(String collection, String comment) throws Throwable {
		
		// Goto Collection and Add Comment
		Navigation.gotoURL("/files/collections/");
		CommonFunctions.click(LocatorType.Xpath,"//div[@id='myCollections']//h3[contains(node(),'" + collection+"')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "commentsBox");
		CommonFunctions.clearAndSendKeys(By.id("commentsBox"), comment);
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Add Comment']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div/p[contains(text(),'"+comment+"')]");
	  
	}

	

	@Then("^Share collection \"([^\"]*)\" \"([^\"]*)\"$")
	public void Share_collection(String collection, String user) throws Throwable {
		
		// Goto Collection
		Navigation.gotoURL("/files/collections/");
		CommonFunctions.click(LocatorType.Xpath,"//div[@id='myCollections']//h3[contains(node(),'" + collection	+ "')]");
		CommonFunctions.click(LocatorType.Xpath,"//button[contains(node(),'Share Collection')]");

		// Verify Pop Title, Fields and Buttons
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//h2[contains(node(),'Share Collection')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "userSelected");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "customMessage");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//button[contains(node(),'Cancel')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//button[contains(node(),'OK')]");

		// Enter User Email and Send
		CommonFunctions.clearAndSendKeys(By.id("userSelected"), user);
		CommonFunctions.browserWait(5L);
		CommonFunctions.pressKey("Enter");
		CommonFunctions.click(LocatorType.Xpath, "//button[contains(node(),'OK')]");

		// Verify Email Sent
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//h2[contains(node(),'Email Sent')]");

	}

	@Then("^Verify the shared collection \"([^\"]*)\"$")
	public void Verify_the_shared_collection(String collection) throws Throwable {		

		// Goto Collection 
		Navigation.gotoURL("/files/collections/");
		Navigation.gotoURL("/files/collections/");
		VerificationFunctions.verifyURL("/files/collections/");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@id='sharedCollectionsContainer']//h3[contains(node(),'"+collection+"')]");
			
	}

	@Given("^Edit asset mandatory fields \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void Edit_asset_mandatory_fields_(String assetName, String name, String description) throws Throwable {
		
		//Search for Asset, Edit and Verify Fields
		Search_for_an_asset_and_click_on_asset(assetName);
		CommonFunctions.click(LocatorType.LinkedText,"edit");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//span[@class='title'][text()='"+assetName+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[.='Core Info']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h2[contains(text(),'Core Info')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//ul//label[.='Preview']");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Download Asset");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[.='Upload File']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[.='Upload Custom Preview']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@id='item_file']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@id='item_file_custom_preview']");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "item_name");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "item_description");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[.='Name']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[.='Description']");
		CommonFunctions.clearText(LocatorType.id, "item_name");
		CommonFunctions.clearText(LocatorType.id, "item_description");
		CommonFunctions.click(LocatorType.LinkedText,"Save");
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//dfn[.='This field is required.']");
				
		//Enter New Details and Save
		CommonFunctions.clearAndSendKeys(By.id("item_name"), name);
		CommonFunctions.clearAndSendKeys(By.id("item_description"), description);
		CommonFunctions.browserWait(5L);
		CommonFunctions.click(LocatorType.LinkedText, "Save");
		CommonFunctions.browserWait(20L);
		CommonFunctions.click(LocatorType.className,"awaiting_approval_state");
		CommonFunctions.click(LocatorType.Xpath, "//ul[@class='nav']//li[contains(text(),'Publish')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div[@class='searchResults']//ul//li//div//div[@class='title']//a[contains(text(),'"+name+"')]");
	    
	}

	@Then("^Add taxonomy to asset and verify \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void Add_taxonomy_to_asset_and_verify_(String assetName, String parentTaxonomy, String childTaxonomy) throws Throwable {
	    
		//Goto Asset and Edit
		Navigation.gotoURL("/files");
		Search_for_an_asset_and_click_on_asset(assetName);
		CommonFunctions.browserWait(10L);
		CommonFunctions.click(LocatorType.LinkedText, "edit");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//span[@class='title'][text()='"+assetName+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//a[contains(text(),'"+parentTaxonomy+"')]");		
		
		try{
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//a[.='"+parentTaxonomy+"']/parent::div//img[contains(@src,'plus')]");
			CommonFunctions.doubleClick(LocatorType.Xpath,"//a[contains(text(),'"+parentTaxonomy+"')]");
			CommonFunctions.scrollIntoView("//a[contains(text(),'"+childTaxonomy+"')]");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, childTaxonomy);
		}
		catch(WebDriverException e){
			CommonFunctions.scrollIntoView("//a[contains(text(),'"+childTaxonomy+"')]");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, childTaxonomy);			
		}
						
		//Select Taxonomy,Save and get ItemID
		String itemid = CommonFunctions.getItemID();
		CommonFunctions.checkBox(LocatorType.Xpath, "//a[.='"+parentTaxonomy+"']/following::div//a[.='"+childTaxonomy+"']/parent::div//input[contains(@id,'chckbx_categoryTree')]");
		CommonFunctions.click(LocatorType.LinkedText, "Save");	
		CommonFunctions.sleep(2000);
				
		//Verify Taxonomy on Asset View Page 
		Navigation.gotoURL("/files/asset/"+itemid);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//dl/dt[node()='"+parentTaxonomy+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//dd[node()='"+childTaxonomy+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//dt[text()='"+parentTaxonomy+"']/parent::dl/dd[.='"+childTaxonomy+"']");
		
		//Verify Taxonomy on Search Page
		CommonFunctions.click(LocatorType.LinkedText,"Assets");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[text()='"+parentTaxonomy+"']");
		CommonFunctions.click(LocatorType.Xpath, "//label[text()='"+parentTaxonomy+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[text()='"+childTaxonomy+"']");
	}

	@Then("^Remove AssetTaxonomy \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void Remove_AssetTaxonomy(String assetName, String parentTaxonomy, String childTaxonomy) throws Throwable {
		
		//Goto Asset and Edit
		Navigation.gotoURL("/files");
		Search_for_an_asset_and_click_on_asset(assetName);
		CommonFunctions.click(LocatorType.LinkedText, "edit");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//span[@class='title'][text()='"+assetName+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//a[contains(text(),'"+parentTaxonomy+"')]");	

		try{
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//a[.='"+parentTaxonomy+"']/parent::div//img[contains(@src,'plus')]");
			CommonFunctions.doubleClick(LocatorType.Xpath,"//a[contains(text(),'"+parentTaxonomy+"')]");
			CommonFunctions.scrollIntoView("//a[contains(text(),'"+childTaxonomy+"')]");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, childTaxonomy);
		}
		catch(WebDriverException e){
			CommonFunctions.scrollIntoView("//a[contains(text(),'"+childTaxonomy+"')]");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, childTaxonomy);			
		}
		
		//Remove Taxonomy,Save and get ItemID
		String itemid = CommonFunctions.getItemID();
		CommonFunctions.uncheckBox(LocatorType.Xpath, "//a[.='"+parentTaxonomy+"']/following::div//a[.='"+childTaxonomy+"']/parent::div//input[contains(@id,'chckbx_categoryTree')]");
		CommonFunctions.uncheckBox(LocatorType.Xpath, "//a[.='"+parentTaxonomy+"']/parent::div//input[contains(@id,'chckbx_categoryTree')]");
		CommonFunctions.click(LocatorType.LinkedText, "Save");	
		CommonFunctions.sleep(2000);

		//Verify Taxonomy Not Visible on Asset View Page 
		Navigation.gotoURL("/files/asset/"+itemid);
		VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath, "//dl/dt[node()='"+parentTaxonomy+"']");
		VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath, "//dd[node()='"+childTaxonomy+"']");
		VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath, "//dt[text()='"+parentTaxonomy+"']/parent::dl/dd[.='"+childTaxonomy+"']");

		//Verify Taxonomy Not Visible on Search Page
		CommonFunctions.click(LocatorType.LinkedText,"Assets");
		VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath, "//label[text()='"+parentTaxonomy+"']");
		VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath, "//label[text()='"+childTaxonomy+"']");
	    
	}
	
	@Given("^Edit asset restriction \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void Edit_asset_restriction(String assetName, String restriction, String condition) throws Throwable {
		
		//Verify Restrictions Label and Options
		//Apply Restrictions and Verify on Asset on Page
				
		Search_for_an_asset_and_click_on_asset(assetName);
		CommonFunctions.click(LocatorType.LinkedText,"edit");
				
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[.='Core Info']");
		CommonFunctions.javaScriptExe("window.scrollBy(100,700);");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//H2[contains(text(),'Usage Rights')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[text()='Restrictions Type']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Select/Option[.='None']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Select/Option[.='Limited']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Select/Option[.='Unlimited']");
						
		if(restriction.equals("None")) {
					
			CommonFunctions.selectDropList("//Select","None");
		}
		
		if(restriction.equals("Limited")){
				
			CommonFunctions.selectDropList("//Select","Limited");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//Label[.='Conditions']/parent::li//textarea");
			CommonFunctions.clearAndSendKeys(By.xpath("//Label[.='Conditions']/parent::li//textarea"), "");
			CommonFunctions.click(LocatorType.LinkedText, "Save");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//dfn[contains(text(),'This field is required when usage type is ')]");
			CommonFunctions.clearAndSendKeys(By.xpath("//Label[.='Conditions']/parent::li//textarea"), condition);	
		}
		
		if(restriction.equals("Unlimited")){
		
			CommonFunctions.selectDropList("//Select","Unlimited");	
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//Label[.='Conditions']/parent::li//textarea");
			CommonFunctions.clearAndSendKeys(By.xpath("//Label[.='Conditions']/parent::li//textarea"), condition);	
		}
				
		//get ItemID, Save and Verify Restriction on Asset Page
		String itemid = CommonFunctions.getItemID();
		CommonFunctions.click(LocatorType.LinkedText, "Save");	
		CommonFunctions.sleep(2000);
			
		//Verify  Asset View Page 
		Navigation.gotoURL("/files/asset/"+itemid);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//H2[contains(text(),'"+assetName+"')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//dt[text()='Restrictions Type']/parent::dl/dd[.='"+restriction+"']");
				
		//Verify on Search Page
		CommonFunctions.click(LocatorType.LinkedText,"Assets");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Usage Rights Type");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//H4[contains(text(),'"+restriction+"')]");
	    
	}
	
	@Given("^Edit asset start date and expiry date \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void Edit_asset_start_date_and_expiry_date_(String assetName, String startDate, String expiryDate) throws Throwable {
		
        /**Edit Asset Start and Expiry Date using Format DD.MM.YYYY */
		
		//Search for Asset, Edit and Verify Fields
		Search_for_an_asset_and_click_on_asset(assetName);
		CommonFunctions.click(LocatorType.LinkedText,"edit");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//span[@class='title'][text()='"+assetName+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[.='Core Info']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h2[contains(text(),'Core Info')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[.='Start Date']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[.='Start Date']/parent::li//input[contains(@id,'ITEM')]");
		CommonFunctions.javaScriptExe("window.scrollBy(100,700);");
				
		//Verify Expiry Date Required
		CommonFunctions.checkBox(LocatorType.id, "hasExpiryDate");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[text()='Expiry Date']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[text()='Expiry Date']/parent::li//input[contains(@id,'ITEM')]");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Save");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Publish and Email Watchers");
		CommonFunctions.click(LocatorType.LinkedText, "Save");
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//dfn[.='This field is required.']");
					
		//Enter New Details and Save
		CommonFunctions.clearAndSendKeys(By.xpath("//label[.='Start Date']/parent::li//input[contains(@id,'ITEM')]"), startDate);
		CommonFunctions.clearAndSendKeys(By.xpath("//label[.='Expiry Date']/parent::li//input[contains(@id,'ITEM')]"), expiryDate);
		CommonFunctions.click(LocatorType.LinkedText, "Save");
		CommonFunctions.sleep(2000);
	   
	}

	@Then("^Remove asset expiry date \"([^\"]*)\"$")
	public void Remove_asset_expiry_date(String assetName) throws Throwable {
		
		//Search Asset Uncheck Expiry Date Box and Save
		Search_for_an_asset_and_click_on_asset(assetName);
		CommonFunctions.click(LocatorType.LinkedText,"edit");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//span[@class='title'][text()='"+assetName+"']");
		CommonFunctions.javaScriptExe("window.scrollBy(100,700);");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "hasExpiryDate");
		CommonFunctions.uncheckBox(LocatorType.id, "hasExpiryDate");
		CommonFunctions.click(LocatorType.LinkedText, "Save");
	   
	}
	
	@Given("^Delete the published asset \"([^\"]*)\"$")
	public void Delete_the_published_asset(String assetName) throws Throwable {
		
		// Search asset getItemID and Delete
		Search_for_an_asset_and_click_on_asset(assetName);
		String itemid = CommonFunctions.getItemID();
		CommonFunctions.click(LocatorType.LinkedText, "edit");
		CommonFunctions.click(LocatorType.LinkedText, "Delete");
		CommonFunctions.confirmAlert("OK");
		
		//Verify Asset Deleted
		Navigation.refreshPage();
		Navigation.gotoURL("/files/asset/"+itemid);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h2[text()='We could not find that page. Click ']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h2[text()=' to get to your dashboard.']");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "here");
		CommonFunctions.click(LocatorType.LinkedText, "here");
			    
	}
	
	@Then("^Verify the fields in create new collection page$")
	public void Verify_the_fields_in_create_new_collection_page() throws Throwable {
		
		// Verify Create new Collection Pop Fields and Labels
		CommonFunctions.click(LocatorType.LinkedText, "+ New Collection");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//h2[contains(node(),'Create new Collection')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//label[contains(text(),'Title')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "newCollectionName");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//label[contains(text(),'Description')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "newCollectionDescription");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//label[contains(text(),'Privacy')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//input[contains(@value,'private')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//input[contains(@value,'public')]");

		// Verify Buttons
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//button[contains(node(),'Cancel')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath,	"//button[contains(node(),'Create')]");

		// Verify Mandatory Field Error
		CommonFunctions.click(LocatorType.Xpath, "//button[contains(node(),'Create')]");
		VerificationFunctions.verifyText(LocatorType.className, "error", "textContent","required");
	    
	}


}








