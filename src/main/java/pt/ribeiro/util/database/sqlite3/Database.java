package pt.ribeiro.util.database.sqlite3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database{
	
	/*
	 * Singleton Object
	 * */
	private static Database singleton;
	
	/*
	 * Singleton GetInstance
	 * @return Singleton Object
	 * */
	public static Database getInstance() throws ClassNotFoundException{
		if(singleton == null){
			singleton = new Database();
		}
		return singleton;
	}
	
	/* ################################################################ */
	
	/*
	 * The connection to the .db file
	 * */
	private Connection _connection;
	
	private Database() throws ClassNotFoundException{
		//Load up the class
		Class.forName("org.sqlite.JDBC");
	}
	
	/*
	 * Connects to a sqlite3 .db file
	 * @param The filepath as String
	 * */
	public void connectToTable(String databaseFilePath) throws SQLException{
		if(_connection!=null){
			_connection.close();
		}
		_connection = DriverManager.getConnection("jdbc:sqlite:"+databaseFilePath);
	}
	
	/*
	 * Closes the connection to the file
	 * */
	public void closeConnection() throws SQLException{
		if(_connection != null) {
			_connection.close();
		}
	}
	
	/*
	 * Executes a query string (non escaped)
	 * @return The Query ResultSet
	 * */
	public ResultSet runQuery(String query) throws SQLException{
		Statement statement = _connection.createStatement();
	    statement.setQueryTimeout(30);
	    return statement.executeQuery(query);
	}
}

