package com.example.iam.approval.service;

import com.example.iam.approval.model.ActionEnum;
import com.example.iam.approval.model.FlowEnum;
import com.example.iam.approval.model.UserChangeModel;
import com.example.iam.approval.repository.ApprovalRequestRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ApprovalServiceImpl implements ApprovalService {
  @Autowired
  ApprovalRequestRepository approvalRequestRepository;

  @Autowired
  KafkaEventProducer kafkaEventProducer;
  @Override
  public List<UserChangeModel> getApproval() {
    return approvalRequestRepository.getApprovalList();
  }

  @Override
  public UserChangeModel getApprovalRequest(String requestId) {
    return approvalRequestRepository.getApprovalRequest(requestId);
  }

  @Override
  public void approveRequest(String requestId, ActionEnum actionEnum) {
    UserChangeModel userChangeModel = approvalRequestRepository.getApprovalRequest(requestId);
    if (actionEnum == ActionEnum.APPROVED){
      userChangeModel.setFlowEnum(FlowEnum.APPROVAL_NOT_REQUIRED);
    }
    kafkaEventProducer.sendMessage(userChangeModel);
    approvalRequestRepository.deleteApprovalRequest(requestId);
  }

  @Override
  public UserChangeModel addApprovalRequest(UserChangeModel userChangeModel) {
    return approvalRequestRepository.addApprovalRequest(userChangeModel);
  }
}
