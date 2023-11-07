package com.qn.qiniudemoapi.handler;


import com.qn.qiniudemoapi.util.ResponseDataStructure;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseDataStructure exceptionHandler(@NotNull Exception ex){
        ex.printStackTrace();
        return new ResponseDataStructure(500,"服务器异常",null);
    }
}
