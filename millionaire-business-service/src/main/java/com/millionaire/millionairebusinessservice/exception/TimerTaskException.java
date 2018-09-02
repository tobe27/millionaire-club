package com.millionaire.millionairebusinessservice.exception;

public class TimerTaskException extends Exception {

    //异常信息
    public String message;
    //重写构造方法
    public TimerTaskException(String message) {

        //这句话的意思是调用Exception的有参构造方法。
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
