package academy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import academy.AbstractComponents.AbstractComponent;

public class OrdersPage  extends AbstractComponent{
	
	//life of the driver 
	//create object in the standalone class and send an driver as an argument 
	//come here and create local object(driver) ,and create construtor passes that argumenyt 
	//
	
//this is the local object
	WebDriver driver;//does not have life
	 
  public OrdersPage(WebDriver driver) {
		
		super(driver);		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
  
  
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderedList;
	
	
	public boolean verifyOrderDisplay(String productName){
	boolean check=	orderedList.stream().anyMatch(order->order.getText().equalsIgnoreCase(productName));
	return check;
	}
	
	
			
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

		
	
	
	
	
	
	

