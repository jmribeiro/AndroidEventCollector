package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.presentation;

import java.util.ArrayList;
import java.util.List;

import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.AndroidEventCollectorException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FailedToLoadFromFileException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.core.CallExtractorCore;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.core.ModuleCore;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.structures.Call;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.structures.Event;

public class CallExtractorModuleHandler extends ModuleHandler {

	public CallExtractorModuleHandler(String crunchedDataFolder) {
		super(crunchedDataFolder);
	}
	
	private CallExtractorCore _core;
	
	@Override
	public String getName() {
		return "CallExtractor";
	}

	@Override
	public void setCore(ModuleCore core) {
		_core = (CallExtractorCore)core;
		
	}

	@Override
	public String getOutput() throws AndroidEventCollectorException {
		return getFoundCallFormatted();
		
	}

	private String getFoundCallFormatted() throws FailedToLoadFromFileException {
		String output = "";
		List<Call> foundCalls = getFoundCalls();
		for(Call call : foundCalls){
			output+=call+"\n--------------------\n";
		}
		return output;
		
	}

	private List<Call> getFoundCalls() throws FailedToLoadFromFileException {
		List<Call> callList;
		
		callList = _core.getCallFromFile(_crunchedDataFolder+"/Call.ser");
		
		return callList;
	}

	@Override
	public List<Event> getEvents() throws AndroidEventCollectorException {
		List<Event> events = new ArrayList<Event>();
		events.addAll(getFoundCalls());
		return events;
	}

}
