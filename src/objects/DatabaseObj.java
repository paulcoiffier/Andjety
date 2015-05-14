/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package objects;

/**
 *
 * @author Paul Coiffier
 */
public class DatabaseObj {

    private String dblist_libelle;
    private String dblist_ip;
    private String dblist_instance;
    private String dblist_user;
    private String dblist_password;
    private String dblist_sgbd;

    public DatabaseObj() {
    }

    public DatabaseObj(String dblist_libelle, String dblist_ip, String dblist_instance, String dblist_user, String dblist_password, String dblist_sgbd) {
        this.dblist_libelle = dblist_libelle;
        this.dblist_ip = dblist_ip;
        this.dblist_instance = dblist_instance;
        this.dblist_user = dblist_user;
        this.dblist_password = dblist_password;
        this.dblist_sgbd = dblist_sgbd;
    }

    public String getDblist_sgbd() {
        return dblist_sgbd;
    }

    public void setDblist_sgbd(String dblist_sgbd) {
        this.dblist_sgbd = dblist_sgbd;
    }

    public String getDblist_instance() {
        return dblist_instance;
    }

    public void setDblist_instance(String dblist_instance) {
        this.dblist_instance = dblist_instance;
    }

    public String getDblist_ip() {
        return dblist_ip;
    }

    public void setDblist_ip(String dblist_ip) {
        this.dblist_ip = dblist_ip;
    }

    public String getDblist_libelle() {
        return dblist_libelle;
    }

    public void setDblist_libelle(String dblist_libelle) {
        this.dblist_libelle = dblist_libelle;
    }

    public String getDblist_password() {
        return dblist_password;
    }

    public void setDblist_password(String dblist_password) {
        this.dblist_password = dblist_password;
    }

    public String getDblist_user() {
        return dblist_user;
    }

    public void setDblist_user(String dblist_user) {
        this.dblist_user = dblist_user;
    }
}
