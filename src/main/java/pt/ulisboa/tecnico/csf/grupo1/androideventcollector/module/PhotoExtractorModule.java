package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module;

import java.io.IOException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.AndroidEventCollectorException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.NonFatalException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.core.ModuleCore;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.core.PhotoExtractorCore;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.presentation.ModuleHandler;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.presentation.PhotoExtractorModuleHandler;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.presentation.SDExtractorModuleHandler;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.extractionstrategy.ExtractionStrategy;

public class PhotoExtractorModule extends Module {

	private PhotoExtractorCore _core;	
	
	public PhotoExtractorModule(String extractionRootFolder, ExtractionStrategy extractionStrategy) throws AndroidEventCollectorException {
		super(extractionRootFolder, extractionStrategy);
	}
	
	@Override
	public String getName() {
		return "PhotoExtractor";
	}

	@Override
	protected void fetchData() throws NonFatalException{
		_core.extractPhotoFromDevice();
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
		_core = (PhotoExtractorCore) core;
	}

	@Override
	protected void setDataHandlerCore(ModuleHandler dataHandler) {
		((PhotoExtractorModuleHandler)dataHandler).setCore(_core);
		
	}

}