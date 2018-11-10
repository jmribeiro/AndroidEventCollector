package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public interface Event {
	
	public Date getDate();
	public String getTypeOfEvent();
	public String getEventDescription();
	
	public static void sortByDate(List<Event> events) {
		Collections.sort(events, new Comparator<Event>() {
			public int compare(Event e1, Event e2) {
			      return e1.getDate().compareTo(e2.getDate());
			  }
			}
		);
	}
	
	public static List<Event> removeEventsNotInRange(List<Event> events, Date startDate, Date endDate) {
		List<Event> eventsWithinRange = new ArrayList<Event>();
		for(int i = 0; i<events.size(); i++){
			Event e = events.get(i);
			
			Date currentDate = e.getDate();
			
			if(currentDate.after(startDate) && currentDate.before(endDate)){
				eventsWithinRange.add(e);
			}
		
		}
		return eventsWithinRange;
	}
}
