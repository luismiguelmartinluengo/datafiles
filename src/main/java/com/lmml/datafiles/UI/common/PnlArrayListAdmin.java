package com.lmml.datafiles.UI.common;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;

public class PnlArrayListAdmin<G> extends JPanel implements ActionListener{

    private class TextField extends JTextField {
        private static final long serialVersionUID = 1L;

        public TextField(String _text) {
            super();
            this.setText(_text);
            this.setEditable(false);
            this.setOpaque(false);
            this.setBorder(null);
        }//End constructor

    }//End class TextField

    private class InnerButton extends JButton {
        private static final long serialVersionUID = 1L;

        public InnerButton() {
            super();
            this.setBorderPainted(false);
            this.setFocusPainted(false);
            this.setContentAreaFilled(false);
        }//End constructor

    }//End class GenericButton

    private class AddButton extends InnerButton {
        private static final long serialVersionUID = 1L;

        public AddButton() {
            super();
            this.setText("Add");
            this.setActionCommand("Add");
        }//End constructor

    }//End class AddButton

    private class RemoveButton extends InnerButton {
        private static final long serialVersionUID = 1L;

        public RemoveButton() {
            super();
            this.setText("Remove");
            this.setActionCommand("Remove");
        }//End constructor

    }//End class RemoveButton

    private class EditButton extends InnerButton {
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

        public void showValue(String _newValue){
            textField.setText(_newValue);
            this.validate();
            this.repaint();
        }//End showValue

        private void initComponents() {
            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
            buttonsPanel.add(addButton);
            buttonsPanel.add(removeButton);
            buttonsPanel.add(editButton);
            buttonsPanel.setOpaque(false);
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
            this.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
            this.setOpaque(false);
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

    private class AdminObjectPanel extends JPanel implements ActionListener {
        private static final long serialVersionUID = 1L;

        private ObjectAdminUI objectAdminUI = null;
        private JButton btnAcceptChanges = new JButton("Accept Changes");
        private JButton btnCancelChanges = new JButton("Cancel Changes");
        private ArrayList<ActionListener> actionListeners = new ArrayList<ActionListener>();

        public void setUIForNew(){
            objectAdminUI.clearUI();
            btnAcceptChanges.setText("Add");
            btnCancelChanges.setText("Cancel");
        }//End setUIForNew

        public void setUIForEdit(Object _administratedObject){
            objectAdminUI.setObject(_administratedObject);
            btnAcceptChanges.setText("Accept Changes");
            btnCancelChanges.setText("Cancel Changes");
        }//End setUIForEdit

        public Object getNewObject(){
            return objectAdminUI.getNew();
        }//End getNewObject

        public Object getModifiedObject(Object _administratedObject){
            return objectAdminUI.acceptChanges(_administratedObject);
        }//End getModifiedObject

        public AdminObjectPanel(ObjectAdminUI _objectAdminUI, ActionListener _actionListener) {
            super();
            objectAdminUI = _objectAdminUI;
            actionListeners.add(_actionListener);
            btnAcceptChanges.addActionListener(this);
            btnCancelChanges.addActionListener(this);   
            JPanel buttonsPanel = new JPanel(new BorderLayout());
            buttonsPanel.add(btnAcceptChanges, BorderLayout.EAST);
            buttonsPanel.add(btnCancelChanges, BorderLayout.WEST);
            this.setLayout(new BorderLayout());
            this.add((Component) objectAdminUI, BorderLayout.CENTER);
            this.add(buttonsPanel, BorderLayout.SOUTH);
            this.setOpaque(false);
        }//End constructor

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btnAcceptChanges)){
                for (ActionListener actionListener : actionListeners) {
                    actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "AcceptChanges"));
                }//End for
            }else if (e.getSource().equals(btnCancelChanges)){
                for (ActionListener actionListener : actionListeners) {
                    actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "CancelChanges"));
                }//End for
            }//End if
        }//End actionPerformed

    }//End class AdminObjectPanel

    private static final long serialVersionUID = 1L;

    private JPanel listPanel = new JPanel();
    private JScrollPane scrollInnerPanel;
    private ArrayList<G> objects = new ArrayList<G>();
    private ArrayList<JButton> addButtons = new ArrayList<JButton>();
    private ArrayList<JButton> removeButtons = new ArrayList<JButton>();    
    private ArrayList<JButton> editButtons = new ArrayList<JButton>();
    private ArrayList<RowPanel> rowPanels = new ArrayList<RowPanel>();
    private AdminObjectPanel adminObjectPanel = null;
    private int indexForNewRow = -1;
    private int indexForEdit = -1;    

    private JPanel getNewRowPanel(Object _object, int indexForNewRow){
        RowPanel rowPanel = new RowPanel(_object, this);
        addButtons.add(indexForNewRow, rowPanel.getAddButton());
        removeButtons.add(indexForNewRow, rowPanel.getRemoveButton());
        editButtons.add(indexForNewRow, rowPanel.getEditButton());
        rowPanels.add(indexForNewRow, rowPanel);
        return rowPanel;
    }//End getNewRowPanel

    private void showAdminObjectPanel() {
        if (adminObjectPanel != null) {
            this.remove(scrollInnerPanel);
            this.add(adminObjectPanel, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "No se ha definido el panel de administración de objetos", "Error", JOptionPane.ERROR_MESSAGE);
        }//End nested if
    }//End showAdminObjectPanel

    private void hideAdminObjectPanel() {
        if (adminObjectPanel != null) {
            this.remove(adminObjectPanel);
            this.add(scrollInnerPanel, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "No se ha definido el panel de administración de objetos", "Error", JOptionPane.ERROR_MESSAGE);
        }//End nested if
    }//End hideAdminObjectPanel

    private void initComponents() {
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
            if (objects.size()==0){
                JPanel rowPanel = getNewRowPanel(null, 0);
                listPanel.add(rowPanel);
            } else {
                for(Object object : objects) {
                    JPanel rowPanel = getNewRowPanel(object, rowPanels.size());
                    listPanel.add(rowPanel);
                }//End for
            }//End if
            listPanel.setOpaque(false);
        JPanel innerPanel = new JPanel(new BorderLayout());
            innerPanel.setBackground(Color.white);
            innerPanel.add(listPanel, BorderLayout.NORTH);
        scrollInnerPanel = new JScrollPane(innerPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setLayout(new BorderLayout());
           this.add(scrollInnerPanel, java.awt.BorderLayout.CENTER);

    }//End initComponents

    public PnlArrayListAdmin(ArrayList<G> _objects, ObjectAdminUI _objectAdminUI) {
        super();
        objects = _objects;
        adminObjectPanel = new AdminObjectPanel(_objectAdminUI, this);
        initComponents();
    }//End constructor

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Add":
                if (objects.size() == 0){
                    indexForNewRow = 0;
                } else {
                    indexForNewRow = addButtons.indexOf(e.getSource()) + 1;
                }//End if
                adminObjectPanel.setUIForNew();
                showAdminObjectPanel();
                break;
            case "Remove":
                int indexForRemove = removeButtons.indexOf(e.getSource());
                if(JOptionPane.showConfirmDialog(this, "Se va a eliminar el elemento %s. ¿Estás seguro?".formatted(objects.get(indexForRemove).toString()), "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
                    listPanel.remove(indexForRemove);
                    addButtons.remove(indexForRemove);
                    removeButtons.remove(indexForRemove);
                    editButtons.remove(indexForRemove);
                    rowPanels.remove(indexForRemove);
                    objects.remove(indexForRemove);
                    if (objects.size() == 0){
                        JPanel rowPanel = getNewRowPanel(null, 0);
                        listPanel.add(rowPanel);
                    }//End if
                } else {
                    JOptionPane.showMessageDialog(this, "Borrado cancelado");
                }//End nested if
                break;
            case "Edit":
                indexForEdit = editButtons.indexOf(e.getSource());
                adminObjectPanel.setUIForEdit(objects.get(indexForEdit));
                showAdminObjectPanel();
                break;
            case "AcceptChanges":
                if (indexForNewRow > -1) {
                    if (indexForNewRow == 0){
                        listPanel.removeAll();
                        addButtons.clear();
                        removeButtons.clear();
                        editButtons.clear();
                        rowPanels.clear();
                    }//End if
                    @SuppressWarnings("unchecked")
                    G newObject = (G) adminObjectPanel.getNewObject();
                    objects.add(indexForNewRow, newObject);
                    JPanel rowPanel = getNewRowPanel(newObject, indexForNewRow);
                    listPanel.add(rowPanel, indexForNewRow);
                    listPanel.revalidate();
                    listPanel.repaint();    
                } else {
                    if (indexForEdit > -1) {
                        @SuppressWarnings("unchecked")
                        G modifiedObject = (G) adminObjectPanel.getModifiedObject(objects.get(indexForEdit));
                        objects.set(indexForEdit, modifiedObject);
                        rowPanels.get(indexForEdit).showValue(modifiedObject.toString());
                    }//End if
                }//End nested if
                hideAdminObjectPanel();
                indexForNewRow = -1;
                indexForEdit = -1;
                break;
            case "CancelChanges":
                indexForNewRow = -1;
                indexForEdit = -1;
                hideAdminObjectPanel();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Unknown action");
        }//End switch
    }//End 

}//End PnlArrayListAdmin
