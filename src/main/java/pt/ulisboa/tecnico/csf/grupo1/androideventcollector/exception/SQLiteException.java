package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception;

import java.sql.SQLException;

public class SQLiteException extends NonFatalException {

	private static final long serialVersionUID = 8239021337628062402L;

	public SQLiteException(SQLException e) {
		super(e.getMessage());
	}

}
