package com.chinasoft.actualcombattable.actualcombattable.response;

import org.springframework.stereotype.Component;

/**
 * 工厂模式
 * 接口信息生成工具
 * 。@Component 添加到Spring组件中
 */
@Component
public class ResultGenerator extends RestResult{

    private static final String SUCCESS = "success";
    RestResult restResult=new RestResult();

    //成功
    public RestResult getSuccessResult() {
        return new RestResult()
                .setCode(RestCode.SUCCESS)
                .setMessage(SUCCESS);
    }
    //成功，附带额外数据
    public  RestResult getSuccessResult(Object data) {
        return new RestResult()
                .setCode(RestCode.SUCCESS)
                .setMessage(SUCCESS)
                .setData(data);
    }
    //成功，自定义消息及数据
    public  RestResult getSuccessResult(String message,Object data) {
        return new RestResult()
                .setCode(RestCode.SUCCESS)
                .setMessage(message)
                .setData(data);
    }
    //失败，附带消息
    public  RestResult getFailResult(String message) {
        return new RestResult()
                .setCode(RestCode.FAIL)
                .setMessage(message);
    }
    //失败，自定义消息及数据
    public RestResult getFailResult(String message, Object data) {
        return new RestResult()
                .setCode(RestCode.FAIL)
                .setMessage(message)
                .setData(data);
    }
    //自定义创建
    public RestResult getFreeResult(RestCode code, String message, Object data) {
        return new RestResult()
                .setCode(code)
                .setMessage(message)
                .setData(data);
    }
}
