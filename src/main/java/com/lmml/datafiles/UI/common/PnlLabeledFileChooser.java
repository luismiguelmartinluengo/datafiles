package com.lmml.datafiles.UI.common;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PnlLabeledFileChooser extends JPanel implements ActionListener{

    private static final long serialVersionUID = 1L;
    
    PnlLabeledTextField ltfPath = new PnlLabeledTextField("Ruta:", "");
    JButton btnBrowse = new JButton("...");
    JFileChooser fileChooser = new JFileChooser();

    public String getPaht(){
        return ltfPath.getText();
    }//End getPath

    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.add(ltfPath, java.awt.BorderLayout.CENTER);
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
                ltfPath.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }//End if
        }//End if
    }//End actionPerformed
    
}//End PnlLabeledFileChooser

