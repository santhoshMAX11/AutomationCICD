package academy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	//without creating the object of this class , can we call the methods of this class
	//we can directly using class name , we can call the method
	public static  ExtentReports getReportObject() {
		
	  String path =	System.getProperty("user.dir")+"//reports//index.html";
	  ExtentSparkReporter reporter =new ExtentSparkReporter(path); //ExtentSparkReporter is responsible for to create new html file.
	  //by passing the path as argument, it will create a file in that location
	  reporter.config().setReportName("WEB Automation Results");//reportname
	  reporter.config().setDocumentTitle("Test results");//for title
	  
	//this is main class and it is responsible for create and consolidate for all your reports
	  ExtentReports extent = new ExtentReports();
	  extent.attachReporter(reporter);//reporter(object) tell us the info of the files
	  extent.setSystemInfo("Tester", "Santoman");
	 return extent;
	 // extent.createTest(path);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
