package onbrand;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.DataTable;
import onbrand.tools.DriverManager;

public class BrandAssets {
	private int totalDisplayBrandAssets; //the total of displayed brand assets, it is updated after adding o deleting brand assets
	private String lastSearchKeyword; // the keyword to search
	public int defaultBrandAssetCount; //number of brand assets displayed  according to item per Page and pagination (if it there is) selected 
	private String brandAssetsPath = "C:\\svnqa\\OB Cucumber Automation\\Onbrand\\TestData\\ba.img3.jpg";
	public String defaulBrandAssetName = "ba.img3.jpg";
	private int selectItemPerPageOption; //the selected option of items per page : 5,15,30 or 45
	private int totalNumberOfPagination; //total number of pagination calculated according to selected item per page and totalDisplayBrandAssets
	private String paginationBarPosition; // top or bottom values
	private static String HEADER = "header";
	private static String FOOTER = "footer";

	public Map<String, String> firstVersion; //It contains the values of the fields changed the first time for compare versions
	public Map<String, String> secondVersion; //It contains the values of the fields changed the second time for compare versions
	public List<String> fieldsToChange; //It contains the fields that will be modified in order to get a new version
	String brandAssetTabName;
	private static String BLUE_COLOR = "#336699"; //This color is used to verify that the labels were modified
	private static String RED_BACKGROUND = "#ffe2d9"; //This color is used to verify that the old value background was changed
	private static String GREEN_BACKGROUND = "#d5f0c7"; //This color is used to verify that the current value background was changed
	private static String RED_COLOR = "#b32d00"; //This color is used to verify that the old value color was changed
	private static String GREEN_COLOR = "#2b8000"; //This color is used to verify that the current value color was changed
	private String nextToLastVersion; //It will used to get the last version before creating a new one
	private List<String> blueLabels; //It will used to get the list of the labels that are in blue color
	private List<String> createdAssetNamesList = new ArrayList<String>(); // list of name for each created item
	private int totalCreatedAssetsWithPrefix = 0;  //the total of created Assets in execution of automated scenario("brand.asset as prefix"), it is updated after adding o deleting assets
	
	/**
	 * Navigate to a single item
	 * In case of a bulk edit 
	 * @param nameItem
	 */
	@And("^Navigate to \"([^\"]*)\"$")
	public void Navigate_to(String nameItem) throws Throwable{
		CommonFunctions.waitForActiveTab();
		String[] nameList;
		CommonCode common= new CommonCode();
		brandAssetTabName=getActiveTab();
		
		try {
			if(nameItem.contains(",")){
				WebElement tab=DriverManager.driver.findElement(By.xpath("//li[contains(text(),'"+brandAssetTabName+"')]"));		
				common.Select_the_view("List"); 
				nameList=nameItem.split(",");
				String id="";
				
				for (int i = 0; i < nameList.length; i++) {
					id=DriverManager.driver.findElement(By.xpath(".//td//a[contains(text(),'"+nameList[i]+"')]//preceding::td[1]")).getText();					
					CommonFunctions.checkBox(LocatorType.Xpath, "//input[contains(@id,'"+id.trim()+"')]");
				}
				
				CommonFunctions.click(LocatorType.cssSelector, "."+tab.getAttribute("class").replace(" active", "")+" .bulkedit_stackcount");
				CommonFunctions.click(LocatorType.Xpath, "//a[contains(@title,'Bulk Edit')]");
				
			}else{
				CommonFunctions.waitForElement(LocatorType.Xpath, "//a[contains(text(),'"+nameItem+"')]", 20);
				CommonFunctions.click(LocatorType.Xpath, "//a[contains(text(),'"+nameItem+"')]");
			}
		} catch (Exception e) {
			Assert.fail("The item was not found");
		}	
	}
	
	/**
	 * Verify all item on Comments section
	 */
	@Then("Verify comments components$")
	public void Verify_comments_components() throws Throwable{
		
		CommonFunctions.click(LocatorType.Xpath, "//span[contains(text(),'Comments')]");
		
		try {			
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h2[contains(text(),'Comments')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Enter a comment')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//textarea[contains(text(),'Enter a comment...')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Post Comment')]");
			
			CommonFunctions.click(LocatorType.Xpath, "//span[contains(text(),'Core Info')]");		
			
		} catch (Exception e) {
			Assert.fail("Some element is missing on Comments section");
		}
	}
	
	/**
	 * Verifying main components of an brand asset 
	 * @param nameItem
	 * @throws Throwable
	 */
	@Then("Verify main components \"([^\"]*)\" \"([^\"]*)\"$")
	public void Verify_main_components(String nameTab,String nameItem)throws Throwable{
				
		verifyCommonComponents(nameItem);
		try {
			switch (nameTab) {
			case "Awaiting Approval":
					VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Reject')]");
					VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Publish')]");
					VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Rejection Comment')]");
					VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//li[contains(@rel,'rejection comment')]//textarea[contains(@class,'portlet-form-input-field textarea')]");
					break;			
			case "Published":
					VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Publish and Email Watchers')]");
					VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Archive')]");
					break;
			case "Rejected":
					VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Archive')]");
					break;
			case "Archived":
					VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Re-Publish')]");
					break;
			case "Expired":
					VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Archive')]");
					break;			
			default:
				Assert.fail("There is not actions for the tab: "+nameTab);
				break;
			}			
		} catch (Exception e) {
			Assert.fail("An specified item is missing of "+nameTab);
		}	
	} 
	
	/**
	 * Verifying the error for each file on each tab
	 */
	@Then("Verify the errors$")
	public void Verify_the_errors(){
		
		CommonFunctions.selectDropList("//label[contains(text(),'Restrictions Type')]//following::select", "Limited");
		DriverManager.driver.findElement(By.xpath("//label[contains(text(),'Conditions')]//following::textarea[contains(@class,'portlet-form-input-field textarea')]")).clear();
		
		CommonFunctions.checkBox(LocatorType.Xpath, "//label[contains(text(),'Expiry Date Required')]//following::input[contains(@id,'hasExpiryDate')]");
		DriverManager.driver.findElement(By.xpath("//label[contains(text(),'Expiry Date')]//following::input[contains(@class,'portlet-form-input-field calendar')]")).clear();
		
		DriverManager.driver.findElement(By.cssSelector("#item_description")).clear();
		DriverManager.driver.findElement(By.cssSelector("#item_name")).clear();
			
		try {
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//span[contains(text(),'4 errors')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//li//span[contains(text(),'4')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//label[contains(text(),'Name')]//following-sibling::dfn[contains(text(),'This field is required')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//label[contains(text(),'Description')]//following-sibling::dfn[contains(text(),'This field is required')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//label[contains(text(),'Conditions')]//following-sibling::dfn[contains(text(),'This field is required when usage type is ')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//label[contains(text(),'Expiry Date')]//following-sibling::dfn[contains(text(),'This field is required')]");
			
		} catch (Exception e) {
			Assert.fail("Some error is missing");
		}		
	}
	
	/**
	 * Method to verify main components on a Brand Asset
	 * @param name
	 */
	public void verifyCommonComponents(String name){
		try {
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h1[contains(@id,'title')]//span[contains(text(),'"+name+"')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h1[contains(@id,'title')]//span[contains(text(),'"+name+"')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[contains(@id,'item_name')][contains(@value,'"+name+"')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//span[contains(text(),'Core Info')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//span[contains(@class,'title')][contains(text(),'Comments')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Compare Versions')]");
			
			//Verifying the sections
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h2[contains(text(),'Core Info')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h2[contains(text(),'Taxonomy')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h2[contains(text(),'Usage Rights')]");
			
			//Verifying the labels
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//li[contains(@class,'unit thumbnail-image')]//label[contains(text(),'Preview')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Upload File')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Upload Custom Preview')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Name')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Description')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Keywords')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Client')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Start Date')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Marketing Activity')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Market Suitability')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Asset type')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Language')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Market Created')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Restrictions Type')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//li[contains(@class,'unit checkbox help')]//label[contains(text(),'Expiry Date Required')]");
			//VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//li[contains(@class,'unit shorttext')]//label[contains(text(),'Expiry Date')]");
			//VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Conditions')]");
			
			//Verifying the fields
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//li[contains(@class,'unit thumbnail-image')]//img[contains(@src,'preview')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//li[contains(@class,'unit thumbnail-image')]//a[contains(text(),'Download Asset')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Upload File')]//following-sibling::input[contains(@class,'portlet-form-input-field')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[contains(@id,'item_file_custom_preview')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[contains(@id,'item_name')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//textarea[contains(@id,'item_description')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[contains(@id,'item_keywords')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Client')]//following::input[contains(@class,'portlet-form-input-field text')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//li[contains(@rel,'Start Date')]//input[contains(@class,'portlet-form-input-field calendar')]");
			//VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Conditions')]//following::textarea[contains(@class,'portlet-form-input-field textarea')]");
			
			
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Restrictions Type')]//following::select");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Expiry Date Required')]//following::input[contains(@id,'hasExpiryDate')]");
			//VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Expiry Date')]//following::input[contains(@class,'portlet-form-input-field calendar')]");
			
			//Verifying buttons
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Delete')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Cancel')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div[contains(@id,'overlay')]//a[contains(text(),'Save')]");
			Assert.assertTrue(true);
			
		} catch (Exception e) {
			Assert.fail("Some element is missing on single Brand Asset page");
		}		
	}	
	
	/**
	 * Update a brand asset with the values given in the Brand Asset Feature file.
	 * @param file
	 * @param preview
	 * @param name
	 * @param description
	 * @param keyword
	 * @param client
	 * @param startDate
	 * @param marketingActivity
	 * @param restrictType
	 * @param conditions
	 * @param expiryDateRequired
	 * @param expiryDate
	 * @throws Throwable
	 */
	@When("Update a brand asset \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void Update_a_brand_asset(String file, String preview, String name, String description, String keyword, String client, String startDate, String marketingActivity,String restrictType, String conditions, String expiryDateRequired, String expiryDate) throws Throwable{
		
		Fill_data(file, preview, name, description, keyword, client, startDate, marketingActivity, restrictType, conditions, expiryDateRequired, expiryDate);
		CommonFunctions.clickOnOverlayButton("Save");		
   }
	
   /**
    * Fill the  fields with the values given
    * @param file
    * @param preview
    * @param name
    * @param description
    * @param keyword
    * @param client
    * @param startDate
    * @param marketingActivity
    * @param restrictType
    * @param conditions
    * @param expiryDateRequired
    * @param expiryDate
    * @throws Throwable 
    */
   @When("^Fill data \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
   public void Fill_data(String file, String preview, String name, String description, String keyword, String client, String startDate, String marketingActivity,String restrictType, String conditions, String expiryDateRequired, String expiryDate) throws Throwable {
	 
	 //Giving the values
	 DriverManager.driver.findElement(By.cssSelector("#item_file")).sendKeys(file);
     DriverManager.driver.findElement(By.cssSelector("#item_file_custom_preview")).sendKeys(preview);
	 setName(name);
	 setDescription(description);
	 setKeywords(keyword);
	 setClient(client);
	 setStartDate(startDate);
	  		
	 if(marketingActivity.equals("yes")){
	 	CommonFunctions.checkBox(LocatorType.Xpath,"//a[contains(text(),'Marketing Activity')]//preceding::input[contains(@class,'tree-check-box')]");	
	 }else{
	 	CommonFunctions.uncheckBox(LocatorType.Xpath,"//a[contains(text(),'Marketing Activity')]//preceding::input[contains(@class,'tree-check-box')]");
	 }	
	 
	 setRestrictionType(restrictType);
	 setConditions(conditions);	 	
	 setExpiryDateRequired(expiryDateRequired);		
	 setExpiryDate(expiryDate);
   }
	
   /**
    * Verify that the brand asset was edited
    */
   @Then("Verify the brand asset \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
   public void Verify_the_brand_asset(String name, String description, String keyword, String client, String startDate, String marketingActivity,String restrictType, String conditions, String expiryDateRequired, String expiryDate) throws Throwable{
	   	   
	   Assert.assertEquals(client, DriverManager.driver.findElement(By.xpath("//label[contains(text(),'Client')]//following::input[contains(@class,'portlet-form-input-field text')]")).getAttribute("value"));
	   Assert.assertEquals(startDate, DriverManager.driver.findElement(By.xpath("//li[contains(@rel,'Start Date')]//input[contains(@class,'portlet-form-input-field calendar')]")).getAttribute("value"));
	   
	   Verify_brand_asset(name, description, keyword, restrictType, conditions, expiryDateRequired, expiryDate);	   	   
   }
   
   /**
    * Verifying that the element was archived/published/expired/rejected correctly
    * @param name
    * @param action
    * @throws Throwable
    */
   @Then("Verify if \"([^\"]*)\" was \"([^\"]*)\"$")
   public void Verify_if_was(String name,String action) throws Throwable{
	   CommonCode commonCode= new CommonCode();
	   
	   switch (action) {
	   case "archived":
		   if(!brandAssetTabName.equals("Archived")){
			   commonCode.Verify_item_is_not_displayed(name);
		   	   Go_to_tab("Archived");
		   }		   
		   Navigate_to(name);		   
		   break;
	   case "published":
		   if(!brandAssetTabName.equals("Published")){
			   commonCode.Verify_item_is_not_displayed(name);
		   	   Go_to_tab("Published");
		   }		   
		   Navigate_to(name);
		   break;
	   case "expired":
		   commonCode.Verify_item_is_not_displayed(name);
		   Go_to_tab("Expired");
		   Navigate_to(name);		   
		   break;
	   case "rejected":
		   if(!brandAssetTabName.equals("Rejected")){
			   commonCode.Verify_item_is_not_displayed(name);
		   	   Go_to_tab("Rejected");
		   }		   
		   Navigate_to(name);  		   
		   break;
	   case "deleted":
		   commonCode.Verify_item_is_not_displayed(name);		   
		   break;
	   case "approved":
		   commonCode.Verify_item_is_not_displayed(name);
		   Go_to_tab("Approved");
		   Navigate_to(name);
		   break;
	   case "deactivated":
		   commonCode.Verify_item_is_not_displayed(name);
		   Go_to_tab("Inactive");
		   Navigate_to(name);	   
		   break;
	   default:
		 Assert.fail("The action "+action+" doesn't exist");
	   }	    
   }
   
   /**
    * Method to perform an action on an item
    * Publish, Archive, Cancel, Delete, Expire, Publish, Approve, Deactivate
    * @param action
    * @throws Throwable 
    */
   @When("\"([^\"]*)\" the item$")
   public void the_item(String action) throws Throwable{
	   switch (action) {
	   case "Publish":
		   if(brandAssetTabName.equals("Expired")){
			   CommonFunctions.uncheckBox(LocatorType.Xpath,"//label[contains(text(),'Expiry Date Required')]//following::input[contains(@id,'hasExpiryDate')]");
		   }		   
		   CommonFunctions.clickOnOverlayButton(action);
		   break;
	   case "Expire":
		   setExpiryDateRequired("yes");
		   setExpiryDate("02.02.2014");
		   	   
		   CommonFunctions.clickOnOverlayButton("Save");
		   break;
	   case "Delete":
		   CommonFunctions.click(LocatorType.Xpath, "//a[contains(text(),'"+action+"')]");
		   
		   try {
			   CommonFunctions.confirmAlert("Yes");
			   CommonFunctions.waitUntilCompleteLoading();
		   } catch (Exception e) {
			   Assert.fail("The modal window was not displayed");
		   }		   
		   break;
	   case "Approve":
		   BrandUsers brandUser=new BrandUsers();
		   brandUser.setUserProfilePermissions("Testing");
		   CommonFunctions.clickOnOverlayButton(action);
		   	break;
	   case "Reject":
		   Locator rejectCommentLocator=Locator.byXPath("//li[contains(@rel,'rejection comment')]//textarea[contains(@class,'portlet-form-input-field textarea')]");
		   Locator noteLocator=Locator.byXPath(".//label[contains(text(),'Note')]//following-sibling::input");
		   
		   if(rejectCommentLocator.isPresent())
			   if(DriverManager.driver.findElement(By.xpath("//li[contains(@rel,'rejection comment')]//textarea[contains(@class,'portlet-form-input-field textarea')]")).isDisplayed())
				   setRejectionComment("The item was rejected");
		   
		   if(noteLocator.isPresent())
			   DriverManager.driver.findElement(By.xpath(".//label[contains(text(),'Note')]//following-sibling::input")).sendKeys("User was rejected");
		   
		   CommonFunctions.clickOnOverlayButton(action);
		   	break;
	   default:
		   //For now doing click on Cancel, Deactivate, Archive buttons
		   CommonFunctions.clickOnOverlayButton(action);
		 break;
	   }	      
   }

   /**
    * Method to update a brand asset
    * @param name
    * @param description
    * @param keywords
    * @param restrictType
    * @param conditions
    * @param expiryDateRequired
    * @param expiryDate
    * @throws Throwable
    */
   @When("Update brand assets \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
   public void Update_brand_assets(String name, String description, String keywords, String restrictType, String conditions, String expiryDateRequired, String expiryDate) throws Throwable {
	   
	   //CommonFunctions.click(LocatorType.Xpath, ".//span[contains(text(),'Core Info')]");
	   fill_all_data(name, description, keywords, restrictType, conditions, expiryDateRequired, expiryDate);
	   CommonFunctions.clickOnOverlayButton("Save");
   }
   
   /**
    * Method to fill the form with the given values
    * @param name
    * @param description
    * @param keywords
    * @param restrictType
    * @param conditions
    * @param expiryDateRequired
    * @param expiryDate
    * @throws Throwable
    */
   @When("Fill all data \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
   public void fill_all_data(String name, String description, String keywords, String restrictType, String conditions, String expiryDateRequired, String expiryDate) throws Throwable {
	   
	   CommonFunctions.click(LocatorType.Xpath, ".//span[contains(text(),'Core Info')]");
	   
	   setName(name);
	   setDescription(description);
	   setKeywords(keywords);
	   setRestrictionType(restrictType);	   
	   setConditions(conditions); 		
	   setExpiryDateRequired(expiryDateRequired);
	   setExpiryDate(expiryDate);
   }
   
   /**
    * Verifying if the values are correct
    * @param name
    * @param description
    * @param keyword
    * @param restrictType
    * @param conditions
    * @param expiryDateRequired
    * @param expiryDate
    * @throws Throwable
    */
   @Then("Verify brand asset \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
   	public void  Verify_brand_asset(String name, String description, String keyword, String restrictType, String conditions, String expiryDateRequired, String expiryDate) throws Throwable {
	   
	   Assert.assertEquals(name, DriverManager.driver.findElement(By.xpath(".//h1//span[contains(@class,'title')]")).getText());
	   Assert.assertEquals(name, DriverManager.driver.findElement(By.xpath("//input[contains(@id,'item_name')]")).getAttribute("value"));
	   Assert.assertEquals(description, DriverManager.driver.findElement(By.xpath("//textarea[contains(@id,'item_description')]")).getText());
	   Assert.assertEquals(keyword, DriverManager.driver.findElement(By.xpath("//input[contains(@id,'item_keywords')]")).getAttribute("value"));
	   
	   if(restrictType.equals("Limited")){
		   Assert.assertEquals("true", DriverManager.driver.findElement(By.xpath("//label[contains(text(),'Restrictions Type')]//following::select//option[contains(text(),'Limited')]")).getAttribute("selected"));
		   Assert.assertEquals(conditions, DriverManager.driver.findElement(By.xpath("//label[contains(text(),'Conditions')]//following::textarea[contains(@class,'portlet-form-input-field textarea')]")).getText());
	   }else{
			if(restrictType.equals("Unlimited")){
				Assert.assertEquals("true", DriverManager.driver.findElement(By.xpath("//label[contains(text(),'Restrictions Type')]//following::select//option[contains(text(),'Unlimited')]")).getAttribute("selected"));
				Assert.assertEquals(conditions, DriverManager.driver.findElement(By.xpath("//label[contains(text(),'Conditions')]//following::textarea[contains(@class,'portlet-form-input-field textarea')]")).getText());
			}else{
				Assert.assertEquals("true", DriverManager.driver.findElement(By.xpath("//label[contains(text(),'Restrictions Type')]//following::select//option[contains(text(),'None')]")).getAttribute("selected"));
			}
	   }
	   
	   if(expiryDateRequired.equals("yes")){
		   Assert.assertTrue(DriverManager.driver.findElement(By.xpath("//label[contains(text(),'Expiry Date Required')]//following::input[contains(@id,'hasExpiryDate')]")).isSelected());
		   Assert.assertEquals(expiryDate,DriverManager.driver.findElement(By.xpath("//label[contains(text(),'Expiry Date')]//following::input[contains(@class,'portlet-form-input-field calendar')]")).getAttribute("value"));
		}else{
			Assert.assertFalse(DriverManager.driver.findElement(By.xpath("//label[contains(text(),'Expiry Date Required')]//following::input[contains(@id,'hasExpiryDate')]")).isSelected());
		}
	   	
	   CommonFunctions.clickOnOverlayButton("Save");	
   	}
   
   private void changeAssetName(String originAssetName, String newAssetName) {
	   
       CommonFunctions.click(LocatorType.Xpath, "//ul[@class='gridList']//strong[./text()='" + originAssetName + "']");
       CommonFunctions.clearAndSendKeys(By.id("item_name"), newAssetName);
       CommonFunctions.click(LocatorType.cssSelector, ".buttons input[value='Save']");
       CommonFunctions.waitForElement(LocatorType.Xpath, "//div[@id='metadataRequired']//ul[@class='gridList']//strong[./text()='" + newAssetName+ "']", 10);   
   }
   
   /**
    * Method to submit n brand assets on x tab
    * @param numberRequiredAssets
    * @param brandAssetTabName
    * @param prefixName
    * @param filePath
    * @param nameFile
    * @throws Throwable
    */
   @When("Upload several assets \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
   public void Upload_several_assets(int numberRequiredAssets,String brandAssetTabName, String prefixName, String filePath, String nameFile) throws Throwable {
       Assets asset = new Assets();
       CommonCode commonCode = new CommonCode();
       List<String> names= new ArrayList<>();
            
       for (int i = 1; i <= numberRequiredAssets; i++) {
	    	asset.Click_on_upload_assets_button();
	        commonCode.Upload_file(filePath);
	       
	        CommonFunctions.waitForElement(LocatorType.Xpath, "//div[@id='metadataRequired']//ul[@class='gridList']//strong[./text()='" + nameFile + "']", 10);
	        changeAssetName(nameFile, prefixName+i);
	           
	        if (brandAssetTabName.equals("Awaiting Approval") || brandAssetTabName.equals("Rejected")) {
	          asset.Upload_an_asset_and_send_for_approval(prefixName+i);	        	   
	        }else{
			  asset.Upload_an_asset_and_approve(prefixName+i); 
	        }	           	           
	        names.add(prefixName+i);
	   }     
       createNumBrandAssetsPerTab(numberRequiredAssets,brandAssetTabName,names);
   }
   /**
    * Method to reject/archive/expire a brand asset
    * @param numberRequiredAssets
    * @param brandAssetTabName
    * @param names
    * @throws Throwable
    */
   private void createNumBrandAssetsPerTab(int numberRequiredAssets,String brandAssetTabName, List<String> names) throws Throwable {
	   Navigation.gotoURL("/admin/brand_admin/files/brand_assets/");
	   CommonFunctions.waitForActiveTab();
	   
	       for (int j = 0; j < names.size(); j++) {
		    				
				switch (brandAssetTabName) {				   
				   case "Rejected":
					   Go_to_tab("Awaiting Approval");
					   CommonFunctions.waitForActiveTab();
					   Navigate_to(names.get(j));
					   the_item("Reject");
					   CommonFunctions.waitForActiveTab();
					   break;
				   case "Archived":
					   Go_to_tab("Published");
					   Navigate_to(names.get(j));
					   the_item("Archive");
					   CommonFunctions.waitForActiveTab();
					   break;
				   case "Expired":
					   Go_to_tab("Published");
					   Navigate_to(names.get(j));
					   the_item("Expire");					
					   CommonFunctions.waitForActiveTab();
					   break;
				}		
		}		         
   }
   
   //Jose
   /**
    * Those Methods are used to set the values for the different fields related to Core Info and Usage Rights sections
    */ 
   public void setUploadFile(String file) throws Throwable {
	   DriverManager.driver.findElement(By.cssSelector("#item_file")).sendKeys(file);
   }

   public void setUploadCustomPreview(String customPreview) throws Throwable {
	   DriverManager.driver.findElement(By.cssSelector("#item_file_custom_preview")).sendKeys(customPreview);

   }

   public void setName(String name) throws Throwable {
	   DriverManager.driver.findElement(By.cssSelector("#item_name")).clear();
	   DriverManager.driver.findElement(By.cssSelector("#item_name")).sendKeys(name);
   }

   public void setDescription(String description) throws Throwable {
	   DriverManager.driver.findElement(By.cssSelector("#item_description")).clear();
	   DriverManager.driver.findElement(By.cssSelector("#item_description")).sendKeys(description);
   }

   public void setKeywords(String keywords) throws Throwable {
	   DriverManager.driver.findElement(By.cssSelector("#item_keywords")).clear();
	   DriverManager.driver.findElement(By.cssSelector("#item_keywords")).sendKeys(keywords);
   }

   public void setClient(String client) throws Throwable {
	   DriverManager.driver.findElement(By.xpath("//label[contains(text(),'Client')]//following::input[contains(@class,'portlet-form-input-field text')]")).clear();
	   DriverManager.driver.findElement(By.xpath("//label[contains(text(),'Client')]//following::input[contains(@class,'portlet-form-input-field text')]")).sendKeys(client);
   }

   public void setRejectionComment(String rejectionComment) throws Throwable {
	   DriverManager.driver.findElement(By.cssSelector("li[class^=index_1] li[id^=unit][rel='rejection comment'] textarea")).clear();
	   DriverManager.driver.findElement(By.cssSelector("li[class^=index_1] li[id^=unit][rel='rejection comment'] textarea")).sendKeys(rejectionComment);
   }

   public void setStartDate(String startDate) throws Throwable {
	   DriverManager.driver.findElement(By.xpath("//li[contains(@rel,'Start Date')]//input[contains(@class,'portlet-form-input-field calendar')]")).clear();
	   DriverManager.driver.findElement(By.xpath("//li[contains(@rel,'Start Date')]//input[contains(@class,'portlet-form-input-field calendar')]")).sendKeys(startDate);
   }

   public void setRestrictionType(String restrictionType) throws Throwable {
	   WebElement selectedRestriction = DriverManager.driver.findElement(By.cssSelector("option[selected=true]"));
	   if (!selectedRestriction.equals(restrictionType)) {
		   CommonFunctions.selectDropList("//label[contains(text(),'Restrictions Type')]//following::select", restrictionType);
	   }
   }

   public void setConditions(String conditions) throws Throwable {
	   if(DriverManager.driver.findElement(By.xpath("//label[contains(text(),'Conditions')]//following::textarea[contains(@class,'portlet-form-input-field textarea')]")).isDisplayed()) {
		   DriverManager.driver.findElement(By.xpath("//label[contains(text(),'Conditions')]//following::textarea[contains(@class,'portlet-form-input-field textarea')]")).clear();
		   DriverManager.driver.findElement(By.xpath("//label[contains(text(),'Conditions')]//following::textarea[contains(@class,'portlet-form-input-field textarea')]")).sendKeys(conditions);
	   }
   }

   public void setExpiryDateRequired(String expiryDateRequired) throws Throwable {
	   if(expiryDateRequired.equals("yes")) {
		   CommonFunctions.checkBox(LocatorType.Xpath, "//label[contains(text(),'Expiry Date Required')]//following::input[contains(@id,'hasExpiryDate')]");
	   } else {
		   CommonFunctions.uncheckBox(LocatorType.Xpath, "//label[contains(text(),'Expiry Date Required')]//following::input[contains(@id,'hasExpiryDate')]");
	   }
   }

   public void setExpiryDate(String expiryDate) throws Throwable {
	   if(DriverManager.driver.findElement(By.xpath("//label[contains(text(),'Expiry Date')]//following::input[contains(@class,'portlet-form-input-field calendar')]")).isDisplayed()) {
		   DriverManager.driver.findElement(By.xpath("//label[contains(text(),'Expiry Date')]//following::input[contains(@class,'portlet-form-input-field calendar')]")).clear();
		   DriverManager.driver.findElement(By.xpath("//label[contains(text(),'Expiry Date')]//following::input[contains(@class,'portlet-form-input-field calendar')]")).sendKeys(expiryDate);
	   }
   }

   @When("^Create first version$")
   public void Create_first_version(DataTable dataTable) throws Throwable {
	   List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
	   nextToLastVersion = getVersion(1);
	   firstVersion = getAssetChanges(data, "firstVersion");
	   secondVersion = getAssetChanges(data, "secondVersion");
	   fieldsToChange = getFieldsToChange(data);
	   updateAssetFields(firstVersion, fieldsToChange);
	   saveAsset();
	   Reset_brand_assets_search_results();
   }

   @When("^Create second version$")
   public void Create_second_version() throws Throwable {
	   updateAssetFields(secondVersion, fieldsToChange);
	   saveAsset();
   }

   @Then("^Verify that a new version is created$")
   public void Verify_that_a_new_version_is_created() throws Throwable {
	   String lastVersion = getVersion(1);
	   Assert.assertNotEquals(lastVersion, nextToLastVersion);
	   Assert.assertEquals(nextToLastVersion, getVersion(2));
   }
   
   @When("^Select the last version$")
   public void Select_the_last_version() throws Throwable {
	   selectCompareVersions();
	   selectLastCompareVersion();
   }
   
   @Then("^Verify the number of changes is correct$")
   public void Verify_the_number_of_changes_is_correct() throws Throwable {
	   List<WebElement> labels = DriverManager.driver.findElements(By.cssSelector("li[class^=active] li[class^=unit] > label"));
	   blueLabels = getChangedFields(labels);
	   int numberOfChanges = blueLabels.size();
	   String fileNameChanges = DriverManager.driver.findElement(By.cssSelector("#title .diff_tally")).getText();
	   String coreInfoChanges = DriverManager.driver.findElement(By.cssSelector("li[class^=active] .diff_tally")).getText();
	   if (numberOfChanges == 1) {
		   Assert.assertEquals(numberOfChanges + " change", fileNameChanges);
	   } else {
		   Assert.assertEquals(numberOfChanges + " changes", fileNameChanges);
	   }
	   Assert.assertEquals(Integer.toString(numberOfChanges), coreInfoChanges);
   }
   
   @Then("^Verify that changed fields are in blue$")
   public void Verify_that_changed_fields_are_in_blue() throws Throwable {
	   Assert.assertEquals(firstVersion.size(), blueLabels.size());
	   for (int index=0; index < blueLabels.size(); index++) {
		   Assert.assertNotEquals(firstVersion.get(blueLabels.get(index)), null);
	   }
   }
   
   @Then("^Verify that changed Core Info fields have an icon$")
   public void Verify_that_changed_Core_Info_fields_have_an_icon() throws Throwable {
	   List<WebElement> labels = DriverManager.driver.findElements(By.cssSelector("li[class='index_1 stacked_mode'] li[class^=unit] > label"));
	   List<String> coreInfoBlueLabels = getChangedFields(labels);
	   List<WebElement> updatedFields = DriverManager.driver.findElements(By.cssSelector("li[class='index_1 stacked_mode'] li[class*='diff']"));
	   Assert.assertEquals(coreInfoBlueLabels.size(), updatedFields.size());
   }
   
   @Then("^Verify that changes are correct in the modal window$")
   public void Verify_that_changes_are_correct_in_the_modal_window() throws Throwable {
	   List<WebElement> updatedFields = DriverManager.driver.findElements(By.cssSelector("li[class='index_1 stacked_mode'] li[class*='diff']"));
	   String userName = DriverManager.driver.findElement(By.cssSelector(".user a")).getText();
	   for (WebElement element : updatedFields) {
		   String relAttribute = element.getAttribute("rel");
		   CommonFunctions.click(By.cssSelector("li[rel='" + relAttribute + "'] .diff_icon"));
		   String oldValue = firstVersion.get(WordUtils.capitalizeFully(relAttribute));
		   String currentValue = secondVersion.get(WordUtils.capitalizeFully(relAttribute));
		   Assert.assertEquals("Modified by " + userName, DriverManager.driver.findElement(By.id("modal_title")).getText());
		   List<WebElement> changes = DriverManager.driver.findElements(By.cssSelector("#modal_body span"));
		   verifyChangesInModalWindow(changes, oldValue, currentValue);
		   CommonFunctions.click(By.cssSelector(".close"));
	   }
   }
   
   @When("^Select latest version$")
   public void Select_latest_version() throws Throwable {
	   CommonFunctions.click(LocatorType.cssSelector,"#latest_version a");
	   CommonFunctions.waitForElement(LocatorType.Xpath, ".//*[@id='compare_versions']/span/a[contains(text(),'Compare Versions')]", 10);
   }
   
   @When("^Verify that latest version is selected$")
   public void Verify_that_latest_version_is_selected() throws Throwable {
	   List<WebElement> actions = DriverManager.driver.findElements(By.cssSelector("#titlebar .actions li"));
	   for (WebElement action : actions) {
		   Assert.assertNotEquals("latest_version", action.getAttribute("id"));
	   }
   }
   
   //This method set the values to the fields that will be updated
   private void updateAssetFields(Map<String, String> fieldValues, List<String> fieldsToChange) throws Throwable {
	   for (int index=0; index < fieldsToChange.size(); index++) {
		   switch (fieldsToChange.get(index)) {
		   		case "Name": 
		   			setName(fieldValues.get("Name"));
		   			break;
		   		case "Description":
		   			setDescription(fieldValues.get("Description"));
		   			break;
		   		case "Keywords":
		   			setKeywords(fieldValues.get("Keywords"));
		   			break;
		   		case "Client":
		   			setClient(fieldValues.get("Client"));
		   			break;
		   		case "Rejection Comment":
		   			setRejectionComment(fieldValues.get("Rejection Comment"));
		   			break;
		   		case "Start Date":
		   			setStartDate(fieldValues.get("Start Date"));
		   			break;
		   		case "Restrictions Type":
		   			setRestrictionType(fieldValues.get("Restrictions Type"));
		   			break;
		   		case "Conditions":
		   			setConditions(fieldValues.get("Conditions"));
		   			break;
		   		case "Expiry Date Required":
		   			setExpiryDateRequired(fieldValues.get("Expiry Date Required"));
		   			break;
		   		case "Expiry Date":
		   			setExpiryDate(fieldValues.get("Expiry Date"));
		   			break;
		   }
	   }
   }
  
   // This method saves the changes made in the asset and creates a new version
   private void saveAsset() throws Throwable {
	   CommonFunctions.click(LocatorType.Xpath, "//div[contains(@id,'overlay')]//a[contains(text(),'Save')]");
	   CommonFunctions.waitForActiveTab();
   }
   
   // This method verifies that the changes displayed in the modal window are correct
   private void verifyChangesInModalWindow(List<WebElement> changes, String oldValue, String currentValue) throws Throwable {
	   for (WebElement change : changes) {
		   if (change.getAttribute("class").contains("del")) {
			   Assert.assertEquals(oldValue, change.getText());
			   Assert.assertEquals(RED_BACKGROUND, getColorInHex(change.getCssValue("background-color")));
			   Assert.assertEquals(RED_COLOR, getColorInHex(change.getCssValue("color")));
			   Assert.assertEquals("line-through", change.getCssValue("text-decoration"));
		   } else if (change.getAttribute("class").contains("add")) {
			   Assert.assertEquals(currentValue, change.getText());
			   Assert.assertEquals(GREEN_BACKGROUND, getColorInHex(change.getCssValue("background-color")));
			   Assert.assertEquals(GREEN_COLOR, getColorInHex(change.getCssValue("color")));
		   }
	   }
   }

   //This method gets the number of changes made in the selected version
   private List<String> getChangedFields(List<WebElement> labels) throws Throwable {
	   List<String> blueLabels = new ArrayList<String>();
	   for (WebElement element : labels) {
		   if (getColorInHex(element.getCssValue("color")).equals(BLUE_COLOR) && element.isDisplayed()) {
			   blueLabels.add(element.getText());
		   }
	   }
	   return blueLabels;
   }
   
   //This method gets the color in hexadecimal format
   private String getColorInHex(String color) throws Throwable {
	   String[] hexValue = color.replace("rgba(", "").replace(")", "").split(",");
	   int hexValue1=Integer.parseInt(hexValue[0]);
	   hexValue[1] = hexValue[1].trim();
	   int hexValue2=Integer.parseInt(hexValue[1]);
	   hexValue[2] = hexValue[2].trim();
	   int hexValue3=Integer.parseInt(hexValue[2]);
	   return String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
   }

   //This method gets an specific version according to the index provided. e.g. In order to get the last version index = 1, next to last version index = 2
   private String getVersion(int index) throws Throwable {
	   String versionName;
	   selectCompareVersions();
	   versionName = DriverManager.driver.findElement(By.cssSelector("#menu li:nth-child(" + index + ")")).getText();
	   selectCompareVersions();
	   return versionName;
   }
   
   //This method expand and collapse compare versions
   private void selectCompareVersions() throws Throwable {
	   CommonFunctions.click(LocatorType.cssSelector,"#compare_versions a");
   }
   
   //This method select the last version compare versions
   private void selectLastCompareVersion() throws Throwable {
	   CommonFunctions.click(LocatorType.cssSelector,"#menu li:first-child");
	   CommonFunctions.waitUntilCompleteProcessing();
   }

   // This method stores the fields and the values that will be updated for an asset
   private Map<String, String> getAssetChanges(List<Map<String, String>> data, String column) {
	   Map<String, String> assetChanges = new HashMap<String, String>();
	   for (Map<String, String> rowData : data) {
		   assetChanges.put(rowData.get("fieldName"), rowData.get(column));
	   }
	   return assetChanges;
   }

   // This method stores the names of the fields that will be updated for an asset
   private List<String> getFieldsToChange(List<Map<String, String>> tableData) {
	   List<String> fieldNames = new ArrayList<>();
	   for (int row = 0; row < tableData.size(); row++) {
		   fieldNames.add(tableData.get(row).get("fieldName"));
	   }
	   return fieldNames;
   }

   /**
    * Clicks on an specific tab of the list container
    */
   @When("^Go to \"([^\"]*)\" tab$")
   public void Go_to_tab(String tabName) throws Throwable {
	   if (getActiveTab().compareTo(tabName) != 0) 
		   CommonFunctions.click(LocatorType.Xpath, "//ul[@class='nav']//li[text()='" + tabName + "']");
	   CommonFunctions.waitUntilCompleteLoading();
   }

   /**
    * This method gets the name of the active tab in the list container
    */
   public String getActiveTab() {
	   return DriverManager.driver.findElement(By.cssSelector(".nav [class~=active]")).getText();
   }

/*by virginia*/
   @When("^Search (\\d+) brand assets with \"(.*?)\" item name$")
   public void search_brand_assets_with_item_name(int numberOfAssets, String assetName) throws Throwable {
	   initializeAssetsCreation();
	   Search_brand_assets_by(assetName);
	   int matchedItem = CommonFunctions.getElementsCount(CommonFunctions.getItemNameLocatorOfActiveView());
	   WebElement activeTabElem = DriverManager.driver.findElement(By.cssSelector("#listContainer .nav li[class*='" + CommonFunctions.getClassActiveTab() + "']"));
	  
	   while (matchedItem != numberOfAssets) {
		   Reset_brand_assets_search_results();
		   if (matchedItem > numberOfAssets) {
			   deleteBrandAssetsWithPrefix(matchedItem - numberOfAssets, assetName);			  
		   } else if (matchedItem < numberOfAssets){
			   addNewBrandAssets(numberOfAssets - matchedItem, activeTabElem.getText(), assetName, brandAssetsPath);
		   }
		   
		   Search_brand_assets_by(assetName);
		   matchedItem = CommonFunctions.getElementsCount(CommonFunctions.getItemNameLocatorOfActiveView());
	   }
   }
   
   @Given("^Validate there are at least \"([^\"]*)\" brand assets in \"([^\"]*)\"$")
   public void Validate_there_are_at_least_brand_assets_in(int numberOfAssets, String tabName) throws Throwable {
	   initializeAssetsCreation();
	   if (numberOfAssets > totalDisplayBrandAssets) {
		   addNewBrandAssets(numberOfAssets - totalDisplayBrandAssets, tabName, "brand.asset", brandAssetsPath);
	   }
   }

	private void addNewBrandAssets(int numberRequiredAssets, String brandAssetTabName, String assetName, String filePath) throws Throwable {
		String activeView =  CommonFunctions.getNameOfActiveViewAssets();
		
		switch (brandAssetTabName) {
			case "Awaiting Approval" :
				uploadAssetsAndSendToAwaitingApprovalOrPublishedTab(numberRequiredAssets, assetName, filePath, activeView, brandAssetTabName);
				break;
			case "Published" :
				uploadAssetsAndSendToAwaitingApprovalOrPublishedTab(numberRequiredAssets, assetName, filePath, activeView, brandAssetTabName);
				break;
			case "Rejected" :
				uploadAssetsAndChangeStateFromAwaitingApprovalTab(numberRequiredAssets, assetName, filePath, activeView, brandAssetTabName, "Reject");
				break;
			case "Archived" :
				uploadAssetsAndChangeStateFromPublishedTab(numberRequiredAssets, assetName, filePath, activeView, brandAssetTabName, "Archive"); 
				break;
			case "Expired" :
				uploadAssetsAndChangeStateFromPublishedTab(numberRequiredAssets, assetName, filePath, activeView, brandAssetTabName, "Expire"); 
				break;
		}
	}

	private void uploadAssetsAndChangeStateFromAwaitingApprovalTab(int numberRequiredAssets, String prefixName, 
											                       String filePath, String activeView, String brandAssetTabName, String actionName) throws Throwable {
		CommonCode commonCode = new CommonCode();
		this.uploadAssetsAndSendToAwaitingApprovalOrPublishedTab(numberRequiredAssets, prefixName, filePath, activeView, "Awaiting Approval");
		
		if (prefixName.equals("brand.asset.search")) {
			Navigate_to(prefixName);
			the_item(actionName);
		} else { //for asset names : brand.asset1,brand.asset2,brand.asset3,........
			for (int numberAssets = 0 ; numberAssets < createdAssetNamesList.size(); numberAssets ++) {
				Navigate_to(createdAssetNamesList.get(numberAssets));
				the_item(actionName);
			}
		}
		
		Go_to_tab(brandAssetTabName);
		commonCode.Select_the_view(activeView);
		initializeAssetsCreation();
	}

	private void uploadAssetsAndChangeStateFromPublishedTab(int numberRequiredAssets, String prefixName, 
			                                                String filePath, String activeView, String brandAssetTabName, String actionName) throws Throwable {
		CommonCode commonCode = new CommonCode();
		this.uploadAssetsAndSendToAwaitingApprovalOrPublishedTab(numberRequiredAssets, prefixName, filePath, activeView, "Published");
		
		if (prefixName.equals("brand.asset.search")) { 
			Navigate_to(prefixName);
			the_item(actionName);
		} else { //for asset names : brand.asset1,brand.asset2,brand.asset3,........
			for (int numberAssets = 0 ; numberAssets < createdAssetNamesList.size(); numberAssets ++) {
				Navigate_to(createdAssetNamesList.get(numberAssets));
				the_item(actionName);
			}
		}

		Go_to_tab(brandAssetTabName);
		commonCode.Select_the_view(activeView);
		initializeAssetsCreation();		
	}

	private void uploadAssetsAndSendToAwaitingApprovalOrPublishedTab(int numberRequiredAssets, String prefixName, 
																     String filePath, String activeView, String brandAssetTabName) throws Throwable {
		Assets asset = new Assets();
		CommonCode commonCode = new CommonCode();
		String assetName;
		int id = totalCreatedAssetsWithPrefix + 1;
		for (int number = 1; number <= numberRequiredAssets; number++) { 
			asset.Click_on_upload_assets_button();
			VerificationFunctions.verifyText(LocatorType.id, "popupTitle", "textContent","Upload File");
			commonCode.Upload_file(filePath);	
			CommonFunctions.waitForElement(LocatorType.Xpath, "//div[@id='metadataRequired']//ul[@class='gridList']//strong[./text()='" + defaulBrandAssetName + "']", 10);
			
			if (prefixName.equals("brand.asset")) {
				assetName = prefixName + id; 
			} else {
				assetName = prefixName;
			}
			changeAssetNameAndState(defaulBrandAssetName, assetName, brandAssetTabName);
			id++;
		}
		
		Navigation.gotoURL("/admin/brand_admin/files/brand_assets/");
		Go_to_tab(brandAssetTabName);
		commonCode.Select_the_view(activeView);
		initializeAssetsCreation();
	}
	
	private void changeAssetNameAndState(String originAssetName, String newAssetName, String tabName) {
		String operation = "";
		String sectionId = "";
		
		CommonFunctions.click(LocatorType.Xpath, "//ul[@class='gridList']//strong[./text()='" + originAssetName + "']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h2[contains(node(),'Editing')]");
		CommonFunctions.clearAndSendKeys(By.id("item_name"), newAssetName);//change asset name
		
		if (tabName.equals("Awaiting Approval")) {
			operation = "Save & Send for Approval";
			sectionId = "awaitingApproval";
		} else if (tabName.equals("Published")) {
			operation = "Save & Approve";
			sectionId = "approved";
		}
		
		By button = By.cssSelector(".buttons input[value = '" + operation + "']");
		CommonFunctions.waitUntilElementToBeclickable(button);
		CommonFunctions.click(button);
		DriverManager.driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
		DriverManager.driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		CommonFunctions.waitForLoadPage();
		//verify it the asset is displayed in the corresponding section
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div[@id='" + sectionId + "']//strong[./text() = '" + newAssetName + "']");
	}
	/**
	 * Method to initialize the total number of all displayed brand assets that there is after selecting "Items per page 45" option, 
	 * the total number of brand assets created during automation execution (with brand.asset prefix)and add their item names to a list for those(with brand.asset prefix).
	 * 
	 * */
	private void initializeAssetsCreation() {
		By itemNameLocator = CommonFunctions.getItemNameLocatorOfActiveView();
		defaultBrandAssetCount = CommonFunctions.getElementsCount(CommonFunctions.getItemNameLocatorOfActiveView());
		totalDisplayBrandAssets = CommonFunctions.getTotalCreateElements();
		createdAssetNamesList  = CommonFunctions.getListOfItemNamesWithPrefix("brand.asset", itemNameLocator);
		if (!createdAssetNamesList .isEmpty()) {
			totalCreatedAssetsWithPrefix = createdAssetNamesList.size();
		}
	}
	
	
	@Then("^Verify that \"([^\"]*)\" brand assets are displayed$")
	public void Verify_that_brand_assets_are_displayed(int expectedItemPerPage) throws Throwable {
		int itemsCount = CommonFunctions.getElementsCount(CommonFunctions.getItemNameLocatorOfActiveView());
	    Assert.assertTrue(itemsCount == expectedItemPerPage);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h3[contains(text(),'Displaying 1 - " + itemsCount + " of " + totalDisplayBrandAssets + "')]");
	}
	
	@When("^Search brand assets by \"([^\"]*)\"$")
	public void Search_brand_assets_by(String text) throws Throwable {
		lastSearchKeyword = text;
		CommonFunctions.clearAndSendKeys(By.cssSelector("input[id='ajaxSearchInput']"), lastSearchKeyword);
		CommonFunctions.pressKey("Enter");
		CommonFunctions.waitUntilCompleteLoading();
	}
	
	@Then("^Verify that none brand asset is found$")
	public void Verify_that_none_brand_asset_is_found() throws Throwable {
		int itemsCount = CommonFunctions.getElementsCount(CommonFunctions.getItemNameLocatorOfActiveView());
		
		Assert.assertTrue(itemsCount == 0);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div[@id = '" + CommonFunctions.getClassActiveTab() +"']//h3[./text()='Displaying 1 - 0 of 0']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div[@id = '" + CommonFunctions.getClassActiveTab() +"']//p[./text()='No search results found']");
	}
	
	@Then("^Reset brand assets search results$")
	public void Reset_brand_assets_search_results() throws Throwable {
		CommonFunctions.click(LocatorType.Xpath,"//ul[@class= 'actions']//a[./text()= 'Reset']");
		CommonFunctions.waitUntilCompleteLoading();
		
		//count the items displayed 
		int itemsCount = CommonFunctions.getElementsCount(CommonFunctions.getItemNameLocatorOfActiveView());
		Assert.assertEquals("The result after reseting the search is incorrect :" + defaultBrandAssetCount  +  " is different to " + itemsCount,
				            defaultBrandAssetCount, itemsCount);
		
		//Verify the Displayed label
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h3[contains(text(),'Displaying 1 - " + defaultBrandAssetCount + " of " + totalDisplayBrandAssets + "')]");
	}
	
	//Pagination
	@Then("^Verify number of pages is correct on brand assets \"([^\"]*)\" pagination$")
	public void Verify_number_of_pages_is_correct_on_brand_assets_pagination(String pagination) throws Throwable {
		List<WebElement> paginationOptionsList; //list of pagination Options
		selectItemPerPageOption = CommonFunctions.getActiveItemPerPageOption(); //initialize the selectItemPerPageOption
		totalNumberOfPagination = CommonFunctions.calculateNumberOfPagination(selectItemPerPageOption, totalDisplayBrandAssets);

		if (pagination.equals("top")) {
			paginationOptionsList = CommonFunctions.getListOfPaginationOptions("header");
			paginationBarPosition = HEADER;  //initialize the pagination bar position
		} else {
			paginationOptionsList = CommonFunctions.getListOfPaginationOptions("footer");
			paginationBarPosition = FOOTER; //initialize the pagination bar position
		}
		
		List<String> paginationListA =  CommonFunctions.createNumberPaginationOptionList(totalNumberOfPagination);
		int positionA = 0;
		for (int positionB = 1; positionB < paginationOptionsList.size()-1; positionB++) { //verify the number of pagination displayed
			Assert.assertTrue((paginationOptionsList.get(positionB).getText()).equals(paginationListA.get(positionA)));
			positionA++;
		}
	}

	@Then("^Verify brand assets pagination works using number of page$")
	public void Verify_brand_assets_pagination_works_using_number_of_page() throws Throwable {
		String numberPaginationLocator;
		int counterItemPerPage;
		int rest = totalDisplayBrandAssets;
		By activePaginationLocator = CommonFunctions.getActivePaginationOptionLocator(paginationBarPosition);
		for (int numberPagination = 1; numberPagination <= totalNumberOfPagination; numberPagination++) {
			numberPaginationLocator = CommonFunctions.getNumberPaginationOptionLocator(numberPagination, paginationBarPosition);
			
			CommonFunctions.clickOnPaginationNumberOption(activePaginationLocator, numberPaginationLocator);
			counterItemPerPage = CommonFunctions.getElementsCount(CommonFunctions.getItemNameLocatorOfActiveView());
			if (numberPagination < totalNumberOfPagination) {
				Assert.assertTrue(counterItemPerPage == selectItemPerPageOption);
				rest = rest - selectItemPerPageOption;
			} else {
				Assert.assertTrue(counterItemPerPage == rest);
			}
		}
	}

	@Then("^Verify \"([^\"]*)\" icon is not displayed on brand assets$")
	public void Verify_icon_is_not_displayed_on_brand_assets(String iconName) throws Throwable {
		String numberPaginationLocator;
		By iconLocator;
		By activePaginationLocator = CommonFunctions.getActivePaginationOptionLocator(paginationBarPosition);
		if (iconName.equals("previous")) {
			numberPaginationLocator = CommonFunctions.getNumberPaginationOptionLocator(1, paginationBarPosition);
			iconLocator =  CommonFunctions.getPreviousPaginationOptionLocator(paginationBarPosition);
		} else {
			//to click on last number of pagination
			numberPaginationLocator = CommonFunctions.getNumberPaginationOptionLocator(totalNumberOfPagination, paginationBarPosition);
			iconLocator =  CommonFunctions.getNextPaginationOptionLocator(paginationBarPosition);
		}
		WebElement iconNavigationElem = DriverManager.driver.findElement(iconLocator);
		CommonFunctions.clickOnPaginationNumberOption(activePaginationLocator, numberPaginationLocator);
		
		Assert.assertTrue(iconNavigationElem.getAttribute("class").contains("inactive"));
		Assert.assertTrue(!iconNavigationElem.isDisplayed());

	}

	@Then("^Verify brand assets pagination works using previous$")
	public void Verify_brand_assets_pagination_works_using_previous() throws Throwable {
		int counterItemPerPage;
		By previousLocator = CommonFunctions.getPreviousPaginationOptionLocator(paginationBarPosition);
		WebElement previousElement = DriverManager.driver.findElement(CommonFunctions.getPreviousPaginationOptionLocator(paginationBarPosition));
		By activePaginationLocator = CommonFunctions.getActivePaginationOptionLocator(paginationBarPosition);
		int lastNumberOption = totalNumberOfPagination;
		String endNumberLocator = CommonFunctions.getNumberPaginationOptionLocator(lastNumberOption , paginationBarPosition);
		
		//click on endNumber pagination option
		CommonFunctions.clickOnPaginationNumberOption(activePaginationLocator, endNumberLocator);

		//verify that the previous pagination option is displayed
		Assert.assertTrue(previousElement.isDisplayed());

		for (int numberPagination = totalNumberOfPagination; numberPagination > 1; numberPagination--) {	
			
			CommonFunctions.click(previousLocator);
			CommonFunctions.waitUntilCompleteLoading();
			
			//get the number of brand assets displayed
			counterItemPerPage = CommonFunctions.getElementsCount(CommonFunctions.getItemNameLocatorOfActiveView());

			//Verify the number of brand assets displayed is correct according to selectItePerPageOption
			Assert.assertTrue(counterItemPerPage == selectItemPerPageOption);
		}
	}

	@Then("^Verify brand assets pagination works using next$")
	public void Verify_brand_assets_pagination_works_using_next() throws Throwable {
		int counterItemPerPage;
		int rest = totalDisplayBrandAssets  - selectItemPerPageOption;
		By nextLocator = CommonFunctions.getNextPaginationOptionLocator(paginationBarPosition);
		WebElement nextElement = DriverManager.driver.findElement(nextLocator);
		By activePaginationLocator = CommonFunctions.getActivePaginationOptionLocator(paginationBarPosition);
		String startNumberLocator = CommonFunctions.getNumberPaginationOptionLocator(1 , paginationBarPosition);
		
		//click on startNumber pagination option
		CommonFunctions.clickOnPaginationNumberOption(activePaginationLocator, startNumberLocator);
		
		//verify that the previous pagination option is displayed
		Assert.assertTrue(nextElement.isDisplayed());
		for (int numberPagination = 2; numberPagination <= totalNumberOfPagination; numberPagination++) {				
			CommonFunctions.click(nextLocator);
			CommonFunctions.waitUntilCompleteLoading();
			//get the number of files displayed
			counterItemPerPage = CommonFunctions.getElementsCount(CommonFunctions.getItemNameLocatorOfActiveView());
			
			if (numberPagination < totalNumberOfPagination) {
				//Verify the number of files displayed is correct according to selectItePerPageOption
				Assert.assertTrue(counterItemPerPage == selectItemPerPageOption);
				rest = rest - selectItemPerPageOption;
			} else {
				//Verify the number of files displayed is correct according to selectItePerPageOption
				Assert.assertTrue(counterItemPerPage == rest);
			}
		}
	}
	
	/**
	 * Method to delete brand assets created in the execution of automated scenarios(delete the item that has "brand.asset" as prefix )
	 * @param numberOfImagesToDelete
	 * @param prefixName
	 * */
	private void deleteBrandAssetsWithPrefix(int numberOfBrandAssetsToDelete, String prefixName) throws Throwable {
		if (totalCreatedAssetsWithPrefix > 0) {
			int numberOfDeletedBrandAssets = 0;
			String brandAssetName = "";
			int itemIndex = totalCreatedAssetsWithPrefix - 1;
			while((numberOfBrandAssetsToDelete > numberOfDeletedBrandAssets) && (!createdAssetNamesList.isEmpty())) {
				brandAssetName = createdAssetNamesList.get(itemIndex);				
				if (brandAssetName.startsWith(prefixName)) {
					//delete the created assets in the scenario, starting last item
					Navigate_to(brandAssetName);
					the_item("Delete");					
					createdAssetNamesList.remove(itemIndex);
					this.totalDisplayBrandAssets--;
					this.totalCreatedAssetsWithPrefix--;
					numberOfDeletedBrandAssets ++;
				}
				itemIndex--;
			}
			initializeAssetsCreation();
		}
	}
	
	@Given("^Delete all brand assets$")
	public void delete_all_brand_assets() throws Throwable {
		initializeAssetsCreation();
		deleteBrandAssetsWithPrefix(totalCreatedAssetsWithPrefix, "brand.asset");
	}	
}