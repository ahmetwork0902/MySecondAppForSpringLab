package ru.ahmetdavresh.MySecondAppForSpringLab.model;

import lombok.Getter;

@Getter
public class Response {
    @Getter
    private String uid;
    @Getter
    private String operationUid;
    @Getter
    private String systemTime;
    private String code;
    private String errorCode;
    private String errorMessage;
}
