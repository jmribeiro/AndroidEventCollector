package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.structures;

import java.io.Serializable;
import java.util.Date;

import pt.ribeiro.util.Time;

public class Call implements Serializable, Event {

	private static final long serialVersionUID = 5490075440362266524L;
	
	private String _id;
	private String _number;
	private Date _date;
	private String _duration;
	private String _countryIso;
	
	public Call(String id, String number, Date date, String duration, String countryIso) {
		
		_id = id;
		_number = number;
		_date = date;
		_duration = duration;
		_countryIso = countryIso;
	}
	
	@Override
	public String toString() {
		return "Call [id=" + _id + ", Number=" + _number + ", date=" + Time.dateToString(_date) 
				+ ", Duration=" + Time.secondsToTimeString(Long.parseLong(_duration)) +  "]";
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Call other = (Call) obj;
		if (_countryIso == null) {
			if (other._countryIso != null)
				return false;
		} else if (!_countryIso.equals(other._countryIso))
			return false;
		if (_date == null) {
			if (other._date != null)
				return false;
		} else if (!_date.equals(other._date))
			return false;
		if (_duration == null) {
			if (other._duration != null)
				return false;
		} else if (!_duration.equals(other._duration))
			return false;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (_number == null) {
			if (other._number != null)
				return false;
		} else if (!_number.equals(other._number))
			return false;
		return true;
	}

	@Override
	public Date getDate() {
		return _date;
	}

	@Override
	public String getTypeOfEvent() {
		return "Call";
	}

	@Override
	public String getEventDescription() {
		return "Call with "+_number+"\nDuration: "+Time.secondsToTimeString(Long.parseLong(_duration));
	}
	

}
