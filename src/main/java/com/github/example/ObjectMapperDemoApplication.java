package com.github.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.OffsetDateTime;

@SpringBootApplication
public class ObjectMapperDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObjectMapperDemoApplication.class, args);
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper(final Jackson2ObjectMapperBuilder builder,
                                     final OffsetDateTimeDeserializer offsetDateTimeDeserializer) {

        final JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(OffsetDateTime.class, offsetDateTimeDeserializer);

        builder.modules(javaTimeModule);
        builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return builder.build();
    }


    @Bean
    public ObjectMapper failingObjectMapper(final Jackson2ObjectMapperBuilder builder,
                                            final OffsetDateTimeDeserializer offsetDateTimeDeserializer) {

        final JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(OffsetDateTime.class, offsetDateTimeDeserializer);

        builder.modulesToInstall(javaTimeModule);
        builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return builder.build();
    }

}
