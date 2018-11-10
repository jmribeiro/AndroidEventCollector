package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.structures;

import java.io.Serializable;
import java.util.Date;

import pt.ribeiro.util.Time;

public class SMS implements Serializable, Event{
	
	private static final long serialVersionUID = 4330447019471919301L;
	
	private String _id;
	private String _address;
	private Date _date;
	private String _body;
	
	//TODO KNOW IF IT WAS SENT OR RECEIVED
	private boolean _sent = false;
	
	public SMS(String smsId, String smsAddress, Date smsDate, String smsBody){
		_id = smsId;
		_address = smsAddress;
		_date = smsDate;
		_body = smsBody;
	}
	
	@Override
	public String toString(){
		return "ID: "+ _id
				+ "\nAddress: "+_address
				+ "\nDate: "+Time.dateToString(_date)
				+ "\nBody: "+_body
				;
				
	}
	
	@Override
	public boolean equals(Object object){
		
		if(!(object instanceof SMS)) return false;
		
		SMS sms2 = (SMS)object;
		
		return _id.equals(sms2._id) 
				&& _address.equals(sms2._address)
				&& _date.equals(sms2._date)
				&& _body.equals(sms2._body)
				;
	}
	
	@Override
	public Date getDate() {
		return _date;
	}

	@Override
	public String getTypeOfEvent() {
		return (_sent ? "SENT SMS" : "RECEIVED SMS");
	}

	@Override
	public String getEventDescription() {
		//TODO FIXME _sent variable is not set properly
		return "SMS "+ (_sent ? "sent to " : "received from ") +_address+"\nText:\n\t"+_body;
	}
	
}
