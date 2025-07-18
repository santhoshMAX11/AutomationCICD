package academy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
 //179                 //IRetryAnalyzer  is method is used to retry the test after the fail
	
	int count =0;
	int maxTry =1;
	
	@Override
	public boolean retry(ITestResult result) {
		if (count<maxTry) {
			count++;
			return true;
		}
		
		
		return false;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
