/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.utils;

import java.io.File;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * @author Paul Coiffier
 */
public class ExcelSheetWriter {

    /**
     * This method is used to write data to an excel file.
     *
     * @param fileName - Name of the excel file.
     */
    private static File fileName;
    private static List<String> LstColonnes;
    private static List<ArrayList<String>> LstLignes;

    private void writeExcelFile(String fileName) {
        FileOutputStream fileOutputStream = null;
        HSSFWorkbook sampleWorkbook = null;
        HSSFSheet sampleDataSheet = null;
        try {
            /**
             * Create a new instance for HSSFWorkBook class and create a sample
             * worksheet using HSSFSheet class to write data.
             */
            sampleWorkbook = new HSSFWorkbook();
            sampleDataSheet = sampleWorkbook.createSheet("SampleDataSheet1");
            /**
             * Create two rows using HSSFRow class, where headerRow denotes the
             * header and the dataRow1 denotes the cell data.
             */
            HSSFRow headerRow = sampleDataSheet.createRow(0);
            
            HSSFCellStyle cellStyle = setHeaderStyle(sampleWorkbook);

            /**
             * Call the setHeaderStyle method and set the styles for the all the
             * three header cells.
             */
            /*
             * HSSFCell firstHeaderCell = headerRow.createCell(0);
             * firstHeaderCell.setCellStyle(cellStyle);
             * firstHeaderCell.setCellValue(new HSSFRichTextString("Employer
             * Name")); HSSFCell secondHeaderCell = headerRow.createCell(1);
             * secondHeaderCell.setCellStyle(cellStyle);
             * secondHeaderCell.setCellValue(new
             * HSSFRichTextString("Designation")); HSSFCell thirdHeaderCell =
             * headerRow.createCell(2); thirdHeaderCell.setCellStyle(cellStyle);
             * thirdHeaderCell.setCellValue(new HSSFRichTextString("Country"));
             */
            //ArrayList list = new ArrayList();
            Set set = new HashSet();
            set.addAll(LstColonnes);
            ArrayList distinctList = new ArrayList(set);

            for (int i = 0; i < distinctList.size(); i++) {
                HSSFCell firstHeaderCell = headerRow.createCell(i);
                firstHeaderCell.setCellStyle(cellStyle);
                firstHeaderCell.setCellValue(new HSSFRichTextString(LstColonnes.get(i).toString()));
            }

            // On compte le nombre de lignes
            //System.out.println("Nombre de lignes : " + LstLignes.size());

            int cpt=1;
            for (ArrayList p : LstLignes) {
                HSSFRow dataRow1 = sampleDataSheet.createRow(cpt);
                for (int i = 0; i < p.size(); i++) {
                    //System.out.println("[" + LstLignes.indexOf(p) + "]" + "[" + p.indexOf(p.get(i)) + "]  =" + p.get(i));
                    //System.out.println("Ajoute " + p.get(i).toString());
                    dataRow1.createCell(i).setCellValue(new HSSFRichTextString(p.get(i).toString()));
                };
                cpt++;
            }

            fileOutputStream = new FileOutputStream(fileName);
            sampleWorkbook.write(fileOutputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            /**
             * Close the fileOutputStream.
             */
            
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * This method is used to set the styles for all the headers of the excel
     * sheet.
     *
     * @param sampleWorkBook - Name of the workbook.
     * @return cellStyle - Styles for the Header data of Excel sheet.
     */
    private HSSFCellStyle setHeaderStyle(HSSFWorkbook sampleWorkBook) {
        HSSFFont font = sampleWorkBook.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL);
        font.setColor(IndexedColors.PLUM.getIndex());
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFCellStyle cellStyle = sampleWorkBook.createCellStyle();
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static void main(String[] args, File fs, List<String> colonnes, List<ArrayList<String>> lignes) {
        fileName = fs;
        LstColonnes = colonnes;
        LstLignes = lignes;

        new ExcelSheetWriter().writeExcelFile(fileName.getAbsolutePath().toString());
    }
}