package academy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import academy.AbstractComponents.AbstractComponent;

public class productCatalogue extends AbstractComponent {
	
	
	//life of the driver 
	//create object in the standalone class and send an driver as an argument 
	//come here and create local object(driver) ,and create construtor passes that argumenyt 
	//
	
//this is the local object
	WebDriver driver;//does not have life
	 
	//constructor is the first method is excute in the class
	//that class argument(driver) , here we can catch that in constructor
	public productCatalogue(WebDriver driver) {
		
		super(driver);//to transfer the driver into the parent class(AbstractComponent) and every child has to give the super
		//initialization
		//local object= driver from og class
		this.driver=driver;
		
		
		PageFactory.initElements(driver, this);
		//initElements is trigged the constrution of @findby and passes the driver to it
		//write the pagefactory in constructor because it is executed first
	}
	//page factory is exclusive for the driver.
	//List <WebElement> items=	 driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	
	//driver.findElement( By.cssSelector(".ng-animating"))));
	@FindBy(css=".ng-animating")
	WebElement spinner;
	//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
	//locator from the top parent
  // List <WebElement> items=	 driver.findElements(By.cssSelector(".mb-3"));
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".btn.w-10.rounded");//161 tricky postion
	By toastMessage = By.cssSelector("[role=alert]");
	
	public List<WebElement> getproductList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement pro=   products.stream().filter(product->product.findElement(By.cssSelector("b"))
	    		.getText().equals(productName)).findFirst().orElse(null);
		return pro;
	}
	
	public void addProductToCart(String productName) {
		//here we are search the addtocart button within the product 
	WebElement prod=	getProductByName(productName);//first find the desered product 
			prod.findElement(addToCart).click();//after that ,search the addtocart within that product
			waitForElementToAppear(toastMessage); //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[role=alert]"))); so we are creating (by)
			waitForElementToDisappear(spinner);//wait.until(ExpectedConditions.invisibilityOf(driver.findElement( By.cssSelector(".ng-animating"))));
			//THIS is driver. so we can create pagefactory
			

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
