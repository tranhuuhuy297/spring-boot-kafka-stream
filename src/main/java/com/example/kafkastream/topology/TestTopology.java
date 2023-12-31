package com.example.kafkastream.topology;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.beans.factory.annotation.Autowired;

// @Component
public class TestTopology {

    @Autowired
    public void run(StreamsBuilder streamsBuilder) {
        KStream<String, String> source = streamsBuilder.stream("sample_data_topic");

        KTable<String, Long> wordCounts = source
                .mapValues(v -> 1L)
                .groupByKey()
                .count();

        wordCounts.toStream().foreach((key, value) -> System.out.println(key + " - " + value));
    }

}
