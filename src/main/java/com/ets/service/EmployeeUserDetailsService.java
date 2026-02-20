//package com.ets.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.ets.model.Employee;
//import com.ets.repository.EmployeeRepository;
//
////import org.springframework.security.core.userdetails.*;
////
////import org.springframework.stereotype.Service;
////
////import com.ets.repository.EmployeeRepository;
////
////import lombok.RequiredArgsConstructor;
////
////@Service
////
////@RequiredArgsConstructor
////
////public class EmployeeUserDetailsService implements UserDetailsService {
////
////  private final EmployeeRepository repo;
////
////  @Override
////
////  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////
////      var user = repo.findByUsername(username)
////
////              .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
////
////      return org.springframework.security.core.userdetails.User
////
////              .withUsername(user.getUsername())
////
////              .password(user.getPassword())
////
////              .roles(user.getRole())
////
////              .build();
////
////  }
////
////}
////
//
//
//@Service
//package com.ets.security;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.*;
//import org.springframework.stereotype.Service;
//
//import com.ets.model.Employee;
//import com.ets.repository.EmployeeRepository;
//
//@Service
//public class EmployeeUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Employee employee = employeeRepository.findByEmployeename(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Employee not found"));
//
//        return new User(
//                employee.getUsername(),
//                employee.getPassword(),
//                List.of(new SimpleGrantedAuthority("ROLE_" + employee.getRole().name()))
//        );
//    }
//}