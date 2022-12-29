package data.process;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

public class FromExcelToMap {

	public static void main(String[] args) {
		FromExcelToMap fem = new FromExcelToMap();
		Map<String, Integer> map = fem.loadExcel("datas/hashmap/Countries.xls", "Countries Worksheet");
		fem.print(map);
	}

	public Map<String, Integer> loadExcel(String fileName, String sheetName) {
		Map<String, Integer> map = new TreeMap<String, Integer>();
		
		try {
			FileInputStream fis = new FileInputStream(fileName);
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			HSSFSheet worksheet = workbook.getSheet(sheetName);
			DataFormatter formatter = new DataFormatter();
			
			for (Row row : worksheet) {
				HSSFRow hssfRow = (HSSFRow) row;
				HSSFCell cell = hssfRow.getCell(0);
				String country = cell.getStringCellValue();
				cell = hssfRow.getCell(1);
				String str = formatter.formatCellValue(cell);
				int population = Integer.parseInt(str);
				map.put(country, population);
			}
			
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
		
		return map;
	}

	public void print(Map<String, Integer> map) {
		Set<String> countries = map.keySet();
		for (String country : countries) {
			int population = map.get(country);
			System.out.printf("%-10s%,15d%n", country, population);
		}
	}

}
