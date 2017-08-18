package com.pl.poc.view;

import com.pl.poc.controller.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Created by Rafał on 2017-08-15.
 */
public class ContourSettingsView extends JFrame {
    private MainView mainView;

    private JButton cancelButton;
    private JButton okButton;
    private JButton runButton;
    private JTable table;
    private JSlider verticallySlider;
    private JSlider horizontallySlider;

    public ContourSettingsView(MainView mainView){
        this.mainView = mainView;
        initComponents();
    }

    public void initComponents(){
        cancelButton = new JButton();
        okButton = new JButton();
        runButton = new JButton();
        verticallySlider = new JSlider(SwingConstants.VERTICAL);
        horizontallySlider = new JSlider(SwingConstants.HORIZONTAL);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Contour...");
        setMinimumSize(new Dimension(300, 100));
        setPreferredSize(new Dimension(800, 500));

        cancelButton.setText("Anuluj");
        cancelButton.setPreferredSize(new Dimension(125, 25));
        cancelButton.addActionListener(new CancelController(mainView, this));

        okButton.setText("OK");
        okButton.setPreferredSize(new Dimension(125, 25));
        okButton.addActionListener(new UpdateController(mainView, this));

        runButton.setText("Run");
        runButton.setPreferredSize(new Dimension(125, 25));
        runButton.addActionListener(new ContourButtonController(mainView, this));

        ContourSliderController contourSliderController = new ContourSliderController(mainView, this);
        verticallySlider.setMaximum(5);
        verticallySlider.setMinimum(1);
        verticallySlider.setValue(2);
        verticallySlider.addChangeListener(contourSliderController);

        horizontallySlider.setMaximum(5);
        horizontallySlider.setMinimum(1);
        horizontallySlider.setValue(2);
        horizontallySlider.addChangeListener(contourSliderController);

        table = new JTable(verticallySlider.getValue()*2+1, horizontallySlider.getValue()*2+1);
        table.setRowHeight(25);

        JPanel tablePanel = new JPanel();
        tablePanel.add(table);
        JScrollPane scroll = new JScrollPane(tablePanel);
        JPanel panel = new JPanel();
        panel.add(scroll);

        JPanel sliderVerticallyPanel = new JPanel();
        sliderVerticallyPanel.add(verticallySlider);
        sliderVerticallyPanel.setPreferredSize(new Dimension(50, 300));

        JPanel sliderHorizontallyPanel = new JPanel();
        sliderHorizontallyPanel.add(horizontallySlider);

        JPanel runPanel = new JPanel();
        runPanel.add(runButton);

        JPanel okPanel = new JPanel();
        okPanel.add(okButton);

        JPanel cancelPanel = new JPanel();
        cancelPanel.add(cancelButton);

        Container pane = getContentPane();
        pane.setLayout(new GridLayout(3,2));
        pane.add(panel);
        pane.add(sliderVerticallyPanel);
        pane.add(sliderHorizontallyPanel);
        pane.add(runPanel);
        pane.add(okPanel);
        pane.add(cancelPanel);

        pack();
    }

    public void resizeTable(int height, int width){
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(height);
        dtm.setColumnCount(width);
        table.setModel(dtm);

        for(int i=0; i<table.getColumnCount(); i++){
            for(int j=0; j<table.getRowCount(); j++){
                table.setValueAt(1,j,i);
            }
        }
//        table.setValueAt(1, table.getRowCount()/2, table.getColumnCount()/2);

    }

    public int[] getValuesWithTable(){
        int[] mat = new int[table.getRowCount()*table.getColumnCount()];

        for(int i=0; i<table.getRowCount(); i++){
            for(int j=0; j<table.getColumnCount(); j++){
                mat[i*table.getColumnCount()+j] = Integer.parseInt(table.getValueAt(i,j).toString());
//                mat[i*table.getColumnCount()+j] = (Integer) table.getValueAt(i,j);
            }
        }
        return mat;
    }

    public JSlider getVerticallySlider() {
        return verticallySlider;
    }

    public void setVerticallySlider(JSlider verticallySlider) {
        this.verticallySlider = verticallySlider;
    }

    public JSlider getHorizontallySlider() {
        return horizontallySlider;
    }

    public void setHorizontallySlider(JSlider horizontallySlider) {
        this.horizontallySlider = horizontallySlider;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }
}
