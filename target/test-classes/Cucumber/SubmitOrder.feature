
@tag
Feature: Purchase the order from Ecommerce Websit
  I want to use this template for my feature file
  
  Background:
   Given I landed on Ecommerce Page
  
  
  
  
  @Regression 
  Scenario Outline: Positive Test of submitting the order
   Given Logged in with username <name> and password <password>
   When I add product <productName> to Cart
   And Checkout <productName> and submit the order
   Then "THANKYOU FOR THE ORDER." message is displayed on the ConfirmationPage


Examples:
    |name                |password     | productName  |
    |santoman@gmail.com  |Santoman@11  |  ZARA COAT 3 |













