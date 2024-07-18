package com.example.test.controller;

import com.example.test.model.CurrencyAnswer;
import com.example.test.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/test")
public class CurrencyController {

    private static final Logger log = LoggerFactory.getLogger(CurrencyController.class);
    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/loadRates")
    public ResponseEntity<String> loadRates(@RequestParam String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            boolean success = currencyService.loadRatesForDate(localDate);
            if (success) {
                log.info("data loaded successfully for this date:{}", date);
                return ResponseEntity.ok("Data loaded successfully");
            } else {
                log.error("some error with db");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error loading data");
            }
        } catch (Exception e) {
            log.warn("bad request{}", date);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid date format");
        }
    }

    @GetMapping("/rate")
    public ResponseEntity<CurrencyAnswer> getRate(@RequestParam String date, @RequestParam Long curId) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            Optional<CurrencyAnswer> rate = currencyService.getRateByDateAndCurrencyCode(localDate, curId);
            return rate.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}