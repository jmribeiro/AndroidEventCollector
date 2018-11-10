package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.core;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import java.util.List;
import java.util.ArrayList;

import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.structures.FileMetaData;
import pt.ribeiro.util.FileSystem;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FailedToLoadFromFileException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FailedToSaveToFileException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FilesNotFoundOnDeviceException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.extractionstrategy.ExtractionStrategy;

public class SDExtractorCore extends ModuleCore {
	
	public SDExtractorCore(ExtractionStrategy extractionStrategy, String moduleExtractionOutputFolder) {
		super(extractionStrategy, moduleExtractionOutputFolder);
	}
	
	public void extractDataFromSD() throws FilesNotFoundOnDeviceException{
		String path = "/sdcard/";
		_extractionStrategy.copyFile(path,_moduleExtractionOutputFolder+"/fetchedData/sdcard");
	}
	
	public void crunchFiles() throws IOException, FailedToSaveToFileException{
		File directory = new File(_moduleExtractionOutputFolder+"/fetchedData/sdcard");
		List<FileMetaData> metaData = new ArrayList<FileMetaData>();
	
		for(File f : directory.listFiles()){

			String name = f.getName();
			Date modificationDate = new Date(f.lastModified());

			metaData.add(new FileMetaData(name, modificationDate));
		}
		
		saveFilesMetaDataListToFile("filesMetaData.ser", metaData);
		
	}
	
	public void saveFilesMetaDataListToFile(String fileName, List<FileMetaData> files) throws FailedToSaveToFileException {
		
		String filePath = _moduleExtractionOutputFolder+"/crunchedData/"+fileName;
		
		try{
			FileSystem.saveObject(files, filePath);
	    }catch (IOException e) {
    		throw new FailedToSaveToFileException(fileName, e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<FileMetaData> getFilesMetaDataFromFile() throws FailedToLoadFromFileException  {
		
		try{
			return (List<FileMetaData>) FileSystem.loadObject(_moduleExtractionOutputFolder+"/crunchedData/filesMetaData.ser");
		} catch (ClassNotFoundException | IOException | ClassCastException e) {
    		throw new FailedToLoadFromFileException(_moduleExtractionOutputFolder+"/crunchedData/filesMetaData.ser", e.getMessage());
		}
	}

}
