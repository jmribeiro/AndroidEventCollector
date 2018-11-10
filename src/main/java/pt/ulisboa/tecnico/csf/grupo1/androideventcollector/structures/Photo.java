package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.structures;

import java.io.Serializable;
import java.util.Date;

public class Photo implements Serializable {

	private static final long serialVersionUID = 1320785201868164893L;
	
	private String _id;
	private Date _date;
	private String _size;
	private String _display_name;

	public Photo(String _id, Date _date, String _size, String _display_name) {
		super();
		this._id = _id;
		this._date = _date;
		this._size = _size;
		this._display_name = _display_name;
	}
	
	@Override
	public String toString() {
		return "Photo [_id=" + _id + ", _date=" + _date + ", _size=" + _size + ", _display_name=" + _display_name + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Photo other = (Photo) obj;
		if (_date == null) {
			if (other._date != null)
				return false;
		} else if (!_date.equals(other._date))
			return false;
		if (_display_name == null) {
			if (other._display_name != null)
				return false;
		} else if (!_display_name.equals(other._display_name))
			return false;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (_size == null) {
			if (other._size != null)
				return false;
		} else if (!_size.equals(other._size))
			return false;
		return true;
	}

}
