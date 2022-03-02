package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;




import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.model.UserModel;
import com.example.repository.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class LoginController {
	

	@Autowired
	private UserService userService;
	
	/*
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<Map> showCurrentUser(@RequestBody UserModel userModel, @PathVariable int id){
		
		Map<String, Object> map = new HashMap<String, Object>();
		UserService currentUser = (UserService) userService.findById((long) id).get();	
		map.put("data", currentUser);
		return (ResponseEntity<Map>) currentUser;
		
	}
	
	*/
	
	
	@PostMapping("/saveUser")
	UserModel user(@RequestBody UserModel user) {
	    return userService.save(user);
	  }
	
	

	
	@PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody UserModel user) {
        List<UserModel> users = (List<UserModel>) userService.findAll();
        Map<String, Object> map = new HashMap<String, Object>();
        
        for (UserModel other : users) {
            if (other.getUsername().equals(user.getUsername()) &&  other.getPassword().equals(user.getPassword())) {
            	  map.put("message", "Login Successful");
            	  map.put("status", "Success");
            	  map.put("data", other);
            	  return ResponseEntity.ok(map);
            	
            }
        }

        map.put("message", "Login fail!");
        map.put("status", "Failed");
        map.put("data", null);
        return ResponseEntity.status(412).body(map);
    }
    

	
	@GetMapping("/findAllUser")
	public List<UserModel> findAll() {
		List<UserModel> user = (List<UserModel>) userService.findAll();	
		return user;	
		
	}
	
	
	@GetMapping("/getUser/{id}")
	public UserModel findUser( @PathVariable int id) {
		UserModel user = (UserModel) userService.findById((long) id).get();	
		return user;		
	}
	
	@GetMapping("/getUserInfo/{username}")
	public UserModel findUserByUsername( @PathVariable String username) {
		UserModel user = (UserModel) userService.findByUserName((String) username).get(0);	
		return user;		
	}
	
	
	
	
	@DeleteMapping("/deleteUser/{id}")
	private void delete(@PathVariable int id) {
		userService.deleteById( (long) id);
	}
	
	
	
	
}

	
	
	
	

