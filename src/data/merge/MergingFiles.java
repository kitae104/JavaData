package data.merge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MergingFiles {

	public static void main(String[] args) {
		File inFile1 = new File("datas/files/Countries3.dat");
        File inFile2 = new File("datas/files/Countries4.dat");
        File outFile = new File("datas/output/Countries.dat");
        
        try {
			Scanner sc1 = new Scanner(inFile1);
			Scanner sc2 = new Scanner(inFile2);
			PrintWriter out = new PrintWriter(outFile);
			
			Country country1 = new Country(sc1);
			Country country2 = new Country(sc2);
			System.out.println(country1.hashCode());
			System.out.println(country2.hashCode());
			
			while(!country1.isNull() && !country2.isNull()) {
				if(country1.compareTo(country2) < 0) {			// 인구수에 대해 비교한 후에 내용 이동 
					out.println(country1);
					country1 = new Country(sc1);
				} else {
					out.println(country2);
					country2 = new Country(sc2);
				}
			}
			
			while(!country1.isNull()) {
				out.println(country1);
				country1 = new Country(sc1);
			}
			
			while(!country2.isNull()) {
				out.println(country2);
				country2 = new Country(sc2);
			}
			
			sc1.close();
			sc2.close();
			out.close();
			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
	}
}
