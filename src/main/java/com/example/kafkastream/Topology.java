package com.example.kafkastream;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Topology {

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
