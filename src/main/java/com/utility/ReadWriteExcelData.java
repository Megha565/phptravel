package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadWriteExcelData 
{
	XSSFWorkbook wb;
	File src;
	public ReadWriteExcelData(String filename) {
		
		 try {
			  src = new File(System.getProperty("user.dir")+"/ExcelData/"+filename);
			 FileInputStream file = new FileInputStream(src);
			wb = new XSSFWorkbook(file);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public int getrowCount(String sheetName) {
		return wb.getSheet(sheetName).getPhysicalNumberOfRows();
	}
	
	public int getColumncount(String sheetName, int row) {
		return wb.getSheet(sheetName).getRow(row).getPhysicalNumberOfCells();
	}
	
	public String getdata(String sheetname, int row, int column) {
		String data ="";
		XSSFCell cell =  wb.getSheet(sheetname).getRow(row).getCell(column);
		if(cell.getCellType()==CellType.STRING) {
			data = cell.getStringCellValue();
		}
		if(cell.getCellType()==CellType.NUMERIC) {
			data = String.valueOf(cell.getNumericCellValue());
		}
		 return data;
	}
	
	public void writedata(String email,int row) {
		
		try {
			FileOutputStream output = new FileOutputStream(src);
			wb.getSheet("Sheet1").getRow(row).createCell(2).setCellValue(email);
			wb.write(output);
			wb.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
