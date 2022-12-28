package data.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadingCSVFiles {
	
	public static void main(String[] args) {
		File dataFile = new File("datas/hashmap/Countries.csv");
		
		try {
			Scanner sc = new Scanner(dataFile);
			sc.useDelimiter(",|\\s");			// 자바의 모든 공백 문자(단일 공백, 탭, 새 줄 등)
			String column1 = sc.next();
			String column2 = sc.next();
			System.out.printf("%-10s%12s%n", column1, column2);
			
			while(sc.hasNext()) {
				String country = sc.next();
				int population = sc.nextInt();
				
				// %-10s 문자열을 좌측 정렬해서 10열 형태로 출력
				// %,12d 정수형 데이터를 우측 정렬해 12열 형태로 출력 
				System.out.printf("%-10s%,12d%n", country, population);	
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
