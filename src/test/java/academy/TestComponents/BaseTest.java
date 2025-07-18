package academy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import academy.pageobjects.LandingPage;
import academy.tests.SubmitOrderTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest   {
	
   
    
	public WebDriver driver;//gobally giving the life for the outside driver 
	//165
	public LandingPage landingPage ;//the public landingPage object to eliminate the code in deafult test
	
	public WebDriver initializeDriver() throws IOException {
		
		
	    //properties class
		Properties prop =new Properties();//It can read any files which .properties
		
		//It’s a Java class used to read data from a file.
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//academy//resources//GlobalData.properties");
		prop.load(fis);                           //"C:\\Users\\testi\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\academy\\resources\\GlobalData.properties"
	    
		//because of maven we create this line of code, first part chck wether maven is passing any browser , if not then go for the goblaldata browser
	  // String browserName=	System.getProperty("browser")!=null ? System.getProperty("browser")  :prop.getProperty("browser");

		String browserName =prop.getProperty("browser"); //this is the normal string use for stdy purpose
		
//	   //For headless mode using the ChromeOptions
//		if(browserName.contains("chrome")) { //equalsIgnoreCase is excepting exact match so here we gona use .contains
//	    ChromeOptions options =new ChromeOptions();
//	    WebDriverManager.chromedriver().setup();
//	    if(browserName.contains("headless")) {
//	    	options.addArguments("headless");	
//	    }
//	    
//	    
//	    driver =new ChromeDriver(options);
//		
//		}
//this is the normal method 		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
		driver =new ChromeDriver();
		
			}
		else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
		   driver =new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
		    driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//set driver into threadlocal
		
		return driver;
	}
	
 
 
	public  List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		//read json to String
	String jsonContent=	FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
	//String to HashMap (Jackson Databind) it is external thing which help to covert the string into hashmap
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
	
	return data;
		
	}
	                           //from the listeners class driver is coming
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		///take screen
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		//destination
		File destination = new File(System.getProperty("user.dir")+"//reports//"+ testCaseName +".png");
		
		//save screenshot
		FileUtils.copyFile(source, destination );
		System.out.println("screenshot saved at: "+ destination.getAbsolutePath());
		return destination.getAbsolutePath();                 //this gives the correct location 
		//return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		
		
	}
	
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException, InterruptedException {
	  driver=	initializeDriver();//here we have to create life for driver by using initilizedriver();
	   landingPage = new LandingPage(driver);//transfer the driver power into landingpage
	  // LandingPage   landingPage = new LandingPage(driver);
	   //after creating a gobal public object we have to delete the LandingPage ,like the above
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		 driver.quit();
	}
	
	
	
}