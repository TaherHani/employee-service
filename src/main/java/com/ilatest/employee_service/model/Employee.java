package com.ilatest.employee_service.model;

import java.util.Date;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private double salary;
    private Date joinDate;
    private String department;

    public Employee(int id, String firstName, String lastName, Date dateOfBirth, double salary, Date joinDate, String department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.joinDate = joinDate;
        this.department = department;
    }
}
