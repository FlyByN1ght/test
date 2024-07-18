package com.example.test.service;

import com.example.test.model.CurrencyAnswer;
import com.example.test.repository.CurrencyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

@Service
@Slf4j
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public boolean loadRatesForDate(LocalDate date) {
        String url = "https://api.nbrb.by/exrates/rates?ondate=" + date.toString()+ "&periodicity=0";
        try {
            CurrencyAnswer[] rates = restTemplate.getForObject(url, CurrencyAnswer[].class);
            if (rates != null) {
                for (CurrencyAnswer rate : rates) {
                    rate.setDate(date);
                }
                currencyRepository.saveAll(Arrays.asList(rates));
                log.info("Successfully loaded and saved currency rates for date: {}", date);
            }
            return true;
        } catch (Exception e) {
            log.error("Error loading currency rates for date: {}. Exception: {}", date, e.getMessage(), e);
            return false;
        }
    }

    public Optional<CurrencyAnswer> getRateByDateAndCurrencyCode(LocalDate date, Long curId) {
        return currencyRepository.findByDateAndCurId(date, curId);
    }
}
