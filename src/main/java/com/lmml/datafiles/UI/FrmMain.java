package com.lmml.datafiles.UI;


import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.lmml.datafiles.UI.Sources.FileDescriptorAdmin;
import com.lmml.datafiles.UI.Sources.PnlFileDescriptorSelector;
import com.lmml.datafiles.UI.common.PnlArrayListAdmin;
import com.lmml.datafiles.UI.common.PnlLabeledTextField;

public class FrmMain extends JFrame {

	private static final long serialVersionUID = 1L;
	JTabbedPane tpPrincipal = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
	FileDescriptorAdmin fileDescriptorAdmin = new FileDescriptorAdmin();
	
	public FrmMain(){
		this.setTitle("Exploradores");
		this.setBounds(200, 200, 1000, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.add(tpPrincipal, BorderLayout.CENTER);


					ArrayList<String> testStringArray = new ArrayList<String>();
					
					testStringArray.add("Elemento 1");
					testStringArray.add("Elemento 2");
					testStringArray.add("Elemento 3");
					testStringArray.add("Elemento 4");
					testStringArray.add("Elemento 5");
					testStringArray.add("Elemento 6");
					testStringArray.add("Elemento 7");
					testStringArray.add("Elemento 8");
					testStringArray.add("Elemento 9");
					testStringArray.add("Elemento 10");
					testStringArray.add("Elemento 11");
					testStringArray.add("Elemento 12");
					

		tpPrincipal.add("Administración Descriptores de fichero", new PnlArrayListAdmin<String>(testStringArray, new PnlLabeledTextField("Nuevo Valor: ", "")));
		tpPrincipal.add("Explorador de árbol", new PnlFileDescriptorSelector(fileDescriptorAdmin));
		this.setVisible(true);//Esto siempre al final del constructor
	}//End constructor
	

}//End FrmMain