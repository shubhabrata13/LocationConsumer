package com.apmm.config;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class kafkaConfigConsumer {
	
	@Value("${spring.kafka.properties.bootstrap.servers}") 
	private String bootstrapServers;
	
	@Value("${spring.kafka.properties.sasl.mechanism}") 
	private String sasl_mechanism;
	
	@Value("${spring.kafka.properties.sasl.jaas.config}") 
	private String jaas_config;
	
	@Value("${spring.kafka.properties.security.protocol}") 
	private String security_protocol;
	
	public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(SaslConfigs.SASL_MECHANISM,sasl_mechanism);
        configProps.put(SaslConfigs.SASL_JAAS_CONFIG,jaas_config);
        configProps.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,security_protocol);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "groupId");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        
        return factory;
    }

}
