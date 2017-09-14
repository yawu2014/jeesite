/**
 * 
 */
package com.thinkgem.jeesite.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 * <p>
 * 
 * </p>
 * 
 * @author liuyujian
 * @version 1.0
 * @date 2017年9月7日 
 * @since JDK 1.8
 * @copyright Copyright 2017 CLOUD SERVICES.
 */
public class PoiUtils {
	private String getStringValue(XSSFCell cell) {
		switch(cell.getCellType()) {
		case XSSFCell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case XSSFCell.CELL_TYPE_NUMERIC:
			return String.valueOf(cell.getNumericCellValue());
		}
		return null;
	}
	private ArrayList<ArrayList<Object>> readFile(File file,String sheetName){
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet = workbook.getSheet(sheetName);//获取指定的Sheet
			int rowCount = sheet.getPhysicalNumberOfRows();//行数
			System.out.println("total:"+rowCount);
			for(int i=63;i<rowCount;i++) {
				StringBuilder builder = new StringBuilder();
				XSSFRow row = sheet.getRow(i);
				String id = getStringValue(row.getCell(0));
				String code = getStringValue(row.getCell(1));
//				String name = getStringValue(row.getCell(2));
				String diseaseCode = getStringValue(row.getCell(3));
				String diseaseName = getStringValue(row.getCell(4));
//				String diseaseAns = getStringValue(row.getCell(5));
				builder.append("1\t").append(diseaseCode+"\t").append(diseaseName).append("\t").append("\t1\t1\t=\ts\tnumber\t").append(code).append("\t").append(id);
				/*
				//遍历行
				int colstart = row.getFirstCellNum();
				int colend = row.getLastCellNum();
				for(int j=colstart;j<colend;j++) {
					XSSFCell cell = row.getCell(j);
					if(cell != null && cell.getRawValue() != null) {
						if(cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
							builder.append("\t"+cell.getStringCellValue());
						}else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
							builder.append("\t"+cell.getNumericCellValue());
						}
						
					}
				}*/
				System.out.println(builder.toString());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		}
		return null;
	}
	@Test
	public void resolveFile() {
		File file = new File("E:\\workplace\\糖尿病\\糖尿病药品库2017-7-25v2.xlsx");
		readFile(file,"绝对禁忌症");
	}
}
