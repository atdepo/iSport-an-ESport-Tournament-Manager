package it.unisa.model.connessione;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
public class DriverManagerConnectionPool  {

	private static List<Connection> freeDbConnections= new LinkedList<Connection>();

	static {
		freeDbConnections = new LinkedList<Connection>();
		System.out.println("faccio cose");
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	public DriverManagerConnectionPool() {
		freeDbConnections = new LinkedList<Connection>();
	}

	private static synchronized Connection createDBConnection() throws SQLException {
		
		String url="jdbc:sqlserver://atdepo.database.windows.net:1433;database=ProgettoTSW;user=atdepo@atdepo;password=Montefuscobastaruttare01!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		Connection newConnection = DriverManager.getConnection(url);
		newConnection.setAutoCommit(false);
		return newConnection;
	}


	public static synchronized Connection getConnection() throws SQLException {
		Connection connection;

		if (!freeDbConnections.isEmpty()) {
			connection = (Connection) freeDbConnections.get(0);
			freeDbConnections.remove(0);

			try {
				if (connection.isClosed())
					connection = getConnection();
			} catch (SQLException e) {
				connection.close();
				connection = getConnection();
			}
		} else {
			connection = createDBConnection();
		}

		return connection;
	}

	public static synchronized void releaseConnection(Connection connection) throws SQLException {
		if(connection != null) freeDbConnections.add(connection);
	}
}
