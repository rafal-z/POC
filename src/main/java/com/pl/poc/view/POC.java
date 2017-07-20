package com.pl.poc.view;

import javax.swing.*;

import static java.awt.EventQueue.*;

/**
 * Created by Rafał on 2017-07-19.
 */
public class POC {
    public static void main(String[] args)
    {
        invokeLater(new Runnable()
        {
            public void run()
            {
                MainView frame = new MainView();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.setSize(800, 600);
            }
        });

    }
}
