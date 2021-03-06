/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.wizard;

import com.mobissime.andjety.utils.OptionsParser;
import com.mobissime.andjety.MainFen;
import com.mobissime.andjety.windows.TasksPanel;
import java.awt.Cursor;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.DefaultListModel;
import com.mobissime.andjety.objects.DatabaseListeObj;
import com.mobissime.andjety.objects.DatabaseObj;
import com.mobissime.andjety.objects.TaskObj;

/**
 *
 * @author Paul Coiffier
 */
public class Assistant2 extends javax.swing.JDialog {

    /**
     * Creates new form Assistant1
     */
    private DefaultListModel listModelTable1 = new DefaultListModel();
    private DefaultListModel listModelTable2 = new DefaultListModel();
    public String taskLibelle;
    public String taskSql;
    public String cheminResultat;
    public TasksPanel tksPanel;
    public String taskType;
    private TaskObj laNouvelleTache;
    private DatabaseListeObj dbListe;
    private MainFen mf;
    private Properties properties = new Properties();

    public Assistant2(java.awt.Frame parent, boolean modal, TaskObj laNouvelleTache, final DatabaseListeObj dbListe, MainFen mf) {
        super(parent, modal);
        initComponents();
        this.laNouvelleTache = laNouvelleTache;
        this.dbListe = dbListe;
        this.mf = mf;

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

        jLabel2.setText(properties.getProperty("wizardTitle"));
        jLabel5.setText(properties.getProperty("wizard2Message1"));
        jButton1.setText(properties.getProperty("finishButton"));
        jButton7.setText(properties.getProperty("backButton"));
        jButton2.setText(properties.getProperty("cancelButton"));
        // Thread pour execution légèrement décalée
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

               // setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                //  DerbyConnexion msConObj = new DerbyConnexion();
                //  Connection dbConMysql = msConObj.getMysqlCon();
               // if (dbConMysql == null) {
                //     JOptionPane.showMessageDialog(null, "Erreur : " + msConObj.erreurConnexion, "Andjety", JOptionPane.WARNING_MESSAGE);
                //     setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                // } else {
                // Liste des serveurs / Base de données
                for (int i = 0; i < dbListe.getDbListe().size(); i++) {
                    listModelTable2.addElement(dbListe.getDbListe().get(i).getDblist_libelle().toString());
                }

                jList1.setModel(listModelTable1);
                jList2.setModel(listModelTable2);
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                //}
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

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/tick.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/mobissime/andjety/wizard/Bundle"); // NOI18N
        jButton1.setText(bundle.getString("Assistant2.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        jButton2.setText(bundle.getString("Assistant2.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/wizard.png"))); // NOI18N
        jLabel1.setText(bundle.getString("Assistant2.jLabel1.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText(bundle.getString("Assistant2.jLabel2.text")); // NOI18N

        jLabel5.setText(bundle.getString("Assistant2.jLabel5.text")); // NOI18N

        jList1.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
                jList1AncestorResized(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        jScrollPane3.setViewportView(jList2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/gnome_go_next.png"))); // NOI18N
        jButton3.setText(bundle.getString("Assistant2.jButton3.text")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/gnome_go_previous.png"))); // NOI18N
        jButton4.setText(bundle.getString("Assistant2.jButton4.text")); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/arrow_left.png"))); // NOI18N
        jButton7.setText(bundle.getString("Assistant2.jButton7.text")); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 431, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton7)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        ArrayList<DatabaseObj> listeOfDBObjectsToAdd = new ArrayList<DatabaseObj>();

        // Construction de l'array d'objets DatabaseObj
        for (int i = 0; i < jList1.getModel().getSize(); i++) {

            // On récupère la databaseObj
            for (DatabaseObj dbo : dbListe.getDbListe()) {
                if (dbo.getDblist_libelle().equals(jList1.getModel().getElementAt(i).toString())) {
                    // On a trouvé le databaseObj, on l'ajoute dans le tableau
                    listeOfDBObjectsToAdd.add(dbo);
                }
            }
        }

        laNouvelleTache.setTASK_SERVERSLIST(listeOfDBObjectsToAdd);
        // On enregistre la tâche en appellant la fonction de la classe principale
        mf.saveTaskToTasksList(laNouvelleTache);

        if (tksPanel != null) {
            tksPanel.LoadData();
            tksPanel.mainFenReloadTasks();
        }

        this.dispatchEvent(new WindowEvent(
                this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_jButton1ActionPerformed

    static void persist(Object object) {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("AndjetyPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.hide();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        this.hide();
        Assistant1 assitantFen = new Assistant1(null, rootPaneCheckingEnabled, laNouvelleTache.getTASK_RESULTAT(), taskLibelle, taskSql, dbListe, mf);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        assitantFen.setLocation(
                (screenSize.width - assitantFen.getWidth()) / 2,
                (screenSize.height - assitantFen.getHeight()) / 2);

        assitantFen.setTitle(properties.getProperty("wizardTitle"));
        assitantFen.show();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jList1AncestorResized(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jList1AncestorResized
    }//GEN-LAST:event_jList1AncestorResized

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (jList2.getSelectedValue() != null) {

            // Ajout dans l'autre jList
            int[] selectedIx = jList2.getSelectedIndices();

            for (int i = 0; i < selectedIx.length; i++) {
                Object sel = jList2.getModel().getElementAt(selectedIx[i]);
                listModelTable1.addElement(sel.toString());
            }

            for (int i = selectedIx.length - 1; i > -1; i--) {
                listModelTable2.remove(selectedIx[i]);
            }
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (jList1.getSelectedValue().toString().length() > 1) {
            int[] selectedIx = jList1.getSelectedIndices();

            for (int i = 0; i < selectedIx.length; i++) {
                Object sel = jList1.getModel().getElementAt(selectedIx[i]);
                listModelTable2.addElement(sel.toString());
            }

            for (int i = selectedIx.length - 1; i > -1; i--) {
                listModelTable1.remove(selectedIx[i]);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Assistant2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Assistant2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Assistant2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Assistant2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                Assistant2 dialog = new Assistant2(new javax.swing.JFrame(), true, null, null, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
