package com.lmml.datafiles.UI.common;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public abstract class PnlLabeledComponent extends JPanel{

    private static final long serialVersionUID = 1L;

    JLabel lblTitle = new JLabel();

    public void setTitleLabel(JLabel _label){
        this.remove(lblTitle);
        lblTitle = _label;
        this.add(lblTitle, BorderLayout.WEST);
        this.revalidate();
        this.repaint();
    }//End setLabel

    public void setTitle(String _title){
        lblTitle.setText(_title);
    }//End setTitle

    public String getTitle(){
        return lblTitle.getText();
    }//End getTitle

    protected void initComponents(JComponent _component) {
        this.add(lblTitle, BorderLayout.WEST);
        this.add(_component, BorderLayout.CENTER);
        lblTitle.setLabelFor(_component);
    }//End initComponents

    public PnlLabeledComponent(){ 
        super();
        this.setLayout(new BorderLayout());
    }//End constructor

}//End abstract class PnlLabeledComponent

