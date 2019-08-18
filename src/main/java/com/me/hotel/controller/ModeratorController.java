package com.me.hotel.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.hotel.dao.HotelTypeDAO;
import com.me.hotel.dao.UserDAO;
import com.me.hotel.exception.HotelException;
import com.me.hotel.exception.UserException;
import com.me.hotel.pojo.HotelType;
import com.me.hotel.pojo.Hotel;

@Controller
@RequestMapping("/moderator")
public class ModeratorController {

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private HotelTypeDAO hotelTypeDao;
	
	//home page for Moderator
		@RequestMapping(value = "/completeInfo.htm", method = RequestMethod.GET)
		public ModelAndView showForm(HttpServletRequest request, ModelMap model, Hotel hotel) {
			ModelAndView mv=null;
			HttpSession session = request.getSession();
			//getting all list of all Hotel Type
			try {
				session.setAttribute("hotelTypeList", hotelTypeDao.hotelTypeList());
				
			} catch (HotelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				session.setAttribute("clientList", userDao.clientList());
			} catch (UserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				session.setAttribute("ownerList", userDao.ownerList());
			} catch (UserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mv = new ModelAndView("moderatorPage");
			return mv;	
		
		}
		
		@RequestMapping(value = "/createHotelType.htm", method = RequestMethod.POST)
		public ModelAndView createHotelType(HttpServletRequest req,ModelMap model, HttpSession session,ModelMap map) throws ServletException, HotelException{
			ModelAndView mv=null;
			
			//checking if type already exist
			String hotelTypeName = req.getParameter("hotelTypeName");
//			for(HotelType ht:hotelTypeDao.hotelTypeList()) {
//				if(ht.getHotelType().equalsIgnoreCase(hotelTypeName)) {
//					return new ModelAndView("moderatorPage");
//				}
//			}
			HotelType ht = new HotelType();
			ht.setHotelType(hotelTypeName);
			ht.setHotelTypeDescription(req.getParameter("hotelTypeDescription"));
			
			
			try {
				int flag = hotelTypeDao.createHotelType(ht);
				if (flag==1) {
					return new ModelAndView("moderatorPage");
				}
				
			} catch (HotelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	
}
