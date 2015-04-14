package onbrand;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import onbrand.tools.DriverManager;

public class SlideshowImages {
	private int totalCreatedImages; //the total of created images, it is updated after adding o deleting images
	private int totalCreatedImagesWithPrefix = 0;  //the total of created Images in execution of automated scenario("ssi.item as prefix"), it is updated after adding o deleting images
	private List<String> createdImageNamesList = new ArrayList<String>(); // list of name for each created item
	private int totalNumberOfPagination; //total number of pagination calculated according to selected item per page and totalCreatedImages
	private String paginationBarPosition; // top or bottom values
	private int selectItemPerPageOption; //the selected option of items per page : 5,15,30 or 45
	private String lastSearchKeyword;
	private int defaultImagesCount; //number of images displayed  according to item per Page and pagination (if it there is) selected 
	public static String HEADER = "header";
	public static String FOOTER = "footer";
	private String slideImagesPath = "C:\\svnqa\\OB Cucumber Automation\\Onbrand\\TestData\\sf.img1.jpg";
	
	/*By Shirley*/
	@When ("^Navigate to create new slideshow image page$")
	public void Navigate_to_create_new_slideshow_image_page() throws Throwable{
				
		CommonFunctions.click(LocatorType.Xpath, "//a[contains(text(),'Create New Item')]");
		CommonFunctions.browserWait(50);
		
		try {
			//Verifying the components on the Create New Slideshow Image
			//Verifying the Titles
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//span[contains(text(),'Create New Slideshow Image')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//span[contains(text(),'Core Info')]");

			//Verifying the labels
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Upload file')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Link URL')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Opens in new window')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Name')]");
			
			//Verifying the Input Text fields
			VerificationFunctions.verifyElementVisible(LocatorType.cssSelector, "#item_file");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Link URL')]//following-sibling::div//input[contains(@class, 'portlet-form-input-field text')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(), 'Opens in new window')]//following::input[contains(@class, 'input-field checkbox')]");
			VerificationFunctions.verifyElementVisible(LocatorType.cssSelector, "#item_name");
			
			//Verifying Save and Cancel buttons
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Save')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Cancel')]");
		
		} catch (Exception e) {
			Assert.fail("There is some item missing in Create New Slideshow Image window");
		}
	}
	
	@When("^Create slideshow image \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void Create_slideshow_image(String file, String link,String open, String name) throws Throwable{
		
		Set_fields_with(file, link, open, name);
		
		if(file.equals(""))
			CommonFunctions.click(LocatorType.Xpath,"//a[contains(text(),'Save')]");
		else
			CommonFunctions.clickOnOverlayButton("Save");
	}
	
	@And("^Set fields with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void Set_fields_with(String file, String link, String open, String name) throws Throwable{
		
		//Giving the values
		DriverManager.driver.findElement(By.cssSelector("#item_file")).sendKeys(file);
		DriverManager.driver.findElement(By.xpath("//label[contains(text(),'Link URL')]//following-sibling::div//input[contains(@class, 'portlet-form-input-field text')]")).sendKeys(link);
		DriverManager.driver.findElement(By.cssSelector("#item_name")).sendKeys(name);
				
		if(open.equals("yes")){
			CommonFunctions.checkBox(LocatorType.Xpath,"//label[contains(text(),'Opens in new window')]//following::input[contains(@class,'input-field checkbox')]");
		}else{
			CommonFunctions.uncheckBox(LocatorType.Xpath,"//label[contains(text(),'Opens in new window')]//following::input[contains(@class,'input-field checkbox')]");
		}
	}
	
	@And("^Navigate to slideshow image \"([^\"]*)\"$")
	public void Navigate_to_slideshow_image(String name) throws Throwable {

		CommonFunctions.click(LocatorType.Xpath, "//a[./text()= '" + name + "']");
		//VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//span[contains(text(),'Core Info')]");
		try {
			CommonFunctions.waitForElementPresent(LocatorType.Xpath, "//span[contains(text(),'Core Info')]",10);
			
			//Verifying labels, links
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Preview')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Upload file')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Link URL')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Opens in new window')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Name')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Download Asset')]");
			
			//Verifying buttons
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Delete')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Cancel')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Save')]");
						
			//Verifying the Input Text fields
			VerificationFunctions.verifyElementVisible(LocatorType.cssSelector, "#item_file");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Link URL')]//following-sibling::div//input[contains(@class, 'portlet-form-input-field text')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(), 'Opens in new window')]//following::input[contains(@class, 'input-field checkbox')]");
			VerificationFunctions.verifyElementVisible(LocatorType.cssSelector, "#item_name");
						
			//Verifying values
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//span[contains(text(),'"+name+"')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='"+name+"']");
			
		} catch (Exception e) {
			Assert.fail("There is some item missing in Edit Slideshow Image window");
		}		
	}
	
	@When("^Update slideshow image \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void Update_slideshow_image(String newImage, String newLink, String newOpen,String newName)throws Throwable{
			
		DriverManager.driver.findElement(By.cssSelector("#item_file")).sendKeys(newImage);
		DriverManager.driver.findElement(By.xpath("//label[contains(text(),'Link URL')]//following-sibling::div//input[contains(@class, 'portlet-form-input-field text')]")).sendKeys(newLink);
		DriverManager.driver.findElement(By.cssSelector("#item_name")).clear();
		DriverManager.driver.findElement(By.cssSelector("#item_name")).sendKeys(newName);
				
		if(newOpen.equals("yes")){
			CommonFunctions.checkBox(LocatorType.Xpath,"//label[contains(text(),'Opens in new window')]//following::input[contains(@class,'input-field checkbox')]");
		}else{
			CommonFunctions.uncheckBox(LocatorType.Xpath,"//label[contains(text(),'Opens in new window')]//following::input[contains(@class,'input-field checkbox')]");			
		}		
		CommonFunctions.clickOnOverlayButton("Save");	   
	}
	
	@Then("^Verify all errors displayed$")
	public void Verify_all_errors_displayed() throws Throwable {
		
		try {			
			//Verifying that the errors were displayed
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//span[contains(text(),'2')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//span[contains(text(),'2 errors')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@name='item_file']//following-sibling::dfn[contains(text(),'This field is required')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@name='item_name']//following-sibling::dfn[contains(text(),'This field is required')]");
			Assert.assertTrue(true);
			
		} catch (Exception e) {
			
			//Catching the exception and displaying an appropriate error
			Assert.fail("Some error is missing during the verification");
			throw(e);
		}	
	}

	//by virginia search
	@Given("^Validate there are \"([^\"]*)\" slideshow images \"([^\"]*)\" \"([^\"]*)\"$")
	public void Validate_there_are_slideshow_images(int numberOfImages, String name, String file) throws Throwable {
		initializeImagesCreation(); 
		int matchCounter = 0; 
		String nameToMatch = name;
		if (nameToMatch.contains("*")) {
			matchCounter = CommonFunctions.calculateNumberItemNameStartWithPrefix(name.replace("*", ""), createdImageNamesList);
		} else {
			matchCounter = CommonFunctions.calculateNumberItemNameEqualToPrefix(nameToMatch, createdImageNamesList);
		}

		if (matchCounter > numberOfImages) {
			deleteSlidesShowImagesWithPrefix(matchCounter  - numberOfImages, nameToMatch);
		} else if (matchCounter < numberOfImages) {
			addImagesUsingNameFileNumberAndLink(nameToMatch, numberOfImages, file, "");
		}

	}

	@When("^Search slideshow images by \"([^\"]*)\"$")
	public void Search_slideshow_images_by(String text) throws Throwable {
		lastSearchKeyword = text;
		CommonFunctions.sleep(1000);
		CommonFunctions.clearAndSendKeys(By.cssSelector("input[id='ajaxSearchInput']"), lastSearchKeyword);
		CommonFunctions.pressKey("Enter");
		CommonFunctions.waitUntilCompleteLoading();
	}

	@Then("^Verify that \"([^\"]*)\" slideshow images are found$")
	public void Verify_that_slideshow_images_are_found(int expectedItemsCount) throws Throwable {
		int matchedItemsCount = 0;

		//Get elements that match with the items style
		List<WebElement> itemNames = DriverManager.driver.findElements(By.xpath("//div[@class='title']//a[@class='title']"));
		for (WebElement element:itemNames) {
			if (lastSearchKeyword.equals(element.getText())) {
				matchedItemsCount++;				
			}
		}

		Assert.assertTrue(matchedItemsCount == expectedItemsCount);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h3[contains(text(),'Displaying 1 - " + expectedItemsCount + " of " + expectedItemsCount + "')]");
	}
	
	@Then("^Verify that \"([^\"]*)\" slideshow images are found using \"([^\"]*)\"$")
	public void Verify_that_slideshow_images_are_found_using(String expectedItemsCount, String symbol) throws Throwable {
		int matchedItemsCount = 0;
		//Get elements that match with the items style
		List<WebElement> items = DriverManager.driver.findElements(By.xpath("//div[@class='title']//a[@class='title']"));
		if (symbol.equals("*")) {
			lastSearchKeyword = lastSearchKeyword.replace("*", "");
			for (WebElement element:items) {
				if (element.getText().startsWith(lastSearchKeyword)) {
					matchedItemsCount++;				
				}
			}
		}

		Assert.assertTrue(Integer.parseInt(expectedItemsCount) == matchedItemsCount);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h3[contains(text(),'Displaying 1 - " + expectedItemsCount + " of " + expectedItemsCount + "')]");
	}
	
	@Then("^Verify that \"([^\"]*)\" slideshow images are found with \"([^\"]*)\"$")
	public void Verify_that_slideshow_images_are_found_with(String expectedItemsCount, String itemName) throws Throwable {
		int itemsCount = CommonFunctions.getElementsCount(By.xpath("//div[@class='title']//a[contains(text(),'" + itemName + "')]"));

		Assert.assertTrue(Integer.parseInt(expectedItemsCount) == itemsCount);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h3[contains(text(),'Displaying 1 - " + expectedItemsCount + " of " + expectedItemsCount + "')]");
	}

	@Given("^Validate there are \"([^\"]*)\" slideshow images \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void Validate_there_are_slideshow_images(int numberOfImages, String name, String file, String link) throws Throwable {
		initializeImagesCreation(); 
		int matchCounter = 0; 
		matchCounter = CommonFunctions.calculateNumberItemNameEqualToPrefix(name, createdImageNamesList);

		if (matchCounter > numberOfImages) {
			deleteSlidesShowImagesWithPrefix(matchCounter  - numberOfImages, name);
		} else if (matchCounter < numberOfImages) {
			addImagesUsingNameFileNumberAndLink(name, numberOfImages, file, link);
		}
	}

	@Then("^Reset the slideshow images search results$")
	public void Reset_the_slideshow_images_search_results() throws Throwable {
		CommonFunctions.click(LocatorType.Xpath,"//a[contains(text(),'Reset')]");
		CommonFunctions.waitUntilCompleteLoading();
		
		//count the items displayed 
		int itemsCount = CommonFunctions.getElementsCount(CommonFunctions.getItemNameLocatorOfActiveView());
		Assert.assertEquals("The result after reseting the search is incorrect :" + defaultImagesCount +  " is different to " + itemsCount,
				            defaultImagesCount, itemsCount);
		
		//Verify the Displayed label
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h3[contains(text(),'Displaying 1 - " + defaultImagesCount + " of " + totalCreatedImages + "')]");
	}
	   
	
	//By Virginia pagination
	@When("^Search (\\d+) slideshow images with \"([^\"]*)\" item name$")
	public void Search_slideshow_images_with_item_name(int numberOfItems, String itemName) throws Throwable {
		initializeImagesCreation();
		Search_slideshow_images_by(itemName);
		int matchedItem = CommonFunctions.getElementsCount(CommonFunctions.getItemNameLocatorOfActiveView());

		while (matchedItem != numberOfItems) {
			Reset_the_slideshow_images_search_results();
			if (matchedItem > numberOfItems) {
				deleteSlidesShowImagesWithPrefix(matchedItem - numberOfItems, itemName);
			} else if (matchedItem < numberOfItems){
				addNewSlidesShowImages(numberOfItems-matchedItem, itemName, ""); //add slideshow images with a defined itemName
			}
			Search_slideshow_images_by(itemName);
			CommonFunctions.sleep(30000);
			matchedItem = CommonFunctions.getElementsCount(CommonFunctions.getItemNameLocatorOfActiveView());
		}
	}
	
	@Given("^Validate there are \"([^\"]*)\" slideshow images$")
	public void Validate_there_are_slideshow_images(String requiredImages) throws Throwable {
		initializeImagesCreation();
		int numberRequiredItems = Integer.parseInt(requiredImages);
		if (numberRequiredItems > totalCreatedImages) {
			int number = numberRequiredItems - totalCreatedImages;
			addNewSlidesShowImages(number,"ssi.item", ""); //add slideshow images with ssi.item as prefix of item name
		}
	}
	
	@Then("^Verify number of pages is correct on slideshow images \"([^\"]*)\" pagination$")
	public void Verify_number_of_pages_is_correct_on_slideshow_images_pagination(String pagination) throws Throwable {
		List<WebElement> paginationOptionsList; //list of pagination Options
		selectItemPerPageOption = CommonFunctions.getActiveItemPerPageOption(); //initialize the selectItemPerPageOption
		totalNumberOfPagination = CommonFunctions.calculateNumberOfPagination(selectItemPerPageOption, totalCreatedImages);
		if (pagination.equals("top")) {
			paginationOptionsList = CommonFunctions.getListOfPaginationOptions("header");
			paginationBarPosition = HEADER; 
		} else {
			paginationOptionsList = CommonFunctions.getListOfPaginationOptions("footer");
			paginationBarPosition = FOOTER; 
		}
		List<String> paginationListA =  CommonFunctions.createNumberPaginationOptionList(totalNumberOfPagination);
		int positionA = 0;
		for (int positionB = 1; positionB < paginationOptionsList.size()-1; positionB++) { //verify the number of pagination displayed
			Assert.assertTrue((paginationOptionsList.get(positionB).getText()).equals(paginationListA.get(positionA)));
			positionA++;
		}
	}

	@Then("^Verify slideshow images pagination works using number of page$")
	public void Verify_slideshow_images_pagination_works_using_number_of_page() throws Throwable {
		String numberPaginationLocator;
		int counterItemPerPage;
		int rest = totalCreatedImages;
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

	@Then("^Verify \"([^\"]*)\" icon is not displayed on slideshow images$")
	public void Verify_icon_is_not_displayed_on_slideshow_images(String iconName) throws Throwable {
		String numberPaginationLocator;
		By iconLocator;
		By activePaginationLocator = CommonFunctions.getActivePaginationOptionLocator(paginationBarPosition);
		if (iconName.equals("previous")) {
			//to click on first number of pagination
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

	@Then("^Verify slideshow images pagination works using previous$")
	public void Verify_slideshow_images_pagination_works_using_previous() throws Throwable {
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
			
			//get the number of slideshow images displayed
			counterItemPerPage = CommonFunctions.getElementsCount(CommonFunctions.getItemNameLocatorOfActiveView());
			
			//Verify the number of slideshow images displayed is correct according to selectItePerPageOption
			Assert.assertTrue(counterItemPerPage == selectItemPerPageOption);
		}
	}

	@Then("^Verify slideshow images pagination works using next$")
	public void Verify_slideshow_images_pagination_works_using_next() throws Throwable {
		int counterItemPerPage;
		int rest = totalCreatedImages  - selectItemPerPageOption;
		By nextLocator = CommonFunctions.getNextPaginationOptionLocator(paginationBarPosition);
		WebElement nextElement = DriverManager.driver.findElement(nextLocator);
		By activePaginationLocator = CommonFunctions.getActivePaginationOptionLocator(paginationBarPosition);
		String startNumberLocator = CommonFunctions.getNumberPaginationOptionLocator(1 , paginationBarPosition);
		
		//click on startNumber pagination option
		CommonFunctions.clickOnPaginationNumberOption(activePaginationLocator, startNumberLocator);
		
		//verify that the next pagination option is displayed
		Assert.assertTrue(nextElement.isDisplayed());
		
		for (int numberPagination = 2; numberPagination <= totalNumberOfPagination; numberPagination++) {
			CommonFunctions.click(nextLocator);
			CommonFunctions.waitUntilCompleteLoading();
			
			//get the number of slideshow images displayed
			counterItemPerPage = CommonFunctions.getElementsCount(CommonFunctions.getItemNameLocatorOfActiveView());
			if (numberPagination < totalNumberOfPagination) {
				//Verify the number of slideshow images displayed is correct according to selectItePerPageOption
				Assert.assertTrue(counterItemPerPage == selectItemPerPageOption);
				rest = rest - selectItemPerPageOption;
			} else {
				//Verify the number of slideshow images displayed is correct according to selectItePerPageOption
				Assert.assertTrue(counterItemPerPage == rest);
			}
		}
	}
	
	/*pagination*/
	
	@Then("^Verify that \"([^\"]*)\" slideshow images are displayed$")
	public void Verify_that_slideshow_images_are_displayed(int expectedItemPerPage) throws Throwable {
		int itemsCount = CommonFunctions.getElementsCount(CommonFunctions.getItemNameLocatorOfActiveView());

	    Assert.assertTrue(itemsCount == expectedItemPerPage);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h3[contains(text(),'Displaying 1 - " + itemsCount + " of " + totalCreatedImages + "')]");
	}
	
	@Given("^Add slideshow images$")
	public void Add_slideshow_images(DataTable dataTable) throws Throwable {
		List<Map<String, String>> maps = dataTable.asMaps(String.class,String.class);

		for (Map<String, String> map: maps) {
			String itemname = map.get("itemname");
			int numberOfItems = Integer.valueOf(map.get("numberOfImages"));
			String filePath = map.get("file");
			String linkUrl = map.get("link");
			addImagesUsingNameFileNumberAndLink(itemname, numberOfItems, filePath, linkUrl);
		}
		initializeImagesCreation();
	}
	
	/**
	 * Only delete all created item during the execution of feature that start with "sf.item" prefix
	 * */
	@Given("^Delete all slideshow images$")
	public void Delete_all_slideshow_images() throws Throwable {
		initializeImagesCreation();
		deleteSlidesShowImagesWithPrefix(totalCreatedImagesWithPrefix, "ssi.item");
	}
	
	/**
	 * Method to initialize the total number of all created items that there is after selecting "Items per page 45" option, 
	 * the total number of items created during automation execution (with sf.item prefix)and add their item names to a list for those(with sf.item prefix).
	 * 
	 * */
	private void initializeImagesCreation() {
		By itemNameLocator = CommonFunctions.getItemNameLocatorOfActiveView();
		defaultImagesCount = CommonFunctions.getElementsCount(itemNameLocator);
		totalCreatedImages = CommonFunctions.getTotalCreateElements();
		createdImageNamesList  = getListOfItemNameWithPrefix(itemNameLocator);
		if (!createdImageNamesList .isEmpty()) {
			totalCreatedImagesWithPrefix = createdImageNamesList.size();
		}
	}
	
	/**
	 * Method to add images for automated scenarios(add images with ssi.item as prefix )
	 * @param prefix
	 * @param numberRequiredImages
     * @param link
	 * */
	private void addNewSlidesShowImages(int numberRequiredImages, String prefix, String link) throws Throwable {
		String itemName = prefix;
		int id = totalCreatedImagesWithPrefix + 1;
		for (int i = 1; i <= numberRequiredImages; i++) { 
			if (prefix.equals("ssi.item")) {
				itemName = prefix + id ;
			}
			//add the required items for the scenario
			Navigate_to_create_new_slideshow_image_page();
			Create_slideshow_image(slideImagesPath , link, "yes", itemName);
			id ++;
		}
		initializeImagesCreation();
	}
	
	private void addImagesUsingNameFileNumberAndLink(String itemname, int numberOfItems, String filePath, String link) throws Throwable {
		for (int i = 1; i <= numberOfItems; i++) {
			//add the required items for the scenario
			String name = itemname;
			Navigate_to_create_new_slideshow_image_page();
			if (name.contains("*")) {
				name = itemname.replace("*", (i+""));
			}
			Create_slideshow_image(filePath , link, "yes", name);
		}
		initializeImagesCreation();
	}
	
	/**
	 * Method to delete slideshow images created in the execution of automated scenarios(delete the item that has "ssi.item" as prefix )
	 * @param numberOfImagesToDelete
	 * @param prefixName
	 * */
	private void deleteSlidesShowImagesWithPrefix(int numberOfImagesToDelete, String prefixName) throws Throwable {
		//Set_the_items_per_page_option("45");
		BrandAssets brandAssets = new BrandAssets();
		if (totalCreatedImagesWithPrefix > 0) {
			int numberOfDeletedItems = 0;
			String itemName = "";
			int itemIndex = totalCreatedImagesWithPrefix - 1;
			while((numberOfImagesToDelete > numberOfDeletedItems) && (!createdImageNamesList.isEmpty())) {
				itemName = createdImageNamesList.get(itemIndex);				
				if (itemName.startsWith(prefixName)) {
					//delete the created item in the scenario, starting last item
					Navigate_to_slideshow_image(itemName);
					brandAssets.the_item("Delete"); 
					CommonFunctions.sleep(3000); 
					createdImageNamesList.remove(itemIndex);
					this.totalCreatedImages--;
					this.totalCreatedImagesWithPrefix--;
					numberOfDeletedItems ++;
				}
				itemIndex--;
			}
			initializeImagesCreation();
		}
	}
	
	/**
	 * Method to get a list with the names of created item that has "sf.item" as prefix
	 * @param locatorView
	 * */
	private List<String> getListOfItemNameWithPrefix(By locatorView) {
		List<WebElement> elementItems = DriverManager.driver.findElements(locatorView);
		CommonFunctions.sleep(1000);
		List<String> itemNames = new ArrayList<String>();
		for (WebElement elementItem:elementItems) {
			
			if (elementItem.getText().startsWith("ssi.item")) {
				itemNames.add(elementItem.getText());
			}
		}
		return itemNames;
	}
}
