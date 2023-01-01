package data.timeseries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class TimeSeries<T> implements Iterable<TimeSeries.Entry<T>>{

	private final Map<Long, T> map = new TreeMap<Long, T>();
    
	public void add(Long time, T event) {
		map.put(time, event);
		
		try {
			TimeUnit.MICROSECONDS.sleep(1);		// 0.000001 sec delay
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public T get(Long time) {
		return map.get(time);
	}
	
	public ArrayList<TimeSeries.Entry<T>> getList(){
		ArrayList<TimeSeries.Entry<T>> list = new ArrayList<TimeSeries.Entry<T>>();
		for (TimeSeries.Entry<T> entry : this) {
			list.add(entry);
		}
		return list;
	}
	
	public int size() {
        return map.size();
    }
	
	@Override
	public Iterator<Entry<T>> iterator() {		
		return new Iterator<TimeSeries.Entry<T>>() {

			private final Iterator it = map.keySet().iterator();
			
			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			@Override
			public Entry<T> next() {
				Long time = (Long) it.next();
                T event = map.get(time);
                return new Entry(time, event);
			}
		};
	}

	public static class Entry<T> {
		private final Long time;
		private final T event;
		
		public Entry(Long time, T event) {
			this.time = time;
			this.event = event;
		}

		public Long getTime() {
			return time;
		}

		public T getEvent() {
			return event;
		}
		
		@Override
        public String toString() {
            return String.format("(%d, %s)", time, event);
        }
	}
}
