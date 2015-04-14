package onbrand;

import java.util.concurrent.TimeUnit;

import onbrand.tools.DriverManager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Registration {

	@Given("^Navigate to create account page$")
	public void Navigate_to_create_account_page() throws Throwable {

		Navigation.gotoURL("/login/register/internal/");
	}
	
	
	@When("^Enter registration data$")
	public void Enter_registration_data(DataTable dataTable) throws Throwable {
		
		java.util.List<java.util.List<String>> tableData = dataTable.raw();		
		DriverManager.driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		
		
		//CommonFunctions.clearAndSendKeys(By.id("DEF_ATT_user.name.given"), tableData.get(1).get(1));
		DriverManager.driver.findElement(By.id("DEF_ATT_user.name.given")).sendKeys(tableData.get(1).get(1));
		DriverManager.driver.findElement(By.id("DEF_ATT_user.name.family")).sendKeys(tableData.get(2).get(1));
		DriverManager.driver.findElement(By.id("USERNAME")).sendKeys(tableData.get(3).get(1));
		DriverManager.driver.findElement(By.id("PASSWORD")).sendKeys(tableData.get(4).get(1));
		DriverManager.driver.findElement(By.id("confirmedPassword")).sendKeys(tableData.get(5).get(1));
		DriverManager.driver.findElement(By.id("DEF_ATT_user.jobtitle")).sendKeys(tableData.get(6).get(1));
		DriverManager.driver.findElement(By.id("DEF_ATT_user.department")).sendKeys(tableData.get(7).get(1));
		DriverManager.driver.findElement(By.id("marketingContact")).sendKeys(tableData.get(8).get(1));
		DriverManager.driver.findElement(By.id("message")).sendKeys(tableData.get(9).get(1));
	
		DriverManager.driver.findElement(By.id("terms")).click();
		
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Apply for Access']");
				
	}
	
	
	@Then("^Verify successful user registration$")
	public void Verify_successful_user_registration() throws Throwable {

		//Boolean wait = new WebDriverWait(TestHooks.driver, 10).until(ExpectedConditions.titleIs("Registration successful"));
		//Assert.assertTrue(wait);	
		
		VerificationFunctions.verifyPageTitle("Registration successful");
				
	}
	
	
	@Then("^Delete user from Backend \"([^\"]*)\"$")
	public void Delete_user_from_Backend(String user) throws Throwable {
		//Login to backend and delete the user
		
		DriverManager.driver.navigate().to(Login.baseURL+"vyre4/");
		
		DriverManager.driver.findElement(By.id("USER_USERNAME")).sendKeys("yoge");
		DriverManager.driver.findElement(By.id("USER_PASSWORD")).sendKeys("yoge");
		DriverManager.driver.findElement(By.name("submit-user-login")).click();
		
		
		DriverManager.driver.navigate().to(Login.baseURL +"vyre4/realms/users/list.st8?REALM_CONFIG_ENT_ID=1");

		DriverManager.driver.findElement(By.name("SEARCH_USER_QUERY")).sendKeys(""+user+""); 
		DriverManager.driver.findElement(By.name("SEARCH_USER_QUERY")).submit();
		
		Boolean wait1 = new WebDriverWait(DriverManager.driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//a[.='"+user+"']"), user));
		Assert.assertTrue(wait1);		
		
		DriverManager.driver.findElement(By.xpath("//a/img[@src='/vyre4/images/delete.png']")).click();	
						
		DriverManager.driver.switchTo().alert().accept();		
	}
	
	
	@Then("^Verify registration page$")
	public void Verify_registration_page() throws Throwable {
	
		VerificationFunctions.verifyPageTitle("Create an Account");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'First name')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "DEF_ATT_user.name.given");		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Surname')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "DEF_ATT_user.name.family");		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Email')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "USERNAME");		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Password')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "PASSWORD");		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Confirm password')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "confirmedPassword");		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Job title')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "DEF_ATT_user.jobtitle");		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Department')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "DEF_ATT_user.department");		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Marketing contact')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "marketingContact");		
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Label[contains(text(),'Message')]");
		VerificationFunctions.verifyElementVisible(LocatorType.id, "message");
		
		VerificationFunctions.verifyElementVisible(LocatorType.id, "terms");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='Apply for Access']");
	}
	
	
	@Then("^Verify mandatory error messages for registration page$")
	public void Verify_mandatory_error_messages_for_registration_page() throws Throwable {

		Assert.assertTrue(DriverManager.driver.findElement(By.id("DEF_ATT_user.name.given")).getAttribute("class").contains("validationElementError"));
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div[input[@id='DEF_ATT_user.name.given']]/div[@class='errorContainerClass']/strong[contains(text(),'field required')]");
		
		Assert.assertTrue(DriverManager.driver.findElement(By.id("DEF_ATT_user.name.given")).getAttribute("class").contains("validationElementError"));	
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div[input[@id='DEF_ATT_user.name.family']]/div[@class='errorContainerClass']/strong[contains(text(),'field required')]");
		
		Assert.assertTrue(DriverManager.driver.findElement(By.id("USERNAME")).getAttribute("class").contains("validationElementError"));
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div[input[@id='USERNAME']]/div[@class='errorContainerClass']/strong[contains(text(),'field required')]");
		
		Assert.assertTrue(DriverManager.driver.findElement(By.id("DEF_ATT_user.jobtitle")).getAttribute("class").contains("validationElementError"));	
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div[input[@id='DEF_ATT_user.jobtitle']]/div[@class='errorContainerClass']/strong[contains(text(),'field required')]");
		
		Assert.assertTrue(DriverManager.driver.findElement(By.id("DEF_ATT_user.department")).getAttribute("class").contains("validationElementError"));
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div[input[@id='DEF_ATT_user.department']]/div[@class='errorContainerClass']/strong[contains(text(),'field required')]");
		
		
	}
	
		
	@Then("^Clear all Registration fields$")
	public void Clear_all_Registration_fields() throws Throwable {
		
		CommonFunctions.clearText(LocatorType.id, "DEF_ATT_user.name.given");
		CommonFunctions.clearText(LocatorType.id, "DEF_ATT_user.name.family");
		CommonFunctions.clearText(LocatorType.id, "USERNAME");
		CommonFunctions.clearText(LocatorType.id, "PASSWORD");
		CommonFunctions.clearText(LocatorType.id, "confirmedPassword");
		CommonFunctions.clearText(LocatorType.id, "DEF_ATT_user.jobtitle");
		CommonFunctions.clearText(LocatorType.id, "DEF_ATT_user.department");
		CommonFunctions.clearText(LocatorType.id, "marketingContact");
		CommonFunctions.clearText(LocatorType.id, "message");
	
		DriverManager.driver.findElement(By.id("terms")).click();
	}


	
	//Then Verify message "message" in class "classname"
	@Then("^Verify message \"([^\"]*)\" in class \"([^\"]*)\"$")
	public void Verify_message(String message, String className) throws Throwable {
		
		Boolean wait = new WebDriverWait(DriverManager.driver, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.className(className), message));
		Assert.assertTrue(wait);
		
	}
	
	
	@When("^Enter registration data without accepting terms$")
	public void Enter_registration_data_without_accepting_terms(DataTable dataTable) throws Throwable {
		
		java.util.List<java.util.List<String>> tableData = dataTable.raw();		
		DriverManager.driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		
		
		//CommonFunctions.clearAndSendKeys(By.id("DEF_ATT_user.name.given"), tableData.get(1).get(1));
		DriverManager.driver.findElement(By.id("DEF_ATT_user.name.given")).sendKeys(tableData.get(1).get(1));
		DriverManager.driver.findElement(By.id("DEF_ATT_user.name.family")).sendKeys(tableData.get(2).get(1));
		DriverManager.driver.findElement(By.id("USERNAME")).sendKeys(tableData.get(3).get(1));
		DriverManager.driver.findElement(By.id("PASSWORD")).sendKeys(tableData.get(4).get(1));
		DriverManager.driver.findElement(By.id("confirmedPassword")).sendKeys(tableData.get(5).get(1));
		DriverManager.driver.findElement(By.id("DEF_ATT_user.jobtitle")).sendKeys(tableData.get(6).get(1));
		DriverManager.driver.findElement(By.id("DEF_ATT_user.department")).sendKeys(tableData.get(7).get(1));
		DriverManager.driver.findElement(By.id("marketingContact")).sendKeys(tableData.get(8).get(1));
		DriverManager.driver.findElement(By.id("message")).sendKeys(tableData.get(9).get(1));
		
		CommonFunctions.click(LocatorType.Xpath, "//input[@value='Apply for Access']");
				
	}
	
	
}
