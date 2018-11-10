package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.presentation;

import java.util.List;

import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.AndroidEventCollectorException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.core.ModuleCore;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.structures.Event;

public abstract class ModuleHandler {
	
	protected String _crunchedDataFolder;
	
	public ModuleHandler(String crunchedDataFolder){
		setupCrunchedDataFolderPath(crunchedDataFolder);
	}
	
	public abstract String getName();
	public abstract void setCore(ModuleCore core);
	
	private void setupCrunchedDataFolderPath(String crunchedDataFolder) {
		_crunchedDataFolder = crunchedDataFolder;
	}

	public abstract String getOutput() throws AndroidEventCollectorException;

	public abstract List<Event> getEvents() throws AndroidEventCollectorException;


}
