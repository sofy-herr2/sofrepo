Feature: Testing the Supporting files|Admin section functionality in frontend of the Onbrand Application

  Background:
    Given Login to the system "jose.rioja@jalasoft.com" "Testing1"
    When Navigate to admin section
    And Navigate to "Assets" menu
    And Navigate to "Supporting Files" menu

#By Shirley
@VerifySFP
  Scenario: Verify supporting files page is loaded correctly  
    Then Verify "Supporting Files" page "true" 
    And Verify tab components
    
@CreateSF
  Scenario Outline: Create a new supporting file
    When Navigate to create new support file page
    And Create support file "<file>" "<preview>" "<name>"
    Then Verify "<name>" item 
    Examples:
    | file    													 	  | preview        	  			 									| name   |
    | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf.img1.jpg 	  | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf.img1.jpg 	| Image1 |

@UpdateDeleteSF
  Scenario: Update a supporting file
    And Navigate to support file "Image1"
    When Update support file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf.img2.jpg" "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf.img2.jpg" "NewName"
    Then Verify "NewName" item
    And Navigate to support file "NewName"
   	And "Delete" the item
    Then Verify "NewName" item is not displayed
      
@RequiredFieldsOB-3131  
  Scenario: Validate required fields 
    When Navigate to create new support file page
    And Create support file "" "" ""
    Then Verify the errors displayed

@CancelSFCreation
  Scenario: Cancel a New Supporting File creation
    When Navigate to create new support file page
    And Set fields "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf.img1.jpg" "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf.img1.jpg" "Image5"
    And "Cancel" the item    
    Then Verify "Image5" item is not displayed    
 
 #Jose
 @GridListView
  Scenario: Verifies that the assets are displayed correctly in the Grid and List view for Supporting Files
  When Select the "Grid" view
  Then Verify the asset attributes and options
    | thumbnail |
    | title     |
    | type      |
    | size      |
    | download  |
  When Select the "List" view
  Then Verify the asset attributes
    | ID         	|
    | Name     		|
    | Type     		|
    | Size      	|
    | Last updated  |
    | By     		|
  Then Verify both views have same number of assets
    
@SortList
Scenario: Validates that the assets are ordered in ascendant and descendent way in the list view for Supporting Files
          Verifies sorting using name, last modified and created attributes with ascendant and descendant combinations
  Given Select the "List" view
  And Set the items per page option to 45
  #Verify that the sort by Name is ordered ascendent in the list view
  When Sort by "Name" and "ascendent" order
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
  When Sort by "Created" and "ascendent" order
  Then Verify the list view is ordered
  #Verify that the sort by Created is ordered descendent in the list view
  When Sort by "Created" and "descendent" order
  Then Verify the list view is ordered
# When Sort by "empty" and "ascendent" order
# Then Verify the list view is ordered
# When Sort by "empty" and "descendent" order
# Then Verify the list view is ordered

@UpDown
Scenario: Verifies that Name and Last updated are sorted ascendent or descendent using up/down for Supporting Files
  Given Select the "List" view
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

@SortGrid
Scenario: Validates that the assets are ordered in ascendant and descendent way in the grid view for supporting files
          Verifies sorting using name, last modified and created attributes with ascendant and descendant combinations
  Given Select the "List" view
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
  When Select the "List" view
  And Get "Created" data in "ascendent" order
  When Select the "Grid" view
  Then Verify the grid view is ordered
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

  #Virginia
  @Search&ResetSF0
  Scenario: Prerequisite for search tests
   Given Select the "Grid" view
   And Add supporting files
   | itemname        | numberOfItems | file         	                 								| 
   | sf.item         | 2             |C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf.img1.jpg      |
   | sf.item.search  | 1             |C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf.img1.jpg      |
   | sf.itemTest*    | 3             |C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf.img1.jpg      |
   | sf.item.DocPdf  | 1             |C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf_pdf_doc.pdf   |
   | sf.item.DocWord | 1             |C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf_word_doc.docx |
   | sf.item.DocPPT  | 1             |C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf_pp_doc.pptx   |
  
  @Search&ResetSF1
  Scenario Outline: Verify Search & Reset for non existing files.Search on item name
 	Given Select the "Grid" view
 	And Set the items per page option "45"
 	And Validate there are "2" item(s) created
	When Search items by "<itemname>"
    Then Verify that none item is found
    Then Reset the search results
    Examples: 
    | itemname |
    | anyWord  |

  @Search&ResetSF2
  Scenario Outline: Verify Search & Reset for existing files.Search on item name
 	Given Select the "Grid" view
 	And Set the items per page option "45"
 	And Validate there are "<numberOfItems>" with "<itemname>" and "<file>"
    When Search items by "<itemname>"
    Then Verify that "<numberOfItems>" items are found
    Then Reset the search results
    Examples:
    | itemname      | numberOfItems | file         	   	                                           | 
    | sf.item       | 2             |C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf.img1.jpg  |
  
  @Search&ResetSF3
  Scenario Outline: Verify Search & Reset for existing files.Search on item name using some wildcards 
 	Given Select the "Grid" view
 	And Set the items per page option "45"
 	And Validate there are "<numberOfItems>" with "<itemname>" and "<file>"
    When Search items by "<itemname>"
    Then Verify that "<numberOfItems>" items are found using "<wildcard>"
    Then Reset the search results
    Examples:
    | itemname       | wildcard | numberOfItems | file         	     									       |
    | sf.itemTest*	 |   *	    | 3             | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf.img1.jpg |

  @Search&ResetSF4
  Scenario Outline: Verify Search & Reset for existing files.
  					Search on content of document kind pdf,word,pptx
 	Given Select the "Grid" view
 	And Set the items per page option "45"
 	And Validate there are "<numberOfItems>" with "<itemname>" and "<file>"
    When Search items by "<content>"
    Then Verify that "<numberOfItems>" items are found with content "<itemname>" 
    Then Reset the search results
    Examples:
    | itemname        | content  | numberOfItems | file         	         										| 
    | sf.item.DocPdf  | testing  | 1             |C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf_pdf_doc.pdf   |
    | sf.item.DocWord | testing1 | 1             |C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf_word_doc.docx |
    | sf.item.DocPPT  | testing2 | 1             |C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf_pp_doc.pptx   |

    
  #Virginia Pagination
  @paginationSF1
  Scenario: Verify paginagion buttons when there are either single or no matches. 
            The top and bootom pagination buttons are not visible if there is not any created item or
  			the number of created item is lower than the selected item per page option(only a number button is visible)    			 
    Given Select the "Grid" view
    And Set the items per page option "45"
    When Search 1 item(s) with "sf.item.search" item name
    Then Verify the "top" pagination bar displays "1" page
    And Verify the "bottom" pagination bar displays "1" page
    When Search 0 item(s) with "anyworddd" item name
    Then Verify the pagination buttons are not visible on "top" pagination bar
    Then Verify the pagination buttons are not visible on "bottom" pagination bar
    
  @paginationSF2
  Scenario: Top pagination buttons behave properly when using 'number','next' and 'previous' to navigate the available pages
  			All top pagination buttons are visible when the number of created items is more than the selected items per page 
    Given Select the "Grid" view
    And Validate there are "11" item(s) created
    When Set the items per page option "5"
    Then Verify the number of pages is correct on "top" pagination
    Then Verify the pagination works using number of page
    Then Verify the "next" icon is not displayed
    Then Verify the pagination works using previous
    Then Verify the "previous" icon is not displayed
    Then Verify the pagination works using next

  @paginationSF3
  Scenario: Bottom pagination buttons behave properly when using 'number','next' and 'previous' to navigate the available pages
  			All bottom pagination buttons are visible when the number of created items is more than the selected 'Items Per Page' option
    Given Select the "Grid" view
    And Validate there are "11" item(s) created
    When Set the items per page option "5"
    Then Verify the number of pages is correct on "bottom" pagination
    Then Verify the pagination works using number of page
    Then Verify the "next" icon is not displayed
    Then Verify the pagination works using previous
    Then Verify the "previous" icon is not displayed
    Then Verify the pagination works using next
       
  #Virginia ItemsPerPage
  @itemsperpageSF1
  Scenario Outline: Verify the number of items per page in the grid view is consistent with the selected 'Items Per Page' option
   Given Select the "Grid" view
   And Set the items per page option "45"
   And Validate there are "<numberOfItems>" item(s) created
   When Set the items per page option "<itemsPerPage>"
   Then Verify that "<numberOfItems>" items are displayed in search view
   Examples:
    | itemsPerPage | numberOfItems |
    | 5	           | 5             |
    | 15           | 15            |
    #| 30	       | 30            |
    #| 45	       | 45            |

  @itemsperpageSF2
  Scenario Outline: Verify the number of items per page in the list view is consistent with the selected 'Items Per Page' option
   Given Select the "List" view
   And Set the items per page option "45"
   And Validate there are "<numberOfItems>" item(s) created
   When Set the items per page option "<itemsPerPage>"
   Then Verify that "<numberOfItems>" items are displayed in search view
   Examples:
    | itemsPerPage | numberOfItems |
    | 5	           | 5             |
    | 15           | 15            |
    #| 30	       | 30            |
    #| 45	       | 45            |
 
  @DeleteSupportingFiles
  Scenario: Delete all created items during the execution of feature (with sf.item as prefix)
   Given Select the "List" view
   And Set the items per page option "45"
   And Delete all created items