package data.filter;

import java.util.Scanner;

public class Country {
	protected String name;
	protected int population;
	protected int area;
	protected boolean landlocked;

	public Country(Scanner in) {
		if (in.hasNextLine()) {
			this.name = in.next();
			this.population = in.nextInt();
			this.area = in.nextInt();
			this.landlocked = in.nextBoolean();
		}
	}

	@Override
	public String toString() {
		return String.format("%-10s %,12d %,12d %b", name, population, area, landlocked);
	}	
}
