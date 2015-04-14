package onbrand;

import onbrand.tools.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class Articles {
	


@Then("^Verify Brand tab in CMS$")
public void Verify_Brand_tab_in_CMS() throws Throwable {
    // Express the Regexp above with the code you wish you had
	VerificationFunctions.verifyURL("/admin/brand_admin/brand/articles/"); 
	//Verify Search Field, Search and Reset buttons
	VerificationFunctions.verifyElementVisible( LocatorType.id, "ajaxSearchInput" );
	VerificationFunctions.verifyElementVisible( LocatorType.id, "ajaxSearchButton" );
	VerificationFunctions.verifyElementVisible( LocatorType.LinkedText, "Reset" );

	//Verify Admin Page, Article Tabs, Sort Droplist, Items Per page and Feedback Link
	VerificationFunctions.verifyElementVisible( LocatorType.className, "all_articles_state" );
	VerificationFunctions.verifyElementVisible( LocatorType.className, "published_state" );
	VerificationFunctions.verifyElementVisible( LocatorType.className, "unassigned_state" );
	VerificationFunctions.verifyElementVisible( LocatorType.className, "archived_state" );
	VerificationFunctions.verifyElementVisible( LocatorType.className, "itemsPerPage" );
	VerificationFunctions.verifyElementVisible( LocatorType.className, "sortBar" );
}


@Given("^User is in Brand_CMS$")
public void User_is_in_Brand_CMS() throws Throwable {
	VerificationFunctions.verifyURL("/admin/brand_admin/brand/articles/");
}



@Then("^Add parent section \"([^\"]*)\"$")
public void Add_parentsection(String parentSection) throws Throwable {
	VerificationFunctions.verifyElementVisible( LocatorType.Xpath, "//li[contains(@class,'jstree-last')]//a" );
	CommonFunctions.rightClick(LocatorType.Xpath, "//li[contains(@class,'jstree-last')]//a");
	CommonFunctions.click(LocatorType.LinkedText, "Add new Child Section");
	CommonFunctions.setText(LocatorType.Xpath, "//input[@class='jstree-rename-input']", parentSection);
	Navigation.refreshPage();
	VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'"+parentSection+"')]");
}

@Then("^Add child section \"([^\"]*)\" \"([^\"]*)\"$")
public void Add_childsection(String parentSection, String childSection) throws Throwable {
	VerificationFunctions.verifyElementVisible( LocatorType.Xpath, "//a[contains(text(),'"+parentSection+"')]" );
	CommonFunctions.rightClick(LocatorType.Xpath, "//a[contains(text(),'"+parentSection+"')]");
	CommonFunctions.click(LocatorType.LinkedText, "Add new Child Section");
	CommonFunctions.setText(LocatorType.Xpath, "//input[@class='jstree-rename-input']", childSection);
	Navigation.refreshPage();
	VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'"+childSection+"')]");
}

@Then("^Verify section \"([^\"]*)\"$")
public void Verify_section(String parentSection) throws Throwable {
	VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'"+parentSection+"')]");
   
}

@When("^Rename section \"([^\"]*)\" to \"([^\"]*)\"$")
public void Rename_section(String parentSection,String parentSectionUpdated) throws Throwable {
	
	CommonFunctions.rightClick(LocatorType.Xpath, "//a[contains(text(),'"+parentSection+"')]");
	CommonFunctions.click(LocatorType.LinkedText, "Rename");
	CommonFunctions.setText(LocatorType.Xpath, "//input[@class='jstree-rename-input']", parentSectionUpdated);
	Navigation.refreshPage();
	VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'"+parentSectionUpdated+"')]");
}


@When("^Delete section \"([^\"]*)\"$")
public void Delete_section(String parentSection) throws Throwable {
	
	CommonFunctions.rightClick(LocatorType.Xpath, "//a[contains(text(),'"+parentSection+"')]");
	CommonFunctions.click(LocatorType.LinkedText, "Remove Section");
	CommonFunctions.confirmAlert("OK");
	Navigation.refreshPage();
	//verify it is not available
	VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath, "//a[contains(text(),'"+parentSection+"')]");
   
}


@Then("^Verify the new child section \"([^\"]*)\" does not exist$")
public void Verify_no_childsection(String parentSection) throws Throwable {
	VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath, "//a[contains(text(),'"+parentSection+"')]"); 
}

@When("^Verify article's mandatory fields$")
public void mandatory_fields_article() throws Throwable {
	
	CommonFunctions.click(LocatorType.LinkedText, "Create New Article");
	//Verify Mandatory Fields
	CommonFunctions.click(LocatorType.LinkedText, "Save");
	VerificationFunctions.verifyText(LocatorType.className, "error", "textContent","This field is required.");
    
}



@When("^Click create new article button$")
public void Click_create_new_article_button() throws Throwable {
	Navigation.gotoURL("/admin/brand_admin/brand/articles/"); 
	CommonFunctions.click(LocatorType.LinkedText, "Create New Article");

}

@When("^Create article \"([^\"]*)\"$")
public void Creates_unassigned_article(String articleName) throws Throwable {
	//Goto Article Section and Create Article
			
			//CommonFunctions.click(LocatorType.LinkedText, "Create New Article");

		
			//Enter Article Name and Save
			CommonFunctions.clearAndSendKeys(By.id("item_name"), articleName);
			
			CommonFunctions.click(LocatorType.LinkedText, "Save");
			CommonFunctions.waitForElement(LocatorType.className, "unassigned_state", 15);

			CommonFunctions.click(LocatorType.className, "unassigned_state");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, articleName);
			CommonFunctions.click (LocatorType.LinkedText, articleName);
			
			// click on the Core Info tab
			CommonFunctions.click(LocatorType.Xpath, "//span[text()='Core Info']");
			VerificationFunctions.verifyText(LocatorType.id, "title","textContent", articleName);
			VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath, "//label[text()='Workflow Status']");
			VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath, "//span[contains(node(),'Published')]");
			
			//buutons/actions available
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Delete");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Cancel");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Archive");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Save");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Publish");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Publish and Email Watchers");
			
    
}

@When("^Create article \"([^\"]*)\" in \"([^\"]*)\"$")
public void Create_article_in(String articleName, String parentSection) throws Throwable {
	//Goto Article Section and Create Article
	Navigation.goToCMS();
	CommonFunctions.click(LocatorType.LinkedText, "Create New Article");

	
	//Enter Article Name and Save
	CommonFunctions.clearAndSendKeys(By.id("item_name"), articleName);
	CommonFunctions.selectDropList("//select", parentSection);
	CommonFunctions.click(LocatorType.LinkedText, "Publish");
	CommonFunctions.waitForElement(LocatorType.className, "published_state", 5);

	CommonFunctions.click(LocatorType.className, "published_state");
	VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, articleName);
	CommonFunctions.click (LocatorType.LinkedText, articleName);
	
	// click on the Core Info tab
	CommonFunctions.click(LocatorType.Xpath, "//span[text()='Core Info']");
	CommonFunctions.browserWait(20L);
	CommonFunctions.waitForElement(LocatorType.Xpath, "//li/input[@name='item_name']", 10);
	VerificationFunctions.verifyText(LocatorType.Xpath, "//li/input[@name='item_name']","value", articleName);
	//CommonFunctions.setText(LocatorType.Xpath, "//input[@class='jstree-rename-input']", parentSection);
	VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[text()='Workflow Status']");
	VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//span[contains(node(),'Published')]");

}

@When("^Verify article tree \"([^\"]*)\"  \"([^\"]*)\"$")
public void Verify_article_tree(String section, String article) throws Throwable {
	VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//li[a[contains(text(),'"+section+"')]]/ul/li[a[contains(text(),'"+article+"')]]");
	
}

@Then("^Delete article \"([^\"]*)\"$")
public void Delete_article(String article){

	Navigation.gotoURL("/admin/brand_admin/brand/articles/"); 
	CommonFunctions.click( LocatorType.LinkedText, article);
	String artilceID = CommonFunctions.getItemID();
	VerificationFunctions.verifyElementVisible( LocatorType.LinkedText,"Delete");
	CommonFunctions.click( LocatorType.LinkedText,"Delete");
	CommonFunctions.confirmAlert("OK");
	VerificationFunctions.verifyElementNotVisible( LocatorType.LinkedText,article);

	//Navigate to Article and Confirm Deletion
	Navigation.gotoURL("/admin/brand_admin/brand/articles/edit/"+artilceID);
	//this assertion fails and need to look into that
	//VerificationFunctions.verifyPageTitle("404 - Page not found");
	VerificationFunctions.verifyText(LocatorType.Xpath, "//h2","textContent", "We could not find that page");
}


@When("^Create chapter \"([^\"]*)\" in article \"([^\"]*)\"$")
public void Create_chapter_in_article(String chapterName, String article) throws Throwable {
	//Goto Article Section and Create Article
			
			CommonFunctions.click(LocatorType.LinkedText, article);
			String artilceID = CommonFunctions.getItemID();

			//UI of Article page ,Verify Article name, Tabs, Comments, Begin Sorting, Create new chapter
			VerificationFunctions.verifyElementVisible( LocatorType.Xpath, "//span[.='"+article+"']");
			VerificationFunctions.verifyElementVisible( LocatorType.Xpath, "//span[.='Core Info']");
			VerificationFunctions.verifyElementVisible( LocatorType.Xpath, "//span[.='Chapters']");
			VerificationFunctions.verifyElementVisible( LocatorType.Xpath, "//span[.='Comments']");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Begin Sorting");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Create new chapter");

			//Verify Delete, cancel, archive, save and Publish Buttons
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Delete");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Cancel");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Archive");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Save");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Publish and Email Watchers");

			//Goto Chapters Verify mandatory Fields and Create Chapter
			//go to Chapters tab
			CommonFunctions.click(LocatorType.Xpath, "//span[.='Chapters']");
			CommonFunctions.click(LocatorType.LinkedText, "Create new chapter");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Save changes");

			//Verify Mandatory Fields
			CommonFunctions.click(LocatorType.LinkedText, "Save changes");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//dfn[.='This field is required.']");


			//Verify Chapter Labels and Fields
			VerificationFunctions.verifyElementVisible( LocatorType.Xpath, "//label[.='Headline']");
			VerificationFunctions.verifyElementVisible( LocatorType.id, "embedded_name");
			VerificationFunctions.verifyElementVisible( LocatorType.Xpath, "//label[.='Hide headline']");
			VerificationFunctions.verifyElementVisible( LocatorType.Xpath, "//label[.='Hide headline']/following::input[contains(@id,'embedded')]");
			VerificationFunctions.verifyElementVisible( LocatorType.Xpath, "//iframe[contains(@id,'embedded')]");

			//Verify Media Styles and Positions
			VerificationFunctions.verifyElementVisible( LocatorType.Xpath, "//Label[.='Media style']");
			VerificationFunctions.verifyElementVisible( LocatorType.Xpath, "//Option[.='Stacked']");
			VerificationFunctions.verifyElementVisible( LocatorType.Xpath, "//Option[.='Slideshow']");
			VerificationFunctions.verifyElementVisible( LocatorType.Xpath, "//Label[.='Media position']");
			VerificationFunctions.verifyElementVisible( LocatorType.Xpath, "//Option[.='Top']");
			VerificationFunctions.verifyElementVisible( LocatorType.Xpath, "//Option[.='Left']");
			VerificationFunctions.verifyElementVisible( LocatorType.Xpath, "//Option[.='Right']");
			VerificationFunctions.verifyElementVisible( LocatorType.Xpath, "//Option[.='Bottom']");
			VerificationFunctions.verifyElementVisible( LocatorType.Xpath, "//Label[.='Linked media']");
			VerificationFunctions.verifyElementVisible( LocatorType.Xpath, "//Label[.='Attached Files']");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Add Media");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Add Attached Files");
			VerificationFunctions.verifyElementVisible(LocatorType.className, "feedback");


			//Enter Chapter name and Save Changes
			CommonFunctions.clearAndSendKeys(By.id("embedded_name"), chapterName);
			CommonFunctions.click(LocatorType.LinkedText, "Save changes");
			//verify the actions available
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Delete");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Cancel");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Archive");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Save");
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Publish and Email Watchers");
			
			//take the action
			CommonFunctions.click(LocatorType.LinkedText, "Publish and Email Watchers");
			
			


			//Verify Chapter in Front end
			//Navigation.gotoURL("/brand/article/"+artilceID);
			//VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h1[text()='"+article+"']");
			//VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h2[text()='"+chapterName+"']");
			//VerificationFunctions.verifyElementVisible( LocatorType.LinkedText,"edit");
			//VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Input[@value='Add to Watchlist']");
}


@When("^User Creates a new article \"([^\"]*)\" in \"([^\"]*)\" in popup menu$")
public void Create_new_article_in_popup_menu(String articleName, String childSection) throws Throwable {
	
	
	CommonFunctions.rightClick(LocatorType.Xpath, "//a[contains(text(),'"+childSection+"')]");
	
	
	//right click on the childsection
	CommonFunctions.click(LocatorType.LinkedText, "Create New Article");

	

	//Enter Article Name and Save
	CommonFunctions.clearAndSendKeys(By.id("item_name"), articleName);
	CommonFunctions.selectDropList("//select", "");
	CommonFunctions.click(LocatorType.LinkedText, "Publish");
	CommonFunctions.waitForElement(LocatorType.className, "published_state", 5);

	CommonFunctions.click(LocatorType.className, "published_state");
	VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, articleName);
	CommonFunctions.click (LocatorType.LinkedText, articleName);
	
	// click on the Core Info tab
	CommonFunctions.click(LocatorType.Xpath, "//span[text()='Core Info']");
	VerificationFunctions.verifyText(LocatorType.id, "title", "textContent", articleName);
	VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//label[text()='Workflow Status']");
	VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//span[contains(node(),'Published')]");
}


@When("^Invokes popup menu on section \"([^\"]*)\" and click on \"([^\"]*)\"$")
public void Invokes_popup_menu_on_section_and_click_on(String childSection, String action) throws Throwable {
     CommonFunctions.rightClick(LocatorType.Xpath, "//a[contains(text(),'"+childSection+"')]");
	//right click on the childSection
	CommonFunctions.click(LocatorType.LinkedText, action);
}

@When("^Expand the section to see \"([^\"]*)\"$")
public void Expand_section(String section) throws Throwable {
	
	VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//a[contains(text(),'"+section+"')]");
	//expand the tree
	String classname = CommonFunctions.getAttributeValue(By.xpath("//a[contains(text(),'sub1')]/parent::li"), "class");
	if(classname.contains("close")){
		CommonFunctions.click(LocatorType.className, classname );
	}

}


@When("^Edit chapter body \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
public void Edit_chapter_body(String chapter, String article, String body) throws Throwable {
	
	Navigation.gotoURL("/admin/brand_admin/brand/articles/"); 
	CommonFunctions.click( LocatorType.LinkedText, article);

	//Edit Chapter
	CommonFunctions.click(LocatorType.Xpath, "//span[.='Chapters']");
	CommonFunctions.click( LocatorType.Xpath, "//h2[text()='"+chapter+"']//a[.='Edit']" );

	WebElement frame = DriverManager.driver.findElement(By.xpath("//iframe[contains(@id,'embedded')]"));
	DriverManager.driver.switchTo().frame(frame);
	CommonFunctions.clearAndSendKeys( By.tagName("body"), body );
	Navigation.switchtoDefaultFrame();
	VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Save changes");
	CommonFunctions.click( LocatorType.LinkedText, "Save changes" );
	CommonFunctions.click( LocatorType.LinkedText, "Save" );
    
}


@When("^Add media files \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
public void Add_media_files(String chapter, String article, String media) throws Throwable {
	
	
			Navigation.gotoURL("/admin/brand_admin/brand/articles/"); 
			CommonFunctions.click( LocatorType.LinkedText, article);
			String artilceID = CommonFunctions.getItemID();

			//Edit Chapter
			CommonFunctions.click(LocatorType.Xpath, "//span[.='Chapters']");
			CommonFunctions.click( LocatorType.Xpath, "//h2[text()='"+chapter+"']//a[.='Edit']" );

			//Add Media
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Add Media");
			CommonFunctions.click(LocatorType.LinkedText, "Add Media");

			//Verify Add Media Popup, Search 
			VerificationFunctions.verifyElementVisible( LocatorType.id, "ajaxSearchInput");
			VerificationFunctions.verifyElementVisible( LocatorType.LinkedText,"Search");
			VerificationFunctions.verifyElementVisible( LocatorType.LinkedText,"Reset");
			VerificationFunctions.verifyElementVisible( LocatorType.LinkedText,"New");
			VerificationFunctions.verifyElementVisible( LocatorType.LinkedText,"Finish");
			CommonFunctions.clearAndSendKeys(By.id("ajaxSearchInput"), media);
			CommonFunctions.click( LocatorType.LinkedText,"Search");
			//wait for few seconds for ajax search otherwise clicking on the results will fail
			CommonFunctions.sleep(3000);
			CommonFunctions.click( LocatorType.LinkedText, media);
			
			
			
			//CommonFunctions.click(LocatorType.Xpath, "//a[.='asd']");
			CommonFunctions.click( LocatorType.LinkedText,"Finish");
			CommonFunctions.click( LocatorType.LinkedText, "Save changes" );
			CommonFunctions.click( LocatorType.LinkedText, "Save" );

			//Verify Added Media in Front-end
			Navigation.gotoURL("/brand/article/"+artilceID);
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h1[text()='"+article+"']");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h2[text()='"+chapter+"']");
			VerificationFunctions.verifyElementVisible( LocatorType.LinkedText,"edit");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Input[@value='Add to Watchlist']");
			VerificationFunctions.verifyElementVisible( LocatorType.TagName,"img");
  
}


@When("^Edit media position and style \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
public void Edit_media_position_and_style(String chapter, String article, String position, String style) throws Throwable {
	//Goto an Article Chapter
	        Navigation.gotoURL("/admin/brand_admin/brand/articles/"); 
			
	        CommonFunctions.click( LocatorType.LinkedText, article);
			String artilceID = CommonFunctions.getItemID();

			//Edit Chapter
			CommonFunctions.click(LocatorType.Xpath, "//span[.='Chapters']");
			CommonFunctions.click( LocatorType.Xpath, "//h2[text()='"+chapter+"']//a[.='Edit']" );

			//Select Media Style and Position 
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Option[.='"+style+"']");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Option[.='"+position+"']");
			CommonFunctions.click(LocatorType.Xpath, "//Option[.='"+style+"']");
			CommonFunctions.click(LocatorType.Xpath, "//Option[.='"+position+"']");
			CommonFunctions.click( LocatorType.LinkedText, "Save changes" );
			CommonFunctions.click( LocatorType.LinkedText, "Save" );

			//Verify Modifications in Front-end
			Navigation.gotoURL("/brand/article/"+artilceID);
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h1[text()='"+article+"']");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h2[text()='"+chapter+"']");
			VerificationFunctions.verifyElementVisible( LocatorType.LinkedText,"edit");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Input[@value='Add to Watchlist']");

			if(style.equals("Stacked")){

				switch(position){

				case "Left":
					VerificationFunctions.verifyElementVisible( LocatorType.Xpath,"//div[contains(@class,'left')]");
					break;
				case "Right":
					VerificationFunctions.verifyElementVisible( LocatorType.Xpath,"//div[contains(@class,'right')]");
					break;
				case "Top":
					VerificationFunctions.verifyElementVisible( LocatorType.Xpath,"//div[contains(@class,'full')]");
					break;	
				case "Bottom":
					VerificationFunctions.verifyElementVisible( LocatorType.Xpath,"//div[contains(@class,'full')]");
					break;
				}
			}	

			else if(style.equals("Slideshow")){

				switch(position){

				case "Left":
					VerificationFunctions.verifyElementVisible( LocatorType.Xpath,"//div[contains(@class,'slider left')]");
					break;
				case "Right":
					VerificationFunctions.verifyElementVisible( LocatorType.Xpath,"//div[contains(@class,'slider right')]");
					break;
				case "Top":
					VerificationFunctions.verifyElementVisible( LocatorType.Xpath,"//div[contains(@class,'slider full')]");
					break;
				case "Bottom":
					VerificationFunctions.verifyElementVisible( LocatorType.Xpath,"//div[contains(@class,'slider full')]");
					break;
				}
			}
			
}		
			
@When("^Add attached files \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
public void Add_attached_files(String chapter, String article, String media) throws Throwable {			
			
			//Goto an Article Chapter
		
	        Navigation.gotoURL("/admin/brand_admin/brand/articles/");
			CommonFunctions.click( LocatorType.LinkedText, article);
			String artilceID = CommonFunctions.getItemID();

			//Edit Chapter
			CommonFunctions.click(LocatorType.Xpath, "//span[.='Chapters']");
			CommonFunctions.click( LocatorType.Xpath, "//h2[text()='"+chapter+"']//a[.='Edit']" );

			//Add Media
			VerificationFunctions.verifyElementVisible(LocatorType.LinkedText, "Add Attached Files");
			CommonFunctions.click(LocatorType.LinkedText, "Add Attached Files");

			//Verify Add Media Popup, Search 
			VerificationFunctions.verifyElementVisible( LocatorType.id, "ajaxSearchInput");
			VerificationFunctions.verifyElementVisible( LocatorType.LinkedText,"Search");
			VerificationFunctions.verifyElementVisible( LocatorType.LinkedText,"Reset");
			VerificationFunctions.verifyElementVisible( LocatorType.LinkedText,"Finish");
			CommonFunctions.clearAndSendKeys(By.id("ajaxSearchInput"), media);
			CommonFunctions.click( LocatorType.LinkedText,"Search");
			CommonFunctions.sleep(3000);
			CommonFunctions.click( LocatorType.LinkedText,media);
			CommonFunctions.click( LocatorType.LinkedText,"Finish");
			CommonFunctions.click( LocatorType.LinkedText, "Save changes" );
			CommonFunctions.click( LocatorType.LinkedText, "Save" );

			//Verify Added Media in Front-end
			Navigation.gotoURL("/brand/article/"+artilceID);
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h1[text()='"+article+"']");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h2[text()='"+chapter+"']");
			VerificationFunctions.verifyElementVisible( LocatorType.LinkedText,"edit");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//Input[@value='Add to Watchlist']");
			VerificationFunctions.verifyElementVisible(LocatorType.Xpath, "//h4[contains(text(),'Attached')]");
			VerificationFunctions.verifyElementVisible( LocatorType.LinkedText,media);
			VerificationFunctions.verifyElementVisible( LocatorType.LinkedText,"download");
			VerificationFunctions.verifyElementVisible( LocatorType.LinkedText,"Feedback");

}




@When("^Remove media files \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
public void Remove_media_files(String chapter, String article, String media) throws Throwable {

	//Goto an Article Chapter
	Navigation.gotoURL("/admin/brand_admin/brand/articles/");
	CommonFunctions.click( LocatorType.LinkedText, article);

	//Edit Chapter
	CommonFunctions.click(LocatorType.Xpath, "//span[.='Chapters']");
	CommonFunctions.click( LocatorType.Xpath, "//h2[text()='"+chapter+"']//a[.='Edit']" );

	//Remove Added Media
	VerificationFunctions.verifyElementVisible( LocatorType.className, "remove");
	CommonFunctions.click( LocatorType.Xpath,"//li[label[text() = 'Linked media']]//ul/li/div[@class = 'meta'][.//div[contains(text(), '"+media+"')]]/a");
	CommonFunctions.click( LocatorType.LinkedText, "Save changes" );
	VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath, "//div[@class='title'][contains(text(),'"+media+"')]");
	CommonFunctions.click( LocatorType.LinkedText, "Save" );

}


@When("^Remove attached files \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
public void Remove_attached_files(String chapter, String article, String media) throws Throwable {

	//Goto an Article Chapter
		Navigation.gotoURL("/admin/brand_admin/brand/articles/");
		CommonFunctions.click( LocatorType.LinkedText, article);

		//Edit Chapter
		CommonFunctions.click(LocatorType.Xpath, "//span[.='Chapters']");
		CommonFunctions.click( LocatorType.Xpath, "//h2[text()='"+chapter+"']//a[.='Edit']" );

		//Remove Added Media
		VerificationFunctions.verifyElementVisible( LocatorType.className, "remove");
		CommonFunctions.click( LocatorType.Xpath,"//li[label[text() = 'Attached Files']]//ul/li/div[@class = 'meta'][.//div[contains(text(), '"+media+"')]]/a");
		CommonFunctions.click( LocatorType.LinkedText, "Save changes" );
		VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath, "//div[@class='title'][contains(text(),'"+media+"')]");
		CommonFunctions.click( LocatorType.LinkedText, "Save" );


}


@When("^Remove chapter \"([^\"]*)\" \"([^\"]*)\"$")
public void Remove_chapter(String chapter, String article) throws Throwable {
	
		Navigation.gotoURL("/admin/brand_admin/brand/articles/");
		
		CommonFunctions.click( LocatorType.LinkedText, article);

		//Edit Chapter
		CommonFunctions.click(LocatorType.Xpath, "//span[.='Chapters']");
		CommonFunctions.click( LocatorType.Xpath, "//h2[text()='"+chapter+"']//a[.='Edit']" );

		//Remove Chapter and Save
		VerificationFunctions.verifyElementVisible( LocatorType.LinkedText, "Remove Chapter");
		CommonFunctions.click( LocatorType.LinkedText, "Remove Chapter");
		CommonFunctions.confirmAlert("OK");
		VerificationFunctions.verifyElementNotVisible( LocatorType.Xpath,"//h2[@class='legend'][contains(text(),'"+chapter+"')]");
		CommonFunctions.click( LocatorType.LinkedText, "Save" );
		CommonFunctions.waitForElement(LocatorType.className, "all_articles_state", 5);

}


@When("^Verify brand menu \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")

public void Verify_brand_menu(String menu, String submenu, String article1, String article2, String article3) throws Throwable {
	
		Navigation.gotoURL("/brand/article/");
		VerificationFunctions.verifyElementVisible( LocatorType.Xpath,"//ul/li/a[contains(text(), '"+menu+"')]");
		CommonFunctions.click( LocatorType.Xpath, "//ul/li/a[contains(text(), '"+menu+"')]" );
		
		
		if (submenu.equals("")){
		//Do nothing
			System.out.println("Inside submenu------");
		}
		
		else if (article1.equals("")){
			System.out.println("Inside article1------");
		//verify the left hand side submenu
		VerificationFunctions.verifyElementVisible( LocatorType.Xpath,"//ul[@class='articleNav']/li/a[contains(text(), '"+submenu+"')]");
	
				
		}
		else if (article2.equals("")){
			System.out.println("Inside article2------");
			//verify the left hand side submenu
			VerificationFunctions.verifyElementVisible( LocatorType.Xpath,"//ul[@class='articleNav']/li/a[contains(text(), '"+submenu+"')]");
			//if there is only 1 article then tree doesnt display the areticle there
			VerificationFunctions.verifyElementNotVisible(LocatorType.Xpath,"//ul[@class='articleNav']//a[contains(text(),'"+article1+"')]");
			
			//verify article1 is displayed in the right hand pane (article name and edit link)
			VerificationFunctions.verifyElementVisible( LocatorType.Xpath,"//h1[contains(text(), '"+article1+"')]//a[contains(text(),'edit')]");
					
			}
		
		
			else  {
				System.out.println("Inside all------");
				VerificationFunctions.verifyElementVisible( LocatorType.Xpath,"//ul[@class='articleNav']/li/a[contains(text(), '"+submenu+"')]");
				VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//ul[@class='articleNav']//a[contains(text(),'"+article1+"')]");
				VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//ul[@class='articleNav']//a[contains(text(),'"+article2+"')]");
				VerificationFunctions.verifyElementVisible(LocatorType.Xpath,"//ul[@class='articleNav']//a[contains(text(),'"+article3+"')]");
					}
		
}



@When("^Goto article \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
public void Goto_article(String menu,String submenu, String article) throws Throwable {	
	
	Navigation.gotoURL("/brand/article/");
	
	CommonFunctions.click( LocatorType.Xpath, "//ul/li/a[contains(text(), '"+menu+"')]" );	
	CommonFunctions.click( LocatorType.Xpath,"//ul[@class='articleNav']/li/a[contains(text(), '"+submenu+"')]");
	CommonFunctions.click(LocatorType.Xpath,"//h1[contains(text(), '"+article+"')]");
	
	//click on the core info tab
	//CommonFunctions.click(LocatorType.Xpath, "//span[text()='Core Info']");
	
	//CommonFunctions.waitForElement(LocatorType.Xpath, "//li/input[@name='item_name']", 10);
	//VerificationFunctions.verifyText(LocatorType.Xpath, "//li/input[@name='item_name']","value", article);
	//CommonFunctions.verifyDropListselectedOption("//select", article);
}


@When("^Verify article coreinfo \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
public void Verify_article_coreinfo(String menu,String submenu, String article) throws Throwable {	
	
	
	
	//click on the core info tab
	CommonFunctions.click(LocatorType.Xpath, "//span[text()='Core Info']");
	
	CommonFunctions.waitForElement(LocatorType.Xpath, "//li/input[@name='item_name']", 10);
	VerificationFunctions.verifyText(LocatorType.Xpath, "//li/input[@name='item_name']","value", article);
//	CommonFunctions.verifyDropListselectedOption("//select", article);
}


@When("^Verify article chapters \"([^\"]*)\"$")
public void Verify_article_chapters(String article) throws Throwable {	
	
	
	
	//verify the edit link and add to watchlist button
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath,"//h1[contains(text(), '"+article+"')]//a[contains(text(),'edit')]");
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath,"//input[@value='Add to Watchlist']");
	
	//verify the chapters in order (chapter 1 should be in span1, chapter 2 be in span 2 etc)
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath,"//span[1]/h2[contains(text(),'chapter1')]");
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath,"//span[2]/h2[contains(text(),'chapter2')]");
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath,"//span[3]/h2[contains(text(),'chapter3')]");
	
	//verify chapter1 media in order
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//span/h2['chapter1']/following::img[@title='brand Red flower.jpg']");
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//span/h2['chapter1']/following::img[@title='BrandTestPDF.pdf']");
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//span/h2['chapter1']/following::img[@title='BrandWildlife.wmv']");
	
	
	//check actions for different media files pdf should have  full screen, video should have play
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//ul[@class='mediaActions document']//a[contains(text(),'fullscreen')]");
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath, "//ul[@class='mediaActions video']//a[contains(text(),'fullscreen')]");
	
	//body text
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath,"//div[@class='articleBody']/p[contains(text(),'This is the body text chap1 article aaa')]");
	
	
	//verify the article body text is followed by the media file 'brand Red flower.jpg'
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath,"//span[div/img[@title='brand Red flower.jpg']]/following-sibling::div[p[contains(text(),'This is the body text chap1 article aaa')]]");
	
	//verifying attached assets table content for chapter1
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath,"//table[//a[contains(text(),'Brand Desert.jpg')]]/preceding-sibling::div[p[contains(text(),'This is the body text chap1 article aaa')]]");
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath,"//table[//a[contains(text(),'Brand_text1.txt')]]/preceding-sibling::div[p[contains(text(),'This is the body text chap1 article aaa')]]");			
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath,"//table[//a[contains(text(),'BrandTestPDF.pdf')]]/preceding-sibling::div[p[contains(text(),'This is the body text chap1 article aaa')]]");			
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath,"//table[//a[contains(text(),'BrandWildlife.wmv')]]/preceding-sibling::div[p[contains(text(),'This is the body text chap1 article aaa')]]");			
		
	
	//==============================================================
	//================================================================
	
	//Chapter2 Verification
	//body text
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath,"//div[@class='articleBody']/p[contains(text(),'This is the body text chap2 article aaa')]");
	
	//verifying attached assets table content for chapter2
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath,"//div[@class='slider full']/following-sibling::table[//a[contains(text(),'BrandWildlife.wmv')]]");
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath,"//div[@class='slider full']/following-sibling::table[//a[contains(text(),'Brand Jellyfish.jpg')]]");
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath,"//div[@class='slider full']/following-sibling::table[//a[contains(text(),'brand Red flower.jpg')]]");
	
	//verify the slide show controls of chapeter 2
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath,"//div[@class='slider full ']//div[@class='controls']//li[@class='back action']");
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath,"//div[@class='slider full ']//div[@class='controls']//li[@class='info']/em[contains(text(),'1/3')]"); //this will fail as it displays 1/18 which should be a bug
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath,"//div[@class='slider full ']//div[@class='controls']//li[@class='next action']");
	
	//verify the 3 assets in the slide show 
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath,"//div[@class='slider full ']//ol//img[@title='BrandWildlife.wmv']");
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath,"//div[@class='slider full ']//ol//img[@title='Brand Jellyfish.jpg']");
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath,"//div[@class='slider full ']//ol//img[@title='brand Red flower.jpg']");

	//Chapter3 Verification
	//body text
	VerificationFunctions.verifyElementsVisible(LocatorType.Xpath,"//div[@class='articleBody']/p[contains(text(),'This is the body text chap3 article aaa')]");
		
}









}




