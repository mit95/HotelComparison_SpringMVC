package com.me.hotel.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.me.hotel.pojo.HotelType;
import com.me.hotel.pojo.Review;
import com.me.hotel.pojo.Owner;

@Entity
public class Hotel {
	
	public Hotel() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long hotelId;
	
	private String hotelName;
	private String hotelPrice;
	private String hotelImage;
	@Transient
	private MultipartFile photo;
	private String hotelDescription;
	
	private String hotelAddress;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="hotelTypeId")
	private HotelType hotelType;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(name = "Hotel_Owner_table", joinColumns = {
			@JoinColumn(name = "hotelId", nullable = false, updatable = false)
			},
	inverseJoinColumns = {
			@JoinColumn(name ="ownerId")
	})
	private Set<Owner> owner = new HashSet<Owner>();
	
	@OneToMany(mappedBy="hotel",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Review> review;

	public long getHotelId() {
		return hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelPrice() {
		return hotelPrice;
	}

	public void setHotelPrice(String hotelPrice) {
		this.hotelPrice = hotelPrice;
	}

	public String getHotelImage() {
		return hotelImage;
	}

	public void setHotelImage(String hotelImage) {
		this.hotelImage = hotelImage;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}

	public String getHotelDescription() {
		return hotelDescription;
	}

	public void setHotelDescription(String hotelDescription) {
		this.hotelDescription = hotelDescription;
	}

	public HotelType getHotelType() {
		return hotelType;
	}

	public void setHotelType(HotelType hotelType) {
		this.hotelType = hotelType;
	}

	public Set<Owner> getOwner() {
		return owner;
	}

	public void setOwner(Set<Owner> owner) {
		this.owner = owner;
	}

	public Set<Review> getReview() {
		return review;
	}

	public void setReview(Set<Review> review) {
		this.review = review;
	}

	public String getHotelAddress() {
		return hotelAddress;
	}

	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}
	
}
