package com.lmml.datafiles.UI.common;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField; 
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class PnlArrayListAdmin<G> extends JPanel implements ActionListener{

    private class TextField extends JTextField {
        private static final long serialVersionUID = 1L;

        public TextField(String _text) {
            super();
            this.setText(_text);
            this.setEditable(false);
        }//End constructor

    }//End class TextField

    private class AddButton extends JButton {
        private static final long serialVersionUID = 1L;

        public AddButton() {
            super();
            this.setText("Add");
            this.setActionCommand("Add");
        }//End constructor

    }//End class AddButton

    private class RemoveButton extends JButton {
        private static final long serialVersionUID = 1L;

        public RemoveButton() {
            super();
            this.setText("Remove");
            this.setActionCommand("Remove");
        }//End constructor

    }//End class RemoveButton

    private class EditButton extends JButton {
        private static final long serialVersionUID = 1L;

        public EditButton() {
            super();
            this.setText("Edit");
            this.setActionCommand("Edit");
        }//End constructor

    }//End class EditButton

    private class RowPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        private AddButton addButton = new AddButton();
        private RemoveButton removeButton = new RemoveButton();
        private EditButton editButton = new EditButton();
        private TextField textField = new TextField(""); 

        public AddButton getAddButton() {
            return addButton;
        }//End getAddButton

        public RemoveButton getRemoveButton() {
            return removeButton;
        }//End getRemoveButton

        public EditButton getEditButton() {
            return editButton;
        }//End getEditButton

        private void initComponents() {
            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
            buttonsPanel.add(addButton);
            buttonsPanel.add(removeButton);
            buttonsPanel.add(editButton);
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            //textField
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1.0; // Expande en ancho
                gbc.weighty = 0.0; // No se expande en altura
                gbc.fill = GridBagConstraints.HORIZONTAL; // Solo expansión horizontal
                gbc.anchor = GridBagConstraints.LINE_START; // Mantener alineado a la derecha
                this.add(textField, gbc);
            //buttonsPanel
                gbc.gridx = 1;
                gbc.gridy = 0;
                gbc.weightx = 0.0; // No se expande en ancho
                gbc.weighty = 0.0; // No se expande en altura
                gbc.fill = GridBagConstraints.NONE; // Mantener tamaño original
                gbc.anchor = GridBagConstraints.LINE_END; // Alineado a la derecha
                this.add(buttonsPanel, gbc);
        }//End initComponents

        public RowPanel(Object _object, ActionListener _Listener) {
            super();
            addButton.addActionListener(_Listener);
            removeButton.addActionListener(_Listener);
            editButton.addActionListener(_Listener);
            initComponents();
            if (_object != null) {
                textField.setText(_object.toString());
            } else {
                textField.setVisible(false);
                removeButton.setVisible(false);
                editButton.setVisible(false);
            }//End if
        }//End constructor

    }//End class RowPanel

    private static final long serialVersionUID = 1L;

    JPanel listPanel = new JPanel();
    ArrayList<G> objects = new ArrayList<G>();
    ArrayList<JButton> addButtons = new ArrayList<JButton>();
    ArrayList<JButton> removeButtons = new ArrayList<JButton>();    
    ArrayList<JButton> editButtons = new ArrayList<JButton>();
    ArrayList<JPanel> rowPanels = new ArrayList<JPanel>();

    private JPanel getNewRowPanel(Object _object){
        RowPanel rowPanel = new RowPanel(_object, this);
        addButtons.add(rowPanel.getAddButton());
        removeButtons.add(rowPanel.getRemoveButton());
        editButtons.add(rowPanel.getEditButton());
        rowPanels.add(rowPanel);
        return rowPanel;
        //hay que poner el código para añadir el panel al innerPanel
    }//End getNewRowPanel

    private void initComponents() {
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        JPanel innerPanel = new JPanel(new BorderLayout());
        innerPanel.setBackground(Color.GREEN);
        innerPanel.add(listPanel, BorderLayout.NORTH);
        if (objects.size()==0){
            JPanel rowPanel = getNewRowPanel(null);
            listPanel.add(rowPanel);
        } else {
            for(Object object : objects) {
                JPanel rowPanel = getNewRowPanel(object);
                listPanel.add(rowPanel);
            }//End for
        }//End if
        JScrollPane scrollInnerPanel = new JScrollPane(innerPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setLayout(new BorderLayout());
        this.add(scrollInnerPanel, java.awt.BorderLayout.CENTER);
    }//End initComponents

    public PnlArrayListAdmin(ArrayList<G> _objects) {
        super();
        objects = _objects;
        initComponents();
    }//End constructor

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Add":
                System.out.println("Add button clicked");
                int index = addButtons.indexOf(e.getSource());
                String texto = objects.get(index).toString();
                JOptionPane.showMessageDialog(this, "Add button clicked para elemento %s".formatted(texto));
                break;
            case "Remove":
                JOptionPane.showMessageDialog(this, "Remove button clicked");
                break;
            case "Edit":
                JOptionPane.showMessageDialog(this, "Edit button clicked");
                break;
            default:
                JOptionPane.showMessageDialog(this, "Unknown action");
        }//End switch
    }//End 

}//End PnlArrayListAdmin
