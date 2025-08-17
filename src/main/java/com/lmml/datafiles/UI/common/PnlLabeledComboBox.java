package com.lmml.datafiles.UI.common;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PnlLabeledComboBox<G> extends PnlLabeledComponent implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JComboBox<G> cbxCombo = new JComboBox<G>();
    private Class<G> instantiatedType;
    private ArrayList<ActionListener> actionListeners = new ArrayList<>();

    public void addActionListener(ActionListener _listener) {
        actionListeners.add(_listener);
    }//End addActionListener
    
    @SuppressWarnings("unchecked")
    public G getSelectedItem() {
        if (cbxCombo.isEditable()) {
            Object value = cbxCombo.getEditor().getItem();
            if (instantiatedType.isInstance(value)) {
                return instantiatedType.cast(value);
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "El valor ingresado no es compatible con el tipo " + instantiatedType.getSimpleName(),
                    "Tipo incompatible",
                    JOptionPane.ERROR_MESSAGE);
                return null;
            }//End if
        } else {
            return (G) cbxCombo.getSelectedItem();
        }//End if
    }//End getSelectedItem

    public void setEditable(boolean _editable) {
        cbxCombo.setEditable(_editable);
    }//End setEditable

    public void setSelectedItem(G _value) {
        if (instantiatedType.isInstance(_value)) {
            cbxCombo.setSelectedItem(_value);
        } else {
            JOptionPane.showMessageDialog(
                this,
                "El valor ingresado no es compatible con el tipo " + instantiatedType.getSimpleName(),
                "Tipo incompatible",
                JOptionPane.ERROR_MESSAGE);
        }//End if
    }//End setSelectedItem

    public void setSelectedIndex(int _index) {
        if (_index < 0) {
            cbxCombo.setSelectedIndex(-1);
        }else if (_index < cbxCombo.getItemCount()) {
            cbxCombo.setSelectedIndex(_index);
        } else {
            JOptionPane.showMessageDialog(
                this,
                "El índice seleccionado no es válido",
                "Índice inválido",
                JOptionPane.ERROR_MESSAGE);
        }//End if
    }//End setSelectedIndex

    @SuppressWarnings("unchecked")
    public PnlLabeledComboBox(String _title, G[] _values) {
        super();
        this.setTitle(_title);
        for (G value : _values) {
            cbxCombo.addItem(value);    
        }
        cbxCombo.setBackground(Color.WHITE);
        cbxCombo.addActionListener(this);
        initComponents(cbxCombo);
        // Asignar el tipo usando el array
        this.instantiatedType = (Class<G>) _values.getClass().getComponentType();
    }//End constructor

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cbxCombo)) {
            e.setSource(this);
            for (ActionListener listener : actionListeners) {
                listener.actionPerformed(e);
            }//End for
        }//End if
    }//End actionPerformed

}//End class PnlLabeledComboBox
