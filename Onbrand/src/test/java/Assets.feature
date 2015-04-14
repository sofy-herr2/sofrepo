Feature: This is to test Assets section

  @Assets0
  Scenario: To verify the single asset upload and save & approve the asset
    Given Login to the system "qa1@vyre.com" "Testing1"
    # verify Upload Page and Upload an asset
    # after uploading the asset, save & approve the asset
    # first asset
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsTestImage1.jpg"
    Then Upload an asset and approve "AssetsTestImage1.jpg"
    # second asset
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsTestImage2.jpg"
    Then Upload an asset and approve "AssetsTestImage2.jpg"

  @Assets1
  Scenario: To verify the single asset upload and save & send for approval the asset
    Given Login to the system "qa1@vyre.com" "Testing1"
    # verify Upload Page and Upload an asset
    # after uploading the asset, save & send for approval the asset
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsTestImage3.jpg"
    Then Upload an asset and send for approval "AssetsTestImage3.jpg"
    Then Approve the asset "AssetsTestImage3.jpg"

  @Assets2
  Scenario: To verify the bulk asset upload and save & approve the asset
    Given Login to the system "qa1@vyre.com" "Testing1"
    # upload bulk assets and save & approve
    # first bulk asset
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsTestDoc.doc"
    Then Bulkupload asset "AssetsTestDoc.doc"
    # second bulk asset
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsTestDocx.docx"
    Then Bulkupload asset "AssetsTestDocx.docx"
    # third bulk asset
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsTestImage4.jpg"
    Then Bulkupload asset "AssetsTestImage4.jpg"
    # fourth bulk asset
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsTestImage5.jpg"
    Then Bulkupload asset "AssetsTestImage4.jpg"
    # now save & approve the bulk uploaded assets
    Then Approve the bulk uploaded assets

  @Assets3
  Scenario: To verify the bulk asset upload and save & send for approval the asset
    Given Login to the system "qa1@vyre.com" "Testing1"
    # upload bulk assets and send for approve
    # first bulk asset
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsTestImage5.jpg"
    Then Bulkupload asset "AssetsTestImage5.jpg"
    # second bulk asset
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsTestPDF.pdf"
    Then Bulkupload asset "AssetsTestPDF.pdf"
    # third bulk asset
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsTestVideo.mp4"
    Then Bulkupload asset "AssetsTestVideo.mp4"
    # fourth bulk asset
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsTestImage6.jpg"
    Then Bulkupload asset "AssetsTestImage6.jpg"
    # now send for approval the bulk uploaded assets
    Then Send those bulk uploaded assets to approve
    #login as admin and approve those bulk uploaded assets
    Then Approve the bulk uploaded assets from admin page "AssetsTestImage5.jpg" "AssetsTestPDF.pdf" "AssetsTestVideo.mp4" "AssetsTestImage6.jpg"

  @Assets4
  Scenario: To verify to reject some assets, publish a rejected asset and delete a rejected asset
    Given Login to the system "qa1@vyre.com" "Testing1"
    # first asset
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsRejected.jpg"
    Then Upload an asset and send for approval "AssetsRejected.jpg"
    # second asset
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsPublishRejected.jpg"
    Then Upload an asset and send for approval "AssetsPublishRejected.jpg"
    Then Reject the asset "AssetsRejected.jpg" "Rejecting this asset"
    Then Reject the asset "AssetsPublishRejected.jpg" "Rejecting this asset to republish"
    Then Publish the rejected asset "AssetsPublishRejected.jpg"
    Then Delete the rejected asset "AssetsRejected.jpg"

  @Assets5
  Scenario: To verify the elements present in all pages related to assets
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to "Assets" tab
    Then Verify the fields in assets search page
    When Navigate to "Collections" tab
    Then Verify the fields in collections page
    Then Verify the fields in create new collection page
    When Navigate to "Upload" tab
    Then Verify the fields in upload page
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsMetadatapageVerify1.jpg"
    Then Verify the fields in metadata required page "AssetsMetadatapageVerify1.jpg"

  @Assets6
  Scenario: To verify the assets search page. Search an asset using assets name, facet searches, keyword searches. Save a search and delete the search.
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to "Assets" tab
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsSearch1.jpg"
    Then Upload an asset and approve "AssetsSearch1.jpg"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\Assetsimagesearch.jpg"
    Then Upload an asset and approve "Assetsimagesearch.jpg"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsPDFsearch.pdf"
    Then Upload an asset and approve "AssetsPDFsearch.pdf"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\Assetsdocsearch.doc"
    Then Upload an asset and approve "Assetsdocsearch.doc"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\Assetsvideosearch.mp4"
    Then Upload an asset and approve "Assetsvideosearch.mp4"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsBMPsearch.bmp"
    Then Upload an asset and approve "AssetsBMPsearch.bmp"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\Assetstextsearch.txt"
    Then Upload an asset and approve "Assetstextsearch.txt"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\Assetsexcelsearch.xlsx"
    Then Upload an asset and approve "Assetsexcelsearch.xlsx"
    # NAME SEARCH
    Then Search for an asset and click on asset "AssetsSearch1.jpg"
    Then Search for an asset and click on asset "AssetsSear*"
    Then Search for a non existing asset with name "Qwerty"
    Then Search for an asset "AssetsSearch1.jpg" and reset
    Then Search for an asset "AssetsSearch1.jpg" and remove query
    #PAGE FACET SEARCH
    Then Search for page facet "File Type" "doc" "Assetsdocsearch.doc"
    Then Reset the page facet search "File Type" "doc"
    Then Search for page facet "File Type" "jpg" "Assetsimagesearch.jpg"
    Then Reset the page facet search "File Type" "jpg"
    Then Search for page facet "File Type" "pdf" "AssetsPDFsearch.pdf"
    Then Reset the page facet search "File Type" "pdf"
    Then Search for page facet "File Type" "mp4" "Assetsvideosearch.mp4"
    Then Reset the page facet search "File Type" "mp4"
    Then Search for page facet "File Type" "bmp" "AssetsBMPsearch.bmp"
    Then Reset the page facet search "File Type" "bmp"
    Then Search for page facet "File Type" "txt" "Assetstextsearch.txt"
    Then Reset the page facet search "File Type" "txt"
    Then Search for page facet "File Type" "xlsx" "Assetsexcelsearch.xlsx"
    Then Reset the page facet search "File Type" "xlsx"
    #KEYWORD SEARCH
    And Add keywords to an asset "AssetsSearch1.jpg" "Selenium"
    Then Search for page facet "Keywords" "Selenium" "AssetsSearch1.jpg"
    #SAVED SEARCH
    Then Save a search "saved search name" "AssetsSearch1.jpg"
    Then Delete the saved search "saved search name"

  @Assets7
  Scenario: To verify asset view page for image, video and document file types.
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to "Assets" tab
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\Assetsvideoview.mp4"
    Then Upload an asset and approve "Assetsvideoview.mp4"
    Then Search for an asset and click on asset "Assetsvideoview.mp4"
    Then Verify the asset view page "Assetsvideoview.mp4"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\Assetsdocview.doc"
    Then Upload an asset and approve "Assetsdocview.doc"
    Then Search for an asset and click on asset "Assetsdocview.doc"
    Then Verify the asset view page "Assetsdocview.doc"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\Assetsimageview.jpg"
    Then Upload an asset and approve "Assetsimageview.jpg"
    Then Search for an asset and click on asset "Assetsimageview.jpg"
    Then Verify the asset view page "Assetsimageview.jpg"

  @Assets8
  Scenario: To verify sharing an asset.
    # failing scenario - sharing an asset is not working, not able to enter email address
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to "Assets" tab
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\Assetsshare.jpg"
    Then Upload an asset and approve "Assetsshare.jpg"
    Then Search for an asset and click on asset "Assetsshare.jpg"
    Then Share the searched asset with the user "qa2@vyre.com"

  @Assets9
  Scenario: To verify creation of collection, add asset to that collection, remove asset from the collection and delete the collection.
    Given Login to the system "qa1@vyre.com" "Testing1"
    Then Create a collection "Assets Collection" "private"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsCollection3.jpg"
    Then Upload an asset and approve "AssetsCollection3.jpg"
    Then Search for an asset and click on asset "AssetsCollection3.jpg"
    Then Add asset to collection "AssetsCollection3.jpg" "Assets Collection"
    Then Remove asset from collection "AssetsCollection3.jpg" "Assets Collection"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsTestImage9.jpg"
    Then Upload an asset and approve "AssetsTestImage9.jpg"
    Then Search for an asset "AssetsTestImage9.jpg"
    Then Add asset to existing collection from search results "AssetsTestImage9.jpg" "Assets Collection"
    Then Search for an asset "AssetsTestImage9.jpg"
    Then Add asset to new collection from search results "AssetsTestImage9.jpg" "Assets Collection New"
    Then Delete the collection "Assets Collection"
    Then Delete the collection "Assets Collection New"

  @Assets10
  Scenario: To verify adding asset to watchlist
    Given Login to the system "qa1@vyre.com" "Testing1"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsWatchlist.jpg"
    Then Upload an asset and approve "AssetsWatchlist.jpg"
    Then Search for an asset and click on asset "AssetsWatchlist.jpg"
    And Add asset to watchlist "AssetsWatchlist.jpg"
    Then Remove asset from watchlist "AssetsWatchlist.jpg"
    Then Search for an asset "AssetsWatchlist.jpg"
    And Add asset to watchlist from results page "AssetsWatchlist.jpg"
    Then Remove asset from watchlist from results page "AssetsWatchlist.jpg"

  @Assets11
  Scenario: To verify linking of an asset with another asset
    # failing scenario - asset linking is not working, need to raise a jira report
    Given Login to the system "qa1@vyre.com" "Testing1"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsLink1.jpg"
    Then Upload an asset and approve "AssetsLink1.jpg"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsLink2.jpg"
    Then Upload an asset and approve "AssetsLink2.jpg"
    Then Search for an asset and click on asset "AssetsLink1.jpg"
    Then Link an asset "AssetsLink1.jpg" "AssetsLink2.jpg"
    Then Remove the linked asset "AssetsLink2.jpg"

  @Assets12
  Scenario: To verify adding single and multiple assets to basket and empty the basket
    Given Login to the system "qa1@vyre.com" "Testing1"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsBasket1.jpg"
    Then Upload an asset and approve "AssetsBasket1.jpg"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsBasket2.jpg"
    Then Upload an asset and approve "AssetsBasket2.jpg"
    Then Search for an asset and click on asset "AssetsBasket1.jpg"
    Then Add the asset to basket "AssetsBasket1.jpg"
    Then Remove the asset from basket "AssetsBasket1.jpg"
    Then Search for an asset "AssetsBasket*"
    Then Add multiple assets to basket "AssetsBasket1.jpg" "AssetsBasket2.jpg"
    Then Empty the basket

  @Assets13
  Scenario: To verify ordering unrestricted and restricted assets and various download restrictions
    # failing scenario - ordering unrestricted asset is showing server not found page only in automation, but testing manually works fine
    Given Login to the system "qa1@vyre.com" "Testing1"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsUnrestricted.jpg"
    Then Upload an asset and approve "AssetsUnrestricted.jpg"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsRestricted.jpg"
    Then Upload an asset and approve "AssetsRestricted.jpg"
    Then Search for an asset and click on asset "AssetsUnrestricted.jpg"
    Then Add the asset to basket "AssetsUnrestricted.jpg"
    Then Order and asset "AssetsUnrestricted.jpg" "Low Res"
    Then Edit the asset restriction "AssetsRestricted.jpg" "Limited" "Testing"
    Then Order the restricted asset "AssetsRestricted.jpg" "Mid Res"
    Then Verify download resolutions for image asset "AssetsUnrestricted.jpg"

  @Assets14
  Scenario: To download the asset in various resolutions
    Given Login to the system "qa1@vyre.com" "Testing1"
    Then Download the asset original resolution "AssetsUnrestricted.jpg" "Original"
    Then Download the asset Mid resolution "AssetsUnrestricted.jpg" "Mid Res"
    Then Download the asset Low resolution "AssetsUnrestricted.jpg" "Low Res"
    Then Download the restricted asset low resolution "AssetsRestricted.jpg" "Low Res"
    Then Download the asset from search results page "AssetsTestImage9.jpg" "Original"
    Then Download the asset from search results page "AssetsTestImage9.jpg" "Mid Res"
    Then Download the asset from search results page "AssetsTestImage9.jpg" "Low Res"

  @Assets15
  Scenario: To create public, private collection, public collection. Edit the collection, add asset to collection
    add comment, remove asset from collection. share the collection, and delete the collection

    # failing scenario - adding asset to collection is not working, sharing collection is not working
    Given Login to the system "qa1@vyre.com" "Testing1"
    Then Create a collection "Public Collection" "public"
    Then Create a collection "Private Collection" "private"
    Then Edit a collection "Public Collection" "Edited Public" "Changed to Private" "private"
    Then Edit a collection "Private Collection" "Edited Private" "Changed to Public" "public"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsCollection1.jpg"
    Then Upload an asset and approve "AssetsCollection1.jpg"
    Then Edit a collection by adding asset "Edited Public" "AssetsCollection1.jpg"
    Then Add comment "Edited Public" "this is a comment for public collection"
    Then Remove asset from collection "AssetsCollection1.jpg" "Edited Public"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsCollection2.jpg"
    Then Upload an asset and approve "AssetsCollection2.jpg"
    Then Edit a collection by adding asset "Edited Private" "AssetsCollection2.jpg"
    Then Add comment "Edited Private" "this is a comment for private collection"
    Then Remove asset from collection "AssetsCollection2.jpg" "Edited Private"
    Then Share collection "Edited Public" "priyadarshini lakshminarasimhan"
    Then Logout from the system
    Given Login to the system "plakshminarasimhan@northplains.com" "Vyre123$"
    Then Verify the shared collection "Edited Public"

  @Assets16
  Scenario: To edit asset metadata, taxonomy, restrictions, start & expiry date; add/remove comment, supporting file
    archive asset, re-publish, make asset expire and re-publish, then finally deleting all the assets

    # failing scenario - after adding taxonomy to the asset, the taxonomy tree is not displayed in the asset's search page (Asset type, Language,Market Created ), and in assets view page (Asset type)
    # failing scenario - after removing taxonomy from the asset, the taxonomy tree is displayed in the asset's search page (Marketing Activity)
    Given Login to the system "qa1@vyre.com" "Testing1"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsTestImage7.jpg"
    Then Upload an asset and approve "AssetsTestImage7.jpg"
    And Edit asset mandatory fields "AssetsTestImage7.jpg", "AssetsTestImage7_name_edited", "AssetsTestImage7_description_edited"
    Then Add taxonomy to asset and verify "AssetsTestImage7_name_edited", "Marketing Activity", "Branding"
    Then Add taxonomy to asset and verify "AssetsTestImage7_name_edited", "Asset Type", "Photography"
    Then Add taxonomy to asset and verify "AssetsTestImage7_name_edited", "Language", "English"
    Then Add taxonomy to asset and verify "AssetsTestImage7_name_edited", "Market Created", "Global"
    Then Add taxonomy to asset and verify "AssetsTestImage7_name_edited", "Market Suitability", "Africa"
    Then Remove AssetTaxonomy "AssetsTestImage7_name_edited" "Marketing Activity" "Branding"
    Then Remove AssetTaxonomy "AssetsTestImage7_name_edited" "Asset Type" "Photography"
    Then Remove AssetTaxonomy "AssetsTestImage7_name_edited" "Language" "English"
    Then Remove AssetTaxonomy "AssetsTestImage7_name_edited" "Market Created" "Global"
    Then Remove AssetTaxonomy "AssetsTestImage7_name_edited", "Market Suitability", "Africa"
    # edit Asset Restrictions and Verify
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\AssetsTestImage8.jpg"
    Then Upload an asset and approve "AssetsTestImage8.jpg"
    And Edit asset restriction "AssetsTestImage8.jpg" "None" "Testing"
    And Edit asset restriction "AssetsTestImage8.jpg" "Limited" "Limited Testing"
    And Edit asset restriction "AssetsTestImage8.jpg" "Unlimited" "Unlimited Testing"
    # edit Asset Start, Expiry Date and Remove
    And Edit asset start date and expiry date "AssetsTestImage8.jpg", "17.03.2015", "18.03.2050"
    Then Remove asset expiry date "AssetsTestImage8.jpg"

  @Assets17
  Scenario: To delete the uploaded assets under published state and collections
    Given Login to the system "qa1@vyre.com" "Testing1"
    And Delete the published asset "AssetsTestImage1.jpg"
    And Delete the published asset "AssetsTestImage2.jpg"
    And Delete the published asset "AssetsTestImage3.jpg"
    And Delete the published asset "AssetsTestImage4.jpg"
    And Delete the published asset "AssetsTestImage5.jpg"
    And Delete the published asset "AssetsTestImage6.jpg"
    And Delete the published asset "AssetsTestDoc.doc"
    And Delete the published asset "AssetsTestDocx.docx"
    And Delete the published asset "AssetsTestPDF.pdf"
    And Delete the published asset "AssetsTestVideo.mp4"
    And Delete the published asset "AssetsPublishRejected.jpg"
    And Delete the published asset "AssetsMetadatapageVerify1.jpg"
    And Delete the published asset "AssetsSearch1.jpg"
    And Delete the published asset "Assetsimagesearch.jpg"
    And Delete the published asset "AssetsPDFsearch.pdf"
    And Delete the published asset "Assetsdocsearch.doc"
    And Delete the published asset "Assetsvideosearch.mp4"
    And Delete the published asset "AssetsBMPsearch.bmp"
    And Delete the published asset "Assetstextsearch.txt"
    And Delete the published asset "Assetsexcelsearch.xlsx"
    And Delete the published asset "Assetsvideoview.mp4"
    And Delete the published asset "Assetsdocview.doc"
    And Delete the published asset "Assetsimageview.jpg"
    And Delete the published asset "Assetsshare.jpg"
    And Delete the published asset "AssetsPrivateCollection.jpg"
    And Delete the published asset "AssetsWatchlist.jpg"
    And Delete the published asset "AssetsLink1.jpg"
    And Delete the published asset "AssetsLink2.jpg"
    And Delete the published asset "AssetsBasket1.jpg"
    And Delete the published asset "AssetsBasket2.jpg"
    And Delete the published asset "AssetsUnrestricted.jpg"
    And Delete the published asset "AssetsRestricted.jpg"
    And Delete the published asset "AssetsCollection1.jpg"
    And Delete the published asset "AssetsCollection2.jpg"
    And Delete the published asset "AssetsTestImage7_name_edited.jpg"
    And Delete the published asset "AssetsTestImage8.jpg"
    And Delete the published asset "AssetsTestImage9.jpg"
    Then Delete the collection "Edited Public"
    Then Delete the collection "Edited Private"
