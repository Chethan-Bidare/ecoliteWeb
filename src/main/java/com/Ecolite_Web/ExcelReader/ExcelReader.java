package com.Ecolite_Web.ExcelReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.Ecolite_Web.TestBase.TestBase;

public class ExcelReader extends TestBase {
	
	public String path ;
	
	XSSFWorkbook wb ;
	XSSFSheet sheet ;
	XSSFRow Row ;
	XSSFCell Cell ;
	FileInputStream fis ;
	
	
	public ExcelReader(String path){
		
		this.path = path ;
		
		try {
			fis = new FileInputStream(path);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@SuppressWarnings({ "deprecation", "static-access" })
	public String[][] getDataFromSheet(String SheetName,String ExcelName){
		String DataSets[][] = null ;
		
		sheet = wb.getSheet(SheetName);
		int TotalRows = sheet.getLastRowNum() + 1 ;
		int TotalCols = sheet.getRow(0).getLastCellNum();
		
		DataSets= new String[TotalRows -1][TotalCols] ;
		
		for(int i=1 ;i<TotalRows; i++){
			Row = sheet.getRow(i);
			for(int j=0; j<TotalCols; j++){
				
				Cell = Row.getCell(j);
				
				if(Cell.getCellType()==Cell.CELL_TYPE_STRING){
					DataSets[i-1][j]= Cell.getStringCellValue();
				}
				else if(Cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
					
					String CellText = String.valueOf(Cell.getNumericCellValue());
					DataSets[i-1][j]=CellText ;
				}
				else if(Cell.getCellType()==Cell.CELL_TYPE_BOOLEAN){
					String CellText = String.valueOf(Cell.getBooleanCellValue());
					DataSets[i-1][j]=CellText ;
				}
			}
		}
		return DataSets ;
	}
}
