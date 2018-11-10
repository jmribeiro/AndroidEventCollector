package pt.ulisboa.tecnico.csf.grupo1.androideventcollector;

import java.util.Date;

import pt.ribeiro.util.Logger;
import pt.ribeiro.util.Logger.LogMode;
import pt.ribeiro.util.userinterface.TextUserInterface;
import pt.ribeiro.util.userinterface.UserInterface;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FatalException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.RequiredToolsNotInstalledException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.AndroidEventCollectorPhase;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.ExtractionPhase;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.InitializationPhase;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.PresentationPhase;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.SetupPhase;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.ShutdownPhase;

public class AndroidEventCollector {
	
	/* ############################################# */
	/* ############################################# */
	
	public static final String VERSION = "0.1.0";
	public static String getVersion(){
		return VERSION;
	}
	
	/* ############################################# */
	/* ############################################# */
	
	private static AndroidEventCollector singleton;
	public static AndroidEventCollector getInstance(){
		if(singleton==null){
			singleton = new AndroidEventCollector();
		}
		return singleton;
	}
	
	private AndroidEventCollector(){
		reset();
	}
	
	/* ############################################# */
	/* ############################################# */
	
	private AndroidEventCollectorPhase _currentPhase;
	private UserInterface _userInterface;
	private boolean _timeframedAnalysis = false;
	private Date _startDate;
	private Date _endDate;
	
	public void start(String[] args) {
		Logger.Log("Starting...");
		try{
			enterInitializationPhase();
			enterSetupPhase(args);
			enterExtractionPhase();
			enterPresentationPhase();
			enterShutdownPhase();
		}catch(RequiredToolsNotInstalledException e){
			Logger.Log("System didn't had the required tools:\n"+e.getMessage(), LogMode.ERROR);
			_userInterface.displayLine(e.getMessage());
			System.exit(-1);
		}catch(FatalException e ){
			Logger.Log("Fatal Error:\n"+e.getMessage(), LogMode.ERROR);
			_userInterface.displayLine("Fatal Error:\n"+e.getMessage());
		}
	}
	
	public void reset(){
		Logger.Log("Resetting...");
		_userInterface = new TextUserInterface();
	}

	public void terminate() {
		Logger.Log("Terminating...");
		_userInterface.displayLine("Goodbye...");
		System.exit(0);
	}
	
	/* ############################################# */
	/* ############################################# */
	
	private void enterInitializationPhase() throws FatalException {
		Logger.Log("Initializing AEC");
		_currentPhase = new InitializationPhase(_userInterface);
		_currentPhase.run();
	}
	
	private void enterSetupPhase(String[] args) throws FatalException {
		Logger.Log("Entering Setup Phase");
		_currentPhase = new SetupPhase(_userInterface, args);
		_currentPhase.run();
	}
	
	private void enterExtractionPhase() throws FatalException {
		Logger.Log("Entering Extraction Phase");
		SetupPhase setupPhase = (SetupPhase)_currentPhase;
		_timeframedAnalysis = setupPhase.hasTimeframe();
		if(_timeframedAnalysis){
			_startDate = setupPhase.getStartDate();
			_endDate = setupPhase.getEndDate();
		}
		_currentPhase = setupPhase.getExtractionPhase();
		_currentPhase.run();
	}
	
	private void enterPresentationPhase() throws FatalException{
		Logger.Log("Entering Presentation Phase");
		if(_timeframedAnalysis){
			_currentPhase = new PresentationPhase(_userInterface, ((ExtractionPhase)_currentPhase).getModuleHandlers(), _startDate, _endDate);
		}else{
			_currentPhase = new PresentationPhase(_userInterface, ((ExtractionPhase)_currentPhase).getModuleHandlers());
		}
		_currentPhase.run();
	}
	
	private void enterShutdownPhase() throws FatalException {
		Logger.Log("Shutting Down AEC");
		_currentPhase = new ShutdownPhase(_userInterface);
		_currentPhase.run();
	}

}
