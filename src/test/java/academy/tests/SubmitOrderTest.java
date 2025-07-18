package academy.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import academy.TestComponents.BaseTest;
import academy.pageobjects.CartPage;
import academy.pageobjects.CheckOutPage;
import academy.pageobjects.ConfirmationPage;
import academy.pageobjects.LandingPage;
import academy.pageobjects.OrdersPage;
import academy.pageobjects.productCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
// inherite the parent class to use their methods
	
	String productName ="ZARA COAT 3";
		
	    //dataprovider is providing the datas for the test cases
		@Test(dataProvider = "getData",groups = {"Purchase"})
		public void submitOrder(HashMap<String, String> thrive) throws IOException //HashMap<String, String> input
		//public void submitOrder(String email, String password, String productName) throws IOException
		{
			
	   // LandingPage landingPage = launchApplication(); //not required,because we are using the @BeforeMethod
		//that has all the main generic functions to it
		productCatalogue productcatalogue = landingPage.loginApplication(thrive.get("email"), thrive.get("password"));//to remove the error here, we have to create a landingPage object as a public in baseTest page
		//hybrid method of creating the class object 1. create object when we have to takeoff to the new page 
	    //productCatalogue productcatalogue = new productCatalogue(driver);
		//return productcatalogue; like this and take the class and object and placed in the last step of the previous step 
	    //this how we created the objects
	    
		
	    productcatalogue.getproductList();
	    productcatalogue.addProductToCart(thrive.get("product"));
	    
	    //we can call the method gotocart method using either of the tow class because it is in the abstract class
	    CartPage cartPage=  productcatalogue.goToCartPage();//child object using parent method
	    boolean check= cartPage.verifyProduct(thrive.get("product"));
	    Assert.assertTrue(check);
	    CheckOutPage checkoutPage=  cartPage.goToCheckOut();
	    checkoutPage.enterRequiredField("1109","santoman");
        checkoutPage.selectCountry("ind","India");
        ConfirmationPage confirmationPage= checkoutPage.placeOrder();
        String confirmMessage= confirmationPage.getTheText();
		assertEquals(confirmMessage,"THANKYOU FOR THE ORDER.");	
		   }

		
		@Test(dependsOnMethods = {"submitOrder"})//because of this method, above test will run first after that this test will run
		public void OrderHistoryTest() {
			
			productCatalogue productcatalogue = landingPage.loginApplication("santoman@gmail.com", "Santoman@11");
			OrdersPage orderspage=	productcatalogue.GoToOrdersPage();
	     	Assert.assertTrue(orderspage.verifyOrderDisplay(productName));
			
		}
		
		@DataProvider
		public Object[][] getData() throws IOException{
		List<HashMap<String,String>> data=	getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//academy//data//PurchaseOrder.json");
		
			return new Object[][] {{data.get(0)},{data.get(1)}};
		}
		
//		@DataProvider 
//		public Object[][] getData() {
//			//Object [] []  is a multi dimensional thing it can store anything (string,int..)
//		
//			
//		return	new Object [] [] {{"santoman@gmail.com","Santoman@11","ZARA COAT 3"}, {"dharsh1109@gmail.com","Santoman@11","ADIDAS ORIGINAL"}};		
//				//passing the data through this 	
//		}
	
//		@DataProvider
//		public Object[][] getData(){
//			HashMap<String, String> map = new HashMap<String, String>();
//			map.put("email", "santoman@gmail.com");
//			map.put("password", "Santoman@11");
//			map.put("productName", "ZARA COAT 3");
//			
//			HashMap<String, String> map1 = new HashMap<String, String>();
//			map1.put("email", "dharsh1109@gmail.com");
//			map1.put("password", "Santoman@11");
//			map1.put("productName", "ADIDAS ORIGINAL");
//			
//			return new Object[][] {{map},{map1}};
//		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
