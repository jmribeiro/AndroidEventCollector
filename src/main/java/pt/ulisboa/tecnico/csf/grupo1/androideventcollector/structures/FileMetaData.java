package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.structures;

import java.io.Serializable;
import java.util.Date;

import pt.ribeiro.util.Time;

public class FileMetaData implements Serializable, Event {

	private static final long serialVersionUID = 5490075857462266524L;
	
	private String _filename;
	private Date _modificationDate;

	public FileMetaData(String filename, Date modificationTime) {
		_filename = filename;
		_modificationDate = modificationTime;
	}
	
	@Override
	public String toString() {
		return getEventDescription();
	}

	@Override
	public boolean equals(Object object) {
		if(!(object instanceof FileMetaData)) return false;
		
		FileMetaData file2 = (FileMetaData)object;
		
		return _filename.equals(file2._filename) 
				&& _modificationDate.equals(file2._modificationDate)
				;
	}

	@Override
	public Date getDate() {
		return _modificationDate;
	}

	@Override
	public String getTypeOfEvent() {
		return "File";
	}

	@Override
	public String getEventDescription() {
		return "File | Name: "+_filename+" | Last Modification: "+Time.dateToString(_modificationDate);
	}
	

}
