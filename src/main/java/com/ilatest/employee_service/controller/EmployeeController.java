package com.ilatest.employee_service.controller;
import com.ilatest.employee_service.dto.ReqEmployeeDto;
import com.ilatest.employee_service.dto.ResEmployeeDto;
import com.ilatest.employee_service.model.Employee;
import com.ilatest.employee_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<ResEmployeeDto> createEmployee(@RequestBody ReqEmployeeDto newEmployee) {
        ResEmployeeDto employeeDto = employeeService.createEmployee(newEmployee);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double fromSalary,
            @RequestParam(required = false) Double toSalary) {
        List<Employee> employees = employeeService.getEmployees(name, fromSalary, toSalary);
        return ResponseEntity.ok(employees);
    }
}
