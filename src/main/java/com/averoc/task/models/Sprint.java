package com.averoc.task.models;

import java.util.List;

/**
 * Created by de1mos on 8.08.16.
 */
public class Sprint {
    private int sprintWeekNumber;
    List<Task> sprintTasks;
    private Float summarizedBurnedPoints = 0.0F;

    public Float getSummarizedBurnedPoints() {
        summarizedBurnedPoints = 0.0F;
        for(Task task : sprintTasks) {
            summarizedBurnedPoints += task.getBurnedPoints();
        }

        return summarizedBurnedPoints;
    }

    public Float getArithmeticAverageOfBurnedPoints() {
        return (getSummarizedBurnedPoints() / sprintTasks.size());
    }

    public int getTasksCount() {
        return sprintTasks.size();
    }

    // getters / setters
    public int getSprintWeekNumber() {
        return sprintWeekNumber;
    }
    public void setSprintWeekNumber(int sprintWeekNumber) {
        this.sprintWeekNumber = sprintWeekNumber;
    }

    public List<Task> getSprintTasks() {
        return sprintTasks;
    }
    public void setSprintTasks(List<Task> sprintTasks) {
        this.sprintTasks = sprintTasks;
    }
}
