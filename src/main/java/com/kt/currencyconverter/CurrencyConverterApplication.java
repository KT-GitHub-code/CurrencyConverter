package com.kt.currencyconverter;

import com.kt.currencyconverter.model.Currency;
import com.kt.currencyconverter.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CurrencyConverterApplication {

    @Autowired
    CurrencyRepository currencyRepository;

    public static void main(String[] args) {
        SpringApplication.run(CurrencyConverterApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            Currency c1 = Currency.builder()
                    .name("Euro")
                    .symbol("EUR")
                    .conversionRateToCHF(0.95)
                    .conversionRateToEUR(1.0)
                    .conversionRateToHUF(400.0)
                    .conversionRateToJPY(135.0)
                    .conversionRateToUSD(1.05)
                    .build();
            currencyRepository.save(c1);

            Currency c2 = Currency.builder()
                    .name("US dollar")
                    .symbol("USD")
                    .conversionRateToCHF(0.94)
                    .conversionRateToEUR(0.97)
                    .conversionRateToHUF(380.0)
                    .conversionRateToJPY(133.0)
                    .conversionRateToUSD(1.0)
                    .build();
            currencyRepository.save(c2);

            Currency c3 = Currency.builder()
                    .name("forint")
                    .symbol("HUF")
                    .conversionRateToCHF(0.0025)
                    .conversionRateToEUR(0.0026)
                    .conversionRateToHUF(1.0)
                    .conversionRateToJPY(0.35)
                    .conversionRateToUSD(0.0026)
                    .build();
            currencyRepository.save(c3);

            Currency c4 = Currency.builder()
                    .name("Japanese yen")
                    .symbol("JPY")
                    .conversionRateToCHF(0.0071)
                    .conversionRateToEUR(0.0073)
                    .conversionRateToHUF(2.86)
                    .conversionRateToJPY(1.0)
                    .conversionRateToUSD(0.0075)
                    .build();
            currencyRepository.save(c4);

            Currency c5 = Currency.builder()
                    .name("Swiss franc")
                    .symbol("CHF")
                    .conversionRateToCHF(1)
                    .conversionRateToEUR(1.03)
                    .conversionRateToHUF(405.0)
                    .conversionRateToJPY(142.0)
                    .conversionRateToUSD(1.06)
                    .build();
            currencyRepository.save(c5);

        };
    }

}
