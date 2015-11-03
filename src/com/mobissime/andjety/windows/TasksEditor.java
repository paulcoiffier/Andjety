/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package com.mobissime.andjety.windows;

import com.mobissime.andjety.utils.OptionsParser;
import com.mobissime.andjety.MainFen;
import java.awt.Cursor;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import jsyntaxpane.DefaultSyntaxKit;
import com.mobissime.andjety.objects.DatabaseObj;
import com.mobissime.andjety.objects.TaskObj;
import javax.swing.JRootPane;

/**
 *
 * @author Paul Coiffier
 */
public class TasksEditor extends javax.swing.JDialog {

    /**
     * Creates new form TasksEditor
     */
    private DefaultListModel listModelTable1 = new DefaultListModel();
    private DefaultListModel listModelTable2 = new DefaultListModel();
    public TaskObj maTache;
    private TasksEditor tk;
    private MainFen mf;
    private Properties properties = new Properties();
    public String typeFen;

    public TasksEditor(java.awt.Frame parent, boolean modal, final TaskObj maTache, final MainFen mf) {
        super(parent, modal);
        initComponents();
        this.maTache = maTache;
        this.mf = mf;
        tk = this;
        
        
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
        
        jLabel5.setText(properties.getProperty("onetaskTitle"));
        jLabel3.setText(properties.getProperty("libelleTitle") + " : ");
        jLabel7.setText(properties.getProperty("resultTitle") + " : ");
        jLabel4.setText(properties.getProperty("sqlQueryTitle") + " : ");
        jRadioButtonSelect.setText(properties.getProperty("wizard1RadioButton1"));
        jRadioButtonInsert2.setText(properties.getProperty("wizard1RadioButton2"));
        jRadioButtonProcPlSql.setText(properties.getProperty("wizard1RadioButton3"));
        jLabel6.setText(properties.getProperty("databasesTitle"));
        jButtonOk.setText(properties.getProperty("okButton"));
        jButton2.setText(properties.getProperty("cancelButton"));
        
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                
                //DerbyConnexion msConObj = new DerbyConnexion();
                //Connection dbConMysql = msConObj.getMysqlCon();

                DefaultSyntaxKit.initKit();
                jEditorPane1.setContentType("text/sql");
                jEditorPane1.setText(maTache.getTASK_REQUETE());
                jTextField1.setText(maTache.getTASK_LIBELLE());
                jTextFieldResultat.setText(maTache.getTASK_RESULTAT());

              /*  if (dbConMysql == null) {
                    JOptionPane d = new JOptionPane();
                    d.showMessageDialog(null, "Erreur : " + msConObj.erreurConnexion, "Andjety", JOptionPane.WARNING_MESSAGE);
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                } else {
                */   
                    //add the cache
                    ArrayList<DatabaseObj> resultList = mf.getDatabaseList().getDbListe();

                    for (DatabaseObj rs : resultList) {
                        // On vérifie si l'item n'est pas dans Tasksdb sinon on ne l'affiche pas
                        

                        Boolean ifTrouve = false;
                        for (DatabaseObj dbo:  maTache.getTASK_SERVERSLIST()) {
                            if(dbo.getDblist_libelle().equals(rs.getDblist_libelle())){
                                ifTrouve = true;
                                listModelTable1.addElement(rs.getDblist_libelle().toString());
                                break;
                            }
                            //listModelTable1.addElement(resultListaaa.get(ie).getDblistLibelle().toString());
                            
                        }
                        if (ifTrouve == false) {
                            listModelTable2.addElement(rs.getDblist_libelle().toString());
                        }
                    }

                    // Selection des items à ajouter dans la liste de droite : jList1
                    //Query queryZ = em.createQuery("SELECT t FROM Tasksdb t WHERE t.idTask = " + Integer.valueOf(maTache.getId()));
                    //List<Tasksdb> resultListTasksdb = queryZ.getResultList();

                    /*for (int i = 0; i < resultListTasksdb.size(); i++) {
                        // Selection du libelle de la tache
                        Query queryLibelle = em.createQuery("SELECT d FROM Databaselist d WHERE d.id = " + resultListTasksdb.get(i).getIdDb());
                        List<Databaselist> resultList2 = queryLibelle.getResultList();

                        for (int ie = 0; ie < resultList2.size(); ie++) {
                            listModelTable1.addElement(resultList2.get(ie).getDblistLibelle().toString());
                        }

                    }*/
                    /*for(DatabaseObj dbo : resultList){
                        listModelTable1.addElement(dbo.getDblist_libelle().toString());
                    }*/

                    // On coche la case de type de requete par défaut
                    if (maTache.getTASK_TYPE().equals("INSERT")) {
                        jRadioButtonInsert2.setSelected(true);
                    } else if (maTache.getTASK_TYPE().equals("SELECT")) {
                        jRadioButtonSelect.setSelected(true);
                    } else if (maTache.getTASK_TYPE().equals("PROCEDURE")) {
                        jRadioButtonProcPlSql.setSelected(true);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButtonOk = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldResultat = new javax.swing.JTextField();
        jButtonFileChooser = new javax.swing.JButton();
        jRadioButtonSelect = new javax.swing.JRadioButton();
        jRadioButtonInsert2 = new javax.swing.JRadioButton();
        jRadioButtonProcPlSql = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setViewportView(jEditorPane1);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/mobissime/andjety/windows/Bundle"); // NOI18N
        jTextField1.setText(bundle.getString("Assistant1.jTextField1.text")); // NOI18N

        java.util.ResourceBundle bundle1 = java.util.ResourceBundle.getBundle("assistant/Bundle"); // NOI18N
        jLabel3.setText(bundle1.getString("Assistant1.jLabel3.text")); // NOI18N

        jLabel4.setText(bundle.getString("Assistant1.jLabel4.text")); // NOI18N

        jLabel5.setBackground(new java.awt.Color(204, 204, 255));
        jLabel5.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gnome_task_due.png"))); // NOI18N
        jLabel5.setText("Tâche");
        jLabel5.setOpaque(true);

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

        jLabel6.setBackground(new java.awt.Color(204, 204, 255));
        jLabel6.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gnome_network_server.png"))); // NOI18N
        jLabel6.setText("Bases de données");
        jLabel6.setOpaque(true);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        jButton2.setText(bundle.getString("Assistant2.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButtonOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/tick.png"))); // NOI18N
        jButtonOk.setText("Terminé");
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });

        jLabel7.setText(bundle1.getString("Assistant1.jLabel3.text")); // NOI18N
        jLabel7.setToolTipText("");

        jTextFieldResultat.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldResultat.setText(bundle.getString("Assistant1.jTextField1.text")); // NOI18N
        jTextFieldResultat.setToolTipText("");

        jButtonFileChooser.setText("...");
        jButtonFileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFileChooserActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonSelect);
        jRadioButtonSelect.setSelected(true);
        jRadioButtonSelect.setText(bundle1.getString("com.mobissime.andjety.windows.Bundle")); // NOI18N

        buttonGroup1.add(jRadioButtonInsert2);
        jRadioButtonInsert2.setText(bundle.getString("Assistant1.jRadioButtonInsert.text")); // NOI18N
        jRadioButtonInsert2.setEnabled(false);
        jRadioButtonInsert2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonInsert2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonProcPlSql);
        jRadioButtonProcPlSql.setText(bundle.getString("Assistant1.jRadioButtonProcPlSql.text")); // NOI18N
        jRadioButtonProcPlSql.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jTextFieldResultat)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButtonSelect)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButtonInsert2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButtonProcPlSql, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, Short.MAX_VALUE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonOk, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldResultat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonFileChooser))
                .addGap(24, 24, 24)
                .addComponent(jLabel4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButtonInsert2)
                            .addComponent(jRadioButtonProcPlSql)
                            .addComponent(jRadioButtonSelect))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addGap(74, 74, 74)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonOk)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jList1AncestorResized(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jList1AncestorResized
    }//GEN-LAST:event_jList1AncestorResized

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (jList2.getSelectedValue().toString().length() > 1) {
            // Ajout dans l'autre jList

            int[] selectedIx = jList2.getSelectedIndices();
            int[] selectedIxBis = jList2.getSelectedIndices();


            for (int i = 0; i < selectedIx.length; i++) {
                Object sel = jList2.getModel().getElementAt(selectedIx[i]);
                listModelTable1.addElement(sel.toString());
                //jList2.remove(selectedIx[i]);
            }

            for (int i = selectedIx.length - 1; i > -1; i--) {
                listModelTable2.remove(selectedIx[i]);
            }

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (jList1.getSelectedValue().toString().length() > 1) {
            int[] selectedIx = jList1.getSelectedIndices();
            int[] selectedIxBis = jList1.getSelectedIndices();


            for (int i = 0; i < selectedIx.length; i++) {
                Object sel = jList1.getModel().getElementAt(selectedIx[i]);
                listModelTable2.addElement(sel.toString());
                //jList2.remove(selectedIx[i]);
            }

            for (int i = selectedIx.length - 1; i > -1; i--) {
                listModelTable1.remove(selectedIx[i]);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.hide();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                // Insertion en base
                maTache.setTASK_LIBELLE(jTextField1.getText());
                maTache.setTASK_REQUETE(jEditorPane1.getText());
                maTache.setTASK_RESULTAT(jTextFieldResultat.getText());

                if (jRadioButtonInsert2.isSelected()) {
                    maTache.setTASK_TYPE("INSERT");
                } else if (jRadioButtonSelect.isSelected()) {
                    maTache.setTASK_TYPE("SELECT");
                } else if (jRadioButtonProcPlSql.isSelected()) {
                    maTache.setTASK_TYPE("PROCEDURE");
                }

                
                ArrayList<DatabaseObj> tab = new ArrayList<DatabaseObj>();
                
                // On supprime tous les serveurs de la tache
                maTache.setTASK_SERVERSLIST(tab);
                // On recréé les objets serveurs à partir des libellés
                for(int i=0;i<listModelTable1.size();i++){
                    
                    String libelleDatabase = listModelTable1.getElementAt(i).toString();
                    for(DatabaseObj dbo : mf.getDBList().getDbListe()){
                        if(dbo.getDblist_libelle().equals(libelleDatabase)){
                            tab.add(dbo);
                            break;
                        }
                    }
                    
                }
                
                // On définit le tableau en tant que serveurs de la tâche
                maTache.setTASK_SERVERSLIST(tab);
                mf.saveTaskToTasksList(maTache);
                
                
                //DBUtils dbu = new DBUtils();
                //String TaskId = dbu.getTaskId(maTache.getTASK_LIBELLE());


                /*Query queryZ = em.createQuery("SELECT t FROM Tasksdb t WHERE t.idTask = " + Integer.valueOf(maTache.getId()));
                List<Tasksdb> resultListTasksdb = queryZ.getResultList();

                for (int i = 0; i < resultListTasksdb.size(); i++) {
                    // Selection du libelle de la tache
                    entities.Tasksdb newTaskDbDelete = new entities.Tasksdb(resultListTasksdb.get(i).getId(), resultListTasksdb.get(i).getIdTask(), resultListTasksdb.get(i).getIdDb());
                    deleteObj(newTaskDbDelete);
                    //System.out.println("SUPPRIME");
                }

                for (int i = 0; i < jList1.getModel().getSize(); i++) {
                    // On va chercher l'id de l'equipement en fonction de son nom
                    Object item = jList1.getModel().getElementAt(i);
                    String DbId = dbu.getDbId(item.toString());


                    // On supprime l'existant
                    entities.Tasksdb newTaskDb = new entities.Tasksdb();
                    newTaskDb.setIdDb(Integer.parseInt(DbId));
                    newTaskDb.setIdTask(Integer.parseInt(TaskId));



                    persist(newTaskDb);
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                //em.close();
                */ 
                tk.dispatchEvent(new WindowEvent(
                        tk, WindowEvent.WINDOW_CLOSING));
            }
        });



    }//GEN-LAST:event_jButtonOkActionPerformed

    private void jButtonFileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFileChooserActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(properties.getProperty("selectDirectory"));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            jTextFieldResultat.setText(chooser.getSelectedFile().toString());
        } else {
            jTextFieldResultat.setText("");
        }
    }//GEN-LAST:event_jButtonFileChooserActionPerformed

    private void jRadioButtonInsert2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonInsert2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonInsert2ActionPerformed

  

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
            java.util.logging.Logger.getLogger(TasksEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TasksEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TasksEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TasksEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                TasksEditor dialog = new TasksEditor(new javax.swing.JFrame(), true, null,null);
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonFileChooser;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JRadioButton jRadioButtonInsert2;
    private javax.swing.JRadioButton jRadioButtonProcPlSql;
    private javax.swing.JRadioButton jRadioButtonSelect;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldResultat;
    // End of variables declaration//GEN-END:variables
}
