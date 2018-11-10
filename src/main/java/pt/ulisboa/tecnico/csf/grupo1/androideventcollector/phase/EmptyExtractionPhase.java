package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase;

import java.util.Arrays;
import java.util.List;

import pt.ribeiro.util.FileSystem;
import pt.ribeiro.util.userinterface.UserInterface;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FatalException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.Module;

public class EmptyExtractionPhase extends ExtractionPhase {

	public EmptyExtractionPhase(UserInterface userInterface, String extractionFolder) throws FatalException {
		super(userInterface);
		_extractionFolder = extractionFolder;
		setupModules();
	}
	
	/* Public Interface */

	private void setupModules() throws FatalException{
			
		List<String> moduleNames =  Arrays.asList(FileSystem.getDirectoryNamesFromFolder(_extractionFolder));
		
		setupModules(moduleNames);
		
		for(Module module : _modules){
			module.pairWithPreviousExtraction(_extractionFolder);
		}
		
	}

	@Override
	public void run(){
		//Easy, We're Done
	}

	public boolean isComplete(){
		return true;
	}
	

}
