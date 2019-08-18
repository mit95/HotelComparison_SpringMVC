package com.me.hotel.controller;

import java.util.List;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.me.hotel.dao.HotelTypeDAO;
import com.me.hotel.dao.UserDAO;
import com.me.hotel.exception.HotelException;
import com.me.hotel.exception.UserException;
import com.me.hotel.pojo.HotelType;
import com.me.hotel.pojo.Review;
import com.me.hotel.pojo.Owner;
import com.me.hotel.pojo.Hotel;

@Controller
@RequestMapping("/owner")
public class OwnerController {
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private HotelTypeDAO hotelTypeDao;
	
	@RequestMapping(value = "/ownerCont.htm", method = RequestMethod.GET)
	public String showForm(HttpServletRequest request, ModelMap model, Hotel hotel) throws HotelException, UserException {
		Owner hotelOwner = null;
		//to print feedback of Hotel for a given owner
		List<String> reviewList = new ArrayList<>();
		List<String>hotelList = new ArrayList<>();
		HttpSession session = request.getSession();
		//to get all the reviews of a particular hotel
		String ownerName = (String)session.getAttribute("ownerName");
		for(Owner o:hotelTypeDao.ownerList()) {
			if(o.getOwnerUserName().equals(ownerName)) {
				hotelOwner = o;
				break;
			}
		}
		for(Hotel h:hotelTypeDao.allHotelList()) {
			Set<Owner> ownerSet = new HashSet<>();
			ownerSet = h.getOwner();
			for(Owner o:ownerSet) {
				if(o.equals(hotelOwner)) {
					Set<Review> reviewSet = h.getReview();
					for(Review r:reviewSet) {
						hotelList.add(h.getHotelName());
						reviewList.add(r.getReview());
						}
				}
			}
		}
		session.setAttribute("Reviews",reviewList );
		session.setAttribute("Hotel",hotelList);
		//to set category while adding a product
		session.setAttribute("hotelTypeList", hotelTypeDao.hotelTypeList());
		return "ownerViewPage";
	}
	
	    //to disallow spring to bind the hotelType object
		@InitBinder
		public void customBinding(WebDataBinder binder) {
		    binder.setDisallowedFields(new String[] {"hotelType"});
			
		}
		
		//to create a new Hotel
		@RequestMapping(value = "/ownerCont.htm", method = RequestMethod.POST)
		public ModelAndView addHotel(@ModelAttribute("hotel") Hotel hotel,HttpServletRequest req,ModelMap model,HttpSession session) throws ServletException, HotelException, UserException{
			ModelAndView mv=null;
			HotelType hotel_hotelType = null;
			Owner hotelOwner = null;
			
			//Setting the hotelType of the hotel
			String hotelTypeName = req.getParameter("hotelType");
			for(HotelType ht:hotelTypeDao.hotelTypeList()) {
				if(ht.getHotelType().equals(hotelTypeName)) {
					hotel_hotelType = ht;
					break;
				}
			}
			hotel.setHotelType(hotel_hotelType);
			
			//setting Owner of the new hotel
			String ownerName = (String)session.getAttribute("ownerName");
			for(Owner o:hotelTypeDao.ownerList()) {
				if(o.getOwnerUserName().equals(ownerName)) {
					hotelOwner = o;
					break;
				}
			}
			Set ownerSet = new HashSet<>();
			Set hotelSet = new HashSet<>();
			hotelSet.add(hotel);
			ownerSet.add(hotelOwner);
			hotelOwner.setHotel(hotelSet);
			hotel.setOwner(ownerSet);
			
			//setting the photo of the Hotel
			String localPath = "C:\\Users\\Mit\\Desktop\\hotelComparerFiles";
			String photoNewName = generateFileName(hotel.getPhoto());
			hotel.setHotelImage(photoNewName);
			try {
				hotel.getPhoto().transferTo(new File(localPath,photoNewName));		
			}catch(Exception e) {
				
			}

			try {
				int flag = hotelTypeDao.createHotel(hotel);
				if (flag==1) {
					return new ModelAndView("hotelSuccessPage");
				}
				
			} catch (HotelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		private String generateFileName(MultipartFile multipart) {
			
			return new Date().getTime() + "-"+multipart.getOriginalFilename().replace(" ", "-");
		}
		
		//to display hotel form for updating
		@RequestMapping(value = "/ownerUpdate.htm", method = RequestMethod.GET)
		public ModelAndView updateHotelForm(HttpServletRequest req, HttpServletResponse resp,HttpSession session,Hotel hotel) throws ServletException, UserException{
			ModelAndView mv=null;
			Hotel selectedHotel = null;
			String hotelName = req.getParameter("hotelName");
			
			//Only allowing owner to edit his own hotel
			String ownerName = (String)session.getAttribute("ownerName");
			Owner hotelOwner = null;
			for(Owner o:hotelTypeDao.ownerList()) {
				if(o.getOwnerUserName().equals(ownerName)) {
					hotelOwner = o;
					break;
				}
			}
			int temp = 0;
			Set<Hotel> hotelsOfSelectedOwner = hotelOwner.getHotel();
			for(Hotel h:hotelsOfSelectedOwner) {
				String g = h.getHotelName();
				if(!(h.getHotelName().equalsIgnoreCase(hotelName))) {
					temp = 1;
				}
				else {
					temp = 0;
					break;
				}
			}
			if(temp==1) {
				return new ModelAndView("ownerViewPage");
			}
			
			try {
				List<Hotel> hotelList = new ArrayList<Hotel>();
				hotelList =  hotelTypeDao.hotelDetails(hotelName);
				if(hotelList.isEmpty()) {
					return new ModelAndView("ownerViewPage");
				}
				for(Hotel hot:hotelList) {
					if(hot.getHotelName().equalsIgnoreCase(hotelName)) {
						selectedHotel = hot;
						break;
					}
				}
				session = req.getSession();
				session.setAttribute("hotel", selectedHotel);
				return new ModelAndView("updateHotel");
			} catch (HotelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return null;
		
		}
		
		//to update the hotel as given by owner
		@RequestMapping(value = "/ownerUpdate.htm", method = RequestMethod.POST)
		public ModelAndView updateHotel(Hotel hotel,HttpServletRequest req,ModelMap model, HttpSession session) throws ServletException{
			ModelAndView mv=null;
			hotel = (Hotel) session.getAttribute("hotel");
			hotel.setHotelPrice(req.getParameter("price"));
			hotel.setHotelDescription(req.getParameter("description"));
			//hotel.setHotelAddress(req.getParameter("address"));
			
			try {
				int flag = hotelTypeDao.updateHotel(hotel);
				if (flag==1) {
					return new ModelAndView("ownerViewPage");
				}
				
			} catch (HotelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		

}
