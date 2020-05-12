1
https://raw.githubusercontent.com/falvojr/stackoverflow-61560293/master/src/main/java/com/falvojr/Application.java
package com.falvojr;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.falvojr.domain.Log;
import com.falvojr.domain.LogRepository;
import com.falvojr.domain.MyClass1;
import com.falvojr.domain.MyClass2;
import com.mongodb.MongoClientOptions;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private LogRepository logRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public MongoClientOptions mongoClientOption() {
        // I am using the PojoCodec to automatic parse my domain classes:
        final CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        final CodecRegistry fullCodecRegistry = fromRegistries(getDefaultCodecRegistry(), pojoCodecRegistry);
        return MongoClientOptions.builder().codecRegistry(fullCodecRegistry).build();
    }

    @Override
    public void run(String... args) throws Exception {

        final String key = "response";

        // Simulate my dynamic of entity insertion (with several domain classes into "outbound" field):
        final Map<String, Object> map = new HashMap<>();
        map.put(key, new MyClass1("attr1", new MyClass2(1L)));
        final Log log = new Log();
        log.setOutbound(new Document(map));
        logRepository.save(log);

        // Find saved entity to try specific class conversion (MyClass1):
        final Log logSaved = logRepository.findById(log.getId()).orElseThrow(RuntimeException::new);

        try {
            // Error occurs here, but in Spring Boot 1.X this conversion/cast works...
            // Note: In Spring Boot 1, the "_class" field is present inside my "outbound" field.
            final MyClass1 test = logSaved.getOutbound().get(key, MyClass1.class);
            System.out.println(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
