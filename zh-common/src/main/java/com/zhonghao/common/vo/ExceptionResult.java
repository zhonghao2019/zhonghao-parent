package com.zhonghao.common.vo;

import com.zhonghao.common.enums.ExceptionEnum;
import lombok.Data;

@Data
// 封装异常结果
public class ExceptionResult {

    private int status;
    private String message;
    private Long timestamp;

    public ExceptionResult(ExceptionEnum exceptionEnum) {
        this.status = exceptionEnum.getCode();
        this.message = exceptionEnum.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
