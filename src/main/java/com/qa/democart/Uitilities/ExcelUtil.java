package com.qa.democart.Uitilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private static String TEST_DATA_SHEET ="./src//test//resources//testdata//TestExcelData" ;
	private static Workbook book;
	private static Sheet mysheet;
	private static Object[][] mydata;
	
	public static Object[][] getTestData(String sheetName)
	{
		FileInputStream fis;
		try {
			fis = new FileInputStream(TEST_DATA_SHEET);
			book=	WorkbookFactory.create(fis);
			mysheet= book.getSheet(sheetName);
			
			mydata = new Object[mysheet.getLastRowNum()][mysheet.getRow(0).getLastCellNum()];
			for(int i=0; i<mysheet.getLastRowNum();i++)
			{
				for(int j=0; i<mysheet.getRow(0).getLastCellNum();j++)
				{
					mydata[i][j]= mysheet.getRow(i+1).getCell(j).toString();
				}
			}
			
			
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return mydata;
				
				
				
	
		}
	}



