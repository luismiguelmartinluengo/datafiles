package com.lmml.datafiles.UI.common;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class PnlLabeledComboBox<G> extends PnlLabeledComponent {

    private static final long serialVersionUID = 1L;

    private JComboBox<G> cbxCombo = new JComboBox<G>();
    private Class<G> instantiatedType;
    
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
                    JOptionPane.ERROR_MESSAGE
                );
                return null;
            }//End if
        } else {
            return (G) cbxCombo.getSelectedItem();
        }//End if
    }//End getSelectedItem

    public void setEditable(boolean _editable) {
        cbxCombo.setEditable(_editable);
    }//End setEditable



    @SuppressWarnings("unchecked")
    public PnlLabeledComboBox(String _title, G[] _values) {
        super();
        this.setTitle(_title);
        for (G value : _values) {
            cbxCombo.addItem(value);
        }
        cbxCombo.setEditable(false);
        initComponents(cbxCombo);
        // Asignar el tipo usando el array
        this.instantiatedType = (Class<G>) _values.getClass().getComponentType();
    }//End constructor

}//End class PnlLabeledComboBox
