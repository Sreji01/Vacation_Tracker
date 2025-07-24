package com.vacationtracker.import_service.infrastructure.file;

import com.vacationtracker.import_service.domain.model.Employee;
import com.vacationtracker.import_service.domain.model.VacationBalance;
import com.vacationtracker.import_service.infrastructure.persistence.EmployeeRepository;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CsvVacationBalanceImporter {

    private final EmployeeRepository employeeRepository;

    public CsvVacationBalanceImporter(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<VacationBalance> parse(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String metaLine = reader.readLine();
            int year = Integer.parseInt(metaLine.split(",")[1]);

            return reader.lines()
                    .skip(1)
                    .filter(line -> !line.isBlank())
                    .map(line -> parseLineToVacationBalance(line, year))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV file", e);
        }
    }

    private VacationBalance parseLineToVacationBalance(String line, int year) {
        String[] data = line.split(",");

        if (data.length != 2) {
            throw new IllegalArgumentException("Invalid CSV line: " + line);
        }

        String email = data[0].trim();
        int totalVacationDays;

        try {
            totalVacationDays = Integer.parseInt(data[1].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format in line: " + line);
        }

        Optional<Employee> employee = employeeRepository.findByEmail(email);
        if (employee.isEmpty()) {
            throw new IllegalArgumentException("Employee not found for email: " + email);
        }

        return new VacationBalance(year, totalVacationDays, employee.get());
    }
}
