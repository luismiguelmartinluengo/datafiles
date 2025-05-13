package com.lmml.datafiles.UI.Sources;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import com.lmml.datafiles.Explorer.FileDescriptor;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PnlFileDescriptorSelector extends JPanel implements ChangeListener {

    private static final long serialVersionUID = 1L;
    private FileDescriptorAdmin fileDescriptorAdmin = null;
    JLabel LblTitle = new JLabel("Descriptores:");
    JComboBox<FileDescriptor> CmbFileDescriptor = new JComboBox<FileDescriptor>();
    FileDescriptor selectedFileDescriptor = null;

    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.add(LblTitle, BorderLayout.WEST);
        this.add(CmbFileDescriptor, BorderLayout.CENTER);
        CmbFileDescriptor.setEditable(false);
        CmbFileDescriptor.setSelectedIndex(-1); 
    }//End initComponents

    private void loadCmbFileDescriptor() {
        CmbFileDescriptor.removeAllItems();
        DefaultComboBoxModel<FileDescriptor> model = new DefaultComboBoxModel<>(fileDescriptorAdmin.getFileDescriptors());
        CmbFileDescriptor.setModel(model);
        if (selectedFileDescriptor != null) {
            if (model.getIndexOf(selectedFileDescriptor) != -1) {
                CmbFileDescriptor.setSelectedItem(selectedFileDescriptor);
            } else {
                selectedFileDescriptor = null;
                CmbFileDescriptor.setSelectedIndex(-1);
            }//End if
        } else {
            CmbFileDescriptor.setSelectedIndex(-1);
        }//End if
    }//End loadCmbFileDescriptor

    public PnlFileDescriptorSelector(FileDescriptorAdmin _fileDescriptorAdmin) {
        super();
        initComponents();
        fileDescriptorAdmin = _fileDescriptorAdmin;
        loadCmbFileDescriptor();
        fileDescriptorAdmin.addChangeListener(this);
    }//End constructor

    @Override
    public void stateChanged(ChangeEvent e) {
        selectedFileDescriptor = (FileDescriptor) CmbFileDescriptor.getSelectedItem();
    }//End constructor

}//PnlFileDescriptorSelector
