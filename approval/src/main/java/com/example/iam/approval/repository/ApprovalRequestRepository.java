package com.example.iam.approval.repository;

import com.example.iam.approval.model.UserChangeModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;


@Repository
public class ApprovalRequestRepository {
  static private List<UserChangeModel> userChangeModelDb = new ArrayList<>();

  public UserChangeModel addApprovalRequest(UserChangeModel userChangeModel) {
    userChangeModelDb.add(userChangeModel);
    return userChangeModel;
  }

  public UserChangeModel getApprovalRequest(String requestId) {
    return userChangeModelDb.stream()
        .filter(x -> x.getChangeRequestId().equalsIgnoreCase(requestId))
        .findFirst()
        .orElse(null);
  }

  public List<UserChangeModel> getApprovalList() {
    return userChangeModelDb;
  }

  public void deleteApprovalRequest(String requestId) {
    UserChangeModel userChangeModel = userChangeModelDb.stream()
        .filter(x -> x.getChangeRequestId().equalsIgnoreCase(requestId))
        .findFirst()
        .orElse(null);
    userChangeModelDb.remove(userChangeModel);
  }
}
