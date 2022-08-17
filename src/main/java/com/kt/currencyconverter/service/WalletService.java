package com.kt.currencyconverter.service;

import com.kt.currencyconverter.wallet.Wallet;
import com.kt.currencyconverter.wallet.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {

    @Autowired
    public WalletRepository walletRepository;


    public List<Wallet> getWalletsList() {
        List<Wallet> wallets = walletRepository.findAll();
        return wallets;
    }

    public void save(Wallet wallet) {
        walletRepository.save(wallet);
    }
}
