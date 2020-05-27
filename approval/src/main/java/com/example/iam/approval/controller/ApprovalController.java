package com.example.iam.approval.controller;

import com.example.iam.approval.model.ActionEnum;
import com.example.iam.approval.model.UserChangeModel;
import com.example.iam.approval.service.ApprovalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApprovalController {

  @Autowired
  ApprovalService approvalService;
  @GetMapping("/approvals")
  List<UserChangeModel> getApprovalList(){
    return approvalService.getApproval();
  }

  @GetMapping("/approvals/{requestId}")
  UserChangeModel getApprovalRequest(@PathVariable("requestId") String requestId){
    return approvalService.getApprovalRequest(requestId);
  }

  @PostMapping("/approvals/{requestId}")
  void approveRequest(@PathVariable("requestId") String requestId, @RequestParam("action") ActionEnum actionEnum){
     approvalService.approveRequest(requestId, actionEnum);
  }

  @PostMapping("/approvals")
  UserChangeModel addApprovalRequest(@RequestBody UserChangeModel userChangeModel){
    return approvalService.addApprovalRequest(userChangeModel);
  }
}
