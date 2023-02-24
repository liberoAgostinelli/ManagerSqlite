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

	private String isolaNomeDataBase(String contenutoQuery, String formato) {
		
		System.out.println(contenutoQuery.replace(" ","").toUpperCase());
		System.out.println(formato.replace(" ", "").toUpperCase());
		if(contenutoQuery.replace(" ","").toUpperCase().equals(formato.replace(" ", "").toUpperCase()) ) {
			
			return "";
		}
		System.out.println("inizio debug");
		System.out.println("0-" + contenutoQuery);
		String sottoString = contenutoQuery.replace(formato + " ", " " + formato + " ");
		System.out.println("1-" + sottoString);
		System.out.println("2-" +sottoString.indexOf(" " + formato + " "));
		String c = sottoString.substring(0, sottoString.indexOf(" " + formato + " "));
		System.out.println("3-" +c);
		sottoString = sottoString.replace(c, "");
		System.out.println("4-" +sottoString);
		sottoString = sottoString.replace(formato, "");
		System.out.println("5-" +sottoString);
		sottoString = sottoString.replace(" ", "");
		System.out.println("6-" +sottoString);
		sottoString = sottoString.replace(";", "");
		System.out.println("7-" +sottoString);

		return sottoString;
	}

}// end classe
