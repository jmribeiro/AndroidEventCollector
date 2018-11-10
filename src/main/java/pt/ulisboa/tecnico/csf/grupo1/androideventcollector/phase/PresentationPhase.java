package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pt.ribeiro.util.Logger;
import pt.ribeiro.util.userinterface.UserInterface;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FatalException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.presentation.ModuleHandler;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.presentationstrategy.DisplayPresentationStrategy;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.presentationstrategy.PresentationStrategy;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.presentationstrategy.TimelinePresentationStrategy;

public class PresentationPhase extends AndroidEventCollectorPhase{
	
	private List<PresentationStrategy> _strategies;
	
	@SuppressWarnings("serial")
	public PresentationPhase(UserInterface userInterface, List<ModuleHandler> dataHandlers) {
		super(userInterface);
		_strategies = new ArrayList<PresentationStrategy>(){
			{
				add(new DisplayPresentationStrategy(dataHandlers));
			}
		};
	}
	
	@SuppressWarnings("serial")
	public PresentationPhase(UserInterface userInterface, List<ModuleHandler> dataHandlers, Date startDate, Date endDate) {
		super(userInterface);
		_strategies = new ArrayList<PresentationStrategy>(){
			{
				add(new DisplayPresentationStrategy(dataHandlers));
				add(new TimelinePresentationStrategy(dataHandlers, startDate, endDate));
			}
		};
	}

	@Override
	public void run() throws FatalException{
		
		for(PresentationStrategy strategy : _strategies){
			Logger.Log("Presenting Data To User");
			_userInterface.displayLine("###############################");
			_userInterface.display(strategy.presentData());
			_userInterface.displayLine("###############################");
		}
	}

}
