package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception;

public class NoPreviousExtractionsException extends NonFatalException {

	private static final long serialVersionUID = -6353297351467288217L;

	public NoPreviousExtractionsException() {
		super("No Previous Extractions To Load From");
	}

}
