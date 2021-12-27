package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.AdvertisingForm;

@Repository
public interface AdvertisingService extends CrudRepository<AdvertisingForm, Long>{

}
