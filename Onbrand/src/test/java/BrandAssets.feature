Feature: Testing the Brand Assets|Admin section functionality in frontend of the Onbrand Application

  Background: 
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to admin section
    And Navigate to "Assets" menu
    And Navigate to "Brand Assets" menu
  
  @VerifyBAP    
  Scenario: Verify Brand Assets page is loaded correctly       
  #Create New Item button is displayed? true/false
    Then Verify "Brand Assets" page "false" 
    And Verify tab components
    
 @PrerequisitesBE
  Scenario: Loading all the brand assets needed for Bulk Edit scenarios 
  	When Upload several assets "8" "Awaiting Approval" "brand.asset.approval" "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img2.jpg" "ba.img2.jpg"
  	When Upload several assets "8" "Published" "brand.asset.published" "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img2.jpg" "ba.img2.jpg"
  	When Upload several assets "8" "Rejected" "brand.asset.rejected" "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img2.jpg" "ba.img2.jpg"
  	When Upload several assets "8" "Archived" "brand.asset.archived" "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img2.jpg" "ba.img2.jpg"
  	When Upload several assets "8" "Expired" "brand.asset.expired" "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img2.jpg" "ba.img2.jpg"
  	 		
  @BulkEditCancelBA
  Scenario Outline: Bulk Edit and cancel a set of brand assets
	When Go to "<brandAssetTab>" tab  
	And Set the items per page option "45"
    When Navigate to "<items>"
    When Fill all data "<name>" "<description>" "<keywords>" "<restricType>" "<conditions>" "<expiryDateRequired>" "<expiryDate>"
    And "Cancel" the item
    Then Verify "<name>" item is not displayed
    
    Examples:
      | brandAssetTab	  | items 			 		  					  | name     			  | description | keywords | restricType | conditions | expiryDateRequired | expiryDate |    
      | Awaiting Approval | brand.asset.approval1,brand.asset.approval2   | brand.asset.NewName11 | To update 2 | new2     | Limited     | Condition1 | no 			       | 15.03.2016 |
      | Published 		  | brand.asset.published1,brand.asset.published2 | brand.asset.NewName12 | To update 6 | new6     | Unlimited   | Condition1 | yes 			   | 15.03.2016 |
      | Rejected 		  | brand.asset.rejected1,brand.asset.rejected2   | brand.asset.NewName13 | To update 3 | new3     | None        | Condition1 | no 			       | 15.03.2016 |
      | Archived 		  | brand.asset.archived1,brand.asset.archived2   | brand.asset.NewName14 | To update 4 | new4     | Limited     | Condition1 | yes 			   | 15.03.2016 |
      | Expired			  | brand.asset.expired1,brand.asset.expired2     | brand.asset.NewName15 | To update 5 | new5     | Unlimited   | Condition1 | yes 			   | 12.12.2014 |
  
  @BulkEditBAOB-3130
  Scenario Outline: Bulk Edit a set of brand assets
	When Go to "<brandAssetTab>" tab  
	And Set the items per page option "45"
    When Navigate to "<items>"
    When Update brand assets "<name>" "<description>" "<keywords>" "<restricType>" "<conditions>" "<expiryDateRequired>" "<expiryDate>"
    When Navigate to "<name>"
    Then Verify brand asset "<name>" "<description>" "<keywords>" "<restricType>" "<conditions>" "<expiryDateRequired>" "<expiryDate>"
    
    Examples:
      | brandAssetTab	  | items 			 		 				  	  | name      			  | description | keywords | restricType | conditions | expiryDateRequired | expiryDate |    
      | Awaiting Approval | brand.asset.approval1,brand.asset.approval2   | brand.asset.NewName16 | To update 2 | new2	   | Limited     | Condition1 | no 	  	           | 15.05.2016 |
      | Published 		  | brand.asset.published1,brand.asset.published2 | brand.asset.NewName17 | To update 6 | new6	   | Unlimited   | Condition1 | yes 			   | 15.06.2016 |
      | Rejected 		  | brand.asset.rejected1,brand.asset.rejected2   | brand.asset.NewName18 | To update 3 | new3	   | None        | Condition1 | no 			       | 15.07.2016 |
      | Archived 		  | brand.asset.archived1,brand.asset.archived2   | brand.asset.NewName19 | To update 4 | new4	   | Limited     | Condition1 | yes 			   | 15.08.2016 |
      | Expired			  | brand.asset.expired1,brand.asset.expired2     | brand.asset.NewName20 | To update 5 | new5	   | Unlimited   | Condition1 | yes 			   | 10.10.2014 |
    
  @BulkPublishBAOB-3130
  Scenario Outline: Publish a set of brand assets
	When Go to "<brandAssetTab>" tab
	And Set the items per page option "45"
    When Navigate to "<items>"
    When Fill all data "<name>" "<description>" "<keywords>" "<restricType>" "<conditions>" "<expiryDateRequired>" "<expiryDate>"     
    And "Publish" the item
    Then Verify if "<name>" was "published"
    Then Verify brand asset "<name>" "<description>" "<keywords>" "<restricType>" "<conditions>" "<expiryDateRequired>" "<expiryDate>"
         
    Examples:
     | brandAssetTab  	  | items 			 		  					  | name 			      | description | keywords  | restricType | conditions | expiryDateRequired | expiryDate |    
     | Awaiting Approval  | brand.asset.approval3,brand.asset.approval4   | brand.asset.NewName21 | To update 2 | new2	    | Limited     | Condition1 | no 			    | 15.03.2016 |
     | Published 		  | brand.asset.published3,brand.asset.published4 | brand.asset.NewName22 | To update 6 | new6	    | Unlimited   | Condition1 | yes 			    | 15.03.2016 |
     | Rejected 		  | brand.asset.rejected3,brand.asset.rejected4   | brand.asset.NewName23 | To update 3 | new3	    | None        | Condition1 | no 			    | 15.03.2016 |
     | Archived 		  | brand.asset.archived3,brand.asset.archived4   | brand.asset.NewName24 | To update 4 | new4	    | Limited     | Condition1 | yes 			    | 15.03.2016 |
     | Expired			  | brand.asset.expired3,brand.asset.expired4     | brand.asset.NewName25 | To update 5 | new5	    | Unlimited   | Condition1 | yes 			    | 12.12.2014 |
  
  @BulkArchiveBAOB-3130	
  	Scenario Outline: Archive a set of brand assets
	When Go to "<brandAssetTab>" tab  
	And Set the items per page option "45"
    When Navigate to "<items>"
    When Fill all data "<name>" "<description>" "<keywords>" "<restricType>" "<conditions>" "<expiryDateRequired>" "<expiryDate>"     
    And "Archive" the item
    Then Verify if "<name>" was "archived"
    Then Verify brand asset "<name>" "<description>" "<keywords>" "<restricType>" "<conditions>" "<expiryDateRequired>" "<expiryDate>" 
    
    Examples:
     | brandAssetTab  	  | items 			 		  					  | name      			  | description | keywords | restricType | conditions | expiryDateRequired | expiryDate |    
     | Awaiting Approval  | brand.asset.approval5,brand.asset.approval6   | brand.asset.NewName26 | To update 2 | new2	   | Limited     | Condition1 | no 			       | 15.03.2016 |
     | Published 		  | brand.asset.published5,brand.asset.published6 | brand.asset.NewName27 | To update 6 | new6	   | Unlimited   | Condition1 | yes 			   | 15.03.2016 |
     | Rejected 		  | brand.asset.rejected5,brand.asset.rejected6   | brand.asset.NewName28 | To update 3 | new3	   | None        | Condition1 | no 			       | 15.03.2016 |
     | Archived 		  | brand.asset.archived5,brand.asset.archived6   | brand.asset.NewName29 | To update 4 | new4	   | Limited     | Condition1 | yes 			   | 15.03.2016 |
     | Expired			  | brand.asset.expired5,brand.asset.expired6     | brand.asset.NewName30 | To update 5 | new5	   | Unlimited   | Condition1 | yes 			   | 12.12.2014 |
  	
  @BulkRejectBAOB-3130	
  	Scenario Outline: Reject a set of brand assets
	When Go to "<brandAssetTab>" tab
	And Set the items per page option "45"
    When Navigate to "<items>"
    When Fill all data "<name>" "<description>" "<keywords>" "<restricType>" "<conditions>" "<expiryDateRequired>" "<expiryDate>"     
    And "Reject" the item
    Then Verify if "<name>" was "rejected"
    Then Verify brand asset "<name>" "<description>" "<keywords>" "<restricType>" "<conditions>" "<expiryDateRequired>" "<expiryDate>"
     
    Examples:
     | brandAssetTab  	  | items 			 		  					  | name      			  | description | keywords | restricType | conditions | expiryDateRequired | expiryDate |    
     | Awaiting Approval  | brand.asset.approval7,brand.asset.approval8   | brand.asset.NewName31 | To update 2 | new2	   | Limited     | Condition1 | no 			       | 15.03.2016 |
     | Published 		  | brand.asset.published7,brand.asset.published8 | brand.asset.NewName32 | To update 6 | new6	   | Unlimited   | Condition1 | yes 			   | 15.03.2016 |
     | Rejected 		  | brand.asset.rejected7,brand.asset.rejected8   | brand.asset.NewName33 | To update 3 | new3	   | None        | Condition1 | no 			       | 15.03.2016 |
     | Archived 		  | brand.asset.archived7,brand.asset.archived8   | brand.asset.NewName34 | To update 4 | new4	   | Limited     | Condition1 | yes 			   | 15.03.2016 |
     | Expired			  | brand.asset.expired7,brand.asset.expired8     | brand.asset.NewName35 | To update 5 | new5	   | Unlimited   | Condition1 | yes 			   | 12.12.2014 |
  
  @PostRequisitesBE
  Scenario Outline: Delete all created brand assets created for bulk edit scenarios
    Given Go to "<brandAssetTab>" tab
    Given Select the "List" view
    And Set the items per page option "45"
    And Delete all brand assets
    Examples:
     | brandAssetTab	  |
     | Awaiting Approval  |
     | Published          |
     | Rejected           |
     | Archived           |
	 | Expired            |        	  
	 
 @PrerequisitesBA
  Scenario: Loading all the brand assets needed for the testing
  	When Upload several assets "3" "Awaiting Approval" "brand.asset.approval" "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img2.jpg" "ba.img2.jpg"
  	When Upload several assets "4" "Published" "brand.asset.published" "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img2.jpg" "ba.img2.jpg"
  	When Upload several assets "2" "Rejected" "brand.asset.rejected" "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img2.jpg" "ba.img2.jpg"
  	When Upload several assets "2" "Archived" "brand.asset.archived" "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img2.jpg" "ba.img2.jpg"
  	When Upload several assets "2" "Expired" "brand.asset.expired" "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img2.jpg" "ba.img2.jpg"
       
  @VerifyFieldsAndCommentsBA
  Scenario Outline: Verify the required fields and the comment section for all tabs
    When Go to "<brandAssetTab>" tab      
    When Navigate to "<item>"
    Then Verify main components "<brandAssetTab>" "<item>"
    Then Verify comments components
    
   	Examples:
  	| brandAssetTab		| item  	  			 |
  	| Awaiting Approval | brand.asset.approval1  | 
    | Published 		| brand.asset.published1 | 
    | Rejected 	        | brand.asset.rejected1  | 
    | Archived 		    | brand.asset.archived1  | 
    | Expired			| brand.asset.expired1   | 
  	
  @FieldsRequiredBAOB-3132
  Scenario Outline: Verify the required fields for all tabs
    When Go to "<brandAssetTab>" tab  
    When Navigate to "<item>"
    Then Verify the errors
    
  	Examples:
  	| brandAssetTab		| item		  			 |
  	| Awaiting Approval | brand.asset.approval1  | 
    | Published 		| brand.asset.published1 | 
    | Rejected 	        | brand.asset.rejected1  | 
    | Archived 		    | brand.asset.archived1  | 
    | Expired			| brand.asset.expired1   |  
  	     
  @CancelEditBA
  Scenario Outline: Cancel a brand asset edition
	When Go to "<brandAssetTab>" tab  
    When Navigate to "<item>"
    When Fill data "<file>" "<preview>" "<name>" "<description>" "<keywords>" "<client>" "<startDate>" "<marketingActivity>" "<restricType>" "<conditions>" "<expiryDateRequired>" "<expiryDate>"
    And "Cancel" the item
    Then Verify "<name>" item is not displayed
   	Examples:
  	| brandAssetTab		| item 		   			 | file 		      											| preview 		     										   | name     			  | description     | keywords  | client     | startDate  | marketingActivity | restricType | conditions | expiryDateRequired | expiryDate |    
    | Awaiting Approval | brand.asset.approval1  | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img2.jpg | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img2.jpg | brand.asset.NewName1 | new description | canceled2 | newClient2 | 23.03.2015 | yes 			  | Limited 	| Condition1 | no 			      | 15.03.2016 |
    | Published			| brand.asset.published1 | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img6.jpg | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img6.jpg | brand.asset.NewName2 | new description | canceled6 | newClient6 | 23.03.2015 | yes 			  | Limited 	| Condition1 | no 			      | 15.03.2016 |
	| Rejected 			| brand.asset.rejected1  | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img3.jpg | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img3.jpg | brand.asset.NewName3 | new description | canceled3 | newClient3 | 23.03.2015 | yes 			  | Limited 	| Condition1 | no 			      | 15.03.2016 |
    | Archived 			| brand.asset.archived1  | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img4.jpg | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img4.jpg | brand.asset.NewName4 | new description | canceled4 | newClient4 | 23.03.2015 | yes 			  | Limited 	| Condition1 | no 			      | 15.03.2016 |
    | Expired 			| brand.asset.expired1   | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img5.jpg | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img5.jpg | brand.asset.NewName5 | new description | canceled5 | newClient5 | 23.03.2015 | yes 			  | Limited 	| Condition1 | no 			      | 15.03.2016 |
  
  @UpdateBA
  Scenario Outline: Update a Brand Asset file
    When Go to "<brandAssetTab>" tab
    When Navigate to "<item>"
    When Update a brand asset "<file>" "<preview>" "<name>" "<description>" "<keywords>" "<client>" "<startDate>" "<marketingActivity>" "<restricType>" "<conditions>" "<expiryDateRequired>" "<expiryDate>"
    And Navigate to "<name>"
    Then Verify the brand asset "<name>" "<description>" "<keywords>" "<client>" "<startDate>" "<marketingActivity>" "<restricType>" "<conditions>" "<expiryDateRequired>" "<expiryDate>"
    
    Examples:
      | brandAssetTab	  | item 				   | file 		       					   						  | preview    									  		         | name     			| description           | keywords | client  | startDate  | marketingActivity | restricType | conditions | expiryDateRequired | expiryDate |    
      | Awaiting Approval | brand.asset.approval1  | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img2.jpg | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img2.jpg | brand.asset.NewName6 | To update the image 2 | new2	   | Client2 | 23.03.2015 | yes 			  | Limited 	| Condition1 | no 			  	  | 15.03.2016 |
      | Published 		  | brand.asset.published1 | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img6.jpg | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img6.jpg | brand.asset.NewName7 | To update the image 6 | new6	   | Client6 | 23.03.2015 | no 			      | Unlimited   | Condition1 | yes 			      | 15.03.2016 |
      | Rejected 		  | brand.asset.rejected1  | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img3.jpg | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img3.jpg | brand.asset.NewName8 | To update the image 3 | new3	   | Client3 | 23.03.2015 | yes 			  | None	    | Condition1 | no 			      | 15.03.2016 |
      | Archived 		  | brand.asset.archived1  | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img4.jpg | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img4.jpg | brand.asset.NewName9 | To update the image 4 | new4	   | Client4 | 23.03.2015 | no 			      | Limited     | Condition1 | yes 			      | 15.03.2016 |
      | Expired			  | brand.asset.expired1   | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img5.jpg | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\ba.img5.jpg | brand.asset.NewName10 | To update the image 5 | new5	   | Client5 | 23.03.2015 | yes 			  | Unlimited   | Condition1 | yes 			      | 12.12.2014 |
  
  @RejectBA
  Scenario Outline: Reject a brand asset
    When Go to "<brandAssetTab>" tab  
    When Navigate to "<item>"
    When "Reject" the item
    Then Verify if "<item>" was "rejected"
            
  	Examples:
  	| brandAssetTab		| item 	      			| 
  	| Awaiting Approval | brand.asset.approval2 | 
  
  @ArchiveBA
  Scenario Outline: Archive a brand asset
    When Go to "<brandAssetTab>" tab  
    When Navigate to "<item>"
    When "Archive" the item
    Then Verify if "<item>" was "archived"        
  	Examples:
  	| brandAssetTab	 | item         		  |
  	| Rejected  	 | brand.asset.rejected2  |
  	| Published 	 | brand.asset.published2 |
  
  @ExpireBA
  Scenario Outline: Expire a brand asset
    When Go to "<brandAssetTab>" tab  
    When Navigate to "<item>"
    When "Expire" the item
    Then Verify if "<item>" was "expired" 
           
  	Examples:
  	| brandAssetTab | item    	   			  |
  	| Published 	| brand.asset.published3 |	
  
  @PublishRePublishBA
  Scenario Outline: Publish and Email Watcher,Publish and Re Publish a brand asset
    When Go to "<brandAssetTab>" tab  
    When Navigate to "<item>"
    When "Publish" the item
    Then Verify if "<item>" was "published" 
    #Then Verify that the email was sent// to implement  
         
  	Examples:
  	| brandAssetTab		| item     	   			 |
  	| Awaiting Approval | brand.asset.approval3  |
  	| Published			| brand.asset.published4 |
  	| Archived			| brand.asset.archived2  |
  	| Expired			| brand.asset.expired2   |
  
  @DeleteBA
  Scenario Outline: Delete a brand asset
    When Go to "<brandAssetTab>" tab  
    When Navigate to "<item>"
    When "Delete" the item
    Then Verify if "<item>" was "deleted" 
    
   	Examples:
  	| brandAssetTab		| item      			|
  	| Awaiting Approval | brand.asset.NewName6  |
  	| Published			| brand.asset.NewName7  |
  	| Rejected			| brand.asset.NewName8  |
  	| Archived			| brand.asset.NewName9  |
  	| Expired			| brand.asset.NewName10 |
  
#Jose
@CompareVersions1
  Scenario: Select a brand asset, modify some fields and create a new version for Awaiting Approval tab. 
  			Also verifies that the changed fields are in blue and the main fields have an icon and 
  			the changed values are displayed in a modal window.
	When Go to "Awaiting Approval" tab
	And Select the "Grid" view
	And Search 1 brand assets with "brand.asset.version" item name
	And Navigate to "brand.asset.version"
	And Create first version
	| fieldName             | firstVersion             | secondVersion         |
	| Name                  | brand.asset.version1     | brand.asset.version2  |
	| Description           | Description1             | Description2          |
	| Keywords              | Keyword1                 | Keyword2              |
	| Client                | Client1                  | Client2               |
	| Rejection Comment     | Rejection1               | Rejection2            |
	| Start Date            | 03.03.2015               | 04.03.2015            |
	| Restrictions Type     | Limited                  | Unlimited             |
	| Conditions            | Condition1               | Condition2            |
#	| Expiry Date Required  | yes                      | yes                   |
#	| Expiry Date           | 03.04.2015               | 03.06.2015            |
	When Navigate to "brand.asset.version1"
	Then Verify that a new version is created
	When Select the last version
	Then Verify the number of changes is correct
	Then Verify that changed fields are in blue
	Then Verify that changed Core Info fields have an icon
	When Create second version
	And Navigate to "brand.asset.version2"
	And Select the last version
	Then Verify that changes are correct in the modal window
	When Select latest version
	Then Verify that latest version is selected
	
@CompareVersions1
  Scenario Outline: Select a brand asset, modify some fields and create a new version for Published, Rejected, Archived and Expired tabs. 
  					Also verifies that the changed fields are in blue and the main fields have an icon and 
  					the changed values are displayed in a modal window.
	When Go to "<brandAssetTab>" tab
	And Select the "Grid" view
	And Search 1 brand assets with "brand.asset.version" item name
	And Navigate to "brand.asset.version"
	And Create first version
	| fieldName             | firstVersion          | secondVersion         |
	| Name                  | brand.asset.version1  | brand.asset.version2  |
	| Description           | Description1          | Description2          |
	| Keywords              | Keyword1              | Keyword2              |
	| Client                | Client1               | Client2               |
	| Start Date            | 03.03.2015            | 04.03.2015            |
	| Restrictions Type     | Limited               | Unlimited             |
	| Conditions            | Condition1            | Condition2            |
#	| Expiry Date Required  | yes                   | yes                   |
#	| Expiry Date           | 03.04.2015            | 03.06.2015            |
	When Navigate to "brand.asset.version1"
	Then Verify that a new version is created
	When Select the last version
	Then Verify the number of changes is correct
	Then Verify that changed fields are in blue
	Then Verify that changed Core Info fields have an icon
	When Create second version
	And Navigate to "brand.asset.version2"
	And Select the last version
	Then Verify that changes are correct in the modal window
	When Select latest version
	Then Verify that latest version is selected
    Examples:
  	| brandAssetTab |
#  	| Draft         |
  	| Published     |
  	| Rejected      |
  	| Archived      |
  	| Expired       | 
	
@GridListView2
  Scenario Outline: Verifies that the brand assets are displayed correctly in the Grid and List view for all brand asset tabs
  When Go to "<brandAssetTab>" tab
  And Select the "Grid" view
  Then Verify the asset attributes and options
    | thumbnail |
    | title     |
    | type      |
    | size      |
    | download  |
    | checkbox  |
  When Select the "List" view
  Then Verify the asset attributes
    | ID         	|
    | Name     		|
    | File Type  	|
    | Size      	|
    | Last updated  |
    | By     		|
    |        		|
  Then Verify both views have same number of assets
    Examples:
  	| brandAssetTab     |
#  	| Draft             |
  	| Awaiting Approval |
  	| Published         |
  	| Rejected          |
  	| Archived          |
  	| Expired           |
  
@SortList2
  Scenario Outline: Validates that the brand assets are ordered in ascendant and descendent way in the list view for all brand asset tabs
          			Verifies sorting using name, last modified and created attributes with ascendant and descendant combinations
  When Go to "<brandAssetTab>" tab
  And Select the "List" view
  And Set the items per page option to 45
  #Verify that the sort by Name is ordered ascendent in the list view
  And Sort by "Name" and "ascendent" order
  Then Verify the list view is ordered
  #Verify that the sort by Name is ordered descendent in the list view
  When Sort by "Name" and "descendent" order
  Then Verify the list view is ordered
  #Verify that the sort by Last Modified is ordered ascendent in the list view
  When Sort by "Last Modified" and "ascendent" order
  Then Verify the list view is ordered
  #Verify that the sort by Last Modified is ordered descendent in the list view
  When Sort by "Last Modified" and "descendent" order
  Then Verify the list view is ordered
  #Verify that the sort by Created is ordered ascendent in the list view
#  When Sort by "Created" and "ascendent" order
#  Then Verify the list view is ordered
  #Verify that the sort by Created is ordered descendent in the list view
  When Sort by "Created" and "descendent" order
  Then Verify the list view is ordered
# When Sort by "empty" and "ascendent" order
# Then Verify the list view is ordered
# When Sort by "empty" and "descendent" order
# Then Verify the list view is ordered
    Examples:
  	| brandAssetTab     |
#  	| Draft             |
  	| Awaiting Approval |
  	| Published         |
  	| Rejected          |
  	| Archived          |
  	| Expired           |

@UpDown2
Scenario Outline: Verifies that Name and Last updated are sorted ascendent or descendent using up/down for all brand asset tabs
  When Go to "<brandAssetTab>" tab 
  And Select the "List" view
  And Set the items per page option to 45
  #Verify that the list view is sorted by name in ascendent or descendent order
  When Set "ascendent" order for "Name" column
  Then Verify the list view is ordered
  When Set "descendent" order for "Name" column
  Then Verify the list view is ordered
  #Verify that the list view is sorted by last updated in ascendent or descendent order 
  When Set "ascendent" order for "Last updated" column
  Then Verify the list view is ordered
  When Set "descendent" order for "Last updated" column
  Then Verify the list view is ordered
    Examples:
  	| brandAssetTab     |
#  	| Draft             |
  	| Awaiting Approval |
  	| Published         |
  	| Rejected          |
  	| Archived          |
  	| Expired           |

@SortGrid2
Scenario Outline: Validates that the brand assets are ordered in ascendant and descendent way in the grid view for all brand asset tabs
          		  Verifies sorting using name, last modified and created attributes with ascendant and descendant combinations
  When Go to "<brandAssetTab>" tab
  And Select the "List" view
  And Set the items per page option to 45
  #Verify that the sort by Name is ordered ascendent in the grid view
  And Get "Name" data in "ascendent" order
  When Select the "Grid" view
  Then Verify the grid view is ordered
  #Verify that the sort by Name is ordered descendent in the grid view
  When Select the "List" view
  And Get "Name" data in "descendent" order
  When Select the "Grid" view
  Then Verify the grid view is ordered
  #Verify that the sort by Last Modified is ordered ascendent in the grid view
  When Select the "List" view
  And Get "Last Modified" data in "ascendent" order
  When Select the "Grid" view
  Then Verify the grid view is ordered
  #Verify that the sort by Last Modified is ordered descendent in the grid view
  When Select the "List" view
  And Get "Last Modified" data in "descendent" order
  When Select the "Grid" view
  Then Verify the grid view is ordered
  #Verify that the sort by Created is ordered ascendent in the grid view
#  When Select the "List" view
#  And Get "Created" data in "ascendent" order
#  When Select the "Grid" view
#  Then Verify the grid view is ordered
  #Verify that the sort by Created is ordered descendent in the grid view
  When Select the "List" view
  And Get "Created" data in "descendent" order
  When Select the "Grid" view
  Then Verify the grid view is ordered
# It is not implemented yet because we don't have what is the default sort order
#  When Select the "List" view
#  And Get "empty" data in "ascendent" order
#  When Select the "Grid" view
#  Then Verify the grid view is ordered
  
#  When Select the "List" view
#  And Get "empty" data in "descendent" order
#  When Select the "Grid" view
#  Then Verify the grid view is ordered
    Examples:
  	| brandAssetTab     |
#  	| Draft             |
  	| Awaiting Approval |
  	| Published         |
  	| Rejected          |
  	| Archived          |
  	| Expired           |

  #By Virginia    
  @Search&ResetBA1
  Scenario Outline: Verify Search & Reset for non existing brand asset in each different tab.
  		            The tabs of brand assets will be test : Awaiting Approval, Published, Rejected, Archieved and Expired
    Given Go to "<brandAssetTab>" tab
    And Select the "Grid" view
    And Set the items per page option "45"
    And Validate there are at least "2" brand assets in "<brandAssetTab>"
    When Search brand assets by "<searchWord>"
    Then Verify that none brand asset is found
    Then Reset brand assets search results
    Examples: 
    | brandAssetTab      | searchWord |
    | Awaiting Approval  | anyWord    |
    | Published          | anyWord    |
    | Rejected           | anyWord    |
    | Archived           | anyWord    |
    | Expired            | anyWord    |
  
 #Virginia Pagination
 @paginationBA1
  Scenario Outline: Verify brand assets paginagion buttons when there are either single or no matches. 
                    The top and bootom pagination buttons are not visible if there is not any created item or
  			        the number of created item is lower than the selected item per page option(only a number button is visible)  
  	Given Go to "<brandAssetTab>" tab  			 
    And Select the "Grid" view
    And Set the items per page option "45"
    When Search 1 brand assets with "brand.asset.search" item name
    Then Verify the "top" pagination bar displays "1" page
    And Verify the "bottom" pagination bar displays "1" page
    When Search 0 brand assets with "anyworddd" item name
    Then Verify the pagination buttons are not visible on "top" pagination bar
    Then Verify the pagination buttons are not visible on "bottom" pagination bar
    Examples:
    | brandAssetTab      |
    | Awaiting Approval  |
    | Published          |
    | Rejected           |
    | Archived           |
    | Expired            |
    
  @paginationBA
  Scenario Outline: Brand Assets top pagination buttons behave properly when using 'number','next' and 'previous' to navigate the available pages
    	            All top pagination buttons are visible when the number of displayed brand assets is more than the selected items per page 
    Given Go to "<brandAssetTab>" tab
    And Select the "Grid" view
    And Validate there are at least "11" brand assets in "<brandAssetTab>"
    When Set the items per page option "5"
    Then Verify number of pages is correct on brand assets "top" pagination
    Then Verify brand assets pagination works using number of page
    Then Verify "next" icon is not displayed on brand assets
    Then Verify brand assets pagination works using previous
    Then Verify "previous" icon is not displayed on brand assets
    Then Verify brand assets pagination works using next
    Examples:
    | brandAssetTab      |
    | Awaiting Approval  |
    | Published          |
    | Rejected           |
    | Archived           |
    | Expired            |

  @paginationBA3
  Scenario Outline: Brand Assets bottom pagination buttons behave properly when using 'number','next' and 'previous' to navigate the available pages
    	            All bottom pagination buttons are visible when the number of displayed brand assets is more than the selected items per page 
    Given Go to "<brandAssetTab>" tab
    And Select the "Grid" view
    And Validate there are at least "11" brand assets in "<brandAssetTab>"
    When Set the items per page option "5"
    Then Verify number of pages is correct on brand assets "bottom" pagination
    Then Verify brand assets pagination works using number of page
    Then Verify "next" icon is not displayed on brand assets
    Then Verify brand assets pagination works using previous
    Then Verify "previous" icon is not displayed on brand assets
    Then Verify brand assets pagination works using next
    Examples:
    | brandAssetTab      |
    | Awaiting Approval  |
    | Published          |
    | Rejected           |
    | Archived           |
    | Expired            |
    
  #Virginia ItemsPerPage
  @itemsperpageBA1
  Scenario Outline: The number of items per page in brand assets list view is consistent with the selected 'Items Per Page' option
    Given Go to "<brandAssetTab>" tab
    And Select the "List" view
    And Validate there are at least "16" brand assets in "<brandAssetTab>"
    When Set the items per page option "5"
    Then Verify that "5" brand assets are displayed
    When Set the items per page option "15"
    Then Verify that "15" brand assets are displayed
    #When Set the items per page option "30"
    #Then Verify that "30" brand assets are displayed
    #When Set the items per page option "45"
    #Then Verify that "45" brand assets are displayed
    Examples:
    | brandAssetTab      |
    | Awaiting Approval  |
    | Published          |
    | Rejected           |
    | Archived           |
	| Expired            |

  @itemsperpageBA2
  Scenario Outline: The number of items per page in the brand assets grid view is consistent with the selected 'Items Per Page' option
    Given Go to "<brandAssetTab>" tab
    And Select the "Grid" view
    And Validate there are at least "16" brand assets in "<brandAssetTab>"
    When Set the items per page option "5"
    Then Verify that "5" brand assets are displayed
    When Set the items per page option "15"
    Then Verify that "15" brand assets are displayed
    #When Set the items per page option "30"
    #Then Verify that "30" brand assets are displayed
    #When Set the items per page option "45"
    #Then Verify that "45" brand assets are displayed
    Examples:
    | brandAssetTab      |
    | Awaiting Approval  |
    | Published          |
    | Rejected           |
    | Archived           |
    | Expired            |
   
  @DeleteBrandAssets
  Scenario Outline: Delete all created brand assets during the execution of feature (with brand.asset as prefix)
    Given Go to "<brandAssetTab>" tab
    And Select the "List" view
    And Set the items per page option "45"
    And Delete all brand assets
    Examples:
     | brandAssetTab      |
     | Awaiting Approval  |
     | Published          |
     | Rejected           |
     | Archived           |
	 | Expired            |
