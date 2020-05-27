package com.example.iam.changeeventlistener.model;

import java.io.Serializable;


public class UserChangeModel implements Serializable {
  private String changeRequestId;
  private ChangeEventType changeEventType;
  private String userAdName;
  private String userEmail;
  private String userOrg;
  private String userDepartment;
  private String userDesignation;
  private FlowEnum flowEnum;

  @Override
  public String toString() {
    return "UserChangeModel{" + "changeRequestId='" + changeRequestId + '\'' + ", changeEventType=" + changeEventType
        + ", userAdName='" + userAdName + '\'' + ", userEmail='" + userEmail + '\'' + ", userOrg='" + userOrg + '\''
        + ", userDepartment='" + userDepartment + '\'' + ", userDesignation='" + userDesignation + '\'' + ", flowEnum="
        + flowEnum + '}';
  }

  public String getChangeRequestId() {
    return changeRequestId;
  }

  public void setChangeRequestId(String changeRequestId) {
    this.changeRequestId = changeRequestId;
  }

  public ChangeEventType getChangeEventType() {
    return changeEventType;
  }

  public void setChangeEventType(ChangeEventType changeEventType) {
    this.changeEventType = changeEventType;
  }

  public String getUserAdName() {
    return userAdName;
  }

  public void setUserAdName(String userAdName) {
    this.userAdName = userAdName;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getUserOrg() {
    return userOrg;
  }

  public void setUserOrg(String userOrg) {
    this.userOrg = userOrg;
  }

  public String getUserDepartment() {
    return userDepartment;
  }

  public void setUserDepartment(String userDepartment) {
    this.userDepartment = userDepartment;
  }

  public String getUserDesignation() {
    return userDesignation;
  }

  public void setUserDesignation(String userDesignation) {
    this.userDesignation = userDesignation;
  }

  public FlowEnum getFlowEnum() {
    return flowEnum;
  }

  public void setFlowEnum(FlowEnum flowEnum) {
    this.flowEnum = flowEnum;
  }
}
