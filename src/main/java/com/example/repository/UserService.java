package com.example.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.AdvertisingForm;
import com.example.model.UserModel;


@Repository
public interface UserService extends CrudRepository<UserModel, Long> {
	
	@Query("Select add from UserModel add where add.username = ?1") 
	List <UserModel> findByUserName(String username);
		
}
