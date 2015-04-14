Feature: Testing the Brand Users |Admin section functionality in frontend of the Onbrand Application

  Background: 
    Given Login to the system "qa1@vyre.com" "Testing1"
    When Navigate to admin section
    And Navigate to "Users" menu
    
  @VerifyBUP    
  Scenario: Verify that Brand Admin Users page is loaded correctly       
  #Create New Item button is displayed? true/false
    Then Verify "Users" page "false" 
    And Verify tab components
    
  @VerifyFieldsAndRequiredFieldsBU
  Scenario Outline: Verify the required fields for all User tabs
    When Go to "<brandUserTab>" tab      
    When Navigate to "<userName>"
    Then Verify user components "<brandUserTab>"
    And Verify errors
    
   	Examples:
  	| brandUserTab			| userName 	  		  |
  	| Approved 				| ba.user10 ba.user10 | 
    | Pending approval 		| ba.user8 ba.user8   | 
    | Pending user response | ba.user7 ba.user7   | 
    | Rejected 		    	| ba.user11 ba.user11 | 
    | Inactive				| ba.user5 ba.user5   |
    | Deactivated By System | ba.user6 ba.user6   | 
    
  @EditAndCancelBU
  Scenario Outline: Cancel after editing a user
    When Go to "<brandUserTab>" tab      
    When Navigate to "<userName>"
    And Fill user data
    | fields	  	 | userName	  	 	 |
  	| First Name  	 | ba.NewName 	 	 | 
    | Surname 	  	 | ba.NewSurname 	 | 
    | Permissions 	 | TestConfig 		 | 
    | Company/Agency | ba.NewCompany 	 | 
    | Department 	 | ba.Newdepartment  |
    | Job title 	 | ba.NewJob		 |
    | Note 			 | ba.NewNote		 |
    And "Cancel" the item
    Then Verify "ba.NewName" item is not displayed 
        
    Examples:
  	| brandUserTab			| userName 	  		  |
  	| Approved 				| ba.user10 ba.user10 | 
    | Pending approval 		| ba.user8 ba.user8   | 
    | Pending user response | ba.user7 ba.user7   | 
    | Rejected 		    	| ba.user11 ba.user11 | 
    | Inactive				| ba.user5 ba.user5   |
    | Deactivated By System | ba.user6 ba.user6   | 
    
  @EditBU
  Scenario Outline: Edit a user
    When Go to "<brandUserTab>" tab      
    When Navigate to "<userName>"
    When Edit the user
    | fields	  	 | values  	  	 	 |
  	| First Name  	 | ba.NewName 	 	 | 
    | Surname 	  	 | ba.NewSurname 	 | 
    | Permissions 	 | TestConfig 		 | 
    | Company/Agency | ba.NewCompany 	 | 
    | Department 	 | ba.Newdepartment  |
    | Job title 	 | ba.NewJob		 |
    | Note 			 | ba.NewNote		 |
    And Go to "Approved" tab
    Then Verify "ba.NewName" item 
        
    Examples:
  	| brandUserTab			| userName 	  		  |
  	| Approved 				| ba.user10 ba.user10 |
    | Rejected 		    	| ba.user12 ba.user12 | 
    | Inactive				| ba.user11 ba.user11 |
    | Deactivated By System | ba.user4 ba.user4   |
    
  @RejectBU
  Scenario Outline: Reject a user
    When Go to "<brandAssetTab>" tab  
    When Navigate to "<userName>"
    When "Reject" the item
    Then Verify if "<userName>" was "rejected"
            
  	Examples:
  	| brandAssetTab	   | userName      	   | 
  	| Pending approval | ba.user8 ba.user8 | 
  	
  @ApproveBU
  Scenario Outline: Approve a user
    When Go to "<brandAssetTab>" tab  
    When Navigate to "<userName>"
    When "Approve" the item
    Then Verify if "<userName>" was "approved"
            
  	Examples:
  	| brandAssetTab	   | userName      	   | 
  	| Pending approval | ba.user3 ba.user3 | 
  
  @DeactivateBU
  Scenario Outline: Deactivate a user
    When Go to "<brandAssetTab>" tab  
    When Navigate to "<userName>"
    When "Deactivate" the item
    Then Verify if "<userName>" was "deactivated"
            
  	Examples:
  	| brandAssetTab | userName     	    | 
  	| Approved 	 	| ba.user9 ba.user9 | 
  	
@ListView
  Scenario Outline: Verifies that the column names displayed in the list view are correct for all the user tabs
  When Go to "<brandUserTab>" tab
  Then Verify the asset attributes
    | Name         		|
    | Email     		|
    | Marketing contact |
    | Creation Date     |
    | Last Logged In  	|

    Examples:
  	| brandUserTab     		|
  	| Approved             	|
  	| Pending approval 		|
  	| Pending user response |
  	| Rejected          	|
  	| Inactive          	|
  	| Deactivated By System |
