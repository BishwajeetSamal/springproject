package com.papun.springproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.papun.springproject.exception.ResourceNotFoundException;
import com.papun.springproject.model.Employeee;
import com.papun.springproject.repository.EmployeeRepository;
@CrossOrigin(origins="http://localhost:3001")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	//get all employess
	@GetMapping("/employees")
	public List<Employeee> getAllEmployees(){
		return employeeRepository.findAll();
		
	}
	
	@PostMapping("/employees")
	//create employee rest Api
	public Employeee createEmployee(@RequestBody Employeee employee) {
		System.out.println(employee);
		return employeeRepository.save(employee);
	}
	
	//get employee by id RestAPI
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employeee> getEmployeeeById(@PathVariable long id) {
		Employeee employeeId = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not exist with Id :"+ id));
	return ResponseEntity.ok(employeeId);
	}
	
	//update employee rest API
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employeee> updateEmployee(@PathVariable long id,@RequestBody Employeee employeeDetails){
		Employeee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not exist with Id :"+ id));
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		
		Employeee updateEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updateEmployee);
		
		
	}
	
	//delete employee rest API
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable long id) {
		Employeee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not exist with Id :"+ id));
		employeeRepository.delete(employee);
		Map<String,Boolean> response = new HashMap<>();
		response.put("delete",Boolean.TRUE);
		return ResponseEntity.ok(response);
		
	}
}
