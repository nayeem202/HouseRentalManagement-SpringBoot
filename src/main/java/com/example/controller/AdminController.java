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
			@RequestParam("file") MultipartFile file, @RequestParam("user_id") long userId) {
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

	@PostMapping("/updateadvertising")
	public ResponseEntity<Map> saveFormData1(@ModelAttribute AdvertisingForm advertisingForm,
			@RequestParam("file") MultipartFile file, @RequestParam("user_id") long userId) {

		System.out.println(advertisingForm.getAdvertisingId());

		System.out.println(userId);

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
	public ResponseEntity<?> getAdvertising() {
		try {
			List<AdvertisingForm> model = (List<AdvertisingForm>) (advertiseService).findAll();
			return ResponseEntity.ok(model);
		} catch (Exception e) {
			return ResponseEntity.ok("getting data failed");
		}

	}

	
	// get Advertising details by advertisingId
	@GetMapping("/getAddvertising/{id}")
	public ResponseEntity<?> getAdvertisingById(@PathVariable int id) {
		try {
			AdvertisingForm model =  (AdvertisingForm) (advertiseService).findById((long) id).get();
			return ResponseEntity.ok(model);
		} catch (Exception e) {
			return ResponseEntity.ok("getting data failed");
		}

	}

	

	// Show advertising by User
	
	@GetMapping("/getAddvertisingOfUser/{id}")
	public ResponseEntity<?> getAdvertisingByUserId(@PathVariable int id) {
		try {
			List<AdvertisingForm> model = (List<AdvertisingForm>) (advertiseService).findByUserId((long) id);
			return ResponseEntity.ok(model);
		} catch (Exception e) {
			return ResponseEntity.ok("getting data failed");
		}

	}
	

	/*
	
	@DeleteMapping("/deleteAdvertising/{id}")
	public ResponseEntity<?> deleteAdvertisingByUserId(@PathVariable long id) {
		try {
			 advertiseService.deleteById((long) id);
			return ResponseEntity.ok("successfully deleted");
		} catch (Exception e) {
			return ResponseEntity.ok("deleting data failed");
		}

	}
	
	*/
	
	

	@DeleteMapping("/deleteAdvertising/{id}")
	private void delete(@PathVariable int id) {
		advertiseService.deleteById((long) id);
	}
	
	
	//Type wise search
	
	@GetMapping("/getAddvertisingByType/{type}")
	public ResponseEntity<?> findAllByType(@PathVariable String type) {
		try {
			List<AdvertisingForm> advertisingtype =(List<AdvertisingForm>) (advertiseService).categorytype((String) type);
			return ResponseEntity.ok(advertisingtype);
		} catch (Exception e) {
			return ResponseEntity.ok("getting typewise data failed");
		}

	}
	
	// search advertising by SearchText
	@GetMapping("/getAddvertisingBySearch/{searchText}")
	public ResponseEntity<?> findAllBysearching(@PathVariable String searchText) {
		try {
			List<AdvertisingForm> advertisingSearching = (List<AdvertisingForm>) (advertiseService).searchAdvertise(searchText);
			return ResponseEntity.ok(advertisingSearching);
		} catch (Exception e) {
			return ResponseEntity.ok("searching data failed");
		}

	}
	

	
	//filtering
		@GetMapping("/getAddvertisingbyLocation/{location}/{price}")
		public ResponseEntity<?> getAdvertisingBySearching(@PathVariable String location, @PathVariable int price) {
			try {
				List<AdvertisingForm>  model =  (List<AdvertisingForm>) (advertiseService).findByIdSearchinggggggg(location,price);
				return ResponseEntity.ok(model);
			} catch (Exception e) {
				return ResponseEntity.ok(e.getLocalizedMessage());
			}

		}

	

}
