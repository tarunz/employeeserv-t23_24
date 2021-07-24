package com.paypal.bfs.test.employeeserv.utils;

import lombok.Getter;

public enum ErrorCode {

    EMP_NOT_FOUND("Employee is not valid."),
    DUPLICATE_EMPLOYEE_WITH_ID("Another employee with same id exists."),
    ILLEGAL_ID("Id is not valid number");

    @Getter
    private String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
