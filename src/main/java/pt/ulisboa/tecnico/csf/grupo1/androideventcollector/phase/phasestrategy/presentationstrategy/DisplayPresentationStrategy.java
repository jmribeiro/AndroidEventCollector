package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.presentationstrategy;

import java.util.List;

import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.AndroidEventCollectorException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.presentation.ModuleHandler;

public class DisplayPresentationStrategy extends PresentationStrategy {

	public DisplayPresentationStrategy(List<ModuleHandler> dataHandlers) {
		super(dataHandlers);
	}

	@Override
	public String presentData() {
		String output = "<<< DATA FOUND BY MODULES >>>\n\n";
		for(ModuleHandler handler : _dataHandlers){
			output+="[MODULE]"+handler.getName()+"\n";
			try {
				output+=handler.getOutput();
			} catch (AndroidEventCollectorException e) {
				output +="No data for "+handler.getName()+", Skipping\n";
			}
		}
		return output+"\n";
	}

}
