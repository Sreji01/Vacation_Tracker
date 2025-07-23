package com.vacationtracker.import_service.infrastracture.persistence;

import com.vacationtracker.import_service.domain.model.VacationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VacationRecordRepository extends JpaRepository<VacationRecord, UUID> {
}
