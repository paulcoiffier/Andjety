/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety;

import com.mobissime.andjety.utils.OptionsParser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Application initialisation 
 * @author {@value com.mobissime.andjety.Constants#appAuthor}
 * @version {@value com.mobissime.andjety.Constants#appVersion}
 */
public class Andjety {

    /**
     * Main function to start the application
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                OptionsParser optionsParse = new OptionsParser();
                optionsParse.parseXml();
                JFrame.setDefaultLookAndFeelDecorated(true);

                try {
                    UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.Substance" + optionsParse.skinName);

                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                    System.out.println("Impossible de définir le Look : " + e.getMessage().toString());
                }

                MainFen theFen = new MainFen();
                theFen.show();
            }
        });
    }

    /**
     * Exit the application
     */
    public static void exit() {
        System.exit(0);
    }
}
