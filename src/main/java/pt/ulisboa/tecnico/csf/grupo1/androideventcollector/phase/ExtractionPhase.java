package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import pt.ribeiro.util.FileSystem;
import pt.ribeiro.util.Logger;
import pt.ribeiro.util.Logger.LogMode;
import pt.ribeiro.util.userinterface.UserInterface;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FatalException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.ModuleClassNotImplementedException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.ModuleDoesntExistException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.NonFatalException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.Module;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.presentation.ModuleHandler;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.extractionstrategy.ExtractionStrategy;

public class ExtractionPhase extends AndroidEventCollectorPhase {
	
	protected String _extractionFolder;
	protected List<Module> _modules;
	private ExtractionStrategy _extractionStrategy;
	
	public ExtractionPhase(UserInterface userInterface, ExtractionStrategy extractionStrategy, List<String> moduleNames,  String extractionsFolder) throws ModuleDoesntExistException{
		super(userInterface);
		_extractionStrategy = extractionStrategy;
		setupOnFolder(extractionsFolder);
		setupModules(moduleNames);
	}
	
	protected ExtractionPhase(UserInterface userInterface){
		super(userInterface);
	}
	
	
	/* Public Interface */
	
	public void run() throws FatalException {
		Logger.Log("Extracting");
		_userInterface.displayLine(">>> Extracting, Please Wait <<<\n");
		for(Module module : _modules){
			Logger.Log("Module "+module.getName());
			_userInterface.displayLine(">>> Running module "+module.getName()+" <<<");
			try{
				module.run();
				Logger.Log("Module "+module.getName()+" Complete!");
				Thread.sleep(5000);
				_userInterface.displayLine("Module "+module.getName()+" Complete!\n");
				
			}catch(NonFatalException e){
				Logger.Log("Module Error:\n"+e.getMessage());
				_userInterface.displayLine(e.getMessage());	
			}catch(InterruptedException e){
				Logger.Log("Error sleeping after module "+module.getName(), LogMode.WARNING);
			}
		}
		_userInterface.displayLine("");
	}
	
	public List<ModuleHandler> getModuleHandlers() throws ModuleClassNotImplementedException{
		
		List<ModuleHandler> dataHandlers = new ArrayList<ModuleHandler>();
		
		for(Module module : _modules){
			dataHandlers.add(module.getDataHandler());			
		}
		return dataHandlers;
	}
	
	/* Private Methods */
	
	private void setupOnFolder(String extractionsFolder) {
		
		String newExtractionFolder = getNextExtractionFolderName(extractionsFolder);
		FileSystem.createDirectory(newExtractionFolder);
		_extractionFolder = newExtractionFolder;
		
	}

	private String getNextExtractionFolderName(String extractionsFolder) {
		
		String baseFolder = extractionsFolder+"/extraction#";
		int currentFolderNumber = 0;
		boolean nextFolderNumberFound = false;
		
		while(!nextFolderNumberFound){
			currentFolderNumber++;
			nextFolderNumberFound = !FileSystem.fileExists(baseFolder+currentFolderNumber);
		}
		
		return baseFolder+currentFolderNumber;
	}

	protected void setupModules(List<String> moduleNames) throws ModuleDoesntExistException{
		
		_modules = new ArrayList<Module>();
		
		for(String moduleName : moduleNames){
			
			try{
				Module currentModule = instantiateModule(moduleName);
				_modules.add(currentModule);
			}catch(ClassNotFoundException | NoSuchMethodException | 
					SecurityException |	InstantiationException | 
					IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
			{
				throw new ModuleDoesntExistException(moduleName);
			}
			
		}
	}

	private Module instantiateModule(String moduleName) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> instanceClass = Class.forName("pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module."+moduleName+"Module");
		Constructor<?> constructor = instanceClass.getConstructor(String.class, ExtractionStrategy.class);
		return (Module) constructor.newInstance(_extractionFolder, _extractionStrategy);
	}
}
