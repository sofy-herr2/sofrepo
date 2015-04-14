package onbrand.widgets;


import onbrand.CommonFunctions;
import onbrand.SupportingFile.SearchResultsComponent;
import onbrand.SupportingFile.SortEntry;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.Assert.*;

public class SearchResultsComponentTest {
    
    static WebDriver driver;
    private static WebElement listContainerElement;
    private static By listContainerLocator = By.id("listContainer");
    private static CommonFunctions.DriverHelperSingleton driverHelper = CommonFunctions.DriverHelperSingleton.getInstance();
    private SearchResultsComponent searchResultsComponent;

    @BeforeClass
    public static void setUpClass() {
        driver = new FirefoxDriver();
        driverHelper.setDriver(driver);
        driver.navigate().to("http://devpointuat.northplains.com/admin/brand_admin/files/supporting_files");
        driver.findElement(By.id("email")).sendKeys("jose.rioja@jalasoft.com");
        driver.findElement(By.id("password")).sendKeys("Testing1");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
    }

    @AfterClass
    public static void tearDownClass() {
        driver.close();
        driver.quit();
    }

    @Before
    public void setUp() {

    }

    private void navigateTo(String linkName) {
        System.out.println("Navigating to: " + linkName);
        By linkLocator = By.xpath("//*[@id='sub_nav']//li[not(contains(@class, 'active'))]");
        List<WebElement> anchorElements = driverHelper.getDriver().findElements(linkLocator);
        for (WebElement menuItemElement: anchorElements) {
            if (linkName.equals(menuItemElement.getText().trim())) {
                listContainerElement = driver.findElement(listContainerLocator);
                System.out.println("link found: " + linkName);
                anchorElements.get(0).click();
                driverHelper.waitFor(30).until(ExpectedConditions.stalenessOf(listContainerElement));
                break;
            }
        }
        driverHelper.waitFor(30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(listContainerLocator));
        listContainerElement = driver.findElement(listContainerLocator);
    }
    
    @Test
    public void testSearchResultsCanReadTheTableColumns() {
        navigateTo("Supporting Files");
        searchResultsComponent = new SearchResultsComponent(listContainerElement);

        searchResultsComponent.selectListView();

        assertTrue(searchResultsComponent.getColumns().toString(), searchResultsComponent.getColumns().size() >= 6);
        assertEquals("ID", searchResultsComponent.getColumns().get(0));
        assertTrue(searchResultsComponent.getColumns().indexOf("Last updated") > 0);
    }
    
    @Test
    public void testSearchResultsReturnAListOfSearchEntries() {
        navigateTo("Supporting Files");
        searchResultsComponent = new SearchResultsComponent(listContainerElement);

        searchResultsComponent.selectListView();

        assertEquals(15, searchResultsComponent.getTableData().size());
        SortEntry entry = searchResultsComponent.getTableData().get(0);

        assertFalse(entry.getAttribute("ID").length() == 0);
    }

    @Test
    public void testColumnsAreReadFromListView() {
        navigateTo("Supporting Files");
        searchResultsComponent = new SearchResultsComponent(listContainerElement);

        searchResultsComponent.selectListView();
        List<SortEntry> entries = searchResultsComponent.getColumnData("ID");

        assertEquals(searchResultsComponent.countItemsInCurrentPage(), entries.size());
        assertNotNull(entries.get(0).getAttribute("ID"));
    }

    @Test
    public void testToggleViewFromGridToList() {
        navigateTo("Supporting Files");
        searchResultsComponent = new SearchResultsComponent(listContainerElement);

        searchResultsComponent.selectListView();

        assertTrue(searchResultsComponent.viewIsInListMode());
        assertFalse(searchResultsComponent.viewIsInGridMode());
    }

    @Test
    public void testToggleToGridView() {
        navigateTo("Supporting Files");
        searchResultsComponent = new SearchResultsComponent(listContainerElement);

        searchResultsComponent.selectGridView();

        assertTrue(searchResultsComponent.viewIsInGridMode());
        assertFalse(searchResultsComponent.viewIsInListMode());
    }

    @Test
    public void testGettingTheCurrentSearchCriteria() {
        navigateTo("Supporting Files");
        searchResultsComponent = new SearchResultsComponent(listContainerElement);

        assertNotNull(searchResultsComponent.getSelectedSearchCriteria());
    }

    @Test
    public void testChangingTheSearchCriteria() {
        navigateTo("Supporting Files");
        searchResultsComponent = new SearchResultsComponent(listContainerElement);

        searchResultsComponent.selectSearchCriteria("Name");

        assertEquals("Name", searchResultsComponent.getSelectedSearchCriteria());
    }

    @Test
    public void testGettingSortOrder() {
        navigateTo("Supporting Files");
        searchResultsComponent = new SearchResultsComponent(listContainerElement);

        searchResultsComponent.selectListView();
        searchResultsComponent.selectSearchCriteria("Name");

        assertTrue(searchResultsComponent.isSortAscending() || !searchResultsComponent.isSortAscending());
    }

    @Test
    public void testChangeSortOrderToAscending() {
        navigateTo("Supporting Files");
        searchResultsComponent = new SearchResultsComponent(listContainerElement);

        searchResultsComponent.selectListView();
        searchResultsComponent.setSortAscending();

        assertTrue(searchResultsComponent.isSortAscending());
    }

    @Test
    public void testChangeSortOrderToDescending() {
        navigateTo("Supporting Files");
        searchResultsComponent = new SearchResultsComponent(listContainerElement);

        searchResultsComponent.selectListView();
        searchResultsComponent.setSortDescending();

        assertTrue(searchResultsComponent.isSortDescending());
    }

    @Test
    public void testGetTheListOfAvailableItemsPerPageOptions() {
        navigateTo("Supporting Files");
        searchResultsComponent = new SearchResultsComponent(listContainerElement);

        searchResultsComponent.selectListView();

        assertTrue(searchResultsComponent.getAvailableItemsPerPageOptions().size() > 0);
        assertEquals("5", searchResultsComponent.getAvailableItemsPerPageOptions().get(0));
    }

    @Test
    public void testGetCurrentItemsPerPageConfiguration() {
        navigateTo("Supporting Files");
        searchResultsComponent = new SearchResultsComponent(listContainerElement);

        searchResultsComponent.selectListView();
        int currentItemsPerPage = searchResultsComponent.getSelectedItemsPerPageOption();

        assertTrue(searchResultsComponent.countItemsInCurrentPage() <= currentItemsPerPage);
    }

    @Test
    public void testCountTheNumberOfItemsInCurrentPage() {
        navigateTo("Supporting Files");
        searchResultsComponent = new SearchResultsComponent(listContainerElement);

        searchResultsComponent.selectListView();
        assertTrue(searchResultsComponent.getAvailablePageOptions().size() > 0);

        assertTrue(searchResultsComponent.countItemsInCurrentPage() > 0);

        searchResultsComponent.selectGridView();
        assertTrue(searchResultsComponent.countItemsInCurrentPage() > 0);
    }

    @Test
    public void testChangeTheCurrentItemsPerPageConfigurationOnAGridView() {
        navigateTo("Supporting Files");
        searchResultsComponent = new SearchResultsComponent(listContainerElement);

        searchResultsComponent.selectGridView();

        searchResultsComponent.setItemsPerPage("5");
        int currentItemsPerPage = searchResultsComponent.countItemsInCurrentPage();
        assertEquals(5, searchResultsComponent.countItemsInCurrentPage());

        searchResultsComponent.setItemsPerPage("15");

        assertNotEquals(currentItemsPerPage, searchResultsComponent.getSelectedItemsPerPageOption());
        assertEquals(15, searchResultsComponent.getSelectedItemsPerPageOption());
        assertEquals(15, searchResultsComponent.countItemsInCurrentPage());
    }

    @Test
    public void testChangeTheCurrentItemsPerPageConfigurationOnAListView() {
        navigateTo("Supporting Files");
        searchResultsComponent = new SearchResultsComponent(listContainerElement);

        searchResultsComponent.selectListView();
        searchResultsComponent.goToPage("1");
        searchResultsComponent.setItemsPerPage("5");
        assertEquals(5, searchResultsComponent.countItemsInCurrentPage());

        int currentItemsPerPage = searchResultsComponent.getSelectedItemsPerPageOption();
        assertEquals(currentItemsPerPage, searchResultsComponent.countItemsInCurrentPage());
    }

    @Test
    public void testGettingTheTotalNumberOfItemsListed() {
        navigateTo("Supporting Files");
        searchResultsComponent = new SearchResultsComponent(listContainerElement);

        int totalItems = searchResultsComponent.getTotalNumberOfItems();

        assertTrue(totalItems >= 0);
    }

    @Test
    public void gettingNavigationPagesList() {
        navigateTo("Supporting Files");
        searchResultsComponent = new SearchResultsComponent(listContainerElement);

        List<String> pages = searchResultsComponent.getAvailablePageOptions();

        assertTrue(pages.size() >= 0);
    }

    @Test
    public void changeThePageOnGridViewMode() {
        navigateTo("Supporting Files");
        searchResultsComponent = new SearchResultsComponent(listContainerElement);

        searchResultsComponent.selectGridView();
        searchResultsComponent.setItemsPerPage("5");

        List<String> pages = searchResultsComponent.getAvailablePageOptions();
        int currentPageItemsCount = searchResultsComponent.countItemsInCurrentPage();

        String lastAvailablePage = pages.get(pages.size() - 1);
        searchResultsComponent.goToPage(lastAvailablePage);

        assertTrue(searchResultsComponent.countItemsInCurrentPage() <= currentPageItemsCount);
        assertTrue(searchResultsComponent.countItemsInCurrentPage() <= searchResultsComponent.getSelectedItemsPerPageOption());
    }

    @Test
    public void countItemsAcrossAllPagesShouldBeEqualToTheTotalAmountOfItemsOnGridViewMode() {
        navigateTo("Supporting Files");
        searchResultsComponent = new SearchResultsComponent(listContainerElement);

        searchResultsComponent.selectGridView();
        int totalAmountOfItems = searchResultsComponent.getTotalNumberOfItems();
        int itemsCounter = 0;

        for (String page: searchResultsComponent.getAvailablePageOptions()) {
            searchResultsComponent.goToPage(page);
            itemsCounter += searchResultsComponent.countItemsInCurrentPage();
        }

        assertEquals(totalAmountOfItems, itemsCounter);
    }

    @Test
    public void getCurrentlyActiveTab() {
        navigateTo("Supporting Files");
        searchResultsComponent = new SearchResultsComponent(listContainerElement);

        String tabLabel  =  searchResultsComponent.getActiveTab();

        assertEquals("Search", tabLabel);
    }

    @Test
    public void getCurrentActiveTabFromMultiTab()  {
        navigateTo("Brand Assets");

        WebElement listContainer = driverHelper.getDriver().findElement(By.id("listContainer"));

        SearchResultsComponent searchResultsComponent = new SearchResultsComponent(listContainer);

        assertNotNull(searchResultsComponent.getActiveTab());

    }

    @Test
    public void changeCurrentlyActiveTabAndCountTheItemsInCurrentPage() {
        navigateTo("Brand Assets");
        searchResultsComponent = new SearchResultsComponent(listContainerElement);

        searchResultsComponent.changeActiveTab("Draft");
        int itemsBeforeChangingTab = searchResultsComponent.countItemsInCurrentPage();

        assertEquals("Draft", searchResultsComponent.getActiveTab());

        searchResultsComponent.changeActiveTab("Awaiting Approval");
        assertEquals("Awaiting Approval", searchResultsComponent.getActiveTab());
        assertNotEquals(itemsBeforeChangingTab, searchResultsComponent.countItemsInCurrentPage());


    }


}
