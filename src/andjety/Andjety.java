/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package andjety;

import Utils.OptionsParser;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author Paul Coiffier
 */
public class Andjety {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                OptionsParser optionsParse = new OptionsParser();
                optionsParse.parseXml();
                String skinName = optionsParse.skinName;
                JFrame.setDefaultLookAndFeelDecorated(true);
                
                try {
                    /*SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin." + skinName);
                     UIManager.setLookAndFeel("org.jvnet.substance.SubstanceLookAndFeel");*/
                    UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.Substance" + optionsParse.skinName);

                } catch (Exception e) {
                    System.out.println("Impossible de définir le Look : " + e.getMessage().toString());
                }

                /*SplashScreen mySplash = SplashScreen.getSplashScreen();
                 if (mySplash != null) {
                 try {
                 Thread.currentThread().sleep(5000L);
                 } catch (Exception e) {
                 }
                 mySplash.close();
                 }*/


                MainFen theFen = new MainFen();
                theFen.show();
            }
        });
    }

    static void exit() {
        System.exit(0);
    }
}
