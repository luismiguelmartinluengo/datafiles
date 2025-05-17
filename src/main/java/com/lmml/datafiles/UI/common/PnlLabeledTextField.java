package com.lmml.datafiles.UI.common;

import javax.swing.JTextField;
import javax.swing.event.DocumentListener;


public class PnlLabeledTextField extends PnlLabeledComponent implements ObjectAdminUI{

    private static final long serialVersionUID = 1L;
    
    private JTextField tflText = new JTextField();

    public void addDocumentListener(DocumentListener _listener){
        tflText.getDocument().addDocumentListener(_listener);
    }//End addDocumentListener

    public void removeDocumentListener(DocumentListener _listener){
        tflText.getDocument().removeDocumentListener(_listener);
    }//End removeDocumentListener

    public String getText(){
        return tflText.getText();
    }//End getText

    public void setText(String _text){
        tflText.setText(_text);
    }//End setText
    
    public PnlLabeledTextField(String _title, String _value){
        super();
        this.setTitle(_title);
        initComponents(tflText);
    }//End constructor

    @Override
    public void setObject(Object _administratedObject) {
        if (_administratedObject instanceof String) {
            String administratedString = (String) _administratedObject;
            tflText.setText(administratedString);
        } else {
            throw new IllegalArgumentException("El objeto pasado para administración no es de tipo String.");
        }//End nested if
    }//End setObject

    @Override
    public Object getNew(){
        return new String(tflText.getText());
    }//End getNew

    @Override
    public Object acceptChanges(Object _administratedObject) {
        if (_administratedObject instanceof String) {
            String administratedString = (String) _administratedObject;
            administratedString = tflText.getText();
            return administratedString;
        } else {
            throw new IllegalArgumentException("El objeto pasado para administración no es de tipo String.");
        }//End nested if
    }//End acceptChanges

    @Override
    public void clearUI() {
        tflText.setText("");
    }//End clear


}//End PnlLabeledTextBox
