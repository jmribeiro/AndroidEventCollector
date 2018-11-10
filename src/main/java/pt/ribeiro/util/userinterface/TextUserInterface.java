package pt.ribeiro.util.userinterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextUserInterface extends UserInterface {
	
	private static final String PROMPT = "$";
	
	@Override
	public void display(String string) {
		System.out.print(string);
	}

	@Override
	public void displayLine(String line) {
		System.out.println(line);
	}
	
	@Override
	public String getUserInput(){
		display(PROMPT+" ");
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		try {
			return bufferedReader.readLine();
		} catch (IOException e) {
			return "";
		}
	}

	
}
