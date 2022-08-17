package com.kt.currencyconverter.controller;

import com.kt.currencyconverter.service.WalletService;
import com.kt.currencyconverter.wallet.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WalletController {

    @Autowired
    public WalletService walletService;


    @GetMapping("wallets")
    public List<Wallet> getWallets(){

        return walletService.getWalletsList();
    }

    @PostMapping("wallet")
    public void addWallet(@RequestBody Wallet wallet){
        walletService.save(wallet);
    }

}
