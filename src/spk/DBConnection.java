package spk;

import java.sql.*;

public class DBConnection {
	private static String url="jdbc:mysql://localhost:3306/ATM_Database";
	private static String name="root";
	private static String password="Spk@2003";
	
	public static Connection dbConnector() throws SQLException{
		return DriverManager.getConnection(url,name,password);
	}
	
	
	public static ResultSet dbRetrieve() throws SQLException {
		Connection sq=DBConnection.dbConnector();
		String query="select * from userDatabase";
		Statement st=sq.createStatement();
		ResultSet res=st.executeQuery(query);
		return res;
	}
	public static String dbRetrieveId(int serialNo) throws SQLException {
		Connection sq=DBConnection.dbConnector();
		String query="select * from userId_Generator where serialNo="+serialNo;
		Statement st=sq.createStatement();
		ResultSet rs=st.executeQuery(query); 
		String userId="";
		if(rs.next()) {
			userId=rs.getString(1); 
		}
		return userId;	
	}
	public static void dbInsert(String name,int password,long cardNumber,int balanceAvailable) throws SQLException {
		Connection sq=DBConnection.dbConnector();
		String query="insert into userdatabase(name, Password, AccountNumber, Balance) values(?,?,?,?);";
		PreparedStatement ps=sq.prepareStatement(query);
		ps.setString(1,name);
		ps.setInt(2,password);
		ps.setLong(3,cardNumber);
		ps.setInt(4,balanceAvailable);
		ps.executeUpdate();
	}
	public static void dbUpdateBalance(int balance ,int userId) throws SQLException {
		Connection sq=DBConnection.dbConnector();
		String query="update userdatabase set balance="+balance+" where serialNo="+userId;
		Statement st=sq.createStatement();
		st.executeUpdate(query);
	}
	public static int dbRetrieveSerialNo() throws SQLException {
		Connection sq=DBConnection.dbConnector();
		String query="select * from userdatabase";
		Statement st=sq.createStatement();
		ResultSet rs=st.executeQuery(query);
		int serialNo=0;
		while(rs.next()) {
			serialNo=rs.getInt(5);
		}
		return serialNo;
	}
	public static void dbInsertUserId(String userId ,int serialNo) throws SQLException {
		Connection sq=DBConnection.dbConnector();
		String query="update userdatabase set userId=? where serialNo=?";
		PreparedStatement ps=sq.prepareStatement(query);
		ps.setString(1, userId);
		ps.setInt(2, serialNo);
		ps.executeUpdate();
		
	}
}
