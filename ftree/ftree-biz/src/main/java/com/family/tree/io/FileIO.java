package com.family.tree.io;

import java.util.HashMap;
import java.util.Map;

public class FileIO {
	private static String split = "-";
	private Map<String, String> map = new HashMap<String, String>();
	
	public String getIndex(int row, int colum){
		return row + split + colum;
	}
	
	public int getRow(String index){
		String[] indexs = index.split(split);
		return Integer.valueOf(indexs[0]);
	}
	
	public int getColum(String index){
		String[] indexs = index.split(split);
		return Integer.valueOf(indexs[1]);
	}
}
