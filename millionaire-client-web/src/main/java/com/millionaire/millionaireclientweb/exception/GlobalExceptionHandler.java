package com.millionaire.millionaireclientweb.exception;

import com.millionaire.millionaireclientweb.result.ErrorInfo;
import com.millionaire.millionairecommonapi.excepion.AliyunAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = AliyunAPIException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, AliyunAPIException e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        logger.info("============aliyun图片上传错误=========");
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("new data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }
}
