package com.vacationtracker.import_service.application.service;

import com.vacationtracker.import_service.domain.model.VacationBalance;
import com.vacationtracker.import_service.infrastructure.persistence.VacationBalanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacationBalanceImportService {

    private VacationBalanceRepository vacationBalanceRepository;

    public VacationBalanceImportService(VacationBalanceRepository vacationBalanceRepository) {
        this.vacationBalanceRepository = vacationBalanceRepository;
    }

    public void importVacationBalances(List<VacationBalance> vacationBalances) {
        vacationBalances.forEach(balance -> {
            boolean exists = vacationBalanceRepository.findByEmployeeAndYear(balance.getEmployee(), balance.getYear()).isPresent();
            if(!exists){
                vacationBalanceRepository.save(balance);
            }
        });
    }
}
