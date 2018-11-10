package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.extractionstrategy;

import pt.ribeiro.util.ShellCommandExecuter;

public class DeviceExtractionStrategy extends ExtractionStrategy {
	
	private String _deviceId;
	
	public DeviceExtractionStrategy(String deviceId){
		_deviceId = deviceId;
	}

	@Override
	public void getFile(String deviceFilePath, String destinationPath) {
		String command = "adb -s " + _deviceId + " pull -a " + deviceFilePath + " " + destinationPath;
		ShellCommandExecuter.exec(command);
	}
	

}
