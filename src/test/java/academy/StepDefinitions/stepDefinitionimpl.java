package academy.StepDefinitions;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import academy.TestComponents.BaseTest;
import academy.pageobjects.CartPage;
import academy.pageobjects.CheckOutPage;
import academy.pageobjects.ConfirmationPage;
import academy.pageobjects.LandingPage;
import academy.pageobjects.productCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionimpl extends BaseTest{
    
	public LandingPage landingPage;//declaring the LandingPage gobally to use this from anywhere
	public productCatalogue productcatalogue; 
	public  ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException, InterruptedException {
		// LandingPage landingPage =	launchApplication();//1.by calling the lauchApplication() method we got return as Landing page,so we catch that landing page by an object
		 	landingPage=	launchApplication();//after declaring gobally
	}
	                           //regular express (.+) and start with^ and end with $
    @Given ("^Logged in with username (.+) and password (.+)$")
    public void  Logged_in_with_username_and_password(String username, String password) {
    	//productCatalogue productcatalogue = landingPage.loginApplication(username, password);
    	
    	productcatalogue =  landingPage.loginApplication(username, password);//after declaring gobal
    }                       //because of declaring gobally here we are using that object

    @When("^I add product (.+) to Cart$")
    public void I_add_product_to_Cart(String productName) {
    	productcatalogue.getproductList();
	    productcatalogue.addProductToCart(productName);
	   
    }

    @And ("^Checkout (.+) and submit the order$")
    public void Checkout_product_and_submit_the_orders(String productName){
    	 CartPage cartPage=  productcatalogue.goToCartPage();
    	 boolean check= cartPage.verifyProduct(productName);
 	     Assert.assertTrue(check);
 	    CheckOutPage checkoutPage=  cartPage.goToCheckOut();
	    checkoutPage.enterRequiredField("1109","santoman");
        checkoutPage.selectCountry("ind","India");
        confirmationPage= checkoutPage.placeOrder();
    }
	
	//when you {} whatwere before message(from @Then in enduser page) are getting into the {string} that transfered to String
    @Then("{string} message is displayed on the ConfirmationPage")
    public void message_displayed_on_ConfirmationPage(String string) {
    	 String confirmMessage= confirmationPage.getTheText();
 		 assertEquals(confirmMessage,string);	
 		 driver.close();
    }
	
	
	//here we are gonna reuse the word code 
    //@ Given I landed on Ecommerce Page
  //  @When  Logged in with username <name> and password <password>
	//these steps are aleady done here so we dont want to create another time, cucumber itself find it and use it
    
    //only need to set the code for the not presented line 
    @Then ( "{string} message is displayed")
    public void message_is_displayed(String string) {    	
 	    assertEquals(landingPage.getErrorMessage(),string );
 	    driver.close();
    }
	
	
}
