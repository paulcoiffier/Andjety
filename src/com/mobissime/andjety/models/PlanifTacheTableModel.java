/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.models;

/**
 *
 * @author Paul Coiffier
 */

import com.mobissime.andjety.utils.DBUtils;
import com.mobissime.andjety.entities.Scheduletasks;
import com.mobissime.andjety.entities.Tasks;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Paul
 */
public class PlanifTacheTableModel extends AbstractTableModel {

    private final EntityManager manager;
    private int startPosition;
    private List<Scheduletasks> theList;
    private int counter = 0;

    public PlanifTacheTableModel(EntityManager manager) {
        this.manager = manager;
        this.startPosition = 0;
        this.theList = getItems(startPosition, startPosition + 100);
    }

    public String getColumnName(int col) {
        String columnName = "vide";
        if(col==0){
           columnName = "Id"; 
        } 
        else if(col==1){
           columnName = "Tâche"; 
        }
        else if(col==2){
           columnName = "Heure"; 
        }
        else if(col==3){
           columnName = "Récurrent"; 
        }
        return columnName;
    }
    
    @Override
    public int getRowCount() {
        return ((Long) manager.createQuery("SELECT COUNT(s) FROM Scheduletasks s").getSingleResult()).intValue();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if ((rowIndex >= startPosition) && (rowIndex < (startPosition + 100))) {
        } else {
            this.theList = getItems(rowIndex, rowIndex + 100);
            this.startPosition = rowIndex;
        }
        Scheduletasks c = theList.get(rowIndex - startPosition);

        Object toReturn = null;
        switch (columnIndex) {
            case 0:
                toReturn = c.getId();    
                break;
            case 1:
                // On va cherche le libellé de la tâche
                DBUtils dbu = new DBUtils();
                Tasks theTmp = dbu.getTaskById(c.getIdtask(),manager);
                toReturn = theTmp.getTaskLibelle();   
                //toReturn = "test";
                break;
            case 2:
                toReturn = c.getHeures() + "h" + c.getMinutes();
                break;
            case 3:
                toReturn = c.getIfalldays();
                break;    
            default:
                toReturn = c.getId();

        }
        return toReturn;
    }

    private List<Scheduletasks> getItems(int from, int to) {

        Query query = manager.createQuery("SELECT s FROM Scheduletasks s").setMaxResults(to - from).setFirstResult(from);

        //add the cache
        List<Scheduletasks> resultList = query.getResultList();
        return resultList;
    }
}