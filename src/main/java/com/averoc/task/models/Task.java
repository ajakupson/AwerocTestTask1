package com.averoc.task.models;

/**
 * Created by de1mos on 7.08.16.
 */
public class Task {
    private Long sprintWeekNumber;
    private String taskName;
    private Long burnedPoints;

    // getters / setters
    public Long getSprintWeekNumber() {
        return sprintWeekNumber;
    }
    public void setSprintWeekNumber(Long sprintWeekNumber) {
        this.sprintWeekNumber = sprintWeekNumber;
    }

    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getBurnedPoints() {
        return burnedPoints;
    }
    public void setBurnedPoints(Long burnedPoints) {
        this.burnedPoints = burnedPoints;
    }
}
