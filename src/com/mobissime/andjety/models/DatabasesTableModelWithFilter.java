/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.models;

import com.mobissime.andjety.utils.OptionsParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.table.AbstractTableModel;
import com.mobissime.andjety.objects.DatabaseObj;

public class DatabasesTableModelWithFilter extends AbstractTableModel {

    private ArrayList<DatabaseObj> dbListe;
    private int startPosition;
    private ArrayList<DatabaseObj> theList;
    private Properties properties = new Properties();

    public DatabasesTableModelWithFilter(ArrayList<DatabaseObj> dbListe) {
        //System.out.println("Initialise DatabasesTableModelWithFilter");
        this.dbListe = dbListe;
        this.startPosition = 0;
        this.theList = getItems();

        // Définition de la langue
        OptionsParser optionsParse = new OptionsParser();
        optionsParse.parseXml();
        String languageValue = optionsParse.languageValue;
        InputStream stream = null;

        if (languageValue.equals("english")) {
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("languages/english.properties");

        } else if (languageValue.toUpperCase().contains("FRA")) {
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("languages/french.properties");
        } else {
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("languages/english.properties");
        }

        try {
            properties.load(stream);
        } catch (IOException e) {
            System.out.println(properties.getProperty("errorOpenFile"));
        }
    }

    @Override
    public String getColumnName(int col) {
        String columnName = "vide";
        if (col == 0) {
            columnName = properties.getProperty("libelleTitle");
        } else if (col == 1) {
            columnName = properties.getProperty("DatabaseEditorLabel1");
        } else if (col == 2) {
            columnName = properties.getProperty("DatabaseEditorLabel2");
        } else if (col == 3) {
            columnName = properties.getProperty("DatabaseEditorLabel3");
        } else if (col == 4) {
            columnName = properties.getProperty("DatabaseEditorLabel4");
        }
        return columnName;
    }

    @Override
    public int getRowCount() {
        return theList.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if ((rowIndex >= startPosition) && (rowIndex < (startPosition + 100))) {
        } else {
            this.theList = getItems();
            this.startPosition = rowIndex;
        }
        DatabaseObj c = theList.get(rowIndex - startPosition);

        Object toReturn = null;
        switch (columnIndex) {
            case 0:
                toReturn = c.getDblist_libelle();

                break;
            case 1:
                toReturn = c.getDblist_ip();
                break;
            case 2:
                toReturn = c.getDblist_instance();
                break;
            case 3:
                toReturn = c.getDblist_user();
                break;
            case 4:
                toReturn = c.getDblist_password();
                break;
            default:
                toReturn = c.getDblist_libelle();

        }
        return toReturn;
    }

    private ArrayList<DatabaseObj> getItems() {
        return dbListe;
    }
}