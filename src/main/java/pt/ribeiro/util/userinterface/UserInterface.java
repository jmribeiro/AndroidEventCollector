package pt.ribeiro.util.userinterface;

import java.util.List;


public abstract class UserInterface {
	
	public abstract void display(String string);
	public abstract void displayLine(String line);
	
	public abstract String getUserInput();
	
	public String getUserInput(String promptMessage) {
		displayLine(promptMessage);
		return getUserInput();
	}
	
	public int getMenuItem(List<String> menuOptions) throws EmptyMenuException{
		
		if(menuOptions.size() == 0) throw new EmptyMenuException();
		
		displayOptions(menuOptions);
		
		while(true){
			try{
				String userAnswer = getUserInput("Please Pick An Option Above");
				int option = Integer.parseInt(userAnswer);
				if(option >= 0 && option > (menuOptions.size()-1) ) throw new NumberFormatException();
				return option;
			}catch(NumberFormatException e){
				displayLine("Please Pick A Number Between 0 and "+(menuOptions.size()-1));
			}
		}
	}
	
	private void displayOptions(List<String> menuOptions) {
		for(int menuNumber = 0; menuNumber < menuOptions.size()-1; menuNumber++){
			displayLine((menuNumber+1) + ") "+menuOptions.get(menuNumber));
		}
		displayLine("0) "+menuOptions.get(menuOptions.size()-1));
	}
}
