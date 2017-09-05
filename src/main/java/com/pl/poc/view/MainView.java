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

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem mitem;
    private JMenu morphologyMenu;
    private JMenu nonlinearMenu;

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
    private AdaptiveMedianSettingsView adaptiveMedianSettingsView;
    private BilateralFilterSettingsView bilateralFilterSettingsView;

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

    private void initComponents(){
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menu = new JMenu("File");
        menuBar.add(menu);

        mitem = new JMenuItem("Open");
        mitem.addActionListener(new ReadImageController(this));
        menu.add(mitem);

        mitem = new JMenuItem("Reset Image");
        mitem.addActionListener(new ResetImageController(this));
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
                gaussSettingsView.setVisible(true);
            }
        });
        menu.add(mitem);

        mitem = new JMenuItem("Unsharp Mask");
        mitem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sharpeningSettingsView = new SharpeningSettingsView(MainView.this);
                sharpeningSettingsView.setVisible(true);
            }
        });
        menu.add(mitem);

        morphologyMenu = new JMenu("Morphological operations");
        menu.add(morphologyMenu);

        mitem = new JMenuItem("Skeletonization");
        mitem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                skeletonizationSettingsView = new SkeletonizationSettingsView(MainView.this);
                skeletonizationSettingsView.setVisible(true);
            }
        });
        morphologyMenu.add(mitem);

        mitem = new JMenuItem("Contour");
        mitem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contourSettingsView = new ContourSettingsView(MainView.this);
                contourSettingsView.setVisible(true);
            }
        });
        morphologyMenu.add(mitem);

        nonlinearMenu = new JMenu("Nonlinear filters");
        menu.add(nonlinearMenu);

        mitem = new JMenuItem("Minimum filter");
        mitem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                minimumFilterSettingsView = new NonlinearFiltersSettingsView(MainView.this);
                minimumFilterSettingsView.getRunButton().addActionListener(new MinimumFilterController(MainView.this, minimumFilterSettingsView));
                minimumFilterSettingsView.setTitle("Minimum filter");
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
                closingFilterSettingsView.setVisible(true);
            }
        });
        nonlinearMenu.add(mitem);

        mitem = new JMenuItem("Adaptive Median");
        mitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaptiveMedianSettingsView = new AdaptiveMedianSettingsView(MainView.this);
                adaptiveMedianSettingsView.setVisible(true);
            }
        });
        nonlinearMenu.add(mitem);

        mitem = new JMenuItem("Bilateral Filter");
        mitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bilateralFilterSettingsView = new BilateralFilterSettingsView(MainView.this);
                bilateralFilterSettingsView.setVisible(true);
            }
        });
        nonlinearMenu.add(mitem);

        imageLabel = new JLabel();
        add(new JScrollPane(imageLabel));
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
}
