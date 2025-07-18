package academy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import academy.AbstractComponents.AbstractComponent;

public class CartPage  extends AbstractComponent{
	
	//life of the driver 
	//create object in the standalone class and send an driver as an argument 
	//come here and create local object(driver) ,and create construtor passes that argumenyt 
	//
	
//this is the local object
	WebDriver driver;//does not have life
	 
	//constructor is the first method is excute in the class
	//that class argument(driver) , here we can catch that in constructor
	public CartPage(WebDriver driver) {
		
		super(driver);//to transfer the driver into the parent class(AbstractComponent)and every child has to give the super
		//initialization
		//local object= driver from og class
		this.driver=driver;
		
		
		PageFactory.initElements(driver, this);
		//initElements is trigged the constrution of @findby and passes the driver to it
		//write the pagefactory in constructor because it is executed first
	}
	
	//private means only access to this particular class
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartproducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	
	public boolean verifyProduct(String productName){
	boolean check=	cartproducts.stream().anyMatch(order->order.getText().equalsIgnoreCase(productName));
	return check;
	}
	
	public CheckOutPage goToCheckOut() {
		checkout.click();
		CheckOutPage checkoutPage= new CheckOutPage(driver);
		return checkoutPage;
			
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

		
	}
	
	
	
	
	

