package com.lmml.datafiles.UI.common;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JScrollPane; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PnlFilePreview extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JTextArea tarPreview = new JTextArea();
    private PnlLabeledComboBox<Integer> lcbShowLines = new PnlLabeledComboBox<Integer>("Mostrar líneas: ", new Integer[] {5, 10, 25, 50, 100 });
    private JLabel lblFileName = new JLabel("");
    int linesToShow;
    File fileToPreview;

    public void clear() {
        tarPreview.setText("");
        fileToPreview = null;
    }//End clear

    private void showPreview(File _file) {
        if (_file.isFile() && _file.exists()) {
            try {
                StringBuilder sb = new StringBuilder();
                String line;
                int linesToShow = lcbShowLines.getSelectedItem();
                linesToShow = linesToShow > 0 ? linesToShow : 5;
                int count = 0;
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(_file), "UTF-8")); 
                while ((line = br.readLine()) != null && count < linesToShow) {
                    sb.append(line).append("\n");
                    count++;
                }//End while
                tarPreview.setText(sb.toString());
                br.close();
            } catch (IOException e) {
                tarPreview.setText("Error al leer el archivo: " + e.getMessage());
            }//End try
        } else {
            tarPreview.setText("No se puede mostrar preview, el archivo '%s' no existe".formatted(_file.getAbsolutePath()));
        }//End if
    }//End showPreview

    public void setFilePath(File _file) {
        lblFileName.setText(_file.getName());
        fileToPreview = _file;
        showPreview(_file);
    }//End setFileName

    public PnlFilePreview() {
        lblFileName.setForeground(Color.GRAY);
        tarPreview.setEditable(false);
        lcbShowLines.setSelectedItem(0);
        lcbShowLines.addActionListener(this);
        linesToShow = lcbShowLines.getSelectedItem();
        JScrollPane scrTarPreview = new JScrollPane(tarPreview, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(lcbShowLines, BorderLayout.EAST);
        northPanel.add(lblFileName, BorderLayout.CENTER);
        this.setLayout(new BorderLayout(0, 5));
        this.add(northPanel, BorderLayout.NORTH); 
        this.add(scrTarPreview, BorderLayout.CENTER);
        this.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Preview"), 
                BorderFactory.createEmptyBorder(2,2,2,2)));
    }//End constructor

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(lcbShowLines)) {
            linesToShow = lcbShowLines.getSelectedItem();
            showPreview(fileToPreview);
        }//End if
    }//End actionPerformed

}//End class PnlFilePreview
