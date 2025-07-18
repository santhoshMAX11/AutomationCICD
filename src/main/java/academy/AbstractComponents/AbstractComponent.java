package academy.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import academy.pageobjects.CartPage;
import academy.pageobjects.OrdersPage;

public class AbstractComponent {
     
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
    @FindBy(css="[routerlink*='cart']")
    WebElement cartHeader;
    
    @FindBy(css="[routerlink*='myorders']")
    WebElement ordersbutton;
    
    
  //it is generic one, it can be used in all the exciplit wait
	//By.cssSelector(".mb-3") it is called as( by locator ), so here we are using the by 
   public void waitForElementToAppear(By findBy) {	
	WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
   }
   
   public CartPage goToCartPage() {
	   cartHeader.click();
	   CartPage cartPage =new CartPage(driver);
	   return cartPage;
   }
	
   public void waitForWebElementToAppear(WebElement web) {
	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfAllElements(web));
			
   }
   
   public OrdersPage GoToOrdersPage() {
	   ordersbutton.click();
	   OrdersPage orderspage= new OrdersPage(driver);
	   return orderspage;
   }
	
	public void
	waitForElementToDisappear(WebElement ele) {
		//Thread.sleep(1000);
		//or
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement( By.cssSelector(".ng-animating"))));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
