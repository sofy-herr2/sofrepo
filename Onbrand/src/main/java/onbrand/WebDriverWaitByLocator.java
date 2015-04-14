package onbrand;

import org.openqa.selenium.By;

public class WebDriverWaitByLocator {

	public static By getBy(LocatorType type, String locator) {

		By by= null;
		switch( type ) {
		case LinkedText:
			by =  By.linkText( locator );
			break;
		case Name:
			by =  By.name( locator );
			break;
		case Xpath:
			by =  By.xpath( locator );
			break;
		case className:
			by =  By.className( locator );
			break;
		case cssSelector:
			by =  By.cssSelector( locator );
			break;
		case id:
			by =  By.id( locator );
			break;
		case partialLinkedText:
			by =  By.partialLinkText( locator );
			break;
		case TagName:
			by =  By.tagName( locator );
			break;			

		}

		return by;
	}
}