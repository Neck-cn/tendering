package cn.ych.tendering.handler;

import cn.ych.tendering.exception.TenderingException;
import cn.ych.tendering.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> handlerException(Exception e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            logger.error(stackTraceElement.toString());
        }
        e.printStackTrace();
        if (e instanceof NoHandlerFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Result(404, e.getMessage()));
        } else if (e instanceof TenderingException) {
            return ResponseEntity.status(((TenderingException) e).getCode()).body(new Result(((TenderingException) e).getCode(), e.getMessage()));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Result(500, e.getMessage()));
        }
    }
}