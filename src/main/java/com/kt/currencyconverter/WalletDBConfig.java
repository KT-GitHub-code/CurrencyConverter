package com.kt.currencyconverter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "walletEntityManagerFactory",
        transactionManagerRef = "walletTransactionManager",
        basePackages = {"com.kt.currencyconverter.wallet"}
)
public class WalletDBConfig {

    @Bean(name="walletDataSource")
    @ConfigurationProperties(prefix="spring.seconddatasource")
    public DataSource walletDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "walletEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean walletEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        return builder
                .dataSource(walletDataSource())
                .packages("com.kt.currencyconverter.wallet")
                .persistenceUnit("CurrencyConverterWallet")
                .properties(properties)
                .build();
    }

    @Bean(name = "walletTransactionManager")
    public PlatformTransactionManager walletTransactionManager(
            @Qualifier("walletEntityManagerFactory") EntityManagerFactory walletEntityManagerFactory) {
        return new JpaTransactionManager(walletEntityManagerFactory);
    }

}
