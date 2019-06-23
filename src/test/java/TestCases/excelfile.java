package TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelfile {

	public void ReadExcel()
	{
		 Object data[][]=null;
		 
		 int i,j;
		File file =new File("C:\\Oxygen Eclipse\\PHP_Travels\\src\\main\\java\\TestData\\TestData.xlsx");
		try {
			
			FileInputStream fis =new FileInputStream(file);
			XSSFWorkbook wb=new XSSFWorkbook(fis);
			XSSFSheet sheet=wb.getSheetAt(0);
			int RowCount=sheet.getLastRowNum();
			int ColumnCount=sheet.getRow(0).getLastCellNum();
			data=new Object[RowCount][ColumnCount];
			System.out.println("inside excel methd" + RowCount + ColumnCount);
			int ci=0;
			for(i=1;i<=RowCount;i++,ci++)
				
			{	
				int cj=0;
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
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		excelfile obj=new excelfile();
		obj.ReadExcel();
	}

}
