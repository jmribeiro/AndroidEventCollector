package pt.ribeiro.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ShellCommandExecuter {
	
	/*
	 * Execute command and arguments
	 * @param Array (command + arguments)
	 * @return String with all the return lines (separated by \n)
	 */
	public static String exec(String[] commandAndArgs){
		
		String commandWithArgs = commandAndArgs[0];

		for(int i = 1; i<commandAndArgs.length; i++){
			commandWithArgs += " " + commandAndArgs[i];
		}
		
		return exec(commandWithArgs);
	}
	
	/*
	 * Execute command with arguments
	 * @param String command and arguments array
	 * @return String with all the return lines (separated by \n)
	 */
	public static String exec(String command, String[] args){
		
		String commandWithArgs = command;
		
		for(int i = 0; i<args.length; i++){
			commandWithArgs += " " + args[i];
		}
		
		return exec(commandWithArgs);
	}
	
	/*
	 * Execute command with arguments
	 * @param String command and arguments split by spaces " "
	 * @return String with all the return lines (separated by \n)
	 */
	public static String exec(String commandWithArgs) {

		StringBuffer output = new StringBuffer();
		output.append("");

		Process p;
		
		try {
			p = Runtime.getRuntime().exec(getOSShell()+" "+commandWithArgs); 
            p.waitFor(); 
            BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			
			while((line = reader.readLine()) != null){
				output.append(line + "\n");
			}
		} catch (Exception e) {
			output.append(e.getMessage());
		}
		return output.toString();
	}
	
	/*
	 * Private method that returns the O.S. as a string 
	 * @return The OS String
	 */
	private static String getOSShell(){

		if(System.getProperty("os.name").startsWith("Windows")){
			return "cmd /c";
		}else if(System.getProperty("os.name").startsWith("Linux")){
			return "/bin/sh -c";
		}else{
			return "cmd /c";
		}

	}
}