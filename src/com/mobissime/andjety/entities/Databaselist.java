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
@Table(name = "databaselist")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Databaselist.findAll", query = "SELECT d FROM Databaselist d"),
    @NamedQuery(name = "Databaselist.findById", query = "SELECT d FROM Databaselist d WHERE d.id = :id"),
    @NamedQuery(name = "Databaselist.findByDblistLibelle", query = "SELECT d FROM Databaselist d WHERE d.dblistLibelle = :dblistLibelle"),
    @NamedQuery(name = "Databaselist.findByDblistIp", query = "SELECT d FROM Databaselist d WHERE d.dblistIp = :dblistIp"),
    @NamedQuery(name = "Databaselist.findByDblistInstance", query = "SELECT d FROM Databaselist d WHERE d.dblistInstance = :dblistInstance"),
    @NamedQuery(name = "Databaselist.findByDblistUser", query = "SELECT d FROM Databaselist d WHERE d.dblistUser = :dblistUser"),
    @NamedQuery(name = "Databaselist.findByDblistPassword", query = "SELECT d FROM Databaselist d WHERE d.dblistPassword = :dblistPassword")})
public class Databaselist implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "dblist_libelle")
    private String dblistLibelle;
    @Column(name = "dblist_ip")
    private String dblistIp;
    @Column(name = "dblist_instance")
    private String dblistInstance;
    @Column(name = "dblist_user")
    private String dblistUser;
    @Column(name = "dblist_password")
    private String dblistPassword;

    public Databaselist() {
    }

    public Databaselist(Integer id) {
        this.id = id;
    }

    public Databaselist(Integer id, String dblistLibelle) {
        this.id = id;
        this.dblistLibelle = dblistLibelle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDblistLibelle() {
        return dblistLibelle;
    }

    public void setDblistLibelle(String dblistLibelle) {
        this.dblistLibelle = dblistLibelle;
    }

    public String getDblistIp() {
        return dblistIp;
    }

    public void setDblistIp(String dblistIp) {
        this.dblistIp = dblistIp;
    }

    public String getDblistInstance() {
        return dblistInstance;
    }

    public void setDblistInstance(String dblistInstance) {
        this.dblistInstance = dblistInstance;
    }

    public String getDblistUser() {
        return dblistUser;
    }

    public void setDblistUser(String dblistUser) {
        this.dblistUser = dblistUser;
    }

    public String getDblistPassword() {
        return dblistPassword;
    }

    public void setDblistPassword(String dblistPassword) {
        this.dblistPassword = dblistPassword;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Databaselist)) {
            return false;
        }
        Databaselist other = (Databaselist) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Utils.Databaselist[ id=" + id + " ]";
    }
    
}
