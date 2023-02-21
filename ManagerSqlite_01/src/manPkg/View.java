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

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
	
	public JPanel borderLayoutPanel = new JPanel();
	private JPanel centralPanel = new JPanel();
	public JPanel sudPanel = new JPanel();
	
	private JTextArea textAreaQuery = new JTextArea();
	private JTextArea textAreaResponse = new JTextArea();
	private Font font = new Font(Font.SERIF, Font.PLAIN,  20);
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu = new JMenu("menu");
	private JMenuItem menuItem = new JMenuItem("A text-only menu item");
	
	private Border blackline = BorderFactory.createLineBorder(Color.black);
	
	private JButton btnEsegui = new JButton("Esegui");
	
	public View() { // Costruttore
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setTitle("Query Manager");
		this.setSize(1200, 900);
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
		JScrollPane scrollArea = new JScrollPane(textAreaQuery);
		scrollArea.setPreferredSize(new Dimension(800, 400));
		centralPanel.setLayout(new BorderLayout());
		centralPanel.setSize(new Dimension(600, 400));
		//centralPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		//p.setBorder(blackline);
		p.setPreferredSize(new Dimension(400, 200));
		p.add(scrollArea);
		JPanel b = new JPanel();
		b.setLayout(new FlowLayout());
		b.setBorder(blackline);
		b.setPreferredSize(new Dimension(400, 100));
		b.add(btnEsegui);
		centralPanel.add(p, BorderLayout.CENTER);
		centralPanel.add(b, BorderLayout.SOUTH);
		borderLayoutPanel.add(centralPanel, BorderLayout.CENTER);
		
	}
	
	private void setSudPanel() {
		textAreaResponse.setFont(font);
		textAreaResponse.setMargin(new Insets(10, 10, 10, 10));
		JScrollPane scrollAreaSud = new JScrollPane(textAreaResponse);
		scrollAreaSud.setPreferredSize(new Dimension(800, 200));
		sudPanel.setLayout(new FlowLayout());
		sudPanel.setSize(new Dimension(600, 400));
		sudPanel.setBorder(new EmptyBorder(40, 40, 40, 40));
		sudPanel.add(scrollAreaSud);
		borderLayoutPanel.add(sudPanel, BorderLayout.SOUTH);
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
	
}
