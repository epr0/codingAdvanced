package com.sda.sdaScheduler.model;

import java.time.LocalDate;

public class Person implements Comparable<Person> {

    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;

    public Person() {
    }

    public Person(String firstname, String lastname, LocalDate dateOfBirth) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Overriding compareTo in order to be able to sort by last name through Comparable
     * Another way to sort the collection would be by using a Comparator
     * <p>
     * https://www.geeksforgeeks.org/comparable-vs-comparator-in-java/
     */
    @Override
    public int compareTo(Person o) {
        return this.lastname.compareTo(o.getLastname());
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
