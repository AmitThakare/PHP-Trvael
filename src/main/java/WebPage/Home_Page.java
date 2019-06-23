package WebPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home_Page extends TestBase{

//	public WebDriver driver;
	public Home_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		
		 //(//a[@class='dropdown-toggle go-text-right'])[1]
	}
	@FindBy (xpath="(//li[@id='li_myaccount'])[2]") //
	public WebElement MyAccountDropDown;
	
	@FindBy (xpath="(//a[contains(text(),'Login')])[2]")
	public WebElement LoginOption;
	
	public LoginPage ClickOnMyAccountDropDown()
	
	{
		MyAccountDropDown.click();
		LoginOption.click();
		return PageFactory.initElements(driver, LoginPage.class);
	}
	
	
}
