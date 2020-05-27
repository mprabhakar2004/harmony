package com.example.iam.changeeventlistener.configuration;

import com.example.iam.changeeventlistener.model.UserChangeModel;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;


//@EnableKafka
@Configuration
public class KafkaConsumerConfig {
  @Value(value = "${kafka.bootstrapAddress}")
  private String bootstrapAddress;

  @Bean
  public ConsumerFactory<String, UserChangeModel> consumerFactory() {
    Map<String, Object> props = new HashMap<>();
    JsonDeserializer<UserChangeModel> deserializer = new JsonDeserializer<>(UserChangeModel.class);
    deserializer.setRemoveTypeHeaders(false);
    deserializer.addTrustedPackages("*");
    deserializer.setUseTypeMapperForKey(true);
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "changeeventrouter");
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,  deserializer);
    return new DefaultKafkaConsumerFactory<>(props,new StringDeserializer(),
        deserializer);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, UserChangeModel> kafkaListenerContainerFactory() {

    ConcurrentKafkaListenerContainerFactory<String, UserChangeModel> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }
}