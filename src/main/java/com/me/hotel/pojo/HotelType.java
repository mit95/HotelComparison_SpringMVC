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
import javax.persistence.OneToMany;

import com.me.hotel.pojo.Client;
import com.me.hotel.pojo.Hotel;

@Entity
public class HotelType {

	public HotelType() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long hotelTypeId;
	
	private String hotelType;
	private String hotelTypeDescription;
	
	@OneToMany(mappedBy="hotelType",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinColumn(name="hotelId")
	private Set<Hotel> hotel;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(name = "hotelType_client_table", joinColumns = {
			@JoinColumn(name = "hotelTypeId", nullable = false, updatable = false)},
	inverseJoinColumns = {
			@JoinColumn(name ="clientId")
	})
	private Set<Client> client = new HashSet<Client>();

	public long getHotelTypeId() {
		return hotelTypeId;
	}

	public String getHotelType() {
		return hotelType;
	}

	public void setHotelType(String hotelType) {
		this.hotelType = hotelType;
	}

	public String getHotelTypeDescription() {
		return hotelTypeDescription;
	}

	public void setHotelTypeDescription(String hotelTypeDescription) {
		this.hotelTypeDescription = hotelTypeDescription;
	}

	public Set<Hotel> getHotel() {
		return hotel;
	}

	public void setHotel(Set<Hotel> hotel) {
		this.hotel = hotel;
	}

	public Set<Client> getClient() {
		return client;
	}

	public void setClient(Set<Client> client) {
		this.client = client;
	}
	
	
}
