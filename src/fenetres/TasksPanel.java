/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package fenetres;

import Threads.TaskMotorThread;
import Utils.OptionsParser;
import andjety.MainFen;
import assistant.Assistant1;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import jsyntaxpane.DefaultSyntaxKit;
import models.DatabasesTableModelWithFilter;
import models.TasksTableModel;
import objects.TaskObj;
import objects.TaskObjListe;
import observateurs.MainFenObervateur;

/**
 *
 * @author Paul Coiffier
 */
public class TasksPanel extends javax.swing.JPanel implements KeyListener {

    private MainFen mainFenInstance;
    private TaskObjListe taskListObj;
    private Properties properties = new Properties();

    public TasksPanel(MainFen m, final TaskObjListe taskListObj) {
        mainFenInstance = m;
        //em = emEntity;
        this.taskListObj = taskListObj;
        initComponents();
        LoadData();

        m.addObservateur(new MainFenObervateur() {

            @Override
            public void update(List<TaskMotorThread> liste) {
                LoadData();
            }
        });
        // Définition de la langue
        OptionsParser optionsParse = new OptionsParser();
        optionsParse.parseXml();
        String languageValue = optionsParse.languageValue;
        InputStream stream = null;

        if (languageValue.toUpperCase().equals("ENGLISH")) {
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("languages/english.properties");
        } else if (languageValue.toUpperCase().contains("FRAN")) {
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("languages/french.properties");
        } else {
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("languages/english.properties");
        }

        try {
            properties.load(stream);
        } catch (IOException e) {
            System.out.println(properties.getProperty("errorOpenFile"));
        }

        jLabel1.setText(properties.getProperty("taskTitle"));
        jButtonAddDatabase.setToolTipText(properties.getProperty("TasksPanelToolTip1"));
        jButtonEditer.setToolTipText(properties.getProperty("TasksPanelToolTip2"));
        jButtonSupprimer.setToolTipText(properties.getProperty("TasksPanelToolTip3"));
        jButtonExecuteTable.setToolTipText(properties.getProperty("TasksPanelToolTip4"));

        java.awt.Dimension scdreenSize = new Dimension(MainFen.theJTabbedPan.getSize().width / 2, 100);
        jScrollPane2.setMinimumSize(scdreenSize);
        jScrollPane2.setPreferredSize(scdreenSize);

        jXTable1.addKeyListener(this);

        DefaultSyntaxKit.initKit();
        jEditorPane1.setContentType("text/sql");

        jXTable1.addMouseListener(new MouseAdapter() {

            int row;

            @Override
            public void mouseClicked(MouseEvent e) {

                //DBUtils dbu = new DBUtils();
                //Tasks laTache = dbu.getTask(jXTable1.getValueAt(jXTable1.getSelectedRow(), 0).toString());
                jEditorPane1.setText(taskListObj.getDbObjListe().get(jXTable1.getSelectedRow()).getTASK_REQUETE());

                try {
                    // Génération de la liste des serveurs
                    jXTable2.setModel(new DatabasesTableModelWithFilter(taskListObj.getDbObjListe().get(jXTable1.getSelectedRow()).getTASK_SERVERSLIST()));
                    //
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

                } catch (Exception ex) {
                    Logger.getLogger(DatabasesPanel.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    JTable source = (JTable) e.getSource();

                    int row = source.rowAtPoint(e.getPoint());
                    int column = source.columnAtPoint(e.getPoint());

                    if (row >= 0) {
                        if (!source.isRowSelected(row)) {
                            source.changeSelection(row, column, false, false);
                        }
                        jPopupMenu1.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }
        });
    }

    public void mainFenReloadTasks() {
        mainFenInstance.LoadTasksDataList();
    }

    public final void LoadData() {

        new Thread(new Runnable() {

            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        //System.out.println("LoadData");
                        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));


                        try {
                            jXTable1.setModel(new TasksTableModel(taskListObj));

                        } catch (Exception ex) {
                            Logger.getLogger(DatabasesPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        mainFenInstance.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        jXTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                        ListSelectionModel tableSelectionModel = jXTable1.getSelectionModel();
                        tableSelectionModel.setSelectionInterval(0, 0);

                    }
                ;
            }
        );
    }

    }).start();
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItemAjouter = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItemEdit = new javax.swing.JMenuItem();
        jMenuItemDelete = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItemExecute = new javax.swing.JMenuItem();
        jMenuItemListeExecution = new javax.swing.JMenuItem();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable();
        jSplitPane2 = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jXTable2 = new org.jdesktop.swingx.JXTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jButtonAddDatabase = new javax.swing.JButton();
        jButtonEditer = new javax.swing.JButton();
        jButtonSupprimer = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButtonExecuteTable = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();

        jMenuItemAjouter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/database_add.png"))); // NOI18N
        jMenuItemAjouter.setText("Créer une tâche");
        jMenuItemAjouter.setToolTipText("Créer une tâche");
        jMenuItemAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAjouterActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemAjouter);
        jPopupMenu1.add(jSeparator3);

        jMenuItemEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/database_edit.png"))); // NOI18N
        jMenuItemEdit.setText("Editer la tâche");
        jMenuItemEdit.setToolTipText("Editer la tâche");
        jMenuItemEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEditActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemEdit);

        jMenuItemDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/database_delete.png"))); // NOI18N
        jMenuItemDelete.setText("Supprimer la tâche");
        jMenuItemDelete.setToolTipText("Supprimer la tâche");
        jMenuItemDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDeleteActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemDelete);
        jPopupMenu1.add(jSeparator4);

        jMenuItemExecute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/database_gear.png"))); // NOI18N
        jMenuItemExecute.setText("Executer la tâche");
        jMenuItemExecute.setToolTipText("Executer la tâche");
        jMenuItemExecute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExecuteActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemExecute);

        jMenuItemListeExecution.setText("Liste des exécutions");
        jMenuItemListeExecution.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItemListeExecutionMouseClicked(evt);
            }
        });
        jMenuItemListeExecution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemListeExecutionActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemListeExecution);

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(23, 300));

        jXTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jXTable1.setMinimumSize(new java.awt.Dimension(0, 100));
        jScrollPane1.setViewportView(jXTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 324, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jSplitPane1.setTopComponent(jPanel1);

        jEditorPane1.setEditable(false);
        jScrollPane2.setViewportView(jEditorPane1);

        jSplitPane2.setLeftComponent(jScrollPane2);

        jXTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jXTable2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );

        jSplitPane2.setRightComponent(jPanel3);

        jSplitPane1.setRightComponent(jSplitPane2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gnome_task_due.png"))); // NOI18N
        jLabel1.setText("Tâches");

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButtonAddDatabase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/database_add.png"))); // NOI18N
        jButtonAddDatabase.setToolTipText("Ajouter une tâche");
        jButtonAddDatabase.setFocusable(false);
        jButtonAddDatabase.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonAddDatabase.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonAddDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddDatabaseActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonAddDatabase);

        jButtonEditer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/database_edit.png"))); // NOI18N
        jButtonEditer.setToolTipText("Editer la base de données");
        jButtonEditer.setFocusable(false);
        jButtonEditer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEditer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonEditer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditerActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonEditer);

        jButtonSupprimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/database_delete.png"))); // NOI18N
        jButtonSupprimer.setToolTipText("Supprimer la base de données");
        jButtonSupprimer.setFocusable(false);
        jButtonSupprimer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSupprimer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSupprimerActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonSupprimer);
        jToolBar1.add(jSeparator1);

        jButtonExecuteTable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/database_gear.png"))); // NOI18N
        jButtonExecuteTable.setToolTipText("Executer la tâche");
        jButtonExecuteTable.setFocusable(false);
        jButtonExecuteTable.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonExecuteTable.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonExecuteTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExecuteTableActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonExecuteTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(702, 702, 702)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddDatabaseActionPerformed
        addAction();
    }//GEN-LAST:event_jButtonAddDatabaseActionPerformed

    private void addAction() {
        Assistant1 assitantFen = new Assistant1(mainFenInstance, true, null, null, null, mainFenInstance.getDBList(), mainFenInstance);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        assitantFen.setLocation(
                (screenSize.width - assitantFen.getWidth()) / 2,
                (screenSize.height - assitantFen.getHeight()) / 2);

        assitantFen.tksPanel = this;
        assitantFen.setTitle(properties.getProperty("wizardTitle"));
        assitantFen.show();

        assitantFen.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {
                //System.out.println("RELOAD");
                /*
                 * em = null; EntityManagerFactory emf =
                 * javax.persistence.Persistence.createEntityManagerFactory("AndjetyPU");
                 * em = emf.createEntityManager(); jXTable1.setModel(new
                 * TasksTableModel(em));
                 */
            }
        });
    }

    private void editerAction() {

        //DBUtils theDbUtils = new DBUtils();
        TaskObj theTasksToEdit = taskListObj.getDbObjListe().get(jXTable1.getSelectedRow());

        TasksEditor assitantFen = new TasksEditor(mainFenInstance,
                true, theTasksToEdit, mainFenInstance);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        assitantFen.maTache = theTasksToEdit;

        assitantFen.setLocation((screenSize.width - assitantFen.getWidth())
                / 2, (screenSize.height - assitantFen.getHeight()) / 2);

        assitantFen.setTitle(properties.getProperty("TasksPanelMessage1"));
        assitantFen.typeFen = "Editer";
        assitantFen.show();

        assitantFen.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {
                /*
                 * em = null; EntityManagerFactory emf =
                 * javax.persistence.Persistence.createEntityManagerFactory("AndjetyPU");
                 * em = emf.createEntityManager(); jXTable1.setModel(new
                 * TasksTableModel(em)); mainFenInstance.LoadTasksDataList();
                 */
            }
        });
    }

    private void jButtonEditerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditerActionPerformed
        editerAction();
    }//GEN-LAST:event_jButtonEditerActionPerformed

    private void jButtonSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSupprimerActionPerformed
        supprimerAction();
    }//GEN-LAST:event_jButtonSupprimerActionPerformed

    private void supprimerAction() {

        try {

            int selectedRow = jXTable1.getSelectedRow();
            //Custom button text
            Object[] options = {properties.getProperty("yesButton"),
                properties.getProperty("noButton")};
            int response = JOptionPane.showOptionDialog(null, properties.getProperty("TasksPanelMessage2"),
                    properties.getProperty("exitTitle"),
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);

            if (response == 0) {

                // On supprime l'objet
                TaskObj theTasksToDelete = taskListObj.getDbObjListe().get(jXTable1.getSelectedRow());
                mainFenInstance.deleteTaskObj(theTasksToDelete);
                taskListObj.getDbObjListe().remove(theTasksToDelete);

                jXTable1.setModel(new TasksTableModel(taskListObj));

                ListSelectionModel selectionModel =
                        jXTable1.getSelectionModel();
                selectionModel.setSelectionInterval(selectedRow - 1, selectedRow - 1);

                mainFenInstance.LoadTasksDataList();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, properties.getProperty("errorTitle") + " : " + e.getMessage().toString(),
                    properties.getProperty("exitTitle"),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void executeTask() {
        TaskObj theTasksToDelete = taskListObj.getDbObjListe().get(jXTable1.getSelectedRow());
        mainFenInstance.loadTaskMotor(theTasksToDelete);
    }

    private void jButtonExecuteTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExecuteTableActionPerformed
        executeTask();
    }//GEN-LAST:event_jButtonExecuteTableActionPerformed

    private void jMenuItemEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditActionPerformed
        editerAction();
    }//GEN-LAST:event_jMenuItemEditActionPerformed

    private void jMenuItemDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDeleteActionPerformed
        supprimerAction();
    }//GEN-LAST:event_jMenuItemDeleteActionPerformed

    private void jMenuItemExecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExecuteActionPerformed
        executeTask();
    }//GEN-LAST:event_jMenuItemExecuteActionPerformed

    private void jMenuItemAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAjouterActionPerformed
        addAction();
    }//GEN-LAST:event_jMenuItemAjouterActionPerformed

    private void jMenuItemListeExecutionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItemListeExecutionMouseClicked
    }//GEN-LAST:event_jMenuItemListeExecutionMouseClicked

    private void jMenuItemListeExecutionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemListeExecutionActionPerformed
        // 
        System.out.println("Liste des executions");
        HistoriqueTaskDialog assitantFen = new HistoriqueTaskDialog(mainFenInstance, true);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        assitantFen.setLocation(
                (screenSize.width - assitantFen.getWidth()) / 2,
                (screenSize.height - assitantFen.getHeight()) / 2);

        assitantFen.setTitle(properties.getProperty("TasksExecutionTitle"));
        assitantFen.show();
    }//GEN-LAST:event_jMenuItemListeExecutionActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddDatabase;
    private javax.swing.JButton jButtonEditer;
    private javax.swing.JButton jButtonExecuteTable;
    private javax.swing.JButton jButtonSupprimer;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItemAjouter;
    private javax.swing.JMenuItem jMenuItemDelete;
    private javax.swing.JMenuItem jMenuItemEdit;
    private javax.swing.JMenuItem jMenuItemExecute;
    private javax.swing.JMenuItem jMenuItemListeExecution;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JToolBar jToolBar1;
    private org.jdesktop.swingx.JXTable jXTable1;
    private org.jdesktop.swingx.JXTable jXTable2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyPressed(KeyEvent ke) {
        //System.out.println("keyPressed : Key EVENT : " + ke.toString());
        // Si pression sur la touche "supprimer"
        if (ke.getKeyCode() == 127) {
            supprimerAction();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //System.out.println("Key EVENT : " + ke.toString());
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //System.out.println("keyTyped : Key EVENT : " + ke.toString());
    }
}
