/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.utils;

import com.mobissime.andjety.dbUtils.DerbyConnexion;
import com.mobissime.andjety.entities.Databaselist;
import com.mobissime.andjety.entities.Scheduletasks;
import com.mobissime.andjety.entities.Tasks;
import com.mobissime.andjety.entities.Tasksexec;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Paul Coiffier
 */
public class DBUtils {

    private EntityManager em;

    public String getDbId(String dbIntitule) {
        String dbId = null;
        try {
            DerbyConnexion theCon = new DerbyConnexion();
            Connection dbCon = theCon.getMysqlCon();

            Statement statement = dbCon.createStatement();
            ResultSet rs = statement.executeQuery("Select id from databaselist where dblist_libelle='" + dbIntitule + "'");
            while (rs.next()) {
                dbId = String.valueOf(rs.getInt("id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dbId;
    }

    public String getTaskId(String dbIntitule) {
        String dbId = null;
        try {
            DerbyConnexion theCon = new DerbyConnexion();
            Connection dbCon = theCon.getMysqlCon();

            Statement statement = dbCon.createStatement();
            ResultSet rs = statement.executeQuery("Select id from tasks where task_libelle='" + dbIntitule + "'");
            while (rs.next()) {
                dbId = String.valueOf(rs.getInt("id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dbId;
    }

    public Databaselist getDatabase(String idClient) {

        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("AndjetyPU");
        em = emf.createEntityManager();

        Query query = em.createQuery("SELECT c FROM Databaselist c where c.dblistLibelle = '" + idClient + "'");
        Databaselist clientToReturn = (Databaselist) query.getSingleResult();

        return clientToReturn;
    }

    public int ifDatabaseExist(String dblistLibelle, EntityManager em) {
        int nbResults = ((Long) em.createQuery("SELECT COUNT(c) FROM Databaselist c where c.dblistLibelle = '" + dblistLibelle + "'").getSingleResult()).intValue();
        return nbResults;
    }

    public Tasks getTask(String idClient) {

        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("AndjetyPU");
        em = emf.createEntityManager();

        Query query = em.createQuery("SELECT c FROM Tasks c where c.taskLibelle  = '" + idClient + "'");
        Tasks clientToReturn = (Tasks) query.getSingleResult();

        return clientToReturn;
    }

    public Tasks getTaskWithEm(String idClient, EntityManager emm) {

        Query query = emm.createQuery("SELECT c FROM Tasks c where c.taskLibelle  = '" + idClient + "'");
        Tasks clientToReturn = (Tasks) query.getSingleResult();

        return clientToReturn;
    }

    public Scheduletasks getScheduleTask(String libelleTask, String dateDeb) {

        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("AndjetyPU");
        em = emf.createEntityManager();

        Query query = em.createQuery("SELECT s FROM Scheduletasks s where s.idtask  = " + libelleTask + " and s.heureschedule  = '" + dateDeb + "'");
        Scheduletasks clientToReturn = (Scheduletasks) query.getSingleResult();

        return clientToReturn;
    }

    public Scheduletasks getScheduleTaskById(String idTask) {

        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("AndjetyPU");
        em = emf.createEntityManager();

        Query query = em.createQuery("SELECT s FROM Scheduletasks s where s.id = " + idTask);
        Scheduletasks clientToReturn = (Scheduletasks) query.getSingleResult();

        return clientToReturn;
    }

    public Tasks getTaskById(int idTask, EntityManager emm) {

        Query query = emm.createQuery("SELECT c FROM Tasks c where c.id  = " + idTask + "");
        Tasks clientToReturn = (Tasks) query.getSingleResult();

        return clientToReturn;
    }

    public List<Scheduletasks> getTaskForSchedule(int heure, int minute, EntityManager emm) {

        Query query = emm.createQuery("SELECT s FROM Scheduletasks s where s.heures  = " + heure + " and s.minutes = " + minute);
        List<Scheduletasks> clientToReturn = query.getResultList();

        return clientToReturn;
    }

    public List<Scheduletasks> getListOfScheduleTask() {

        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("AndjetyPU");
        EntityManager emm = emf.createEntityManager();

        Query query = emm.createQuery("SELECT s FROM Scheduletasks s");
        List<Scheduletasks> clientToReturn = query.getResultList();

        return clientToReturn;
    }
    
    public Tasksexec getTasksExecByTaskDate(String idScheduleTask, EntityManager emm) {

        Query query = emm.createQuery("SELECT t FROM Tasksexec t WHERE t.dateheure = '" + idScheduleTask+"'");
        Tasksexec clientToReturn = (Tasksexec) query.getSingleResult();
        System.out.println("REturn : " + clientToReturn.getLogdetails());
        return clientToReturn;
    }
}
