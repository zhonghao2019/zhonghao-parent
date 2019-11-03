package com.zhonghao.common.advice;

import com.zhonghao.common.enums.ExceptionEnum;
import com.zhonghao.common.exception.ZhException;
import com.zhonghao.common.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
//通用异常处理器
public class CommonExceptionHandler {

//    拦截指定异常
    @ExceptionHandler(ZhException.class)
    public ResponseEntity<ExceptionResult> handleException(ZhException e) {
        ExceptionEnum em = e.getExceptionEnum();
        return ResponseEntity.status(em.getCode()).body(new ExceptionResult(em));
    }
}
