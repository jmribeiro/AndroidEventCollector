package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception;

public class NoVirtualDevicesRunningException extends NonFatalException {

	private static final long serialVersionUID = 878082343788505725L;

	public NoVirtualDevicesRunningException() {
		super("There Are No Running Devices To Analyze");
	}

}
