package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception;

public class InvalidArgumentException extends FatalException 
{
	private static final long serialVersionUID = -6328862368525075525L;

	public InvalidArgumentException(String identifier) 
	{
		super("Unrecognized Option: " + identifier);
	}

}
