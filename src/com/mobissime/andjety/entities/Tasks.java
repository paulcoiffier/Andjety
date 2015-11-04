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
 * @author CoiffierP
 */
@Entity
@Table(name = "TASKS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tasks.findAll", query = "SELECT t FROM Tasks t"),
    @NamedQuery(name = "Tasks.findById", query = "SELECT t FROM Tasks t WHERE t.id = :id"),
    @NamedQuery(name = "Tasks.findByTaskAffichexec", query = "SELECT t FROM Tasks t WHERE t.taskAffichexec = :taskAffichexec"),
    @NamedQuery(name = "Tasks.findByTaskLibelle", query = "SELECT t FROM Tasks t WHERE t.taskLibelle = :taskLibelle"),
    @NamedQuery(name = "Tasks.findByTaskRequete", query = "SELECT t FROM Tasks t WHERE t.taskRequete = :taskRequete"),
    @NamedQuery(name = "Tasks.findByTaskResultat", query = "SELECT t FROM Tasks t WHERE t.taskResultat = :taskResultat"),
    @NamedQuery(name = "Tasks.findByTaskType", query = "SELECT t FROM Tasks t WHERE t.taskType = :taskType")})
public class Tasks implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TASK_AFFICHEXEC")
    private Integer taskAffichexec;
    @Column(name = "TASK_LIBELLE")
    private String taskLibelle;
    @Column(name = "TASK_REQUETE")
    private String taskRequete;
    @Column(name = "TASK_RESULTAT")
    private String taskResultat;
    @Column(name = "TASK_TYPE")
    private String taskType;

    public Tasks() {
    }

    public Tasks(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskAffichexec() {
        return taskAffichexec;
    }

    public void setTaskAffichexec(Integer taskAffichexec) {
        this.taskAffichexec = taskAffichexec;
    }

    public String getTaskLibelle() {
        return taskLibelle;
    }

    public void setTaskLibelle(String taskLibelle) {
        this.taskLibelle = taskLibelle;
    }

    public String getTaskRequete() {
        return taskRequete;
    }

    public void setTaskRequete(String taskRequete) {
        this.taskRequete = taskRequete;
    }

    public String getTaskResultat() {
        return taskResultat;
    }

    public void setTaskResultat(String taskResultat) {
        this.taskResultat = taskResultat;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tasks)) {
            return false;
        }
        Tasks other = (Tasks) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tasks[ id=" + id + " ]";
    }
    
}
