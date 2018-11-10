package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception;

public class NoDevicesConnectedException extends NonFatalException {

	private static final long serialVersionUID = 9127677303566429646L;

	public NoDevicesConnectedException() {
		super("There Are No Connected Devices To Analyze");
	}

}
