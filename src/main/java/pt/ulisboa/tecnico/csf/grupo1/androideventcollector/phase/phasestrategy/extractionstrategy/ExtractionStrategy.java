package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.extractionstrategy;

import java.io.IOException;

import pt.ribeiro.util.FileSystem;
import pt.ribeiro.util.Hasher;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FilesNotFoundOnDeviceException;

public abstract class ExtractionStrategy {
	
	public void copyFile(String devicePath, String destinationPath) throws FilesNotFoundOnDeviceException{
		
		getFile(devicePath, destinationPath);
		
		try{
			byte[] md5Signature = Hasher.hashFile(destinationPath);
			FileSystem.createFile(destinationPath+".md5_signature", Hasher.byteArrayToString(md5Signature));
		}catch(IOException e){
			//Failed to fetch
		}
		
		if(!FileSystem.fileExists(destinationPath)){
			throw new FilesNotFoundOnDeviceException(destinationPath);
		}
	}
	
	public abstract void getFile(String devicePath, String destinationPath);
}
