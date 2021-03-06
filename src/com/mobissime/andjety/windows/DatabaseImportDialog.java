/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.windows;

import com.mobissime.andjety.utils.FiltreExtensible;
import com.mobissime.andjety.MainFen;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import com.mobissime.andjety.objects.DatabaseObj;
import com.mobissime.andjety.objects.TaskObj;
import com.mobissime.andjety.objects.TaskObjListe;

/**
 *
 * @author Paul Coiffier
 */
public class DatabaseImportDialog extends javax.swing.JDialog {

    // Pour la persistance de la connexion à la base DERBY
    private MainFen mf;
    private TaskObjListe listOfTasks;

    public DatabaseImportDialog(java.awt.Frame parent, boolean modal, MainFen mf, TaskObjListe listOfTasks) {
        super(parent, modal);
        initComponents();
        this.mf = mf;
        this.listOfTasks = listOfTasks;
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel5 = new javax.swing.JLabel();
        jRadioButtonCSV = new javax.swing.JRadioButton();
        jRadioButtonXML = new javax.swing.JRadioButton();
        jRadioButtonBaseMySQL = new javax.swing.JRadioButton();
        jTextArea1 = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonValider = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jTextArea2 = new javax.swing.JTextArea();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jRadioButtonReplace = new javax.swing.JRadioButton();
        jRadioButtonIgnore = new javax.swing.JRadioButton();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel5.setBackground(new java.awt.Color(204, 204, 255));
        jLabel5.setFont(new java.awt.Font("Bauhaus 93", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gnome_network_server.png"))); // NOI18N
        jLabel5.setText("Importer de bases de données");
        jLabel5.setOpaque(true);

        buttonGroup1.add(jRadioButtonCSV);
        jRadioButtonCSV.setSelected(true);
        jRadioButtonCSV.setText("Fichier CSV");
        jRadioButtonCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCSVActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonXML);
        jRadioButtonXML.setText("Fichier XML");
        jRadioButtonXML.setEnabled(false);

        buttonGroup1.add(jRadioButtonBaseMySQL);
        jRadioButtonBaseMySQL.setText("Base de données MySQL");
        jRadioButtonBaseMySQL.setEnabled(false);

        jTextArea1.setBackground(new java.awt.Color(240, 240, 240));
        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Veuillez selectionner le type de fichier à importer. Ce fichier sera analysé par Andjety et créera de nouvelles entrées dans la liste des bases de données");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(null);

        jButtonValider.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/tick.png"))); // NOI18N
        jButtonValider.setText("Valider");
        jButtonValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValiderActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        jButton2.setText("Annuler");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextArea2.setBackground(new java.awt.Color(240, 240, 240));
        jTextArea2.setColumns(20);
        jTextArea2.setEditable(false);
        jTextArea2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setText("Le fichier doit contenir 5 colonnes : \n- Libellé\n- Adresse IP\n- Instance\n- Utilisateur\n- Mot de passe\n- Type de SGBD (Oracle ou MySQL)");
        jTextArea2.setWrapStyleWord(true);
        jTextArea2.setBorder(null);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Options d'import");

        buttonGroup2.add(jRadioButtonReplace);
        jRadioButtonReplace.setSelected(true);
        jRadioButtonReplace.setText("Remplacer les bases de données ayant le même libellé");
        jRadioButtonReplace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonReplaceActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButtonIgnore);
        jRadioButtonIgnore.setText("Ignorer les doublons de libellé");
        jRadioButtonIgnore.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Source de données");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Source de l'import");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonValider))
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2)
                            .addComponent(jSeparator3)
                            .addComponent(jTextArea2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jRadioButtonCSV)
                                            .addComponent(jRadioButtonBaseMySQL, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(11, 11, 11)
                                        .addComponent(jRadioButtonXML, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButtonReplace)
                                    .addComponent(jRadioButtonIgnore)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextArea2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonCSV)
                    .addComponent(jRadioButtonXML))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonBaseMySQL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(9, 9, 9)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonReplace)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonIgnore)
                .addGap(13, 13, 13)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonValider)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispatchEvent(new WindowEvent(
                this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButtonCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCSVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonCSVActionPerformed

    private void jButtonValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValiderActionPerformed

        if (jRadioButtonCSV.isSelected()) {

            //EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("AndjetyPU");
            //em = emf.createEntityManager();

            // Ouverture du fichier CSV via un jdialog
            FiltreExtensible filtre = new FiltreExtensible("Fichiers CSV");
            filtre.addExtension(".csv");

            final JFileChooser chooser = new JFileChooser(".");
            chooser.addChoosableFileFilter(filtre);

            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

                try {
                    String chemin = chooser.getSelectedFile().getAbsolutePath();

                    BufferedReader fichier_source = new BufferedReader(new FileReader(chemin));
                    String chaine;
                    int i = 1;

                    setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));

                    while ((chaine = fichier_source.readLine()) != null) {
                        //System.out.println("Valeur chaine : " + chaine);
                        if (chaine.contains((","))) {

                            String[] tabChaine = chaine.split(",");

                            // Création d'une nouvelle entrée
                            DatabaseObj newDb = new DatabaseObj();
                            newDb.setDblist_libelle(tabChaine[0].toString().replace("\"", ""));
                            newDb.setDblist_ip(tabChaine[1].replace("\"", ""));
                            newDb.setDblist_instance(tabChaine[2].replace("\"", ""));
                            newDb.setDblist_user(tabChaine[3].replace("\"", ""));
                            newDb.setDblist_password(tabChaine[4].replace("\"", ""));
                            newDb.setDblist_sgbd(tabChaine[5].replace("\"", ""));
                            mf.saveServersAndTestToXML(newDb);

                            // Boucle sur toute les tâches
                            for (TaskObj tsk : listOfTasks.getDbObjListe()) {
                                System.out.println("Task : " + tsk.getTASK_LIBELLE());
                                for (DatabaseObj db : tsk.getTASK_SERVERSLIST()) {
                                    System.out.println("Database : " + db.getDblist_libelle());
                                    if (db.getDblist_libelle().equals(newDb.getDblist_libelle())) {
                                        db.setDblist_ip(newDb.getDblist_ip());
                                    }
                                }
                            }

                        } else if (chaine.contains((";"))) {

                            String[] tabChaine = chaine.split(";");

                            // Création d'une nouvelle entrée
                            DatabaseObj newDb = new DatabaseObj();
                            newDb.setDblist_libelle(tabChaine[0].toString().replace("\"", ""));
                            newDb.setDblist_ip(tabChaine[1].replace("\"", ""));
                            newDb.setDblist_instance(tabChaine[2].replace("\"", ""));
                            newDb.setDblist_user(tabChaine[3].replace("\"", ""));
                            newDb.setDblist_password(tabChaine[4].replace("\"", ""));
                            newDb.setDblist_sgbd(tabChaine[5].replace("\"", ""));
                            mf.saveServersAndTestToXML(newDb);

                            // On test si le serveur existe dans une tâche. Si c'est le cas, on le met à jour

                            // Boucle sur toute les tâches
                            for (TaskObj tsk : listOfTasks.getDbObjListe()) {
                                System.out.println("Task : " + tsk.getTASK_LIBELLE());
                                for (DatabaseObj db : tsk.getTASK_SERVERSLIST()) {
                                    System.out.println("Database : " + db.getDblist_libelle());
                                    if (db.getDblist_libelle().equals(newDb.getDblist_libelle())) {
                                        db.setDblist_ip(newDb.getDblist_ip());
                                    }
                                }
                            }

                        }
                        i++;
                    }
                    mf.serialiseServersTOXML();
                    mf.serialiseTasksTOXML();
                    fichier_source.close();
                    setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    JOptionPane.showMessageDialog(null, "Import des données terminé !", "Andjety", JOptionPane.INFORMATION_MESSAGE);
                    //em.close();

                    this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

                } catch (IOException ex) {
                    Logger.getLogger(DatabaseImportDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jButtonValiderActionPerformed

    private void jRadioButtonReplaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonReplaceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonReplaceActionPerformed

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
            java.util.logging.Logger.getLogger(DatabaseImportDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatabaseImportDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatabaseImportDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatabaseImportDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                DatabaseImportDialog dialog = new DatabaseImportDialog(new javax.swing.JFrame(), true, null, null);
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
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonValider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton jRadioButtonBaseMySQL;
    private javax.swing.JRadioButton jRadioButtonCSV;
    private javax.swing.JRadioButton jRadioButtonIgnore;
    private javax.swing.JRadioButton jRadioButtonReplace;
    private javax.swing.JRadioButton jRadioButtonXML;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}