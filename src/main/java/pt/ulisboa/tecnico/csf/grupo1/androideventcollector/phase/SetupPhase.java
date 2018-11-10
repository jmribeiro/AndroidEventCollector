package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase;

import java.util.Date;

import pt.ribeiro.util.Logger;
import pt.ribeiro.util.userinterface.UserInterface;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FatalException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.setupstrategy.SetupStrategy;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.setupstrategy.UserConfigurationStrategy;

public class SetupPhase extends AndroidEventCollectorPhase {

	private SetupStrategy _setupStrategy;
	private ExtractionPhase _extractionPhase;
	private Date _startDate;
	private Date _endDate;
	private boolean _timeframe;
	
	public SetupPhase(UserInterface userInterface, String[] args) {
		super(userInterface);
		pickSetupStrategy(args);
	}
	
	private void pickSetupStrategy(String[] args) {
		if(args.length!=0){
			Logger.Log("User Configuration Setup Selected (Since command line init is not implemented)");
			_userInterface.displayLine("Argument initialization not implemented yet.");
			_setupStrategy = new UserConfigurationStrategy(_userInterface);
		}else{
			Logger.Log("User Configuration Setup Selected");
			_setupStrategy = new UserConfigurationStrategy(_userInterface);
		}	
	}

	public ExtractionPhase getExtractionPhase() {
		return _extractionPhase;
	}
	
	@Override
	public void run() throws FatalException {
		
		Logger.Log("Setup complete!");
		_extractionPhase = _setupStrategy.configureExtractionPhase();
		
		_timeframe = _setupStrategy.hasTimeframe();
		if(_timeframe){
			_startDate = _setupStrategy.getStartDate();
			_endDate = _setupStrategy.getEndDate();
			Logger.Log("Presentation will use event timeline");
		}
	}
	
	public ExtractionPhase getStrategy(){
		return _extractionPhase;
	}
	
	public boolean hasTimeframe(){
		return _timeframe;
	}
	
	public Date getStartDate(){
		return _startDate;
	}
	
	public Date getEndDate(){
		return _endDate;
	}

}
