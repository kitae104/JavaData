package data.timeseries;

import java.util.ArrayList;

import data.timeseries.TimeSeries.Entry;

public class TimeSeriesTester {

	static final String[] EVENTS = {"It", "was", "the", "best", "of", "times"};
	
	public static void main(String[] args) {
		TimeSeries<String> series = new TimeSeries<String>();
		load(series);
		
		for (TimeSeries.Entry<String> entry : series) {
			Long time = entry.getTime();
			String event = entry.getEvent();
			System.out.printf("%16d: %s%n", time, event);
		}
		
		ArrayList list = series.getList();
	    System.out.printf("list.get(3) = %s%n", list.get(3));
	}

	private static void load(TimeSeries<String> series) {
		for (String event : EVENTS) {
			series.add(System.currentTimeMillis(), event);
		}
	}

}
