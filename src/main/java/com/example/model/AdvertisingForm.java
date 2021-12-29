package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "advertisingform")
public class AdvertisingForm {
	
	 @Id()
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(unique = true, length=20)
	 private long advertisingId;
	 
	 @Transient
	 @JsonIgnore
	 MultipartFile file;
	
	 
	 private String location;
	 private String type;
	 private String status;
	 private int bedrooms;
	 private int bathrooms;
	 private int price;
	 private int sqft;
	 private String additionalinformation;
	 private String images;
	 private String imagesUri;
	 
	 @ManyToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "adminid", nullable = false)
	 private UserModel user;
	 
	 

	public AdvertisingForm() {
	}

	public AdvertisingForm(Long advertisingId, String location, String type, String status, int bedrooms, int bathrooms,
			int price, int sqft, String additionalinformation, String images, UserModel user) {
		super();
		this.advertisingId = advertisingId;
		this.location = location;
		this.type = type;
		this.status = status;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.price = price;
		this.sqft = sqft;
		this.additionalinformation = additionalinformation;
		this.images = images;
		this.user = user;
	}

	public Long getAdvertisingId() {
		return advertisingId;
	}

	public void setAdvertisingId(Long advertisingId) {
		this.advertisingId = advertisingId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}

	public int getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(int bathrooms) {
		this.bathrooms = bathrooms;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSqft() {
		return sqft;
	}

	public void setSqft(int sqft) {
		this.sqft = sqft;
	}

	public String getAdditionalinformation() {
		return additionalinformation;
	}

	public void setAdditionalinformation(String additionalinformation) {
		this.additionalinformation = additionalinformation;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public void setAdvertisingId(long advertisingId) {
		this.advertisingId = advertisingId;
	}

	public String getImagesUri() {
		return imagesUri;
	}

	public void setImagesUri(String imagesUri) {
		this.imagesUri = imagesUri;
	}
	 
	 
	
	 
	 
	
	 
	 
	
}
