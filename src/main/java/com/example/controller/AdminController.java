package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.model.AdvertisingForm;
import com.example.model.UserModel;
import com.example.repository.AdvertisingService;
import com.example.repository.UserService;
import com.example.storage.controller.FileController;
import com.example.storage.payload.UploadFileResponse;
import com.example.storage.service.FileStorageService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)

public class AdminController {

	private static final Logger log = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private FileStorageService fileStorageService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private AdvertisingService advertiseService;

	@PostMapping("/saveadvertising")
	AdvertisingForm advertisingForm(@RequestBody AdvertisingForm advertisingForm) {
		return advertiseService.save(advertisingForm);
	}

	@PostMapping("/saveadvertising_withfile")
	public ResponseEntity<Map> saveFormData(@ModelAttribute AdvertisingForm advertisingForm,
			@RequestParam("file") MultipartFile file,
			@RequestParam("user_id") long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			String fileName = fileStorageService.storeFile(file);

			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
					.path(fileName).toUriString();
			advertisingForm.setImages(fileName);
			advertisingForm.setImagesUri(fileDownloadUri);
			
			UserModel user = userService.findById(userId).get();
			advertisingForm.setUser(user);
			advertisingForm = advertiseService.save(advertisingForm);
			map.put("status", "Success");
			map.put("data", advertisingForm);
			map.put("message", "Data saved successfully");
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			map.put("status", "failed");
			map.put("data", null);
			map.put("message", e.getLocalizedMessage());
			return ResponseEntity.status(500).body(map);
		}
		
	}

	@GetMapping("/getAddvertising")
	private List<AdvertisingForm> findAll() {
		return (List<AdvertisingForm>) (advertiseService).findAll();

	}

	@GetMapping("/getAddvertising/{id}")
	private AdvertisingForm findById(@PathVariable int id) {

		return advertiseService.findById((long) id).get();
	}

	@PutMapping("/updateadvertising/{id}")
	private AdvertisingForm advertisingFormU(@PathVariable long advertisingId,
			@RequestBody AdvertisingForm advertisingForm) {
		advertisingForm.setAdvertisingId(advertisingId);
		return advertiseService.save(advertisingForm);
	}

	@DeleteMapping("/deleteAdvertising/{id}")
	private void delete(@PathVariable int id) {
		advertiseService.deleteById((long) id);
	}

}
