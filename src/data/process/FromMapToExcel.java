package data.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class FromMapToExcel {

	public static void main(String[] args) {
		
		FromMapToExcel fme = new FromMapToExcel();
		
		Map<String, Integer> map = new TreeMap<String, Integer>();
		fme.load(map, "datas/hashmap/Countries.dat");
		fme.print(map);
		fme.storeExcel(map, "datas/hashmap/Countries.xls", "Countries Worksheet");
	}

	/**
	 * 파일 읽어서 Map으로 만들기 
	 * @param map
	 * @param fileName
	 */
	public void load(Map<String, Integer> map, String fileName) {
		File file = new File(fileName);

		try {
			Scanner input = new Scanner(file);
			while (input.hasNext()) {
				String country = input.next();
				int population = input.nextInt();
				map.put(country, population); 	// 해시에 데이터 입력 
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}

	/**
	 * Map 내용 출력하기 
	 * @param map
	 */
	public void print(Map<String, Integer> map) {
		Set<String> countries = map.keySet();
		for (String country : countries) {
			int population = map.get(country);
			System.out.printf("%-10s%,15d%n", country, population);
		}
	}

	public void storeExcel(Map<String, Integer> map, String fileName, String sheet) {
		try {
			FileOutputStream out = new FileOutputStream(fileName);
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet(sheet);
			Set<String> countries = map.keySet();
			int rowNum = 0; 
			
			for (String country : countries) {
				int population = map.get(country);
				HSSFRow row = worksheet.createRow(rowNum++);
				row.createCell(0).setCellValue(country);
				row.createCell(1).setCellValue(population);
			}
			
			workbook.write(out);
			out.flush();
			out.close();
			
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
