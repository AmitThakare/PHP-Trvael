package WebPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;



public class TestBase {
public WebDriver driver;
public WebDriverWait wait;
public static WebElement element;	
public static Properties prop;



	@BeforeSuite(alwaysRun=true)
	public void ReadPropertiesFile() throws IOException
	{
		System.out.println("Inside the configuration");
		FileInputStream fis = new FileInputStream("C:\\Oxygen Eclipse\\PHP_Travels\\src\\main\\java\\ConfigFiles\\config.properties");
		 prop=new Properties();
		prop.load(fis);
		
		
	}
	@BeforeMethod
	public void Setup()
	{
		String WebSite_Url=prop.getProperty("url");
		
		System.setProperty("webdriver.chrome.driver", "E:\\Automation jar files\\Driver files\\chromedriver.exe");
		driver=new ChromeDriver();
	
	driver.get(WebSite_Url);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	System.out.println("Driver is " + driver);
	}
	
	public String GetMethodName()
	{

         String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
         return methodName;
        
	}
	public String  GetText(WebElement element)
	{
		return element.getText();
	}
	
	public void WaitTillTheTextToBePresent(WebDriver driver,WebElement element)
	{
		 wait =new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	
	public void ScreenShotTake(WebDriver driver)
	{
		Date date=new Date();
		SimpleDateFormat Simpleformat=new SimpleDateFormat("dd/mm/yyyy");
		String ScreenShotName=Simpleformat.format(date).replace("/", "_");
		String FolderName=Simpleformat.format(date).replace("/", "");
		new File("D:\\Stored Screen shots\\"+ FolderName).mkdir();
		TakesScreenshot ScreenShot=(TakesScreenshot)driver;
		File SourceLocation=ScreenShot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(SourceLocation, new File("D:\\Stored Screen shots\\"+ FolderName + "\\"+ ScreenShotName +".jpg"));
		System.out.println("Screen shot has been taken");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("This message "+e.getMessage() + e.getStackTrace().getClass().getEnclosingMethod());
		}
		
			
	}
	
	public void CreateBorder(WebElement element)
	{
		JavascriptExecutor  js=(JavascriptExecutor )driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 3px solid red;');", element);
			
			
	}
//	@BeforeSuite(alwaysRun=true)
	@DataProvider(name="ReadDatafromExcel")
	public Object[][] ReadExcel()
	{
		 Object data[][]=null;
		 
		 int i,j,ci,cj;
		File file =new File(prop.getProperty("DataFile"));
		System.out.println(file);
		try {
			
			FileInputStream fis =new FileInputStream(file);
			XSSFWorkbook wb=new XSSFWorkbook(fis);
			XSSFSheet sheet=wb.getSheetAt(0);
			int RowCount=sheet.getLastRowNum();
			int ColumnCount=sheet.getRow(0).getLastCellNum();
			data=new Object[RowCount][ColumnCount];
			System.out.println("inside excel methd" + RowCount + ColumnCount);
			ci=0;
			for(i=1;i<=RowCount;i++,ci++)
				
			{	
			cj=0;
				for(j=0;j<ColumnCount;j++,cj++)
				{
					
					data[ci][cj]=CheckDataTypeOfExcelSheet(sheet.getRow(i).getCell(j));
					System.out.println("Data present in the excel is \n "+data[ci][cj]);
				}

			}
			wb.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage() + " File path is missing or incorrect " );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage() +"Workbook path or workbook not able to find" );
		}
		
		return data;
	}
	
	public String CheckDataTypeOfExcelSheet(XSSFCell cell)
	{
		String CellValue=null;
		if(cell!=null)
		{
		switch (cell.getCellTypeEnum()) 
		{
	    case STRING:
	        CellValue=cell.getStringCellValue();
	        break;
		case NUMERIC:  
			Double DoubleValue=cell.getNumericCellValue();
			 CellValue=DoubleValue.toString();
			 break;  
	    case BLANK:
	    	CellValue="";
	    	break;     
	    case BOOLEAN:
	    	Boolean BooleanValue=cell.getBooleanCellValue();
	    	CellValue=BooleanValue.toString();
	    	break;
	    default:
	        System.out.println("You have choosed wrong option");
	     }
		}
		return CellValue;
		
	
	}
	//@AfterMethod
	public void TearDown()
	{
		driver.close();
	}
}
