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
@Table(name = "TASKSEXEC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tasksexec.findAll", query = "SELECT t FROM Tasksexec t"),
    @NamedQuery(name = "Tasksexec.findById", query = "SELECT t FROM Tasksexec t WHERE t.id = :id"),
    @NamedQuery(name = "Tasksexec.findByIdtask", query = "SELECT t FROM Tasksexec t WHERE t.idtask = :idtask"),
    @NamedQuery(name = "Tasksexec.findByIdscheduletask", query = "SELECT t FROM Tasksexec t WHERE t.idscheduletask = :idscheduletask"),
    @NamedQuery(name = "Tasksexec.findByDateheure", query = "SELECT t FROM Tasksexec t WHERE t.dateheure = :dateheure"),
    @NamedQuery(name = "Tasksexec.findByResultat", query = "SELECT t FROM Tasksexec t WHERE t.resultat = :resultat"),
    @NamedQuery(name = "Tasksexec.findByLogresult", query = "SELECT t FROM Tasksexec t WHERE t.logresult = :logresult"),
    @NamedQuery(name = "Tasksexec.findByLogdetails", query = "SELECT t FROM Tasksexec t WHERE t.logdetails = :logdetails")})
public class Tasksexec implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "IDTASK")
    private int idtask;
    @Basic(optional = false)
    @Column(name = "IDSCHEDULETASK")
    private int idscheduletask;
    @Basic(optional = false)
    @Column(name = "DATEHEURE")
    private String dateheure;
    @Basic(optional = false)
    @Column(name = "RESULTAT")
    private String resultat;
    @Column(name = "LOGRESULT")
    private String logresult;
    @Column(name = "LOGDETAILS")
    private String logdetails;

    public Tasksexec() {
    }

    public Tasksexec(Integer id) {
        this.id = id;
    }

    public Tasksexec(Integer id, int idtask, int idscheduletask, String dateheure, String resultat) {
        this.id = id;
        this.idtask = idtask;
        this.idscheduletask = idscheduletask;
        this.dateheure = dateheure;
        this.resultat = resultat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdtask() {
        return idtask;
    }

    public void setIdtask(int idtask) {
        this.idtask = idtask;
    }

    public int getIdscheduletask() {
        return idscheduletask;
    }

    public void setIdscheduletask(int idscheduletask) {
        this.idscheduletask = idscheduletask;
    }

    public String getDateheure() {
        return dateheure;
    }

    public void setDateheure(String dateheure) {
        this.dateheure = dateheure;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public String getLogresult() {
        return logresult;
    }

    public void setLogresult(String logresult) {
        this.logresult = logresult;
    }

    public String getLogdetails() {
        return logdetails;
    }

    public void setLogdetails(String logdetails) {
        this.logdetails = logdetails;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Tasksexec)) {
            return false;
        }
        Tasksexec other = (Tasksexec) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tasksexec[ id=" + id + " ]";
    }
    
}
