package com.anjuke.incubator.mss.controller.exception;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anjuke.incubator.mss.common.Const;
import com.anjuke.incubator.mss.exception.MssBaseException;

/**
 * 全局的 HTTP 异常处理，把异常信息以 JSON 包装并返回给客户端
 *
 */
@ControllerAdvice
public class GlobalExceptionController{

    private static final Logger LOGGER = LoggerFactory
            .getLogger(GlobalExceptionController.class);

    private final HttpStatus defaultErrorStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<HashMap<String, String>> defaultErrorHandler(
            HttpServletRequest req, Exception e) {

        HttpStatus realStatus = defaultErrorStatus;

        // 如果是从 MssBaseException 继承而来，就设置为 4xx
        // 默认返回 5xx
        if( e instanceof MssBaseException){
            realStatus = HttpStatus.BAD_REQUEST;
        }

        LOGGER.debug("{}", e.getClass());

        HashMap<String, String> result = new HashMap<String, String>();
        result.put(Const.MESSAGE, e.getMessage());
        result.put(Const.STATUS, Const.FAIL);
        ResponseEntity<HashMap<String, String>> response = new ResponseEntity<HashMap<String, String>>(
                result, realStatus);

        return response;
    }
}
