package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception;

public class FailedToLoadFromFileException extends NonFatalException {

	private static final long serialVersionUID = -2341286723130284741L;

	public FailedToLoadFromFileException(String fileName) {
		super("Failed to load object from "+fileName);
	}
	
	public FailedToLoadFromFileException(String fileName, String ioExceptionDetails){
		super("Failed to load object from "+fileName+"\n"+ioExceptionDetails);
	}

}
