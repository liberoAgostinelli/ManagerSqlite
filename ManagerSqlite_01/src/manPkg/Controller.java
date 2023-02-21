package manPkg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Controller {

	JButton btnEsegui = null;
	public Controller(View view, Model model) { // Costruttore
		btnEsegui = view.getButtonEsegui();
		btnEsegui.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String contenutoQuery = view.getContenutoTextArea();
				
				if(!contenutoQuery.isEmpty()) {
					
					// Create database
					if(contenutoQuery.contains("CREATE DATABASE")) {
						//System.out.println("CREATE DATABASE");
						//String str = contenutoQuery.replace(" ", "");
						String sottoString = contenutoQuery.replace("CREATE DATABASE ", "");
						//System.out.println(sottoString);
						String s = sottoString.replace(";", "");
						//System.out.println(s);
						if(s.contains(" ")) {
							view.setTextArea("Errore nella query!");
						}else {
							//System.out.println(s +" Database Creato");
							model.createDatabase(s + ".db");
							view.setTextArea("Database " + s +".db Creato");
						}
						
					}else {
						view.setTextArea("Errore nella query!");
					}
					
					if(contenutoQuery.contains("create database")) {
						String sottoString = contenutoQuery.replace("create database ", "");
						//System.out.println(sottoString);
						String s = sottoString.replace(";", "");
						//System.out.println(s);
						if(s.contains(" ")) {
							view.setTextArea("Errore nella query!");
						}else {
							//System.out.println(s +" Database Creato");
							model.createDatabase(s + ".db");
							view.setTextArea("Database " + s +".db Creato");
						}
					}else {
						view.setTextArea("Errore nella query!");
					}
					// end Create database
				}// end contenutoQuery.isEmpity
					
				
			}
		});
	} //End Costruttore
	
	// Methods
}
