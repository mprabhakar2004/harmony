package com.example.iam.approval.service;

import com.example.iam.approval.model.UserChangeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailManager {
  @Autowired
  private JavaMailSender javaMailSender;

  public void sendEmail(UserChangeModel userChangeModel) {
    SimpleMailMessage msg = new SimpleMailMessage();
    msg.setTo(userChangeModel.getUserEmail());

    msg.setSubject("Testing from Spring Boot");
    msg.setText("Hello World \n Spring Boot Email");

    javaMailSender.send(msg);
  }
}
