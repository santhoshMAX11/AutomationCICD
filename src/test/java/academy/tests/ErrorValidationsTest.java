package academy.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import academy.TestComponents.BaseTest;
import academy.TestComponents.Retry;
import academy.pageobjects.CartPage;
import academy.pageobjects.CheckOutPage;
import academy.pageobjects.ConfirmationPage;
import academy.pageobjects.LandingPage;
import academy.pageobjects.productCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTest {
// inherite the parent class to use their methods
	
		//@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
	//when you need re run try this method (Retry class )
	
	
		@Test(groups = {"ErrorHandling"})
		public void LoginErrorValidation() throws IOException
		{
	   landingPage.loginApplication("santoman11@gmail.com", "Santoman@11");//to remove the error here, we have to create a landingPage object as a public in baseTest page
	    assertEquals(landingPage.getErrorMessage(),"Incorrect email or password." );	
			}
		
		
		
	    @Test
		public void ProductErrorValidation() throws IOException
		{
		String productName ="ZARA COAT 3";
	   
		productCatalogue productcatalogue = landingPage.loginApplication("santoman@gmail.com", "Santoman@11");//to remove the error here, we have to create a landingPage object as a public in baseTest page
	    productcatalogue.getproductList();
	    productcatalogue.addProductToCart(productName);
	    CartPage cartPage=  productcatalogue.goToCartPage();//child object using parent method
	    boolean check= cartPage.verifyProduct("ZARA COAT 33");
	    Assert.assertFalse(check);
	   	
		   
				
	}

}
