package com.company.signup.infrastructure.client.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

@Component
public class EventsProducer {

  // private final KafkaProducer kafkaProducer;

  // private final ProducerRecord producerRecord;

  // private final KafkaConfiguration kafkaConfiguration;

  // public EventsProducer(@Autowired KafkaConfiguration kafkaConfiguration) {
  // this.producerRecord = producerRecord;
  // this.kafkaConfiguration = kafkaConfiguration;

    /* Properties properties = new Properties();
    properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
        kafkaConfiguration.getBootstrap().getServers());
    properties.setProperty(ProducerConfig.ACKS_CONFIG, kafkaConfiguration.getAcks().toString());
    properties.setProperty(ProducerConfig.LINGER_MS_CONFIG,
        kafkaConfiguration.getLinger().getMs().toString());
    properties.setProperty(ProducerConfig.RETRIES_CONFIG,
        kafkaConfiguration.getRetries().toString());
    properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        kafkaConfiguration.getKey().getSerializer());
    properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        kafkaConfiguration.getValue().getSerializer()); */

  // this.kafkaProducer = new KafkaProducer<>(properties);
// }

  // public EventsProducer(KafkaProducer kafkaProducer, KafkaConfiguration kafkaConfiguration) {
  // this.producerRecord = producerRecord;
  // this.kafkaConfiguration = kafkaConfiguration;

    /* Properties properties = new Properties();
    properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfiguration.getBootstrap().getServers());
    properties.setProperty(ProducerConfig.ACKS_CONFIG, kafkaConfiguration.getAcks().toString());
    properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, kafkaConfiguration.getLinger().getMs().toString());
    properties.setProperty(ProducerConfig.RETRIES_CONFIG, kafkaConfiguration.getRetries().toString());
    properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, kafkaConfiguration.getKey().getSerializer());
    properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, kafkaConfiguration.getValue().getSerializer()); */

  // this.kafkaProducer = new KafkaProducer<>(properties);
  // }

  public void send(ProducerRecord producerRecord) {
    // kafkaProducer.send(producerRecord);
  }

}
