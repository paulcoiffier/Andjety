/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.windows;

import com.mobissime.andjety.utils.UtilitaireXLS;
import com.mobissime.andjety.MainFen;
import java.awt.Desktop;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author Paul Coiffier
 */
public class ExcelViewJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ExcelViewJPanel
     */
    private File fileToOpen;
    private static DefaultTableModel tblModel;

    public ExcelViewJPanel(File fs) {

        initComponents();

        fileToOpen = fs;

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {

                    File cible = new File(fileToOpen.getAbsoluteFile().toString());
                    jTable1 = CreerJTableAvecExcel(cible);

                    //jTable1.setModel(tblModel);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainFen.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainFen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public  JTable CreerJTableAvecExcel(File file) throws FileNotFoundException, IOException {
        //Créé nouvelle table
        JTable table = new JTable();
        //Lecture du fichier excel
        InputStream inp = new FileInputStream(file);
        HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
        //Recupére page 1 du fichier xls
        HSSFSheet sheet = wb.getSheetAt(0);
        //compte nombre de lignes
        int Nrow = sheet.getLastRowNum();
        //compte nombre de colonnes
        int st = NombreMaxColonne(sheet);
        //crée nouveau tableau d'objet
        Object[][] o = new Object[Nrow+1][st];
        //en tête du tableau
        String[] titre = new String[st];
        for (int i = 0; i < st; i++) {
            titre[i] = "#" + i;
        }
        
        System.out.println("Valeur de Nrow : " + Nrow);
        System.out.println("Nombre de colonnes : " + st);
        
        //parcours la feuille et on recupère les lignes une par une
        for (int i = 0; i <= Nrow; i++) {
            //System.out.println("Ligne trouvée");
            HSSFRow row = sheet.getRow(i);
            //parcours la ligne pour récupérer les colonnes
            if (row != null) {
                for (int j = 0; j < st; j++) {
                    //Récupère la cellule puis sa valeur
                    HSSFCell cell = row.getCell((short) j);
                    Object value = ContenuCellule(cell);
                    o[i][j] = value;
                }
            }
        }
        inp.close();
        jTable1.setModel(new DefaultTableModel(o, titre));
        //tblModel = new DefaultTableModel(o, titre);
        return table;
    }

    /**
     * La cellule peut contenir différent type de valeur qui doivent être
     * récupéré spécifiquement
     */
    private static Object ContenuCellule(HSSFCell cell) {
        Object value = null;
        if (cell == null) {
            value = "";
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
            value = cell.getBooleanCellValue();
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_ERROR) {
            value = cell.getErrorCellValue();
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
            value = cell.getCellFormula();
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            value = cell.getNumericCellValue();
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
            value = cell.getStringCellValue();
        }
        return value;
    }

    /**
     * Permet de récupérer le nombre maximun de colonne . Si une ligne comporte
     * 2 cases vides à la fin , celles ci ne sont pas prises en compte. Donc
     * pour pouvoir récupérer le nombre de colonne pour initialiser notre
     * tableau on doit parcourir toutes les lignes
     *
     */
    private static int NombreMaxColonne(HSSFSheet sheet) {
        int r = sheet.getLastRowNum();
        int max = 0;
        int s = 0;
        while (s < r) {
            if (sheet.getRow(s) != null) {
                int c = sheet.getRow(s).getLastCellNum();
                if (c > max) {
                    max = c;
                }
            }
            s++;
        }
        return max + 1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jButton2 = new javax.swing.JButton();

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
        jScrollPane1.setViewportView(jTable1);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ressources/gnome_media_floppy.png"))); // NOI18N
        jButton2.setToolTipText("Ouvrir dans Excel");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            Desktop.getDesktop().open(fileToOpen);
        } catch (IOException ex) {
            Logger.getLogger(TaskMotor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
