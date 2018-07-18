package com.pl.poc.view;

import com.pl.poc.controller.CancelController;
import com.pl.poc.controller.GaussToSharpeningController;
import com.pl.poc.controller.SharpeningController;
import com.pl.poc.controller.UpdateController;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by Rafał on 2017-08-05.
 */
public class SharpeningSettingsView extends BaseSettingsView{
    private MainView mainView;

    private JButton cancelButton;
    private JButton okButton;
    private JSlider gaussSlider;
    private JSlider sharpeningSlider;
    private JLabel numberGauss;
    private JLabel numberSharpening;

    SharpeningSettingsView(MainView mainView){
        this.mainView = mainView;
        initComponents();
    }

    private void initComponents() {
        gaussSlider = new JSlider();
        numberGauss = new JLabel("1");
        sharpeningSlider = new JSlider();
        numberSharpening = new JLabel("1");
        cancelButton = new JButton();
        okButton = new JButton();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Unsharp mask...");
        setMinimumSize(new Dimension(300, 100));
        setPreferredSize(new Dimension(410, 180));

        gaussSlider.setMaximum(50);
        gaussSlider.setMinimum(1);
        gaussSlider.setValue(1);
        gaussSlider.addChangeListener(new GaussToSharpeningController(mainView, this));

        sharpeningSlider.setMaximum(50);
        sharpeningSlider.setMinimum(1);
        sharpeningSlider.setValue(1);
        sharpeningSlider.addChangeListener(new SharpeningController(mainView, this));

        cancelButton.setText("Anuluj");
        cancelButton.setPreferredSize(new Dimension(125, 25));
        cancelButton.addActionListener(new CancelController(mainView, this));

        okButton.setText("OK");
        okButton.setPreferredSize(new Dimension(125, 25));
        okButton.addActionListener(new UpdateController(mainView, this));

        JPanel panel1 = new JPanel(new GridBagLayout());
        JPanel panel2 = new JPanel(new GridBagLayout());
        JPanel panel3 = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(20, 0,0, 45);
        c.gridx = 0;
        c.gridwidth = 1;
        panel1.add(new JLabel("Radius: "), c);

        c.gridwidth = 2; //2 columns wide
        c.gridx = 1;
        panel1.add(gaussSlider, c);

        c.insets = new Insets(20, 0,0, 0);
        c.gridwidth = 1;
        c.gridx = 3;
        panel1.add(numberGauss, c);

        c.insets = new Insets(10, 0,0, 45);
        c.gridx = 0;
        c.gridwidth = 1;
        panel2.add(new JLabel("Unsharp mask: "));

        c.gridwidth = 2;
        c.gridx = 1;
        panel2.add(sharpeningSlider, c);

        c.insets = new Insets(5, 0,0, 0);
        c.gridwidth = 1;
        c.gridx = 3;
        panel2.add(numberSharpening, c);

        c.gridy = 0;
        c.gridx = 0;
        c.insets = new Insets(10,0,10,50);
        panel3.add(okButton, c);

        c.gridy = 0;
        c.gridx = 3; //4th column
        c.insets = new Insets(20,50,10,0);
        panel3.add(cancelButton, c);

        add(panel1, BorderLayout.PAGE_START);
        add(panel2, BorderLayout.CENTER);
        add(panel3, BorderLayout.PAGE_END);
        pack();
    }

    public JLabel getNumberGauss() {
        return numberGauss;
    }

    public void setNumberGauss(JLabel numberGauss) {
        this.numberGauss = numberGauss;
    }

    public JLabel getNumberSharpening() {
        return numberSharpening;
    }

    public void setNumberSharpening(JLabel numberSharpening) {
        this.numberSharpening = numberSharpening;
    }

    public JSlider getGaussSlider() {
        return gaussSlider;
    }

    public void setGaussSlider(JSlider gaussSlider) {
        this.gaussSlider = gaussSlider;
    }

    public JSlider getSharpeningSlider() {
        return sharpeningSlider;
    }

    public void setSharpeningSlider(JSlider sharpeningSlider) {
        this.sharpeningSlider = sharpeningSlider;
    }
}
