package com.ivoronline.springboot_validation_requestparameters.controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
@RestController
public class MyController {

  //==================================================================
  // HELLO
  //==================================================================
  @RequestMapping("Hello")
  String hello(
    @NotBlank(message = "Name should not be blank") @RequestParam String  name,
    @NotNull                                       @RequestParam Integer age
  ) {
    return "Hello " + name;
  }

  //==================================================================
  // MISSING PARAMETER EXCEPTIONS    (it only catches first exception)
  //==================================================================
  // http://localhost:8080/Hello
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public String missingParameterExceptions(MissingServletRequestParameterException exception) {

    //GET EXCEPTION DETAILS
    String parameterType = exception.getParameterType(); //String
    String parameterName = exception.getParameterName(); //name
    String message       = exception.getMessage();       //Required String parameter 'name' is not present

    //RETURN MESSAGE
    return message;

  }

  //==================================================================
  // CONSTRAINT VIOLATION EXCEPTIONS
  //==================================================================
  // http://localhost:8080/Hello?name=
  @ExceptionHandler(ConstraintViolationException.class)
  public String constraintViolationExceptions(ConstraintViolationException exception) {
    return exception.getMessage();     //validateHTTPParameters.name: must not be blank
  }

}
