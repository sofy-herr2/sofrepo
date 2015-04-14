package onbrand.tools;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.google.common.base.Predicate;

public class SearchResultsComponent {
    private static By sortDirectionToggleButtonLocator = By.xpath(".//*[@class='sortablePlugin']//*[contains(@class, 'sortable')]");

    private final By searchResultsFirstElementLocator = By.xpath("//*[@class='searchResults']/*[1]");
    protected WebElement rootElement;
    protected List<String> columns;
    protected DriverHelper driverHelper = DriverHelper.getInstance();

    //locators
    private final By resultsTableLocator = By.cssSelector(".searchResults table");
    private final By listColumnsLocator = By.cssSelector("thead tr th");
    private final By listRowsLocator = By.cssSelector("tbody tr");
    private final By searchCriteriaSelectLocator = By.xpath(".//*[@class='sortablePlugin']//select");
    private final By headerPaginationOptionsLocator = By.cssSelector("[class~=table_header] .pagination li");
    private final By itemsPerPageOptionsLocator = By.cssSelector(".itemsPerPage [class~=itemCount]");
    private final By searchResultsElementsLocator = By.cssSelector(".searchResults *");
    private final By listViewToggleButtonLocator = By.xpath(".//div[contains(@class, 'List')]");
    private final By gridViewToggleButtonLocator = By.xpath(".//div[contains(@class, 'Grid')]");
    private final By paginationBarLocator = By.cssSelector("[class~=pagination]");


    public SearchResultsComponent(WebElement resultsTableElement) {
        rootElement = resultsTableElement.findElement(By.xpath(".//li[contains(@style, 'block')]"));
    }

    public List<String> getColumns() {
        if (columns == null)
            initializeColumns();
        return columns;
    }

    private void initializeColumns() {
        waitForColumnsToLoad();

        columns = new ArrayList<>();

        for (WebElement column: rootElement.findElements(listColumnsLocator)) {
            columns.add(column.getText().trim());
        }
    }

    public List<SortEntry> getTableData() {
        List<SortEntry> rows = new ArrayList<>();
        List<String> columns = getColumns();

        for (WebElement row: rootElement.findElements(listRowsLocator)) {
            SortEntry entry = new SortEntry();
            List<WebElement> cells = row.findElements(By.cssSelector("td"));
            for (int index = 0; index < columns.size(); index ++) {
                entry.setAttribute(columns.get(index), cells.get(index).getText().trim());
            }

            rows.add(entry);
        }
        return rows;
    }

    public List<SortEntry> getColumnData(String column) {
        List<SortEntry> rows = new ArrayList<>();
        List<WebElement> tableRows = rootElement.findElements(listRowsLocator);
        List<String> columns = getColumns();
        int index = columns.indexOf(column);

        for (WebElement row: tableRows) {

            SortEntry entry = new SortEntry();
            List<WebElement> cells = row.findElements(By.cssSelector("td"));
            entry.setAttribute(columns.get(index), cells.get(index).getText().trim());
            rows.add(entry);
        }
        return rows;
    }

    public void selectListView() {
        if (viewIsInGridMode()) {

            rootElement.findElement(listViewToggleButtonLocator).click();

            driverHelper.waitFor(30).until(new Predicate<WebDriver>() {
                @Override
                public boolean apply(WebDriver webDriver) {
                    return viewIsInListMode();
                }
            });
        }
    }

    private void waitForColumnsToLoad() {
        driverHelper.waitFor(30).until(ExpectedConditions.presenceOfAllElementsLocatedBy(listColumnsLocator));
    }

    public boolean viewIsInListMode() {
        return rootElement.findElements(resultsTableLocator).size() > 0;
    }

    public boolean viewIsInGridMode() {
        return !viewIsInListMode();
    }

    public void selectGridView() {
        if (viewIsInListMode()) {
            WebElement searchResults = rootElement.findElement(searchResultsElementsLocator);
            rootElement.findElement(gridViewToggleButtonLocator).click();
            driverHelper.waitFor(30).until(new Predicate<WebDriver>() {
                @Override
                public boolean apply(WebDriver webDriver) {
                    return viewIsInGridMode();
                }
            });
        }
    }

    public String getSelectedSearchCriteria() {
        Select searchSelect = new Select(rootElement.findElement(searchCriteriaSelectLocator));
        return searchSelect.getFirstSelectedOption().getText();
    }


    public void selectSearchCriteria(String criteria) {
        if (criteria.equals(getSelectedSearchCriteria())) {
            return;
        }
        Select searchSelect = new Select(rootElement.findElement(searchCriteriaSelectLocator));
        WebElement column = rootElement.findElement(searchResultsElementsLocator);
        searchSelect.selectByVisibleText(criteria);
        driverHelper.waitFor(10).until(ExpectedConditions.stalenessOf(column));
    }

    public boolean isSortAscending() {
        WebElement toggleSortDirectionButton = rootElement.findElement(sortDirectionToggleButtonLocator);
        return toggleSortDirectionButton.getAttribute("class").trim().endsWith("up");
    }

    public void setSortAscending() {
        if (!isSortAscending()) {
            rootElement.findElement(sortDirectionToggleButtonLocator).click();
        }
    }

    public void setSortDescending() {
        if (isSortAscending()) {
            rootElement.findElement(sortDirectionToggleButtonLocator).click();
        }
    }

    public boolean isSortDescending() {
        return !isSortAscending();
    }

    public List<String> getAvailableItemsPerPageOptions() {
        List<String> availableItemsPerPageOptions = new ArrayList<>();
        List<WebElement> itemCountElements = rootElement.findElements(this.itemsPerPageOptionsLocator);
        for (WebElement itemCount: itemCountElements) {
            availableItemsPerPageOptions.add(itemCount.getText());
        }

        return availableItemsPerPageOptions;
    }

    public void setItemsPerPage(String itemsPerPage) {
        for (WebElement webElement: rootElement.findElements(itemsPerPageOptionsLocator)) {
            if (webElement.getText().equals(itemsPerPage)) {
                WebElement contentItem = rootElement.findElement(searchResultsFirstElementLocator);
                webElement.click();
                driverHelper.waitFor(10).until(ExpectedConditions.stalenessOf(contentItem));
                break;
            }
        }
    }


    public int countItemsInCurrentPage() {
        By itemsSelector = getPageItemCountLocator();
        return rootElement.findElements(itemsSelector).size();
    }

    private By getPageItemCountLocator() {
        By itemsSelector = By.cssSelector(".assets [class~=item]");
        if (viewIsInListMode()) {
            itemsSelector = By.cssSelector("table tbody tr");
        }
        return itemsSelector;
    }

    public int getSelectedItemsPerPageOption() {
        return Integer.valueOf(rootElement.findElement(By.cssSelector(".itemsPerPage [class~=countActive]")).getText());
    }

    public int getTotalNumberOfItems() {
        WebElement propertiesElement = rootElement.findElement(By.cssSelector("[class=PropertiesPlugin]"));
        String propertiesLabel = propertiesElement.getText().trim();

        return Integer.valueOf(propertiesLabel.split(".*\\d+\\s*-\\s*\\d+\\s+\\S+\\s+")[1]);
    }

    public List<String> getAvailablePageOptions() {
        ArrayList<String> availablePages = new ArrayList<>();
        List<WebElement> optionElements = rootElement.findElements(headerPaginationOptionsLocator);
        for (WebElement option: optionElements) {
            if (option.getText().matches("\\d")) {
                availablePages.add(option.getText());
            }
        }
        return availablePages;
    }

    public void goToPage(String page) {
        WebElement paginationBar = rootElement.findElement(paginationBarLocator);
        final By pageButtonLocator = By.xpath(".//*[contains(@class, 'pagination')]//*[text()='" + page + "']");
        driverHelper.waitFor(20).until(new Predicate<WebDriver>() {
            @Override
            public boolean apply(WebDriver webDriver) {
                try {
                    WebElement pageButton = rootElement.findElement(pageButtonLocator);
                    pageButton.click();
                    return true;
                }
                catch (Exception e) {
                    return false;
                }
            }
        });

        driverHelper.waitFor(10).until(ExpectedConditions.stalenessOf(paginationBar));
    }

    public String getActiveTab() {
        WebElement tabElement = driverHelper.getDriver().findElement(By.cssSelector(".nav [class~=active]"));
        return tabElement.getText();
    }

    public void changeActiveTab(String tabLabel) {
        WebElement tabElement = driverHelper.getDriver().findElement(By.xpath(".//*[@class='nav']//li[text()='" + tabLabel + "']"));
        String tabClassName = tabElement.getAttribute("class");

        if (tabClassName.contains("active")) {
            return;
        }

        tabElement.click();

        rootElement = driverHelper.getDriver().findElement(By.cssSelector(".content ." + tabClassName.trim()));
        driverHelper.waitFor(10).until(ExpectedConditions.elementToBeClickable(getPageItemCountLocator()));
    }
}
