package com.vacationtracker.import_service.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class VacationBalance {

    @Id
    @GeneratedValue
    private UUID id;

    private int year;
    private int numberOfDays;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public VacationBalance() {
    }

    public VacationBalance(int year, int numberOfDays, Employee employee) {
        this.year = year;
        this.numberOfDays = numberOfDays;
        this.employee = employee;
    }
}
