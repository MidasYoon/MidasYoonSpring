package com.midas.yoon.errors;

import com.midas.yoon.utils.ApiUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import static com.midas.yoon.utils.ApiUtils.error;

@ControllerAdvice
public class GeneralExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private ResponseEntity<ApiUtils.ApiResult<?>> newResponse(Throwable throwable, HttpStatus status) {
        return newResponse(throwable.getMessage(), status);
    }

    private ResponseEntity<ApiUtils.ApiResult<?>> newResponse(String message, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(error(message, status), headers, status);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(Exception e) {
        return newResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<?> handleException(Exception e) {
        return newResponse("요청 작업을 처리하던 도중 오류가 발생하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
//
//    @ExceptionHandler(UnauthorizedException.class)
//    public ResponseEntity<?> handleUnauthorizedException(Exception e) {
//        return newResponse(e, HttpStatus.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler({
//            IllegalArgumentException.class,
//            IllegalStateException.class,
//            ConstraintViolationException.class,
//            MethodArgumentNotValidException.class
//    })
//    public ResponseEntity<?> handleBadRequestException(Exception e) {
//        log.debug("Bad request exception occurred: {}", e.getMessage(), e);
//        if (e instanceof MethodArgumentNotValidException) {
//            return newResponse(
//                    ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage(),
//                    HttpStatus.BAD_REQUEST
//            );
//        }
//        return newResponse(e, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(HttpMediaTypeException.class)
//    public ResponseEntity<?> handleHttpMediaTypeException(Exception e) {
//        return newResponse(e, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
//    }
//
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public ResponseEntity<?> handleMethodNotAllowedException(Exception e) {
//        return newResponse(e, HttpStatus.METHOD_NOT_ALLOWED);
//    }
//
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<?> handleHttpMessageNotReadableException(Exception e) {
//        return newResponse("Required request body is missing", HttpStatus.BAD_REQUEST);
//    }
}