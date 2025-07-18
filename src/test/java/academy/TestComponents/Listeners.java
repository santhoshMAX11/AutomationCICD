package academy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import academy.resources.ExtentReporterNG;

public class Listeners  extends BaseTest implements ITestListener {
	//ITestListener is a method giving many methods 
	//unimplemented methods
	
	
	//176
	ExtentTest test;// declare variable to hold individual test log
	//“I want to create a variable to hold one test report entry, but I will assign it later.”
	             
	              //get the HTML report setup
    ExtentReports extent=	ExtentReporterNG.getReportObject();// here we are calling the method using the classname  
	// calls the static method from the previous class and stores the report

	//private String  onTestStartString();
	//ExtentReports extent(method and their return)
	@Override
	public void onTestStart(ITestResult result) {//result, this variable has all the info about the testcases
		//so using this variable we have to smartly grab the name of the test
		  test=	 extent.createTest(result.getMethod().getMethodName());
		     //creates a new test entry in the report with the test method's name
		// ExtentTest= test) will now be used to log pass/fail steps for this test
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	
	}

	@Override
	public void onTestFailure(ITestResult result) {//177
		test.fail(result.getThrowable());//this will showcase the error message
	
		
		
		
		//driver =(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		//String filepath = getScreenshot(result.getMethod().getMethodName()); machine will automatically goes to the next step
		//create classs level object to initize the variable
		try {
			//result.getInstance() → gives the failed test object
			//(BaseTest) result.getInstance() → allows access to .driver & .getScreenshot()
			WebDriver driver =((BaseTest) result.getInstance()).driver; //Correct Approach: Get the already used WebDriver instance
			//You should reuse the driver from the failed test class like this:
			                                                                //(tring testcasename, Webdriver driver)
			String filepath=((BaseTest)result.getInstance()).getScreenshot(result.getMethod().getMethodName(), driver);//getScreenshot("loginTest", driver) → takes screenshot, saves it, returns path
			//String	filepath = getScreenshot(result.getMethod().getMethodName());
			System.out.println("Screenshot saved at:"+ filepath);
			test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
			                                     //it shows the testcase name in reports
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//here were the getting the knowled
		
		
		
		}
	


	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
			}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();//this is must to generate perfect results
	}





















}
