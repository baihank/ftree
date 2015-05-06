package com.family.tree.io;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileWriter extends FileIO{
	
	/**
	 * @throws IOException 
	* @Title: initDate 
	* @Description: 初始化家谱树
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void initDate(String fileName, InputStream in) throws IOException{
		Workbook wb;
		
		if (fileName.endsWith(".xls"))
			wb = new HSSFWorkbook(in);
		else
			wb = new XSSFWorkbook(in);
		
		Sheet sheet = wb.getSheetAt(SHEET_INDEX);
	}

}
