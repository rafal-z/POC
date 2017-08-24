package com.pl.poc.view;

import com.pl.poc.controller.*;
import com.pl.poc.model.ImagesModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Created by Rafał on 2017-07-13.
 */
public class MainView extends JFrame{

    private JLabel imageLabel;
    private ImageIcon imageIcon;

    private ImagesModel imagesModel;

    private BrightnessSettingsView brightnessForm;
    private GaussSettingsView gaussSettingsView;
    private SharpeningSettingsView sharpeningSettingsView;
    private SkeletonizationSettingsView skeletonizationSettingsView;
    private ContourSettingsView contourSettingsView;
    private NonlinearFiltersSettingsView minimumFilterSettingsView;
    private NonlinearFiltersSettingsView maximumFilterSettingsView;
    private NonlinearFiltersSettingsView medianFilterSettingsView;
    private NonlinearFiltersSettingsView openingFilterSettingsView;
    private NonlinearFiltersSettingsView closingFilterSettingsView;

    public MainView(){
        setTitle("Przetwarzanie obrazów cyfrowych");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents();
    }

    public void repaint(){
        imageIcon.setImage(imagesModel.getDstImage());
        imageLabel.setIcon(imageIcon);
        imageLabel.repaint();
    }

    public JLabel getImageLabel() {
        return imageLabel;
    }

    public void setImageLabel(JLabel imageLabel) {
        this.imageLabel = imageLabel;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public ImagesModel getImagesModel() {
        return imagesModel;
    }

    public void setImagesModel(ImagesModel imagesModel) {
        this.imagesModel = imagesModel;
    }

    private void initComponents(){
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem mitem = new JMenuItem("Open");
        ReadImageController readImageController = new ReadImageController(this);
        mitem.addActionListener(readImageController);
        menu.add(mitem);

        mitem = new JMenuItem("Reset Image");
        ResetImageController resetImageController = new ResetImageController(this);
        mitem.addActionListener(resetImageController);
        menu.add(mitem);

        menu.addSeparator();

        mitem = new JMenuItem("Exit");
        mitem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }
        });
        menu.add(mitem);


        menu = new JMenu("Colors");
        menuBar.add(menu);
        mitem = new JMenuItem("Brightness");
        mitem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                brightnessForm = new BrightnessSettingsView(MainView.this);
                brightnessForm.pack();
                brightnessForm.setVisible(true);
            }
        });
        menu.add(mitem);

        menu = new JMenu("Filters");
        menuBar.add(menu);
        mitem = new JMenuItem("Gaussian Blur");
        mitem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gaussSettingsView = new GaussSettingsView(MainView.this);
                gaussSettingsView.pack();
                gaussSettingsView.setVisible(true);
            }
        });
        menu.add(mitem);

        mitem = new JMenuItem("Unsharp Mask");
        mitem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sharpeningSettingsView = new SharpeningSettingsView(MainView.this);
                sharpeningSettingsView.pack();
                sharpeningSettingsView.setVisible(true);
            }
        });
        menu.add(mitem);

        JMenu morphologyMenu = new JMenu("Morphological operations");
        menu.add(morphologyMenu);

        mitem = new JMenuItem("Skeletonization");
        mitem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                skeletonizationSettingsView = new SkeletonizationSettingsView(MainView.this);
                skeletonizationSettingsView.pack();
                skeletonizationSettingsView.setVisible(true);
            }
        });
        morphologyMenu.add(mitem);

        mitem = new JMenuItem("Contour");
        mitem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contourSettingsView = new ContourSettingsView(MainView.this);
                contourSettingsView.pack();
                contourSettingsView.setVisible(true);
            }
        });
        morphologyMenu.add(mitem);

        JMenu nonlinearMenu = new JMenu("Nonlinear filters");
        menu.add(nonlinearMenu);

        mitem = new JMenuItem("Minimum filter");
        mitem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                minimumFilterSettingsView = new NonlinearFiltersSettingsView(MainView.this);
                minimumFilterSettingsView.getRunButton().addActionListener(new MinimumFilterController(MainView.this, minimumFilterSettingsView));
                minimumFilterSettingsView.setTitle("Minimum filter");
                minimumFilterSettingsView.pack();
                minimumFilterSettingsView.setVisible(true);
            }
        });
        nonlinearMenu.add(mitem);

        mitem = new JMenuItem("Maximum filter");
        mitem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                maximumFilterSettingsView = new NonlinearFiltersSettingsView(MainView.this);
                maximumFilterSettingsView.getRunButton().addActionListener(new MaximumFilterController(MainView.this, maximumFilterSettingsView));
                maximumFilterSettingsView.setTitle("Maximum filter");
                maximumFilterSettingsView.pack();
                maximumFilterSettingsView.setVisible(true);
            }
        });
        nonlinearMenu.add(mitem);


        mitem = new JMenuItem("Median filter");
        mitem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                medianFilterSettingsView = new NonlinearFiltersSettingsView(MainView.this);
                medianFilterSettingsView.getRunButton().addActionListener(new MedianFilterController(MainView.this, medianFilterSettingsView));
                medianFilterSettingsView.setTitle("Median filter");
                medianFilterSettingsView.pack();
                medianFilterSettingsView.setVisible(true);
            }
        });
        nonlinearMenu.add(mitem);

        mitem = new JMenuItem("Opening filter");
        mitem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openingFilterSettingsView = new NonlinearFiltersSettingsView(MainView.this);
                openingFilterSettingsView.getRunButton().addActionListener(new OpeningFilterController(MainView.this, openingFilterSettingsView));
                openingFilterSettingsView.setTitle("Opening filter");
                openingFilterSettingsView.pack();
                openingFilterSettingsView.setVisible(true);
            }
        });
        nonlinearMenu.add(mitem);

        mitem = new JMenuItem("Closing filter");
        mitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closingFilterSettingsView = new NonlinearFiltersSettingsView(MainView.this);
                closingFilterSettingsView.getRunButton().addActionListener(new ClosingFilterController(MainView.this, closingFilterSettingsView));
                closingFilterSettingsView.setTitle("Closing filter");
                closingFilterSettingsView.pack();
                closingFilterSettingsView.setVisible(true);
            }
        });
        nonlinearMenu.add(mitem);

        imageLabel = new JLabel();
        add(new JScrollPane(imageLabel));
    }
}
