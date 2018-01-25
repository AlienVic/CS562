package stevens.cs562.EMFQuery.Util;

import java.sql.*;
import java.util.HashMap;
import java.util.Properties;

public class ConnectDB {
	private static Properties prop;
	private static final String Configname = "../../../../db.properties";
	private Connection conn;
	private String tableName;
	private HashMap<String,String> tableStructure = new HashMap<String, String>();
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public void setProp(Properties prop) {
		ConnectDB.prop = prop;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public void setTableStructrue(HashMap<String,String> tableStructure) {
		this.tableStructure = tableStructure;
	}
	
	public String getTableName() {
		return tableName;
	}
	public static Properties getProp() {
		return prop;
	}
	public Connection getConn() {
		return conn;
	}
	

	
	
	public ConnectDB() throws Exception {
		prop = new Properties();
		
		//System.out.println(Configname);
		prop.load(this.getClass().getResourceAsStream(Configname));
		try {
			Class.forName(prop.getProperty("DRIVER_CLASS"));
			System.out.println("Success loading Driver!");
		}
		catch(Exception e) {
			System.err.println("Fail loading Driver !");
			e.printStackTrace();
		}
		try{
			conn = DriverManager.getConnection(
						prop.getProperty("CONNECTION_URL"), 
						prop.getProperty("CONNECTION_USERNAME"),
						prop.getProperty("CONNECTION_PASSWORD"));
			tableName = "sales";	
			tableStructure = setTableStructure("sales");
		}
		catch(SQLException e) {
			System.out.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}
		
	}
	public HashMap<String, String> setTableStructure(String tableName)
	{		
		tableStructure = new HashMap<String, String>();
		try
		{
			Connection connection = conn;
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select column_name,data_type" +
											" from information_schema.columns"
											+ " where table_name='"+ tableName+"'");
			
			while(rs.next())
			{
				String dataType = null;
				String dataName = rs.getString("column_name");
				if(rs.getString("data_type").equals("character varying") ||
						rs.getString("data_type").equals("character"))
				{
					dataType = "String";
				}
				else
					dataType = "int";
				tableStructure.put(dataName, dataType);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("getTableStructure fail");
		}
		return tableStructure;
		
	}
	public HashMap<String, String> getTableStructure() {
		return tableStructure;
	}
	
}
