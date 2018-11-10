package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module;

import java.util.List;

import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.AndroidEventCollectorException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FilesNotFoundOnDeviceException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.NonFatalException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.SQLiteException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.core.CallExtractorCore;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.core.ModuleCore;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.presentation.CallExtractorModuleHandler;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.presentation.ModuleHandler;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.extractionstrategy.ExtractionStrategy;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.structures.Call;

public class CallExtractorModule extends Module {

	private CallExtractorCore _core;	
	
	public CallExtractorModule(String extractionRootFolder, ExtractionStrategy extractionStrategy)
			throws AndroidEventCollectorException {
		super(extractionRootFolder, extractionStrategy);
	}
	
	public CallExtractorModule() throws AndroidEventCollectorException {
		super();
	}

	@Override
	public String getName() {
		return "CallExtractor";
	}

	@Override
	protected void fetchData() throws NonFatalException {
		_core.extractDatabaseFileFromDevice();
		
	}

	@Override
	protected void crunchData() throws NonFatalException  {
		
		try {
			List<Call> callFound = _core.loadCallFromExtractedDatabaseFile();
			_core.saveCallListToFile("Call.ser", callFound);
		} catch (SQLiteException e) {
			throw new FilesNotFoundOnDeviceException(this);
		}
		
	}

	@Override
	protected void setCore(ModuleCore core) {
		_core = (CallExtractorCore) core;
		
	}

	@Override
	protected void setDataHandlerCore(ModuleHandler dataHandler) {
		((CallExtractorModuleHandler)dataHandler).setCore(_core);
		
	}

}
