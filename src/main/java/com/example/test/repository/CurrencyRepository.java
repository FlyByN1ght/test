package com.example.test.repository;

import com.example.test.model.CurrencyAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyAnswer, Long> {
    Optional<CurrencyAnswer> findByDateAndCurId(LocalDate date, Long curId);
}
