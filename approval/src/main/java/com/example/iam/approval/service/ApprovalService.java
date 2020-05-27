package com.example.iam.approval.service;

import com.example.iam.approval.model.ActionEnum;
import com.example.iam.approval.model.UserChangeModel;
import java.util.List;


public interface ApprovalService {
  List<UserChangeModel> getApproval();
  UserChangeModel getApprovalRequest(String requestId);
  UserChangeModel addApprovalRequest(UserChangeModel userChangeModel);
  void approveRequest(String requestId, ActionEnum actionEnum);
}
