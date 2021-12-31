package com.example.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.AdvertisingForm;
import com.example.model.UserModel;

@Repository
public interface AdvertisingService extends CrudRepository<AdvertisingForm, Long>{





}
