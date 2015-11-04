/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.models;

import com.mobissime.andjety.utils.OptionsParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import javax.swing.table.AbstractTableModel;
import com.mobissime.andjety.objects.RunningTasks;

/**
 *
 * @author Paul
 */
public class TasksExecTableModel extends AbstractTableModel {

    private int startPosition;
    private List<RunningTasks> theList;
    private int counter = 0;
    private Properties properties = new Properties();

    public TasksExecTableModel(List<RunningTasks> lst) {
 
        this.theList = lst;
        this.startPosition = 0;
        this.theList = getItems(startPosition, startPosition + 100);
        
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

    @Override
    public String getColumnName(int col) {
        String columnName = "vide";
        if (col == 0) {
            columnName = properties.getProperty("Date / Heure"); 
        } else if (col == 1) {
            columnName = properties.getProperty("pourcentageTitle"); 
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
            this.theList = getItems(rowIndex, rowIndex + 100);
            this.startPosition = rowIndex;
        }
        RunningTasks c = theList.get(rowIndex - startPosition);

        Object toReturn = null;
        switch (columnIndex) {
            case 0:
                toReturn = c.getTaskName();

                break;
            case 1:

                String str[] = c.getTaskPourcentage().split(",");
                toReturn = Integer.valueOf(str[0]);
                break;

            default:
                toReturn = c.getTaskId();

        }
        return toReturn;
    }

    private List<RunningTasks> getItems(int from, int to) {
        //add the cache
        return theList;
    }
}