package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase;

import java.util.ArrayList;
import java.util.List;

import pt.ribeiro.util.FileSystem;
import pt.ribeiro.util.ShellCommandExecuter;
import pt.ribeiro.util.userinterface.UserInterface;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.R;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FatalException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.RequiredToolsNotInstalledException;

public class InitializationPhase extends AndroidEventCollectorPhase {
	
	public InitializationPhase(UserInterface userInterface) {
		super(userInterface);
	}

	@Override
	public void run() throws FatalException {
		createDirectories();
		displayTitle();
		displayDisclaimer();
		checkInstalledTools();
	}
	
	private void createDirectories() {
		String root = "resources";
		
		String[] directories = new String[]{
				root+"/tmp",
				root+"/logs"		
		};
		
		for(String directoryPath : directories){
			if(!FileSystem.fileExists(directoryPath)){
				FileSystem.createDirectory(directoryPath);
			}
		}
	}

	private void checkInstalledTools() throws RequiredToolsNotInstalledException{
		displayToolCheckStatus();
		
		boolean adbInstalled = checkADBInstallation();
		boolean sqlite3Installed = checkSQLite3Installation();
				
		List<String> missingTools = new ArrayList<String>();

		if(!adbInstalled) missingTools.add("ADB (Android Debug Bridge)");
		if(!sqlite3Installed) missingTools.add("SQLite3");
		if(!adbInstalled || !sqlite3Installed) throw new RequiredToolsNotInstalledException(missingTools);
	}

	private void displayToolCheckStatus() {
		_userInterface.displayLine("\nChecking For Required Tools On The Computer");
	}

	private boolean  checkADBInstallation(){
		String adbOutputTest = ShellCommandExecuter.exec("adb devices");
		return adbOutputTest.contains("List of devices attached");
	}

	private boolean checkSQLite3Installation(){
		String sqlite3OutputTest = ShellCommandExecuter.exec("sqlite3 -version");
		return sqlite3OutputTest.startsWith("3.");
	}
	
	private void displayTitle() {
		_userInterface.displayLine(R.TITLE);
	}

	private void displayDisclaimer() {
		_userInterface.display(R.DISCLAIMER);
		
	}

}
