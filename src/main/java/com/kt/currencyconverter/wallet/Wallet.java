package com.kt.currencyconverter.wallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "wallet")
public class Wallet {

    public String owner;

    public double eur;
    public double usd;
    public double huf;
    public double jpy;
    public double chf;

}
