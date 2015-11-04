/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
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
@Table(name = "tasksdb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tasksdb.findAll", query = "SELECT t FROM Tasksdb t"),
    @NamedQuery(name = "Tasksdb.findById", query = "SELECT t FROM Tasksdb t WHERE t.id = :id"),
    @NamedQuery(name = "Tasksdb.findByIdTask", query = "SELECT t FROM Tasksdb t WHERE t.idTask = :idTask"),
    @NamedQuery(name = "Tasksdb.findByIdDb", query = "SELECT t FROM Tasksdb t WHERE t.idDb = :idDb")})
public class Tasksdb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_task")
    private int idTask;
    @Basic(optional = false)
    @Column(name = "id_db")
    private int idDb;

    public Tasksdb() {
    }

    public Tasksdb(Integer id) {
        this.id = id;
    }

    public Tasksdb(Integer id, int idTask, int idDb) {
        this.id = id;
        this.idTask = idTask;
        this.idDb = idDb;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public int getIdDb() {
        return idDb;
    }

    public void setIdDb(int idDb) {
        this.idDb = idDb;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Tasksdb)) {
            return false;
        }
        Tasksdb other = (Tasksdb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Utils.Tasksdb[ id=" + id + " ]";
    }
    
}
