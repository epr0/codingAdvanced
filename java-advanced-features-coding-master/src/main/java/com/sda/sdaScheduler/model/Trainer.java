package com.sda.sdaScheduler.model;


import java.time.LocalDate;

public class Trainer extends Person {

    private boolean isAuthorised;

    public Trainer() {
    }

    public Trainer(String firstname, String lastname, LocalDate dateOfBirth, boolean isAuthorised) {
        super(firstname, lastname, dateOfBirth);
        this.isAuthorised = isAuthorised;
    }

    public boolean isAuthorised() {
        return isAuthorised;
    }

    public void setAuthorised(boolean authorised) {
        isAuthorised = authorised;
    }

    @Override
    public String toString() {
        return "Trainer{" + super.toString() +
                " isAuthorised=" + isAuthorised +
                '}';
    }
}
