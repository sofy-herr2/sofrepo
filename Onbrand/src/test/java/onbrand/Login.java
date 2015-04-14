package onbrand;

import java.util.concurrent.TimeUnit;

import onbrand.tools.DriverManager;

import org.junit.Assert;
import org.openqa.selenium.By;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Login {

	//public static WebDriver driver =  new FirefoxDriver();

		static String baseURL = "http://devpointuat.northplains.com/"; 
		
		
		@Given("^Navigate to login page$")
		public void Navigates_to_login_page() throws Throwable {
		    
			DriverManager.driver.navigate().to(baseURL);
			
		}
		
		
		@When("^Login with invalid username$")
		public void Login_with_invalid_username(DataTable dataTable) throws Throwable {
		    
			java.util.List<java.util.List<String>> tableData = dataTable.raw();
			
			DriverManager.driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
			
			DriverManager.driver.findElement(By.id("email")).sendKeys(tableData.get(1).get(0));
			DriverManager.driver.findElement(By.id("password")).sendKeys(tableData.get(1).get(1));
			DriverManager.driver.findElement(By.xpath("//input[@value='Login']")).click();
		}
		
		
		@When("^Login with invalid password$")
		public void Login_with_invalid_password(DataTable dataTable) throws Throwable {

			java.util.List<java.util.List<String>> tableData = dataTable.raw();
			
			DriverManager.driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
			
			DriverManager.driver.findElement(By.id("email")).sendKeys(tableData.get(1).get(0));
			DriverManager.driver.findElement(By.id("password")).sendKeys(tableData.get(1).get(1));
			DriverManager.driver.findElement(By.xpath("//input[@value='Login']")).click();
		}
		
		
		@When("^Login with inactive username$")
		public void Login_with_inactive_username(DataTable dataTable) throws Throwable {
		   
			java.util.List<java.util.List<String>> tableData = dataTable.raw();
			
			DriverManager.driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
			
			DriverManager.driver.findElement(By.id("email")).sendKeys(tableData.get(1).get(0));
			DriverManager.driver.findElement(By.id("password")).sendKeys(tableData.get(1).get(1));
			DriverManager.driver.findElement(By.xpath("//input[@value='Login']")).click();
		}
		
		//Then Verify error message "message"
		@Then("^Verify login error message \"([^\"]*)\"$")
		public void Verify_login_error_message(String message) throws Throwable {
		    
			Assert.assertTrue(DriverManager.driver.findElement(By.className("error")).getAttribute("textContent").contains(message));
		    
		}
		
		@When("^Click on the login button$")
		public void Click_on_the_login_button() throws Throwable {

			CommonFunctions.click(LocatorType.Xpath, "//input[@value='Login']");
			
		}
		
		
		@When("^Enter login details$")
		public void Enter_login_details(DataTable dataTable) throws Throwable {
		   
			java.util.List<java.util.List<String>> tableData = dataTable.raw();
			
			DriverManager.driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
			
			DriverManager.driver.findElement(By.id("email")).sendKeys(tableData.get(1).get(0));
			DriverManager.driver.findElement(By.id("password")).sendKeys(tableData.get(1).get(1));
			DriverManager.driver.findElement(By.xpath("//input[@value='Login']")).click();
		}
		
		
		
		@Then("^Verify Dashboard page$")
		public void Verify_Dashboard_page() throws Throwable {

			Assert.assertTrue(DriverManager.driver.getTitle().contains("Dashboard | Home"));
		}
		
		
		@Then("^Verify validation error for \"([^\"]*)\"$")
		public void Verify_validation_error_for(String field) throws Throwable {
			
			Assert.assertTrue(DriverManager.driver.findElement(By.id(field)).getAttribute("class").contains("error"));		
		}
				
}
