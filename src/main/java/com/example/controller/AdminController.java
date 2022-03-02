package com.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)

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

	// Adding Multiple Image
	@PostMapping("/saveadvertising_withMultipleFile")
	public ResponseEntity<List> saveFormData2(@ModelAttribute AdvertisingForm advertisingForm,
			@RequestParam("files") MultipartFile[] filess, @RequestParam("file") MultipartFile video, @RequestParam("user_id") long userId) {
		
		
		
		List message = new ArrayList();
		int count = 0;
		try {
			
			String fileNameV = fileStorageService.storeFile(video);
			String fileDownloadUriV = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
					.path(fileNameV).toUriString();
			 advertisingForm.setVideoType(video.getContentType());
			 advertisingForm.setVideo(fileDownloadUriV);
			 
			
			for (MultipartFile file : filess) {

				String fileName = fileStorageService.storeFile(file);
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
						.path(fileName).toUriString();
				
				if (count == 0) {
					advertisingForm.setImages(fileName);
					advertisingForm.setImagesUri(fileDownloadUri);
				}

				if (count == 1) {
					advertisingForm.setImg(file.getOriginalFilename());
					advertisingForm.setImgUri(fileDownloadUri);
				}

				if (count == 2) {
					advertisingForm.setImg3(fileName);
					advertisingForm.setImgUri3(fileDownloadUri);
				}
				
				count++;

				UserModel user = userService.findById(userId).get();
				advertisingForm.setUser(user);
				advertiseService.save(advertisingForm);
				message.add(advertisingForm);
				message.add("Successfully! -> upload filename: " + file.getOriginalFilename());
				message.add("Successfully! -> upload videoType: " + file.getContentType());
				System.out.println(advertisingForm.getImg());
			}
		} catch (Exception e) {
			message.add("failed");
			message.add(null);
			message.add(e.getLocalizedMessage());
			return ResponseEntity.status(500).body(message);
		}
		return ResponseEntity.ok(message);
	}
	
	

	// updateAdvertising
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
			return ResponseEntity.ok(e);
		}

	}

	// get Advertising details by advertisingId
	@GetMapping("/getAddvertising/{id}")
	public ResponseEntity<?> getAdvertisingById(@PathVariable int id) {
		try {
			AdvertisingForm model = (AdvertisingForm) (advertiseService).findById((long) id).get();
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
	 * 
	 * @DeleteMapping("/deleteAdvertising/{id}") public ResponseEntity<?>
	 * deleteAdvertisingByUserId(@PathVariable long id) { try {
	 * advertiseService.deleteById((long) id); return
	 * ResponseEntity.ok("successfully deleted"); } catch (Exception e) { return
	 * ResponseEntity.ok("deleting data failed"); }
	 * 
	 * }
	 * 
	 */

	@DeleteMapping("/deleteAdvertising/{id}")
	private void delete(@PathVariable int id) {
		advertiseService.deleteById((long) id);
	}

	// Type wise search

	@GetMapping("/getAddvertisingByType/{type}")
	public ResponseEntity<?> findAllByType(@PathVariable String type) {
		try {
			List<AdvertisingForm> advertisingtype = (List<AdvertisingForm>) (advertiseService)
					.categorytype((String) type);
			return ResponseEntity.ok(advertisingtype);
		} catch (Exception e) {
			return ResponseEntity.ok("getting typewise data failed");
		}

	}

	// search advertising by SearchText
	@GetMapping("/getAddvertisingBySearch/{searchText}")
	public ResponseEntity<?> findAllBysearching(@PathVariable String searchText) {
		try {
			List<AdvertisingForm> advertisingSearching = (List<AdvertisingForm>) (advertiseService)
					.searchAdvertise(searchText);
			return ResponseEntity.ok(advertisingSearching);
		} catch (Exception e) {
			return ResponseEntity.ok("searching data failed");
		}

	}
	

	// filtering
	@GetMapping("/getAdvancedSearching/{location}/{price}/{price2}/{sqft}/{sqft2}/{type}/{status}/{bedrooms}/{bathrooms}")
	public ResponseEntity<?> getAdvertisingBySearching(@PathVariable String location, @PathVariable int price,
			@PathVariable int price2, @PathVariable int sqft, @PathVariable int sqft2, @PathVariable String type,
			@PathVariable String status, @PathVariable int bedrooms, @PathVariable int bathrooms) {
		try {
			List<AdvertisingForm> model = (List<AdvertisingForm>) (advertiseService).findByadvancedSearchinggggggg(
					location, price, price2, sqft, sqft2, type, status, bedrooms, bathrooms);
			return ResponseEntity.ok(model);
		} catch (Exception e) {
			return ResponseEntity.ok(e.getLocalizedMessage());
		}

	}

	/*
	 * @GetMapping("/getAdvancedSearching/{price}/{p}") public ResponseEntity<?>
	 * getAdvertisingBySearch(@PathVariable int price, @PathVariable int p) { try {
	 * List<AdvertisingForm> model = (List<AdvertisingForm>)
	 * (advertiseService).findByadvancedSearching(price, p); return
	 * ResponseEntity.ok(model); } catch (Exception e) { return
	 * ResponseEntity.ok(e.getLocalizedMessage()); }
	 * 
	 * }
	 */

}
