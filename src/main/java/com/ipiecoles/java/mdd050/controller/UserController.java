package com.ipiecoles.java.mdd050.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	private final static String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8"; 
	
	
	@GetMapping("/count")
	public Long countEmployes() {
		return employeService.countAllEmploye(); 
	}
	
	@RequestMapping(
		value= "/{id}", 
		produces = APPLICATION_JSON_CHARSET_UTF_8, 
		method = RequestMethod.GET)
	public Employe getEmploye(@PathVariable("id") Long id) {
		Employe empSearch = employeService.findById(id); 
		if (empSearch == null || id == 0) {
			throw new EntityNotFoundException(EMP_NOT_FOUND); 
		}
		return empSearch; 
	}
	
	
	@SuppressWarnings("null")
	@RequestMapping(
		params = {"matricule"}, 
		method = RequestMethod.GET, 
		produces = APPLICATION_JSON_CHARSET_UTF_8
	)
	public Employe searchByMatricule(@RequestParam("matricule") String matricule){
		Employe empSearch = employeService.findMyMatricule(matricule); 
		if (empSearch == null) {
			throw new EntityNotFoundException(EMP_NOT_FOUND); 
		}
		return empSearch; 
	}
	
	 @RequestMapping (
		 params={"page", "size", "sortProperty", "sortDirection"}, 
		 method = RequestMethod.GET
	 )	 
	 public Page<Employe> employes(
		 @RequestParam("page") Integer page,
		 @RequestParam("size") Integer size,
		 @RequestParam("sortProperty") String sortProperty,
		 @RequestParam("sortDirection") String sortDirection
	) {
		Page<Employe> listeEmployes = employeService.findAllEmployes(page, size, sortProperty, sortDirection);
	 return listeEmployes; 
	 }
	
	
	
	

}