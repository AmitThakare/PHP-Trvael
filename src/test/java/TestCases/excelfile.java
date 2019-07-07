package TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class excelfile {

	public String TestCaseId;
	
	File file =new File("C:\\Users\\Amit\\Desktop\\TestData.xlsx");
	@DataProvider(name="Data")
	public Object[][] ReadExcel()
	{
		 Object data[][]=null;
		 
		 int i,j;
		
		try {
			
			FileInputStream fis =new FileInputStream(file);
			XSSFWorkbook wb=new XSSFWorkbook(fis);
			XSSFSheet sheet=wb.getSheet("input");
			int RowCount=sheet.getLastRowNum();
			int ColumnCount=sheet.getRow(0).getLastCellNum();
			data=new Object[RowCount][ColumnCount];
			System.out.println("inside excel method and RowCount is " + RowCount + " and Column count is " + ColumnCount);
			int ci=0;
			for(i=1;i<=RowCount;i++,ci++)
				
			{	
				int cj=0;
				for(j=0;j<ColumnCount;j++,cj++)
				{
					
					data[ci][cj]=CheckDataTypeOfExcelSheet(sheet.getRow(i).getCell(j));
				//	System.out.println(data[ci][cj]);
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
	
	@Test(dataProvider="Data")
	public void Test1(String Test_Case_Id, String username,String password)
	{
		System.out.println("Test case started");
		System.out.println(Test_Case_Id + "  " + username + "  " + password);
		TestCaseId=Test_Case_Id;
	
	}
	
	@AfterMethod
	public void VerifyTestCaseResult(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			WriteDataToExcelFile("PASS");
			
		}
		else if(result.getStatus()==ITestResult.FAILURE)
		{
			WriteDataToExcelFile("FAIL");
		}
		else
		{
			WriteDataToExcelFile("SKIP");
		}
	}
	
	public void WriteDataToExcelFile(String status) throws IOException
	{
		
		FileInputStream fis =new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet=wb.getSheet("output");
		int TotalRow=sheet.getLastRowNum();
	
		for(int i=1;i<=TotalRow;i++)
		{
			System.out.println("data from the output file " + sheet.getRow(i).getCell(0).getStringCellValue() );
				if(sheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(TestCaseId))
				{
					sheet.getRow(i).createCell(1).setCellValue(status);
					FileOutputStream fout=new FileOutputStream(file);
					wb.write(fout);
					fout.close();
				}
				
		}
		
	//	wb.close();
		
		
	}
}
