/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author Polo
 */
public class connectionObj {

    private String dbName;
    private String dbUser;
    private String dbPassword;
    private String dbServeur;
    private String dbDatabase;
    private String dbType;

    public connectionObj() {
    }

    public connectionObj(String dbName, String dbUser, String dbPassword, String dbServeur, String dbDatabase, String dbType) {
        this.dbName = dbName;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        this.dbServeur = dbServeur;
        this.dbDatabase = dbDatabase;
        this.dbType = dbType;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbDatabase() {
        return dbDatabase;
    }

    public void setDbDatabase(String dbDatabase) {
        this.dbDatabase = dbDatabase;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbServeur() {
        return dbServeur;
    }

    public void setDbServeur(String dbServeur) {
        this.dbServeur = dbServeur;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }
}
