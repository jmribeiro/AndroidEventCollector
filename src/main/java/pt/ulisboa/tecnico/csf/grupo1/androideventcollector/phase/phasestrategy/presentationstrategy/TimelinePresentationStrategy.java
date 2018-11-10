package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.presentationstrategy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pt.ribeiro.util.Time;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.AndroidEventCollectorException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.presentation.ModuleHandler;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.structures.Event;

public class TimelinePresentationStrategy extends PresentationStrategy {
	
	private Date _startDate, _endDate;
	
	public TimelinePresentationStrategy(List<ModuleHandler> dataHandlers, Date startDate, Date endDate) {
		super(dataHandlers);
		_startDate = startDate;
		_endDate = endDate;
	}

	@Override
	public String presentData() {
		
		String output="";
		
		List<Event> moduleEvents = new ArrayList<Event>();
		for(ModuleHandler handler : _dataHandlers){
			try {
				moduleEvents.addAll(handler.getEvents());
			} catch (AndroidEventCollectorException e1) {
				output +="No data for module "+handler.getName()+"\n\n";
			} 
		}
		
		moduleEvents = Event.removeEventsNotInRange(moduleEvents, _startDate, _endDate);
		Event.sortByDate(moduleEvents);
		
		output += "---[TIMELINE]---\n\nStart Date: "+Time.dateToString(_endDate)+"\n|\nV\n";
		
		for(Event e : moduleEvents){
			output+="EVENT: "+e.getTypeOfEvent()+"\n";
			output+="DATE: "+e.getDate()+"\n";
			output+="Description:\n"+e.getEventDescription()+"\n|\nV\n";
		}
		
		return output+"End Date: "+Time.dateToString(_endDate)+"\n";
	}

}
