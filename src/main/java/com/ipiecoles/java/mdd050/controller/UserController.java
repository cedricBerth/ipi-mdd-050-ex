package com.ipiecoles.java.mdd050.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipiecoles.java.mdd050.service.EmployeService;

@RestController
@RequestMapping("/employes")
public class UserController {
	
	@Autowired
	private EmployeService employeService;
	
	@GetMapping("/count")
	public Long countEmployes() {
		return employeService.countAllEmploye(); 
	}
	
	

}