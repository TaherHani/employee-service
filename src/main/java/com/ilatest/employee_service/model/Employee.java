package com.ilatest.employee_service.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class Employee {
    private long id;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private double salary;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate joinDate;
    private String department;

    public Employee(long id, String firstName, String lastName, LocalDate dateOfBirth, double salary, LocalDate joinDate, String department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.joinDate = joinDate;
        this.department = department;
    }
    public Employee( String firstName, String lastName, LocalDate dateOfBirth, double salary, LocalDate joinDate, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.joinDate = joinDate;
        this.department = department;
    }

    public Employee() {
    }


    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
