package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception;

public class FolderDoesntExistException extends NonFatalException {

	private static final long serialVersionUID = -7189098149241576137L;

	public FolderDoesntExistException(String folder) {
		super("Folder "+folder+" Doesnt Exist");
	}

}
