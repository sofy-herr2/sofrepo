Feature: Testing the Brand/Article section functionaity in frontend and Admin section of the Onbrand Application

  @Prerequisite_brand_tests
  Scenario: Prerequisite for brand tests
    Given Login to the system "qa1@vyre.com" "Testing1"
    And Click on assets menu
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\Brand Desert.jpg"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\Brand hydrangeas.jpg"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\Brand Jellyfish.jpg"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\brand Red flower.jpg"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\brand_Lighthouse.jpg"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\Brand_Penguins.jpg"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\Brand_text1.txt"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\Brand_Tulips.jpg"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\BrandTestPDF.pdf"
    Then Click on upload assets button
    When Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\BrandWildlife.wmv"
    Then Approve the bulk uploaded assets

  @test2
  Scenario: Add new child section
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to admin section
    When Navigate to "Brand" tab
    Then Add parent section "menu1"
    Then Verify section "menu1"

  @test3
  Scenario: Rename child section
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to admin section
    When Navigate to "Brand" tab
    And Rename section "menu1" to "menu1__modified"
    Then Verify section "menu1__modified"

  @test4
  Scenario: Delete section
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to admin section
    When Navigate to "Brand" tab
    And Delete section "menu1__modified"
    Then Verify "menu1__modified" does not exist

  @test5
  Scenario: Create unassigned article
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to admin section
    When Navigate to "Brand" tab
    And Verify article's mandatory fields
    And Click create new article button
    And Create article "Article1"

  @test6
  Scenario: Delete unassigned article
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to admin section
    When Navigate to "Brand" tab
    Then Delete article "Article1"

  @test7
  Scenario Outline: Create Sections and Subsections
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to admin section
    When Navigate to "Brand" tab
    Then Add parent section "<parent>"

    Examples: 
      | parent |
      | menu1  |
      | menu2  |
      | menu3  |
      | menu4  |
      | menu5  |

  @test8
  Scenario Outline: Create Sections and Subsections
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to admin section
    When Navigate to "Brand" tab
    Then Add child section "<parent>" "<child>"

    Examples: 
      | parent | child      |
      | menu1  | sub1       |
      | menu2  | sub2       |
      | menu3  | sub3       |
      | menu4  | sub4       |
      | menu5  | sub5_one   |
      | menu5  | sub5_two   |
      | menu5  | sub5_three |

  @test9
  Scenario Outline: Create assigned articles
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to admin section
    When Navigate to "Brand" tab
    And Create article "<article>" in "<child>"

    Examples: 
      | article | child    |
      | aaa     | sub1     |
      | bbb     | sub1     |
      | ccc     | sub2     |
      | ddd     | sub3     |
      | eee     | sub1     |
      | fff     | sub5_two |

  @test10
  Scenario Outline: Verifiy the articles in tree
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to admin section
    When Navigate to "Brand" tab
    And Verify article tree "<section>"  "<article>"

    Examples: 
      | section  | article |
      | sub1     | aaa     |
      | sub1     | bbb     |
      | sub2     | ccc     |
      | sub3     | ddd     |
      | sub1     | eee     |
      | sub5_two | fff     |

  @test11
  Scenario Outline: Create a chapter in an article
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to admin section
    When Navigate to "Brand" tab
    And Create chapter "<chapter>" in article "<article>"

    Examples: 
      | chapter  | article |
      | chapter1 | aaa     |
      | chapter2 | aaa     |
      | chapter3 | aaa     |
      | chapter1 | bbb     |
      | chapter2 | bbb     |
      | chapter3 | bbb     |
      | chapter1 | fff     |
      | chapter2 | fff     |
      | chapter3 | fff     |

  @test12
  Scenario Outline: Edit chapter body
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to admin section
    When Navigate to "Brand" tab
    And Edit chapter body "<chapter>" "<article>" "<body>"

    Examples: 
      | chapter  | article | body                                    |
      | chapter1 | aaa     | This is the body text chap1 article aaa |
      | chapter2 | aaa     | This is the body text chap2 article aaa |
      | chapter3 | aaa     | This is the body text chap3 article aaa |
      | chapter1 | bbb     | This is the body text chap1 article bbb |
      | chapter2 | bbb     | This is the body text chap2 article bbb |
      | chapter3 | bbb     | This is the body text chap3 article bbb |
      | chapter1 | fff     | This is the body text chap1 article fff |
      | chapter3 | fff     | This is the body text chap3 article fff |

  @test13
  Scenario Outline: Add media files
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to admin section
    When Navigate to "Brand" tab
    And Add media files "<chapter>" "<article>" "<mediaName>"

    Examples: 
      | chapter  | article | mediaName            |
      | chapter1 | aaa     | Brand Desert.jpg     |
      | chapter1 | aaa     | Brand_text1.txt      |
      | chapter1 | aaa     | BrandTestPDF.pdf     |
      | chapter1 | aaa     | BrandWildlife.wmv    |
      | chapter2 | aaa     | BrandWildlife.wmv    |
      | chapter2 | aaa     | Brand Jellyfish.jpg  |
      | chapter2 | aaa     | brand Red flower.jpg |
      | chapter3 | fff     | BrandWildlife.wmv    |
      | chapter3 | fff     | Brand Jellyfish.jpg  |
      | chapter3 | fff     | brand Red flower.jpg |

  @test14
  Scenario Outline: Edit media position and style
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to admin section
    When Navigate to "Brand" tab
    And Edit media position and style "<chapter>" "<article>" "<position>" "<style>"

    Examples: 
      | chapter  | article | position | style     |
      | chapter1 | aaa     | Top      | Stacked   |
      | chapter2 | aaa     | Bottom   | Slideshow |
      | chapter3 | fff     | Left     | Slideshow |

  @test15
  Scenario Outline: Add attached files
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to admin section
    When Navigate to "Brand" tab
    And Add attached files "<chapter>" "<article>" "<mediaName>"

    Examples: 
      | chapter  | article | mediaName            |
      | chapter1 | aaa     | Brand Desert.jpg     |
      | chapter1 | aaa     | Brand_text1.txt      |
      | chapter1 | aaa     | BrandTestPDF.pdf     |
      | chapter1 | aaa     | BrandWildlife.wmv    |
      | chapter2 | aaa     | BrandWildlife.wmv    |
      | chapter2 | aaa     | Brand Jellyfish.jpg  |
      | chapter2 | aaa     | brand Red flower.jpg |
      | chapter3 | fff     | brand Red flower.jpg |
      | chapter3 | fff     | BrandWildlife.wmv    |

  @test16
  Scenario Outline: Remove media files
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to admin section
    When Navigate to "Brand" tab
    And Remove media files "<chapter>" "<article>" "<mediaName>"

    Examples: 
      | chapter  | article | mediaName        |
      | chapter2 | aaa     | BrandTestPDF.pdf |

  @test17
  Scenario Outline: Remove attached files
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to admin section
    When Navigate to "Brand" tab
    And Remove attached files "<chapter>" "<article>" "<mediaName>"

    Examples: 
      | chapter  | article | mediaName           |
      | chapter3 | fff     | Brand Jellyfish.jpg |

  @test18
  Scenario Outline: Remove chapter
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to admin section
    When Navigate to "Brand" tab
    And Remove chapter "<chapter>" "<article>"

    Examples: 
      | chapter  | article |
      | chapter1 | fff     |

  @test19
  Scenario Outline: Verify brand section in frontend
    Given Login to the system "qa1@vyre.com" "Testing1"
    And Verify brand menu "<menu>" "<submenu>" "<article1>" "<article2>" "<article3>"

    Examples: 
      | menu  | submenu  | article1 | article2 | article3 |
      | menu1 | sub1     | aaa      | bbb      | eee      |
      | menu2 | sub2     | ccc      |          |          |
      | menu3 | sub3     | ddd      |          |          |
      | menu4 |          |          |          |          |
      | menu5 |          |          |          |          |
      | menu5 | sub5_two | fff      |          |          |
      | menu5 |          |          |          |          |

  @test20
  Scenario: Verify article in frontend
    Given Login to the system "qa1@vyre.com" "Testing1"
    And Goto article "menu1" "sub1" "aaa"
    And Verify article chapters "aaa"
        
    
