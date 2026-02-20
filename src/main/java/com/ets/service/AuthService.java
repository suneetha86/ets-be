package com.ets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ets.model.Employee;
import com.ets.repository.EmployeeRepository;

@Service
public class AuthService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public Employee login(Long empId, String password) {

        // Use empId instead of id
    	Employee emp = repository.findById(empId)
    	        .orElseThrow(() -> new RuntimeException("User not found"));

    	if (!encoder.matches(password, emp.getPassword())) {
    	    throw new RuntimeException("Invalid password");
    	}

        return emp;
    }

   

}
