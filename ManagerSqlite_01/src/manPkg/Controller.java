package manPkg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Controller {

	JButton btnEsegui = null;
	View view; Model model;
	public Controller(View view, Model model) { // Costruttore
		this.view = view; this.model = model;

		listenersBtnEsegui();

	} //End Costruttore

	// Methods

	private void listenersBtnEsegui() {
		btnEsegui = view.getButtonEsegui();
		btnEsegui.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String contenutoQuery = view.getContenutoTextArea();
				verificaComando(contenutoQuery);
			}
		});
	}

	private void verificaComando(String contenutoQuery) {

		if(!contenutoQuery.isEmpty()) {

			// Create database
			if(contenutoQuery.contains("CREATE DATABASE")) {
				String sottoString = isolaNomeDataBase(contenutoQuery, "CREATE DATABASE");

				if(sottoString.contains(" ") || sottoString == "" || sottoString == " ") {
					view.setTextArea("Errore nella query!");
				}else {
					String path = "/home/molly/git/ManagerSqlite/ManagerSqlite_01/database/";
					if(model.creaDir(path+sottoString)) {
						System.out.println("Ho creato: " + sottoString);
						model.createDatabase(sottoString + "/" + sottoString + ".db");
						view.aggiornaComboBox();
						view.setTextArea("Database " + sottoString +".db Creato");
					}else {
						System.out.println("Impossibile creare: " + sottoString);
					}
					
				}
			}else 

			if(contenutoQuery.contains("create database")) {
				String sottoString = isolaNomeDataBase(contenutoQuery, "create database");

				if(sottoString.contains(" ") || sottoString == "" || sottoString == " ") {
					view.setTextArea("Errore nella query!");
				}else {
					String path = "/home/molly/git/ManagerSqlite/ManagerSqlite_01/database/";
					if(model.creaDir(path+sottoString)) {
						System.out.println("Ho creato: " + sottoString);
						model.createDatabase(sottoString + "/" + sottoString + ".db");
						view.aggiornaComboBox();
						view.setTextArea("Database " + sottoString +".db Creato");
					}else {
						System.out.println("Impossibile creare: " + sottoString);
					}
				}
			}else {
				//view.setTextArea("Errore nella query!");
			}// end Create database
			
			// create table
			if(contenutoQuery.contains("CREATE TABLE") || contenutoQuery.contains("create table")) {
				String esito = "";
				esito = model.createTable(contenutoQuery, view.getDB_selezionato());
				view.setTextArea(esito);
				System.out.println("Query: " +contenutoQuery+ "DB: " + view.getDB_selezionato());
			}
			
		}// end contenutoQuery.isEmpity
	}// end verificaComando

	/*
	 * IsolaNomeDataBase è una funzione che isola il nome del database dalla stringa
	 * immessa dall'utente.
	 * Funzione da migliorare usando regex 
	 */
	private String isolaNomeDataBase(String contenutoQuery, String formato) {
	
		/*
		 *  if che controlla se e stato immesso il nome della tabella oppure
		 *  solo create database. Poi nella funzione verifica comando verifica
		 *  se la stringa sia vuota o meno
		 */
		if(contenutoQuery.replace(" ","").toUpperCase().equals(formato.replace(" ", "").toUpperCase()) ) {
			
			return "";
		}
		System.out.println("inizio debug");
		System.out.println("0-" + contenutoQuery);
		// Aggiunta spazio prima dei create database per pulire caratteri scritti prima
		String sottoString = contenutoQuery.replace(formato + " ", " " + formato + " ");
		System.out.println("1-" + sottoString);
		System.out.println("2-" +sottoString.indexOf(" " + formato + " "));
		// Isola eventuali caratteri scritti prima di create database
		String c = sottoString.substring(0, sottoString.indexOf(" " + formato + " "));
		System.out.println("3-" +c+"-");
		// Elimina eventuali caratteri scritti prima di create table
		sottoString = sottoString.replace(c, "");
		System.out.println("4-" +sottoString);
		// Elimina create database
		sottoString = sottoString.replace(formato, "");
		System.out.println("5-" +sottoString);
		// Si eliminano gli spazi
		sottoString = sottoString.replace(" ", "");
		System.out.println("6-" +sottoString);
		// Si elimina il punto e virgola
		sottoString = sottoString.replace(";", "");
		System.out.println("7-" +sottoString);

		return sottoString;
	}

}// end classe
