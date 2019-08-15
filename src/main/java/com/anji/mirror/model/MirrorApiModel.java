package com.anji.mirror.model;

import java.io.Serializable;

public class MirrorApiModel implements Serializable {
    public static final String  LOG_FLAG  = "magic-mirror";
    private static final String LOG_SPLIT = "|";
    private String              logFlag=LOG_FLAG;                   // 魔镜日志标识
    private String              apiName;                   // 接口名称
    private Long                requestTimestamp;          // 请求时间戳
    private Long                responseTimestamp;         // 应答时间戳
    private String              requestDatetime;           // 请求时间 yyyy-MM-dd hh:MM:ss
    private String              responseDatetime;          // 应答时间 yyyy-MM-dd hh:MM:ss
    private Long                responseTime;              // RT时间 ms
    private String              responseSuccess;           // 返回成功标识 1
    private String              responseFail;              // 返回失败标识 1
    private String              respCode;                  // 应答码
    private int                 responseStatusCode;

    public int getResponseStatusCode() {
        return responseStatusCode;
    }

    public void setResponseStatusCode(int responseStatusCode) {
        this.responseStatusCode = responseStatusCode;
    }

    public String getLogFlag() {
        return logFlag;
    }

    public void setLogFlag(String logFlag) {
        this.logFlag = logFlag;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public Long getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(Long requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    public Long getResponseTimestamp() {
        return responseTimestamp;
    }

    public void setResponseTimestamp(Long responseTimestamp) {
        this.responseTimestamp = responseTimestamp;
    }

    public String getRequestDatetime() {
        return requestDatetime;
    }

    public void setRequestDatetime(String requestDatetime) {
        this.requestDatetime = requestDatetime;
    }

    public String getResponseDatetime() {
        return responseDatetime;
    }

    public void setResponseDatetime(String responseDatetime) {
        this.responseDatetime = responseDatetime;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
    }

    public String getResponseSuccess() {
        return responseSuccess;
    }

    public void setResponseSuccess(String responseSuccess) {
        this.responseSuccess = responseSuccess;
    }

    public String getResponseFail() {
        return responseFail;
    }

    public void setResponseFail(String responseFail) {
        this.responseFail = responseFail;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    @Override
    public String toString() {
        //logFlag   apiName  requestDatetime responseDatetime respCode responseSuccess responseFail  responseTime responseStatusCode
        return new StringBuffer(logFlag).append(LOG_SPLIT).append(apiName).append(LOG_SPLIT).append(requestDatetime).append(LOG_SPLIT).append(responseDatetime).append(LOG_SPLIT).append(respCode)
                .append(LOG_SPLIT).append(responseSuccess).append(LOG_SPLIT).append(responseFail).append(LOG_SPLIT).append(responseTime).append(LOG_SPLIT).append(responseStatusCode).toString();
    }
}
