package data.merge;

import java.util.Scanner;

public class Country implements Comparable<Country>{
	protected String name;
	protected int population;	

	public Country(String name, int population) {
		super();
		this.name = name;
		this.population = population;
	}

	public Country(Scanner in) {
		if (in.hasNextLine()) {
			this.name = in.next();
			this.population = in.nextInt();			
		}
	}

	@Override
	public String toString() {
		return String.format("%-10s %,12d", name, population);
	}

	@Override
	public int compareTo(Country o) {
		Country that = o;
		return this.population - that.population;
	}

	public boolean isNull() {
		return this.name == null;
	}
}
