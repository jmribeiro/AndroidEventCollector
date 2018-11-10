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
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.structures.Call;

public class CallExtractorCore extends ModuleCore {

	public CallExtractorCore(ExtractionStrategy extractionStrategy, String moduleExtractionOutputFolder) {
		super(extractionStrategy, moduleExtractionOutputFolder);
	}

	public void extractDatabaseFileFromDevice() throws FilesNotFoundOnDeviceException{
		String callDatabaseFile = "/data/data/com.android.providers.contacts/databases/contacts2.db";
		_extractionStrategy.copyFile(callDatabaseFile, _moduleExtractionOutputFolder+"/fetchedData/calllog.db");
	}
	
	public List<Call> loadCallFromExtractedDatabaseFile() throws NonFatalException {
		String callDatabaseFile = _moduleExtractionOutputFolder+"/fetchedData/calllog.db";
    	List<Call> call = new ArrayList<Call>();
    	
    	try{
    		Database db = Database.getInstance();
    		
			try {
				db.connectToTable(callDatabaseFile);
			} catch (SQLException e) {
				throw new SQLiteException(e);
			}
	    		
			ResultSet queryResult;
			try {
				
				queryResult = db.runQuery("Select _id, number, date, duration, countryiso from calls");
			
				while(queryResult.next()){

			        String callId = queryResult.getString("_id");
			        String number = queryResult.getString("number");
			        Date callDate = Time.unixEpochToLocalTime(queryResult.getString("date"));			        
					String callDuration = queryResult.getString("duration");					
			        String callCountryIso =  queryResult.getString("countryiso");			        
			        Call currentCall = new Call(callId, number, callDate, callDuration, callCountryIso);
			        call.add(currentCall);
			    }
				
			} catch (SQLException e) {
				throw new SQLiteException(e);
			}
			db.closeConnection();
			
			return call;
    	}catch(ClassNotFoundException e){
    		throw new NonFatalException(e.getMessage());
    	} catch (SQLException e) {
			throw new SQLiteException(e);
		}
	}
	
	public void saveCallListToFile(String callFileName, List<Call> callFound) throws FailedToSaveToFileException {
		
		String filePath = _moduleExtractionOutputFolder+"/crunchedData/"+callFileName;
		
		try{
			FileSystem.saveObject(callFound, filePath);
	    }catch (IOException e) {
    		throw new FailedToSaveToFileException(callFileName, e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Call> getCallFromFile(String callFileName) throws FailedToLoadFromFileException  {
		
		try{
			return (List<Call>) FileSystem.loadObject( callFileName);
		} catch (ClassNotFoundException | IOException | ClassCastException e) {
    		throw new FailedToLoadFromFileException(callFileName, e.getMessage());
		}
	}	

}
