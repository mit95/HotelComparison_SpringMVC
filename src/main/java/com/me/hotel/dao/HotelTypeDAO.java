package com.me.hotel.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import java.util.List;

import com.me.hotel.pojo.*;
import com.me.hotel.exception.ReviewException;
import com.me.hotel.exception.HotelException;
import com.me.hotel.exception.UserException;

public class HotelTypeDAO extends DAO {
	
	public HotelTypeDAO() {}
	
	//Add new hotelType
		public int createHotelType(HotelType hotelType) throws HotelException {
			int result = 0;
			try {
				begin();
				getSession().save(hotelType);
				commit();
				result =1;
				return result;
			} catch (HibernateException e) {
				rollback();
				throw new HotelException("Exception while creating user: " + e.getMessage());
			}
		}
		
		//Add new Hotel
		public int createHotel(Hotel hotel) throws HotelException {
			int result = 0;
			try {
				begin();
				getSession().merge(hotel);
				commit();
				result =1;
				return result;
			} catch (HibernateException e) {
				rollback();
				throw new HotelException("Exception while creating user: " + e.getMessage());
			}
		}
		
		//List all Hotel Types
		public List<HotelType> hotelTypeList() throws HotelException {
			try {
				begin();
				Query q = getSession().createQuery("from HotelType");
				List<HotelType> hotelTypelist = q.list();
				commit();
				return hotelTypelist;
			} catch (HibernateException e) {
				rollback();
				throw new HotelException("Could not list the HotelType", e);
				}
			}
		
		//List all Hotels under a HotelType
				public List<Hotel> hotelList(String hotelTypeName) throws HotelException {
					try {
						begin();
						Criteria hotelList = getSession().createCriteria(Hotel.class);
						Criteria hotelTypeList = hotelList.createCriteria("hotelType");
						hotelTypeList.add(Restrictions.eq("hotelType",hotelTypeName));
						
						hotelList.addOrder(Order.asc("hotelPrice"));
						//hotelList.addOrder(Order.desc("userViewCount"));
						
						List<Hotel> result = hotelList.list();
						commit();
						return result;
					} catch (HibernateException e) {
						rollback();
						throw new HotelException("Could not list the Hotel Type", e);
						}
					}
				
				//Get detail of a hotel
				public List<Hotel> hotelDetails(String hotName) throws HotelException {
					try {
						begin();
						
						Criteria hotelDetail = getSession().createCriteria(Hotel.class);
						
						Criterion hotelName = Restrictions.like("hotelName",hotName);
						
						hotelDetail.add(hotelName);
						hotelDetail.setMaxResults(10);
						List<Hotel> hotelList = hotelDetail.list();
						commit();
						return hotelList;
					} catch (HibernateException e) {
						rollback();
						throw new HotelException("Could not list the Hotel", e);
					}
			}
				
				//Update Hotel
				public int updateHotel(Hotel hotel) throws HotelException {
					int result = 0;
					try {
						begin();
						getSession().merge(hotel);
						commit();
						result =1;
						return result;
					} catch (HibernateException e) {
						rollback();
						throw new HotelException("Exception while creating user: " + e.getMessage());
					}
				}
				
				//Get description of 1 Hotel
				public Hotel hotelDescription(String hotName) throws HotelException {
					try {
						begin();
						
						Criteria hotelDetail = getSession().createCriteria(Hotel.class);
						
						Criterion hotelName = Restrictions.eq("hotelName",hotName);
						
						hotelDetail.add(hotelName);
						//hotelDetail.setMaxResults(10);
						Hotel hotel = (Hotel) hotelDetail.uniqueResult();
						commit();
						return hotel;
					} catch (HibernateException e) {
						rollback();
						throw new HotelException("Could not list the Hotel", e);
					}
		
				}
				
				//Add review to a hotel
				public int addReview(Review review) throws HotelException {
					int result = 0;
					try {
						begin();
						getSession().merge(review);
						commit();
						result = 1;
						return result;
					} catch (HibernateException e) {
						rollback();
						throw new HotelException("Exception while creating user: " + e.getMessage());
					}
				}
				
				//Get review of a hotel
				public List<Review> getReview(String selectedHotel) throws ReviewException {
					try {
						begin();
						Criteria reviewList = getSession().createCriteria(Review.class);
						Criteria hotel = reviewList.createCriteria("hotel");
						hotel.add(Restrictions.eq("hotelName",selectedHotel));
						
						List<Review> result = reviewList.list();
						commit();
						return result;
					} catch (HibernateException e) {
						rollback();
						throw new ReviewException("Exception while creating user: " + e.getMessage());
						}
					}
				
				//List all Hotels
				public List<Hotel> allHotelList() throws HotelException {
					try {
						begin();
						Query q = getSession().createQuery("from Hotel");
						List<Hotel> hotelList = q.list();
						commit();
						return hotelList;
					} catch (HibernateException e) {
						rollback();
						throw new HotelException("Could not list the Hotel", e);
						}
					}
				
				//List all Owners
				public List<Owner> ownerList() throws UserException {
					try {
						begin();
						Query q = getSession().createQuery("from Owner");
						List<Owner> ownerList = q.list();
						commit();
						return ownerList;
					} catch (HibernateException e) {
						rollback();
						throw new UserException("Could not list the Owner", e);
						}
					}
}
