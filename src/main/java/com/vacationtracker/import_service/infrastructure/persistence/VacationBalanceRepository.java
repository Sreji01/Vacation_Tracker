package com.vacationtracker.import_service.infrastructure.persistence;

import com.vacationtracker.import_service.domain.model.Employee;
import com.vacationtracker.import_service.domain.model.VacationBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VacationBalanceRepository extends JpaRepository<VacationBalance, UUID> {

    Optional<VacationBalance> findByEmployeeAndYear(Employee employee, int year);
}
