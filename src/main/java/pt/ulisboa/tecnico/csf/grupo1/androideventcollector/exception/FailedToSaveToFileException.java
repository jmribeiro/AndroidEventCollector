package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception;

public class FailedToSaveToFileException extends NonFatalException {

	private static final long serialVersionUID = -8397178035314406476L;

	public FailedToSaveToFileException(String fileName) {
		super("Failed to save objecto to "+fileName);
	}

	public FailedToSaveToFileException(String fileName, String ioExceptionDetails){
		super("Failed to save objecto to "+fileName+"\n"+ioExceptionDetails);
	}
	
}
