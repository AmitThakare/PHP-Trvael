package ConfigFiles;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;

import org.testng.ITestResult;

import WebPage.TestBase;

public class MyListener extends TestBase implements ITestListener, IInvokedMethodListener{
  
	 WebDriver driver1;
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Inside the test " + result.getMethod().getMethodName() +" From the class " );
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("This test executed successfully " + result.getMethod().getMethodName() +" From the class " + result.getClass().getName());
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("This test failed " + result.getMethod().getMethodName() +" From the class " + result.getClass().getSimpleName());
	 
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("This method has skipped " + result.getMethod().getMethodName() +" From the class " + result.getClass().getSimpleName());
	System.out.println(result.getThrowable().getMessage());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println(context.getFailedTests().getAllMethods());
	}

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
		System.out.println("Inside the method " + method.getTestMethod().getMethodName() + " of class " +method.getTestResult().getInstanceName());
	
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
		System.out.println("Exiting from the method " + method.getTestMethod().getMethodName() + " of class " +method.getTestResult().getInstanceName());
	   
		   
	}

}


