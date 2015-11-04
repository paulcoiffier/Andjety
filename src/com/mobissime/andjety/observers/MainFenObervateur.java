/*
 * Andjety 3.0 - Paul Coiffier - 2012 - 2015
 * 
 */
package com.mobissime.andjety.observers;

import com.mobissime.andjety.threads.TaskMotorThread;
import java.util.List;

/**
 *
 * @author Paul Coiffier
 */
public interface MainFenObervateur {
	public void update(List<TaskMotorThread> liste);
}
