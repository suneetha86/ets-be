package com.ets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ets.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
	
	
	   // Profile findByEmployeeId(Long id);

}




 


