@tag

Feature:Error validation
 I want to use this template for my feature file
 
 @ErrorValidation
 Scenario Outline: Title of your 
    Given I landed on Ecommerce Page
    When  Logged in with username <name> and password <password>
    Then  "Incorrect email or password." message is displayed
    
    
    Examples:
    |name                |password       | 
    |santoman@gmail.com  |Santoman@1109  |  
    
    
    
    
    
    
    
    
    
    
    