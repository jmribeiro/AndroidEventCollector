package pt.ribeiro.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
	
	/*
	 * Format we want our timestamps to be
	 * */
	private static final String TIMESTAMP_FORMAT = "dd/MM/YYYY HH:mm:ss";
	
	/*
	 * Format(ter) object
	 * */
	private static final DateFormat FORMAT = new SimpleDateFormat(TIMESTAMP_FORMAT);
	
	/*
	 * Returns the current call timestamp
	 * @return the timestamp as a string
	 * */
	public static String getNowTimestamp(){	
		Date now = new Date();
		return dateToString(now);
	}
	
	/*
	 * Converts String to Date
	 * @param The Date object
	 * @return The Date's formatted timestamp as string
	 * */
	public static String dateToString(Date date){
		return FORMAT.format(date);
	}

	
	/*
	 * Converts String to Date
	 * @param The timestamp string
	 * @return The Date corresponding to the timestamp
	 * */
	public static Date stringToDate(String dateString) throws ParseException{
		return FORMAT.parse(dateString);
	}
	

	public static String secondsToTimeString(long totalSeconds){
		
		long hours = totalSeconds / 60 / 60;
        long minutes = (totalSeconds - (hours*3600)) / 60;
        long seconds = totalSeconds - ((hours*3600) + (minutes*60));

        return (hours < 10 ? "0" + hours : hours) 
        		+ ":" 
        		+ (minutes < 10 ? "0" + minutes : minutes) 
        		+ ":" 
        		+ (seconds < 10 ? "0" + seconds : seconds);
	}

	/*
	 * Converts unixepoch to Gregorian Callendar
	 * @param unixepoch long
	 * @return the Date corresponding to the unixepoch time
	 * */
	public static Date unixEpochToLocalTime(long unixepoch){
		return new Date(unixepoch);
	}
	
	/*
	 * Converts unixepoch to Gregorian Callendar
	 * @param unixepoch String
	 * @return the Date corresponding to the unixepoch time
	 * */
	public static Date unixEpochToLocalTime(String unixepoch){
		return new Date(Long.parseLong(unixepoch));
	}

	public static Date createDate(String dateString) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.parse(dateString);
	}

}
