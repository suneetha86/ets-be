package com.ets.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ets.dto.LoginDTO;
import com.ets.model.Employee;
import com.ets.service.AuthService;

 
@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
 
    @Autowired
    private AuthService Service;
 
    @PostMapping("/login")
    public Employee login(@RequestBody Employee request) {
        return Service.login(request.getEmail(), request.getPassword());
    }}
//
//    @PostMapping("/login")
//    public Employee login(@RequestBody LoginDTO request) {
//        return Service.login(request.getUsername(), request.getPassword());
//    }
//}


//package com.ets.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import com.ets.dto.LoginDTO;
//import com.ets.model.Employee;
//import com.ets.service.AuthService;
//
//@RestController
//@RequestMapping("/api/auth")
//@CrossOrigin
//public class AuthController {
//
//    @Autowired
//    private AuthService service;
//
//    @PostMapping("/login")
//    public Employee login(@RequestBody LoginDTO request) {
//
//        return service.login(
//                request.getUsername(),
//                request.getPassword()
//        );
//    }
//}
























