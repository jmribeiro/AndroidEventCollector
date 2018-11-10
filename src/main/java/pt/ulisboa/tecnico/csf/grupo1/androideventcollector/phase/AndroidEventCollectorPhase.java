package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase;

import pt.ribeiro.util.userinterface.UserInterface;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FatalException;

public abstract class AndroidEventCollectorPhase {
	
	protected UserInterface _userInterface;
	
	public AndroidEventCollectorPhase(UserInterface userInterface){
		_userInterface = userInterface;
	}
	
	public abstract void run() throws FatalException;

}
