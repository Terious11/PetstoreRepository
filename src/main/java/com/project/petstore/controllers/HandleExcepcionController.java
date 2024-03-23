package com.project.petstore.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.project.petstore.models.ApiResponse;

@RestControllerAdvice
public class HandleExcepcionController {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ApiResponse> badRequestError404(Exception ex){
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setCode(HttpStatus.NOT_FOUND.value());
		apiResponse.setMessage(ex.getMessage());
		apiResponse.setType("Api Rest no Encontrado ");
		
		return ResponseEntity.status(404).body(apiResponse);
	}
	
	public ResponseEntity<ApiResponse> errorInternUsername500(Exception ex){
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		apiResponse.setMessage("Usuario no encontrado");
		apiResponse.setType("Error Usuario");
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
	}
	
	public ResponseEntity<ApiResponse> errorInternId500(Exception ex){
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		apiResponse.setMessage("Id no encontrado");
		apiResponse.setType("Error Id");
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
	}
	
	public ResponseEntity<ApiResponse> errorPet(Exception ex){
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		apiResponse.setMessage("Id No Encontrado de Pet");
		apiResponse.setType("Error Id");
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
	}
	
	
	
}
