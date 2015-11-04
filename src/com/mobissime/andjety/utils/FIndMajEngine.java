/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.utils;

import com.mobissime.andjety.Constants;
import com.mobissime.andjety.MainFen;
import java.awt.TrayIcon;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JOptionPane;

/**
 *
 * @author Paul Coiffier
 */
public class FIndMajEngine implements Runnable {

    public String appVersion;
    public MainFen mf;
    public boolean ifShowMessage;

    @Override
    public void run() {

        InputStream ins = null;

        try {
            URL url = new URL("http://web.mobissime.com/andjety_version.txt");
            ins = url.openStream();

            InputStreamReader is = new InputStreamReader(ins);
            BufferedReader br = new BufferedReader(is);
            final String read = br.readLine();
             
            System.out.println("Read version : " + read.toString());
            System.out.println("Constant version : " + Constants.appVersion);
            
            if (!Constants.appVersion.equals(read.toString())) {

                java.awt.EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        JOptionPane.showMessageDialog(mf, "Une nouvelle version de Andjety est disponible. Veuillez vous rendre à l'adresse https://github.com/paulcoiffier/Andjety/ pour la télécharger", "Andjety", JOptionPane.INFORMATION_MESSAGE);

                    }
                });
                /*mf.jProgressBarUpdateObjet.setVisible(true);
                 appVersion = read.toString();
                 String fileNameToDownload = null;

                 if (OSValidator.isWindows()) {
                 fileNameToDownload = "http://www.saphirsoftware.com/softs/Andjety" + read.toString().replace(".", "") + ".exe";
                 } else if (OSValidator.isUnix()) {
                 fileNameToDownload = "http://www.saphirsoftware.com/softs/Andjety" + read.toString().replace(".", "") + ".deb";
                 }

                 System.out.println("File to Download : " + fileNameToDownload);

                 URL u = new URL(fileNameToDownload);
                 URLConnection uc = u.openConnection();
                 final int taille = uc.getContentLength();
                 InputStream brut = uc.getInputStream();
                 InputStream entree = new BufferedInputStream(brut);
                 byte[] donnees = new byte[taille];
                 int octetsLus = 0;
                 int deplacement = 0;
                 float alreadyRead = 0;
                 // Boucle permettant de parcourir tous les octets du fichier à lire

                 java.awt.EventQueue.invokeLater(new Runnable() {

                 @Override
                 public void run() {
                 mf.jProgressBarUpdateObjet.setMaximum(taille);
                 mf.jProgressBarUpdateObjet.setVisible(true);
                 mf.trayIcon.displayMessage("Andjety", "Téléchargement de la mise à jour " + read.toString() + " en cours...", TrayIcon.MessageType.INFO);
                 mf.setStatusMessage("Téléchargement : ");
                 }
                 });

                 while (deplacement < taille) {

                 final int deplacementBis = deplacement;
                 java.awt.EventQueue.invokeLater(new Runnable() {

                 @Override
                 public void run() {
                 mf.jProgressBarUpdateObjet.setValue(deplacementBis);
                 }
                 });

                 //System.out.println("Downloading..." + deplacement + "/" + taille);
                 // utilisation de la methode "read" de la classe InputStream
                 octetsLus = entree.read(donnees, deplacement, donnees.length - deplacement);

                 // Petit calcul: mise à jour du nombre total d’octets lus par ajout au nombre d’octets lus au cours des précédents passages au nombre d’octets lus pendant ce passage
                 alreadyRead = alreadyRead + octetsLus;

                 // -1 marque par convention la fin d’un fichier, double opérateur "égale": = =
                 if (octetsLus == -1) {
                 break;
                 }

                 // se cadrer à un endroit précis du fichier pour lire les octets suivants, c’est le déplacement
                 deplacement += octetsLus;

                 }

                 final int deplacementBis = deplacement;
                 java.awt.EventQueue.invokeLater(new Runnable() {

                 @Override
                 public void run() {
                 mf.jProgressBarUpdateObjet.setValue(deplacementBis + 1);
                 }
                 });

                 // fermer le flux d’entrée.
                 entree.close();

                 // Récupérer le nom du fichier
                 String fichier = u.getFile();
                 fichier = fichier.substring(fichier.lastIndexOf(
                 '/') + 1);

                 // Créer un fichier sur le DD afin d’y copier le contenu du fichier téléchargé (par un flux de sortie).
                 FileOutputStream fichierSortie = new FileOutputStream(System.getProperty("user.dir") + "/tmp/Andjety_setup.exe");

                 // copier…
                 fichierSortie.write(donnees);

                 // vider puis fermer le flux de sortie
                 fichierSortie.flush();
                 fichierSortie.close();

                 mf.checkUpdateFinish(appVersion, System.getProperty("user.dir") + "/tmp/Andjety_setup.exe");
                 */
            } else {
                mf.setStatusMessage("Andjety est à jour");
                System.out.println("Version identique");
                if (ifShowMessage) {
                    // Affichage du message d'erreur depuis le Thread dans un autre Thread Runnable sinon Substance Look&Feel Plante
                    java.awt.EventQueue.invokeLater(new Runnable() {

                        @Override
                        public void run() {
                            JOptionPane.showMessageDialog(null, "Andjety est à jour !", "Andjety", JOptionPane.INFORMATION_MESSAGE);
                            mf.jProgressBarUpdateObjet.setVisible(false);
                        }
                    });
                }

            }
        } catch (IOException ex) {
            mf.setStatusMessage("Impossible de ce connecter au serveur de mise à jour");
            //Logger.getLogger(FIndMajEngine.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ins.close();
            } catch (IOException ex) {
                //Logger.getLogger(FIndMajEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
