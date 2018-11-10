package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import pt.ribeiro.util.FileSystem;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.AndroidEventCollectorException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.ModuleClassNotImplementedException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.NonFatalException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.core.ModuleCore;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.presentation.ModuleHandler;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.extractionstrategy.ExtractionStrategy;

public abstract class Module{
 
	public static List<String> installedModules(){
		
		List<String> modules = new ArrayList<String>();
		
		/* ADD THE MODULES HERE */
		
			modules.add("SMSExtractor");
			modules.add("CallExtractor");
			modules.add("SDExtractor");
			modules.add("PhotoExtractor");
		
		/* ADD THE MODULES ABOVE */
		
		return modules;
	}
	
	protected String _moduleExtractionOutputFolder;
	
	private ExtractionStrategy _extractionStrategy;
	
	public Module(String extractionRootFolder, ExtractionStrategy extractionStrategy) throws AndroidEventCollectorException{
		
		setupOutputFolder(extractionRootFolder);
		_extractionStrategy = extractionStrategy;
		setupCore();
	}
	
	public Module() throws AndroidEventCollectorException{
		
		_extractionStrategy = null;
		setupCore();
	}
	
	private void setupOutputFolder(String extractionRootFolder) {
		_moduleExtractionOutputFolder = extractionRootFolder+"/"+getName();
		FileSystem.createDirectory(_moduleExtractionOutputFolder+"/"+"fetchedData");
		FileSystem.createDirectory(_moduleExtractionOutputFolder+"/"+"crunchedData");
	}

	/* Public Interface */
	
	public abstract String getName();
	
	public final void start() throws NonFatalException{
		fetchData();
		crunchData();
	}

	public final void run() throws NonFatalException{
		start();
	}

	
	public ModuleHandler getDataHandler() throws ModuleClassNotImplementedException {

		try {
			String moduleName = getName();
			Class<?> instanceClass = Class.forName("pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.presentation."+moduleName+"ModuleHandler");
			Constructor<?> constructor = instanceClass.getConstructor(String.class);
			ModuleHandler dataHandler = (ModuleHandler) constructor.newInstance(_moduleExtractionOutputFolder+"/crunchedData");
			
			setDataHandlerCore(dataHandler);
			
			return dataHandler;
			
		}catch(ClassNotFoundException | NoSuchMethodException | 
				SecurityException |	InstantiationException | 
				IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			throw new ModuleClassNotImplementedException("Module "+getName()+" Is Missing It's Data Handler Class (Presentation), Can't Proceed");
		}
	}
	
	/* Private Interface */
	protected abstract void fetchData() throws NonFatalException;
	protected abstract void crunchData() throws NonFatalException;
	protected abstract void setCore(ModuleCore core);
	protected abstract void setDataHandlerCore(ModuleHandler dataHandler);
	
	protected void setupCore() throws ModuleClassNotImplementedException{
		
			try {
				String moduleName = getName();
				Class<?> instanceClass = Class.forName("pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.core."+moduleName+"Core");
				Constructor<?> constructor = instanceClass.getConstructor(ExtractionStrategy.class, String.class);
				ModuleCore core = (ModuleCore) constructor.newInstance(_extractionStrategy, _moduleExtractionOutputFolder);
				setCore(core);
			}catch(ClassNotFoundException | NoSuchMethodException | 
					SecurityException |	InstantiationException | 
					IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
			{
				throw new ModuleClassNotImplementedException("Module "+getName()+" Is Missing It's Core Class (Presentation), Can't Proceed");
			}
			
	}

	public void pairWithPreviousExtraction(String extractionFolder) {
		_moduleExtractionOutputFolder = extractionFolder+"/"+getName();
	}
	
}
