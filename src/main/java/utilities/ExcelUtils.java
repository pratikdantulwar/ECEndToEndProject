package utilities;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import resources.TestBase;

public class ExcelUtils extends TestBase{

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	//private static XSSFRow Row;

	/**
	 * This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
	 * @param Path Excel Path
	 * @param SheetName SheetName which needs to access
	 * @throws Exception
	 */
	public static void setExcelFile(String Path,String SheetName) throws Exception {
		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e){
			throw (e);
		}
	}

	/**
	 * This method will return the package name and the class name 
	 * for e.g. ‘automationFramework.UDF_TC@2550036c’. Another function is required to refine the long test case name in to UDF_TC.
	 * @param sTestCase Test case name of intended test case
	 * @return
	 * @throws Exception
	 */
	public static String getTestCaseName(String sTestCase)throws Exception{
		String value = sTestCase;
		try{
			int posi = value.indexOf("@");
			value = value.substring(0, posi);
			posi = value.lastIndexOf(".");
			value = value.substring(posi + 1);
			return value;
		}catch(Exception e){
			log.error("Class Utils | Method getTestCaseName | Exception desc : "+e.getMessage());
			throw (e);
		}		
	}
	
	/**
	 * This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
	 * @param RowNum rownum
	 * @param ColNum colnum
	 * @return
	 * @throws Exception
	 */
	public static String getCellData(int RowNum, int ColNum) throws Exception{
		try{
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();
			return CellData;
		}catch (Exception e){			
			return"";		
		}
	}

	/**
	 * This method is to return the Test case row from the Excel sheet
	 * @param sTestCaseName
	 * @param colNum
	 * @return
	 * @throws Exception
	 */
	public static int getRowContains(String sTestCaseName, int colNum) throws Exception{
		int i;
		try {
			int rowCount = ExcelWSheet.getLastRowNum();
			for ( i=0 ; i<rowCount; i++){
				if(ExcelUtils.getCellData(i,colNum).equalsIgnoreCase(sTestCaseName)){
					break;
				}
			}
			return i;
		}catch(Exception e){
			log.error("Class ExcelUtil | Method getRowContains | Exception desc : " + e.getMessage());
			throw(e);
		}
	}

	/*
	//This method is to write in the Excel cell, Row num and Col num are the parameters

	public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception	{
			try{
  			Row  = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
				} else {
					Cell.setCellValue(Result);
				}
				
  // Constant variables Test Data path and Test Data file name

  				FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);
  				ExcelWBook.write(fileOut);
  				fileOut.flush();
					fileOut.close();
				}catch(Exception e){
					throw (e);
			}
		}
	 */
}


