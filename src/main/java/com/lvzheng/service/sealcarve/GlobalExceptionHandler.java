package com.lvzheng.service.sealcarve;

import com.lvzheng.service.sealcarve.util.JSONResult;
import com.lvzheng.service.sealcarve.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 全局异常处理
     * @param e RuntimeException
     * @param response HttpServletResponse
     * @return error JSONResult
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public JSONResult exceptionHandler(RuntimeException e, HttpServletResponse response) {
        logger.error("", e);
        return ResultUtil.error(-1, "error");
    }

}