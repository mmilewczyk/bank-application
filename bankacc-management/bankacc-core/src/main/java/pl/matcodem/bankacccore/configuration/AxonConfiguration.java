package pl.matcodem.bankacccore.configuration;

import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.MongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoFactory;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoSettingsFactory;
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.Collections.singletonList;

@Configuration
public class AxonConfiguration {

    @Value("${spring.data.mongodb.host:localhost}")
    private String mongoHost;
    @Value("${spring.data.mongodb.port:27017}")
    private int mongoPort;
    @Value("${spring.data.mongodb.database:bank}")
    private String mongoDatabase;

    @Bean
    public MongoClient mongo() {
        var mongoFactory = new MongoFactory();
        var mongoSettingsFactory = new MongoSettingsFactory();
        mongoSettingsFactory.setMongoAddresses(singletonList(new ServerAddress(mongoHost, mongoPort)));
        mongoFactory.setMongoClientSettings(mongoSettingsFactory.createMongoClientSettings());
        return mongoFactory.createMongo();
    }

    @Bean
    public MongoTemplate axonMongoTemplate() {
        return DefaultMongoTemplate.builder()
                .mongoDatabase(mongo(), mongoDatabase)
                .build();
    }

    @Bean
    public Serializer messageSerializer() {
        return XStreamSerializer.defaultSerializer();
    }

    @Bean
    public TokenStore tokenStore(Serializer serializer) {
        return MongoTokenStore.builder()
                .mongoTemplate(axonMongoTemplate())
                .serializer(serializer)
                .build();
    }

    @Bean
    public EventStorageEngine eventStorageEngine(MongoClient mongoClient) {
        return MongoEventStorageEngine.builder()
                .mongoTemplate(DefaultMongoTemplate.builder()
                        .mongoDatabase(mongoClient)
                        .build())
                .build();
    }

    @Bean
    public EmbeddedEventStore eventStore(EventStorageEngine eventStorageEngine, org.axonframework.config.Configuration configuration) {
        return EmbeddedEventStore.builder()
                .storageEngine(eventStorageEngine)
                .messageMonitor(configuration.messageMonitor(EventStore.class, "eventStore"))
                .build();
    }
}
