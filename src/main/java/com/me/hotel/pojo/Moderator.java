package com.me.hotel.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.me.hotel.pojo.Address;
import com.me.hotel.pojo.HotelType;
import com.me.hotel.pojo.Client;
import com.me.hotel.pojo.Owner;

@NamedQuery(name="authenticateModerator", query="from Moderator where moderatorUserName=:username and password=:password")

@Entity
public class Moderator {
	
	public Moderator() {}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long moderatorId;
	
	private String firstName;
	private String lastName;
	private String emailId;
	private String moderatorUserName;
	private String password;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<HotelType> hotelType;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Client> client;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Owner> owner;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="addressId")
	private Address moderatorAddress;

	public long getModeratorId() {
		return moderatorId;
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

	public String getModeratorUserName() {
		return moderatorUserName;
	}

	public void setModeratorUserName(String moderatorUserName) {
		this.moderatorUserName = moderatorUserName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<HotelType> getHotelType() {
		return hotelType;
	}

	public void setHotelType(Set<HotelType> hotelType) {
		this.hotelType = hotelType;
	}

	public Set<Client> getClient() {
		return client;
	}

	public void setClient(Set<Client> client) {
		this.client = client;
	}

	public Set<Owner> getOwner() {
		return owner;
	}

	public void setOwner(Set<Owner> owner) {
		this.owner = owner;
	}

	public Address getModeratorAddress() {
		return moderatorAddress;
	}

	public void setModeratorAddress(Address moderatorAddress) {
		this.moderatorAddress = moderatorAddress;
	}
	
	
	
}
