package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception;

public class NoImagesInstalledException extends NonFatalException {

	private static final long serialVersionUID = 5205431384219960050L;

	public NoImagesInstalledException() {
		super("No Device Images To Load From");
	}

}
