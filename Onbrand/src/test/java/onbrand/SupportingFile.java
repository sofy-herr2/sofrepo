package onbrand;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

import onbrand.tools.DriverManager;
import onbrand.tools.SortEntry;
import onbrand.tools.SearchResultsComponent;
import onbrand.BrandAssets;

public class SupportingFile {
    /*
     * Variable to keep the default items count when supporting files page is open the first time
     * it will be used to validate the Reset feature
     */
    public String sortBy;
    public String sortOrder;
    public int defaultItemsCount; //number of files displayed  according to item per Page and pagination (if it there is) selected
    private String lastSearchKeyword;
    private int selectedItemPerPageOption; //the selected option of items per page: 5,15,30 or 45
    private int totalNumberOfPagination; //total number of pagination calculated according to selected item per page and totalCreatedItems
    private int totalCreatedItems = 0;  //the total of created Items, it is updated after adding o deleting items
    private int totalCreatedItemsWithPrefix= 0;  //the total of created Items during the execution of automated scenario, it is updated after adding o deleting items
    private List<String> createdItemNamesList = new ArrayList<String>(); // list of name for each created item
    private List<WebElement> paginationOptionsList; //list of pagination Options
    private String paginationBarPosition; // top or bottom values
    public List<SortEntry> dataColumn; //It contains the data from a specific column from the list view
    public List<SortEntry> nameColumn; //It contains the data from Name column from the list view

	public static String HEADER = "header";
	public static String FOOTER = "footer";
	private String suppFilesPath = "C:\\svnqa\\OB Cucumber Automation\\Onbrand\\TestData\\sf.img1.jpg";
	 
	@Then("^Navigate to support file \"([^\"]*)\"$")
	public void Navigate_to_support_file(String name) throws Throwable {
								
		try {
			CommonFunctions.click(LocatorType.Xpath, "//a[./text()= '" + name + "']");
						
			//Verifying labels, links
			CommonFunctions.waitForElement(LocatorType.Xpath, "//label[contains(text(),'Preview')]",10);
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Upload File')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Upload Custom Preview')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Name')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Download Asset')]");
			
			//Verifying buttons
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Delete')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Cancel')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Save')]");
						
			//Verifying fields
			VerificationFunctions.verifyElementVisible(LocatorType.cssSelector, "#item_file");
			VerificationFunctions.verifyElementVisible(LocatorType.cssSelector, "#item_file_custom_preview");
			VerificationFunctions.verifyElementVisible(LocatorType.cssSelector, "#item_name");
			
			//Verifying values
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//span[contains(text(),'"+name+"')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@value='"+name+"']");
			
		} catch (Exception e) {
			Assert.fail("There is some item missing in Edit Supporting File window");
		}		
	}
	
	@Then("^Update support file \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void Update_support_file(String newSf, String newCP, String newName)throws Throwable{
			
		//Changing the old values
				DriverManager.driver.findElement(By.cssSelector("#item_file")).sendKeys(newSf);
				DriverManager.driver.findElement(By.cssSelector("#item_file_custom_preview")).sendKeys(newCP);
				DriverManager.driver.findElement(By.cssSelector("#item_name")).clear();
			    DriverManager.driver.findElement(By.cssSelector("#item_name")).sendKeys(newName);
	    	    
	    //Click on save button
	    CommonFunctions.clickOnOverlayButton("Save");			    
	}

	@When("^Navigate to create new support file page$")
	public void Navigate_to_create_new_support_file_page() throws Throwable {

		
		try {
			//Verifying the components on the Create New Supporting File
			//Verifying the Title and the tab
			CommonFunctions.click(LocatorType.Xpath, "//a[contains(text(),'Create New Item')]");
			CommonFunctions.waitForElement(LocatorType.Xpath, "//span[contains(text(),'Create New Supporting File')]", 30);
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//span[contains(text(),'Core Info')]");

			//Verifying the labels
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Upload File')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Upload Custom Preview')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[contains(text(),'Name')]");
			
			//Verifying the Input Text fields
			VerificationFunctions.verifyElementVisible(LocatorType.cssSelector, "#item_file");
			VerificationFunctions.verifyElementVisible(LocatorType.cssSelector, "#item_file_custom_preview");
			VerificationFunctions.verifyElementVisible(LocatorType.cssSelector, "#item_name");
			
			//Verifying Save and Cancel buttons
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Save')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'Cancel')]");
			
		} catch (Exception e) {
			Assert.fail("There is some item missing in Create New Supporting File window");
		}
	}
	
	@When("Create support file \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void Create_support_file(String file, String preview, String name) throws Throwable{
		
	    //Creating the supporting file
		CommonFunctions.waitForElement(LocatorType.Xpath, "//a[contains(text(),'Save')]",60);
		
		//Giving the required parameters
		Set_fields(file.trim(), preview.trim(), name.trim());	    	    		

		if(file.equals(""))
			CommonFunctions.click(LocatorType.Xpath,"//a[contains(text(),'Save')]");
		else
			CommonFunctions.clickOnOverlayButton("Save");
	    
	    totalCreatedItems++;
	    createdItemNamesList.add(name);
	    totalCreatedItemsWithPrefix++;
	}
	
	@When("Set fields \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void Set_fields(String file, String preview, String name) throws Throwable{
		
		//Giving the required parameters
		DriverManager.driver.findElement(By.cssSelector("#item_file")).sendKeys(file);
		DriverManager.driver.findElement(By.cssSelector("#item_file_custom_preview")).sendKeys(preview);
	    DriverManager.driver.findElement(By.cssSelector("#item_name")).sendKeys(name);	    
	}

	@Then("Verify the errors displayed$")
	public void Verify_the_errors_displayed() throws Throwable{
	    				
		try {			
			//Verifying that the errors were displayed
	    	CommonFunctions.waitForElement(LocatorType.Xpath, "//input[@name='item_file']//following-sibling::dfn[contains(text(),'This field is required')]", 50);
	    	VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//span[contains(text(),'2')]");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//span[contains(text(),'2 errors')]");
			
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//input[@name='item_name']//following-sibling::dfn[contains(text(),'This field is required')]");
			Assert.assertTrue(true);
			
		} catch (Exception e) {
			
			//Catching the exception and displaying an appropriate error
			Assert.fail("Some error is missing during the verification");
			throw(e);
		}
 	}	

	/*Jose */
	//To Verify that grid view item includes the thumbnail, title, type, size attributes and download option
	@Then("^Verify the asset attributes and options$")
	public void Verify_the_asset_attributes_and_options(List<String> attributes) throws Throwable {
		List<WebElement> assets = DriverManager.driver.findElements(By.cssSelector("." + CommonFunctions.getClassActiveTab() + " .assets li"));
		WebElement asset = assets.get(0);
		defaultItemsCount = assets.size();
		for (int index = 0; index < attributes.size(); index++) 
			Assert.assertTrue(asset.findElement(By.cssSelector("[class*='" + attributes.get(index) + "']")).isDisplayed());
	}

	//To verify that the list view item includes the id, name, type, size, last updated and by attributes
	@Then("^Verify the asset attributes$")
	public void Verify_the_asset_attributes(List<String> attributes) throws Throwable {
		WebElement searchResultsTableNode = DriverManager.driver.findElement(By.cssSelector("#listContainer"));
		SearchResultsComponent resultsList = new SearchResultsComponent(searchResultsTableNode);
		List<String> columns = resultsList.getColumns();
		for (String attribute: attributes) {
			Assert.assertTrue(columns.contains(attribute));
		}
	}
	
	//Verify that the number of items is the same in the grid and list views
	@Then("^Verify both views have same number of assets$")
	public void Verify_both_views_have_same_number_of_assets() throws Throwable {
		List<WebElement> items = DriverManager.driver.findElements(By.cssSelector("." + CommonFunctions.getClassActiveTab() + " tr[class*='item']"));
		boolean isEqual = false;
		if (items.size() == defaultItemsCount) {
			isEqual = true;
		}
		Assert.assertTrue(isEqual);
	}

	//Get up if the sort order is ascendent and down if it is descendent
	public String getSortOrder(String sortOrder) {
		if (sortOrder.compareTo("ascendent") == 0) {
			return "up";
		}
		return "down";
	}

	//Select the sort option and the ascending or descending order.
	@When("^Sort by \"([^\"]*)\" and \"([^\"]*)\" order$")
	public void Sort_by_and_order(String sortedBy, String sortedOrder) throws Throwable {
		sortOrder = getSortOrder(sortedOrder);
		String locator = "//li[@class='" + CommonFunctions.getClassActiveTab() + "']//div[contains(@class,'sortable ')]";
		//Select an specific option  from the "sort" combo box 
		CommonFunctions.selectDropList("//li[@class='" + CommonFunctions.getClassActiveTab() + "']//select", sortedBy);
		sortBy = getSortBy(sortedBy);
		CommonFunctions.setSortableUpOrDown(sortOrder, locator);
		WebElement searchResultsTableNode = DriverManager.driver.findElement(By.cssSelector("#listContainer"));
		SearchResultsComponent resultsList = new SearchResultsComponent(searchResultsTableNode);
		CommonFunctions.waitUntilCompleteLoading();
		dataColumn = resultsList.getColumnData(sortBy);
	}

	//To map the sort by according to the columns
	private String getSortBy(String sortedBy) {
		if (sortedBy.compareTo("Last Modified") == 0) {
			return "Last updated";
		} else if (sortedBy.compareTo("Created") == 0) {
			return "ID";
		}
		return sortedBy;
	}

	//Verify that the grid view is sorted by the criteria and ascendent or descendent order
	@Then("^Verify the grid view is ordered$")
	public void Verify_the_grid_view_is_ordered() throws Throwable {
		List<WebElement> elements = DriverManager.driver.findElements(By.xpath("//li[@class='" + CommonFunctions.getClassActiveTab() + "']//div[@class='title']/a"));
		Assert.assertTrue(elements.size() == dataColumn.size());
		Assert.assertTrue(CommonFunctions.isDataSorted(sortBy, sortOrder, dataColumn));
		Assert.assertTrue(isDataColumnsEquals(elements, nameColumn, "Name"));
	}

	//Get data ordered ascendent or descendent from a specific column from the list view  
	@Given("^Get \"([^\"]*)\" data in \"([^\"]*)\" order$")
	public void Get_data_in_order(String sortedBy, String sortedOrder) throws Throwable {
		sortOrder = getSortOrder(sortedOrder);
		String locator = "//li[@class='" + CommonFunctions.getClassActiveTab() + "']//div[contains(@class,'sortable ')]";
		//Select an specific option  from the "sort" combo box 
		CommonFunctions.selectDropList("//li[@class='" + CommonFunctions.getClassActiveTab() + "']//select", sortedBy);
		sortBy = getSortBy(sortedBy);
		CommonFunctions.setSortableUpOrDown(sortOrder, locator);
		WebElement searchResultsTableNode = DriverManager.driver.findElement(By.cssSelector("#listContainer"));
		SearchResultsComponent resultsList = new SearchResultsComponent(searchResultsTableNode);
		CommonFunctions.waitUntilCompleteLoading();
		dataColumn = resultsList.getColumnData(sortBy);
		nameColumn = resultsList.getColumnData("Name");
	}

	//Set the ascendent/descendent order for the sortable column
	@Given("^Set \"([^\"]*)\" order for \"([^\"]*)\" column$")
	public void Set_order_for_column(String sortedOrder, String sortedBy) throws Throwable {
		sortBy = getSortBy(sortedBy);
		//Map the proper text for the sortable column
		if ((sortedBy.compareTo("Name")) == 0) {
			sortedBy = "name sortable";
		} else if ((sortedBy.compareTo("Last updated")) == 0) {
			sortedBy = "lastModDate sortable";
		}
		sortOrder = getSortOrder(sortedOrder);
		setSortableColumn(sortOrder, "//table//thead//tr//th[contains(@class,'" + sortedBy + "')]");
		WebElement searchResultsTableNode = DriverManager.driver.findElement(By.cssSelector("#listContainer"));
		SearchResultsComponent resultsList = new SearchResultsComponent(searchResultsTableNode);
		dataColumn = resultsList.getColumnData(sortBy);
	}

	//To verify that the list view is sorted according to the sort field selected and ascending or descending order
	@Then("^Verify the list view is ordered$")
	public void Verify_the_list_view_is_ordered() throws Throwable {
		Assert.assertTrue(CommonFunctions.isDataSorted(sortBy, sortOrder, dataColumn));
	}

	//Verify if the sortable column is selected or not and selecting the ascendent or descendent order  
	private void setSortableColumn(String sortOrder, String locator) {
		WebElement sortUpDown = DriverManager.driver.findElement(By.xpath(locator));
		//Select the sortable column if it is not selected
		if (!sortUpDown.getAttribute("class").contains("up") || !sortUpDown.getAttribute("class").contains("down")) {
			CommonFunctions.click(LocatorType.Xpath, locator);
			CommonFunctions.waitUntilCompleteLoading();
			sortUpDown = DriverManager.driver.findElement(By.xpath(locator));
		}
		//Set the ascendent/descendent order according to the order provided
		if (!sortUpDown.getAttribute("class").contains(sortOrder)) {
			CommonFunctions.click(LocatorType.Xpath, locator);
		}
	}
	
	//Compare if the data are equals for the column Names of the grid view with the data obtained from the list view
	public boolean isDataColumnsEquals(List<WebElement> elements,	List<SortEntry> dataColumn, String column) {
		int index = 0;
		for (SortEntry entry: dataColumn) {
			if (elements.get(index).getText().trim().compareTo(entry.getAttribute(column)) != 0) {
				return false;
			}
			index++;
		}
		return true;
	}

	/*merge by virginia*/
	
	@When("^Set the items per page option \"([^\"]*)\"$")
	public void Set_the_items_per_page_option(String itemPerPage) throws Throwable {
		String itemPerPageLocator = "//ul[@class='content']/li[@class = '" + CommonFunctions.getClassActiveTab() + "']//div[@class='itemsPerPage']//div[text() = '" + itemPerPage + "']";
		WebElement elemLocator = DriverManager.driver.findElement(By.xpath(itemPerPageLocator));
		if (!elemLocator.getAttribute("class").contains("Active")) {
			CommonFunctions.click(LocatorType.Xpath, itemPerPageLocator);
		}
		CommonFunctions.waitUntilCompleteLoading();
	}
	
	@Given("^Validate there are \"([^\"]*)\" item\\(s\\) created$")
	public void Validate_there_are_item_s_created(String requiredItems) throws Throwable {
		initializeItemCreation();
		int numberRequiredItems = Integer.parseInt(requiredItems);
		if (numberRequiredItems > totalCreatedItems) {
			int number = numberRequiredItems - totalCreatedItems;
			addNewSfItems(number,"sf.item");
		}
	}
	
	//Search item by some criteria
	@When("^Search items by \"([^\"]*)\"$")
	public void Search_items_for(String text) throws Throwable {
		lastSearchKeyword = text;
		CommonFunctions.sleep(1000);
		CommonFunctions.clearAndSendKeys(By.cssSelector("input[id='ajaxSearchInput']"), lastSearchKeyword);
		CommonFunctions.pressKey("Enter");
		CommonFunctions.waitUntilCompleteLoading();

	}
	
	@Then("^Verify that none item is found$")
	public void Verify_that_none_item_is_found() throws Throwable {
		int itemsCount = CommonFunctions.getElementsCount(CommonFunctions.getItemNameLocatorOfActiveView());

		Assert.assertTrue(itemsCount == 0);
		CommonFunctions.sleep(3000);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div[@id = '" + CommonFunctions.getClassActiveTab() +"']//h3[./text()='Displaying 1 - 0 of 0']");
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//div[@id = '" + CommonFunctions.getClassActiveTab() +"']//p[./text()='No search results found']");
	}
	
	//Reset the searched results
	@Then("^Reset the search results$")
	public void Reset_the_search_results() throws Throwable {
		CommonFunctions.click(LocatorType.Xpath,"//a[contains(text(),'Reset')]");
		CommonFunctions.waitUntilCompleteLoading();
		
		//count the items displayed 
		int itemsCount = CommonFunctions.getElementsCount(CommonFunctions.getItemNameLocatorOfActiveView());
		Assert.assertEquals("The result after reseting the search is incorrect :" + defaultItemsCount +  " is different to " + itemsCount,
				            defaultItemsCount, itemsCount);
		
		//Verify the Displayed label
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h3[contains(text(),'Displaying 1 - " + defaultItemsCount + " of " + totalCreatedItems + "')]");
	}
	
	@Then("^Verify that \"([^\"]*)\" items are found$")
	public void Verify_that_items_are_found(int expectedItemsCount) throws Throwable {
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
	
	@Then("^Verify that \"([^\"]*)\" items are found using \"([^\"]*)\"$")
	public void Verify_that_items_are_found_using(String expectedItemsCount, String symbol) throws Throwable {
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
	
	@Then("^Verify that \"([^\"]*)\" items are found with content \"([^\"]*)\"$")
	public void Verify_that_items_are_found_with_content(String expectedItemsCount, String itemName) throws Throwable {
		int itemsCount = CommonFunctions.getElementsCount(By.xpath("//div[@class='title']//a[contains(text(),'" + itemName + "')]"));

		Assert.assertTrue(Integer.parseInt(expectedItemsCount) == itemsCount);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h3[contains(text(),'Displaying 1 - " + expectedItemsCount + " of " + expectedItemsCount + "')]");
	}
	
	@When("^Search (\\d+) item\\(s\\) with \"([^\"]*)\" item name$")
	public void Search_item_s_with_item_name(int numberOfItems, String itemName) throws Throwable {
		initializeItemCreation();
		Search_items_for(itemName);
		int matchedItem = CommonFunctions.getElementsCount(CommonFunctions.getItemNameLocatorOfActiveView());
		while (matchedItem != numberOfItems) {
			Reset_the_search_results();
			if (matchedItem > numberOfItems) {
				deleteItemsWithPrefix(matchedItem - numberOfItems, itemName);
			} else if (matchedItem < numberOfItems) {
				addNewSfItems(numberOfItems-matchedItem, itemName);
			}
			Search_items_for(itemName);
			matchedItem = CommonFunctions.getElementsCount(CommonFunctions.getItemNameLocatorOfActiveView());
		}
	}
	
	@Then("^Verify the \"([^\"]*)\" pagination bar displays \"([^\"]*)\" page$")
	public void Verify_the_pagination_bar_displays_page(String pagination, String pageCount) throws Throwable {
		if (pagination.equals("top")) {
			paginationOptionsList = CommonFunctions.getListOfPaginationOptions("header");
		} else {
			paginationOptionsList = CommonFunctions.getListOfPaginationOptions("footer");
		}
		
		Assert.assertTrue(paginationOptionsList.size() == 3);
		for (WebElement pageOption:paginationOptionsList) {
			String attributeClass = pageOption.getAttribute("class");
			if ((attributeClass.contains("previous")) || (attributeClass.contains("next"))) {
				Assert.assertTrue((attributeClass.contains("inactive")));
				Assert.assertTrue(!pageOption.isDisplayed());
			} else {
				Assert.assertTrue(pageOption.getText().equals(pageCount));
				Assert.assertTrue(pageOption.isDisplayed());
			}
		}
	}

	@Then("^Verify the pagination buttons are not visible on \"([^\"]*)\" pagination bar$")
	public void Verify_the_pagination_buttons_are_not_visible_on_pagination_bar(String pagination) throws Throwable {
		if (pagination.equals("top")) {
			paginationOptionsList = CommonFunctions.getListOfPaginationOptions("header");
		} else {
			paginationOptionsList = CommonFunctions.getListOfPaginationOptions("footer");
		}
		for (WebElement pageOption:paginationOptionsList) {
			
			Assert.assertTrue((pageOption.getAttribute("class").contains("inactive")));
			Assert.assertTrue(!pageOption.isDisplayed());
		}
	}
	
	@Then("^Verify the number of pages is correct on \"([^\"]*)\" pagination$")
	public void Verify_the_number_of_pages_is_correct_on_pagination(String pagination) throws Throwable {
		selectedItemPerPageOption = CommonFunctions.getActiveItemPerPageOption(); //initialize the selectItemPerPageOption
		totalNumberOfPagination = CommonFunctions.calculateNumberOfPagination(selectedItemPerPageOption , totalCreatedItems);
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

	@Then("^Verify the pagination works using number of page$")
	public void Verify_the_pagination_works_using_number_of_page() throws Throwable {
		String numberPaginationLocator;
		int counterItemPerPage;
		int rest = totalCreatedItems ;
		By activePaginationLocator = CommonFunctions.getActivePaginationOptionLocator(paginationBarPosition);
		for (int numberPagination = 1; numberPagination <= totalNumberOfPagination; numberPagination++) {
			numberPaginationLocator = CommonFunctions.getNumberPaginationOptionLocator(numberPagination, paginationBarPosition);
			CommonFunctions.clickOnPaginationNumberOption(activePaginationLocator, numberPaginationLocator);
			counterItemPerPage = CommonFunctions.getElementsCount(CommonFunctions.getItemNameLocatorOfActiveView());
			if (numberPagination < totalNumberOfPagination) {
				Assert.assertTrue(counterItemPerPage == selectedItemPerPageOption);
				rest = rest - selectedItemPerPageOption;
			} else {
				Assert.assertTrue(counterItemPerPage == rest);
			}
		}
	}
	
	@Then("^Verify the \"([^\"]*)\" icon is not displayed$")
	public void Verify_the_icon_is_not_displayed(String iconName) throws Throwable {
		String numberPaginationLocator;
		By iconLocator;
		By activePaginationLocator = CommonFunctions.getActivePaginationOptionLocator(paginationBarPosition);
		if (iconName.equals("previous")) {
			numberPaginationLocator = CommonFunctions.getNumberPaginationOptionLocator(1, paginationBarPosition);
			iconLocator =  CommonFunctions.getPreviousPaginationOptionLocator(paginationBarPosition);
		} else {
			numberPaginationLocator = CommonFunctions.getNumberPaginationOptionLocator(totalNumberOfPagination, paginationBarPosition);
			iconLocator =  CommonFunctions.getNextPaginationOptionLocator(paginationBarPosition);
		}
		WebElement iconNavigationElem = DriverManager.driver.findElement(iconLocator);
		CommonFunctions.clickOnPaginationNumberOption(activePaginationLocator, numberPaginationLocator);

		Assert.assertTrue(iconNavigationElem.getAttribute("class").contains("inactive"));
		Assert.assertTrue(!iconNavigationElem.isDisplayed());
	}

	@Then("^Verify the pagination works using previous$")
	public void Verify_the_pagination_works_using_previous() throws Throwable {
		int counterItemPerPage;
				
		By previousLocator = CommonFunctions.getPreviousPaginationOptionLocator(paginationBarPosition);
		WebElement previousElement = DriverManager.driver.findElement(CommonFunctions.getPreviousPaginationOptionLocator(paginationBarPosition));
		By activePaginationLocator = CommonFunctions.getActivePaginationOptionLocator(paginationBarPosition);
		int lastNumberOption = totalNumberOfPagination;
		String endNumberLocator = CommonFunctions.getNumberPaginationOptionLocator(lastNumberOption , paginationBarPosition);
		
		CommonFunctions.clickOnPaginationNumberOption(activePaginationLocator, endNumberLocator);
				
		Assert.assertTrue(previousElement.isDisplayed());

		for (int numberPagination = totalNumberOfPagination; numberPagination > 1; numberPagination--) {				
			CommonFunctions.click(previousLocator);
			CommonFunctions.waitUntilCompleteLoading();
			
			//get the number of files displayed
			counterItemPerPage = CommonFunctions.getElementsCount(CommonFunctions.getItemNameLocatorOfActiveView());;
			
			//Verify the number of files displayed is correct according to selectItePerPageOption
			Assert.assertTrue(counterItemPerPage == selectedItemPerPageOption);
		}
	}
	
	@Then("^Verify the pagination works using next$")
	public void Verify_the_pagination_works_using_next() throws Throwable {
		int counterItemPerPage;
		int rest = totalCreatedItems  - selectedItemPerPageOption;

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
				Assert.assertTrue(counterItemPerPage == selectedItemPerPageOption);
				rest = rest - selectedItemPerPageOption;
			} else {
				//Verify the number of files displayed is correct according to selectItePerPageOption
				Assert.assertTrue(counterItemPerPage == rest);
			}
		}
	}

	@Then("^Verify that \"([^\"]*)\" items are displayed in search view$")
	public void Verify_that_items_are_displayed_in_search_view(int expectedItemPerPage) throws Throwable {
	    int itemsCount = CommonFunctions.getElementsCount(CommonFunctions.getItemNameLocatorOfActiveView());
	    
	    Assert.assertTrue(itemsCount == expectedItemPerPage);
		VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h3[contains(text(),'Displaying 1 - " + itemsCount + " of " + totalCreatedItems + "')]");
	}
	
	/**
	 * Only delete all created item during the execution of feature that start with "sf.item" prefix
	 * */
	@Given("^Delete all created items$")
	public void Delete_all_created_items() throws Throwable {
		initializeItemCreation();
		deleteItemsWithPrefix(totalCreatedItemsWithPrefix, "sf.item");
	}
	
	/**
	 * Method to initialize the total number of all created items that there is after selecting "Items per page 45" option, 
	 * the total number of items created during automation execution (with sf.item prefix)and add their item names to a list for those(with sf.item prefix).
	 * 
	 * */
	private void initializeItemCreation() throws Throwable {
		By itemNameLocator = CommonFunctions.getItemNameLocatorOfActiveView();
		defaultItemsCount = CommonFunctions.getElementsCount(itemNameLocator);
		totalCreatedItems = CommonFunctions.getTotalCreateElements();
		createdItemNamesList = getListOfItemNameWithPrefix(itemNameLocator);
		if (!createdItemNamesList.isEmpty()) {
			totalCreatedItemsWithPrefix = createdItemNamesList.size();
		}
	}
	
	/**
	 * Method to add items for automated scenarios(add items with sf.item as prefix )
	 * @param numberRequiredItems
	 * @param prefix
	 * */
	private void addNewSfItems(int numberRequiredItems, String prefix) throws Throwable {
		String itemName = prefix;
		int id = totalCreatedItemsWithPrefix + 1;
		for (int i = 1; i <= numberRequiredItems; i++) { 
			if (prefix.equals("sf.item")) {
				itemName = prefix + id ;
			}
			//add the required items for the scenario
			Navigate_to_create_new_support_file_page();
			Create_support_file(suppFilesPath , suppFilesPath, itemName);
			id ++;
		}
		initializeItemCreation();
	}

	/**
	 * Method to delete the items created in the execution of automated scenarios(delete the item that has "sf.item" as prefix )
	 * @param numberOfItemsToDelete
	 * @param prefixName
	 * */
	private void deleteItemsWithPrefix(int numberOfItemsToDelete, String prefixName) throws Throwable {
		BrandAssets brandAssets = new BrandAssets();
		if (totalCreatedItemsWithPrefix > 0) {
			int numberOfDeletedItems = 0;
			String itemName = "";
			int itemIndex = totalCreatedItemsWithPrefix -1;
			while((numberOfItemsToDelete > numberOfDeletedItems) && (!createdItemNamesList.isEmpty())) {
				itemName = createdItemNamesList.get(itemIndex);				
				if (itemName.startsWith(prefixName)) {
					//delete the created item in the scenario, starting last item
					Navigate_to_support_file(itemName);
					brandAssets.the_item("Delete"); 
					CommonFunctions.sleep(3000); 
					createdItemNamesList.remove(itemIndex);
					this.totalCreatedItems--;
					this.totalCreatedItemsWithPrefix--;
					numberOfDeletedItems ++;
				}
				itemIndex--;
			}
			initializeItemCreation();
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
			
			if (elementItem.getText().startsWith("sf.item")) {
				itemNames.add(elementItem.getText());
			}
		}
		return itemNames;
	}
	
	@Given("^Add supporting files$")
	public void Add_supporting_files(DataTable dataTable) throws Throwable {
		List<Map<String, String>> maps;
		maps = dataTable.asMaps(String.class,String.class);

        for (Map<String, String> map: maps) {
            String itemname = map.get("itemname");
            int numberOfItems = Integer.valueOf(map.get("numberOfItems"));
            String filePath = map.get("file");
            addItemsUsingNameFileNumberOfItems(itemname, numberOfItems, filePath);

        }
        initializeItemCreation();
    }

    private void addItemsUsingNameFileNumberOfItems(String itemname, int numberOfItems, String filePath) throws Throwable {
    	for (int i = 1; i <= numberOfItems; i++) {
    		//add the required items for the scenario
    		String name = itemname;
    		Navigate_to_create_new_support_file_page();
    		if (name.contains("*")) {
    			name = itemname.replace("*", (i+""));
    		}
    		Create_support_file(filePath, filePath, name);
    	}
    	initializeItemCreation();
    }

    @Given("^Validate there are \"([^\"]*)\" with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void Validate_there_are_with_and(String number, String name, String file) throws Throwable {
        int numberItem = Integer.parseInt(number);
        initializeItemCreation();
        int matchCounter = 0;
        String nameToMatch = name;
        if (nameToMatch.contains("*")) {
            matchCounter = CommonFunctions.calculateNumberItemNameStartWithPrefix(name.replace("*", ""), createdItemNamesList);
        } else {
            matchCounter = CommonFunctions.calculateNumberItemNameEqualToPrefix(nameToMatch, createdItemNamesList);
        }

        if (matchCounter > numberItem) {
            deleteItemsWithPrefix(matchCounter  - numberItem, nameToMatch);
        } else if (matchCounter < numberItem) {
            addItemsUsingNameFileNumberOfItems(nameToMatch, numberItem, file);
        }
    }
}

