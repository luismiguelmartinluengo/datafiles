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
import java.awt.Desktop.Action;
import java.awt.GridBagConstraints;
import java.awt.Component;

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

    private class AdminObjectPanel extends JPanel implements ActionListener {
        private static final long serialVersionUID = 1L;

        private ObjectAdminUI objectAdminUI = null;
        private JButton btnAcceptChanges = new JButton("Accept Changes");
        private JButton btnCancelChanges = new JButton("Cancel Changes");
        private ArrayList<ActionListener> actionListeners = new ArrayList<ActionListener>();

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
        }//End constructor

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btnAcceptChanges)) {
                if (objectAdminUI.acceptChanges()) {
                    for (ActionListener actionListener : actionListeners) {
                        actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "AcceptChanges"));
                    }//End for
                } else {
                    JOptionPane.showMessageDialog(this, "No se han podido aceptar los cambios", "Error", JOptionPane.ERROR_MESSAGE);
                }//End nested if
            } else if (e.getSource().equals(btnCancelChanges)) {
                objectAdminUI.cancelChanges();
            } else {
                for (ActionListener actionListener : actionListeners) {
                    actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "CancelChanges"));
                }//End for
            }//End nested if
        }//End actionPerformed

    }//End class AdminObjectPanel

    private static final long serialVersionUID = 1L;

    private JPanel listPanel = new JPanel();
    private JScrollPane scrollInnerPanel;
    private ArrayList<G> objects = new ArrayList<G>();
    private ArrayList<JButton> addButtons = new ArrayList<JButton>();
    private ArrayList<JButton> removeButtons = new ArrayList<JButton>();    
    private ArrayList<JButton> editButtons = new ArrayList<JButton>();
    private ArrayList<JPanel> rowPanels = new ArrayList<JPanel>();
    private AdminObjectPanel adminObjectPanel = null;
    private G newObject = null;

    private JPanel getNewRowPanel(Object _object){
        RowPanel rowPanel = new RowPanel(_object, this);
        addButtons.add(rowPanel.getAddButton());
        removeButtons.add(rowPanel.getRemoveButton());
        editButtons.add(rowPanel.getEditButton());
        rowPanels.add(rowPanel);
        return rowPanel;
        //hay que poner el código para añadir el panel al innerPanel
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
                JPanel rowPanel = getNewRowPanel(null);
                listPanel.add(rowPanel);
            } else {
                for(Object object : objects) {
                    JPanel rowPanel = getNewRowPanel(object);
                    listPanel.add(rowPanel);
                }//End for
            }//End if
        JPanel innerPanel = new JPanel(new BorderLayout());
            innerPanel.setBackground(Color.GREEN);
            innerPanel.add(listPanel, BorderLayout.NORTH);
        scrollInnerPanel = new JScrollPane(innerPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
                showAdminObjectPanel();
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
            case "AcceptChanges":
                JOptionPane.showMessageDialog(this, "AcceptChanges button clicked");
                hideAdminObjectPanel();
                break;
            case "CancelChanges":
                JOptionPane.showMessageDialog(this, "CancelChanges button clicked");
                hideAdminObjectPanel();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Unknown action");
        }//End switch
    }//End 

}//End PnlArrayListAdmin
