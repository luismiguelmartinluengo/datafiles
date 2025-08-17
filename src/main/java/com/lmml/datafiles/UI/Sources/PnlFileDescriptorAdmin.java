package com.lmml.datafiles.UI.Sources;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import com.lmml.datafiles.UI.common.ObjectAdminUI;
import com.lmml.datafiles.UI.common.PnlFilePreview;
import com.lmml.datafiles.UI.common.PnlLabeledComboBox;
import com.lmml.datafiles.UI.common.PnlLabeledFileChooser;
import com.lmml.datafiles.UI.common.PnlLabeledTextField;
import com.lmml.datafiles.Explorer.FileDescriptor;
import com.lmml.datafiles.Util.MaskedValues;

public class PnlFileDescriptorAdmin extends JPanel implements ObjectAdminUI<FileDescriptor>, ActionListener{

    private static final long serialVersionUID = 1L;

    private PnlLabeledTextField ltfName = new PnlLabeledTextField("Nombre: ", "Nombre del descriptor");
    private PnlLabeledFileChooser ltfFileChooser = new PnlLabeledFileChooser();
    private PnlFilePreview fprPreview = new PnlFilePreview();
    private FileDescriptor administratedFileDescriptor;
    private MaskedValues<String, Character> maskedSeparators = new MaskedValues<>(new String[]{";", ",", "|", "(tab)"},
                                                                            new Character[]{';', ',', '|', '\t'});
    private PnlLabeledComboBox<String> lcbSeparator = new PnlLabeledComboBox<String>("Separador de campos: ", maskedSeparators.getMasks());
    private PnlLabeledComboBox<Character> lcbDelimiter = new PnlLabeledComboBox<Character>("Delimitador de campos: ", new Character[]{'"', '\''});
    private PnlLabeledTextField ltfSkipLines = new PnlLabeledTextField("Saltar lineas: ", "0");

    private void clear(){
        ltfName.setText("");
        ltfFileChooser.setPath("");
        fprPreview.clear();
        lcbSeparator.setSelectedIndex(-1);
        lcbDelimiter.setSelectedIndex(-1);
        ltfSkipLines.setText("0");
        this.revalidate();
        this.repaint(); 
    }//End clear

    public void setFileDescriptor(FileDescriptor _fileDescriptor){
        administratedFileDescriptor = _fileDescriptor;
        if (administratedFileDescriptor == null) {
            clear();
        } else {
            ltfName.setText(_fileDescriptor.getName());
            ltfFileChooser.setPath(_fileDescriptor.getPath());
            fprPreview.setFilePath(new File(_fileDescriptor.getPath()));
            lcbSeparator.setSelectedItem(maskedSeparators.getMask(_fileDescriptor.getFieldsSeparator()));
            lcbDelimiter.setSelectedItem(_fileDescriptor.getFieldsDelimiter());
            ltfSkipLines.setText(_fileDescriptor.getSkipLines().toString());
        }//End if
    }//End setFileDescriptor

    private void initComponents(){
        ltfFileChooser.addActionListener(this);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(ltfName);
        this.add(ltfFileChooser);
        this.add(fprPreview);
        this.add(lcbSeparator);
        this.add(lcbDelimiter);
        this.add(ltfSkipLines);
    }//End initComponents

    public PnlFileDescriptorAdmin(){
        super();
        initComponents();
    }//End constructor

    @Override
    public void clearUI() {
        this.clear();
    }//End clearUI

    @Override
    public void setObject(FileDescriptor _administratedObject) {
        this.setFileDescriptor(_administratedObject);
    }//End setObject

    @Override
    public FileDescriptor getNew() {
        try {
            FileDescriptor newFileDescriptor = new FileDescriptor(ltfFileChooser.getPath(), ';', '"');
            newFileDescriptor.setName(ltfName.getText());
            return newFileDescriptor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }//End try 
    }//End getNew

    @Override
    public FileDescriptor acceptChanges(FileDescriptor _administratedObject) {
        //En esta implementación el parámetro no se usa porque el objeto administrado ya lo tiene el panel
        administratedFileDescriptor.setName(ltfName.getText());
        administratedFileDescriptor.setPath(ltfFileChooser.getPath());
        administratedFileDescriptor.setFieldsSeparator(maskedSeparators.getValue(lcbSeparator.getSelectedItem()));
        administratedFileDescriptor.setFieldsDelimiter(lcbDelimiter.getSelectedItem());
        administratedFileDescriptor.setSkipLines(Integer.parseInt(ltfSkipLines.getText()));
        return administratedFileDescriptor;
    }//EndacceptChanges

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(ltfFileChooser)){
            fprPreview.setFilePath(new File(ltfFileChooser.getPath()));
        }//End if   
    }//End actionPerformed

}//End class PnlFileDescriptorAdmin
