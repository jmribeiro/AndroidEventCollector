package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception;

import java.util.List;

public class RequiredToolsNotInstalledException extends FatalException {

	private static final long serialVersionUID = 5660449706802432588L;
	
	private String _message = "###################### ERROR #######################";
 
	public RequiredToolsNotInstalledException(String toolName) {
		super("");
		_message += "\nAEC Requires "+toolName+" To Function."
			+ "\nPlease Install "+toolName
			+ "\n####################################################";
	}
	
	public RequiredToolsNotInstalledException(List<String> tools){
		super("");	
		_message+="\n";
		for(String tool : tools){
			_message += "Required tool not found: "+tool+" [X]\n";
		}
		_message += "####################################################";			

	}
	
	@Override
	public String getMessage(){
		return _message;
	}
}
