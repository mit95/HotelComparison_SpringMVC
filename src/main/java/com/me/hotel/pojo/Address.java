package com.me.hotel.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.me.hotel.pojo.Moderator;
import com.me.hotel.pojo.Client;
import com.me.hotel.pojo.Owner;

@Entity
public class Address {
	
	public Address() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long addressId;
	
	private String streetName;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	
	@OneToOne(mappedBy="moderatorAddress",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Moderator moderator;
	
	@OneToOne(mappedBy="clientAddress",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Client client;
	
	@OneToOne(mappedBy="ownerAddress",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Owner owner;

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Moderator getModerator() {
		return moderator;
	}

	public void setModerator(Moderator moderator) {
		this.moderator = moderator;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public long getAddressId() {
		return addressId;
	}

	
}
