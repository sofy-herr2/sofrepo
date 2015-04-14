Feature: Testing the Slideshow Images|Admin section functionality in frontend of the Onbrand Application

  Background: 
    Given Login to the system "jose.rioja@jalasoft.com" "Testing1"
    When Navigate to admin section
    When Navigate to "Assets" menu
    When Navigate to "Slideshow Images" menu

  #By Shirley
  @VerifySIP
  Scenario: Verify slideshow image is loaded correctly
            Verify Supporting Files title, Create New Item button, Search section, Reset button, Item per page, Sort, View button, Displaying label components are displayed.
    Then Verify "Slideshow Images" page "true" 
    And Verify tab components

  @CreateSI
  Scenario Outline: Create a new slideshow Image
    When Navigate to create new slideshow image page
    And Create slideshow image "<file>" "<link>" "<open>" "<name>"
    Then Verify "<name>" item

    Examples: 
      | file                                                         | link   | open | name   |
      | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf.img1.jpg | image1 | yes  | Happy1 |

  @UpdateDeleteSI
  Scenario: Update a slideshow image
    And Navigate to slideshow image "Happy1"
    When Update slideshow image "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf.img2.jpg" "newLink" "no" "NewName"
    Then Verify "NewName" item
    And Navigate to slideshow image "NewName"
    And "Delete" the item
    Then Verify "NewName" item is not displayed

  @CancelSICreation
  Scenario: Cancel a New Slideshow Image creation
    When Navigate to create new slideshow image page
    And Set fields with "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\img1.jpg" "image4Link" "yes" "Image4"
    And "Cancel" the item
    Then Verify "Image5" item is not displayed

  @RequiredFieldsSI
  Scenario: Validate required fields
    When Navigate to create new slideshow image page
    And Create slideshow image "" "" "no" ""
    Then Verify all errors displayed

  #Jose
  @GridListView
  Scenario: Verifies that the assets are displayed correctly in the Grid and List view for Slideshow Images
    When Select the "Grid" view
    Then Verify the asset attributes and options
      | thumbnail |
      | title     |
      | type      |
      | size      |
      | download  |
    When Select the "List" view
    Then Verify the asset attributes
      | ID           |
      | Name         |
      | Type         |
      | Size         |
      | Last updated |
      | By           |
    Then Verify both views have same number of assets

  @SortList
  Scenario: Validates that the assets are ordered in ascendant and descendent way in the list view for Slideshow Images
            Verifies sorting using name, last modified and created attributes with ascendant and descendant combinations
    Given Select the "List" view
    And Set the items per page option to 45
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
  Scenario: Verifies that Name and Last updated are sorted ascendent or descendent using up/down for Slideshow Images
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
  Scenario: Validates that the assets are ordered in ascendant and descendent way in the grid view for Slideshow Images
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
  
  #Virginia Search&Reset
  @Search&ResetSSI0
  Scenario: Prerequisite for search tests
    Given Select the "Grid" view
    And Add slideshow images
    | itemname          | numberOfImages | file                                                              |link       |
    | ssi.item          | 2              | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf.img1.jpg      |           |
    | ssi.item.search   | 1              | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf.img1.jpg      |			 |
    | ssi.itemTest*     | 3              | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf.img1.jpg      |           |
    | ssi.item.DocPdf   | 1              | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf_pdf_doc.pdf   |           |
    | ssi.item.DocWord  | 1              | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf_word_doc.docx |           |
    | ssi.item.DocPPT   | 1              | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf_pp_doc.pptx   |           |
    | ssi.item.linkUrl  | 1              | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf.img1.jpg      | linkImage |

  @Search&ResetSSI1
  Scenario Outline: Verify Search & Reset for non existing images.Search on item name
    Given Select the "Grid" view
    And Set the items per page option "45"
    And Validate there are "2" slideshow images
    When Search items by "<itemname>"
    Then Verify that none item is found
    Then Reset the slideshow images search results

    Examples: 
      | itemname |
      | anyWord  |

  @Search&ResetSSI2
  Scenario Outline: Verify Search & Reset for existing images.Search on item name
    Given Select the "Grid" view
    And Set the items per page option "45"
    And Validate there are "<numberOfImages>" slideshow images "<itemname>" "<file>"
    When Search slideshow images by "<itemname>"
    Then Verify that "<numberOfImages>" slideshow images are found
    Then Reset the slideshow images search results
    Examples: 
    | itemname | numberOfImages | file                                                         |
    | ssi.item | 2              | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf.img1.jpg |

  @Search&ResetSSI3
  Scenario Outline: Verify Search & Reset for existing images,search on item name using some wildcards
    Given Select the "Grid" view
    And Set the items per page option "45"
    And Validate there are "<numberOfImages>" slideshow images "<itemname>" "<file>"
    When Search slideshow images by "<itemname>"
    Then Verify that "<numberOfImages>" slideshow images are found using "<wildcard>"
    Then Reset the slideshow images search results
    Examples: 
    | itemname      | wildcard | numberOfImages | file                                                         |
    | ssi.itemTest* | *        | 3              | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf.img1.jpg |

  @Search&ResetSSI4
  Scenario Outline: Verify Search & Reset for existing images, search on content of document kind pdf,word,pptx
    Given Select the "Grid" view
    And Set the items per page option "45"
    And Validate there are "<numberOfImages>" slideshow images "<itemname>" "<file>"
    When Search slideshow images by "<content>"
    Then Verify that "<numberOfImages>" slideshow images are found with "<itemname>"
    Then Reset the slideshow images search results
    Examples: 
    | itemname         | content  | numberOfImages | file                                                              |
    | ssi.item.DocPdf  | testing  | 1              | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf_pdf_doc.pdf   |
    | ssi.item.DocWord | testing1 | 1              | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf_word_doc.docx |
    | ssi.item.DocPPT  | testing2 | 1              | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf_pp_doc.pptx   |

  @Search&ResetSSI4
  Scenario Outline: Verify Search & Reset for existing images, search on link url
    Given Select the "Grid" view
    And Set the items per page option "45"
    And Validate there are "<numberOfImages>" slideshow images "<itemname>" "<file>" "<link>"
    When Search slideshow images by "<link>"
    Then Verify that "<numberOfImages>" slideshow images are found with "<itemname>"
    Then Reset the slideshow images search results
    Examples: 
    | itemname         | numberOfImages | file                                                         | link      |
    | ssi.item.linkUrl | 1              | C:\svnqa\OB Cucumber Automation\Onbrand\TestData\sf.img1.jpg | linkImage |

  #Virginia Pagination
  @paginationSSI1
  Scenario: Verify Slideshow images paginagion buttons when there are either single or no matches. 
            The top and bootom pagination buttons are not visible if there is not any created item or
  			the number of created item is lower than the selected item per page option(only a number button is visible)    			 
    Given Select the "Grid" view
    And Set the items per page option "45"
    When Search 1 slideshow images with "sf.item.search" item name
    Then Verify the "top" pagination bar displays "1" page
    And Verify the "bottom" pagination bar displays "1" page
    When Search 0 slideshow images with "anyworddd" item name
    Then Verify the pagination buttons are not visible on "top" pagination bar
    Then Verify the pagination buttons are not visible on "bottom" pagination bar
    
  @paginationSSI2
  Scenario: Slideshow images top pagination buttons behave properly when using 'number','next' and 'previous' to navigate the available pages
    	    All top pagination buttons are visible when the number of created items is more than the selected items per page 
    Given Select the "Grid" view
    And Validate there are "11" slideshow images
    When Set the items per page option "5"
    Then Verify number of pages is correct on slideshow images "top" pagination
    Then Verify slideshow images pagination works using number of page
    Then Verify "next" icon is not displayed on slideshow images
    Then Verify slideshow images pagination works using previous
    Then Verify "previous" icon is not displayed on slideshow images
    Then Verify slideshow images pagination works using next

  @paginationSSI3
  Scenario: Slideshow images bottom pagination buttons behave properly when using 'number','next' and 'previous' to navigate the available pages
    	    All bottom pagination buttons are visible when the number of created items is more than the selected 'Items Per Page' option
    Given Select the "Grid" view
    And Validate there are "11" slideshow images
    When Set the items per page option "5"
    Then Verify number of pages is correct on slideshow images "bottom" pagination
    Then Verify slideshow images pagination works using number of page
    Then Verify "next" icon is not displayed on slideshow images
    Then Verify slideshow images pagination works using previous
    Then Verify "previous" icon is not displayed on slideshow images
    Then Verify slideshow images pagination works using next

  #Virginia ItemsPerPage
  @itemsperpageSSI1
  Scenario Outline: The number of items per page in the slideshow images grid view is consistent with the selected 'Items Per Page' option
    Given Select the "Grid" view
    And Set the items per page option "45"
    And Validate there are "<numberOfItems>" slideshow images
    When Set the items per page option "<itemsPerPage>"
    Then Verify that "<numberOfItems>" slideshow images are displayed
    Examples: 
    | itemsPerPage | numberOfItems |
    | 5            | 5             |
	| 15           | 15            |
   #| 30	       | 30            |
   #| 45	       | 45            |

  @itemsperpageSSI2
  Scenario Outline: The number of items per page in the slideshow images list view is consistent with the selected 'Items Per Page' option
    Given Select the "List" view
    And Set the items per page option "45"
    And Validate there are "<numberOfItems>" slideshow images
    When Set the items per page option "<itemsPerPage>"
    Then Verify that "<numberOfItems>" slideshow images are displayed
    Examples: 
    | itemsPerPage | numberOfItems |
    | 5            | 5             |
    | 15           | 15            |
   #| 30	       | 30            |
   #| 45	       | 45            |

  @DeleteSlideShowImages
  Scenario: Delete all created slideshow images during the execution of feature (with ssi.item as prefix)
   Given Select the "List" view
   And Set the items per page option "45"
   And Delete all slideshow images
