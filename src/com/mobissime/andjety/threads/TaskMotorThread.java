/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package com.mobissime.andjety.threads;

import com.mobissime.andjety.utils.OptionsParser;
import com.mobissime.andjety.windows.TaskMotor;
import java.awt.Color;
import java.io.*;
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
import com.mobissime.andjety.objects.LogObj;
import com.mobissime.andjety.objects.LogObjDetails;
import com.mobissime.andjety.objects.TaskObj;
import com.mobissime.andjety.observers.Observable;
import com.mobissime.andjety.observers.Observateur;

/**
 *
 * @author Paul Coiffier
 */
public class TaskMotorThread extends Thread implements Observable {

    private TaskMotor tskFen;
    public TaskObj maTache;
    private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
    // Tableaux
    private List<String> lstColonnes;
    private List<ArrayList<String>> lstLignes;
    private String typeRequete;
    private Properties properties = new Properties();
    private int maxValOfPg;
    private int pgVal;
    private int nbServeursErreur;
    private int nbServeursOk;
    private int nbServeurs;
    private int nbBaseAtteindre;
    private String dureeDesTraitements;

    public TaskMotorThread(TaskMotor fen, TaskObj maTacheb) {
        //Attribution des variables contructeurs
        this.tskFen = fen;
        this.maTache = maTacheb;

        nbBaseAtteindre = 0;

        // Nom de la fenêtre
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

    @Override
    public void run() {
        nbServeursErreur = 0;
        nbServeursOk = 0;
        nbServeurs = 0;

        java.sql.Date sDate = new java.sql.Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy-hh-mm-ss");

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

        // Création de l'objet LOG
        final LogObj newLog = new LogObj();
        ArrayList<LogObjDetails> ListOfLogsDetails = new ArrayList<LogObjDetails>();
        newLog.setArrayOfLogsDetails(ListOfLogsDetails);

        newLog.setLogobj_taskLibelle(maTache.getTASK_LIBELLE());
        java.util.Date date = new java.util.Date();

        String format = "dd/MM/yy H:mm:ss";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);

        newLog.setLogobj_dateexec(date);
        newLog.setLogobj_heuredeb(formater.format(date));

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

        // Boucle principale : boucle sur les bases de données
        for (int ie = 0; ie < resultList2.size(); ie++) {

            DataTasksdb.setDblist_instance(resultList2.get(ie).getDblist_instance());
            DataTasksdb.setDblist_ip(resultList2.get(ie).getDblist_ip());
            DataTasksdb.setDblist_libelle(resultList2.get(ie).getDblist_libelle());
            DataTasksdb.setDblist_password(resultList2.get(ie).getDblist_password());
            DataTasksdb.setDblist_user(resultList2.get(ie).getDblist_user());
            DataTasksdb.setDblist_sgbd(resultList2.get(ie).getDblist_sgbd());

            // Création de l'objet LOG Détails
            LogObjDetails newLogDetails = new LogObjDetails();
            newLogDetails.setLogobj_date(date);

            java.util.Date heuredeb = new java.util.Date();
            newLogDetails.setLogobj_heuredeb(formater.format(heuredeb));
            newLogDetails.setLogobjd_database(DataTasksdb.getDblist_libelle());

            try {

                final String dbLibelle = resultList2.get(ie).getDblist_libelle();

                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {

                        nbBaseAtteindre++;
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

                sleep(100);

                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
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
                }

                stmt.close();
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        tskFen.LabelLog.setText(properties.getProperty("TaskMotorLabel8"));
                        afficheMessage(properties.getProperty("TaskMotorLabel8"), "green", true);
                        afficheMessage("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ", "green", false);
                    }
                });
                sleep(100);

                java.util.Date heurefin = new java.util.Date();
                newLogDetails.setLogobj_heurefin(formater.format(heurefin));
                newLogDetails.setLogobj_resultat("Ok");
                newLog.getArrayOfLogsDetails().add(newLogDetails);

            } // Fin de boucle sur les bases de données
            catch (Exception ex) {

                java.util.Date heurefin = new java.util.Date();
                newLogDetails.setLogobj_heurefin(formater.format(heurefin));
                newLogDetails.setLogobj_resultat("Erreur : " + ex.getMessage().toString());
                newLog.getArrayOfLogsDetails().add(newLogDetails);

                nbServeursErreur++;
                afficheMessage(ex.toString(), "red", true);
                afficheMessage("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ", "red", false);

            }

        } // Fin de boucle sur les bases de données

        afficheMessage("Traitements terminés !", "blue", false);

        dureeDesTraitements = tskFen.LabelTime.getText().toString();

        java.util.Date dateFin = new java.util.Date();
        newLog.setLogobj_heurefin(formater.format(dateFin));

        newLog.setLogobj_nbServeurs(nbServeurs);
        newLog.setLogobj_nbServeursErreur(nbServeursErreur);
        newLog.setLogobj_nbServeursOk(nbServeursOk);
        newLog.setLogObj_dureeDesTraitements(dureeDesTraitements);

        tskFen.addTaskLog((newLog));

        // Si aucun résultat, donc que des erreurs, on n'affiche pas le panel des résultats
        if (nbServeursErreur != nbServeurs) {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    tskFen.openResultFileInMainWindow(maTache.getTASK_LIBELLE(), lstColonnes, lstLignes, fichier, null, null);
                }
            });
        }

        // Ouverture onglet des informations sur le traitement
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                tskFen.showLogPanel(newLog);
            }
        });

        // Fermeture de l'onglet d'exécution de la tâche
        tskFen.fermerOnglet();

    }

    // Fonction de MAJ da la ProgressBar
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
}
