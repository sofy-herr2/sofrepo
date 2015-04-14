package onbrand;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import onbrand.tools.DriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;



public class Navigation {

	
    public void mainWindow() {
        try {

            String main = DriverManager.driver.getWindowHandle();
            DriverManager.driver.switchTo().window( main );
            DriverManager.driver.switchTo().defaultContent();
           // LogUtils.detailLog( "passed" );

        } catch( NoSuchWindowException e ) {
           // LogUtils.fileWriter( e );
           // LogUtils.detailLog( "failed ---- mainWindow" );
           // myAssertion.fail("Failed to go to Main Window");
        }
    }

    /*
     * Navigate within a page using back and forward
     */
    public void navigatePage( String direction ) {
    	
    	WebDriver.Navigation navigatePage = DriverManager.driver.navigate();

        if( direction.equals("back")) {
           	navigatePage.back();
        	//LogUtils.detailLog( "passed" );
        }
        
        if( direction.equals("forward") ) {
        	navigatePage.forward();
            //LogUtils.detailLog( "passed" );
        } else {
            //LogUtils.detailLog( "failed ---- nagivatePage" );
            //LogUtils.detailLog( "Invalid Page Navigation" );
            //myAssertion.fail("Failed to Navigate -" +direction);
        }
    }

    /**
     * Close Browser Window
     */
    public void closeWindow() {
        try {
        	DriverManager.driver.close();
            //LogUtils.detailLog( "passed" );

        } catch( NoSuchWindowException e ) {

            //LogUtils.fileWriter( e );
            //LogUtils.detailLog( "failed ---- closeWindow" );
            //myAssertion.fail("Failed to Close Window");
        }
    }

    /**
     * Refresh a page
     */
    public static void refreshPage() {
    	DriverManager.driver.navigate().refresh();
       // LogUtils.detailLog( "passed" );
    }

    /**
     * To Load a specific Frame on a Page using frame id
     */
    public static void switchToFrame( String name ) {
        try {
        	DriverManager.driver.switchTo().frame( name );
            //LogUtils.detailLog( "passed" );
        } catch( NoSuchFrameException e ) {

            WebDriverWait wait = new WebDriverWait( DriverManager.driver, 10 );
            wait.until( ExpectedConditions.frameToBeAvailableAndSwitchToIt( name ) );

            //LogUtils.fileWriter( e );
            //LogUtils.detailLog( "fail ----- switchToFrame" );
           // myAssertion.fail("Failed to switchToFrame - " + name);
        }
    }

    /**
     * Switch to default frame in a window
     */
    public static void switchtoDefaultFrame() {
    	DriverManager.driver.switchTo().defaultContent();
    }

    /**
     * Select a browser Window using name
     */
    public void selectWindowName( String windowName ) {
        try {
        	DriverManager.driver.switchTo().window( windowName );
        	DriverManager.driver.switchTo().activeElement();

           // LogUtils.detailLog( "passed" );

        } catch( NoSuchWindowException e ) {
        	DriverManager.driver.switchTo().defaultContent();
           // LogUtils.detailLog( "failed ---- selectWindowName" );
        } catch( WebDriverException e ) {
        	DriverManager.driver.switchTo().defaultContent();
           // LogUtils.detailLog( "failed ---- selectWindowName" );
           // myAssertion.fail("Failed to Select Window Name" + windowName);

        }
    }

    /**
     * Maximise Browser window
     */
    public static void maximiseBrowser() {
        boolean max = DriverManager.driver.manage().window().getSize().equals( DriverManager.driver.manage().window() );

        try {
            if( max ) {
               // LogUtils.detailLog( "passed" );
            } else {

            	DriverManager.driver.manage().window().maximize();
            }

        } catch( AssertionError e ) {
           // LogUtils.fileWriter( e );
           // LogUtils.detailLog( "failed ---- maximiseBrowser" );
           // myAssertion.fail("Failed to Maximise Window");
        }
    }
    
    public static void setBrowserResolution(int width, int height){
    	//Setting Browser Resolution 
    	try {
    	Dimension size = new Dimension(width, height);
    	Dimension resolution; 
    	DriverManager.driver.manage().window().setSize(size);
    	resolution = DriverManager.driver.manage().window().getSize();
      	Assert.assertEquals(resolution, size);
      	
    	// LogUtils.detailLog( "Passed ---- Resolution Set to--"+size );
    	}
    	catch(AssertionError e){
    	//LogUtils.fileWriter( e );
        //LogUtils.detailLog( "failed ---- Setting Browser Resolution" );
        //myAssertion.fail("Failed to Set Resolution" + width + height);
    		
    	}
    }


    /**
     * Goto a URL by parting URL as a string parameter
     */
    public static void gotoURL( String url ) {
    	
        try {
        	DriverManager.driver.navigate().to(DriverManager.baseURL + url);
        	DriverManager.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        	DriverManager.driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        	DriverManager.driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
            
            Assert.assertTrue(url.length()!=0);
            //LogUtils.detailLog( "passed" );

        } catch(Exception e /* WebDriverException | AssertionError e */) {
           // LogUtils.fileWriter( "URL cannot be loaded", e );
           // LogUtils.detailLog( "failed ---- gotoURL" );
           // myAssertion.fail("Failed to go to" +url);
             }

    }


    /**
     * Selecting Window/Not Finding element / frame not active ToDo Read
     * frame segment, then find element or get windows names and switch
     * between
     **/
    public static void selectWindowPopup() {

        String currentWindow = DriverManager.driver.getWindowHandle();
        Set<?> allWindows = DriverManager.driver.getWindowHandles();
        Iterator<?> Iwindow = allWindows.iterator();

        while( Iwindow.hasNext() ) {
            String popup = Iwindow.next().toString();
            try {
                if( !popup.contains( currentWindow ) ) {
                	DriverManager.driver.switchTo().window( popup );
                	DriverManager.driver.switchTo().defaultContent();
                    //LogUtils.detailLog( "passed" );
                } else {
                	DriverManager.driver.switchTo().defaultContent();
                }

            } catch( NoSuchWindowException e ) {
            	DriverManager.driver.switchTo().defaultContent();
                System.out.println( "Window Not Found, default window Selected" );
               // LogUtils.fileWriter( e );
               // LogUtils.detailLog( "failed ---- selectWindowPopup" );
               // myAssertion.fail("Failed to Select Window");
            }
        }
    }

    /**
     * Clicks the OnBrand Main Menu.
     *
     * @param mainMenuLabel label of the main menu
     */
    public static void clickMainMenu(String mainMenuLabel) {
        Locator locator = Locator.byCss(".header ul.modules").find(By.linkText(mainMenuLabel));
        if (locator.isPresent()) {
            locator.getElement().click();
        } else {
           // LogUtils.detailLog("cannot find '"+ mainMenuLabel + "' main menu navigation");
           // myAssertion.fail("Failed to Click Main Menu");
        }
    }

    /**
     * Clicks the OnBrand Main Menu.
     *
     * @param mainMenuLabel label of the main menu
     */
    public static void clickMainMenu(String mainMenuLabel, String labelSubMenu) {
        Locator locator = Locator.byCss(".header ul.modules").find(By.linkText(mainMenuLabel));
        if (locator.isPresent()) {
            locator.getElement().click();
            Locator subMenu = Locator.byCss("ul.subNav").find(By.linkText(labelSubMenu));
            if (subMenu.isPresent()) {
                subMenu.getElement().click();
            } else {
              //  LogUtils.detailLog("cannot find '"+ mainMenuLabel + "' sub menu under '"+ mainMenuLabel+"'");
            }
        } else {
          //  LogUtils.detailLog("cannot find '"+ mainMenuLabel + "' main menu navigation");
          //  myAssertion.fail("Failed to Click Main Menu");
        }
    }

    /**
     * Takes the user to the admin section
     */
    public static void goToCMS(){
        Navigation.gotoURL("/admin/");
    }

}