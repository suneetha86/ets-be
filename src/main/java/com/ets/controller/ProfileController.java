package com.ets.controller;

import java.util.List;
import java.util.Optional;

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

import com.ets.model.Profile;
import com.ets.service.ProfileService;

@RestController
	@RequestMapping("/api/profiles")
	@CrossOrigin("*")
	public class ProfileController {
	    @Autowired
	    private ProfileService profileService;

	    @PostMapping("/create")
	    public Profile createProfile(@RequestBody Profile profile) {
	        return profileService.saveProfile(profile);
	    }

	    @GetMapping("/employee")
	    public List<Profile> getAllProfiles() {
	        return profileService.getAllProfiles();
	    }

	    @GetMapping("/employee/{id}")
	    public Optional<Profile> getByEmployeeId(@PathVariable("id") Long id) {
          	        return profileService.getByEmployeeId(id);
	    }
	    
	    
	    
		    @PutMapping("/{id}")
	    public Profile updateProfile(@PathVariable Long id,
	                                 @RequestBody Profile profile) {
	        return profileService.updateProfile(id, profile);
	    }

	    @DeleteMapping("/{id}")
	    public String deleteProfile(@PathVariable Long id) {
	        profileService.deleteProfile(id);
	        return "Profile Deleted Successfully";
	    }
	}


