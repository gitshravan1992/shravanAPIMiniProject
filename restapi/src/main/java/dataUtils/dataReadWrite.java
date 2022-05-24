package dataUtils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataReadWrite {
	
	public static void writeExcel(String key,String value) {
		try{
			File file=new File("src//test//resources//dataSheet.xlsx");
			FileInputStream fis=new FileInputStream(file);
			XSSFWorkbook wb=new XSSFWorkbook(fis);
			XSSFSheet  sheet=wb.getSheet("Sheet1");
			int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
			int rowindex=0;
			Row row = null;
			for(int i=1;i<=rowCount;i++){
				if(sheet.getRow(i).getCell(0).toString().equalsIgnoreCase(key)){
					rowindex=i;
					row=sheet.getRow(rowindex);

					break;
				}
				else if(sheet.getRow(i).getCell(0).toString().isEmpty()){
					rowindex=i;
					 row=sheet.createRow(rowindex);

					break;
				}
				
			}

			row.createCell(0).setCellValue(key);
			row.createCell(1).setCellValue(value);

			FileOutputStream outputStream = new FileOutputStream(file);
	        wb.write(outputStream);
	        wb.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public static String readExcel(String key) {
		String col = null;

		try{
			File file=new File("src//main//java//resources//dataSheet.xlsx");
			FileInputStream fis=new FileInputStream(file);
			XSSFWorkbook wb=new XSSFWorkbook(fis);
			XSSFSheet  sheet=wb.getSheet("Sheet1");
			int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
			int rowindex=0;
			for(int i=1;i<=rowCount;i++){
				if(sheet.getRow(i).getCell(0).toString().equalsIgnoreCase(key)){
					rowindex=i;
					break;
				}
				
			}

			Row row=sheet.getRow(rowindex);
			 col=row.getCell(1).toString();

			
	        wb.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
        return col;
	}
}


