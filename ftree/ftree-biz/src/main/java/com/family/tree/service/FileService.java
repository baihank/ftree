package com.family.tree.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileService {
	// 只支持一个文档单sheet文档
    private static final int SHEET_INDEX = 0;
	
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
