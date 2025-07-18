package academy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import academy.AbstractComponents.AbstractComponent;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LandingPage  extends AbstractComponent{
	
	//life of the driver 
	//create object in the standalone class and send an driver as an argument 
	//come here and create local object(driver) ,and create construtor passes that argumenyt 
	//
	
//this is the local object
	WebDriver driver;//does not have life
	 
	//constructor is the first method is excute in the class
	//that class argument(driver) , here we can catch that in constructor
	public LandingPage(WebDriver driver) {
		
		super(driver);//to transfer the driver into the parent class(AbstractComponent)and every child has to give the super
		//initialization
		//local object= driver from og class
		this.driver=driver;
		
		
		PageFactory.initElements(driver, this);
		//initElements is trigged the constrution of @findby and passes the driver to it
		//write the pagefactory in constructor because it is executed first
	}
	//page object should only hold the webelements and actions
	
	//trational way
	//WebElement userEmail =driver.findElement(By.id("userEmail"));
	
	//new method
	//pagefactory
	
	@FindBy(id="userEmail")//this will be construct like above code
	WebElement Email;
	
	@FindBy(id="userPassword")
	WebElement passwordele;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css=".toast-bottom-right")
	WebElement errorMessage;
	
	
	
	public void goTo() throws InterruptedException {//url
		WebDriverManager.chromedriver().setup();
		driver.get("https://rahulshettyacademy.com/client/auth/login");
		
		}
	
	//actions for entering email,password and clicking on login button
	public productCatalogue loginApplication(String email, String password) {
		Email.sendKeys(email);
		passwordele.sendKeys(password);
		submit.click();
		productCatalogue productcatalogue = new productCatalogue(driver);
		return productcatalogue;
	}
	
	public String getErrorMessage() {
		
    waitForWebElementToAppear(errorMessage);
    return	errorMessage.getText();
	}
	
	
	
	
}
