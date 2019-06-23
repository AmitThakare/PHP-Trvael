package WebPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AfterLoginHomePage extends TestBase {


	
	public AfterLoginHomePage(WebDriver driver)
	{
		
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy (xpath="//h3[@class='RTL']")
	public WebElement UserNameAfterLogin;
	
	
	@FindBy(xpath="//div[@class='alert alert-danger']")
	public WebElement InvalidCredentials;
}
