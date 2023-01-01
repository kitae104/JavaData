package data.sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

public class SortingData {
	
	public static void main(String[] args) {
		File file = new File("datas/files/Countries.dat");
		TreeMap<Integer, String> dataSet = new TreeMap<Integer, String>();
		
		try {
			Scanner sc = new Scanner(file);
			while(sc.hasNext()) {
				String x = sc.next();
				int y = sc.nextInt();
				dataSet.put(y, x);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		print(dataSet);
	}

	private static void print(TreeMap<Integer, String> dataSet) {
		System.out.printf("%14s  %-16s%n", "Population", "Name");
		System.out.println("===============================");
		for (Integer key : dataSet.keySet()) {
			System.out.printf("%,14d  %-16s%n", key, dataSet.get(key));
		}
	}
}
