package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.presentation;

import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.AndroidEventCollectorException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FailedToLoadFromFileException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.core.ModuleCore;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.core.SDExtractorCore;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.structures.Event;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.structures.FileMetaData;

import java.util.ArrayList;
import java.util.List;

public class SDExtractorModuleHandler extends ModuleHandler {
	
	public SDExtractorModuleHandler(String extractionFolder) {
		super(extractionFolder);
	}

	private SDExtractorCore _core;

	public String getName(){
		return "SDExtractor";
	}
	
	@Override
	public void setCore(ModuleCore core) {
		_core = (SDExtractorCore)core;
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
