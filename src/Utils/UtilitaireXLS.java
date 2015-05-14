/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package Utils;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author Pokitos Fonctionne avec la library POI.
 * (http://poi.apache.org/hssf/index.html); Cette classe permet d'importer un
 * fichier excel (sauf de 2007) dans un jtable.
 */
public class UtilitaireXLS {

    private static File fichier;
    
    public UtilitaireXLS() {
    }

    public static JTable CreerJTableAvecExcel(File file) throws FileNotFoundException, IOException {
        //Créé nouvelle table
        fichier = file;
        JTable table = new JTable();
        //Lecture du fichier excel
        InputStream inp = new FileInputStream(file);
        HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
        //Recupére page 1 du fichier xls
        HSSFSheet sheet = wb.getSheetAt(0);
        //compte nombre de lignes
        int Nrow = sheet.getLastRowNum();
        System.out.println("nr " + Nrow);
        //compte nombre de colonnes
        int st = NombreMaxColonne(sheet);
        System.out.println(st);
        //crée nouveau tableau d'objet
        Object[][] o = new Object[Nrow][st];
        //en tête du tableau
        String[] titre = new String[st];
        for (int i = 0; i < st; i++) {
            titre[i] = "#" + i;
        }
        //parcours la feuille et on recupère les lignes une par une
        for (int i = 0; i < Nrow; i++) {
            HSSFRow row = sheet.getRow(i);
            //parcours la ligne pour récupérer les colonnes
            if (row != null) {
                for (int j = 0; j < st; j++) {
                    //Récupère la cellule puis sa valeur
                    HSSFCell cell = row.getCell((short) j);
                    Object value = ContenuCellule(cell);
                    //System.out.println(value.toString());
                    o[i][j] = value;
                }
            }
        }
        inp.close();
        table.setModel(new DefaultTableModel(o, titre));
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
    //exemple

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    //On peut utiliser JFileChooser() aussi.
                    File cible = new File("C:\\temp\\essai.xls");
                    JTable tableur = UtilitaireXLS.CreerJTableAvecExcel(cible);
                    tableur.setFont(new Font(Font.SERIF, Font.PLAIN, 10));
                    JFrame cadre = new JFrame();
                    cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    cadre.setVisible(true);
                    cadre.setSize(800, 200);
                    JScrollPane panel = new JScrollPane();
                    panel.setViewportView(tableur);
                    cadre.add(panel);
                    //Si vous souhaitez imprimer un jtable utlisez ceci.
                    //MessageFormat header = new MessageFormat("Page {0,number,integer}");
                    //tableur.print(JTable.PrintMode.FIT_WIDTH);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(UtilitaireXLS.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(UtilitaireXLS.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
