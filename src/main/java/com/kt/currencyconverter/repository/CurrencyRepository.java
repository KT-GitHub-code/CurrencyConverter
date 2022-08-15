package com.kt.currencyconverter.repository;

import com.kt.currencyconverter.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
