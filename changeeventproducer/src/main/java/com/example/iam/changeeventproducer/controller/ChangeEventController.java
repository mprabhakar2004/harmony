package com.example.iam.changeeventproducer.controller;

import com.example.iam.changeeventproducer.model.UserChangeModel;
import com.example.iam.changeeventproducer.service.KafkaEventProducer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ChangeEventController {
  @Autowired
  KafkaEventProducer kafkaEventProducer;

  @PostMapping("/changeevent")
  public void produceSingleEvent(@RequestBody UserChangeModel userChangeModel) {
    kafkaEventProducer.sendMessage(userChangeModel);
  }

  @PostMapping("/bulkchangeevent")
  public void produceBulkEvent(@RequestBody List<UserChangeModel> userChangeModels) {
    for (UserChangeModel userChangeModel:userChangeModels) {
      kafkaEventProducer.sendMessage(userChangeModel);
    }
  }
}
