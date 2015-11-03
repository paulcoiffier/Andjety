/*
 * Andjety 2.0 - Code by Paul Coiffier - 2012/2013
 * 
 */
package com.mobissime.andjety.objects;

/**
 *
 * @author Paul Coiffier
 */
public class RunningTasks {

    public String taskId;
    public String taskName;
    public String taskPourcentage;
    public String TaskStatus;

    public RunningTasks() {
    }

    public RunningTasks(String taskId, String taskName, String TaskStatus) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.TaskStatus = TaskStatus;
    }

    public String getTaskStatus() {
        return TaskStatus;
    }

    public void setTaskStatus(String TaskStatus) {
        this.TaskStatus = TaskStatus;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskPourcentage() {
        return taskPourcentage;
    }

    public void setTaskPourcentage(String taskPourcentage) {
        this.taskPourcentage = taskPourcentage;
    }
}
