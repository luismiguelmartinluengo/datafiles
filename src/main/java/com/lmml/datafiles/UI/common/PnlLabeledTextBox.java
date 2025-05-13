package com.lmml.datafiles.UI.common;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class PnlLabeledTextBox extends JPanel{

    private static final long serialVersionUID = 1L;
    
    private JLabel lblTitle = new JLabel();
    private JTextField txtValue = new JTextField();

    public void addDocumentListener(DocumentListener _listener){
        txtValue.getDocument().addDocumentListener(_listener);
    }//End addDocumentListener

    public void removeDocumentListener(DocumentListener _listener){
        txtValue.getDocument().removeDocumentListener(_listener);
    }//End removeDocumentListener

    public String getValue(){
        return txtValue.getText();
    }//End getValue

    public void setValue(String _value){
        txtValue.setText(_value);
    }//End setValue

    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.add(lblTitle, BorderLayout.WEST);
        this.add(txtValue, BorderLayout.CENTER);
    }//End initComponents
    
    public PnlLabeledTextBox(String _title, String _value){
        super();
        lblTitle.setText(_title);
        txtValue.setText(_value);
        initComponents();
    }//End constructor


}//End PnlLabeledTextBox
