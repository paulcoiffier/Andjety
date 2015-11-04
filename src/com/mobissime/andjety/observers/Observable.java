/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
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