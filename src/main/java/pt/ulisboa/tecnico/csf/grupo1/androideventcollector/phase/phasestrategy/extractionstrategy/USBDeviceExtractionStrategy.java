package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.extractionstrategy;

import pt.ribeiro.util.ShellCommandExecuter;

public class USBDeviceExtractionStrategy extends ExtractionStrategy {
	
	@Override
	public void getFile(String devicePath, String destinationPath) {
		String command = "adb -d pull -a " + devicePath + " " + destinationPath;
		ShellCommandExecuter.exec(command);
	}

}
