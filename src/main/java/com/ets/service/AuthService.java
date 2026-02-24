//package com.ets.service;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.ets.model.Employee;
//import com.ets.repository.EmployeeRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//
//    private final EmployeeRepository employeeRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public String register(Employee employee) {
//
//        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
//        employee.setRole(employee.getRole());
//
//        employeeRepository.save(employee);
//
//        return "Employee registered successfully";
//    }
//}


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

    // REGISTER
    public Employee register(Employee employee) {
        employee.setPassword(encoder.encode(employee.getPassword()));
        return repository.save(employee);
    }

    // LOGIN
    public Employee login(String email, String password) {

        Employee employee = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (!encoder.matches(password, employee.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return employee;
    }
}
