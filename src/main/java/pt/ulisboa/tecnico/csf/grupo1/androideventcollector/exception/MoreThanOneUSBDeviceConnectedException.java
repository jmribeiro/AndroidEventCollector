package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception;

public class MoreThanOneUSBDeviceConnectedException extends NonFatalException {

	private static final long serialVersionUID = -7861085461442706253L;

	public MoreThanOneUSBDeviceConnectedException() {
		super("USB Extraction Strategy Requires One And Only One Connected Android Device Via USB...");
	}

}
