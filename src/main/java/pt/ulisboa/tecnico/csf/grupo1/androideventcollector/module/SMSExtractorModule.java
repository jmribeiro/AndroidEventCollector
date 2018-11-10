package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module;

import java.util.List;

import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.AndroidEventCollectorException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FilesNotFoundOnDeviceException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.NonFatalException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.SQLiteException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.core.ModuleCore;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.core.SMSExtractorCore;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.presentation.ModuleHandler;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.presentation.SMSExtractorModuleHandler;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.extractionstrategy.ExtractionStrategy;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.structures.SMS;

public class SMSExtractorModule extends Module {
	
	private SMSExtractorCore _core;
	
	public SMSExtractorModule(String extractionRootFolder, ExtractionStrategy extractionStrategy) throws AndroidEventCollectorException {
		super(extractionRootFolder, extractionStrategy);
	}

	public SMSExtractorModule() throws AndroidEventCollectorException {
		super();
	}

	@Override
	public String getName() {
		return "SMSExtractor";
	}

	@Override
	protected void fetchData() throws NonFatalException{
		_core.extractDatabaseFileFromDevice();
	}

	@Override
	protected void crunchData() throws NonFatalException  {
		try{
			List<SMS> smsFound = _core.loadSMSFromExtractedDatabaseFile();
			_core.saveSMSListToFile("SMS.ser", smsFound);
		} catch (SQLiteException e) {
			throw new FilesNotFoundOnDeviceException(this);
		}
	}

	@Override
	protected void setCore(ModuleCore core) {
		_core = (SMSExtractorCore) core;
	}

	@Override
	protected void setDataHandlerCore(ModuleHandler dataHandler) {
		((SMSExtractorModuleHandler)dataHandler).setCore(_core);
		
	}

}
