package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.core;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pt.ribeiro.util.FileSystem;
import pt.ribeiro.util.Time;
import pt.ribeiro.util.database.sqlite3.Database;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FailedToLoadFromFileException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FailedToSaveToFileException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FilesNotFoundOnDeviceException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.NonFatalException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.SQLiteException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.extractionstrategy.ExtractionStrategy;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.structures.SMS;

public class SMSExtractorCore extends ModuleCore {

	public SMSExtractorCore(ExtractionStrategy extractionStrategy, String moduleExtractionOutputFolder) {
		super(extractionStrategy, moduleExtractionOutputFolder);
	}
	
	public void extractDatabaseFileFromDevice() throws FilesNotFoundOnDeviceException{
		String smsDatabaseFile = "/data/data/com.android.providers.telephony/databases/mmssms.db";
		_extractionStrategy.copyFile(smsDatabaseFile, _moduleExtractionOutputFolder+"/fetchedData/mmssms.db");
	}

	public List<SMS> loadSMSFromExtractedDatabaseFile() throws NonFatalException {
		String smsDatabaseFile = _moduleExtractionOutputFolder+"/fetchedData/mmssms.db";
    	List<SMS> sms = new ArrayList<SMS>();
    	
    	try{
    		Database db = Database.getInstance();
    		
			try {
				db.connectToTable(smsDatabaseFile);
			} catch (SQLException e) {
				throw new SQLiteException(e);
			}
	    		
			ResultSet queryResult;
			try {
				
				queryResult = db.runQuery("Select _id, address, person, date, body from sms");
			
				while(queryResult.next()){

			        String smsId = queryResult.getString("_id");
			        String smsAddress = queryResult.getString("address");
			        Date smsDate = Time.unixEpochToLocalTime(queryResult.getString("date"));
			        String smsBody =  queryResult.getString("body");
			        
			        SMS currentSMS = new SMS(smsId, smsAddress, smsDate, smsBody);
			        sms.add(currentSMS);
			    }
				
			} catch (SQLException e) {
				throw new SQLiteException(e);
			}
			db.closeConnection();
			
			return sms;
    	}catch(ClassNotFoundException e){
    		throw new NonFatalException(e.getMessage());
    	} catch (SQLException e) {
			throw new SQLiteException(e);
		}
	}

	public void saveSMSListToFile(String smsFileName, List<SMS> smsFound) throws FailedToSaveToFileException {
		
		String filePath = _moduleExtractionOutputFolder+"/crunchedData/"+smsFileName;
		
		try{
			FileSystem.saveObject(smsFound, filePath);
	    }catch (IOException e) {
    		throw new FailedToSaveToFileException(smsFileName, e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<SMS> getSMSFromFile(String smsFileName) throws FailedToLoadFromFileException  {
		
		try{
			return (List<SMS>) FileSystem.loadObject( smsFileName);
		} catch (ClassNotFoundException | IOException | ClassCastException e) {
    		throw new FailedToLoadFromFileException(smsFileName, e.getMessage());
		}
	}	

}
