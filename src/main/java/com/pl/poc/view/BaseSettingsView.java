package com.pl.poc.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Rafał on 2017-08-05.
 */
public class BaseSettingsView extends JFrame{
    private JButton cancelButton;
    private JButton okButton;

    public void centerFrameOnScreen(){
        Dimension dimensionScr = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int)((dimensionScr.getWidth() - this.getWidth())/2);
        int y = (int)((dimensionScr.getHeight() - this.getHeight())/2);
        this.setLocation(x,y);
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public void setOkButton(JButton okButton) {
        this.okButton = okButton;
    }
}
