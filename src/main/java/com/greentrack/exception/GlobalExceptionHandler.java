package com.greentrack.exception;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,String>> handleRuntime(RuntimeException ex){
        Map<String,String> m = new HashMap<>(); m.put("error", ex.getMessage()); return ResponseEntity.badRequest().body(m);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidation(MethodArgumentNotValidException ex){
        Map<String,String> m = new HashMap<>(); ex.getFieldErrors().forEach(err -> m.put(err.getField(), err.getDefaultMessage())); return ResponseEntity.badRequest().body(m);
    }
}
