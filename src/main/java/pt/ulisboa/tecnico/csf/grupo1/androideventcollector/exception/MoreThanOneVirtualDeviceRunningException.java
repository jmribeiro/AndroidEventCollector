package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception;

public class MoreThanOneVirtualDeviceRunningException extends NonFatalException {

	private static final long serialVersionUID = 4774241165779130795L;

	public MoreThanOneVirtualDeviceRunningException() {
		super("Virtual Device Extraction Strategy Requires One And Only One Running Virtual Android Device...");
	}

}
