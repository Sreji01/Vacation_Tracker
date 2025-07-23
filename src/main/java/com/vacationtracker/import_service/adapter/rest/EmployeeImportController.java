package com.vacationtracker.import_service.adapter.rest;

import com.vacationtracker.import_service.application.service.EmployeeImportService;
import com.vacationtracker.import_service.domain.model.Employee;
import com.vacationtracker.import_service.infrastructure.file.CsvEmployeeImporter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/import/employees")
public class EmployeeImportController {

    private final EmployeeImportService employeeImportService;
    private final CsvEmployeeImporter csvEmployeeImporter;

    public EmployeeImportController(EmployeeImportService employeeImportService, CsvEmployeeImporter csvEmployeeImporter) {
        this.employeeImportService = employeeImportService;
        this.csvEmployeeImporter = csvEmployeeImporter;
    }

    @PostMapping
    public ResponseEntity<String> importEmployees(@RequestParam("file") MultipartFile file) throws IOException {
        List<Employee> employees = csvEmployeeImporter.parse(file.getInputStream());
        employeeImportService.importEmployees(employees);
        return ResponseEntity.ok("Successfully imported employees");
    }
}
