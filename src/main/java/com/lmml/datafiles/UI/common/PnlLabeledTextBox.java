package com.lmml.datafiles.UI.common;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;

public class PnlLabeledTextBox extends JPanel implements ObjectAdminUI{

    private static final long serialVersionUID = 1L;
    
    private JLabel lblTitle = new JLabel();
    private JTextField txtValue = new JTextField();
    private String administratedObject = null;

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

    @Override
    public void setObject(Object _object) {
        if (_object instanceof String) {
            administratedObject = (String) _object;
            setValue(administratedObject);
        } else {
            throw new IllegalArgumentException("El objeto pasado no es de tipo String.");
        }//End nested if
    }//End setObject

    @Override
    public Boolean acceptChanges() {
        if (administratedObject == null || administratedObject.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encuentra la referencia al objeto administrado", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            administratedObject = getValue();
            return true;
        }//End nested if
    }//End acceptChanges

    @Override
    public Boolean cancelChanges() {
        if (administratedObject == null || administratedObject.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encuentra la referencia al objeto administrado", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            this.setValue(administratedObject);
            return true;
        }//End nested if
    }


}//End PnlLabeledTextBox
