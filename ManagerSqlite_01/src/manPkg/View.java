package manPkg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.MenuBar;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class View extends JFrame{
	
	public JPanel borderLayoutPanel = new JPanel(); // Pannello che contiene tutti gli altri
	private JPanel comboBoxJPanel = new JPanel();
	private JPanel centralPanel = new JPanel();
	public JPanel sudPanel = new JPanel();
	
	private JTextArea textAreaQuery = new JTextArea(); // TextArea superiore per le query
	private JTextArea textAreaResponse = new JTextArea();
	private Font font = new Font(Font.SERIF, Font.PLAIN,  20);
	
	private JMenuBar menuBar = new JMenuBar(); // Da completare
	private JMenu menu = new JMenu("menu"); // idem
	private JMenuItem menuItem = new JMenuItem("A text-only menu item"); // idem
	
	private Border blackline = BorderFactory.createLineBorder(Color.black); // bordo per debug
	
	private JButton btnEsegui = new JButton("Esegui");
	
	JComboBox comboBox; // combo per selezionare il database su cui operare le successive query
	
	public View() { // Costruttore
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setTitle("Query Manager");
		this.setSize(1400, 900);
		this.setLocationRelativeTo(null); 
		setMenu();
        init();
        this.setVisible(true);  
	}// end Costruttore
	
	// Methods
	private void init() {
		borderLayoutPanel.setLayout(new BorderLayout());
		setCentralPanel();
		setSudPanel();
		setComboBoxPanel();
		this.add(borderLayoutPanel);
	}
	private void setMenu() {
		menu.add(menuItem);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}
	private void setCentralPanel() {
		
		textAreaQuery.setFont(font);
		
		textAreaQuery.setMargin(new Insets(10, 10, 10, 10));
		JScrollPane scrollAreaQuery = new JScrollPane(textAreaQuery);
		scrollAreaQuery.setPreferredSize(new Dimension(800, 400));
		centralPanel.setLayout(new BorderLayout());
		centralPanel.setSize(new Dimension(600, 400));
		centralPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
		JPanel scrollAreaQueryJPanel = new JPanel();
		scrollAreaQueryJPanel.setLayout(new FlowLayout());
		scrollAreaQueryJPanel.setPreferredSize(new Dimension(600, 200));
		//scrollAreaQuery.setBorder(new EmptyBorder(40, 40, 40, 40));
		scrollAreaQueryJPanel.add(scrollAreaQuery);
		JPanel btnJPanel = new JPanel();
		btnJPanel.setLayout(new FlowLayout());
		btnJPanel.setBorder(blackline);
		btnJPanel.setPreferredSize(new Dimension(200, 200));
		btnJPanel.add(btnEsegui);
		centralPanel.add(scrollAreaQueryJPanel, BorderLayout.CENTER);
		centralPanel.add(btnJPanel, BorderLayout.EAST);
		borderLayoutPanel.add(centralPanel, BorderLayout.CENTER);
		
	}
	
	private void setSudPanel() {
		textAreaResponse.setFont(font);
		textAreaResponse.setMargin(new Insets(10, 10, 10, 10));
		JScrollPane scrollAreaSud = new JScrollPane(textAreaResponse);
		scrollAreaSud.setPreferredSize(new Dimension(1000, 300));
		sudPanel.setLayout(new FlowLayout());
		sudPanel.setSize(new Dimension(600, 400));
		sudPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
		sudPanel.add(scrollAreaSud);
		borderLayoutPanel.add(sudPanel, BorderLayout.SOUTH);
	}
	
	private void setComboBoxPanel() {
		String[] s = leggiFile();
		comboBox = new JComboBox(s);
		comboBox.setSize(new Dimension(200, 200));
		
		comboBoxJPanel.setLayout(new FlowLayout());
		comboBoxJPanel.setBorder(blackline);
		comboBoxJPanel.setPreferredSize(new Dimension(200, 200));
		comboBoxJPanel.add(comboBox);
		borderLayoutPanel.add(comboBoxJPanel, BorderLayout.WEST);
	}
	// Getter
	public String getContenutoTextArea() {
		return textAreaQuery.getText();
	}
	
	public JButton getButtonEsegui() {
		return btnEsegui;
	}
	
	// Setter
	public void setTextArea(String str) {
		textAreaResponse.setText(str);
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
	
	// Aggiorna comboBox
	public void aggiornaComboBox() {
		comboBox.removeAllItems();
		String[] items = leggiFile();
		for(String s : items){
	        comboBox.addItem(s);
	    }
	}
	
}
