/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package observateurs;

import Threads.TaskMotorThread;
import java.util.List;

/**
 *
 * @author Paul Coiffier
 */
public interface MainFenObervateur {
	public void update(List<TaskMotorThread> liste);
}
