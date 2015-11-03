/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobissime.andjety.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Paul
 */
@Entity
@Table(name = "VUEDBTASKLISTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vuedbtaskliste.findAll", query = "SELECT v FROM Vuedbtaskliste v"),
    @NamedQuery(name = "Vuedbtaskliste.findByDblistInstance", query = "SELECT v FROM Vuedbtaskliste v WHERE v.dblistInstance = :dblistInstance"),
    @NamedQuery(name = "Vuedbtaskliste.findByDblistIp", query = "SELECT v FROM Vuedbtaskliste v WHERE v.dblistIp = :dblistIp"),
    @NamedQuery(name = "Vuedbtaskliste.findByDblistLibelle", query = "SELECT v FROM Vuedbtaskliste v WHERE v.dblistLibelle = :dblistLibelle"),
    @NamedQuery(name = "Vuedbtaskliste.findByDblistPassword", query = "SELECT v FROM Vuedbtaskliste v WHERE v.dblistPassword = :dblistPassword"),
    @NamedQuery(name = "Vuedbtaskliste.findByDblistUser", query = "SELECT v FROM Vuedbtaskliste v WHERE v.dblistUser = :dblistUser"),
    @NamedQuery(name = "Vuedbtaskliste.findByIdDb", query = "SELECT v FROM Vuedbtaskliste v WHERE v.idDb = :idDb"),
    @NamedQuery(name = "Vuedbtaskliste.findByIdTask", query = "SELECT v FROM Vuedbtaskliste v WHERE v.idTask = :idTask")})
public class Vuedbtaskliste implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "DBLIST_INSTANCE")
    private String dblistInstance;
    @Column(name = "DBLIST_IP")
    private String dblistIp;
    @Column(name = "DBLIST_LIBELLE")
    private String dblistLibelle;
    @Column(name = "DBLIST_PASSWORD")
    private String dblistPassword;
    @Column(name = "DBLIST_USER")
    private String dblistUser;
    @Column(name = "ID_DB")
    @Id
    private Integer idDb;
    @Column(name = "ID_TASK")
    private Integer idTask;

    public Vuedbtaskliste() {
    }

    public String getDblistInstance() {
        return dblistInstance;
    }

    public void setDblistInstance(String dblistInstance) {
        this.dblistInstance = dblistInstance;
    }

    public String getDblistIp() {
        return dblistIp;
    }

    public void setDblistIp(String dblistIp) {
        this.dblistIp = dblistIp;
    }

    public String getDblistLibelle() {
        return dblistLibelle;
    }

    public void setDblistLibelle(String dblistLibelle) {
        this.dblistLibelle = dblistLibelle;
    }

    public String getDblistPassword() {
        return dblistPassword;
    }

    public void setDblistPassword(String dblistPassword) {
        this.dblistPassword = dblistPassword;
    }

    public String getDblistUser() {
        return dblistUser;
    }

    public void setDblistUser(String dblistUser) {
        this.dblistUser = dblistUser;
    }

    public Integer getIdDb() {
        return idDb;
    }

    public void setIdDb(Integer idDb) {
        this.idDb = idDb;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }
    
}
