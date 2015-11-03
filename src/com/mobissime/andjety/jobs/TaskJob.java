/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package com.mobissime.andjety.jobs;

import com.mobissime.andjety.MainFen;
import com.mobissime.andjety.entities.Tasks;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author Paul Coiffier
 */
public class TaskJob implements Job {

    public static MainFen fenPrincipale;
    public Tasks laTache;

    @Override
    public void execute(JobExecutionContext context) throws
            JobExecutionException {
        try {

            JobDataMap dataMap = context.getMergedJobDataMap();  // Note the difference from the previous example
            String TaskLibelle = dataMap.getString("TaskLibelle");
            String str[]=TaskLibelle.split("/");
            String ScheduleTaskId = str[1];
            TaskLibelle = str[0];
            System.out.println("TaskJob : Schedule Task ID : " + ScheduleTaskId);
            MainFen.loadTaskMotorThread(TaskLibelle,ScheduleTaskId);

        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}