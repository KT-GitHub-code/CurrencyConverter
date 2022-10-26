package com.kt.currencyconverter.service;

import com.kt.currencyconverter.CurrencyConverterApplication;
import com.kt.currencyconverter.currency.Currency;
import com.kt.currencyconverter.currency.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Service
public class CurrencyService {

    @Autowired
    public CurrencyRepository currencyRepository;

    // properties / DB
    private final boolean propertiesModeOn = false;


    public Currency getCurrency(String symbol) {
        return currencyRepository.findAll().stream()
                .filter(c -> Objects.equals(c.getSymbol(), symbol))
                .findFirst().orElse(null);
    }

    public Currency getCurrencyBySymbol(String symbol) {
        return currencyRepository.findBySymbol(symbol);
    }

    public List<String> getCurrenciesList() {

        List<Currency> currencies = currencyRepository.findAll();
        List<String> symbols = new ArrayList<>();

        for (Currency currency : currencies) {
            symbols.add(currency.getSymbol());
        }

        return symbols;
    }

    public double convert(String from, String to, int amount) throws Exception {
        if (!propertiesModeOn) {
            return convertUsingDatabase(from, to, amount);
        }
        else
            return convertUsingProperties(from, to, amount);
    }

    private double convertUsingProperties(String from, String to, int amount) throws Exception {

        double rate = 0;

        String source = from+".properties";

        try (InputStream input = CurrencyConverterApplication.class.getClassLoader().getResourceAsStream(source)) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("File '"+source+"' not found.");
                throw new Exception("File '"+source+"' not found.");
            }

            prop.load(input);

            rate = Double.parseDouble(prop.getProperty("conversionRateTo"+to));

            return amount*rate;

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return amount*rate;
    }

    private double convertUsingDatabase(String from, String to, int amount) throws Exception {
        Currency fromCurrency = currencyRepository.findAll().stream()
                .filter(c -> Objects.equals(c.getSymbol(), from))
                .findFirst().orElse(null);

        double rate;

        assert fromCurrency != null;
        rate = switch (to) {
            case "EUR" -> fromCurrency.getConversionRateToEUR();
            case "USD" -> fromCurrency.getConversionRateToUSD();
            case "HUF" -> fromCurrency.getConversionRateToHUF();
            case "JPY" -> fromCurrency.getConversionRateToJPY();
            case "CHF" -> fromCurrency.getConversionRateToCHF();
            default -> throw new Exception("Rate not found.");
        };
        return amount * rate;
    }
}
