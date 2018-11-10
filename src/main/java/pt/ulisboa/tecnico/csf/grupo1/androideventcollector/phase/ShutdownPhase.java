package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase;

import pt.ribeiro.util.FileSystem;
import pt.ribeiro.util.userinterface.UserInterface;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FatalException;

public class ShutdownPhase extends AndroidEventCollectorPhase {

	public ShutdownPhase(UserInterface userInterface) {
		super(userInterface);
	}

	@Override
	public void run() throws FatalException {
		deleteTemporaryFiles();
	}
	
	private void deleteTemporaryFiles() {
		FileSystem.eraseDirectoryContents("resources/tmp");
	}

}
