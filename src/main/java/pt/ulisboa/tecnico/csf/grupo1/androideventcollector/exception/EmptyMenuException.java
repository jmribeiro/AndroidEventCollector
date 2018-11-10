package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception;

public class EmptyMenuException extends NonFatalException {

	private static final long serialVersionUID = -2761978548212384988L;

	public EmptyMenuException() {
		super("Menu Must Have At Least One Item");
	}

}
