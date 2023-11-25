package com.example.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class SQSConfig {

    @Value("${cloud.aws.region.static}")
    private String region;

//    @Value("${cloud.aws.credentials.access-key}")
//    private String accessKeyId;
//
//    @Value("${cloud.aws.credentials.secret-key}")
//    private String secretAccessKey;
//
//    @Value("${cloud.aws.end-point.uri}")
//    private String sqsUrl;

    @Bean
    @Primary
    public SqsAsyncClient amazonSQSAsync() {
        return SqsAsyncClient
                .builder()
                .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public SqsTemplate sqsTemplate(SqsAsyncClient sqsAsyncClient){
        return SqsTemplate.builder().sqsAsyncClient(sqsAsyncClient).build();
    }


    @Bean
    protected MessageConverter messageConverter(ObjectMapper objectMapper) {

        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(objectMapper);
        converter.setSerializedPayloadClass(String.class);
        converter.setStrictContentTypeMatch(false);
        return converter;
    }
}
