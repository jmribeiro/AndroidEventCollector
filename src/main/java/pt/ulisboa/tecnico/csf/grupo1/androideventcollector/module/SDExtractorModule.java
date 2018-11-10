package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module;

import java.io.IOException;

import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.AndroidEventCollectorException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.NonFatalException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.core.ModuleCore;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.core.SDExtractorCore;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.presentation.ModuleHandler;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.presentation.SDExtractorModuleHandler;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.extractionstrategy.ExtractionStrategy;

public class SDExtractorModule extends Module {
	
	private SDExtractorCore _core;
	
	public SDExtractorModule(String extractionRootFolder, ExtractionStrategy extractionStrategy) throws AndroidEventCollectorException {
		super(extractionRootFolder, extractionStrategy);
	}
	
	@Override
	public String getName() {
		return "SDExtractor";
	}

	@Override
	protected void fetchData() throws NonFatalException{
		//Sacar ficheiros de sdcard para fetch data
		_core.extractDataFromSD();
	}

	@Override
	protected void crunchData() throws NonFatalException{
		try{
			_core.crunchFiles();
		}catch(IOException e){
			//Not suppose to happen
		}
	}

	@Override
	protected void setCore(ModuleCore core) {
		_core = (SDExtractorCore) core;
	}

	@Override
	protected void setDataHandlerCore(ModuleHandler dataHandler) {
		((SDExtractorModuleHandler)dataHandler).setCore(_core);
		
	}

}
