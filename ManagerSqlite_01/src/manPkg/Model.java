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

	private final String jdbcDriver = "jdbc:sqlite:database/";
	private Connection conn = null;
	private Statement stm = null;

	public Model() {


	}
	//Methods

	public void createDatabase(String fileName) {

		String url = jdbcDriver + fileName;

		try (Connection conn = DriverManager.getConnection(url)) {
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
				System.out.println("A new database has been created.");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}// end create database 
	
	public String createTable(String query, String db_selezionato) {
		String esito = "Tabella creata";
		String cartella = db_selezionato.replace(".db", "");
		System.out.println("Nome cartella: " + cartella);
		try (Connection conn = DriverManager.getConnection(jdbcDriver + cartella + "/" +db_selezionato);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            esito = e.getMessage();
        }
		return esito;
	}// end crea tabelle
	
	public boolean creaDir(String dir) {
		boolean esitoDir = (new File(dir).mkdirs());
		return esitoDir;
	}

}
