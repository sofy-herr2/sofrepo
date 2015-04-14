@Workflow
Feature: Workflow

  Scenario: Verify Workflow Page
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to "Workflow" tab
    Then Verify Workflow page

  Scenario: Verify Create Project Page and verify Mandatory values
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to "Workflow" tab
    And Click on the button "Create Project"
    Then Verify Create Project objects
    When Enter Project Missing details
      | Field       | Value |
      | Name        |       |
      | Description |       |
    And Click on the button "Create Project"
    Then Verify mandatory error messages for create project page

  Scenario: Create Project
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to "Workflow" tab
    When Click on the button "Create Project"
    And Enter Project details
      | Field           | Value                |
      | Name            | Project1             |
      | Description     | Project1 description |
      | Objectives      | test                 |
      | Target Audience | test                 |
    When Click on the button "Create Project"
    Then Verify artwork page
    And Verify discussion page
    And Verify tasks page
    And Verify supporting files page
    And Verify activity and members page

  Scenario: Add/Remove Project members
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to the Workflow Project "Project1"
    And Click on the project edit link
    And Click on the button "Add Member(s)"
    Then Verify Add member popup
    And Add project member "QA Automation User 2"
    And Click on the button "Save Changes"
    And Click on the project edit link
    And Verify project members "QA Automation User 1" "QA Automation User 2"
    Then Remove project member "QA Automation User 2"
    And Click on the button "Save Changes"
    And Click on the project edit link
    And Verify no project members "QA Automation User 2"

  Scenario: Edit Project
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to the Workflow Project "Project1"
    And Click on the project edit link
    And Clear all Project fields
    When Enter Project details
      | Field           | Value                |
      | Name            | Project2             |
      | Description     | Project2 description |
      | Objectives      | test1                |
      | Target Audience | test1                |
    And Add taxonomy "Marketing Activity"
      | Digital                |
      | Branding               |
      | Relationship Marketing |
    And Click on the button "Add Member(s)"
    And Add project member "QA Automation User 2"
    And Click on the button "Save Changes"
    And Click on the project edit link
    Then Verify Project details
      | Field           | Value                |
      | Name            | Project2             |
      | Description     | Project2 description |
      | Objectives      | test1                |
      | Target Audience | test1                |
    And Verify taxonomy "Marketing Activity"
      | Digital                |
      | Branding               |
      | Relationship Marketing |
    # Revert back changes
    And Clear all Project fields
    And Enter Project details
      | Field           | Value                |
      | Name            | Project1             |
      | Description     | Project1 description |
      | Objectives      | test                 |
      | Target Audience | test                 |
    And Click "Save Changes"

  Scenario: Upload file in artwork and Verify Creative page
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to the Workflow Project "Project1"
    And Click on the button "Upload File(s)"
    And Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\WF_image1.jpg"
    And Navigate to creative page of "Project1" and "WF_image1.jpg"
    Then Verify Creative page with members
      | QA Automation User 1 |
      | QA Automation User 2 |

  Scenario: Add Annotations to a file in Creative page
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to creative page of "Project1" and "WF_image1.jpg"
    Then Add Annotations "This is a test annotation"
    And Verify Annotations

  Scenario: Add Comments to a file in Creative page
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to creative page of "Project1" and "WF_image1.jpg"
    Then Add and Verify Comments "This is a test comment"

  Scenario: Share asset in Creative page
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to creative page of "Project1" and "WF_image1.jpg"
    Then Share creative asset with "ymuthurajah@northplains.com"

  Scenario: Download asset in Creative page
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to creative page of "Project1" and "WF_image1.jpg"
    Then Download creative asset

  Scenario: Approve asset in Creative page
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to creative page of "Project1" and "WF_image1.jpg"
    Then Approve creative asset and verify approved "WF_image1.jpg"

  Scenario: Reject asset in Creative page
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to "Workflow" tab
    When Navigate to the Workflow Project "Project1"
    And Click on the button "Upload File(s)"
    And Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\WF_image2.jpg"
    And Navigate to creative page of "Project1" and "WF_image2.jpg"
    Then Reject creative asset and verify rejected "WF_image2.jpg"

  Scenario: Request Revision in Creative page
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to creative page of "Project1" and "WF_image1.jpg"
    Then Request revision from "QA Automation User 2"

  Scenario: Upload Revision in Creative page
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to creative page of "Project1" and "WF_image1.jpg"
    Then Upload revision "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\WF_image3.jpg"
    And Verify latest revision uploaded

  Scenario: Request/Cancel File in artwork
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to the Workflow Project "Project1"
    And Click on the button "Request File(s)"
    Then Request file from "QA Automation User 2"
    And Click on the button "Cancel Request"
    Then Verify request file cancelled

  Scenario: Promote to Fulfilment in Artwork
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to the Workflow Project "Project1"
    And Click on the button "Promote to Fulfilment"
    Then Verify promoted to fulfilment

  Scenario: Skip to Fulfilment in Artwork
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Create a workflow project
      | Field           | Value                    |
      | Name            | ProjectSkip              |
      | Description     | ProjectSkip description1 |
      | Objectives      | test                     |
      | Target Audience | test                     |
    And Click on the button "Skip to Fulfilment"
    Then Verify skipped to fulfilment

  Scenario: Request/Cancel File in Fulfilment page
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to the Workflow Project "Project1"
    And Click on the button "Request File(s)"
    Then Request fulfilment from "QA Automation User 2"
    And Click on the button "Cancel Request"
    Then Verify request file cancelled

  Scenario: Upload file in fulfilment page
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to the Workflow Project "Project1"
    And Click on the button "Upload File(s)"
    And Upload file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\WF_image2.jpg"
    Then Verify file "WF_image1.jpg" uploaded is in "Reviewing" stage

  Scenario: Verify Enrichment page and Mandatory fields
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to enrichment page of "Project1" and "WF_image1.jpg"
    And Verify enrichment page objects
    Then Clear enrichment fields
    And Verify enrichment page mandatory fields

  Scenario: Update Enrichment page and Save
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to enrichment page of "Project1" and "WF_image1.jpg"
    And Clear enrichment fields
    And Enter enrichment page details
      | Field            | Value           |
      | Name             | WF_Update1      |
      | Keyword          | workflow,test   |
      | Client           | workflow client |
      | Restriction Type | None            |
      | Condition        |                 |
    And Add taxonomy "Country"
      | Albania |
      | Chile   |
      | China   |
    And Click on the button "Save"
    And Navigate to enrichment page of "Project1" and "WF_Update1"
    Then Verify enrichment page details
      | Field            | Value           |
      | Name             | WF_Update1      |
      | Keyword          | workflow,test   |
      | Client           | workflow client |
      | Restriction Type | None            |
      | Condition        |                 |
    And Verify taxonomy "Country"
      | Albania |
      | Chile   |
      | China   |

  Scenario: Update Enrichment page and Cancel without Saving
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to enrichment page of "Project1" and "WF_Update1"
    And Clear enrichment fields
    And Enter enrichment page details
      | Field            | Value                    |
      | Name             | WF_Update2               |
      | Keyword          | new keyword              |
      | Client           | new client               |
      | Restriction Type | Unlimited                |
      | Condition        | This is a test condition |
    And Click on the button "Cancel without saving"
    Then Verify the enrichment file name is not saved "WF_Update2"

  Scenario: Update Enrichment page and Save & Send for Approval
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to enrichment page of "Project1" and "WF_Update1"
    And Clear enrichment fields
    And Enter enrichment page details
      | Field            | Value                    |
      | Name             | WF_Update2               |
      | Keyword          | new keyword              |
      | Client           | new client               |
      | Restriction Type | Unlimited                |
      | Condition        | This is a test condition |
    And Click on the button "Save & Send for Approval"
    Then Verify the enrichment file is under pending approval "WF_Update2"

  Scenario: Start a Project Discussion
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to the Workflow Project "Project1"
    And Navigate to "Discussions" tab
    Then Start a discussion and verify "This is a test discussion"
    And Leave a discussion comment "This is a discussion comment"

  Scenario: Create a Project task and delegate myself
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to the Workflow Project "Project1"
    And Navigate to "Tasks" tab
    Then Create a task "Project Task" and delegate

  Scenario: Create a Project task and leave unassigned
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to the Workflow Project "Project1"
    And Navigate to "Tasks" tab
    Then Create a task "Project Task1" and leave unassigned

  @valid1
  Scenario: Upload Supporting File and Link asset to the project
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to the Workflow Project "Projecttest"
    And Navigate to "Supporting Files" tab
    Then Upload project supporting file "C:\svnqa\OB Cucumber Automation\Onbrand\TestData\WF_supportingfile1.jpg"
    When Navigate to the Workflow Project "Projecttest"
    And Navigate to "Supporting Files" tab
    Then Link an asset to the project "WF_linkasset.jpg"
    And Verify supporting file "WF_supportingfile1.jpg" and linked asset "WF_linkasset.jpg" are added to project

  Scenario: Complete Project in fulfilment page
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to the Workflow Project "Project1"
    And Click on the button "Complete Project"
    Then Verify project is in completed stage
