package com.example.iam.changeeventproducer.service;

import com.example.iam.changeeventproducer.model.UserChangeModel;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Service
public class KafkaEventProducer {
  Logger logger = Logger.getLogger(KafkaEventProducer.class.getName());
  @Autowired
  private KafkaTemplate<String, UserChangeModel> kafkaTemplate;

  @Value(value = "${approval.topicName}")
  private String topicName;

  public void sendMessage(UserChangeModel userChangeModel) {
    ListenableFuture<SendResult<String, UserChangeModel>> future = kafkaTemplate.send(topicName, userChangeModel);

    future.addCallback(new ListenableFutureCallback<SendResult<String, UserChangeModel>>() {

      @Override
      public void onSuccess(SendResult<String, UserChangeModel> result) {
        logger.info("Sent message=[" + userChangeModel + "] with offset=[" + result.getRecordMetadata().offset() + "]");
      }

      @Override
      public void onFailure(Throwable ex) {
        logger.info("Unable to send message=[" + userChangeModel + "] due to : " + ex.getMessage());
      }
    });
  }
}

