package com.pl.poc.view;

import com.pl.poc.controller.ReadImageController;
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

    private JLabel imageLabel;
    private ImageIcon imageIcon;

    public MainView()
    {
        setTitle("Przetwarzanie obrazów cyfrowych");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents();
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

        imageLabel = new JLabel();
        add(new JScrollPane(imageLabel));
    }
}
