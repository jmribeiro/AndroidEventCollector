package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.presentation;

import java.util.ArrayList;
import java.util.List;

import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.AndroidEventCollectorException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FailedToLoadFromFileException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.core.ModuleCore;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.core.PhotoExtractorCore;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.structures.Event;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.structures.FileMetaData;

public class PhotoExtractorModuleHandler extends ModuleHandler {

	public PhotoExtractorModuleHandler(String crunchedDataFolder) {
		super(crunchedDataFolder);
	}
	
	private PhotoExtractorCore _core;
	
	@Override
	public String getName() {
		return "PhotoExtractor";
	}

	@Override
	public void setCore(ModuleCore core) {
		_core = (PhotoExtractorCore)core;
		
	}

	@Override
	public String getOutput() throws AndroidEventCollectorException {
		return getFoundFilesFormatted();
	}
	
	public String getFoundFilesFormatted() throws AndroidEventCollectorException{
		String output = "";
		List<FileMetaData> filesMetaData = getFoundFilesMetaData();
		for(FileMetaData file : filesMetaData){
			output+=file+"\n--------------------\n";
		}
		return output;
	}
	
	public List<FileMetaData> getFoundFilesMetaData() throws FailedToLoadFromFileException {
		List<FileMetaData> list;
		
		list = _core.getFilesMetaDataFromFile();
		//TODO treat not written yet exception differently
		
		return list;
	}
	
	@Override
	public List<Event> getEvents() throws AndroidEventCollectorException {
		List<Event> eventos = new ArrayList<Event>(); //FIXME
		return eventos;
	}
	
}