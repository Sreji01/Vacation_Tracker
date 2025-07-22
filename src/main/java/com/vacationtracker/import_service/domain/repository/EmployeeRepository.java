package com.vacationtracker.import_service.domain.repository;

import com.vacationtracker.import_service.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}
