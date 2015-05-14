/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Paul
 */
@Entity
@Table(name = "SCHEDULETASKS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Scheduletasks.findAll", query = "SELECT s FROM Scheduletasks s"),
    @NamedQuery(name = "Scheduletasks.findById", query = "SELECT s FROM Scheduletasks s WHERE s.id = :id"),
    @NamedQuery(name = "Scheduletasks.findByIdtask", query = "SELECT s FROM Scheduletasks s WHERE s.idtask = :idtask"),
    @NamedQuery(name = "Scheduletasks.findByIfalldays", query = "SELECT s FROM Scheduletasks s WHERE s.ifalldays = :ifalldays"),
    @NamedQuery(name = "Scheduletasks.findByIflundi", query = "SELECT s FROM Scheduletasks s WHERE s.iflundi = :iflundi"),
    @NamedQuery(name = "Scheduletasks.findByIfmardi", query = "SELECT s FROM Scheduletasks s WHERE s.ifmardi = :ifmardi"),
    @NamedQuery(name = "Scheduletasks.findByIfmercredi", query = "SELECT s FROM Scheduletasks s WHERE s.ifmercredi = :ifmercredi"),
    @NamedQuery(name = "Scheduletasks.findByIfjeudi", query = "SELECT s FROM Scheduletasks s WHERE s.ifjeudi = :ifjeudi"),
    @NamedQuery(name = "Scheduletasks.findByIfvendredi", query = "SELECT s FROM Scheduletasks s WHERE s.ifvendredi = :ifvendredi"),
    @NamedQuery(name = "Scheduletasks.findByIfsamedi", query = "SELECT s FROM Scheduletasks s WHERE s.ifsamedi = :ifsamedi"),
    @NamedQuery(name = "Scheduletasks.findByIfdimanche", query = "SELECT s FROM Scheduletasks s WHERE s.ifdimanche = :ifdimanche"),
    @NamedQuery(name = "Scheduletasks.findByMinutes", query = "SELECT s FROM Scheduletasks s WHERE s.minutes = :minutes"),
    @NamedQuery(name = "Scheduletasks.findByHeures", query = "SELECT s FROM Scheduletasks s WHERE s.heures = :heures"),
    @NamedQuery(name = "Scheduletasks.findByAffichexec", query = "SELECT s FROM Scheduletasks s WHERE s.affichexec = :affichexec")})
public class Scheduletasks implements Serializable {
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
    @Column(name = "IFALLDAYS")
    private String ifalldays;
    @Basic(optional = false)
    @Column(name = "IFLUNDI")
    private int iflundi;
    @Basic(optional = false)
    @Column(name = "IFMARDI")
    private int ifmardi;
    @Basic(optional = false)
    @Column(name = "IFMERCREDI")
    private int ifmercredi;
    @Basic(optional = false)
    @Column(name = "IFJEUDI")
    private int ifjeudi;
    @Basic(optional = false)
    @Column(name = "IFVENDREDI")
    private int ifvendredi;
    @Basic(optional = false)
    @Column(name = "IFSAMEDI")
    private int ifsamedi;
    @Basic(optional = false)
    @Column(name = "IFDIMANCHE")
    private int ifdimanche;
    @Basic(optional = false)
    @Column(name = "MINUTES")
    private int minutes;
    @Basic(optional = false)
    @Column(name = "HEURES")
    private int heures;
    @Column(name = "AFFICHEXEC")
    private String affichexec;

    public Scheduletasks() {
    }

    public Scheduletasks(Integer id) {
        this.id = id;
    }

    public Scheduletasks(Integer id, int idtask, String ifalldays, int iflundi, int ifmardi, int ifmercredi, int ifjeudi, int ifvendredi, int ifsamedi, int ifdimanche, int minutes, int heures, String ifaffichexec) {
        this.id = id;
        this.idtask = idtask;
        this.ifalldays = ifalldays;
        this.iflundi = iflundi;
        this.ifmardi = ifmardi;
        this.ifmercredi = ifmercredi;
        this.ifjeudi = ifjeudi;
        this.ifvendredi = ifvendredi;
        this.ifsamedi = ifsamedi;
        this.ifdimanche = ifdimanche;
        this.minutes = minutes;
        this.heures = heures;
        this.affichexec = ifaffichexec;
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

    public String getIfalldays() {
        return ifalldays;
    }

    public void setIfalldays(String ifalldays) {
        this.ifalldays = ifalldays;
    }

    public int getIflundi() {
        return iflundi;
    }

    public void setIflundi(int iflundi) {
        this.iflundi = iflundi;
    }

    public int getIfmardi() {
        return ifmardi;
    }

    public void setIfmardi(int ifmardi) {
        this.ifmardi = ifmardi;
    }

    public int getIfmercredi() {
        return ifmercredi;
    }

    public void setIfmercredi(int ifmercredi) {
        this.ifmercredi = ifmercredi;
    }

    public int getIfjeudi() {
        return ifjeudi;
    }

    public void setIfjeudi(int ifjeudi) {
        this.ifjeudi = ifjeudi;
    }

    public int getIfvendredi() {
        return ifvendredi;
    }

    public void setIfvendredi(int ifvendredi) {
        this.ifvendredi = ifvendredi;
    }

    public int getIfsamedi() {
        return ifsamedi;
    }

    public void setIfsamedi(int ifsamedi) {
        this.ifsamedi = ifsamedi;
    }

    public int getIfdimanche() {
        return ifdimanche;
    }

    public void setIfdimanche(int ifdimanche) {
        this.ifdimanche = ifdimanche;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getHeures() {
        return heures;
    }

    public void setHeures(int heures) {
        this.heures = heures;
    }

    public String getAffichexec() {
        return affichexec;
    }

    public void setAffichexec(String affichexec) {
        this.affichexec = affichexec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
       
        if (!(object instanceof Scheduletasks)) {
            return false;
        }
        Scheduletasks other = (Scheduletasks) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Scheduletasks[ id=" + id + " ]";
    }
    
}
