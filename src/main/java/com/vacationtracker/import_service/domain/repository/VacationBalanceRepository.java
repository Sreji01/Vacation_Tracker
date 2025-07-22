package com.vacationtracker.import_service.domain.repository;

import com.vacationtracker.import_service.domain.model.VacationBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VacationBalanceRepository extends JpaRepository<VacationBalance, UUID> {
}
