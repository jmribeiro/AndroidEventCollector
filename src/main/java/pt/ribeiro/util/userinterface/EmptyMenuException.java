package pt.ribeiro.util.userinterface;

public class EmptyMenuException extends RuntimeException {

	private static final long serialVersionUID = -1523447837537408381L;
	
	public EmptyMenuException(){
		super("UserInterface menus must have at least two options");
	}
}
