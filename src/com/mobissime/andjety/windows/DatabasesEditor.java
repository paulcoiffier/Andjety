/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.windows;

import com.mobissime.andjety.dbUtils.MysqlConnexion;
import com.mobissime.andjety.dbUtils.OracleConnexion;
import com.mobissime.andjety.utils.OptionsParser;
import com.mobissime.andjety.MainFen;
import com.mobissime.andjety.entities.Databaselist;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
import javax.swing.JOptionPane;
import com.mobissime.andjety.objects.DatabaseObj;

/**
 *
 * @author Paul Coiffier
 */
public class DatabasesEditor extends javax.swing.JDialog {

    public String serveur;
    public String adresse;
    public String instance;
    public String utilisateur;
    public String motDePasse;
    private DatabaseObj dbo;
    private static String titreDeLaFenetre;
    public String typeFen;
    public Databaselist theDbListToEdit;
    private MainFen mf;
    private Properties properties = new Properties();

    /**
     * Creates new form DatabasesEditor
     */
    public DatabasesEditor(java.awt.Frame parent, boolean modal, String titreFenetre, MainFen mf, DatabaseObj dbo) {
        super(parent, modal);
        initComponents();
        this.setTitle(titreFenetre);
        this.mf = mf;
        this.dbo = dbo;
        titreDeLaFenetre = titreFenetre;
        jLabelTitre.setText(titreFenetre);
        setFieldsValues();

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

        jLabel1.setText(properties.getProperty("libelleTitle"));

        jLabel2.setText(properties.getProperty("DatabaseEditorLabel1") + " : ");
        jLabel3.setText(properties.getProperty("DatabaseEditorLabel2") + " : ");
        jLabel5.setText(properties.getProperty("DatabaseEditorLabel3") + " : ");
        jLabel4.setText(properties.getProperty("DatabaseEditorLabel4") + " : ");

        jButtonValider.setText(properties.getProperty("okButton"));
        jButtonFermer.setText(properties.getProperty("cancelButton"));
        jButtonFermer1.setText(properties.getProperty("testButton"));

    }

    public void setFieldsValues() {
        if (dbo != null) {
            jTextAdresse.setText(dbo.getDblist_ip());
            jTextInstance.setText(dbo.getDblist_instance());
            jTextLibelle.setText(dbo.getDblist_libelle());
            jTextPassword.setText(dbo.getDblist_password());
            jTextUtilisateur.setText(dbo.getDblist_user());
            jComboBoxSGBD.setSelectedItem(dbo.getDblist_sgbd().toUpperCase());
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextLibelle = new javax.swing.JTextField();
        jTextAdresse = new javax.swing.JTextField();
        jTextInstance = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextUtilisateur = new javax.swing.JTextField();
        jTextPassword = new javax.swing.JTextField();
        jButtonValider = new javax.swing.JButton();
        jButtonFermer = new javax.swing.JButton();
        jLabelTitre = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonFermer1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxSGBD = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Libellé : ");

        jLabel2.setText("Adresse :");

        jLabel3.setText("Instance : ");

        jLabel4.setText("Mot de passe :");

        jLabel5.setText("Utilisateur : ");

        jButtonValider.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/tick.png"))); // NOI18N
        jButtonValider.setText("Valider");
        jButtonValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValiderActionPerformed(evt);
            }
        });

        jButtonFermer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        jButtonFermer.setText("Fermer");
        jButtonFermer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFermerActionPerformed(evt);
            }
        });

        jLabelTitre.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelTitre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/gnome_network_server.png"))); // NOI18N
        jLabelTitre.setText("jLabel6");

        jButtonFermer1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/database_lightning.png"))); // NOI18N
        jButtonFermer1.setText("Tester");
        jButtonFermer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFermer1ActionPerformed(evt);
            }
        });

        jLabel6.setText("SGBD : ");

        jComboBoxSGBD.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ORACLE", "MYSQL" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 36, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonFermer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonFermer1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonValider))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTitre)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextLibelle)
                                    .addComponent(jTextAdresse)
                                    .addComponent(jTextUtilisateur)
                                    .addComponent(jTextInstance)
                                    .addComponent(jTextPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                    .addComponent(jComboBoxSGBD, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabelTitre)
                .addGap(7, 7, 7)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextLibelle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxSGBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextAdresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextInstance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextUtilisateur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonValider)
                    .addComponent(jButtonFermer)
                    .addComponent(jButtonFermer1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValiderActionPerformed
        if ((jTextAdresse.getText().length() < 2) || (jTextInstance.getText().length() < 2) || (jTextLibelle.getText().length() < 2) || (jTextPassword.getText().length() < 2) || (jTextUtilisateur.getText().length() < 2)) {
            JOptionPane d = new JOptionPane();
            d.showMessageDialog(null, properties.getProperty("errorCheckFields"), "Andjety", JOptionPane.WARNING_MESSAGE);
        } else {


            // Création de l'objet et engistrement dans le XML
            if (dbo == null) {
                dbo = new DatabaseObj();
            }
            dbo.setDblist_instance(jTextInstance.getText());
            dbo.setDblist_ip(jTextAdresse.getText());
            dbo.setDblist_libelle(jTextLibelle.getText());
            dbo.setDblist_password(jTextPassword.getText());
            dbo.setDblist_user(jTextUtilisateur.getText());
            dbo.setDblist_sgbd(jComboBoxSGBD.getSelectedItem().toString());

            if (typeFen != "Editer") {
                // Appel MainFrame pour enregistrement dans le XML
                if (mf.saveNewServersToXML(dbo)) {
                    this.hide();

                    this.dispatchEvent(new WindowEvent(
                            this, WindowEvent.WINDOW_CLOSING));
                }

            } else {
                // Appel MainFrame pour enregistrement dans le XML
                mf.saveServersToXML(dbo);
                this.hide();

                this.dispatchEvent(new WindowEvent(
                        this, WindowEvent.WINDOW_CLOSING));
            }

        }
    }//GEN-LAST:event_jButtonValiderActionPerformed

    private void jButtonFermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFermerActionPerformed
        this.hide();
    }//GEN-LAST:event_jButtonFermerActionPerformed

    private void jButtonFermer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFermer1ActionPerformed

        if (jComboBoxSGBD.getSelectedItem().toString().toUpperCase().equals("ORACLE")) {
            OracleConnexion orclCon = new OracleConnexion();
            Connection sqlCon = orclCon.getOracleCon(jTextUtilisateur.getText(), jTextPassword.getText(), jTextInstance.getText(), jTextAdresse.getText());
            if (orclCon.erreurConnexion.length() > 0) {
                JOptionPane.showMessageDialog(this, properties.getProperty("errorConnection") + " : " + orclCon.erreurConnexion, "Andjety", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Connexion OK !", "Andjety", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (jComboBoxSGBD.getSelectedItem().toString().toUpperCase().equals("MYSQL")) {
            MysqlConnexion orclCon = new MysqlConnexion();
            Connection sqlCon = orclCon.getMysqlCon(jTextAdresse.getText(), jTextInstance.getText(), jTextUtilisateur.getText(), jTextPassword.getText());
            if (orclCon.erreurConnexion.length() > 0) {
                JOptionPane.showMessageDialog(this, properties.getProperty("errorConnection") + " : " + orclCon.erreurConnexion, "Andjety", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Connexion OK !", "Andjety", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }//GEN-LAST:event_jButtonFermer1ActionPerformed

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
            java.util.logging.Logger.getLogger(DatabasesEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatabasesEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatabasesEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatabasesEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                DatabasesEditor dialog = new DatabasesEditor(new javax.swing.JFrame(), true, titreDeLaFenetre, null, null);
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
    private javax.swing.JButton jButtonFermer;
    private javax.swing.JButton jButtonFermer1;
    private javax.swing.JButton jButtonValider;
    private javax.swing.JComboBox jComboBoxSGBD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelTitre;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextAdresse;
    private javax.swing.JTextField jTextInstance;
    private javax.swing.JTextField jTextLibelle;
    private javax.swing.JTextField jTextPassword;
    private javax.swing.JTextField jTextUtilisateur;
    // End of variables declaration//GEN-END:variables
}
