package onbrand;

import onbrand.tools.DriverManager;

import org.junit.Assert;
import org.openqa.selenium.By;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class Workflow {

	
	@Then("^Verify Workflow page$")
	public void Verify_Workflow_page() throws Throwable {

		Assert.assertTrue(DriverManager.driver.getTitle().contains("Projects | Workflow"));
		
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Projects");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "My Tasks");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Reports");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "textField");
		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Create Project']");
		
	}
	
	

	@Then("^Verify Create Project objects$")
	public void Verify_Create_Project_objects() throws Throwable {
				
		Assert.assertTrue(DriverManager.driver.getTitle().contains("Create Project | Projects"));
		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Name')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Name')]/em[.='required']");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "item_name");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Description')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Description')]/em[.='required']");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "item_description");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Objectives')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "ITEM_ATT57_0");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Target Audience')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "ITEM_ATT58_0");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Start Date')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Start Date')]/parent::div/input[contains(@id,'prettyDate')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Launch Date')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Launch Date')]/parent::div/input[contains(@id,'prettyDate')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Marketing Activity')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Marketing Activity')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "root-cont");
				
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Create Project']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Add Member(s)']");
		
	}

	
	@When("^Enter Project Missing details$")
	public void Enter_Project_Missing_details(DataTable dataTable) throws Throwable {

		java.util.List<java.util.List<String>> tableData = dataTable.raw();		
		DriverManager.driver.findElement(By.id("item_name")).sendKeys(tableData.get(1).get(1));
		DriverManager.driver.findElement(By.id("item_description")).sendKeys(tableData.get(2).get(1));
				
	}

	@Then("^Verify mandatory error messages for create project page$")
	public void Verify_mandatory_error_messages_for_create_project_page() throws Throwable {
		
		Assert.assertTrue(DriverManager.driver.findElement(By.id("item_name")).getAttribute("class").contains("validationElementError"));
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div[input[@id='item_name']]/div[@class='errorContainerClass']/strong[contains(text(),'field required')]");
		
		Assert.assertTrue(DriverManager.driver.findElement(By.id("item_description")).getAttribute("class").contains("validationElementError"));	
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div[textarea[@id='item_description']]/div[@class='errorContainerClass']/strong[contains(text(),'field required')]");
		
	}

	
	@Then("^Clear all Project fields$")
	public void Clear_all_Project_fields() throws Throwable {
		
		CommonFunctions.clearText(LocatorType.id, "item_name");
		CommonFunctions.clearText(LocatorType.id, "item_description");
		CommonFunctions.clearText(LocatorType.id, "ITEM_ATT57_0");
		CommonFunctions.clearText(LocatorType.id, "ITEM_ATT58_0");
	}
	
	@When("^Click on the button \"([^\"]*)\"$")
	public void Click_on_the_button(String button) throws Throwable {

		CommonFunctions.click(LocatorType.Xpath, "//input[@value='"+button+"']");
	}	
	
	@When("^Enter Project details$")
	public void Enter_Project_details(DataTable dataTable) throws Throwable {

		VerificationFunctions.verifyURL("/projects/create/");
		
		java.util.List<java.util.List<String>> tableData = dataTable.raw();		
		DriverManager.driver.findElement(By.id("item_name")).sendKeys(tableData.get(1).get(1));
		DriverManager.driver.findElement(By.id("item_description")).sendKeys(tableData.get(2).get(1));			
		DriverManager.driver.findElement(By.id("ITEM_ATT57_0")).sendKeys(tableData.get(3).get(1));				
		DriverManager.driver.findElement(By.id("ITEM_ATT58_0")).sendKeys(tableData.get(4).get(1));				
	}
	

	
	@When("^Verify artwork page$")
	public void Verify_artwork_page() throws Throwable {
		
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Artwork");
		CommonFunctions.click(LocatorType.LinkedText, "Artwork");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "stage_files");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "stage_fulfilment");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Request File(s)']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Upload File(s)']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Skip to Fulfilment']");		
	}

	@When("^Verify discussion page$")
	public void Verify_discussion_page() throws Throwable {
		
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Discussions");
		CommonFunctions.click(LocatorType.LinkedText, "Discussions");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Start Discussion']");
		VerificationFunctions.verifyText(LocatorType.className, "blankState", "textContent","No Discussions");		
	}

	@When("^Verify tasks page$")
	public void Verify_tasks_page() throws Throwable {
		
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Tasks");
		CommonFunctions.click(LocatorType.LinkedText, "Tasks");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Create Task']");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "taskListSwitcher");

	}

	@When("^Verify supporting files page$")
	public void Verify_supporting_files_page() throws Throwable {
		
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Supporting Files");
		CommonFunctions.click(LocatorType.LinkedText, "Supporting Files");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Link Assets']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Upload Attachments']");
		VerificationFunctions.verifyText(LocatorType.className, "blankState","textContent", "No Supporting Files");
	}

	@When("^Verify activity and members page$")
	public void Verify_activity_and_members_page() throws Throwable {
		
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Activity & Members");
		CommonFunctions.click(LocatorType.LinkedText, "Activity & Members");
		VerificationFunctions.verifyElementVisible(LocatorType.className, "projectDetails");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "add");
	}
	
	@When("^Navigate to the Workflow Project \"([^\"]*)\"$")
	public void Navigate_to_the_Workflow_Project(String prjname) throws Throwable {
	     
		//TestHooks.driver.findElement(By.linkText("Workflow")).click();
		CommonFunctions.click(LocatorType.LinkedText,  "Workflow");
		
		CommonFunctions.click(LocatorType.LinkedText, "Projects");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//H4[contains(text(),'All')]");
		CommonFunctions.click(LocatorType.Xpath, "//H4[contains(text(),'All')]");
		
		CommonFunctions.clearAndSendKeys(By.id("textField"), prjname);
		CommonFunctions.pressKey("Enter");
		CommonFunctions.sleep(2000);
		
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, prjname);
		CommonFunctions.click(LocatorType.LinkedText, prjname);
		
	}
	
		
	@When("^Click on the project edit link$")
	public void Click_on_the_project_edit_link() throws Throwable {

		CommonFunctions.click(LocatorType.LinkedText, "edit");
	}
	
	
	@Then("^Verify Add member popup$")
	public void Verify_Add_member_popup() throws Throwable {
		VerificationFunctions.verifyElementVisible(LocatorType.className, "popupDialog");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//H2[.='Add Members']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[.='Delegate...']");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "assign");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Add']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Cancel']");

	}

	@Then("^Add project member \"([^\"]*)\"$")
	public void Add_project_member(String member) throws Throwable {

		CommonFunctions.clearAndSendKeys(By.id("assign"), member);
		CommonFunctions.click(LocatorType.Xpath, "//a[.='"+member+"']");
		//CommonFunctions.click(LocatorType.LinkedText, member);
		CommonFunctions.click(LocatorType.Xpath, "//input[@type='button' and @value='Add']");
		
	}

	@Then("^Verify project members \"([^\"]*)\" \"([^\"]*)\"$")
	public void Verify_project_members(String prjowner, String prjmember) throws Throwable {
		//Navigation.refreshPage();	
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, prjowner);
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "change");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, prjmember);
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "remove");
	
	}

	@Then("^Remove project member \"([^\"]*)\"$")
	public void Remove_project_member(String member) throws Throwable {
		
		CommonFunctions.click(LocatorType.Xpath, "//li[h4/a[.='"+member+"']]/em/a[.='remove']");
	}
	

	@Then("^Verify no project members \"([^\"]*)\"$")
	public void Verify_no_project_members(String prjmember) throws Throwable {

		VerificationFunctions.verifyElementNotVisible(LocatorType.LinkedText, prjmember);
		
	}

	
	@Then("^Verify Project details$")
	public void Verify_Project_details(DataTable dataTable) throws Throwable {
		java.util.List<java.util.List<String>> tableData = dataTable.raw();		

		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@id='item_name' and @value='"+tableData.get(1).get(1)+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//textarea[@id='item_description' and contains(text(),'"+tableData.get(2).get(1)+"')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//textarea[@id='ITEM_ATT57_0' and contains(text(),'"+tableData.get(3).get(1)+"')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//textarea[@id='ITEM_ATT58_0' and contains(text(),'"+tableData.get(4).get(1)+"')]");
					
	}
	
	
	@When("^Navigate to creative page of \"([^\"]*)\" and \"([^\"]*)\"$")
	public void Navigate_to_creative_page_of_and(String prjname, String file) throws Throwable {
		
		//TestHooks.driver.findElement(By.linkText("Workflow")).click();
		CommonFunctions.click(LocatorType.LinkedText,  "Workflow");
		
		CommonFunctions.click(LocatorType.LinkedText, "Projects");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//H4[contains(text(),'All')]");
		CommonFunctions.click(LocatorType.Xpath, "//H4[contains(text(),'All')]");
		
		CommonFunctions.clearAndSendKeys(By.id("textField"), prjname);
		CommonFunctions.pressKey("Enter");
		CommonFunctions.sleep(2000);
		
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, prjname);
		CommonFunctions.click(LocatorType.LinkedText, prjname);
		
		VerificationFunctions.verifyText(LocatorType.Xpath, "//*[@title='"+file+"']/em/span[contains(@class,'capsule')]","textContent", "Reviewing");
		CommonFunctions.click(LocatorType.Xpath, "//li[em/span[contains(text(),'Reviewing')]]/a/div/img[@title='"+file+"']");
			
		//CommonFunctions.click(LocatorType.Xpath, "//img[@title='"+file+"']");		
		
		VerificationFunctions.verifyPageTitle("Creative "+file);
	}
	


	@Then("^Verify Creative page with members$")
	public void Verify_Creative_page_with_members(DataTable dataTable) throws Throwable {

		java.util.List<java.util.List<String>> tableData = dataTable.raw();	
		
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//input[@type='button' and @value='Add Annotation']");
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//input[@type='button' and @value='Add Comment']");
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//input[@type='button' and @value='Share']");
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//input[@type='button' and @value='Download']");
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//input[@type='button' and @value='Request Revision']");
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//input[@type='button' and @value='Upload Revision']");
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//input[@type='button' and @value='Reject']");
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//input[@type='button' and @value='Approve']");		
		VerificationFunctions.verifyElementsVisible(LocatorType.id, "versionSwitcher");
	
		for (int rowCount = 0; rowCount < tableData.size(); rowCount++) {
				VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, tableData.get(rowCount).get(0));
		}
				
	}
		
		
	@Then("^Add Annotations \"([^\"]*)\"$")
	public void Add_Annotations(String annotation) throws Throwable {
		
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//input[@type='button' and @value='Add Annotation']");
		CommonFunctions.click(LocatorType.Xpath,"//input[@value='Add Annotation']");
		
		//Verify Annotation Window
		Navigation.selectWindowPopup();
		CommonFunctions.click(LocatorType.TagName, "img");
		VerificationFunctions.verifyElementVisible(LocatorType.className, "border");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Delete");
		VerificationFunctions.verifyElementVisible(LocatorType.TagName, "textarea");
		CommonFunctions.clearAndSendKeys(By.tagName("textarea"), annotation);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Add Note']");
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Add Note']");
		
		//Verify Annotation and Save
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Save Changes']");
		VerificationFunctions.verifyElementVisible(LocatorType.className, "note");
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Save Changes']");
		
	}

	
	@Then("^Verify Annotations$")
	public void Verify_Annotations() throws Throwable {
			// This has to be written once issue is fixed. Currently error dialog when adding annotations
		
	}
	
	
	@Then("^Add and Verify Comments \"([^\"]*)\"$")
	public void Add_and_Verify_Comments(String comment) throws Throwable {
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//input[@type='button' and @value='Add Comment']");
		CommonFunctions.click(LocatorType.Xpath,"//input[@value='Add Comment']");
		
		//Verify Comment Popup 
		VerificationFunctions.verifyText(LocatorType.className, "popupDialog","textContent", "Add a Comment");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "comment");
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//input[@type='button' and @value='Add']");
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//input[@type='button' and @value='Cancel']");
		
		//Verify Mandatory Field
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Add']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//textarea[@id='comment' and @class='valid_mandatory validationElementError']");

		//Enter Comment and Verify
		CommonFunctions.clearAndSendKeys(By.id("comment"), comment);
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Add']");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Activity & Members");
		CommonFunctions.click(LocatorType.Xpath, "//a[.='Activity & Members']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//p[contains(text(),'"+comment+"')]");
		
	}

	
	@Then("^Share creative asset with \"([^\"]*)\"$")
	public void Share_creative_asset_with(String email) throws Throwable {
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//input[@type='button' and @value='Share']");
		CommonFunctions.click(LocatorType.Xpath,"//input[@value='Share']");
		
		//Verify Share popup
		VerificationFunctions.verifyText(LocatorType.className, "popupDialog","textContent", "Share Creative");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "userSelected");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "customMessage");
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//input[@value='Send Invites']");
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//input[@value='Cancel']");

		//Enter User email and send invite
		CommonFunctions.clearAndSendKeys(By.id("userSelected"), email);
		CommonFunctions.click(LocatorType.Xpath,  "//input[@value='Send Invites']");
				
		//This is to verify that the user is selected and shared and no 'No user selected' pop up is displayed
		VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath, "//*[@class='popupDialog' and contains(h2,'No User Selected')]");
	}

	
	@Then("^Download creative asset$")
	public void Download_creative_asset() throws Throwable {
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//input[@type='button' and @value='Download']");
		CommonFunctions.click(LocatorType.Xpath,"//input[@value='Download']");
		
	}


	@Then("^Approve creative asset and verify approved \"([^\"]*)\"$")
	public void Approve_creative_asset_and_verify_approved(String file) throws Throwable {	
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Approve']");
		CommonFunctions.click(LocatorType.Xpath,"//input[@value='Approve']");
		
		//Verify Approval Popup
		VerificationFunctions.verifyText(LocatorType.className, "popupDialog","textContent", "Are you sure?");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Cancel']");
		VerificationFunctions.verifyElementVisible(LocatorType.cssSelector, "div.popupDialog input[value=Approve]");
		CommonFunctions.click(LocatorType.cssSelector, "div.popupDialog input[value=Approve]");
		CommonFunctions.click(LocatorType.LinkedText, "Artwork");
		//VerificationFunctions.verifyText(LocatorType.Xpath, "//span[contains(@class,'capsule')]","textContent", "Approved");
		VerificationFunctions.verifyText(LocatorType.Xpath, "//*[@title='"+file+"']/em/span[contains(@class,'capsule')]","textContent", "Approved");
		
		
	}

	@Then("^Reject creative asset and verify rejected \"([^\"]*)\"$")
	public void Reject_creative_asset_and_verify_rejected(String file) throws Throwable {
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Reject']");
		CommonFunctions.click(LocatorType.Xpath,"//input[@value='Reject']");
		
		//Verify Approval Popup
		VerificationFunctions.verifyText(LocatorType.className, "popupDialog","textContent", "Are you sure?");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Cancel']");
		VerificationFunctions.verifyElementVisible(LocatorType.cssSelector, "div.popupDialog input[value=Reject]");
		CommonFunctions.click(LocatorType.cssSelector, "div.popupDialog input[value=Reject]");
		CommonFunctions.click(LocatorType.LinkedText, "Artwork");
		//VerificationFunctions.verifyText(LocatorType.Xpath, "//span[contains(@class,'capsule')]","textContent", "Rejected");
		VerificationFunctions.verifyText(LocatorType.Xpath, "//*[@title='"+file+"']/em/span[contains(@class,'capsule')]","textContent", "Rejected");
		
	}
	
	@Then("^Request revision from \"([^\"]*)\"$")
	public void Request_revision_from(String delegate) throws Throwable {
		VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//input[@type='button' and @value='Request Revision']");
		CommonFunctions.click(LocatorType.Xpath,"//input[@value='Request Revision']");
		
		//Verify Popup and Share
		VerificationFunctions.verifyText(LocatorType.className, "popupDialog","textContent", "Request Revision");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "assign");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Request']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Cancel']");
		
		//Enter User email and send invite
		CommonFunctions.clearAndSendKeys(By.id("assign"), delegate);
		CommonFunctions.click(LocatorType.Xpath, "//li/a[.='"+delegate+"'][contains(@class,'profileId')]");
		//CommonFunctions.click(LocatorType.Xpath, "//a[.='"+delegate+"']");
		CommonFunctions.click(LocatorType.Xpath,  "//input[@value='Request']");
		
	}
	
	
	@Then("^Upload revision \"([^\"]*)\"$")
	public void Upload_revision(String file) throws Throwable {
		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Upload Revision']");
		CommonFunctions.click(LocatorType.Xpath,"//input[@value='Upload Revision']");
				
		//Verify Popup and Upload
		
		VerificationFunctions.verifyText(LocatorType.id, "popupTitle","textContent", "Upload Revision");
		Navigation.switchToFrame("popupIframe");
		CommonFunctions.browseFile(By.id("item_file"), file);
		CommonFunctions.browserWait(5L);		
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Upload']");
		
		//Verify Creative Uploaded 
		Navigation.switchtoDefaultFrame();
		CommonFunctions.sleep(1000);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[@class='popupDialog' and contains(h2,'Creative Uploaded')]");
		CommonFunctions.click(LocatorType.Xpath, "//*[@class='popupDialog']/div/input[@value='OK']");
				
	}
	
	@Then("^Verify latest revision uploaded$")
	public void Verify_latest_revision_uploaded() throws Throwable {
		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//option[contains(node(),'latest')]");		
	}
	
	
	@Then("^Request file from \"([^\"]*)\"$")
	public void Request_file_from(String delegate) throws Throwable {

		//Verify Popup and Share
		VerificationFunctions.verifyText(LocatorType.className, "popupDialog","textContent", "Request Files");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "assign");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Request']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Cancel']");
		
		//Enter User email and send invite
		CommonFunctions.clearAndSendKeys(By.id("assign"), delegate);
		CommonFunctions.click(LocatorType.Xpath, "//li/a[.='"+delegate+"'][contains(@class,'profileId')]");
		//CommonFunctions.click(LocatorType.Xpath, "//a[.='"+delegate+"']");
		CommonFunctions.click(LocatorType.Xpath,  "//input[@value='Request']");
		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Cancel Request']");
		
	}
	
	@Then("^Verify request file cancelled$")
	public void Verify_request_file_cancelled() throws Throwable {
	
		//Verify Popup and Share
		VerificationFunctions.verifyText(LocatorType.className, "popupDialog","textContent", "Are you sure?");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Cancel']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Cancel Request']");
		
		CommonFunctions.click(LocatorType.Xpath,  "//input[@class='button primary' and @value='Cancel Request']");
		
		VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath, "//input[@type='button' and @value='Cancel Request']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Request File(s)']");
	}
	
	
	@Then("^Verify promoted to fulfilment$")
	public void Verify_promoted_to_fulfilment() throws Throwable {

		//Verify Popup and Promote
		VerificationFunctions.verifyText(LocatorType.className, "popupDialog","textContent", "Are you sure?");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Cancel']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Promote']");
		
		CommonFunctions.click(LocatorType.Xpath,  "//input[@value='Promote']");
		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[@id='stage_files' and @class='completeStage']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[@id='stage_fulfilment' and @class='current active last']");
		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Request File(s)']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Upload File(s)']");
		VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath, "//input[@type='button' and @value='Promote to Fulfilment']");	
	
	}
	
	@When("^Create a workflow project$")
	public void Create_a_workflow_project(DataTable dataTable) throws Throwable {

		//TestHooks.driver.findElement(By.linkText("Workflow")).click();
		CommonFunctions.click(LocatorType.LinkedText,  "Workflow");
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Create Project']");
		
		VerificationFunctions.verifyURL("/projects/create/");
		
		java.util.List<java.util.List<String>> tableData = dataTable.raw();		
		DriverManager.driver.findElement(By.id("item_name")).sendKeys(tableData.get(1).get(1));
		DriverManager.driver.findElement(By.id("item_description")).sendKeys(tableData.get(2).get(1));			
		DriverManager.driver.findElement(By.id("ITEM_ATT57_0")).sendKeys(tableData.get(3).get(1));				
		DriverManager.driver.findElement(By.id("ITEM_ATT58_0")).sendKeys(tableData.get(4).get(1));
		
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Create Project']");
		
		CommonFunctions.sleep(5000);
	}
	
	
	@Then("^Verify skipped to fulfilment$")
	public void Verify_skipped_to_fulfilment() throws Throwable {

		//Verify Popup and Promote
		VerificationFunctions.verifyText(LocatorType.className, "popupDialog","textContent", "Are you sure?");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Cancel']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Skip']");
		
		CommonFunctions.click(LocatorType.Xpath,  "//input[@value='Skip']");
		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[@id='stage_files' and @class='completeStage']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[@id='stage_fulfilment' and @class='current active last']");
		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Request File(s)']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Upload File(s)']");
		VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath, "//input[@type='button' and @value='Skip to Fulfilment']");	
	
	}
	
	@Then("^Request fulfilment from \"([^\"]*)\"$")
	public void Request_fulfilment_from(String delegate) throws Throwable {

		//Verify Popup and Share
		VerificationFunctions.verifyText(LocatorType.className, "popupDialog","textContent", "Request Fulfilment");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "assign");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Request']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Cancel']");
		
		//Enter User email and send invite
		CommonFunctions.clearAndSendKeys(By.id("assign"), delegate);
		CommonFunctions.click(LocatorType.Xpath, "//li/a[.='"+delegate+"'][contains(@class,'profileId')]");
		//CommonFunctions.click(LocatorType.Xpath, "//a[.='"+delegate+"']");
		CommonFunctions.click(LocatorType.Xpath,  "//input[@value='Request']");
		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Cancel Request']");
		
	}
	
	@Then("^Verify file \"([^\"]*)\" uploaded is in \"([^\"]*)\" stage$")
	public void Verify_file_uploaded_is_in_stage(String file, String status) throws Throwable {
	
		CommonFunctions.sleep(5000);
		VerificationFunctions.verifyText(LocatorType.Xpath, "//*[@title='"+file+"']/em/span[contains(@class,'capsule')]","textContent", status);

	}
	
	@When("^Navigate to enrichment page of \"([^\"]*)\" and \"([^\"]*)\"$")
	public void Navigate_to_enrichment_page_of_and(String prjname, String file) throws Throwable {
		
		CommonFunctions.sleep(1000);
		//TestHooks.driver.findElement(By.linkText("Workflow")).click();
		CommonFunctions.click(LocatorType.LinkedText,  "Workflow");
		
		CommonFunctions.click(LocatorType.LinkedText, "Projects");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//H4[contains(text(),'All')]");
		CommonFunctions.click(LocatorType.Xpath, "//H4[contains(text(),'All')]");
		
		CommonFunctions.clearAndSendKeys(By.id("textField"), prjname);
		CommonFunctions.pressKey("Enter");
		CommonFunctions.sleep(2000);		
				
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, prjname);
		CommonFunctions.click(LocatorType.LinkedText, prjname);
		
		//Verify the project is on fulfilment page
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[@id='stage_files' and @class='completeStage']");
		
		//VerificationFunctions.verifyText(LocatorType.Xpath, "//*[@title='"+file+"']/em/span[contains(@class,'capsule')]","textContent", "Reviewing");
		VerificationFunctions.verifyText(LocatorType.Xpath, "//li[a/div/img[@title='"+file+"']]/em/span[contains(@class,'capsule')]","textContent", "Reviewing");
	
		CommonFunctions.click(LocatorType.Xpath, "//li[em/span[contains(text(),'Reviewing')]]/a/div/img[@title='"+file+"']");
			
		//CommonFunctions.click(LocatorType.Xpath, "//img[@title='"+file+"']");		
		
		VerificationFunctions.verifyPageTitle("Creative Enrichment");
	}
	
	
	@When("^Verify enrichment page objects$")
	public void Verify_enrichment_page_objects() throws Throwable {
	    
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Name')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Name')]/em[.='required']");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "item_name");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Keywords')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "item_keywords");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Client')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "ITEM_ATT22_0");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Taxonomy')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h3[contains(text(),'Usage Rights')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Restrictions Type')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//select[@id='ITEM_ATT31_0']/option[contains(text(),'None')]|.//select[@id='ITEM_ATT31_0']/option[contains(text(),'Limited')]|.//select[@id='ITEM_ATT31_0']/option[contains(text(),'Unlimited')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@id='hasExpiryDate' and @type='checkbox']|.//Label[contains(text(),'Has expiry date')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Save & Send for Approval' and @type='button']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Save' and @type='button']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Cancel without saving' and @type='button']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Save' and @type='button']");
		
				
	}

	@Then("^Clear enrichment fields$")
	public void Clear_enrichment_fields() throws Throwable {
		CommonFunctions.clearText(LocatorType.id, "item_name");
		CommonFunctions.clearText(LocatorType.id, "item_keywords");
		CommonFunctions.clearText(LocatorType.id, "ITEM_ATT22_0");
	}

	@Then("^Verify enrichment page mandatory fields$")
	public void Verify_enrichment_page_mandatory_fields() throws Throwable {
		Assert.assertTrue(DriverManager.driver.findElement(By.id("item_name")).getAttribute("class").contains("validationElementError"));
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div[input[@id='item_name']]/div[@class='errorContainerClass']/strong[contains(text(),'field required')]");
				
	}

	

	@When("^Enter enrichment page details$")
	public void Enter_enrichment_page_details(DataTable dataTable) throws Throwable {
					
		java.util.List<java.util.List<String>> tableData = dataTable.raw();		
		DriverManager.driver.findElement(By.id("item_name")).sendKeys(tableData.get(1).get(1));
		DriverManager.driver.findElement(By.id("item_keywords")).sendKeys(tableData.get(2).get(1));			
		DriverManager.driver.findElement(By.id("ITEM_ATT22_0")).sendKeys(tableData.get(3).get(1));				
		
		CommonFunctions.selectDropList("//select[@id='ITEM_ATT31_0']",tableData.get(4).get(1));
		
		if(tableData.get(4).get(1).equals("None")) {
			
			CommonFunctions.selectDropList("//select[@id='ITEM_ATT31_0']","None");
		}
		
		if(tableData.get(4).get(1).equals("Limited")){
				
			CommonFunctions.selectDropList("//select[@id='ITEM_ATT31_0']","Limited");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//textarea[@id='ITEM_ATT21_0']");
			CommonFunctions.clearAndSendKeys(By.xpath("//textarea[@id='ITEM_ATT21_0']"), "");
			CommonFunctions.click(LocatorType.LinkedText, "Save");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@class='errorContainerClass']/strong[contains(text(),'A condition is required')]");
			CommonFunctions.clearAndSendKeys(By.xpath("//textarea[@id='ITEM_ATT21_0']"), tableData.get(5).get(1));	
		}
		
		if(tableData.get(4).get(1).equals("Unlimited")){
		
			CommonFunctions.selectDropList("//select[@id='ITEM_ATT31_0']","Unlimited");	
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//textarea[@id='ITEM_ATT21_0']");
			CommonFunctions.clearAndSendKeys(By.xpath("//textarea[@id='ITEM_ATT21_0']"), tableData.get(5).get(1));	
		}
	}
	
	@Then("^Verify enrichment page details$")
	public void Verify_enrichment_page_details(DataTable dataTable) throws Throwable {
					
		CommonFunctions.sleep(1000);
		
		java.util.List<java.util.List<String>> tableData = dataTable.raw();		
		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@id='item_name' and @value='"+tableData.get(1).get(1)+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@id='item_keywords' and @value='"+tableData.get(2).get(1)+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@id='ITEM_ATT22_0' and @value='"+tableData.get(3).get(1)+"']");
		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//select[@id='ITEM_ATT31_0']/option[contains(text(),'"+tableData.get(4).get(1)+"') and @selected='true']");
					
	}

	@Then("^Verify the enrichment file name is not saved \"([^\"]*)\"$")
	public void Verify_the_enrichment_file_name_is_not_saved(String file) throws Throwable {

		CommonFunctions.sleep(1000);
		//Verify the project is on fulfilment page
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[@id='stage_files' and @class='completeStage']");
				
		//Verify the new file name doesnt exist
		VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath, "//li[em/span[contains(text(),'Reviewing')]]/a/div/img[@title='"+file+"']");
		
	}

	
	@Then("^Verify the enrichment file is under pending approval \"([^\"]*)\"$")
	public void Verify_the_enrichment_file_is_under_pending_approval(String file) throws Throwable {
		
		CommonFunctions.sleep(1000);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//li[em/span[contains(text(),'Pending Approval')]]/div/img[@title='"+file+"']");
		
	}
	
		
	
	@Then("^Verify project is in completed stage$")
	public void Verify_project_is_in_completed_stage() throws Throwable {
		CommonFunctions.sleep(1000);
		
		//Verify the fulfilment page is in complete stage
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[@id='stage_fulfilment' and contains(@class,'completeStage')]");
								
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//p[@class='note ' and contains(text(),'Completed')]");
		
	}
	
	@Then("^Start a discussion and verify \"([^\"]*)\"$")
	public void Start_a_discussion_and_verify(String discussion) throws Throwable {

		CommonFunctions.click(LocatorType.Xpath, "//input[@type='button' and @value='Start Discussion']");
		CommonFunctions.sleep(1000);
		
		//Verify pop up
		VerificationFunctions.verifyText(LocatorType.className, "popupDialog", "textContent","Start Discussion");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "title");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Cancel']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Start']");
		
		//Verify Cancel button
		CommonFunctions.clearAndSendKeys(By.id("title"), discussion);
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Cancel']");
		VerificationFunctions.verifyElementNotVisible(LocatorType.className, "popupDialog");
		
		CommonFunctions.click(LocatorType.Xpath, "//input[@type='button' and @value='Start Discussion']");
		CommonFunctions.sleep(1000);
		CommonFunctions.clearAndSendKeys(By.id("title"), discussion);
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Start']");
		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[@class='discussion' and contains(h1, '"+discussion+"')]");
		VerificationFunctions.verifyElementVisible(LocatorType.className, "add");
		VerificationFunctions.verifyElementVisible(LocatorType.className, "comment");
		
		//Verify the discussion is recorded under Activities & Members
		CommonFunctions.click(LocatorType.LinkedText, "Activity & Members");		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//p[contains(text(),'Discussion Started:')]/a[.='"+discussion+"']");
	}
	
	@Then("^Leave a discussion comment \"([^\"]*)\"$")
	public void Leave_a_discussion_comment(String comment) throws Throwable {
		
		CommonFunctions.click(LocatorType.LinkedText, "Discussions");	
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div/textarea[contains(text(),'Leave a message')]");
		CommonFunctions.click(LocatorType.Xpath, "//div/textarea[contains(text(),'Leave a message')]");
					
		//CommonFunctions.clearAndSendKeys(By.xpath("//div/textarea[contains(text(),'Leave a message')]"), comment);
		CommonFunctions.clearAndSendKeys(By.tagName("textarea"), comment);
	
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Send']");
		CommonFunctions.sleep(1000);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//p[.='"+comment+"']");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Reply");
	}	
	
	
	@Then("^Create a task \"([^\"]*)\" and delegate$")
	public void Create_a_task_and_delegate(String task) throws Throwable {
		
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Create Task']");

		//Verify Create Task Popup and Create
		VerificationFunctions.verifyText(LocatorType.className, "popupDialog", "textContent","Add Task");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "title");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[contains(@class,'dueDateContainer datepickerContainer')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[.='Delegate...']");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "delegate");

		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Cancel']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Add']");
		CommonFunctions.clearAndSendKeys(By.id("title"), task);		
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Add']");
		
		//Verify delegate pop up
		VerificationFunctions.verifyText(LocatorType.className, "popupDialog", "textContent","Delegate Task");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Cancel']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Myself']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Leave Unassigned']");
		
		//Verify task is created
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Myself']");
		CommonFunctions.sleep(5000);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//p[text()='"+task+"']");
	
		//Verify the activity is recorded under Activities & Members
		CommonFunctions.click(LocatorType.LinkedText, "Activity & Members");		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//p[contains(text(),'Task Created:')]/a[.='"+task+"']");
		
	}
	
	@Then("^Create a task \"([^\"]*)\" and leave unassigned$")
	public void Create_a_task_and_leave_unassigned(String task) throws Throwable {
	    
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Create Task']");

		//Verify Create Task Popup and Create
		VerificationFunctions.verifyText(LocatorType.className, "popupDialog", "textContent","Add Task");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "title");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//*[contains(@class,'dueDateContainer datepickerContainer')]");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[.='Delegate...']");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "delegate");

		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Cancel']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Add']");
		CommonFunctions.clearAndSendKeys(By.id("title"), task);
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Add']");
		
		//Verify delegate pop up
		VerificationFunctions.verifyText(LocatorType.className, "popupDialog", "textContent","Delegate Task");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Cancel']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Myself']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='Leave Unassigned']");
		
		//Verify task is created
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Leave Unassigned']");
		CommonFunctions.sleep(5000);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//p[text()='"+task+"']");		
		
		CommonFunctions.click(LocatorType.LinkedText, "Activity & Members");
		//CommonFunctions.click(LocatorType.Xpath, "//a[.='Activity & Members']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//p[contains(text(),'Task Created:')]/a[.='"+task+"']");
	}
	
	@Then("^Upload project supporting file \"([^\"]*)\"$")
	public void Upload_project_supporting_file(String file) throws Throwable {

		CommonFunctions.click(LocatorType.Xpath, "//input[@type='button' and @value='Upload Attachments']");
		VerificationFunctions.verifyText(LocatorType.id, "popupTitle", "textContent","Upload File");
		Navigation.switchToFrame("popupIframe");
		CommonFunctions.browseFile(By.xpath("//input[@type='file']"), file);
		CommonFunctions.browserWait(10L);		
		CommonFunctions.click(LocatorType.Xpath, "//input[contains(@class,'green') and @value='Add']");
		CommonFunctions.browserWait(10L);		
		
		Navigation.switchtoDefaultFrame();
		CommonFunctions.sleep(5000);
		
		
	}
	
	@Then("^Link an asset to the project \"([^\"]*)\"$")
	public void Link_an_asset_to_the_project(String asset) throws Throwable {

		//Verify Link Assets Popup
		CommonFunctions.click(LocatorType.cssSelector, "input[value='Link Assets']");
		VerificationFunctions.verifyText(LocatorType.className, "titleBar", "textContent","Add Assets to Project");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "popupTitle");
		//VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "close");
		Navigation.switchToFrame("popupIframe");
		VerificationFunctions.verifyElementVisible(LocatorType.className, "selectDeselect");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "textField");
		VerificationFunctions.verifyElementVisible(LocatorType.className, "submit");
	
		//Search for Link Asset and add to Project
		CommonFunctions.clearAndSendKeys(By.id("textField"), asset);
		CommonFunctions.pressKey("Enter");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, asset);
		CommonFunctions.click(LocatorType.LinkedText, asset);
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Add to project");
		CommonFunctions.click(LocatorType.LinkedText,"Add to project");
		Navigation.switchtoDefaultFrame();
		//VerificationFunctions.verifyText(LocatorType.className, "popupDialog", "textContent","Added 1 Asset");
		//VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//div[@class='popupDialog']//h2['Added 1 Asset']");
		//VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@type='button' and @value='OK']");
		//CommonFunctions.click(LocatorType.Xpath, "//input[@type='button' and @value='OK']");
		//Navigation.switchtoDefaultFrame();
		
		CommonFunctions.sleep(5000);
		
	}

	@Then("^Verify supporting file \"([^\"]*)\" and linked asset \"([^\"]*)\" are added to project$")
	public void Verify_supporting_file_and_linked_asset_are_added_to_project(String file, String asset) throws Throwable {
		
		Navigation.refreshPage();
		VerificationFunctions.verifyText(LocatorType.Xpath, "//li[a/h3/strong[contains(text(),'"+file+"')]]/em/span[contains(@class,'capsule')]","textContent", "attachment");
		VerificationFunctions.verifyText(LocatorType.Xpath, "//li[a/h3/strong[contains(text(),'"+asset+"')]]/em/span[contains(@class,'capsule')]","textContent", "asset");
		VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Download Now");
				
			
	}
	
}


