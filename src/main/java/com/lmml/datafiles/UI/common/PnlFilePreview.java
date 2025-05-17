package com.lmml.datafiles.UI.common;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class PnlFilePreview extends JPanel{

    private static final long serialVersionUID = 1L;

    private JTextArea tarPreview = new JTextArea();
    private PnlLabeledComboBox<Integer> lcbShowLines = new PnlLabeledComboBox<Integer>("Mostrar líneas: ", new Integer[] {5, 10, 25, 50, 100 });
    private JLabel lblFileName = new JLabel("");

    private void showPreview(File _file) {
        if (_file.isFile()) {
            try {
                StringBuilder sb = new StringBuilder();
                String line;
                int linesToShow = lcbShowLines.getSelectedItem();
                linesToShow = linesToShow > 0 ? linesToShow : 5;
                int count = 0;
                BufferedReader br = new BufferedReader(new FileReader(_file));
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
    }//End setFileName

    public PnlFilePreview() {
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(lcbShowLines, BorderLayout.EAST);
        northPanel.add(lblFileName, BorderLayout.CENTER);
        this.setLayout(new BorderLayout());
        this.add(northPanel, BorderLayout.NORTH); 
        this.add(tarPreview, BorderLayout.CENTER);
        this.setBorder(javax.swing.BorderFactory.createTitledBorder("Preview")); 
    }//End constructor

}//End class PnlFilePreview
