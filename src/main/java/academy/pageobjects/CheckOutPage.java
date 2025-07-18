package academy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import academy.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
 
	
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
//driver.findElement(By.xpath("(//*[@type='text'])[2]")).sendKeys("1104");
	//driver.findElement(By.xpath("(//*[@type='text'])[3]")).sendKeys("santoman");
    @FindBy(xpath="(//*[@type='text'])[2]")
    WebElement CVV;
  
    @FindBy(xpath="(//*[@type='text'])[3]")	
    WebElement NameOnCard;
    
    @FindBy(css="[placeholder='Select Country']")	
    WebElement Country;  
    
    @FindBy(css=".ta-item.list-group-item")	
    List<WebElement> countryList;  
    
   
   
    By visible=By.cssSelector(".ta-item.list-group-item");
   
    public void enterRequiredField(String cvv, String nameoncard) {
    	CVV.sendKeys(cvv);
    	NameOnCard.sendKeys(nameoncard);
    	
    }

    public void selectCountry(String CountryName, String fullcountryName)
    {
    	Country.sendKeys(CountryName);
    	waitForElementToAppear(visible);
    	List<WebElement> list=	countryList;
		list.stream().filter(s->s.getText().equalsIgnoreCase(fullcountryName)).findFirst().ifPresent(WebElement::click);
		                                                                              //**********important
    } 
    
   
    @FindBy(css=".action__submit")
    WebElement placetheOrder;
    

     public ConfirmationPage placeOrder() {
	   placetheOrder.click();
	   ConfirmationPage confirmationPage =new ConfirmationPage(driver);
	  return confirmationPage;
	    
   }

    
    
    
    
    
    
		
	}
	
	

