package pt.ulisboa.tecnico.csf.grupo1.androideventcollector;

import pt.ribeiro.util.Logger;

public class Main{
    
	public static void main( String[] args ) throws Exception{
		Logger.startSession();
		AndroidEventCollector app = AndroidEventCollector.getInstance();
		app.start(args);
		Logger.endSession();
    }
	
}
