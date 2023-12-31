package com.mingming.commonservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReactiveKafkaAppProperties {
    @Value("${kafka.bootstrap.servers}")
    public String bootstrapServers;
    @Value("${payment.kafka.consumer-group-id}")
    public String consumerGroupId;
}
