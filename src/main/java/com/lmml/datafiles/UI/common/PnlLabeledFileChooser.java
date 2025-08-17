package com.lmml.datafiles.UI.common;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PnlLabeledFileChooser extends JPanel implements ActionListener{

    private static final long serialVersionUID = 1L;
    
    PnlLabeledTextField ltfPath = new PnlLabeledTextField("Ruta:", "");
    JButton btnBrowse = new JButton("...");
    JFileChooser fileChooser = new JFileChooser();
    ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();
    
    public void addActionListener(ActionListener _listener){
        listeners.add(_listener);
    }//End addActionListener

    public String getPath(){
        return ltfPath.getText();
    }//End getPath

    public void setPath(String _path){
        ltfPath.setText(_path);
    }//End setPath

    private void initComponents() {
        btnBrowse.addActionListener(this);
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
                e.setSource(this);
                for (ActionListener listener : listeners) {
                    listener.actionPerformed(e);
                }//End for
            }//End if
        }//End if
    }//End actionPerformed
    
}//End PnlLabeledFileChooser

