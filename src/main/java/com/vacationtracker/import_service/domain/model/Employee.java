package com.vacationtracker.import_service.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue
    private UUID id;

    private String email;

    private String password;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<VacationBalance> vacationBalances;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<VacationRecord> vacationRecords;

}
