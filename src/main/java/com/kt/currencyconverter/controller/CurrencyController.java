package com.kt.currencyconverter.controller;

import com.kt.currencyconverter.currency.Currency;
import com.kt.currencyconverter.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CurrencyController {

    @Autowired
    public CurrencyService currencyService;


    @GetMapping("currency/{symbol}")
    public Currency getCurrency(@PathVariable String symbol){
        return currencyService.getCurrencyBySymbol(symbol);
    }

    @GetMapping("symbols")
    public List<String> getSymbols(){
        return currencyService.getCurrenciesList();
    }

    @GetMapping("convert")
    public double convert(@RequestParam String from, String to, Integer amount) throws Exception {
        return currencyService.convert(from, to, amount);
    }

}
