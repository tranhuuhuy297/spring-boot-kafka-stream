package com.example.kafkastream.config;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.HashMap;
import java.util.Map;

// @Configuration
// @EnableKafka
// @EnableKafkaStreams
public class KafkaStreamsConfig {

    @Value(value = "${application.id}")
    private String applicationId;

    @Value(value = "${bootstrap.servers}")
    private String bootstrapServers;

    @Value(value = "${state.dir}")
    private String stateDir;

    @Value(value = "${num.standby.task}")
    private Integer numStandByTask;

    @Value(value = "${username}")
    private String username;

    @Value(value = "${password}")
    private String password;

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kStreamsConfigs() {
        Map<String, Object> props = new HashMap<>();

        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        props.put(SaslConfigs.SASL_JAAS_CONFIG,
                "org.apache.kafka.common.security.plain.PlainLoginModule required username='" + username
                        + "' password='" + password + "';");

        props.put(StreamsConfig.APPLICATION_ID_CONFIG, this.applicationId);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.STATE_DIR_CONFIG, this.stateDir);
        props.put(StreamsConfig.NUM_STANDBY_REPLICAS_CONFIG, this.numStandByTask);

        return new KafkaStreamsConfiguration(props);
    }

}