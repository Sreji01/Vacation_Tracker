package com.vacationtracker.import_service.infrastructure.file;

import com.vacationtracker.import_service.domain.model.Employee;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CsvEmployeeImporter {

    public List<Employee> parse(InputStream inputStream) {

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            return reader.lines()
                    .skip(2)
                    .filter(line -> !line.isBlank())
                    .map(this::parseLineToEmployee)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Employee parseLineToEmployee(String line) {

        String[] data = line.split(",");

        if(data.length != 2){
            throw new IllegalArgumentException("Invalid CSV line: " + line);
        }

        return new Employee(data[0].trim(), data[1].trim());
    }

}
