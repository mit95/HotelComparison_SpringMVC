package com.me.hotel.controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.me.hotel.dao.HotelTypeDAO;
import com.me.hotel.dao.UserDAO;
import com.me.hotel.exception.ReviewException;
import com.me.hotel.exception.HotelException;
import com.me.hotel.exception.UserException;
import com.me.hotel.pojo.HotelType;
import com.me.hotel.pojo.Client;
import com.me.hotel.pojo.Review;
import com.me.hotel.controller.hotelDetailPDFImplementation;
import com.me.hotel.pojo.Hotel;

@Controller
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	private HotelTypeDAO hotelTypeDao;
	
	@Autowired
	private UserDAO userDao;
	
	@RequestMapping(value = "/hotelTypeList.htm", method = RequestMethod.GET)
	public ModelAndView getHotelTypeList(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
			HttpSession session = req.getSession();
			ModelAndView mv = null;
			try {
				List<HotelType> hotelTypeList = hotelTypeDao.hotelTypeList();
				session.setAttribute("hotelTypeList", hotelTypeList);
				mv = new ModelAndView("clientViewPage");
			} catch (HotelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return mv;
	}
	
	@RequestMapping(value = "/getHotel", method = RequestMethod.GET)
	public ModelAndView getHotelList(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
			
			String selectedHotelType = req.getParameter("selectedHotelType");
			HttpSession session = req.getSession();
			ModelAndView mv = null;
			try {
				List<Hotel> hotelList = hotelTypeDao.hotelList(selectedHotelType);
				session.setAttribute("allHotelList", hotelList);
				session.setAttribute("HotelTypeName", selectedHotelType);
				mv = new ModelAndView("hotelViewPage");
			} catch (HotelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return mv;
	}
	
	@RequestMapping(value = "/hotelData", method = RequestMethod.GET)
	public ModelAndView gethotelDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
			
			String selectedHotel = req.getParameter("selectedHotel");
			HttpSession session = req.getSession();
			ModelAndView mv = null;
			try {
				Hotel hotelList = hotelTypeDao.hotelDescription(selectedHotel);
				List<Review> review = hotelTypeDao.getReview(selectedHotel);
				session.setAttribute("hotelList", hotelList);
				session.setAttribute("reviewList", review);
				mv = new ModelAndView("hotelDescription");
			} catch (HotelException|ReviewException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return mv;
	}
	
	@RequestMapping(value = "/addReview", method = RequestMethod.POST)
	public ModelAndView addReview(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView mv = null;
		Review r = new Review();
		HttpSession session = req.getSession();
		String review = req.getParameter("review");
		Hotel hotel = (Hotel) session.getAttribute("hotelList");
		String client = (String)session.getAttribute("clientUserName");
		try {
		Client c = userDao.getClientDetail(client);
		r.setClient(c);
		r.setReview(review);
		r.setHotel(hotel);
		int flag = hotelTypeDao.addReview(r);
		if (flag==1)
			mv = new ModelAndView("hotelDescription");
		else
			mv = new ModelAndView("errorRegister");
		} catch (UserException|HotelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mv;
		
		
	}
	
	@RequestMapping(value = "/getPDFView", method = RequestMethod.GET)
	public View getPDFViewHotelDetails(HttpServletRequest req, HttpServletResponse resp) {
		
		String selectedHotelType = req.getParameter("selectedHotelType");
		
		View view = new hotelDetailPDFImplementation(hotelTypeDao,selectedHotelType);
		return view;

			
	}
	

}
