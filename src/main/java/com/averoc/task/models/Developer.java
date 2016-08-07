package com.averoc.task.models;

import java.util.List;

/**
 * Created by de1mos on 7.08.16.
 */
public class Developer extends Person {

    private List<Task> developerTasks;

    // getters / setters
    public List<Task> getDeveloperTasks() {
        return developerTasks;
    }
    public void setDeveloperTasks(List<Task> developerTasks) {
        this.developerTasks = developerTasks;
    }
}
