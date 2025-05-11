package com.lmml.datafiles.UI;


import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;

public class FrmMain extends JFrame {

	private static final long serialVersionUID = 1L;
	JTabbedPane tpPrincipal = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
	
	public FrmMain(){
		this.setTitle("Exploradores");
		this.setBounds(200, 200, 1000, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.add(tpPrincipal, BorderLayout.CENTER);
		tpPrincipal.add("Administración Descriptores de fichero", new JLabel("administración"));
		tpPrincipal.add("Explorador de árbol", new JLabel("explorador"));
		this.setVisible(true);//Esto siempre al final del constructor
	}//End constructor
	

}//End FrmMain