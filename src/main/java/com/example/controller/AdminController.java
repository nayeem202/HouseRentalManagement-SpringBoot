package com.example.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.AdvertisingForm;

import com.example.repository.AdvertisingService;


@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)



public class AdminController {

	@Autowired
	private AdvertisingService advertiseService;
	
	
	
	@PostMapping("/saveadvertising")
	AdvertisingForm advertisingForm(@RequestBody AdvertisingForm  advertisingForm) {
	    return advertiseService.save(advertisingForm);
	  }
	
	
	@GetMapping("/getAddvertising")
	private List<AdvertisingForm> findAll(){
	 return  (List<AdvertisingForm>) (advertiseService).findAll();
		
	}
	
	@GetMapping("/getAddvertising/{id}")
	private AdvertisingForm findById(@PathVariable int id) {
		
		return advertiseService.findById((long) id).get();
	}
	
	

	@PutMapping("/updateadvertising/{id}")
	private AdvertisingForm advertisingFormU(@PathVariable long advertisingId,  @RequestBody AdvertisingForm  advertisingForm) {
		advertisingForm.setAdvertisingId(advertisingId);
		return advertiseService.save(advertisingForm);
	  }
	
	@DeleteMapping("/deleteAdvertising/{id}")
	private void delete(@PathVariable int id) {
		advertiseService.deleteById((long) id);
	}
	
	
	
	
	
	
	
	
}
