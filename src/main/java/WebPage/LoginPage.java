package WebPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase{



	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		
	}

	@FindBy (xpath="//input[@type='email']")
	public WebElement UserNameTextField;
	
	@FindBy (xpath="//input[@type='password']")
	public WebElement PasswordTextField;
	
	@FindBy (xpath="//button[contains(text(),'Login')]")
	public WebElement LoginButton;
	
	
	
	@FindBy (xpath="//div[contains(text(),'Invalid')]")
	public WebElement InvalidMessage;
	
	
	public void EnterUserName(String username)
	{
		UserNameTextField.sendKeys(username);
	}
	
	public void EnterPassword(String password)
	{
		PasswordTextField.sendKeys(password);
	}
	
	
	public AfterLoginHomePage ClickOnLoginButton()
	{
		LoginButton.click();
		return PageFactory.initElements(driver, AfterLoginHomePage.class);
	}
	
	
	
}
