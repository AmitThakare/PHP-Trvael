package TestCases;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.testng.annotations.Test;


import WebPage.AfterLoginHomePage;
import WebPage.Home_Page;
import WebPage.LoginPage;
import WebPage.TestBase;

public class LoginToApplication extends TestBase 

{
	public WebDriverWait wait;

	
	Home_Page home;
	LoginPage Login;
	AfterLoginHomePage AfterLogin;
	
	
	@Test(priority=1,description="Enter valid and invalid username and password",dataProvider="ReadDatafromExcel")
	public void CorrectLogin(String username,String password) throws InterruptedException
	{
		
		Home_Page home=new Home_Page(driver);
		LoginPage Login=new LoginPage(driver);	
		AfterLogin =new AfterLoginHomePage(driver);
		
		home.ClickOnMyAccountDropDown();
		Login.EnterUserName(username);
		Login.EnterPassword(password);
		Login.ClickOnLoginButton();
		if(AfterLogin.InvalidCredentials.isDisplayed()==true)
		{
		WaitTillTheTextToBePresent(driver,AfterLogin.InvalidCredentials);
			

		Assert.assertEquals(GetText(AfterLogin.InvalidCredentials), "Invalid Email or Password");
		System.out.println(GetText(AfterLogin.InvalidCredentials));
		}
		else
		{
			System.out.println("Trying for valid credentials");
		//	WaitTillTheTextToBePresent(driver,AfterLogin.UserNameAfterLogin);
			

		//	Assert.assertEquals(GetText(AfterLogin.InvalidCredentials), "Hi, Johny Smith");
			System.out.println(GetText(AfterLogin.InvalidCredentials));
		}
	}
	
//	@Test(priority=2,description="Enter blank username and password")
	public void BlankUserCredential()
	{
	
		Home_Page home=new Home_Page(driver);
		LoginPage Login=new LoginPage(driver);	
		
		home.ClickOnMyAccountDropDown();
		Login.EnterUserName("");
		Login.EnterPassword("");
		Login.ClickOnLoginButton();
		//WaitTillTheElementToBeVisible(driver, 10, Login.InvalidMessage);
		Assert.assertEquals(GetText(Login.InvalidMessage), "Invalid Email or Password", "Username and password enntered are incorrect");
		CreateBorder(Login.InvalidMessage);
		ScreenShotTake(driver);
	}

	
	
}
