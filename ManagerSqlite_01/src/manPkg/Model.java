package manPkg;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Model {

	private final String jdbcDriver = "jdbc:sqlite:temp.db";
	private Connection conn = null;
	private Statement stm = null;

	public Model() {


	}
	//Methods
	private void init() {
		try {
			conn = DriverManager.getConnection(jdbcDriver);
			System.out.println("DB Aperto");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(conn != null || stm != null)
				try {
					conn.close();
					//stm.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}


	public void createDatabase(String fileName) {

		String url = "jdbc:sqlite:database/" + fileName;

		try (Connection conn = DriverManager.getConnection(url)) {
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
				System.out.println("A new database has been created.");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// Leggi file
	private String[] leggiFile() {
		File folder = new File("/home/molly/git/ManagerSqlite/ManagerSqlite_01/database");
		List<String> l = new ArrayList<>();
		for (File file : folder.listFiles()) {
			if (!file.isDirectory()) {
				System.out.println(file.getName());
				l.add(file.getName());
			}
		}
		String[] arr = new String[l.size()];
		for(int i = 0; i<l.size(); i++) {
			arr[i] = l.get(i);
		}
		return arr;
	}	
}
