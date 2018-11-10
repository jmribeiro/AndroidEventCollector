package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception;

public class ModuleDoesntExistException extends FatalException {

	private static final long serialVersionUID = -3866151421593257653L;

	public ModuleDoesntExistException(String moduleName) {
		super("Module "+moduleName+" doesn't exist");
	}

}
