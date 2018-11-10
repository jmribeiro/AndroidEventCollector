package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception;

public class DeviceNotConnectedException extends NonFatalException {

	private static final long serialVersionUID = -6629980024305173260L;

	public DeviceNotConnectedException(String deviceId) {
		super("Device "+deviceId+" Not Connected");
	}
	
	public DeviceNotConnectedException() {
		super("Device Not Connected");
	}
	

}
