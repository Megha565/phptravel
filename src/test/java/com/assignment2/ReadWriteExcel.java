package com.assignment2;

import java.io.IOException;

import org.testng.annotations.Test;

import com.utility.ReadWriteExcelData;

public class ReadWriteExcel {
	
	@Test
	public void readWriteExcel() throws IOException {
		ReadWriteExcelData data = new ReadWriteExcelData("sample.xlsx");
		int row = data.getrowCount("Sheet1");
		for(int i = 0;i<row;i++) {
			String firstname = data.getdata("Sheet1", i, 0);
			String secondname = data.getdata("Sheet1", i, 1);
			System.out.println("firstname::"+firstname);
			System.out.println("secondname::"+secondname);
			String email = firstname+secondname+"@gmail.com";
			System.out.println("email::"+email);
			data.writedata(email,i);
		}
		
	}

}
