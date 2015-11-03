/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobissime.andjety.threads;

import com.mobissime.andjety.utils.OptionsParser;
import com.mobissime.andjety.windows.TaskMotor;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import com.mobissime.andjety.objects.DatabaseObj;
import com.mobissime.andjety.objects.TaskObj;
import com.mobissime.andjety.observers.Observable;
import com.mobissime.andjety.observers.Observateur;

/**
 *
 * @author Paul
 */
public class TaskMotorNewThread extends Thread implements Observable {

    private TaskMotor tskFen;
    public TaskObj maTache;
    private int pgVal;
    private List<String> lstColonnes;
    private List<ArrayList<String>> lstLignes;
    private BufferedWriter output;
    private BufferedWriter outputSimpleLogFile;
    private File fichierLogDetails;
    private String fichierLogDetailsName;
    private String fichierLogStdName;
    // Compteurs
    private int nbServeursErreur;
    private int nbServeursOk;
    private String heureFin;
    private String dureeTraitements;
    private int nbBaseAtteindre;
    private int nbServeurs;
    private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
    private int maxValOfPg;
    private String typeRequete;
    private Properties properties = new Properties();

    public TaskMotorNewThread(TaskMotor fen, TaskObj maTacheb) {

        tskFen = fen;
        maTache = maTacheb;

        this.setName(maTache.getTASK_LIBELLE());

        lstLignes = new ArrayList<ArrayList<String>>();
        lstColonnes = new ArrayList<String>();
        typeRequete = maTache.getTASK_TYPE();

        // Définition de la langue
        OptionsParser optionsParse = new OptionsParser();
        optionsParse.parseXml();
        String languageValue = optionsParse.languageValue;
        InputStream stream = null;

        if (languageValue.equals("english")) {
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("languages/english.properties");

        } else if (languageValue.toUpperCase().contains("FRA")) {
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("languages/french.properties");
        } else {
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("languages/english.properties");
        }

        try {
            properties.load(stream);
        } catch (IOException e) {
            System.out.println(properties.getProperty("errorOpenFile"));
        }
    }

    private void updatePgVal(int maxVal) {
        maxValOfPg = maxVal;
        tskFen.pgBar.setValue(pgVal);
        //maxVal = maxVal;
        float progressVal = (float) Integer.valueOf(pgVal) / maxVal;
        progressVal = progressVal * 100;
        DecimalFormat df = new DecimalFormat("#.00");

        tskFen.pgBar.setString("Progression : " + df.format(progressVal) + "%");
        updateObservateur();
    }

    @Override
    public void run() {

        nbServeursErreur = 0;
        nbServeursOk = 0;
        nbServeurs = 0;

        java.sql.Date sDate = new java.sql.Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy-hh-mm-ss");

        // Création du fichier de log détaillé
        String path = System.getProperty("user.dir").toString();
        fichierLogDetails = new java.io.File(path + "/log_details_" + sdf.format(sDate).toString() + ".txt");
        fichierLogDetailsName = path + "/log_details_" + sdf.format(sDate).toString() + ".txt";

        File fichierLogStd = new java.io.File(path + "/log_" + sdf.format(sDate).toString() + ".txt");
        fichierLogStdName = path + "/log_" + sdf.format(sDate).toString() + ".txt";
        output = null;

        nbBaseAtteindre = 0;

        try {
            fichierLogDetails.createNewFile();
            FileWriter fw = new FileWriter(fichierLogDetails);
            output = new BufferedWriter(fw);

            fichierLogStd.createNewFile();
            FileWriter fww = new FileWriter(fichierLogStd);
            outputSimpleLogFile = new BufferedWriter(fww);

        } catch (IOException ex) {
            Logger.getLogger(TaskMotorThread.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Ecriture de l'entête dans le fichier LOG
        writeLogEntete();

        // Création du fichier
        String fileResultat = maTache.getTASK_RESULTAT();

        final java.io.File fichier = new java.io.File(fileResultat + "/resultat_" + sdf.format(sDate).toString() + ".xls");
        java.io.FileOutputStream monFluxFichier = null;

        try {
            fichier.createNewFile();
            monFluxFichier = new java.io.FileOutputStream(fichier);
        } catch (IOException ex) {
            Logger.getLogger(TaskMotorThread.class.getName()).log(Level.SEVERE, null, ex);
        }

        // On va lancer la requete pour récupérer l'objet DatabaseList
        final List<DatabaseObj> resultList2 = maTache.getTASK_SERVERSLIST();
        nbServeurs = resultList2.size();
        // Max de la progressBar
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                tskFen.pgBar.setMaximum(resultList2.size());
            }
        });
        final DatabaseObj DataTasksdb = new DatabaseObj();
        for (int ie = 0; ie < resultList2.size(); ie++) {

            DataTasksdb.setDblist_instance(resultList2.get(ie).getDblist_instance());
            DataTasksdb.setDblist_ip(resultList2.get(ie).getDblist_ip());
            DataTasksdb.setDblist_libelle(resultList2.get(ie).getDblist_libelle());
            DataTasksdb.setDblist_password(resultList2.get(ie).getDblist_password());
            DataTasksdb.setDblist_user(resultList2.get(ie).getDblist_user());
            DataTasksdb.setDblist_sgbd(resultList2.get(ie).getDblist_sgbd());
            System.out.println("Database : " + resultList2.get(ie).getDblist_libelle());

            try {

                final String dbLibelle = resultList2.get(ie).getDblist_libelle();

                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {

                        nbBaseAtteindre++;
                        writeInLogFile("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                        writeInLogFile(properties.getProperty("TaskMotorLabel5") + " : " + dbLibelle);
                        tskFen.LabelDatabase.setText(DataTasksdb.getDblist_ip());
                        tskFen.LabelInstance.setText(DataTasksdb.getDblist_instance());
                        tskFen.LblNomDb.setText(DataTasksdb.getDblist_libelle());
                        tskFen.LabelUser.setText(DataTasksdb.getDblist_user());
                        tskFen.LabelLog.setText(properties.getProperty("TaskMotorLabel5") + " : " + dbLibelle);
                        //System.out.println("Connexion à la base de données : " + dbLibelle);
                        afficheMessage(properties.getProperty("TaskMotorLabel5") + " : " + dbLibelle, "black", true);
                        tskFen.LabelLog.setForeground(Color.green);
                        updatePgVal(resultList2.size());
                    }
                });

                pgVal++;
                sleep(1000);

                // TODO : implémenter test pour connexion Oracle / MySQL

                Connection conn = null;
                String url = " ";

                if (DataTasksdb.getDblist_sgbd().toUpperCase().equals("ORACLE")) {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    url = "jdbc:oracle:thin:@" + DataTasksdb.getDblist_ip() + ":1521:" + DataTasksdb.getDblist_instance();
                    conn = DriverManager.getConnection(url, DataTasksdb.getDblist_user(), DataTasksdb.getDblist_password());
                } else if (DataTasksdb.getDblist_sgbd().toUpperCase().equals("MYSQL")) {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    conn = DriverManager.getConnection("jdbc:mysql://" + DataTasksdb.getDblist_ip() + "/"
                            + DataTasksdb.getDblist_instance() + "?" + "user=" + DataTasksdb.getDblist_user() + "&password=" + DataTasksdb.getDblist_password());
                }


                conn.setAutoCommit(false);
                Statement stmt = conn.createStatement();
                ResultSet rset = null;
                int rsetUpdate;

                // En fonction du type de requête, l'appel à l'execution est différent
                if (typeRequete.equals("PROCEDURE")) {
                    // Bloc de code PL/SQL
                    rsetUpdate = stmt.executeUpdate(maTache.getTASK_REQUETE());

                    // Appel de l'execution de la procédure stockée
                    String sql = "{call TESTPCL(?)}";
                    // ==> Trouver une solution pour découper et récupérer le nom de la procédure stockée
                    CallableStatement call = conn.prepareCall(sql);

                    // Passage d'un paramètre bidon afin de permettre l'execution de la requête
                    call.setString(1, "ioio");
                    call.execute();

                } else if (typeRequete.equals("SELECT")) {
                    // Execution selection / requete classique en base de données
                    rset = stmt.executeQuery(maTache.getTASK_REQUETE());
                } else if (typeRequete.equals("INSERT")) {
                    // Execution mise à jour base de données 
                    rsetUpdate = stmt.executeUpdate(maTache.getTASK_REQUETE());
                }

                // Ancienne méthode de requêtage
                //ResultSet rset = stmt.executeQuery(maTache.getTaskRequete());
                sleep(100);

                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        //System.out.println("UpdateVal");
                        tskFen.LabelLog.setText(properties.getProperty("TaskMotorLabel6"));
                        afficheMessage(properties.getProperty("TaskMotorLabel6"), "green", true);
                        //writeInLogFile(properties.getProperty("TaskMotorLabel6"));
                        nbServeursOk++;
                    }
                });


                // Insertion des entêtes
                String[] noms = getNomsColonnes(rset);
                lstColonnes.add("Base");
                for (int ri = 0; ri < noms.length; ri++) {
                    lstColonnes.add(noms[ri]);
                }

                // Insertion des données
                int nbResults = 0;
                while (rset.next()) {
                    // On compte le nombre de champs
                    ArrayList<String> l = new ArrayList();
                    l.add(DataTasksdb.getDblist_libelle());
                    for (int ri = 0; ri < noms.length; ri++) {
                        try {
                            String valToAdd = rset.getString(noms[ri]);
                            if (valToAdd.isEmpty()) {
                                valToAdd = "Vide";
                            }

                            l.add(valToAdd);
                            //System.out.println("Resultat trouvé !");
                            //System.out.println("Ajoute element : " + valToAdd);
                        } catch (Exception e) {
                            System.out.println("Erreur : " + e.getMessage().toString());
                            l.add("vide");
                        }
                    }
                    lstLignes.add(l);
                    nbResults++;
                }

                if (nbResults <= 0) {
                    // On insère un ligne pour indiquer que pas de résultat
                    ArrayList<String> l = new ArrayList();
                    l.add(DataTasksdb.getDblist_libelle());
                    l.add(properties.getProperty("TaskMotorLabel7"));
                    lstLignes.add(l);
                    //System.out.println("Pas de résultat pour ce serveur");
                }

                stmt.close();
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        tskFen.LabelLog.setText(properties.getProperty("TaskMotorLabel8"));
                        afficheMessage(properties.getProperty("TaskMotorLabel8"), "green", true);
                        // writeInLogFile(properties.getProperty("TaskMotorLabel8"));
                        afficheMessage("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ", "green", false);
                    }
                });
                sleep(100);

                if (pgVal == resultList2.size()) {
                    SwingUtilities.invokeLater(new Runnable() {

                        @Override
                        public void run() {
                            tskFen.timeThread.stop();
                            tskFen.LabelLog.setText(properties.getProperty("TaskMotorLabel9"));
                            //writeInLogFile("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                            // writeInLogFile(properties.getProperty("TaskMotorLabel10") + tskFen.LabelTime.getText().toString());
                            //writeInLogFile("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                            tskFen.jBtnDemarrer.setEnabled(true);

                            //tskFen.LblStatutLbl.setText("Terminé en : ");
                            afficheMessage("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ", "green", false);
                            afficheMessage(properties.getProperty("TaskMotorLabel10") + tskFen.LabelTime.getText().toString(), "blue", true);
                            dureeTraitements = tskFen.LabelTime.getText().toString();

                            java.sql.Date sDate = new java.sql.Date(System.currentTimeMillis());
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                            heureFin = sdf.format(sDate);

                            // Ecriture du log standard
                            writeSimpleLogFile();

                            // On écrit les résultats pour les serveurs en erreur

                            try {
                                // Ouverture de la fen$etre de résultats
                                output.close();

                            } catch (IOException ex1) {
                                Logger.getLogger(TaskMotorThread.class.getName()).log(Level.SEVERE, null, ex1);
                            }
                            // Ouverture du fichier résultat
                            tskFen.openResultFileInMainWindow(maTache.getTASK_LIBELLE(), lstColonnes, lstLignes, fichier, fichierLogStdName, fichierLogDetailsName);
                            //tskFen.showLogPanel(fichierLogStdName, fichierLogDetailsName, maTache.getTASK_LIBELLE());
                            updateObservateur();

                            // Fermeture de l'onglet d'exécution de la tâche
                            tskFen.fermerOnglet();
                        }
                    });
                    sleep(100);
                }

            } catch (InstantiationException ex) {
                //Exceptions.printStackTrace(ex);
            } catch (IllegalAccessException ex) {
                //Exceptions.printStackTrace(ex);
            } catch (final InterruptedException ex) {
                //finishWithErrors(resultListTasksdb, ex.getMessage(), fichier);
            } catch (final SQLException ex) {

                final ArrayList<String> l = new ArrayList();
                l.add(DataTasksdb.getDblist_libelle());
                System.out.println("Erreur : " + DataTasksdb.getDblist_libelle());

                //System.out.println("Erreur");
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        afficheMessage(ex.toString(), "red", true);
                        afficheMessage("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ", "green", false);
                        writeInLogFile(properties.getProperty("errorTitle") + " : " + ex.toString());

                        l.add(ex.toString());
                        lstLignes.add(l);

                        //nbServeursErreur++;

                        //if (pgVal == resultListTasksdb.size()) {
                        try {
                            SwingUtilities.invokeLater(new Runnable() {

                                @Override
                                public void run() {
                                    tskFen.timeThread.stop();
                                    tskFen.LabelLog.setText(properties.getProperty("TaskMotorLabel9"));
                                    tskFen.LblStatutLbl.setText(properties.getProperty("TaskMotorLabel10") + ": ");
                                    writeInLogFile("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                                    writeInLogFile(properties.getProperty("TaskMotorLabel9") + tskFen.LabelTime.getText().toString());
                                    writeInLogFile("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                                    tskFen.jBtnDemarrer.setEnabled(true);
                                    afficheMessage("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ", "green", false);
                                    //afficheMessage(properties.getProperty("TaskMotorLabel9") + tskFen.LabelTime.getText().toString(), "blue", true);
                                    dureeTraitements = tskFen.LabelTime.getText().toString();

                                    java.sql.Date sDate = new java.sql.Date(System.currentTimeMillis());
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                                    heureFin = sdf.format(sDate);

                                    //writeSimpleLogFile();

                                    try {
                                        // Ouverture de la fen$etre de résultats
                                        output.close();
                                    } catch (IOException ex1) {
                                        Logger.getLogger(TaskMotorThread.class.getName()).log(Level.SEVERE, null, ex1);
                                    }

                                }
                            });
                            sleep(100);
                        } catch (InterruptedException ex1) {
                            Logger.getLogger(TaskMotorThread.class.getName()).log(Level.SEVERE, null, ex1);
                        }

                    }
                });
                // SI on est sur le dernier enregistrement
                nbServeursErreur++;
                if (pgVal == resultList2.size()) {
                    finishWithErrors(DataTasksdb.getDblist_libelle(), resultList2, ex.getMessage(), fichier);
                }

            } catch (final ClassNotFoundException ex) {
                //finishWithErrors(DataTasksdb.getDblist_libelle(), resultList2, ex.getMessage(), fichier);
            }
        }


    }

    public void afficheMessage(String valeur, String couleur, Boolean ifHeure) {
        Calendar cal = Calendar.getInstance();
        String heure = cal.get(Calendar.HOUR_OF_DAY) + "h " + cal.get(Calendar.MINUTE) + "m et " + cal.get(Calendar.SECOND) + "s";
        try {
            HTMLEditorKit kit = (HTMLEditorKit) tskFen.logEditor.getEditorKit();
            HTMLDocument doc = (HTMLDocument) tskFen.logEditor.getDocument();
            if (ifHeure == true) {
                kit.insertHTML(doc, doc.getLength(), "<font color = '" + couleur + "'>" + heure + " : " + valeur + "</font><br />", 0, 0, null);
            } else {
                kit.insertHTML(doc, doc.getLength(), "<font color = '" + couleur + "'>" + valeur + "</font><br />", 0, 0, null);
            }

        } catch (BadLocationException ex) {
            Logger.getLogger(TaskMotorThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TaskMotorThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String[] getNomsColonnes(ResultSet resultat) throws SQLException {

        ResultSetMetaData metadata = resultat.getMetaData();
        String[] noms = new String[metadata.getColumnCount()];

        for (int i = 0; i < noms.length; i++) {
            String nomColonne = metadata.getColumnLabel(i + 1);
            noms[i] = nomColonne;
            //System.out.println("Colonne : " + nomColonne);
        }
        return noms;

    }

    private void finishWithErrors(final String servername, final List<DatabaseObj> resultListTasksdb, final String ex, final File fichier) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                afficheMessage(ex.toString(), "red", true);
                afficheMessage("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ", "green", false);
                writeInLogFile(properties.getProperty("errorTitle") + " : " + ex.toString());

                ArrayList<String> l = new ArrayList();
                l.add(servername);
                l.add(ex.toString());
                lstLignes.add(l);

                nbServeursErreur++;

                if (pgVal == resultListTasksdb.size()) {
                    try {
                        SwingUtilities.invokeLater(new Runnable() {

                            @Override
                            public void run() {
                                tskFen.timeThread.stop();
                                tskFen.LabelLog.setText(properties.getProperty("TaskMotorLabel9"));
                                tskFen.LblStatutLbl.setText(properties.getProperty("TaskMotorLabel10") + ": ");
                                writeInLogFile("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                                writeInLogFile(properties.getProperty("TaskMotorLabel9") + tskFen.LabelTime.getText().toString());
                                writeInLogFile("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                                tskFen.jBtnDemarrer.setEnabled(true);
                                afficheMessage("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ", "green", false);
                                afficheMessage(properties.getProperty("TaskMotorLabel9") + tskFen.LabelTime.getText().toString(), "blue", true);
                                dureeTraitements = tskFen.LabelTime.getText().toString();

                                java.sql.Date sDate = new java.sql.Date(System.currentTimeMillis());
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                                heureFin = sdf.format(sDate);

                                // Ecriture du log standard
                                writeSimpleLogFile();

                                try {
                                    // Ouverture de la fen$etre de résultats
                                    output.close();
                                } catch (IOException ex1) {
                                    Logger.getLogger(TaskMotorThread.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                                // Si tous les serveurs sont en erreur alors on affiche pas de résultat
                                System.out.println("nbServeursErreur : " + nbServeursErreur);
                                System.out.println("nbServeurs : " + nbServeurs);

                                if (nbServeursErreur < nbServeurs) {
                                    System.out.println("nbServeurs : " + nbServeurs);
                                    System.out.println("nbServeursErreur : " + nbServeursErreur);
                                    tskFen.openResultFileInMainWindow(maTache.getTASK_LIBELLE(), lstColonnes, lstLignes, fichier, fichierLogStdName, fichierLogDetailsName);
                                    updateObservateur();
                                }

                                // Fermeture de l'onglet d'exécution de la tâche
                                tskFen.fermerOnglet();
                                //tskFen.showLogPanel(fichierLogStdName, fichierLogDetailsName, maTache.getTASK_LIBELLE());
                            }
                        });
                        sleep(100);
                    } catch (InterruptedException ex1) {
                        Logger.getLogger(TaskMotorThread.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            }
        });
    }

    private void writeLogEntete() {
        try {
            // Ajout dans le log
            java.sql.Date sDate = new java.sql.Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

            //String heure = cal.get(Calendar.DAY_OF_WEEK) + "/" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR) + " : " + cal.get(Calendar.HOUR_OF_DAY) + "h" + cal.get(Calendar.MINUTE) + "mn" + cal.get(Calendar.SECOND) + "s";
            String heure = sdf.format(sDate);

            output.append("***************************************************\r\n");
            output.append("*                ANDJETY 2.0                      *\r\n");
            output.append("***************************************************\r\n\r\n");

            output.append(properties.getProperty("onetaskTitle") + " : " + maTache.getTASK_LIBELLE() + " \r\n");
            output.append(properties.getProperty("TaskMotorLabel11") + " : " + heure + "\r\n\r\n");
        } catch (IOException ex) {
            Logger.getLogger(TaskMotorThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void writeSimpleLogFile() {
        try {

            java.sql.Date sDate = new java.sql.Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

            String heure = sdf.format(sDate);

            outputSimpleLogFile.append("***************************************************\r\n");
            outputSimpleLogFile.append("*                ANDJETY 2.0                      *\r\n");
            outputSimpleLogFile.append("***************************************************\r\n\r\n");

            outputSimpleLogFile.append(heure + " - " + properties.getProperty("onetaskTitle") + " : " + maTache.getTASK_LIBELLE() + " \r\n\r\n");
            outputSimpleLogFile.append(heure + " - " + properties.getProperty("TaskMotorLabel11") + " : " + heure + "\r\n");
            outputSimpleLogFile.append(heure + " - " + properties.getProperty("TaskMotorLabel12") + " : " + heureFin + "\r\n\r\n");

            outputSimpleLogFile.append(heure + " - " + properties.getProperty("TaskMotorLabel14") + " : " + nbServeursOk + "/" + nbBaseAtteindre + " \r\n");
            outputSimpleLogFile.append(heure + " - " + properties.getProperty("errorsTitle") + " : " + nbServeursErreur + "\r\n\r\n");
            outputSimpleLogFile.append(heure + " - " + properties.getProperty("TaskMotorLabel13") + " : " + dureeTraitements + "\r\n");
            outputSimpleLogFile.close();

        } catch (IOException ex) {
            Logger.getLogger(TaskMotorThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void writeInLogFile(String logValue) {
        try {

            java.sql.Date sDate = new java.sql.Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

            String heure = sdf.format(sDate);
            output.append(heure + " - " + logValue + "\r\n");

        } catch (IOException ex) {
            Logger.getLogger(TaskMotorThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Ajoute un observateur à la liste
     */
    @Override
    public void addObservateur(Observateur obs) {
        this.listObservateur.add(obs);
    }

    /**
     * Retire tous les observateurs de la liste
     */
    @Override
    public void delObservateur() {
        this.listObservateur = new ArrayList<Observateur>();
    }

    /**
     * Avertit les observateurs que l'observable a changé et invoque la méthode
     * update de chaque observateur !
     */
    @Override
    public void updateObservateur() {
        for (Observateur obs : this.listObservateur) {

            float progressVal = (float) Integer.valueOf(pgVal) / maxValOfPg;
            progressVal = progressVal * 100;
            DecimalFormat df = new DecimalFormat("#.00");

            //System.out.println("MAJ de l'observateur à réimplémenter");
            obs.update(String.valueOf(maTache.getTASK_LIBELLE()), df.format(progressVal));
        }
    }
}
