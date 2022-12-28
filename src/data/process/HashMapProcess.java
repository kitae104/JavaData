package data.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class HashMapProcess {
	public static void main(String[] args) {
		File dataFile = new File("datas/hashmap/Countries.dat");
//        System.out.println(dataFile.length());

		HashMap<String, Integer> dataSet = new HashMap<String, Integer>();
		try {
			Scanner input = new Scanner(dataFile);
			while (input.hasNext()) {
				String country = input.next();
				int population = input.nextInt();
				dataSet.put(country, population); 	// 해시에 데이터 입력 
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		System.out.printf("dataset.size(): %d\n", dataSet.size());
        System.out.printf("dataset.get(\"Peru\"): %,d\n", dataSet.get("Peru"));
        
        dataSet.put("Peru", 31000000);
        
        System.out.printf("dataset.size(): %d%n", dataSet.size());
        System.out.printf("dataset.get(\"Peru\"): %,d%n", dataSet.get("Peru"));
	}
}
