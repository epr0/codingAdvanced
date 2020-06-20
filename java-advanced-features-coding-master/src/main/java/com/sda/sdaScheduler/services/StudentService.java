package com.sda.sdaScheduler.services;

import com.sda.sdaScheduler.model.Group;
import com.sda.sdaScheduler.model.Student;
import com.sda.sdaScheduler.model.Trainer;
import com.sda.sdaScheduler.repository.CentralRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class StudentService {

    public static void displayAllGroups() {
        System.out.println("The list of all groups and their trainer/students: ");

        for(Group g: CentralRepository.getGroupList()) {
            System.out.println(g);
        }
    }

    public static void displayStudentsAlphabetically() {
        System.out.println("The list of students in every group sorted alphabetically by lastName: ");


        for(Group g: CentralRepository.getGroupList()) {
            System.out.println(g.getName());

            /**
             * Remember that Set is not sorted. In order for us to be able to sort we need a List
             * So we copy the Set into a List and then sort it
             */
            List<Student> students = new ArrayList<>(g.getStudentList());
            Collections.sort(students);

            for(Student s : students) {
                System.out.println(s);
            }
        }
    }

    public static void displayGroupWithMaxStudents() {
        System.out.println("The group with the highest number of students: ");

        // We initially consider the first group as the one with max number of students
        Group groupWithMaxStudents = CentralRepository.getGroupList().get(0);

        // If we identify a group with a higher number of students then we store it into groupWithMaxStudents
        for(Group g: CentralRepository.getGroupList()) {
            if(g.getStudentList().size() > groupWithMaxStudents.getStudentList().size()) {
                groupWithMaxStudents = g;
            }
        }

        System.out.println("Group with max students: " + groupWithMaxStudents.getName() + " has " + groupWithMaxStudents.getStudentList().size());
    }

    public static void displayStudentsYoungerThan25() {
        System.out.println("Students younger than 25 from all the groups: ");

        for(Group g: CentralRepository.getGroupList()) {
            for(Student s: g.getStudentList()) {
                if(Period.between(s.getDateOfBirth(), LocalDate.now()).getYears() < 25) {
                    System.out.println(s);
                }
            }
        }
    }

    public static void displayStudentsByTrainer() {
        System.out.println("Students grouped by trainer: ");

        /**
         * Whenever you want to group multiple elements by a common key consider using a map
         * In this case the Trainer will be the key and the list of students will be the value
         */
        Map<Trainer, List<Student>> studentsByTrainerMap = new HashMap<>();

        for(Group g: CentralRepository.getGroupList()) {
            List<Student> studForTrainer = studentsByTrainerMap.get(g.getTrainer());
            if(studForTrainer == null) {
                studForTrainer = new ArrayList<>(g.getStudentList());
                studentsByTrainerMap.put(g.getTrainer(), studForTrainer);
            } else {
                studForTrainer.addAll(new ArrayList<>(g.getStudentList()));
            }
        }

        for (Map.Entry<Trainer, List<Student>> entry : studentsByTrainerMap.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().size() + " students");
            System.out.println(entry.getValue() + "\n");
        }
    }

    public static void displayStudentsWithJavaKnowledge() {
        System.out.println("Students with java knowledge from all groups: ");

        for(Student s: CentralRepository.getStudentList()) {
            if(s.hasPreviousJavaKnowledge()) {
                System.out.println(s);
            }
        }
    }

    public static void displayGroupWithMaxStudentsWithNoJavaKnowledge() {
        System.out.println("The group with the highest number of students with no previous java knowledge: ");

        Group groupWithMaxStudentsWithNoJavaKnowledge = null;
        int maxStudentsWithNoJavaKnowledge = 0;

        for(Group g: CentralRepository.getGroupList()) {
            int countStudentsWithNoJavaKnowledge = 0;
            for(Student s : g.getStudentList()) {
                if(!s.hasPreviousJavaKnowledge()) {
                    countStudentsWithNoJavaKnowledge++;
                }
            }
            if(countStudentsWithNoJavaKnowledge > maxStudentsWithNoJavaKnowledge) {
                groupWithMaxStudentsWithNoJavaKnowledge = g;
                maxStudentsWithNoJavaKnowledge = countStudentsWithNoJavaKnowledge;
            }
        }

        System.out.println(groupWithMaxStudentsWithNoJavaKnowledge);
        System.out.println("Students with no java knowledge: " + maxStudentsWithNoJavaKnowledge);
    }

    public static void removeStudentsYoungerThan20FromGroups() {
        System.out.println("Removing students younger than 20: ");

        for(Group g: CentralRepository.getGroupList()) {
            g.removeStudentsYoungerThan20();

            System.out.println(g);
        }
    }

    public static void addSpace() {
        System.out.println("\n ---------------------------- \n ");
    }
}
