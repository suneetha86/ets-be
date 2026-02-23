package com.ets.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ets.model.Profile;
import com.ets.repository.ProfileRepository;

@Service
	public class ProfileService {

	    @Autowired
	    private ProfileRepository profileRepository;
	    

	    public Profile saveProfile(Profile profile) {
	        return profileRepository.save(profile);
	    }
	    

	    public List<Profile> getAllProfiles() {
	        return profileRepository.findAll();
	    }
	    

	    public Optional<Profile> getByEmployeeId(Long id) {
	        return profileRepository.findById(id);
	    }
	    

	    public Profile updateProfile(Long id, Profile profile) {
	        Profile existing = profileRepository.findById(id).orElseThrow();
	        

	        existing.setName(profile.getName());
	        existing.setDesignation(profile.getDesignation());
	        existing.setSystemName(profile.getSystemName());
	        existing.setCohort(profile.getCohort());
	        existing.setLocation(profile.getLocation());
	        existing.setEmail(profile.getEmail());
	        existing.setPhone(profile.getPhone());
	        existing.setAttendance(profile.getAttendance());
	        existing.setCodingScore(profile.getCodingScore());
	        existing.setProfileImage(profile.getProfileImage());

	        return profileRepository.save(existing);
	    }
	    

	    public void deleteProfile(Long id) {
	        profileRepository.deleteById(id);
	    }
	    
	}


