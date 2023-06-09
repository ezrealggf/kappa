package org.ashe.kappa.infra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局捕获spring mvc抛出的异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalMVCAdvice {

    /**
     * we only need catch ServiceException and wrap it to json for view
     */
    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<String> catchException(ServiceException e) {
        // 记录日志
        // 通知运维
        // 通知开发
        log.error(String.format("======== %s ========", e.getClass().toString()), e);
        return ResponseEntity.status(521).body(e.getMessage());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<String> catchException(IllegalArgumentException e) {
        // 记录日志
        // 通知运维
        // 通知开发
        log.error(String.format("======== %s ========", e.getClass().toString()), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
