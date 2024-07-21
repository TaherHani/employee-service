package com.ilatest.employee_service.service;
import com.ilatest.employee_service.dto.ResEmployeeDto;
import com.ilatest.employee_service.dto.ReqEmployeeDto;
import com.ilatest.employee_service.model.Employee;
import com.ilatest.employee_service.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    private List<Employee> employees = new ArrayList<>();


    @PostConstruct
    public void init() {
        // load data from json while service startup
        employees = employeeRepository.loadEmployees();
    }

    @Synchronized
    public ResEmployeeDto createEmployee(ReqEmployeeDto employeedto) {

        try {
            List<Employee> newEmployeesList = new ArrayList<>(employees);

            //convert EmployeeDto to Employee object
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


            Employee employee = new Employee(employeedto.getFirstName(), employeedto.getLastName(), LocalDate.parse(employeedto.getDateOfBirth(), formatter), employeedto.getSalary(),LocalDate.parse(employeedto.getJoinDate(), formatter) , employeedto.getDepartment());
            // set id from counter
            employee.setId(employeeRepository.getNextId());
            newEmployeesList.add(employee);

            //update json file
            boolean isAddedSuccessfully = employeeRepository.writeEmployees(newEmployeesList);
            // reload data from jason
            if (isAddedSuccessfully) {
                employees = employeeRepository.loadEmployees();
            }

            return new ResEmployeeDto(employee.getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Employee getEmployeeById(int id) {
        return employees.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    public List<Employee> getEmployees(String name, Double fromSalary, Double toSalary) {
        List<Employee> filteredEmployees = new ArrayList<>(employees);

        //filter by name
        if (name != null) {
            filteredEmployees = filteredEmployees.stream()
                    .filter(e -> e.getFirstName().contains(name) || e.getLastName().contains(name))
                    .collect(Collectors.toList());
        }

        // filter by fromSalary
        if (fromSalary != null) {
            filteredEmployees = filteredEmployees.stream().filter(e -> e.getSalary() >= fromSalary).collect(Collectors.toList());
        }

        // filter by toSalary
        if (toSalary != null) {
            filteredEmployees = filteredEmployees.stream().filter(e -> e.getSalary() <= toSalary).collect(Collectors.toList());
        }
        return filteredEmployees;
    }
}
