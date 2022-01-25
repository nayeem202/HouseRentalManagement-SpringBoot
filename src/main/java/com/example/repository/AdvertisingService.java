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

	
	
	

	@Query("Select add from AdvertisingForm add where add.location = ?1 and"
			+ " (add.price BETWEEN ?2 AND ?3) and" 
			+ " (add.sqft BETWEEN ?4 AND ?5) and"
			+ " add.type =?6 and"
			+ " add.status =?7 and"
			+ " add.bedrooms =?8 and"
			+ " add.bathrooms =?9")
	List<AdvertisingForm> findByadvancedSearchinggggggg(String location, int price, int price2, int sqft, int sqft2, String type, 
			String status, int bedrooms, int bathrooms);

	
	
	@Query("Select p from AdvertisingForm p where p.price between ?1 and ?2")
	List<AdvertisingForm> findByadvancedSearching(int price, int price2);





}
