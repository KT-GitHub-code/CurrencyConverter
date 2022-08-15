package com.kt.currencyconverter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Currency {

    @Id
    @GeneratedValue
    private Long id;

    public String name;

    public String symbol;

    public double conversionRateToEUR;
    public double conversionRateToUSD;
    public double conversionRateToHUF;
    public double conversionRateToJPY;
    public double conversionRateToCHF;

}
