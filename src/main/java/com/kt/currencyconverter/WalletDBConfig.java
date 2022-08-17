package com.kt.currencyconverter;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableMongoRepositories(basePackages = {"com.kt.currencyconverter.wallet"},
        mongoTemplateRef = WalletDBConfig.MONGO_TEMPLATE
)
public class WalletDBConfig {

    protected static final String MONGO_TEMPLATE = "walletdbMongoTemplate";

    @Primary
    @Bean(name = "walletdbProperties")
    @ConfigurationProperties(prefix = "spring.data.mongo.walletdb")
    public MongoProperties getWalletDBProps() throws Exception {
        return new MongoProperties();
    }

    @Primary
    @Bean(name = "walletdbMongoTemplate")
    public MongoTemplate walletdbMongoTemplate() throws Exception {
        return new MongoTemplate(walletdbMongoDatabaseFactory(getWalletDBProps()));
    }

    @Primary
    @Bean
    public MongoDatabaseFactory walletdbMongoDatabaseFactory(MongoProperties mongo) throws Exception {
        return new SimpleMongoClientDatabaseFactory(
                mongo.getUri()
        );
    }

}