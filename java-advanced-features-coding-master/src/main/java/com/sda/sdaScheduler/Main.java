package com.sda.sdaScheduler;

import com.sda.sdaScheduler.model.Group;
import com.sda.sdaScheduler.model.Student;
import com.sda.sdaScheduler.model.Trainer;
import com.sda.sdaScheduler.repository.CentralRepository;
import com.sda.sdaScheduler.services.StudentService;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Main
{
    public static void main( String[] args )
    {
        StudentService.displayAllGroups();
        StudentService.addSpace();
        StudentService.displayStudentsAlphabetically();
        StudentService.addSpace();
        StudentService.displayGroupWithMaxStudents();
        StudentService.addSpace();
        StudentService.displayStudentsYoungerThan25();
        StudentService.addSpace();
        StudentService.displayStudentsByTrainer();
        StudentService.addSpace();
        StudentService.displayStudentsWithJavaKnowledge();
        StudentService.addSpace();
        StudentService.displayGroupWithMaxStudentsWithNoJavaKnowledge();
        StudentService.addSpace();
        StudentService.removeStudentsYoungerThan20FromGroups();
    }
}
