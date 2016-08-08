package com.averoc.task.models;

/**
 * Created by de1mos on 7.08.16.
 */
public class Person {

    private String firstName;
    private String lastName;
    private String fullName;

    // getters / setters
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
