package com.millionaire.millionaireserverweb.config;

import com.millionaire.millionairecommonapi.excepion.AliyunAPIException;
import com.millionaire.millionaireserverweb.result.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
/**
 * @ControllerAdvice是一个@Component，用于定义@ExceptionHandler，
 * @InitBinder和@ModelAttribute方法，适用于所有使用@RequestMapping方法。
 */
public class GlobalExceptionHandler {
    @ExceptionHandler(value = AliyunAPIException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, AliyunAPIException e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("new data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }
}
