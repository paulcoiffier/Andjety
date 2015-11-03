/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package com.mobissime.andjety.wizard;

import com.mobissime.andjety.utils.OptionsParser;
import com.mobissime.andjety.MainFen;
import com.mobissime.andjety.windows.TasksPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import jsyntaxpane.DefaultSyntaxKit;
import com.mobissime.andjety.objects.DatabaseListeObj;
import com.mobissime.andjety.objects.TaskObj;

/**
 *
 * @author Paul Coiffier
 */
public class Assistant1 extends javax.swing.JDialog {

    public String taskLibelle;
    public String taskSql;
    public TasksPanel tksPanel;
    private TaskObj laNouvelleTache;
    private DatabaseListeObj dbListe;
    private MainFen mf;
    private Properties properties = new Properties();

    /**
     * Creates new form Assistant1
     */
    public Assistant1(java.awt.Frame parent, boolean modal,String resultatChemin, String strtaskLibelle, String strtaskSql, DatabaseListeObj dbListe, MainFen mf) {
        super(parent, modal);
        initComponents();

        taskLibelle = strtaskLibelle;
        taskSql = strtaskSql;
        jTextFieldResultat.setText(resultatChemin);
        jTextField1.setText(taskLibelle);
        this.dbListe = dbListe;
        this.mf = mf;

        DefaultSyntaxKit.initKit();
        jEditorPane1.setContentType("text/sql");
        jEditorPane1.setText(taskSql);

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
        jLabel5.setText(properties.getProperty("wizard1Message1"));
        jLabel6.setText(properties.getProperty("wizard1Message2"));
        jLabel7.setText(properties.getProperty("resultTitle") + " : ");
        jLabel3.setText(properties.getProperty("libelleTitle") + " : ");
        jLabel4.setText(properties.getProperty("sqlQueryTitle") + " : ");

        jRadioButtonSelect.setText(properties.getProperty("wizard1RadioButton1"));
        jRadioButtonInsert.setText(properties.getProperty("wizard1RadioButton2"));
        jRadioButtonProcPlSql.setText(properties.getProperty("wizard1RadioButton3"));

        jButton2.setText(properties.getProperty("cancelButton"));
        jButton1.setText(properties.getProperty("okButton"));
        jButton3.setText(properties.getProperty("testButton"));

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldResultat = new javax.swing.JTextField();
        jButtonFileChooser = new javax.swing.JButton();
        jButtonSQLEditor = new javax.swing.JButton();
        jRadioButtonInsert = new javax.swing.JRadioButton();
        jRadioButtonProcPlSql = new javax.swing.JRadioButton();
        jRadioButtonSelect = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/arrow_right.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/mobissime/andjety/wizard/Bundle"); // NOI18N
        jButton1.setText(bundle.getString("Assistant1.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        jButton2.setText(bundle.getString("Assistant1.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/wizard.png"))); // NOI18N
        jLabel1.setText(bundle.getString("Assistant1.jLabel1.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText(bundle.getString("Assistant1.jLabel2.text")); // NOI18N

        jLabel3.setText(bundle.getString("Assistant1.jLabel3.text")); // NOI18N

        jTextField1.setText(bundle.getString("Assistant1.jTextField1.text")); // NOI18N

        jLabel4.setText(bundle.getString("Assistant1.jLabel4.text")); // NOI18N

        jLabel5.setText(bundle.getString("Assistant1.jLabel5.text")); // NOI18N

        jLabel6.setText(bundle.getString("Assistant1.jLabel6.text")); // NOI18N

        jButton3.setText(bundle.getString("Assistant1.jButton3.text")); // NOI18N
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jEditorPane1);

        jLabel7.setText(bundle.getString("Assistant1.jLabel7.text")); // NOI18N

        jTextFieldResultat.setText(bundle.getString("Assistant1.jTextFieldResultat.text")); // NOI18N

        jButtonFileChooser.setText(bundle.getString("Assistant1.jButtonFileChooser.text")); // NOI18N
        jButtonFileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFileChooserActionPerformed(evt);
            }
        });

        jButtonSQLEditor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/database_edit.png"))); // NOI18N
        jButtonSQLEditor.setText(bundle.getString("Assistant1.jButtonSQLEditor.text")); // NOI18N
        jButtonSQLEditor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSQLEditorActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonInsert);
        jRadioButtonInsert.setText(bundle.getString("Assistant1.jRadioButtonInsert.text")); // NOI18N
        jRadioButtonInsert.setEnabled(false);
        jRadioButtonInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonInsertActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonProcPlSql);
        jRadioButtonProcPlSql.setText(bundle.getString("Assistant1.jRadioButtonProcPlSql.text")); // NOI18N
        jRadioButtonProcPlSql.setEnabled(false);

        buttonGroup1.add(jRadioButtonSelect);
        jRadioButtonSelect.setSelected(true);
        jRadioButtonSelect.setText(bundle.getString("Assistant1.jRadioButtonSelect.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4))
                                    .addGap(2, 2, 2))
                                .addComponent(jButtonSQLEditor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldResultat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jRadioButtonSelect)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButtonInsert)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButtonProcPlSql, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextFieldResultat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonFileChooser))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonSQLEditor))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButtonInsert)
                            .addComponent(jRadioButtonProcPlSql)
                            .addComponent(jRadioButtonSelect))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if ((jTextField1.getText().length() < 2) || (jEditorPane1.getText().length() < 2) || (jTextFieldResultat.getText().length() < 2)) {
            JOptionPane.showMessageDialog(null, properties.getProperty("errorCheckFields"), "Andjety", JOptionPane.WARNING_MESSAGE);

        } else {

            // Création de l'objet "TaskObj"
            laNouvelleTache = new TaskObj();
            laNouvelleTache.setTASK_LIBELLE(jTextField1.getText());
            laNouvelleTache.setTASK_REQUETE(jEditorPane1.getText());
            laNouvelleTache.setTASK_RESULTAT(jTextFieldResultat.getText());
            laNouvelleTache.setTASK_TYPE(taskSql);
            if (jRadioButtonSelect.isSelected()) {
                laNouvelleTache.setTASK_TYPE("SELECT");
            } else if (jRadioButtonInsert.isSelected()) {
                laNouvelleTache.setTASK_TYPE("INSERT");
            } else if (jRadioButtonProcPlSql.isSelected()) {
                laNouvelleTache.setTASK_TYPE("PROCEDURE");
            }

            this.hide();
            Assistant2 assitantFen = new Assistant2(null, rootPaneCheckingEnabled, laNouvelleTache, dbListe, mf);

            /* if (jRadioButtonSelect.isSelected()) {
             assitantFen.taskType = "SELECT";
             } else if (jRadioButtonInsert.isSelected()) {
             assitantFen.taskType = "INSERT";
             } else if (jRadioButtonProcPlSql.isSelected()) {
             assitantFen.taskType = "PROCEDURE";
             }*/

            java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

            assitantFen.setLocation(
                    (screenSize.width - assitantFen.getWidth()) / 2,
                    (screenSize.height - assitantFen.getHeight()) / 2);
            assitantFen.tksPanel = tksPanel;
            assitantFen.setTitle(properties.getProperty("wizardTitle"));
            //taskLibelle = jTextField1.getText();
            //taskSql = jEditorPane1.getText();

            /*assitantFen.taskLibelle = jTextField1.getText();
             assitantFen.taskSql = jEditorPane1.getText();
             assitantFen.cheminResultat = jTextFieldResultat.getText();
             assitantFen.show();*/

            assitantFen.taskLibelle = jTextField1.getText();
            assitantFen.taskSql = jEditorPane1.getText();
            assitantFen.cheminResultat = jTextFieldResultat.getText();
            assitantFen.show();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.hide();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButtonFileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFileChooserActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Selectionner un répertoire");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            jTextFieldResultat.setText(chooser.getSelectedFile().toString());
        } else {
            jTextFieldResultat.setText("");
        }

    }//GEN-LAST:event_jButtonFileChooserActionPerformed

    private void jButtonSQLEditorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSQLEditorActionPerformed

        taskSql = jEditorPane1.getText();
        SQLEditor assitantFen = new SQLEditor(null, true, taskSql);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        assitantFen.setLocation(
                (screenSize.width - assitantFen.getWidth()) / 2,
                (screenSize.height - assitantFen.getHeight()) / 2);
        assitantFen.setTitle(properties.getProperty("sqlQueryTitle"));

        assitantFen.show();

        assitantFen.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("RELOAD");
                jEditorPane1.setText(SQLEditor.textContent);
            }
        });
    }//GEN-LAST:event_jButtonSQLEditorActionPerformed

    private void jRadioButtonInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonInsertActionPerformed
        
    }//GEN-LAST:event_jRadioButtonInsertActionPerformed

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
            java.util.logging.Logger.getLogger(Assistant1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Assistant1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Assistant1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Assistant1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Assistant1 dialog = new Assistant1(new javax.swing.JFrame(), true, "", "", null, null, null);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonFileChooser;
    private javax.swing.JButton jButtonSQLEditor;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JRadioButton jRadioButtonInsert;
    private javax.swing.JRadioButton jRadioButtonProcPlSql;
    private javax.swing.JRadioButton jRadioButtonSelect;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldResultat;
    // End of variables declaration//GEN-END:variables
}
