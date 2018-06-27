package com.ipiecoles.java.mdd050.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipiecoles.java.mdd050.model.Employe;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmploye(@PathVariable("id") Long id) {
		Employe empSearch = employeService.findById(id); 
		if (empSearch == null || id == 0) {
			return new ResponseEntity<String>("Employé inconnu", HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<Employe>(empSearch, HttpStatus.ACCEPTED); 
	}
	
	

}