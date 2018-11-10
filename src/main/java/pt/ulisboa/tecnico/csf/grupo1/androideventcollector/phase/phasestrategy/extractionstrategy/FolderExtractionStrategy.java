package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.extractionstrategy;

import java.io.IOException;

import pt.ribeiro.util.FileSystem;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FolderDoesntExistException;

public class FolderExtractionStrategy extends ExtractionStrategy {
	
	private String _rootImageFolder;

	public FolderExtractionStrategy(String rootImageFolder) throws FolderDoesntExistException{
		setRootImageFolder(rootImageFolder);
	}
	
	private void setRootImageFolder(String rootImageFolder) throws FolderDoesntExistException {
		if (!FileSystem.fileExists(rootImageFolder)){
			throw new FolderDoesntExistException(rootImageFolder);
		}
		_rootImageFolder = rootImageFolder;
		
	}
	@Override
	public void getFile(String devicePath, String destinationPath) {
		try{
			FileSystem.copyFile(_rootImageFolder+"/"+devicePath, destinationPath);
		}catch(IOException e){
		}
		
	}

	
}
