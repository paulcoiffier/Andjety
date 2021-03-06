/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.windows;

import java.io.*;
import com.mobissime.andjety.objects.LogObj;
import com.mobissime.andjety.objects.LogObjDetails;

/**
 *
 * @author Paul Coiffier
 */
public class LogPanel extends javax.swing.JPanel {

    private LogObj leLog;

    /**
     * Creates new form LogPanel
     */
    public LogPanel(LogObj leLog) {
        initComponents();
        this.leLog = leLog;

        // Affichage du contenu du panel principal
        StringBuffer contents = new StringBuffer();

        String format = "dd/MM/yy HH:mm:ss";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
        java.util.Date date = new java.util.Date();

        contents.append("***************************************************" + "\n");
        contents.append("*                ANDJETY 2.0                      *" + "\n");
        contents.append("***************************************************" + "\n");
        contents.append(formater.format(leLog.getLogobj_dateexec())).append(" - Tâche : ").append(leLog.getLogobj_taskLibelle()).append("\n" + "\n");
        contents.append(formater.format(leLog.getLogobj_dateexec())).append(" - Date/heure de début des traitements : ").append(leLog.getLogobj_heuredeb()).append("\n");
        contents.append(formater.format(leLog.getLogobj_dateexec())).append(" - Date/heure de fin des traitements : ").append(leLog.getLogobj_heurefin()).append("\n" + "\n");

        contents.append(formater.format(leLog.getLogobj_dateexec())).append(" - Bases de données atteintes : ").append(leLog.getLogobj_nbServeursOk()).append("/").append(leLog.getLogobj_nbServeurs()).append("\n");
        contents.append(formater.format(leLog.getLogobj_dateexec())).append(" - Erreur(s) : ").append(leLog.getLogobj_nbServeursErreur()).append("\n" + "\n");

        contents.append(formater.format(leLog.getLogobj_dateexec())).append(" - Durée des traitements : ").append(leLog.getLogObj_dureeDesTraitements());

        jEditorPane1.setText(leLog.getLogobj_taskLibelle() + "\n");
        jEditorPane1.setText(contents.toString());

        // Affichage du contenu du panel détails

        StringBuffer contentsDetails = new StringBuffer();

        contentsDetails.append("***************************************************" + "\n");
        contentsDetails.append("*                ANDJETY 2.0                      *" + "\n");
        contentsDetails.append("***************************************************" + "\n");

        for (LogObjDetails lg : leLog.getArrayOfLogsDetails()) {
            contentsDetails.append(lg.getLogobj_heuredeb()).append(" - " + "Connexion à la base de données : ").append(lg.getLogobjd_database()).append("\n");
            contentsDetails.append(lg.getLogobj_heuredeb()).append(" - ").append(lg.getLogobj_resultat()).append("\n");
            contentsDetails.append("***************************************************" + "\n");
        }

        jEditorPaneDetails.setText(contentsDetails.toString());

        /*
         * this.logDetailsFile = logDetailsFilVal; this.logStdFile =
         * logStdFileVal;
         *
         * File file = new File(logStdFile); File file2 = new
         * File(logDetailsFilVal);
         *
         * StringBuffer contents = new StringBuffer(); StringBuffer contents2 =
         * new StringBuffer();
         *
         * BufferedReader reader = null;
         *
         * try { reader = new BufferedReader(new FileReader(file)); String text
         * = null;
         *
         * while ((text = reader.readLine()) != null) {
         * contents.append(text).append(System.getProperty( "line.separator"));
         * } } catch (FileNotFoundException e) { e.printStackTrace(); } catch
         * (IOException e) { e.printStackTrace(); }
         *
         * // show file contents here System.out.println(contents.toString());
         * jEditorPane1.setText(contents.toString());
         *
         * try { reader = new BufferedReader(new FileReader(file2)); String text
         * = null;
         *
         * while ((text = reader.readLine()) != null) {
         * contents2.append(text).append(System.getProperty( "line.separator"));
         * } } catch (FileNotFoundException e) { e.printStackTrace(); } catch
         * (IOException e) { e.printStackTrace(); }
         */

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPaneLog = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPaneDetails = new javax.swing.JEditorPane();

        jEditorPane1.setEditable(false);
        jScrollPane1.setViewportView(jEditorPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
        );

        jTabbedPaneLog.addTab("Log", jPanel1);

        jEditorPaneDetails.setEditable(false);
        jScrollPane2.setViewportView(jEditorPaneDetails);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
        );

        jTabbedPaneLog.addTab("Détails", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneLog)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneLog)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JEditorPane jEditorPaneDetails;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPaneLog;
    // End of variables declaration//GEN-END:variables
}
