package com.ipiecoles.java.mdd050.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ipiecoles.java.mdd050.exception.EmployeException;
import com.ipiecoles.java.mdd050.model.Employe;
import com.ipiecoles.java.mdd050.service.EmployeService;

@RestController
@RequestMapping("/employes")
public class UserController {
	
	@Autowired
	private EmployeService employeService;
	
	// Levée d'exception si id not found
	private final static String EMP_NOT_FOUND = "Employé non trouvé"; 
	
	
	@GetMapping("/count")
	public Long countEmployes() {
		return employeService.countAllEmploye(); 
	}
	
	@GetMapping("/details/{id}")
	public Employe getEmploye(@PathVariable("id") Long id) throws EmployeException {
		Employe empSearch = employeService.findById(id); 
		if (empSearch == null || id == 0) {
			throw new EntityNotFoundException(EMP_NOT_FOUND); 
		}
		return empSearch; 
	}
	
	@SuppressWarnings("null")
	@RequestMapping(
		params = {"matricule"}, 
		method = RequestMethod.GET
	)
	public Employe searchByMatricule(@RequestParam("matricule") String matricule) throws EmployeException{
		Employe empSearch = employeService.findMyMatricule(matricule); 
		if (empSearch == null) {
			throw new EntityNotFoundException(EMP_NOT_FOUND); 
		}
		return empSearch; 
	}
	
	
	
	

}