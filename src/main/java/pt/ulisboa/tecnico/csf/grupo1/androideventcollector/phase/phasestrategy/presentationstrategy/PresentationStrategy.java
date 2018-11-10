package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.presentationstrategy;

import java.util.List;

import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.presentation.ModuleHandler;

public abstract class PresentationStrategy {
	 
	protected List<ModuleHandler> _dataHandlers;
	
	public PresentationStrategy(List<ModuleHandler> dataHandlers){
		_dataHandlers = dataHandlers;
	}
	
	public abstract String presentData();

}
