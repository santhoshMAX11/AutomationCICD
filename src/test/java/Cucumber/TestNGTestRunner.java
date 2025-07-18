package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//this has many helper attribute //we have to clearly tell where the feature files , to run all of them
@CucumberOptions(features="src/test/java/Cucumber",glue="academy.StepDefinitions",//to map them
monochrome=true,tags="@Regression",plugin= {"html:target/cucumber.html"})//and generate the html file for result
//monochrome will give the result in the readable format

public class TestNGTestRunner extends AbstractTestNGCucumberTests  {
//AbstractTestNGCucumberTests which help us to read the testng files in cucumber
	
	
	//cucumber---> 
	//cucumber feature file are runned by the testng or junit
	//here are using testng just for running the feature files
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
