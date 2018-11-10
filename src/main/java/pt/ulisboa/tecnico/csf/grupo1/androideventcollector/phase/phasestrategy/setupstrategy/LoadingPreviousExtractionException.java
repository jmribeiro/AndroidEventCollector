package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.setupstrategy;

import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.NonFatalException;

public class LoadingPreviousExtractionException extends NonFatalException {

	private static final long serialVersionUID = 7698831606875855950L;
	
	private String _extractionFolder;
	
	public LoadingPreviousExtractionException(String extractionFolder) {
		super("Not Extracting");
		_extractionFolder = extractionFolder;
	}
	
	public String getExtractionFolder(){
		return _extractionFolder;
	}

}
