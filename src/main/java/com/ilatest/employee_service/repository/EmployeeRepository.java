package com.ilatest.employee_service.repository;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilatest.employee_service.model.Counter;
import com.ilatest.employee_service.model.Employee;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private final ObjectMapper objectMapper;

    @Autowired
    public EmployeeRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Synchronized
    public boolean writeEmployees(List<Employee> employees) {

        try {
            objectMapper.writeValue(new File("database.json"), employees);

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Synchronized
    public List<Employee> loadEmployees() {
        try {
            File employeeFile = new File("database.json");

            List<Employee> employees = new ArrayList<Employee>();
            // Check if file exists
            if (employeeFile.exists()) {
                // Read JSON from file and deserialize to Employee object
                employees = objectMapper.readValue(employeeFile, new TypeReference<List<Employee>>() {
                });
            }


            return employees;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Long getNextId() throws IOException {
        File employeeFile = new File("counter.json");

        Counter counter = null;

        // Check if file exists
        if (employeeFile.exists()) {

            counter = objectMapper.readValue(new File("counter.json"), Counter.class);

        } else {
            counter = new Counter(1L);
        }

        // Read current counter
        Long currentId = counter.getCounter();

        // Increment counter
        counter.setCounter(currentId + 1);

        // Write new counter
        objectMapper.writeValue(new File("counter.json"), counter);

        return currentId;
    }
}
