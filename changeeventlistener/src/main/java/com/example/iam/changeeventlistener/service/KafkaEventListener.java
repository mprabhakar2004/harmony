package com.example.iam.changeeventlistener.service;

import com.example.iam.changeeventlistener.model.FlowEnum;
import com.example.iam.changeeventlistener.model.UserChangeModel;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class KafkaEventListener {
  Logger logger = Logger.getLogger(KafkaEventListener.class.getName());
  @Autowired
  RestTemplate restTemplate;

  @Value(value = "${harmony.apim.endpoint}")
  String harmonyWorkflowUri;

  @Value(value = "${approval.service.uri}")
  String approvalServiceUri;

  @KafkaListener(topics = "${approval.topicName}", containerFactory = "kafkaListenerContainerFactory")
  public void listenChangeEvent(UserChangeModel userChangeModel) {
    HttpEntity<UserChangeModel> request = new HttpEntity<>(userChangeModel);
    if (userChangeModel.getFlowEnum() == FlowEnum.APPROVAL_REQUIRED) {
      logger.info("****Triggering " + userChangeModel.getChangeEventType() + " approval process for "
          + userChangeModel.getUserAdName() + " with request Id: " + userChangeModel.getChangeRequestId() + "****");
      restTemplate.exchange(approvalServiceUri, HttpMethod.POST, request, UserChangeModel.class);
    } else {
      logger.info("****Triggering " + userChangeModel.getChangeEventType() + " harmony workflow for "
          + userChangeModel.getUserAdName() + " with request Id: " + userChangeModel.getChangeRequestId() + "****");
      ResponseEntity<String> result = restTemplate.exchange(harmonyWorkflowUri, HttpMethod.POST, request, String.class);
      logger.info("****" + result.getBody() + "****");
    }
  }
}
