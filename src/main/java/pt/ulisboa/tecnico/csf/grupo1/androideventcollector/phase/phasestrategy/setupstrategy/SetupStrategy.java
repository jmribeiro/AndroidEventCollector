package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.setupstrategy;

import java.util.Date;

import pt.ribeiro.util.userinterface.UserInterface;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FatalException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.ExtractionPhase;

public abstract class SetupStrategy {
	
	protected UserInterface _userInterface;
	
	public SetupStrategy(UserInterface userInterface){
		_userInterface=userInterface;
	}
	
	public abstract ExtractionPhase configureExtractionPhase() throws FatalException;

	public abstract boolean hasTimeframe();

	public abstract Date getStartDate();
	public abstract Date getEndDate();

}
