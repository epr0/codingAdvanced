package com.sda.sdaScheduler.repository;

import com.sda.sdaScheduler.exception.MaximumNumberOfStudentsReached;
import com.sda.sdaScheduler.model.Group;
import com.sda.sdaScheduler.model.Student;
import com.sda.sdaScheduler.model.Trainer;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.util.*;

/**
 * For simplicity we're creating a central repository to store all courses, students, trainers
 * It would however be best practice to have a separate repository for each model class
 */
public class CentralRepository {

    private static List<Student> studentList = new ArrayList<>();
    private static List<Trainer> trainerList = new ArrayList<>();
    private static List<Group> groupList = new ArrayList<>();

    // Using the static block to initialize our objects
    static {

        // Initializing students
        // Did not want to spend time inventing names for the students so I googled "java random string"
        // First results was RandomStringUtils which comes from the apache commons lang maven dependency
        for(int i=1; i<=15; i++) {
            Student s = new Student();
            s.setFirstname(RandomStringUtils.random(5,true,false).toLowerCase());
            s.setLastname(RandomStringUtils.random(6,true,false).toLowerCase());
            s.setDateOfBirth(getRandomDateOfBirthBetween(1980, 2000));
            s.setHasPreviousJavaKnowledge(new Random().nextBoolean());

            studentList.add(s);
        }

        // Initializing trainers
        for(int i=1; i<=3; i++) {
            Trainer t = new Trainer();
            t.setFirstname(RandomStringUtils.random(5,true,false).toLowerCase());
            t.setLastname(RandomStringUtils.random(6,true,false).toLowerCase());
            t.setDateOfBirth(getRandomDateOfBirthBetween(1980, 1990));
            t.setAuthorised(new Random().nextBoolean());

            trainerList.add(t);
        }

        // Initializing groups
        for(int i=1; i<=4; i++) {
            Group c = new Group();
            c.setName("Group"+i);

            //assigning a random trainer from the trainer list
            Trainer t = trainerList.get(getRandomIntBetween(0, 2));
            c.setTrainer(t);

            groupList.add(c);
        }

        // Assigning students to groups
        try {
            groupList.get(0).addStudent(studentList.get(0));
            groupList.get(0).addStudent(studentList.get(1));
            groupList.get(0).addStudent(studentList.get(2));

            groupList.get(1).addStudent(studentList.get(3));
            groupList.get(1).addStudent(studentList.get(4));
            groupList.get(1).addStudent(studentList.get(5));

            groupList.get(2).addStudent(studentList.get(6));
            groupList.get(2).addStudent(studentList.get(7));
            groupList.get(2).addStudent(studentList.get(8));

            groupList.get(3).addStudent(studentList.get(9));
            groupList.get(3).addStudent(studentList.get(10));
            groupList.get(3).addStudent(studentList.get(11));
            groupList.get(3).addStudent(studentList.get(12));
        } catch (MaximumNumberOfStudentsReached e) {
            System.out.println("Cannot add more than 5 students to a group" + e.getMessage());
        }

    }

    public static List<Student> getStudentList() {
        return studentList;
    }

    public static List<Trainer> getTrainerList() {
        return trainerList;
    }

    public static List<Group> getGroupList() { return groupList; }

    /**
     * Copied these two methods from logicbig.com
     * First result for google search "java get random birthdate"
      */
    private static int getRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    private static LocalDate getRandomDateOfBirthBetween(int startYear, int endYear) {
        int day = getRandomIntBetween(1, 28);
        int month = getRandomIntBetween(1, 12);
        int year = getRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day);
    }
}
