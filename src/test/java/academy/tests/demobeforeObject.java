package academy.tests;

import static org.testng.Assert.assertEquals;

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

import academy.pageobjects.CartPage;
import academy.pageobjects.CheckOutPage;
import academy.pageobjects.ConfirmationPage;
import academy.pageobjects.LandingPage;
import academy.pageobjects.productCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class demobeforeObject {

	public static void main(String[] args) throws InterruptedException {
		
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver =new ChromeDriver();
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String productName ="ZARA COAT 3";
		//create a landing page objects            //argment of this class
		LandingPage landingPage = new LandingPage(driver);//transfer the driver power into landingpage
		landingPage.goTo();
		landingPage.loginApplication("santoman@gmail.com", "Santoman@11");
		
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		//locator from the top parent
	    // List <WebElement> items=	 driver.findElements(By.cssSelector(".mb-3"));
	    productCatalogue productcatalogue = new productCatalogue(driver);
	    productcatalogue.getproductList();
	   // productcatalogue.getProductByName(productName); inbuild in addProductToCart method
	    productcatalogue.addProductToCart(productName);
	   
	    
	    //we can call the method gotocart method using either of the tow class because it is in the abstract class
	    productcatalogue.goToCartPage();//child object using parent method
	    CartPage cartPage =new CartPage(driver);
	    boolean check= cartPage.verifyProduct(productName);
	    Assert.assertTrue(check);
	    CheckOutPage checkoutPage=  cartPage.goToCheckOut();
	    checkoutPage.enterRequiredField("1104", "santoman");
	    checkoutPage.selectCountry("ind", "india");
	    ConfirmationPage confirmationPage= checkoutPage.placeOrder();
	    confirmationPage.getTheText();
	    
//		driver.findElement(By.cssSelector(".totalRow button")).click();
//		//.ta-results.list-group.ng-star-inserted
//		driver.findElement(By.xpath("(//*[@type='text'])[2]")).sendKeys("1104");
//		driver.findElement(By.xpath("(//*[@type='text'])[3]")).sendKeys("santoman");
//		
		

//		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("ind");
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement( By.cssSelector(".ta-item.list-group-item"))));
//		 List<WebElement> list=	driver.findElements(By.cssSelector(".ta-item.list-group-item"));
//		 list.stream().filter(s->s.getText().equalsIgnoreCase("India")).findFirst().ifPresent(WebElement::click);		
//		 //.equalsIgnoreCase(productName) is insensitive but equals is sensitive
		
	  // for(WebElement suggest: list) {
		 ////  if(suggest.getText().equalsIgnoreCase("India")) {
			 //  suggest.click();
			  // break;} }
			 
			//driver.findElement(By.cssSelector(".action__submit")).click();
		    assertEquals(driver.findElement(By.cssSelector(".hero-primary")).getText(),"THANKYOU FOR THE ORDER.");	
		    driver.quit();
				
	}

}
