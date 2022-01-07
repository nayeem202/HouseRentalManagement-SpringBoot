package com.example.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.AdvertisingForm;
import com.example.model.UserModel;

@Repository
public interface AdvertisingService extends CrudRepository<AdvertisingForm, Long>, JpaRepository<AdvertisingForm, Long>{

	
	
	@Query("Select add from AdvertisingForm add where add.user.id = ?1") 
	List<AdvertisingForm> findByUserId(long id);
	
	/*
	@Query("SELECT u FROM AdvertisingForm u WHERE u.bathrooms = ?1")
	List<AdvertisingForm> findByBathrooms(int bathrooms);
	
	*/




}
