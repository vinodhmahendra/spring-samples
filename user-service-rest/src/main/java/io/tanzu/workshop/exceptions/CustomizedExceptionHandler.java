package io.tanzu.workshop.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
//@Component
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request){
		
		CustomErrorResponse errorResponse = new CustomErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
