package com.pl.poc.view;

import com.pl.poc.controller.CancelController;
import com.pl.poc.controller.UpdateController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Rafał on 2017-08-19.
 */
public class NonlinearFiltersSettingsView extends JFrame {
    private MainView mainView;

    private JButton cancelButton;
    private JButton okButton;
    private JButton runButton;
    private JSlider radiusSlider;
    private JRadioButton squareMask;
    private JRadioButton roundMask;
    private ButtonGroup radioButtonGroup;

    public NonlinearFiltersSettingsView(MainView mainView){
        this.mainView = mainView;
        initComponents();
    }

    private void initComponents(){
        cancelButton = new JButton("Cancel");
        okButton = new JButton("OK");
        runButton = new JButton("Run");
        radiusSlider = new JSlider();
        squareMask = new JRadioButton("Square Mask");
        roundMask = new JRadioButton("Round Mask");
        radioButtonGroup = new ButtonGroup();

        cancelButton.setPreferredSize(new Dimension(125, 25));
        cancelButton.addActionListener(new CancelController(mainView, this));

        okButton.setPreferredSize(new Dimension(125, 25));
        okButton.addActionListener(new UpdateController(mainView, this));

        runButton.setPreferredSize(new Dimension(125, 25));

        radiusSlider.setMinimum(1);
        radiusSlider.setMaximum(10);
        radiusSlider.setValue(1);
        radiusSlider.setMajorTickSpacing(5);
        radiusSlider.setMinorTickSpacing(1);
        radiusSlider.setPaintTicks(true);

        squareMask.setSelected(true);

        radioButtonGroup.add(squareMask);
        radioButtonGroup.add(roundMask);

        JPanel separator1 = new JPanel();
        JPanel separator2 = new JPanel();
        JPanel separator3 = new JPanel();
        JPanel separator4 = new JPanel();

        GridLayout gridLayout = new GridLayout(6,2);
        gridLayout.setHgap(30);

        JPanel panel = new JPanel(gridLayout);
        panel.add(new JLabel("Shape mask:"));
        panel.add(new JLabel("Radius:"));
        panel.add(squareMask);
        panel.add(radiusSlider);
        panel.add(roundMask);
        panel.add(separator1);
        panel.add(separator2);
        panel.add(runButton);
        panel.add(separator3);
        panel.add(separator4);
        panel.add(okButton);
        panel.add(cancelButton);
        add(panel);

        pack();
    }

    public JButton getRunButton() {
        return runButton;
    }

    public JSlider getRadiusSlider() {
        return radiusSlider;
    }

    public JRadioButton getSquareMask() {
        return squareMask;
    }

    public JRadioButton getRoundMask() {
        return roundMask;
    }
}