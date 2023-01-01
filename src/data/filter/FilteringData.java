package data.filter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FilteringData {

	public static final int MIN_AREA = 1000000;  // one million 
	
	public static void main(String[] args) {
		FilteringData fd = new FilteringData();
		File file = new File("datas/files/Countries2.dat");
		Set<Country> dataset = fd.readDataset(file);
		
		for (Country country : dataset) {
			if(country.landlocked && country.area >= MIN_AREA) {
				System.out.println(country);
			}
		}		
	}

	public Set<Country> readDataset(File file) {
		Set<Country> set = new HashSet<Country>();
		
		try {
			Scanner sc = new Scanner(file);
			sc.nextLine();
			while(sc.hasNextLine()) {
				set.add(new Country(sc));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return set;
	}

}
