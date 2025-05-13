package com.lmml.datafiles.UI.common;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PnlLabeledFileChooser extends JPanel implements ActionListener{

    private static final long serialVersionUID = 1L;
    
    PnlLabeledTextBox ltxPath = new PnlLabeledTextBox("Ruta:", "");
    JButton btnBrowse = new JButton("...");
    JFileChooser fileChooser = new JFileChooser();

    public String getPaht(){
        return ltxPath.getValue();
    }//End getPath

    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.add(ltxPath, java.awt.BorderLayout.CENTER);
        this.add(btnBrowse, java.awt.BorderLayout.EAST);
    }//End initComponents

    public PnlLabeledFileChooser(){
        super();
        initComponents();
    }//End constructor

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnBrowse)) {
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                ltxPath.setValue(fileChooser.getSelectedFile().getAbsolutePath());
            }//End if
        }//End if
    }//End actionPerformed
    
}//End PnlLabeledFileChooser

