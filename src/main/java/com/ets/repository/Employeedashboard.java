package com.ets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ets.model.EmployeeDashboard;
import com.ets.statusenum.TaskStatus;

@Repository
public interface Employeedashboard extends JpaRepository<EmployeeDashboard, Long> {

	long countByEmployeeIdAndStatus(Long employeeId, TaskStatus status);

}
