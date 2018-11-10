package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception;

import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.Module;

public class FilesNotFoundOnDeviceException extends NonFatalException {

	private static final long serialVersionUID = 2459481884661890547L;

	public FilesNotFoundOnDeviceException(Module module) {
		super("Files required for "+module.getName()+" not found on device. Skipping...");
	}
	
	public FilesNotFoundOnDeviceException(String fileName) {
		super("File "+fileName+" not found on device");
	}

}
