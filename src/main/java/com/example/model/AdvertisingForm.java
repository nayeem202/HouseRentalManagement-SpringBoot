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
	@Column(unique = true, length = 20)
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

	private String img2;
	private String imgUri2;

	private String img3;
	private String imgUri3;

	private String video;
	private String videoType;
	
	private double lat;
	private double lng;
	

	@ManyToOne(optional = false)
	@JoinColumn(name = "adminid", nullable = true)
	private UserModel user;
	
	
	
	
	

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public String getImgUri2() {
		return imgUri2;
	}

	public void setImgUri2(String imgUri2) {
		this.imgUri2 = imgUri2;
	}

	
	
	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getVideoType() {
		return videoType;
	}

	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}

	public String getImg3() {
		return img3;
	}

	public void setImg3(String img3) {
		this.img3 = img3;
	}

	public String getImgUri3() {
		return imgUri3;
	}

	public void setImgUri3(String imgUri3) {
		this.imgUri3 = imgUri3;
	}

	public String getImg() {
		return img2;
	}

	public void setImg(String fileName) {
		this.img2 = fileName;
	}

	public String getImgUri() {
		return imgUri2;
	}

	public void setImgUri(String fileDownloadUri) {
		this.imgUri2 = fileDownloadUri;
	}

	public AdvertisingForm() {
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
