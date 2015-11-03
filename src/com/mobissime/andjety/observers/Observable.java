/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package com.mobissime.andjety.observers;

/**
 *
 * @author Paul Coiffier
 */
public interface Observable {

    public void addObservateur(Observateur obs);

    public void updateObservateur();

    public void delObservateur();
}