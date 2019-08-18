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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import com.me.hotel.pojo.Address;
import com.me.hotel.pojo.Hotel;

@NamedQuery(name="authenticateOwner", query="from Owner where ownerUserName=:username")

@Entity
public class Owner {

	public Owner() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long ownerId;
	
	private String firstName;
	private String lastName;
	private String emailId;
	private String ownerUserName;
	private String password;
	
	@ManyToMany(mappedBy="owner",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Hotel> hotel = new HashSet<Hotel>();
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="addressId")
	private Address ownerAddress;

	public long getOwnerId() {
		return ownerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getOwnerUserName() {
		return ownerUserName;
	}

	public void setOwnerUserName(String ownerUserName) {
		this.ownerUserName = ownerUserName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Hotel> getHotel() {
		return hotel;
	}

	public void setHotel(Set<Hotel> hotel) {
		this.hotel = hotel;
	}

	public Address getOwnerAddress() {
		return ownerAddress;
	}

	public void setOwnerAddress(Address ownerAddress) {
		this.ownerAddress = ownerAddress;
	}
	
	
}
