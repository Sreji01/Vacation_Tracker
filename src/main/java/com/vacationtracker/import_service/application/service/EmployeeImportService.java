package com.vacationtracker.import_service.application.service;

import com.vacationtracker.import_service.domain.model.Employee;
import com.vacationtracker.import_service.infrastructure.persistence.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeImportService {

    private final EmployeeRepository employeeRepository;

    public EmployeeImportService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void importEmployees(List<Employee> employees) {

        employees.forEach(employee -> {
            boolean exists = employeeRepository.findByEmail(employee.getEmail()).isPresent();
            if(!exists){
                employeeRepository.save(employee);
            }
        });
    }
}
