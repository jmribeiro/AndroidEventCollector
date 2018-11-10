package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.core;

import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.extractionstrategy.ExtractionStrategy;

public abstract class ModuleCore {
	
	protected ExtractionStrategy _extractionStrategy;
	protected String _moduleExtractionOutputFolder;
	
	public ModuleCore(ExtractionStrategy extractionStrategy, String moduleExtractionOutputFolder){
		_extractionStrategy = extractionStrategy;
		_moduleExtractionOutputFolder = moduleExtractionOutputFolder;
	}
}
