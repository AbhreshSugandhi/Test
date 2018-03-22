package com.demoaut.mercurytours.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	
	public static String[][] getData(String fileName, String sheetName) throws IOException{
		File file = new File(fileName);
		FileInputStream inputFile = new FileInputStream(file);
		Workbook wb= new XSSFWorkbook(inputFile);
		Sheet sh= wb.getSheet(sheetName);
		int rowNum= sh.getLastRowNum() + 1;
		int colNum= sh.getRow(0).getLastCellNum();
		String data[][]=new String [rowNum][colNum];
		for (int i= 0; i<rowNum; i++){
			Row row= sh.getRow(i);
			for (int j=0; j<colNum; j++){
				Cell cell= row.getCell(j);
				//String value= cellToString(cell);
				String value= new DataFormatter().formatCellValue(cell);
				data[i][j]= value;
			}
		}
		return data;
	}
	
	public static String[][] getLoginOrRegisterUserData() throws IOException{
		String[][] data= ReadExcel.getData("C:\\workspace\\Mercury Tours\\TestData\\MercuryTourseData.xlsx", "LoginAndUserData");
		return data;
	}
	
	public static String[][] getInvalidLoginData() throws IOException{
		String[][] data= ReadExcel.getData("C:\\workspace\\Mercury Tours\\TestData\\MercuryTourseData.xlsx", "InvalidLoginData");
		return data;
	}
	
	public static String[][] getBookingData() throws IOException{
		String[][] data= ReadExcel.getData("C:\\workspace\\Mercury Tours\\TestData\\MercuryTourseData.xlsx", "BookingData");
		return data;
	}
}
