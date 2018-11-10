package pt.ribeiro.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
	
	public enum LogMode{
		INFO,
		DEBUG,
		ERROR,
		WARNING;
	}
	
	/*
	 * Default log file location
	 * */
	private static String logFilePath = "resources/logs/Log.txt";
	
	/*
	 * Logging status flag
	 * */
	private static boolean isLogging = true;
	
	/*
	 * @param Message - The message to write to the Log
	 * @param mode - The mode which defines the tag
	 * */
	public static void Log(String message, LogMode mode){
		if(isLogging){
			message = "["+mode.name()+"][" + Time.getNowTimestamp() + "] - " + message;
			writeLineToLogFile(message);
		}
	}

	/*
	 * TODO
	 * @return
	 * */
	public static void Log(String message){
		Log(message, LogMode.INFO);
	}
	
	public static void startSession() {
		if(isLogging){
			String message = "[" + Time.getNowTimestamp() + "]--- START SESSION ---";
			writeLineToLogFile("\n"+message+"\n");
		}
	}
	
	public static void endSession() {
		if(isLogging){
			String message = "[" + Time.getNowTimestamp() + "]--- END SESSION ---";
			writeLineToLogFile("\n"+message+"\n");
		}
	}
	
	/*
	 * TODO
	 * @return
	 * */
	public static void enableLogging(){
		if(!isLogging){
			isLogging = true;
		}
	}
	
	/*
	 * TODO
	 * @return
	 * */
	public static void disableLogging(){
		if(isLogging){
			isLogging = false;
		}
	}
	
	/*
	 * TODO
	 * @return
	 * */
	public static void changeLogFile(String newFilePath){
		try{
			FileSystem.createFileIfDoesntExist(newFilePath);
		}catch(IOException e){
			//Failed to log... Nothing we can do
		}
	}

	/*
	 * TODO
	 * @return
	 * */
	private static void writeLineToLogFile(String line){
		try {
			File logFile = FileSystem.createFileIfDoesntExist(logFilePath);
			FileWriter fw = new FileWriter(logFile.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(line+"\n");
			bw.close();
		} catch (IOException e) {
			//Failed to log... Nothing we can do
		}
	}
	
}
