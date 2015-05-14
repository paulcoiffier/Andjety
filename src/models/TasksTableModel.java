/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package models;

import Utils.OptionsParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.table.AbstractTableModel;
import objects.TaskObj;
import objects.TaskObjListe;

/**
 *
 * @author Paul Coiffier
 */
public class TasksTableModel extends AbstractTableModel {

    private TaskObjListe taskListObj;
    private int startPosition;
    private List<TaskObj> theList;
    private int counter = 0;
    private Properties properties = new Properties();

    public TasksTableModel(TaskObjListe taskListObj) {
        this.taskListObj = taskListObj;
        this.startPosition = 0;
        this.theList = getItems();
        
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
        
    }

    public String getColumnName(int col) {
        String columnName = "vide";
        if(col==0){
           columnName = properties.getProperty("libelleTitle"); 
        } 
        else if(col==1){
           columnName = properties.getProperty("sqlQueryTitle"); 
        }
      
        return columnName;
    }
    
    @Override
    public int getRowCount() {
        return theList.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if ((rowIndex >= startPosition) && (rowIndex < (startPosition + 100))) {
        } else {
            this.theList = getItems();
            this.startPosition = rowIndex;
        }
        TaskObj c = theList.get(rowIndex - startPosition);

        Object toReturn = null;
        switch (columnIndex) {
            case 0:
                toReturn = c.getTASK_LIBELLE();
                break;
            case 1:
                toReturn = c.getTASK_REQUETE();
                break;
            default:
                toReturn = c.getTASK_LIBELLE();

        }
        return toReturn;
    }

    private ArrayList<TaskObj> getItems() {

        ArrayList<TaskObj> resultList = taskListObj.getDbObjListe();
        return resultList;
    }
}