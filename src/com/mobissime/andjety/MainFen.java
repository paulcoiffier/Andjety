/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety;

import com.mobissime.andjety.objects.TaskExecListe;
import com.mobissime.andjety.objects.LogObjArrayObj;
import com.mobissime.andjety.objects.ScheduleTaskObj;
import com.mobissime.andjety.objects.ScheduleTasksListeObj;
import com.mobissime.andjety.objects.LogObj;
import com.mobissime.andjety.objects.TaskObjListe;
import com.mobissime.andjety.objects.DatabaseObj;
import com.mobissime.andjety.objects.DatabaseListeObj;
import com.mobissime.andjety.objects.TasksexecObj;
import com.mobissime.andjety.objects.TaskObj;
import com.mobissime.andjety.windows.AccueilPanel;
import com.mobissime.andjety.windows.PlanifTachesPanel;
import com.mobissime.andjety.windows.LogPanel;
import com.mobissime.andjety.windows.TaskMotor;
import com.mobissime.andjety.windows.EditorSQL;
import com.mobissime.andjety.dialogs.AboutDialog;
import com.mobissime.andjety.windows.DatabasesPanel;
import com.mobissime.andjety.windows.TasksPanel;
import com.mobissime.andjety.dialogs.OptionsDialog;
import com.mobissime.andjety.windows.ExcelViewJPanel;
import com.mobissime.andjety.utils.FIndMajEngine;
import com.mobissime.andjety.utils.XMLTools;
import com.mobissime.andjety.utils.ButtonTabComponent;
import com.mobissime.andjety.utils.OptionsParser;
import com.mobissime.andjety.utils.OSValidator;
import com.mobissime.andjety.utils.ExcelSheetWriter;
import com.mobissime.andjety.threads.TaskMotorThread;
import com.mobissime.andjety.wizard.Assistant1;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.*;
import com.mobissime.andjety.observers.MainFenObervateur;
import com.mobissime.andjety.observers.MainFenObservable;
import org.openide.util.Exceptions;
import org.quartz.Scheduler;

/**
 * Andjety Main Window
 *
 * @author {@value com.mobissime.andjety.Constants#appAuthor}
 * @version {@value com.mobissime.andjety.Constants#appVersion}
 */
public class MainFen extends javax.swing.JFrame implements MainFenObservable {

    public static JTabbedPane theJTabbedPan;
    private DefaultListModel listModelTable = new DefaultListModel();
    private EntityManager em;
    public Scheduler scheduler;
    public static MainFen laFenetre;
    public String driver = "org.apache.derby.jdbc.ClientDriver";
    public List<TaskMotorThread> threadArray;
    public static List<TaskMotorThread> threadArrayListe;
    private ArrayList<MainFenObervateur> listObservateur = new ArrayList<>();
    private DatabaseListeObj dbListObj;
    private TaskObjListe taskListObj;
    private TaskExecListe taskExecListObj;
    private LogObjArrayObj listOfLogs;
    private ScheduleTasksListeObj scheduleTasksListObj;
    private Properties properties = new Properties();
    public JProgressBar jProgressBarUpdateObjet = new JProgressBar();
    // Systray
    private MenuItem systrayQuitterItem;
    public TrayIcon trayIcon;

    public MainFen() {

        initComponents();

        JDialog.setDefaultLookAndFeelDecorated(true);

        // Initialisation message StatusBar
        this.setStatusMessage("");
        this.setSystemTrayMenu();

        jButtonHelpButton.setVisible(false);
        jProgressBarUpdateObjet.setVisible(false);
        jProgressBarUpdate.setVisible(false);
        //jButtonGestTachesPlanif
        //        .setVisible(false);
        jMenuItem5.setVisible(false);
        jSeparator1.setVisible(false);
        jMenuItemEditeurSQL.setVisible(false);
        jSeparator10.setVisible(false);

        //jButtonEditeurSQL.setVisible(false);
        //jButtonEditeurSQL.setEnabled(false);
        //jButtonBarEditeurSQL.setVisible(false);
        //jButtonTachesPlanif.setVisible(false);
        jProgressBarUpdateObjet = jProgressBarUpdate;

        this.addObservateur(new MainFenObervateur() {

            @Override
            public void update(List<TaskMotorThread> liste) {
                LoadTasksDataList();
            }
        });
        setLanguage();

        String pathOfProject = System.getProperty("user.dir");
        try {
            // Désérialisation des objets XML
            /*if (OSValidator.isUnix()) {
             dbListObj = (DatabaseListeObj) XMLTools.decodeFromFile("/usr/lib/Andjety/Files/Andjety_servers.xml");
             taskListObj = (TaskObjListe) XMLTools.decodeFromFile("/usr/lib/Andjety/Files/Andjety_tasks.xml");
             taskExecListObj = (TaskExecListe) XMLTools.decodeFromFile("/usr/lib/Andjety/Files/Andjety_tasksexec.xml");
             listOfLogs = (LogObjArrayObj) XMLTools.decodeFromFile("/usr/lib/Andjety/Files/Andjety_tasks_logs.xml");
             } else {
             dbListObj = (DatabaseListeObj) XMLTools.decodeFromFile(pathOfProject + "/Files/Andjety_servers.xml");
             taskListObj = (TaskObjListe) XMLTools.decodeFromFile(pathOfProject + "/Files/Andjety_tasks.xml");
             taskExecListObj = (TaskExecListe) XMLTools.decodeFromFile(pathOfProject + "/Files/Andjety_tasksexec.xml");
             listOfLogs = (LogObjArrayObj) XMLTools.decodeFromFile(pathOfProject + "/Files/Andjety_tasks_logs.xml");
             }*/
            dbListObj = (DatabaseListeObj) XMLTools.decodeFromFile(pathOfProject + "/Files/Andjety_servers.xml");
            taskListObj = (TaskObjListe) XMLTools.decodeFromFile(pathOfProject + "/Files/Andjety_tasks.xml");
            taskExecListObj = (TaskExecListe) XMLTools.decodeFromFile(pathOfProject + "/Files/Andjety_tasksexec.xml");
            listOfLogs = (LogObjArrayObj) XMLTools.decodeFromFile(pathOfProject + "/Files/Andjety_tasks_logs.xml");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFen.class.getName()).log(Level.SEVERE, null, ex);
        }

        threadArray = new ArrayList<>();
        threadArrayListe = threadArray;

        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        theJTabbedPan = jTabbedPane1;
        laFenetre = this;

        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("ressources/AndjetyIcon.png")));
        this.setTitle("Andjety " + Constants.appVersion);

        // Ajout du panel d'accueil
        affichePanelAccueil();

        /*
         * NetworkServerControl server; try { server = new
         * NetworkServerControl(InetAddress.getByName("localhost"), 1527);
         * server.start(null);
         *
         * Class.forName(driver).newInstance(); Connection conn =
         * DriverManager.getConnection(dbURL); } catch (Exception ex) {
         * Logger.getLogger(MainFen.class.getName()).log(Level.SEVERE, null,
         * ex); }
         */
        this.LoadTasksDataList();

        // Démarrage du scheduler
        /*
         * try {
         *
         * scheduler = StdSchedulerFactory.getDefaultScheduler();
         * scheduler.start(); // Démarrage des tâches enregistrées
         * restaureScheduledTasks();
         *
         * } catch (SchedulerException se) { System.out.println("Erreur
         * Scheduler : " + se.getMessage().toString()); }
         */
        // Application du controle de mise à jour au démarrage si dans les options
        OptionsParser optParse = new OptionsParser();
        optParse.parseXml();

        if (optParse.majOnStartup.equals("oui")) {
            //System.out.println("FIND MAJ");
            FIndMajEngine fmw = new FIndMajEngine();
            fmw.appVersion = Constants.appVersion;
            fmw.mf = this;
            Thread thk = new Thread(fmw);
            thk.start();
        }

        // Astuces au démarrage
     /*
         * if (optParse.majAstuceOnStartup.equals("oui")) {
         * java.awt.EventQueue.invokeLater(new Runnable() {
         *
         * @Override public void run() { TipOfTheDayFen assitantFen = new
         * TipOfTheDayFen(null, rootPaneCheckingEnabled); java.awt.Dimension
         * screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
         *
         * assitantFen.setLocation( (screenSize.width - assitantFen.getWidth())
         * / 2, (screenSize.height - assitantFen.getHeight()) / 2);
         *
         * assitantFen.setTitle("Andjety- Astuce du jour...");
         * assitantFen.show(); } }); }
         */
    }

    public void CloseTaskTab(String TabName) {

        // Recherche de l'onglet ouvert qui porte le nom de la tâche, et fermeture
        for (int i = 0; i < jTabbedPane1.getTabCount(); i++) {
            if (jTabbedPane1.getTitleAt(i).equals(TabName)) {
                jTabbedPane1.remove(i);
            }
        }

    }

    public void setStatusMessage(String message) {
        jXLabelStatusBar.setText(message);
    }

    public void purgeDatabaseArray() {
        ArrayList<DatabaseObj> arrayDbListe = new ArrayList<>();
        dbListObj.setDbListe(arrayDbListe);
        serialiseServersTOXML();
    }

    public TaskObjListe getTaskObjListe() {
        return taskListObj;
    }

    public void checkUpdateFinish(String newVersion, final String nomFichier) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {

                    JOptionPane.showMessageDialog(MainFen.this, "Le téléchargement de la mise à jour est terminé. Andjety va maintenant ce fermer afin de procéder à l'installation de la mise à jour.",
                            "Mise à jour", JOptionPane.INFORMATION_MESSAGE);

                    // Execution de la MAJ
                    Process p = Runtime.getRuntime().exec(nomFichier);
                    Andjety.exit();

                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }

            }
        });
    }

    public void restaureScheduledTasks() {
        /*
         * // Inventaire des ScheduleTasks DBUtils dbu = new DBUtils();
         * List<Scheduletasks> theListe = dbu.getListOfScheduleTask(); // Boucle
         * for (Scheduletasks th : theListe) {
         *
         * // Reconstitution expression pour les jours String daysParts = "";
         *
         * if (th.getIflundi() == 1) { if (daysParts.length() > 1) { daysParts =
         * daysParts + ",MON"; } else { daysParts = daysParts + "MON"; } }
         *
         * if (th.getIfmardi() == 1) { if (daysParts.length() > 1) { daysParts =
         * daysParts + ",TUE"; } else { daysParts = daysParts + "TUE"; } }
         *
         * if (th.getIfmercredi() == 1) { if (daysParts.length() > 1) {
         * daysParts = daysParts + ",WED"; } else { daysParts = daysParts +
         * "WED"; } }
         *
         * if (th.getIfmercredi() == 1) { if (daysParts.length() > 1) {
         * daysParts = daysParts + ",THU"; } else { daysParts = daysParts +
         * "THU"; } }
         *
         * if (th.getIfmercredi() == 1) { if (daysParts.length() > 1) {
         * daysParts = daysParts + ",FRI"; } else { daysParts = daysParts +
         * "FRI"; } }
         *
         * if (th.getIfmercredi() == 1) { if (daysParts.length() > 1) {
         * daysParts = daysParts + ",SAT"; } else { daysParts = daysParts +
         * "SAT"; } }
         *
         * if (th.getIfmercredi() == 1) { if (daysParts.length() > 1) {
         * daysParts = daysParts + ",SUN"; } else { daysParts = daysParts +
         * "SUN"; } }
         *
         * if (th.getIfalldays().equals("oui")) { daysParts =
         * "MON,TUE,WED,THU,FRI,SAT,SUN"; }
         *
         * // Génération de l'expression CRON String cronString = "0 " +
         * th.getMinutes() + " " + th.getHeures() + "-" + th.getHeures() + " ? *
         * " + daysParts; // Création de la nouvelle tâche JobKey theJkey = new
         * JobKey(String.valueOf(th.getId())); TriggerKey tkey = new
         * TriggerKey(String.valueOf(th.getId())); JobDetail job =
         * newJob(TaskJob.class).withIdentity(theJkey).build(); Trigger trigger
         * =
         * newTrigger().withIdentity(tkey).withSchedule(cronSchedule(cronString)).forJob(theJkey).build();
         *
         * try {
         *
         * // On récupère l'objet tâche pour envoyer son libellé à la
         * planification Tasks tt = dbu.getTaskById(th.getIdtask(), em);
         * job.getJobDataMap().put("TaskLibelle", tt.getTaskLibelle() + "/" +
         * th.getId()); //job.getJobDataMap().put("ScheduleTaskId", tt.getId());
         * System.out.println("Planification tache : " + tt.getTaskLibelle());
         * scheduler.scheduleJob(job, trigger);
         *
         * } catch (SchedulerException ex) {
         * Logger.getLogger(PlanifTacheAdd.class.getName()).log(Level.SEVERE,
         * null, ex); } }
         */
    }

    public void LoadTasksDataList() {
        try {
            listModelTable.clear();

            for (TaskObj tsk : taskListObj.getDbObjListe()) {
                //System.out.println("Ajout tache : " + resultList.get(i).getTaskLibelle().toString());
                listModelTable.addElement(tsk.getTASK_LIBELLE());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Attention : Impossible de ce connecter à la base de données d'Andjety, l'application va ce fermer. Détails : " + e.getMessage(), "Andjety", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        jXList1.setModel(listModelTable);
    }

    private void lanceOptions() {
        OptionsDialog myOptionView = new OptionsDialog(this, true, this);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        myOptionView.setLocation(
                (screenSize.width - myOptionView.getWidth()) / 2,
                (screenSize.height - myOptionView.getHeight()) / 2);

        myOptionView.setTitle(properties.getProperty("optionsTitle"));
        myOptionView.show();
    }

    private void affichePanelAccueil() {
        AccueilPanel assitantFen = new AccueilPanel(this, taskExecListObj);
        jTabbedPane1.add(properties.getProperty("welcomeTitle"), assitantFen);
        jTabbedPane1.setSelectedComponent(assitantFen);
        initTabComponent(jTabbedPane1.getSelectedIndex());
    }

    private void lanceAssitant() {
        Assistant1 assitantFen = new Assistant1(this, rootPaneCheckingEnabled, "", "", "", dbListObj, this);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        assitantFen.setLocation(
                (screenSize.width - assitantFen.getWidth()) / 2,
                (screenSize.height - assitantFen.getHeight()) / 2);

        assitantFen.setTitle(properties.getProperty("wizardTitle"));
        assitantFen.show();
    }

    private void lanceGestBaseDeDonnees() {

        boolean trouve = false;
        for (int i = 0; i < jTabbedPane1.getTabCount(); i++) {
            if (jTabbedPane1.getComponentAt(i).getClass().toString().equals("class com.mobissime.andjety.windows.DatabasesPanel")) {
                jTabbedPane1.setSelectedIndex(i);
                trouve = true;
                break;
            }
        }

        if (!trouve) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DatabasesPanel dbPane = new DatabasesPanel(this, dbListObj);
            jTabbedPane1.add(properties.getProperty("manageDBTitle"), dbPane);
            jTabbedPane1.setSelectedComponent(dbPane);
            initTabComponent(jTabbedPane1.getSelectedIndex());
        }

    }

    private void lanceGestionTaches() {

        boolean trouve = false;
        for (int i = 0; i < jTabbedPane1.getTabCount(); i++) {
            if (jTabbedPane1.getComponentAt(i).getClass().toString().equals("class com.mobissime.andjety.windows.TasksPanel")) {
                jTabbedPane1.setSelectedIndex(i);
                trouve = true;
                break;
            }
        }

        if (!trouve) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            TasksPanel dbPane = new TasksPanel(this, taskListObj);
            jTabbedPane1.add(properties.getProperty("manageTasks"), dbPane);
            jTabbedPane1.setSelectedComponent(dbPane);
            initTabComponent(jTabbedPane1.getSelectedIndex());
        }

    }

    private void lanceEditeurSQL() {
        EditorSQL dbPane = new EditorSQL(dbListObj);
        jTabbedPane1.add(properties.getProperty("sqlEditorTitle"), dbPane);
        jTabbedPane1.setSelectedComponent(dbPane);
        initTabComponent(jTabbedPane1.getSelectedIndex());
    }

    private void lanceGestTaches() {
        PlanifTachesPanel dbPane = new PlanifTachesPanel(this, em);
        jTabbedPane1.add(properties.getProperty("scheduleTasksTitle"), dbPane);
        jTabbedPane1.setSelectedComponent(dbPane);
        initTabComponent(jTabbedPane1.getSelectedIndex());
    }

    public static void initTabComponent(int i) {
        theJTabbedPan.setTabComponentAt(i,
                new ButtonTabComponent(theJTabbedPan));
    }

    public void ExitQuestion() {

        Object[] options = {properties.getProperty("yesButton"),
            properties.getProperty("noButton")};
        int response = JOptionPane.showOptionDialog(this, properties.getProperty("exitQuestion"),
                properties.getProperty("exitTitle"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[1]);

        if (response == 0) {

            Andjety.exit();
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

        jSeparator8 = new javax.swing.JSeparator();
        jXTaskPaneContainer1 = new org.jdesktop.swingx.JXTaskPaneContainer();
        jLabel1 = new javax.swing.JLabel();
        jXTaskPane1 = new org.jdesktop.swingx.JXTaskPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jXList1 = new org.jdesktop.swingx.JXList();
        jButtonExecTask = new javax.swing.JButton();
        jXTaskPane2 = new org.jdesktop.swingx.JXTaskPane();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButtonGestDatabases = new javax.swing.JButton();
        jButtonEditeurSQL = new javax.swing.JButton();
        jButtonGestTaches = new javax.swing.JButton();
        jButtonGestTachesPlanif = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jToolBar1 = new javax.swing.JToolBar();
        jButtonAccueilToolbar = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButton5 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButtonBarGestionTaches = new javax.swing.JButton();
        jButtonBarGestBases = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JToolBar.Separator();
        jButtonBarOptions = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        jButtonHelpButton = new javax.swing.JButton();
        jButtonBarAbout = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButtonToolbarQuitter = new javax.swing.JButton();
        jXStatusBar1 = new org.jdesktop.swingx.JXStatusBar();
        jXLabelStatusBar = new org.jdesktop.swingx.JXLabel();
        jProgressBarUpdate = new javax.swing.JProgressBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuAssistant = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jMenuItemEditeurSQL = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuQuitter = new javax.swing.JMenuItem();
        jMenuEdit = new javax.swing.JMenu();
        jMenuItemOptions = new javax.swing.JMenuItem();
        jMenuTools = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setBackground(new java.awt.Color(162, 162, 244));
        jLabel1.setFont(new java.awt.Font("Showcard Gothic", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/newheader.png"))); // NOI18N
        jLabel1.setOpaque(true);
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jXTaskPaneContainer1.add(jLabel1);

        jXTaskPane1.setTitle("Tâches");

        jXList1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jXList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TacheDlbeClick(evt);
            }
        });
        jScrollPane1.setViewportView(jXList1);

        jButtonExecTask.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/tick.png"))); // NOI18N
        jButtonExecTask.setText("Executer la tâche");
        jButtonExecTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExecTaskActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonExecTask, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonExecTask))
        );

        jXTaskPane1.getContentPane().add(jPanel3);

        jXTaskPaneContainer1.add(jXTaskPane1);

        jXTaskPane2.setTitle("Menu");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/gnome_document_new.png"))); // NOI18N
        jButton3.setText("Nouvelle tâche");
        jButton3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButtonGestDatabases.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/gnome_network_server.png"))); // NOI18N
        jButtonGestDatabases.setText("Bases de données");
        jButtonGestDatabases.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonGestDatabases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGestDatabasesActionPerformed(evt);
            }
        });

        jButtonEditeurSQL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Data_SQL_1.png"))); // NOI18N
        jButtonEditeurSQL.setText("Editeur SQL");
        jButtonEditeurSQL.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonEditeurSQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditeurSQLActionPerformed(evt);
            }
        });

        jButtonGestTaches.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gnome_task_due.png"))); // NOI18N
        jButtonGestTaches.setText("Gestion des tâches");
        jButtonGestTaches.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonGestTaches.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGestTachesActionPerformed(evt);
            }
        });

        jButtonGestTachesPlanif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gnome_document_open_recent.png"))); // NOI18N
        jButtonGestTachesPlanif.setText("Tâches planifiées");
        jButtonGestTachesPlanif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonGestTachesPlanif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGestTachesPlanifActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonGestDatabases, javax.swing.GroupLayout.PREFERRED_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(jButtonEditeurSQL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(jButtonGestTaches, javax.swing.GroupLayout.PREFERRED_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(jButtonGestTachesPlanif, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jButtonGestDatabases)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEditeurSQL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGestTaches)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGestTachesPlanif)
                .addContainerGap())
        );

        jXTaskPane2.getContentPane().add(jPanel2);

        jXTaskPaneContainer1.add(jXTaskPane2);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButtonAccueilToolbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/AndjetyButton.png"))); // NOI18N
        jButtonAccueilToolbar.setToolTipText("Accueil");
        jButtonAccueilToolbar.setFocusable(false);
        jButtonAccueilToolbar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonAccueilToolbar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonAccueilToolbar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAccueilToolbarActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonAccueilToolbar);
        jToolBar1.add(jSeparator4);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/gnome_window_new.png"))); // NOI18N
        jButton5.setToolTipText("Nouvelle tâche");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);
        jToolBar1.add(jSeparator2);

        jButtonBarGestionTaches.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/gnome_task_due.png"))); // NOI18N
        jButtonBarGestionTaches.setToolTipText("Gestion des tâches");
        jButtonBarGestionTaches.setFocusable(false);
        jButtonBarGestionTaches.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonBarGestionTaches.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonBarGestionTaches.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBarGestionTachesActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonBarGestionTaches);

        jButtonBarGestBases.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/gnome_network_server.png"))); // NOI18N
        jButtonBarGestBases.setToolTipText("Gestion des bases de données");
        jButtonBarGestBases.setFocusable(false);
        jButtonBarGestBases.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonBarGestBases.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonBarGestBases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBarGestBasesActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonBarGestBases);
        jToolBar1.add(jSeparator9);

        jButtonBarOptions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/gnome_preferences_other.png"))); // NOI18N
        jButtonBarOptions.setToolTipText("Options");
        jButtonBarOptions.setFocusable(false);
        jButtonBarOptions.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonBarOptions.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonBarOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBarOptionsActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonBarOptions);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/gnome_system_software_update.png"))); // NOI18N
        jButton7.setToolTipText("Vérifier les mises à jour");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton7);
        jToolBar1.add(jSeparator6);

        jButtonHelpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/help.png"))); // NOI18N
        jButtonHelpButton.setToolTipText("Aide");
        jButtonHelpButton.setFocusable(false);
        jButtonHelpButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonHelpButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonHelpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHelpButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonHelpButton);

        jButtonBarAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gnome_dialog_information.png"))); // NOI18N
        jButtonBarAbout.setToolTipText("A Propos De...");
        jButtonBarAbout.setFocusable(false);
        jButtonBarAbout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonBarAbout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonBarAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBarAboutActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonBarAbout);
        jToolBar1.add(jSeparator3);

        jButtonToolbarQuitter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/application-exit-2.png"))); // NOI18N
        jButtonToolbarQuitter.setToolTipText("Quitter Andjety");
        jButtonToolbarQuitter.setFocusable(false);
        jButtonToolbarQuitter.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonToolbarQuitter.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonToolbarQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonToolbarQuitterActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonToolbarQuitter);

        jXLabelStatusBar.setText("jXLabel1");
        jXStatusBar1.add(jXLabelStatusBar);

        jProgressBarUpdate.setStringPainted(true);
        jXStatusBar1.add(jProgressBarUpdate);

        jMenuFile.setText("Fichier");

        jMenuAssistant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/gnome_document_new_small.png"))); // NOI18N
        jMenuAssistant.setText("Nouvelle tâche");
        jMenuAssistant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAssistantActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuAssistant);
        jMenuFile.add(jSeparator10);

        jMenuItemEditeurSQL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Data_SQL_1_small.png"))); // NOI18N
        jMenuItemEditeurSQL.setText("Editeur SQL");
        jMenuItemEditeurSQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEditeurSQLActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemEditeurSQL);
        jMenuFile.add(jSeparator5);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/gnome_document_open_recent_small.png"))); // NOI18N
        jMenuItem5.setText("Tâches planifiées");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItem5);
        jMenuFile.add(jSeparator1);

        jMenuQuitter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/door_out.png"))); // NOI18N
        jMenuQuitter.setText("Quitter");
        jMenuQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuQuitterActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuQuitter);

        jMenuBar1.add(jMenuFile);

        jMenuEdit.setText("Edition");

        jMenuItemOptions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/gnome_preferences_other_small.png"))); // NOI18N
        jMenuItemOptions.setText("Options");
        jMenuItemOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOptionsActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemOptions);

        jMenuBar1.add(jMenuEdit);

        jMenuTools.setText("Outils");

        jMenuItem1.setText("Purger historique des tâches");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuTools.add(jMenuItem1);

        jMenuItem2.setText("Supprimer les bases de données");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenuTools.add(jMenuItem2);

        jMenuBar1.add(jMenuTools);

        jMenuHelp.setText("Aide");

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/gnome_system_software_update_small.png"))); // NOI18N
        jMenuItem6.setText("Mises à jour");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItem6);
        jMenuHelp.add(jSeparator11);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/Andjety_minismall.png"))); // NOI18N
        jMenuItem3.setText("A Propos de Andjety");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItem3);

        jMenuBar1.add(jMenuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jXTaskPaneContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE))
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jXStatusBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXTaskPaneContainer1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXStatusBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuQuitterActionPerformed
        ExitQuestion();
    }//GEN-LAST:event_jMenuQuitterActionPerformed

    private void jMenuItemOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOptionsActionPerformed
        lanceOptions();
    }//GEN-LAST:event_jMenuItemOptionsActionPerformed

    private void jMenuAssistantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAssistantActionPerformed

        lanceAssitant();
    }//GEN-LAST:event_jMenuAssistantActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Assistant1 assitantFen = new Assistant1(this, rootPaneCheckingEnabled, "", "", "", dbListObj, this);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        assitantFen.setLocation(
                (screenSize.width - assitantFen.getWidth()) / 2,
                (screenSize.height - assitantFen.getHeight()) / 2);

        assitantFen.setTitle(properties.getProperty("wizardTitle"));
        assitantFen.show();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButtonToolbarQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonToolbarQuitterActionPerformed
        ExitQuestion();
    }//GEN-LAST:event_jButtonToolbarQuitterActionPerformed

    private void jButtonBarAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBarAboutActionPerformed

        AboutDialog assitantFen = new AboutDialog(this, rootPaneCheckingEnabled);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        assitantFen.setLocation(
                (screenSize.width - assitantFen.getWidth()) / 2,
                (screenSize.height - assitantFen.getHeight()) / 2);

        assitantFen.setTitle(properties.getProperty("aboutTitle"));
        assitantFen.show();
    }//GEN-LAST:event_jButtonBarAboutActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        lanceAssitant();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButtonBarOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBarOptionsActionPerformed
        lanceOptions();
    }//GEN-LAST:event_jButtonBarOptionsActionPerformed

    private void jButtonGestDatabasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGestDatabasesActionPerformed
        lanceGestBaseDeDonnees();
    }//GEN-LAST:event_jButtonGestDatabasesActionPerformed

    private void jButtonEditeurSQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditeurSQLActionPerformed
        lanceEditeurSQL();
    }//GEN-LAST:event_jButtonEditeurSQLActionPerformed

    private void jMenuItemEditeurSQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditeurSQLActionPerformed
        lanceEditeurSQL();
    }//GEN-LAST:event_jMenuItemEditeurSQLActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        AboutDialog assitantFen = new AboutDialog(this, rootPaneCheckingEnabled);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        assitantFen.setLocation(
                (screenSize.width - assitantFen.getWidth()) / 2,
                (screenSize.height - assitantFen.getHeight()) / 2);

        assitantFen.setTitle(properties.getProperty("aboutTitle"));
        assitantFen.show();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButtonBarGestBasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBarGestBasesActionPerformed
        lanceGestBaseDeDonnees();
    }//GEN-LAST:event_jButtonBarGestBasesActionPerformed

    private void jButtonBarGestionTachesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBarGestionTachesActionPerformed
        lanceGestionTaches();
    }//GEN-LAST:event_jButtonBarGestionTachesActionPerformed

    private void jButtonGestTachesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGestTachesActionPerformed
        lanceGestionTaches();
    }//GEN-LAST:event_jButtonGestTachesActionPerformed

    public void insertTaskExec(TasksexecObj newTasks) {
        /*
         * taskExecListObj = new TaskExecListe(); ArrayList<TasksexecObj> array
         * = new ArrayList<TasksexecObj>(); array.add(newTasks);
         */
        taskExecListObj.getListOfTasksExec().add(newTasks);
        serialiseTasksExecTOXML();
    }

    public void afficheExcelResultat(String nomTasks, List<String> colonnes, List<ArrayList<String>> lignes, File oChemin) {

        //File oChemin = new File(fichier);
        ExcelSheetWriter.main(null, oChemin, colonnes, lignes);
        //System.out.println("Chemin du fichier à ouvrir : " + oChemin);
        ExcelViewJPanel dbPane = new ExcelViewJPanel(oChemin);
        jTabbedPane1.add(properties.getProperty("resultTitle") + nomTasks, dbPane);
        jTabbedPane1.setSelectedComponent(dbPane);
        initTabComponent(jTabbedPane1.getSelectedIndex());

    }

    public void afficheLogPanel(LogObj leLog) {

        LogPanel lgPane = new LogPanel(leLog);
        jTabbedPane1.add("Log " + leLog.getLogobj_taskLibelle(), lgPane);
        jTabbedPane1.setSelectedComponent(lgPane);
        initTabComponent(jTabbedPane1.getSelectedIndex());

    }

    public void loadTaskMotor(TaskObj t) {
        TaskMotor dbPane = new TaskMotor(t, this, null);
        dbPane.laTache = t;
        jTabbedPane1.add(t.getTASK_LIBELLE(), dbPane);
        jTabbedPane1.setSelectedComponent(dbPane);
        initTabComponent(jTabbedPane1.getSelectedIndex());
    }

    public static void loadTaskMotorThread(final String t, final String ScheduleTaskId) {
        /*
         * //final MainFen thisInstance = this;
         * java.awt.EventQueue.invokeLater(new Runnable() {
         *
         * @Override public void run() {
         *
         *
         * // On va rechercher la tache DBUtils dbu = new DBUtils(); Tasks
         * laTache = dbu.getTaskWithEm(t, em); System.out.println("Nom de la
         * tache à lancer : " + laTache);
         *
         * Scheduletasks sct = dbu.getScheduleTaskById(ScheduleTaskId);
         * System.out.println("Sct ID : " + sct.getId());
         *
         * TaskMotor dbPane = new TaskMotor(laTache, laFenetre, sct);
         * dbPane.laTache = laTache;
         *
         * // Si la tache doit-être affichée if
         * (sct.getAffichexec().equals("true")) {
         * theJTabbedPan.add(laTache.getTaskLibelle(), dbPane);
         * theJTabbedPan.setSelectedComponent(dbPane);
         * initTabComponent(theJTabbedPan.getSelectedIndex()); }
         * dbPane.lanceControlesFromOtherForm(); } });
         *
         */
    }

    private void jButtonExecTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExecTaskActionPerformed

        if (jXList1.getSelectedIndex() > -1) {
            // Ajout dans l'autre jList

            int[] selectedIx = jXList1.getSelectedIndices();

            for (int i = 0; i < selectedIx.length; i++) {
                Object sel = jXList1.getModel().getElementAt(selectedIx[i]);

                for (TaskObj tsk : taskListObj.getDbObjListe()) {
                    if (tsk.getTASK_LIBELLE().equals(sel.toString())) {
                        loadTaskMotor(tsk);
                        break;
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, properties.getProperty("messageSelectTask"), "Andjety", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButtonExecTaskActionPerformed

    private void jButtonGestTachesPlanifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGestTachesPlanifActionPerformed
        lanceGestTaches();
    }//GEN-LAST:event_jButtonGestTachesPlanifActionPerformed

    private void TacheDlbeClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TacheDlbeClick
        /*
         * if (evt.getClickCount() == 2) { int index =
         * jXList1.locationToIndex(evt.getPoint()); ListModel dlm =
         * jXList1.getModel(); Object item = dlm.getElementAt(index);
         * jXList1.ensureIndexIsVisible(index); System.out.println("Double
         * clicked on " + item);
         *
         * DBUtils dbu = new DBUtils(); Tasks t = dbu.getTask(item.toString());
         *
         * loadTaskMotor(t); }
         */
    }//GEN-LAST:event_TacheDlbeClick

    public DatabaseListeObj getDatabaseList() {
        return dbListObj;
    }

    public void deleteTaskObj(TaskObj tskObj) {
        taskListObj.getDbObjListe().remove(tskObj);
        serialiseTasksTOXML();
        updateObservateur();
    }

    public void addScheduleTaskToPanel(ScheduleTaskObj laTache) {

        // On ajoute ou on modifie l'objet dans son tableau
        boolean trouve = false;
        for (ScheduleTaskObj tsk : scheduleTasksListObj.getListeOfScheduleTasks()) {
            if (tsk.equals(laTache)) {
                trouve = true;
                tsk = laTache;
            }
        }

        ArrayList<ScheduleTaskObj> testOfListe = new ArrayList<>();
        if (trouve == false) {
            //scheduleTasksListObj.getListeOfScheduleTasks().add(laTache);
            testOfListe.add(laTache);
            scheduleTasksListObj = new ScheduleTasksListeObj();
            scheduleTasksListObj.setListeOfScheduleTasks(testOfListe);
        }
        serialiseTasksTOXML();

    }

    public void deleteServersToArray(DatabaseObj dbObj) {
        for (DatabaseObj dbo : dbListObj.getDbListe()) {
            //System.out.println("Objet : " + dbo + " - Compare TO : " + dbObj);
            if (dbo.equals(dbObj)) {
                // Suppression de l'objet
                dbListObj.getDbListe().remove(dbo);
                break;
            }
        }
        serialiseServersTOXML();
    }

    public DatabaseListeObj getDBList() {
        return dbListObj;
    }

    public void saveTaskToTasksList(TaskObj laNouvelleTache) {
        // On ajoute ou on modifie l'objet dans son tableau
        boolean trouve = false;
        for (TaskObj tsk : taskListObj.getDbObjListe()) {
            if (tsk.equals(laNouvelleTache)) {
                trouve = true;
                tsk = laNouvelleTache;
            }
        }

        ArrayList<TaskObj> testOfListe = new ArrayList<>();
        if (trouve == false) {
            taskListObj.getDbObjListe().add(laNouvelleTache);
            //testOfListe.add(laNouvelleTache);
            //taskListObj = new TaskObjListe();
            //taskListObj.setDbObjListe(testOfListe);
        }
        serialiseTasksTOXML();
        updateObservateur();
    }

    public void saveServersToXML(DatabaseObj dbObj) {
        // On regarde si l'objet existe, si ou on le remplace
        boolean ifTrouve = false;
        for (DatabaseObj dbo : dbListObj.getDbListe()) {
            //System.out.println("Objet : " + dbo + " - Compare TO : " + dbObj);
            if (dbo.equals(dbObj)) {
                dbo = dbObj;
                ifTrouve = true;
            }
        }

        if (ifTrouve == false) {
            dbListObj.getDbListe().add(dbObj);
        }

        // On test si le serveur est inclu dans un tache, si oui on le modifie
        // Pour chaque tache
        for (TaskObj tsk : taskListObj.getDbObjListe()) {
            // Pour chaque base de données de chaque tache
            for (DatabaseObj dbo : tsk.getTASK_SERVERSLIST()) {
                if (dbo.getDblist_libelle().equals(dbObj.getDblist_libelle())) {
                    tsk.getTASK_SERVERSLIST().remove(dbo);
                    tsk.getTASK_SERVERSLIST().add(dbObj);
                    //System.out.println("Serveur trouvé et remplacé !");
                    break;

                }
            }
        }

        serialiseTasksTOXML();
        serialiseServersTOXML();
    }

    public boolean saveNewServersToXML(DatabaseObj dbObj) {
        // On regarde si l'objet existe, si ou on le remplace
        boolean ifTrouve = false;
        for (DatabaseObj dbo : dbListObj.getDbListe()) {
            //System.out.println("Objet : " + dbo + " - Compare TO : " + dbObj);
            if (dbo.getDblist_libelle().equals(dbObj.getDblist_libelle())) {
                dbo = dbObj;
                ifTrouve = true;
            }
        }

        if (ifTrouve == false) {
            dbListObj.getDbListe().add(dbObj);
            serialiseServersTOXML();
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Attention : une base de données porte déjà ce libellé. Veuillez le modifier.", "Andjety", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    public void saveServersAndTestToXML(DatabaseObj dbObj) {
        // On regarde si l'objet existe, si ou on le remplace
        boolean ifTrouve = false;
        int a = 0;
        for (DatabaseObj dbo : dbListObj.getDbListe()) {
            //System.out.println("Objet : " + dbo + " - Compare TO : " + dbObj);
            if (dbo.getDblist_libelle().equals(dbObj.getDblist_libelle())) {
                //dbo = dbObj;
                ifTrouve = true;
                dbListObj.getDbListe().remove(a);
                //System.out.println("Supprimer element. Count tab : " + dbListObj.getDbListe().size());
                dbListObj.getDbListe().add(dbObj);
                break;
            }
            a++;
        }

        if (ifTrouve == false) {
            dbListObj.getDbListe().add(dbObj);
            //System.out.println("Ajout de l'élément");
        }
    }

    public void serialiseTasksExecTOXML() {
        try {
            // Sérialisation des tables & attributs du projet
            String pathOfProject = System.getProperty("user.dir");
            if (OSValidator.isUnix()) {
                XMLTools.encodeToFile(taskExecListObj, "/usr/lib/Files/Andjety_tasksexec.xml");
            } else {
                XMLTools.encodeToFile(taskExecListObj, pathOfProject + "/Files/Andjety_tasksexec.xml");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void serialiseTasksTOXML() {
        try {
            // Sérialisation des tables & attributs du projet
            String pathOfProject = System.getProperty("user.dir");
            /*if (OSValidator.isUnix()) {
             XMLTools.encodeToFile(taskListObj, "/usr/lib/Files/Andjety_tasks.xml");
             } else {
             XMLTools.encodeToFile(taskListObj, pathOfProject + "/Files/Andjety_tasks.xml");
             }*/
            XMLTools.encodeToFile(taskListObj, pathOfProject + "/Files/Andjety_tasks.xml");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void serialiseServersTOXML() {
        try {
            // Sérialisation des tables & attributs du projet
            String pathOfProject = System.getProperty("user.dir");
            /*if (OSValidator.isUnix()) {
             XMLTools.encodeToFile(dbListObj, "/usr/lib/Andjety/Files/Andjety_servers.xml");
             } else {
             XMLTools.encodeToFile(dbListObj, pathOfProject + "/Files/Andjety_servers.xml");
             }*/
            XMLTools.encodeToFile(dbListObj, pathOfProject + "/Files/Andjety_servers.xml");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void serialiseTasksLogsTOXML() {
        try {
            // Sérialisation des tables & attributs du projet
            String pathOfProject = System.getProperty("user.dir");
            if (OSValidator.isUnix()) {
                XMLTools.encodeToFile(listOfLogs, "/usr/lib/Andjety/Files/Andjety_tasks_logs.xml");
            } else {
                XMLTools.encodeToFile(listOfLogs, pathOfProject + "/Files/Andjety_tasks_logs.xml");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addTaskLog(LogObj newLog) {

        listOfLogs.getListOfLogsOb().add(newLog);
        serialiseTasksLogsTOXML();
    }

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        lanceGestTaches();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jButtonAccueilToolbarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAccueilToolbarActionPerformed
        affichePanelAccueil();
    }//GEN-LAST:event_jButtonAccueilToolbarActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        MajAction();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButtonHelpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHelpButtonActionPerformed

        OptionsParser optionsParse = new OptionsParser();
        optionsParse.parseXml();
        String languageValue = optionsParse.languageValue;
        InputStream stream = null;

        String executionPath = System.getProperty("user.dir");
        String UrlToHelpFilesEn;
        String UrlToHelpFilesFr;

        if (OSValidator.isWindows()) {
            UrlToHelpFilesEn = "file:///" + executionPath.replace("\\", "/") + "/Help/index_en.html";
            UrlToHelpFilesFr = "file:///" + executionPath.replace("\\", "/") + "/Help/index_fr.html";
            //JOptionPane.showMessageDialog(this, "UrlToHelpFilesFr : " + UrlToHelpFilesFr);
        } else {
            UrlToHelpFilesEn = "file:///usr/lib/Andjety/Help/index_en.html";
            UrlToHelpFilesFr = "file:///usr/lib/Andjety/Help/index_fr.html";
        }

        if (languageValue.toUpperCase().equals("ENGLISH")) {
            if (Desktop.isDesktopSupported()) {
                if (Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        try {
                            Desktop.getDesktop().browse(new URI(UrlToHelpFilesEn));
                        } catch (URISyntaxException ex) {
                            Exceptions.printStackTrace(ex);
                        }
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            }
        } else if (languageValue.toUpperCase().contains("FRA")) {
            if (Desktop.isDesktopSupported()) {
                if (Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        try {
                            Desktop.getDesktop().browse(new URI(UrlToHelpFilesFr));

                        } catch (URISyntaxException ex) {
                            Exceptions.printStackTrace(ex);
                        }
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            }
        } else {
            if (Desktop.isDesktopSupported()) {
                if (Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        try {
                            Desktop.getDesktop().browse(new URI(UrlToHelpFilesEn));
                        } catch (URISyntaxException ex) {
                            Exceptions.printStackTrace(ex);
                        }
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            }
        }

    }//GEN-LAST:event_jButtonHelpButtonActionPerformed

    private void MajAction() {
        FIndMajEngine fmw = new FIndMajEngine();

        fmw.appVersion = Constants.appVersion;
        fmw.mf = this;
        fmw.ifShowMessage = true;

        Thread thk = new Thread(fmw);
        thk.start();
    }
    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        MajAction();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //Custom button text
        Object[] options = {properties.getProperty("yesButton"),
            properties.getProperty("noButton")};
        int response = JOptionPane.showOptionDialog(null, "Purger l'historique des tâches ?",
                "Andjety",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[1]);

        if (response == 0) {

            ArrayList<TasksexecObj> newList = new ArrayList<>();
            taskExecListObj.setListOfTasksExec(newList);
            serialiseTasksExecTOXML();
            updateObservateur();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Object[] options = {properties.getProperty("yesButton"), properties.getProperty("noButton")};
        int response = JOptionPane.showOptionDialog(this, properties.getProperty("question_clear_database"),
                properties.getProperty("title_question"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[1]);

        if (response == 0) {
            purgeDatabaseArray();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MainFen().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButtonAccueilToolbar;
    private javax.swing.JButton jButtonBarAbout;
    private javax.swing.JButton jButtonBarGestBases;
    private javax.swing.JButton jButtonBarGestionTaches;
    private javax.swing.JButton jButtonBarOptions;
    private javax.swing.JButton jButtonEditeurSQL;
    private javax.swing.JButton jButtonExecTask;
    private javax.swing.JButton jButtonGestDatabases;
    private javax.swing.JButton jButtonGestTaches;
    private javax.swing.JButton jButtonGestTachesPlanif;
    private javax.swing.JButton jButtonHelpButton;
    private javax.swing.JButton jButtonToolbarQuitter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuAssistant;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItemEditeurSQL;
    private javax.swing.JMenuItem jMenuItemOptions;
    private javax.swing.JMenuItem jMenuQuitter;
    private javax.swing.JMenu jMenuTools;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBarUpdate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JToolBar.Separator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private org.jdesktop.swingx.JXLabel jXLabelStatusBar;
    private org.jdesktop.swingx.JXList jXList1;
    private org.jdesktop.swingx.JXStatusBar jXStatusBar1;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane1;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane2;
    private org.jdesktop.swingx.JXTaskPaneContainer jXTaskPaneContainer1;
    // End of variables declaration//GEN-END:variables

    /**
     * Retire tous les observateurs de la liste
     */
    @Override
    public void delObservateur() {
        this.listObservateur = new ArrayList<>();
    }

    /**
     * Avertit les observateurs que l'observable a changé et invoque la méthode
     * update de chaque observateur !
     */
    @Override
    public void updateObservateur() {
        for (MainFenObervateur obs : this.listObservateur) {
            // System.out.print("Count threadArrayListe : " + threadArrayListe.size());
            obs.update(threadArrayListe);
        }
    }

    @Override
    public void addObservateur(MainFenObervateur obs) {
        this.listObservateur.add(obs);
    }

    // MAJ System
    public void getLatestRelease() {
        try {
            // Function called
            long startTime = System.currentTimeMillis();

            // Open connection
            System.out.println("Connecting...");
            URL url = new URL("http://www.saphirsoftware.com/softs/");
            url.openConnection();

            try ( // Download routine
                    InputStream reader = url.openStream();
                    FileOutputStream writer = new FileOutputStream("Andjety_setup.exe")) {

                byte[] buffer = new byte[153600];
                int totalBytesRead = 0;
                int bytesRead = 0;

                while ((bytesRead = reader.read(buffer)) > 0) {
                    writer.write(buffer, 0, bytesRead);
                    buffer = new byte[153600];
                    totalBytesRead += bytesRead;
                }

                // Download finished
                long endTime = System.currentTimeMillis();

            }
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

    }
    // Here I catch MalformedURLException and IOException :)

    private void setSystemTrayMenu() {
//System Tray Implémentation
        trayIcon = null;
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("ressources/AndjetyTray.png"));
            ActionListener listener = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == systrayQuitterItem) {
                        ExitQuestion();
                    }
                }
            };

            PopupMenu popup = new PopupMenu();

            systrayQuitterItem = new MenuItem("Quitter");
            systrayQuitterItem.addActionListener(listener);
            popup.add(systrayQuitterItem);

            trayIcon = new TrayIcon(image, "Andjety", popup);
            trayIcon.addActionListener(listener);

            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println(e);
            }
        } // Fin System Tray Implémentation>F
    }

    private void setLanguage() {
        // Définition de la langue
        OptionsParser optionsParse = new OptionsParser();
        optionsParse.parseXml();

        String languageValue = optionsParse.languageValue;
        InputStream stream;

        if (languageValue.toUpperCase().equals("ENGLISH")) {
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("languages/english.properties");
            jMenuFile.setText("File");
            jMenuAssistant.setText("New Task");
            jMenuItemEditeurSQL.setText("SQL Editor");
            jMenuItem5.setText("Schedule Tasks");
            jMenuQuitter.setText("Exit");
            jMenuEdit.setText("Edit");
            jMenuItemOptions.setText("Settings");
            jMenuHelp.setText("Help");
            jMenuItem3.setText("About Andjety");
            jMenuItem6.setText("Check Updates");
            jXTaskPane1.setTitle("Tasks");
            jButtonExecTask.setText("Run task");
            jButton3.setText("New Task");
            jButtonGestDatabases.setText("Databases");
            jButtonEditeurSQL.setText("SQL Editor");
            jButtonGestTachesPlanif.setText("Schedule Tasks");
            jButtonGestTaches.setText("Tasks");
            jButtonAccueilToolbar.setToolTipText("Welcome");
            jButton5.setToolTipText("New Task");
            //jButtonBarEditeurSQL.setToolTipText("SQL Editor");
            jButtonBarGestionTaches.setToolTipText("Tasks");
            jButtonBarGestBases.setToolTipText("Databases");
            jButtonBarOptions.setToolTipText("Settings");
            jButtonBarAbout.setToolTipText("About Andjety");
            jButtonToolbarQuitter.setToolTipText("Quit Andjety");
            jButtonHelpButton.setToolTipText("Help");

        } else if (languageValue.toUpperCase().contains("FRA")) {
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("languages/french.properties");
        } else {
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("languages/english.properties");
        }

        //System.out.println("Current Language : " + languageValue);
        try {
            properties.load(stream);
            //System.out.println("Valeur : " + properties.getProperty("s1"));
        } catch (IOException e) {
            System.out.println(properties.getProperty("errorOpenFile"));
        }

        repaint();
    }
}
