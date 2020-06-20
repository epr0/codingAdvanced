package com.sda.sdaScheduler.model;

import com.sda.sdaScheduler.exception.MaximumNumberOfStudentsReached;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Group {

    private String name;
    private Trainer trainer;

    /**
     * using Set to make sure the same student doesn't get added twice
     * <p>
     * removing the public setter and implementing custom remove/add methods
     * so that we can throw exception when the maximum number of students is reached
     * this way we'll only have access to the studentList through our methods which can control the logic
     * <p>
     * updating the public getter so that it returns an unmodifiableSet; this restricts access to the set storing
     * our students; We will only be able to work with a copy of the original set if we want to manipulate it
     */

    private Set<Student> studentList = new HashSet<>();

    public Group() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    /**
     * Returning an unmodifiableSet;
     * This will prevent us to try to add a student through group.getStudentList().add(new Student());
     * The add operation will only be available through the public addStudent() method
     * <p>
     * https://www.geeksforgeeks.org/collections-unmodifiablecollection-method-in-java-with-examples/
     */
    public Set<Student> getStudentList() {
        return Collections.unmodifiableSet(studentList);
    }

    public void addStudent(Student s) throws MaximumNumberOfStudentsReached {
        if (studentList.size() >= 5) {
            throw new MaximumNumberOfStudentsReached();
        } else {
            studentList.add(s);
        }
    }

    /**
     * Using iterator to remove an element from Set while iterating. You cannot use for in this case
     * You can calculate the difference between two dates with Period
     * <p>
     * https://www.tutorialspoint.com/use-iterator-to-remove-an-element-from-a-collection-in-java
     * https://www.geeksforgeeks.org/period-between-method-in-java-with-examples/
     */
    public void removeStudentsYoungerThan20() {
        Iterator<Student> iterator = studentList.iterator();
        while (iterator.hasNext()) {
            Student stud = iterator.next();
            if (Period.between(stud.getDateOfBirth(), LocalDate.now()).getYears() < 20) {
                iterator.remove();
            }
        }
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", trainer=" + trainer +
                ", studentList=" + studentList +
                '}';
    }
}
