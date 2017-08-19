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
        setPreferredSize(new Dimension(350, 450));
        setResizable(false);
        setLocation(10,10);

        cancelButton.setText("Anuluj");
        cancelButton.setPreferredSize(new Dimension(120, 25));
        cancelButton.addActionListener(new CancelController(mainView, this));

        okButton.setText("OK");
        okButton.setPreferredSize(new Dimension(120, 25));
        okButton.addActionListener(new UpdateController(mainView, this));

        runButton.setText("Run");
        runButton.setPreferredSize(new Dimension(245, 25));
        runButton.addActionListener(new ContourButtonController(mainView, this));

        ContourSliderController contourSliderController = new ContourSliderController(mainView, this);
        verticallySlider.setMaximum(10);
        verticallySlider.setMinimum(1);
        verticallySlider.setValue(1);
        verticallySlider.setInverted(true);
        verticallySlider.setPreferredSize(new Dimension(50,200));
        verticallySlider.setMajorTickSpacing(4);
        verticallySlider.setMinorTickSpacing(1);
        verticallySlider.setPaintTicks(true);
        verticallySlider.addChangeListener(contourSliderController);

        horizontallySlider.setMaximum(10);
        horizontallySlider.setMinimum(1);
        horizontallySlider.setValue(1);
        horizontallySlider.setPreferredSize(new Dimension(200,50));
        horizontallySlider.setMajorTickSpacing(4);
        horizontallySlider.setMinorTickSpacing(1);
        horizontallySlider.setPaintTicks(true);
        horizontallySlider.addChangeListener(contourSliderController);

        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setTableHeader(null);
        table.setPreferredScrollableViewportSize(new Dimension(250,250));

        resizeTable(verticallySlider.getValue()*2+1, horizontallySlider.getValue()*2+1);

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(280,280));
        JPanel tablePanel = new JPanel();

        JPanel separator = new JPanel();
        separator.setPreferredSize(new Dimension(80, 50));

        tablePanel.add(new JScrollPane(table));
        mainPanel.add(tablePanel);
        mainPanel.add(verticallySlider);
        mainPanel.add(horizontallySlider);
        mainPanel.add(separator);
        mainPanel.add(runButton);
        mainPanel.add(okButton);
        mainPanel.add(cancelButton);

        add(mainPanel);
    }

    public void resizeTable(int height, int width){
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(height);
        dtm.setColumnCount(width);
        table.setModel(dtm);
        table.setRowHeight(25);

        for(int i=0; i<table.getRowCount(); i++){
            for(int j=0; j<table.getColumnCount(); j++){
                table.getColumnModel().getColumn(j).setPreferredWidth(25);
                table.setValueAt(1,i,j);
            }
        }
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
