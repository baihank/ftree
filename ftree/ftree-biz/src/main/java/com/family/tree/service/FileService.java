package com.family.tree.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileService {
	// ֻ֧��һ���ĵ���sheet�ĵ�
    private static final int SHEET_INDEX = 0;
	
	/**
	 * @throws IOException 
	* @Title: initDate 
	* @Description: ��ʼ��������
	* @param     �趨�ļ� 
	* @return void    �������� 
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