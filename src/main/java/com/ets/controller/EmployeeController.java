package com.ets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ets.model.Employee;
import com.ets.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@PostMapping
	public Employee create(@RequestBody Employee emp) {
		return service.createEmployee(emp);
	}

	@GetMapping
	public List<Employee> getAll() {
		return service.getAllEmployees();
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long empId) {
		service.deleteEmployee(empId);
	}
}
