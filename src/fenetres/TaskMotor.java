/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package fenetres;

import Threads.TaskMotorThread;
import Utils.OptionsParser;
import andjety.MainFen;
import entities.Scheduletasks;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import objects.LogObj;
import objects.TaskObj;
import objects.TasksexecObj;

/**
 *
 * @author Paul Coiffier
 */
public class TaskMotor extends javax.swing.JPanel {

    /**
     * Creates new form TaskMotor
     */
    public JProgressBar pgBar;
    public TaskObj laTache;
    public JLabel LabelDatabase;
    public JLabel LabelInstance;
    public JLabel LabelUser;
    public JLabel LabelPassword;
    public JLabel LabelLibelle;
    public JLabel LabelLog;
    public JLabel LabelTime;
    public JLabel LblStatutLbl;
    public JLabel LblNomDb;
    public JButton jBtnDemarrer;
    public static TaskMotor laFenetre;
    public Thread timeThread;
    public JEditorPane logEditor;
    public HTMLEditorKit kit;
    public HTMLDocument doc;
    public MainFen fenPrincipale;
    private Scheduletasks sct;
    private Properties properties = new Properties();
    private boolean statutControl;
    Thread threadOfTask;

    public TaskMotor(TaskObj t, MainFen f, Scheduletasks s) {
        try {
            statutControl = false;
            initComponents();
            jXBusyLabel1.setVisible(false);
            sct = s;
            fenPrincipale = f;
            pgBar = jProgressBar1;
            laTache = t;
            jLabel2.setText(laTache.getTASK_LIBELLE());
            jLabelResultat.setText(t.getTASK_RESULTAT());
            jBtnDemarrer = jButtonDemarrer;
            LblNomDb = jLabelDatabase;
            LabelDatabase = jLabelAdresse;
            LabelInstance = jLabelInstance;
            LabelUser = jLabelUtilisateur;
            LabelLog = jLabelLog;
            logEditor = jEditorPane1;
            LabelTime = jLabelTime;
            LblStatutLbl = jLabelLblStatut;
            jEditorPane1.setContentType("text/html");
            kit = new HTMLEditorKit();
            doc = new HTMLDocument();

            jEditorPane1.setEditorKit(kit);
            jEditorPane1.setDocument(doc);

            laFenetre = this;

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

            jLabel1.setText(properties.getProperty("resultTitle") + " : ");
            jLabel8.setText(properties.getProperty("DatabaseEditorLabel1") + " : ");
            jLabel10.setText(properties.getProperty("DatabaseEditorLabel3") + " : ");
            jLabelLblStatut.setText(properties.getProperty("TaskMotorLabel1") + " : ");
            jXBusyLabel1.setText(properties.getProperty("TaskMotorLabel2") + "...");
            jButtonDemarrer.setText(properties.getProperty("TaskMotorLabel3"));

            HTMLEditorKit kitt = (HTMLEditorKit) logEditor.getEditorKit();
            HTMLDocument doct = (HTMLDocument) logEditor.getDocument();
            kitt.insertHTML(doct, doct.getLength(), properties.getProperty("TaskMotorLabel4"), 0, 0, null);
            kitt.insertHTML(doct, doct.getLength(), "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <br />", 0, 0, null);
        } catch (BadLocationException ex) {
            Logger.getLogger(TaskMotor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TaskMotor.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void addTaskLog(LogObj newLog){
        fenPrincipale.addTaskLog(newLog);
    }
    
    public void fermerOnglet() {
        fenPrincipale.CloseTaskTab(laTache.getTASK_LIBELLE());
    }

    public void showLogPanel(LogObj leLog) {
        fenPrincipale.afficheLogPanel(leLog);
        timeThread.stop();
    }

    public void openResultFileInMainWindow(String nomTache, List<String> colonnes, List<ArrayList<String>> lignes, File ochemin, String fileLogStd, String fileLogDetails) {
        // On enregistre la tache dans la base

        java.sql.Date sDate = new java.sql.Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy-hh-mm-ss");

        TasksexecObj newTaskExec;

        if (sct == null) {
            newTaskExec = new TasksexecObj(laTache.getTASK_LIBELLE(), 0, sdf.format(sDate).toString(), ochemin.getAbsolutePath(), fileLogStd, fileLogDetails);
        } else {
            newTaskExec = new TasksexecObj(laTache.getTASK_LIBELLE(), 0, sdf.format(sDate).toString(), ochemin.getAbsolutePath(), fileLogStd, fileLogDetails);
        }

        jXBusyLabel1.setBusy(false);
        jXBusyLabel1.setVisible(false);

        // On enregistre l'objet TaskexecObj pour l'archivage des données
        fenPrincipale.insertTaskExec(newTaskExec);
        // On affiche les résultats
        fenPrincipale.afficheExcelResultat(nomTache, colonnes, lignes, ochemin);
        //System.out.println("Traitement terminé !");
        fenPrincipale.updateObservateur();
    }

    static void persist(Object object) {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("AndjetyPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
            //System.out.println("Commit");
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jXPanel1 = new org.jdesktop.swingx.JXPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabelLblStatut = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButtonDemarrer = new javax.swing.JButton();
        jXPanel2 = new org.jdesktop.swingx.JXPanel();
        jLabelResultat = new javax.swing.JLabel();
        jLabelAdresse = new javax.swing.JLabel();
        jLabelDatabase = new javax.swing.JLabel();
        jLabelInstance = new javax.swing.JLabel();
        jLabelUtilisateur = new javax.swing.JLabel();
        jLabelTime = new javax.swing.JLabel();
        jLabelLog = new javax.swing.JLabel();
        jXBusyLabel1 = new org.jdesktop.swingx.JXBusyLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jProgressBar1 = new javax.swing.JProgressBar();
        jSeparator1 = new javax.swing.JSeparator();

        jRadioButton1.setText("jRadioButton1");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/gnome_task_due.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jXPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jXPanel1.setLayout(new java.awt.GridLayout(7, 2));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Résultat :");
        jXPanel1.add(jLabel1);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Adresse : ");
        jXPanel1.add(jLabel8);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Database : ");
        jXPanel1.add(jLabel7);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Instance :");
        jXPanel1.add(jLabel9);

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Utilisateur :");
        jXPanel1.add(jLabel10);

        jLabelLblStatut.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelLblStatut.setText("Démarrée depuis : ");
        jXPanel1.add(jLabelLblStatut);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("Action : ");
        jXPanel1.add(jLabel11);

        jButtonDemarrer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cog-icon.png"))); // NOI18N
        jButtonDemarrer.setText("Démarrer");
        jButtonDemarrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDemarrerActionPerformed(evt);
            }
        });

        jXPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jXPanel2.setLayout(new java.awt.GridLayout(7, 1));

        jLabelResultat.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabelResultat.setForeground(new java.awt.Color(51, 51, 255));
        jLabelResultat.setText("...");
        jLabelResultat.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabelResultat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelResultatMouseClicked(evt);
            }
        });
        jXPanel2.add(jLabelResultat);

        jLabelAdresse.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabelAdresse.setText("...");
        jXPanel2.add(jLabelAdresse);

        jLabelDatabase.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabelDatabase.setText("...");
        jXPanel2.add(jLabelDatabase);

        jLabelInstance.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabelInstance.setText("...");
        jXPanel2.add(jLabelInstance);

        jLabelUtilisateur.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabelUtilisateur.setText("...");
        jXPanel2.add(jLabelUtilisateur);

        jLabelTime.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabelTime.setText("...");
        jXPanel2.add(jLabelTime);

        jLabelLog.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabelLog.setText("...");
        jXPanel2.add(jLabelLog);

        jXBusyLabel1.setText("Veuillez patienter...");
        jXBusyLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jXBusyLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonDemarrer))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jXPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jXPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jXPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonDemarrer)
                    .addComponent(jXBusyLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jEditorPane1.setEditable(false);
        jScrollPane1.setViewportView(jEditorPane1);

        jProgressBar1.setStringPainted(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(117, 117, 117)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(58, 58, 58)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDemarrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDemarrerActionPerformed

        if (statutControl) {
            // On stop le traitement
            threadOfTask.stop();
            timeThread.stop();
            jXBusyLabel1.setVisible(false);
            //threadOfTask.interrupt();
            jButtonDemarrer.setText("Démarrer");
            jButtonDemarrer.setEnabled(false);
            jLabelLog.setText("<html><font color='red'>Traitement interrompu !</font></html>");
        } else {
            lanceControles();

            timeThread = new Thread(new BasicThread2());
            timeThread.start();
            jButtonDemarrer.setText("Arrêter");
            statutControl = true;
            //jButtonDemarrer.setEnabled(false);
        }


    }//GEN-LAST:event_jButtonDemarrerActionPerformed

    public void lanceControlesFromOtherForm() {
        lanceControles();

        timeThread = new Thread(new BasicThread2());
        timeThread.start();
        jButtonDemarrer.setEnabled(false);
    }

    private void jLabelResultatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelResultatMouseClicked
        try {
            Desktop.getDesktop().open(new File(jLabelResultat.getText()));
        } catch (IOException ex) {
            Logger.getLogger(TaskMotor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabelResultatMouseClicked

    class BasicThread2 implements Runnable {

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            while (true) {
                long time = System.currentTimeMillis() - start;
                final long seconds = time / 1000;
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        jLabelTime.setText(seconds + "s");
                    }
                });
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDemarrer;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAdresse;
    private javax.swing.JLabel jLabelDatabase;
    private javax.swing.JLabel jLabelInstance;
    private javax.swing.JLabel jLabelLblStatut;
    private javax.swing.JLabel jLabelLog;
    private javax.swing.JLabel jLabelResultat;
    private javax.swing.JLabel jLabelTime;
    private javax.swing.JLabel jLabelUtilisateur;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private org.jdesktop.swingx.JXBusyLabel jXBusyLabel1;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private org.jdesktop.swingx.JXPanel jXPanel2;
    // End of variables declaration//GEN-END:variables

    public void updatePgBarValue(int pgval) {
        jProgressBar1.setValue(pgval);
    }

    private void lanceControles() {

        jXBusyLabel1.setVisible(true);
        jXBusyLabel1.setBusy(true);

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                TaskMotorThread tsk = new TaskMotorThread(laFenetre, laTache);
                threadOfTask = new Thread(tsk);
                fenPrincipale.threadArrayListe.add(tsk);
                fenPrincipale.updateObservateur();
                threadOfTask.setDaemon(true);
                threadOfTask.start();
            }
        });
    }
}
