package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.presentation;

import java.util.ArrayList;
import java.util.List;

import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.AndroidEventCollectorException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FailedToLoadFromFileException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.core.ModuleCore;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.core.SMSExtractorCore;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.structures.Event;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.structures.SMS;

public class SMSExtractorModuleHandler extends ModuleHandler {
	
	public SMSExtractorModuleHandler(String extractionFolder) {
		super(extractionFolder);
	}

	private SMSExtractorCore _core;

	public String getName(){
		return "SMSExtractor";
	}
	
	@Override
	public String getOutput() throws AndroidEventCollectorException {
		return getFoundSMSFormatted();
	}
	
	public String getFoundSMSFormatted() throws AndroidEventCollectorException{
		String output = "";
		List<SMS> foundSMS = getFoundSMS();
		for(SMS sms : foundSMS){
			output+=sms+"\n--------------------\n";
		}
		return output;
	}
	
	public List<SMS> getFoundSMS() throws FailedToLoadFromFileException {
		List<SMS> smsList;
		
		smsList = _core.getSMSFromFile(_crunchedDataFolder+"/SMS.ser");
		//TODO treat not written yet exception differently
		
		return smsList;
	}

	@Override
	public void setCore(ModuleCore core) {
		_core = (SMSExtractorCore)core;
	}

	@Override
	public List<Event> getEvents() throws AndroidEventCollectorException {
		List<Event> events = new ArrayList<Event>();
		events.addAll(getFoundSMS());
		return events;
	}


}
