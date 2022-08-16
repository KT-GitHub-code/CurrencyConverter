package com.kt.currencyconverter.currency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String name;

    public String symbol;

    public double conversionRateToEUR;
    public double conversionRateToUSD;
    public double conversionRateToHUF;
    public double conversionRateToJPY;
    public double conversionRateToCHF;

}
