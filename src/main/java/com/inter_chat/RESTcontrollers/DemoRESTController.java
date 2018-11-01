package com.inter_chat.RESTcontrollers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRESTController {
	@RequestMapping("/demo")
	public ResponseEntity<String> demoImpl() {
		System.out.println("Demo REST API Created");
		return new ResponseEntity("Welcome to the REST Controller", HttpStatus.OK);
	}
}