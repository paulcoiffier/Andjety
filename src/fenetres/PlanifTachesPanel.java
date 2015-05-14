/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package fenetres;

import Utils.DBUtils;
import andjety.MainFen;
import entities.Scheduletasks;
import entities.Tasks;
import entities.Tasksexec;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import models.PlanifTacheTableModel;
import models.TaskExecutionTableModel;
import org.quartz.TriggerKey;

/**
 *
 * @author Paul Coiffier
 */
public class PlanifTachesPanel extends javax.swing.JPanel implements KeyListener {

    private MainFen mainFenInstance;
    private EntityManager em;

    /**
     * Creates new form PlanifTachesPanel
     */
    public PlanifTachesPanel(MainFen m, EntityManager emEntity) {
        mainFenInstance = m;
        em = emEntity;
        initComponents();
        LoadData();

        jTable1.addKeyListener(this);

        jTable1.addMouseListener(new MouseAdapter() {
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
       
            
        jTable2.addMouseListener(new MouseAdapter() {

            int row;

            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (jTable2.getSelectedRow() >= 0) {
                        // Ouverture du fichier dans Excel lors du double click sur une ligne de la jTable
                        try {
                            File fileToOpen = new File(jTable2.getValueAt(jTable2.getSelectedRow(), 2).toString());
                            Desktop.getDesktop().open(fileToOpen);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Impossible d'ouvrir le fichier", "Andjety", JOptionPane.ERROR_MESSAGE);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Impossible d'ouvrir le fichier", "Andjety", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else if (e.getClickCount() == 1) {
                    try {
                        // Ouverture des fichiers LOGS

                        DBUtils dbu = new DBUtils();

                        Tasksexec tskexec = dbu.getTasksExecByTaskDate(jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString(), em);

                        File file = new File(tskexec.getLogdetails().toString());
                        File file2 = new File(tskexec.getLogresult().toString());

                        StringBuffer contents = new StringBuffer();
                        StringBuffer contents2 = new StringBuffer();

                        BufferedReader reader = null;

                        reader = new BufferedReader(new FileReader(file));
                        String text = null;

                        try {
                            while ((text = reader.readLine()) != null) {
                                contents.append(text).append(System.getProperty("line.separator"));
                            }
                            jEditorPaneLogDetails.setText(contents.toString());
                        } catch (IOException ex) {
                            Logger.getLogger(PlanifTachesPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        text = "";
                        reader = new BufferedReader(new FileReader(file2));
                        try {
                            while ((text = reader.readLine()) != null) {
                                contents2.append(text).append(System.getProperty("line.separator"));
                            }
                            jEditorPaneLogSTD.setText(contents2.toString());
                        } catch (IOException ex) {
                            Logger.getLogger(PlanifTachesPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(PlanifTachesPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });

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
                            jTable1.setModel(new PlanifTacheTableModel(em));

                        } catch (Exception ex) {
                            Logger.getLogger(DatabasesPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        mainFenInstance.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                        ListSelectionModel tableSelectionModel = jTable1.getSelectionModel();
                        tableSelectionModel.setSelectionInterval(0, 0);

                        // DefaultModel pour jTable2
                        DBUtils theDbUtils = new DBUtils();
                        Scheduletasks theTasksToEdit =
                                theDbUtils.getScheduleTaskById(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                        Tasks laTache = theDbUtils.getTaskById(theTasksToEdit.getIdtask(), em);

                        TaskExecutionTableModel tml = new TaskExecutionTableModel(em, laTache, theTasksToEdit);
                        jTable2.setModel(tml);

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
        jMenuItemAdd = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemEdit = new javax.swing.JMenuItem();
        jMenuItemDelete = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jButtonBarViewTask = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButtonBarAddTask = new javax.swing.JButton();
        jButtonEditer = new javax.swing.JButton();
        jButtonSupprimer = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSplitPane2 = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jEditorPaneLogSTD = new javax.swing.JEditorPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jEditorPaneLogDetails = new javax.swing.JEditorPane();

        jMenuItemAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/date_add.png"))); // NOI18N
        jMenuItemAdd.setText("Créer une tâche planifiée");
        jMenuItemAdd.setToolTipText("Créer une tâche planifiée");
        jMenuItemAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAddActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemAdd);
        jPopupMenu1.add(jSeparator2);

        jMenuItemEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/date_edit.png"))); // NOI18N
        jMenuItemEdit.setText("Editer la tâche planifiée");
        jMenuItemEdit.setToolTipText("Editer la tâche planifiée");
        jMenuItemEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEditActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemEdit);

        jMenuItemDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/date_delete.png"))); // NOI18N
        jMenuItemDelete.setText("Supprimer la tâche planifiée");
        jMenuItemDelete.setToolTipText("Supprimer la tâche planifiée");
        jMenuItemDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDeleteActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemDelete);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/gnome_document_open_recent.png"))); // NOI18N
        jLabel1.setText("Tâches planifiées");

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButtonBarViewTask.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/date_magnify.png"))); // NOI18N
        jButtonBarViewTask.setToolTipText("Afficher la tâche");
        jButtonBarViewTask.setFocusable(false);
        jButtonBarViewTask.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonBarViewTask.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonBarViewTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBarViewTaskActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonBarViewTask);
        jToolBar1.add(jSeparator1);

        jButtonBarAddTask.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/date_add.png"))); // NOI18N
        jButtonBarAddTask.setToolTipText("Ajouter une base de données");
        jButtonBarAddTask.setFocusable(false);
        jButtonBarAddTask.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonBarAddTask.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonBarAddTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBarAddTaskActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonBarAddTask);

        jButtonEditer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/date_edit.png"))); // NOI18N
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

        jButtonSupprimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/date_delete.png"))); // NOI18N
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

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(23, 250));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setMinimumSize(new java.awt.Dimension(60, 150));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jSplitPane1.setTopComponent(jScrollPane1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jSplitPane2.setLeftComponent(jScrollPane2);

        jEditorPaneLogSTD.setEditable(false);
        jScrollPane4.setViewportView(jEditorPaneLogSTD);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Log", jPanel1);

        jEditorPaneLogDetails.setEditable(false);
        jScrollPane3.setViewportView(jEditorPaneLogDetails);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Détails", jPanel2);

        jSplitPane2.setRightComponent(jTabbedPane1);

        jSplitPane1.setBottomComponent(jSplitPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(213, 213, 213))
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBarAddTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBarAddTaskActionPerformed
        ajouterAction();
    }//GEN-LAST:event_jButtonBarAddTaskActionPerformed

    private void ajouterAction() {
        /*PlanifTacheAdd assitantFen = new PlanifTacheAdd(mainFenInstance, true, em, null, "NEW");
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        assitantFen.setLocation(
                (screenSize.width - assitantFen.getWidth()) / 2,
                (screenSize.height - assitantFen.getHeight()) / 2);

        assitantFen.setTitle("Tâche planifiée");
        assitantFen.show();

        assitantFen.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("RELOAD");

                em = null;
                EntityManagerFactory emf =
                        javax.persistence.Persistence.createEntityManagerFactory("AndjetyPU");
                em = emf.createEntityManager();
                jTable1.setModel(new PlanifTacheTableModel(em));

            }
        });*/
    }

    private void supprimerAction() {
        try {
            DBUtils theDbUtils = new DBUtils();
            String taskId = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
            Scheduletasks theTasksToEdit = theDbUtils.getScheduleTaskById(taskId);

            //Custom button text
            Object[] options = {"Oui",
                "Non"};
            int response = JOptionPane.showOptionDialog(null, "Etes-vous sur de vouloir supprimer la planification de cette tâche ?",
                    "Quitter",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);

            if (response == 0) {
                deleteObj(theTasksToEdit);

                jTable1.setModel(new PlanifTacheTableModel(em));

                // Suppression de la planification de la tache
                TriggerKey tkey = new TriggerKey(String.valueOf(theTasksToEdit.getId()));
                mainFenInstance.scheduler.unscheduleJob(tkey);

                // Rechargement de la jTable
                mainFenInstance.LoadTasksDataList();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage().toString(),
                    "Quitter",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSupprimerActionPerformed
        supprimerAction();
    }//GEN-LAST:event_jButtonSupprimerActionPerformed

    private void editerAction() {
      /*  DBUtils theDbUtils = new DBUtils();
        Scheduletasks theTasksToEdit =
                theDbUtils.getScheduleTaskById(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());

        PlanifTacheAdd assitantFen = new PlanifTacheAdd(mainFenInstance, true, em, theTasksToEdit, "UPDATE");

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        assitantFen.setLocation((screenSize.width - assitantFen.getWidth())
                / 2, (screenSize.height - assitantFen.getHeight()) / 2);

        assitantFen.setTitle("Tâche planifiée");
        assitantFen.show();

        assitantFen.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("RELOAD");

                em = null;
                EntityManagerFactory emf =
                        javax.persistence.Persistence.createEntityManagerFactory("AndjetyPU");
                em = emf.createEntityManager();
                jTable1.setModel(new PlanifTacheTableModel(em));
            }
        });*/
    }

    private void jButtonEditerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditerActionPerformed
        editerAction();
    }//GEN-LAST:event_jButtonEditerActionPerformed

    private void jButtonBarViewTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBarViewTaskActionPerformed

        DBUtils theDbUtils = new DBUtils();
        Scheduletasks theTasksToEdit =
                theDbUtils.getScheduleTaskById(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());

        Tasks laTache = theDbUtils.getTaskById(theTasksToEdit.getIdtask(), em);
        System.out.println("Tache ID : " + laTache.getId());
        TaskExecutionDialog assitantFen = new TaskExecutionDialog(mainFenInstance, true, em, laTache, theTasksToEdit);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        assitantFen.setLocation((screenSize.width - assitantFen.getWidth())
                / 2, (screenSize.height - assitantFen.getHeight()) / 2);

        assitantFen.setTitle("Execution(s) de la tâche planifiée");
        assitantFen.show();

        assitantFen.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {
                /*
                 * em = null; EntityManagerFactory emf =
                 * javax.persistence.Persistence.createEntityManagerFactory("AndjetyPU");
                 * em = emf.createEntityManager(); jTable1.setModel(new
                 * PlanifTacheTableModel(em));
                 * mainFenInstance.LoadTasksDataList();
                 */
            }
        });
    }//GEN-LAST:event_jButtonBarViewTaskActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        DBUtils theDbUtils = new DBUtils();
        Scheduletasks theTasksToEdit =
                theDbUtils.getScheduleTaskById(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        Tasks laTache = theDbUtils.getTaskById(theTasksToEdit.getIdtask(), em);

        TaskExecutionTableModel tml = new TaskExecutionTableModel(em, laTache, theTasksToEdit);
        jTable2.setModel(tml);

    }//GEN-LAST:event_jTable1MouseClicked

    private void jMenuItemAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAddActionPerformed
        ajouterAction();
    }//GEN-LAST:event_jMenuItemAddActionPerformed

    private void jMenuItemEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditActionPerformed
        editerAction();
    }//GEN-LAST:event_jMenuItemEditActionPerformed

    private void jMenuItemDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDeleteActionPerformed
        supprimerAction();
    }//GEN-LAST:event_jMenuItemDeleteActionPerformed

    static void deleteObj(Object object) {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("AndjetyPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.remove(em.merge(object));
            em.getTransaction().commit();
            //System.out.println("Commit");
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBarAddTask;
    private javax.swing.JButton jButtonBarViewTask;
    private javax.swing.JButton jButtonEditer;
    private javax.swing.JButton jButtonSupprimer;
    private javax.swing.JEditorPane jEditorPaneLogDetails;
    private javax.swing.JEditorPane jEditorPaneLogSTD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItemAdd;
    private javax.swing.JMenuItem jMenuItemDelete;
    private javax.swing.JMenuItem jMenuItemEdit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JToolBar jToolBar1;
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
