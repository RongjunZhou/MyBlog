package com.example.myblog.Utils;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
//统一返回工具类
public class ResultData<T> {
    @JSONField
    private boolean success;
    @JSONField(ordinal = 1)
    private Integer errCode;
    @JSONField(ordinal = 2)
    private String errMsg;
    @JSONField(ordinal = 3)
    private T data;

    //成功时的返回
    public static <T> ResultData<T> success(T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setSuccess(true);
        resultData.setErrMsg(null);
        resultData.setErrCode(null);
        resultData.setData(data);
        return resultData;
    }

    //不成功的返回
    public static <T> ResultData<T> fail(Integer code, String errMsg) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setSuccess(false);
        resultData.setErrCode(code);
        resultData.setErrMsg(errMsg);
        return resultData;
    }

}
