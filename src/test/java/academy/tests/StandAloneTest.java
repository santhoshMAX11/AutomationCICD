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

import academy.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		//i made changes in app
		WebDriverManager.chromedriver().setup();
		WebDriver driver =new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(15));
		String productName ="ZARA COAT 3";
		driver.get("https://rahulshettyacademy.com/client/auth/login");
		//LandingPage
		//create a landing page objects            //argment of this class
		LandingPage landingPage = new LandingPage(driver);//transfer the driver power into landingpage
		driver.findElement(By.id("userEmail")).sendKeys("santoman@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Santoman@11");
		driver.findElement(By.id("login")).click();
		//ProductCatalogue
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		//locator from the top parent
	    List<WebElement> items=	 driver.findElements(By.cssSelector(".mb-3"));
	    
	    WebElement pro=   items.stream().filter(product->product.findElement(By.cssSelector("b"))
	    		.getText().equals(productName)).findFirst().orElse(null);
	//findfirst() , if a matching item is found that item is returned and stored in pro
	    //orelse null is returned 
		
		pro.findElement(By.cssSelector(".btn.w-10.rounded")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[role=alert]")));
		//that round round thing get to disappear
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement( By.cssSelector(".ng-animating"))));
		//CartPage
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
     	List<WebElement> ordered=	driver.findElements(By.cssSelector(".cartSection h3"));
	    boolean check=	ordered.stream().anyMatch(order->order.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(check);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		//CheckOutPage
		//.ta-results.list-group.ng-star-inserted
		driver.findElement(By.xpath("(//*[@type='text'])[2]")).sendKeys("1104");
		driver.findElement(By.xpath("(//*[@type='text'])[3]")).sendKeys("santoman");
		
		

		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("ind");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement( By.cssSelector(".ta-item.list-group-item"))));
		 List<WebElement> list=	driver.findElements(By.cssSelector(".ta-item.list-group-item"));
		 list.stream().filter(s->s.getText().equalsIgnoreCase("India")).findFirst().ifPresent(WebElement::click);		
		 //.equalsIgnoreCase(productName) is insensitive but equals is sensitive
		
	  // for(WebElement suggest: list) {
		 ////  if(suggest.getText().equalsIgnoreCase("India")) {
			 //  suggest.click();
			  // break;} }
			 
			driver.findElement(By.cssSelector(".action__submit")).click();
		    assertEquals(driver.findElement(By.cssSelector(".hero-primary")).getText(),"THANKYOU FOR THE ORDER.");	
		    driver.quit();
				
	}

}
