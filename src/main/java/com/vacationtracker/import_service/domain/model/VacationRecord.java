package com.vacationtracker.import_service.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class VacationRecord {

    @Id
    @GeneratedValue
    private UUID id;

    private int year;

    private LocalDate startDate;

    private LocalDate endDate;

    private int usedDays;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
