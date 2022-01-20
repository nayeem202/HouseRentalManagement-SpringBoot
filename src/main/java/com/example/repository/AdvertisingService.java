package com.example.repository;



import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.AdvertisingForm;
import com.example.model.UserModel;

@Repository
public interface AdvertisingService extends JpaRepository<AdvertisingForm, Long>{

	
	
	@Query("Select add from AdvertisingForm add where add.user.id = ?1") 
	List<AdvertisingForm> findByUserId(long id);
	
	
	@Query("SELECT a FROM AdvertisingForm a WHERE a.type= ?1")
    public List<AdvertisingForm> categorytype(String type);
	
	
	@Query("SELECT a from AdvertisingForm a where a.location like %:searchText% or "
			+ "a.type like %:searchText% or a.status like %:searchText% or"
			+ " a.price like %:searchText%")	
	public List<AdvertisingForm> searchAdvertise(String searchText);

	
	
	/*
	@Query("Select add from AdvertisingForm add where add.location = ?1 and add.type =?2" )
	public List<AdvertisingForm> advancedsearchAdvertise(String advancedsearchText);
*/


	@Query("Select add from AdvertisingForm add where add.location = ?1 and add.price=?2" )
	List<AdvertisingForm> findByIdSearchinggggggg(String location, int price);



}
