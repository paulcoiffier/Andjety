/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.windows;

import com.mobissime.andjety.observers.Observateur;
import com.mobissime.andjety.threads.TaskMotorThread;
import com.mobissime.andjety.utils.OptionsParser;
import com.mobissime.andjety.MainFen;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;
import com.mobissime.andjety.models.AccueilTableModel;
import com.mobissime.andjety.models.TasksHistoriqueTableModel;
import com.mobissime.andjety.objects.RunningTasks;
import com.mobissime.andjety.objects.TaskExecListe;
import com.mobissime.andjety.observers.MainFenObervateur;
import com.mobissime.andjety.renderers.ProgressRenderer;

/**
 *
 * @author Paul Coiffier
 */
public class AccueilPanel extends javax.swing.JPanel {

    /**
     * Creates new form AccueilPanel
     */
    private MainFen fenPrincipale;
    private DefaultListModel dfl;
    private List<RunningTasks> theRunningTasksListe;
    private AccueilTableModel model;
    private TasksHistoriqueTableModel modelTaskExecution;
    private Properties properties = new Properties();
    private TaskExecListe tskListe;

    public AccueilPanel(MainFen mf, final TaskExecListe tskListe) {
        initComponents();

        jXTable1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();

                    // Ouverture du fichier
                    try {
                        File f = new File(jXTable1.getValueAt(row, 2).toString());
                        Desktop.getDesktop().open(f);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(AccueilPanel.this, "Erreur : " + ex.getMessage().toString(), "Andjety", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

        this.tskListe = tskListe;
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

        jLabel2.setText(properties.getProperty("jPanelAccueilLabel1"));
        jLabel3.setText(properties.getProperty("jPanelAccueilLabel2"));

        theRunningTasksListe = new ArrayList<RunningTasks>();
        model = new AccueilTableModel(theRunningTasksListe);
        modelTaskExecution = new TasksHistoriqueTableModel(tskListe);

        jTable1.setModel(model);
        jXTable1.setModel(modelTaskExecution);
        jXTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TableColumn col = jTable1.getColumnModel().getColumn(1);
        col.setCellRenderer(new ProgressRenderer());

        mf.addObservateur(new MainFenObervateur() {

            @Override
            public void update(List<TaskMotorThread> liste) {

                // MAJ de la liste des lors de dernière execution
                modelTaskExecution = new TasksHistoriqueTableModel(tskListe);
                jXTable1.setModel(modelTaskExecution);

                for (final TaskMotorThread t : liste) {
                    //System.out.println("Boucle");
                    t.addObservateur(new Observateur() {

                        @Override
                        public void update(String idTache, String pourcentage) {
                            //System.out.println("update Observateur");
                            testUpdateArray(idTache, pourcentage, t.getName().toString());
                        }
                    });
                }

            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable();

        jLabel1.setBackground(new java.awt.Color(204, 204, 255));
        jLabel1.setFont(new java.awt.Font("Showcard Gothic", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/headerLogo.png"))); // NOI18N
        jLabel1.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel2.setText("Tâches en cours d'execution ");

        jLabel3.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel3.setText("Dernières tâches executées");

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
        jScrollPane3.setViewportView(jTable1);

        jXTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jXTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public void testUpdateArray(String idTache, String pourcentage, String taskNom) {
        //System.out.println("testUpdateArray");
        RunningTasks rt = new RunningTasks(idTache, taskNom, pourcentage);
        rt.setTaskPourcentage(pourcentage);
        //
        int cpt = 0;
        for (RunningTasks rtt : theRunningTasksListe) {
            //System.out.println("Boucle RunningTasks");
            if (rtt.taskName.equals(taskNom)) {
                cpt = 1;
                // On met à jour le pourcentage
                rtt.setTaskPourcentage(pourcentage);
            }
        }

        if (cpt == 0) {
            theRunningTasksListe.add(rt);
        }

        model.fireTableDataChanged();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private org.jdesktop.swingx.JXTable jXTable1;
    // End of variables declaration//GEN-END:variables
}
