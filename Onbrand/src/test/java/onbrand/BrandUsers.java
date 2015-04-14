package onbrand;

import onbrand.tools.DriverManager;

import org.junit.Assert;
import org.openqa.selenium.By;

import cucumber.api.DataTable;
import cucumber.api.java.en.*;

public class BrandUsers {
	/**
	 * Verify all items on User Profile
	 * @param tabName
	 */
	@Then("Verify user components \"([^\"]*)\"$")
	public void Verify_user_components(String tabName){
		
		verifyUserProfile();
		
		switch (tabName) {
		case "Pending approval":
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//div[@id='overlay']//a[./text()='Cancel']");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//div[@id='overlay']//a[./text()='Reject']");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//div[@id='overlay']//a[./text()='Approve']");
			break;
		case "Pending user response":
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//div[@id='overlay']//a[./text()='Cancel']");
			break;
		case "Deactived By System":
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//div[@id='overlay']//a[./text()='Cancel']");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//div[@id='overlay']//a[./text()='Deactivate']");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//div[@id='overlay']//a[./text()='Saved Changes']");
			break;		
		default:
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//div[@id='overlay']//a[./text()='Cancel']");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//div[@id='overlay']//a[./text()='Save Changes']");
			break;
		}
	}	
	
	/**
	 * Verifying the basic components of User Profile
	 */
	public void verifyUserProfile(){
		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//h1[@id='title'][./text()='Edit User']");
		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//label[./text()='First Name']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//label[./text()='Surname']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//label[./text()='Permissions']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//label[./text()='Company/Agency']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//label[./text()='Department']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//label[./text()='Job title']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//label[./text()='Note']");
		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//label[./text()='First Name']//following-sibling::input");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//label[./text()='Surname']//following-sibling::input");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//label[./text()='Company/Agency']//following-sibling::input");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//label[./text()='Department']//following-sibling::input");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//label[./text()='Job title']//following-sibling::input");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//label[./text()='Note']//following-sibling::input");		
	}
	
	/**
	 * Method to set a value on a field that it is on User Profile page
	 * @param field
	 * @param value
	 */
	public void setUserProfileFields(String field, String value) {
		
		try {
			DriverManager.driver.findElement(By.xpath(".//label[contains(text(),'"+field+"')]//following-sibling::input")).clear();
			DriverManager.driver.findElement(By.xpath(".//label[contains(text(),'"+field+"')]//following-sibling::input")).sendKeys(value);
		} catch (Exception e) {
			Assert.fail("The field " +field+" was not found");
		}		
	}
	
	/**
	 * Method to set a permission on User Profile page
	 * @param permission
	 */
	public void setUserProfilePermissions(String permission) {
		
		if(DriverManager.driver.findElement(By.cssSelector(".tree>li")).getAttribute("class").contains("closed"))
			CommonFunctions.click(LocatorType.cssSelector,".tree>li>span");
		
		if(DriverManager.driver.findElement(By.cssSelector(".tree>li>ul>li")).getAttribute("class").contains("closed"))
			CommonFunctions.click(LocatorType.Xpath, ".//label[contains(text(),'On Brand')]//preceding-sibling::span");
		
		try {
			CommonFunctions.checkBox(LocatorType.Xpath, ".//label[./text()='"+permission+"']//preceding-sibling::input[contains(@class,'checkbox')]");
		} catch (Exception e) {
			Assert.fail("The next permission doesn't exist "+permission);
		}				
	}
	
	/**
	 * Method to verify the errors on User Profile page
	 */
	 @And("Verify errors$")
	 public void Verify_errors(){
		 DriverManager.driver.findElement(By.xpath(".//label[contains(text(),'First Name')]//following-sibling::input")).clear();
		 DriverManager.driver.findElement(By.xpath(".//label[contains(text(),'Surname')]//following-sibling::input")).clear();
		 DriverManager.driver.findElement(By.xpath(".//label[contains(text(),'Department')]//following-sibling::input")).clear();
		 DriverManager.driver.findElement(By.xpath(".//label[contains(text(),'Job title')]//following-sibling::input")).clear();
		 
		try {
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//label[contains(text(),'First Name')]//following-sibling::dfn[contains(text(),'This field is required')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//label[contains(text(),'Surname')]//following-sibling::dfn[contains(text(),'This field is required')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//label[contains(text(),'Department')]//following-sibling::dfn[contains(text(),'This field is required when usage type is ')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, ".//label[contains(text(),'Job title')]//following-sibling::dfn[contains(text(),'This field is required')]");
			
		} catch (Exception e) {
			Assert.fail("Some error is missing");
		}
	 }
	 
	 /**
	  * Method to fill all the fields with the values given in the feature file
	  * @param dataTable
	  */
	 @Then("Fill user data$")
	 public void Fill_user_data(DataTable dataTable){
		 java.util.List<java.util.List<String>> tableData = dataTable.raw();
		 
		 for (int i = 1; i < tableData.size(); i++) {
			 if (tableData.get(i).get(0).equals("Permissions")) {
				 setUserProfilePermissions(tableData.get(i).get(1));
			}else{
			 setUserProfileFields(tableData.get(i).get(0),tableData.get(i).get(1));
			}
		}
	 }
	 
	 /**
	  * Method to edit a user
	  * @param dataTable
	  */
	 @When("Edit the user$")
	 public void Edit_the_user(DataTable dataTable){
		 
		 Fill_user_data(dataTable);
		 CommonFunctions.clickOnOverlayButton("Save");
	 }
}