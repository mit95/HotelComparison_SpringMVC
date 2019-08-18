package com.me.hotel.pojo;

import javax.persistence.NamedQuery;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.me.hotel.pojo.Address;
import com.me.hotel.pojo.HotelType;

import javax.persistence.ManyToMany;
import java.util.Set;

@NamedQuery(name="authenticateClient", query="from Client where clientUserName=:username")

@Entity
public class Client {
	
	public Client() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long clientId;
	
	private String firstName;
	private String lastName;
	private String emailId;
	private String clientUserName;
	private String password;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="addressId")
	private Address clientAddress;
	
	@ManyToMany(mappedBy="client",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<HotelType> hotelType;

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

	public String getClientUserName() {
		return clientUserName;
	}

	public void setClientUserName(String clientUserName) {
		this.clientUserName = clientUserName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(Address clientAddress) {
		this.clientAddress = clientAddress;
	}

	public Set<HotelType> getHotelType() {
		return hotelType;
	}

	public void setHotelType(Set<HotelType> hotelType) {
		this.hotelType = hotelType;
	}

	public long getClientId() {
		return clientId;
	}
	
}
