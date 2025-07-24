package com.vacationtracker.import_service.adapter.rest;

import com.vacationtracker.import_service.application.service.VacationBalanceImportService;
import com.vacationtracker.import_service.domain.model.VacationBalance;
import com.vacationtracker.import_service.infrastructure.file.CsvVacationBalanceImporter;
import com.vacationtracker.import_service.infrastructure.persistence.VacationBalanceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/import/vacation-balances")
public class VacationBalanceImportController {

    private VacationBalanceImportService vacationBalanceImportService;
    private CsvVacationBalanceImporter csvVacationBalanceImporter;

    public VacationBalanceImportController(VacationBalanceImportService vacationBalanceImportService, CsvVacationBalanceImporter csvVacationBalanceImporter, VacationBalanceRepository vacationBalanceRepository) {
        this.vacationBalanceImportService = vacationBalanceImportService;
        this.csvVacationBalanceImporter = csvVacationBalanceImporter;
    }

    @PostMapping
    public ResponseEntity<String> importVacationBalances(@RequestParam("file") MultipartFile file) throws IOException {
        List<VacationBalance> vacationBalances = csvVacationBalanceImporter.parse(file.getInputStream());
        vacationBalanceImportService.importVacationBalances(vacationBalances);
        return ResponseEntity.ok("Successfully imported vacation balances");
    }
}
