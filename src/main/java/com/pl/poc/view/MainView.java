package com.pl.poc.view;

import com.pl.poc.controller.ReadImageController;
import com.pl.poc.controller.ResetImageController;
import com.pl.poc.model.ImagesModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Created by Rafał on 2017-07-13.
 */
public class MainView extends JFrame{

    private ImagesModel imagesModel;
    private BrightnessSettingsView brightnessForm;
    private GaussSettingsView gaussSettingsView;
    private SharpeningSettingsView sharpeningSettingsView;
    private SkeletonizationSettingsView skeletonizationSettingsView;

    private JLabel imageLabel;
    private ImageIcon imageIcon;

    public MainView()
    {
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

    private void initComponents()
    {
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

        mitem = new JMenuItem("Skeletonization");
        mitem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                skeletonizationSettingsView = new SkeletonizationSettingsView(MainView.this);
                skeletonizationSettingsView.pack();
                skeletonizationSettingsView.setVisible(true);
            }
        });
        menu.add(mitem);

        imageLabel = new JLabel();
        add(new JScrollPane(imageLabel));
    }
}
