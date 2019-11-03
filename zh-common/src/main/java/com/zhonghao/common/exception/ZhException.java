package com.zhonghao.common.exception;

import com.zhonghao.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
// 自定义异常
public class ZhException extends RuntimeException{

    private ExceptionEnum exceptionEnum;
}
