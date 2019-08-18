package com.me.hotel.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import org.apache.commons.mail.*;
import com.me.hotel.dao.UserDAO;
import com.me.hotel.exception.UserException;
import com.me.hotel.pojo.*;

@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	private UserDAO userDao;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
		ModelAndView mv=null;
		
		String user = req.getParameter("username");
		String pswd = req.getParameter("password");
		
		try {
			String checkValidUser = userDao.authenticateUser(user, pswd);
			
			if (checkValidUser.startsWith("moderator"))
				mv = new ModelAndView(new RedirectView("moderator/completeInfo.htm",false));
			else if (checkValidUser.startsWith("own")) {
				HttpSession session = req.getSession();
				session.setAttribute("ownerName",user);
				mv = new ModelAndView(new RedirectView("owner/ownerCont.htm",false));}
			else if (checkValidUser.startsWith("client"))
			{
				HttpSession session = req.getSession();
				session.setAttribute("clientUserName",user);
				mv = new ModelAndView(new RedirectView("client/hotelTypeList.htm",false));
				}
			else if (checkValidUser.equalsIgnoreCase("notAUser"))
				mv = new ModelAndView("registerUser");
			
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mv;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest req, HttpServletResponse resp) throws UserException{
		ModelAndView mv=null;
		
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmpassword");
		
		if (password.equals(confirmPassword)) {
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String emailId = req.getParameter("emailId");
			String streetName = req.getParameter("street");
			String city = req.getParameter("city");
			String state = req.getParameter("state");
			String zipCode = req.getParameter("zip");
			String country = req.getParameter("Country");
			String username = req.getParameter("username");
			
			Address address = new Address();
			address.setStreetName(streetName);
			address.setCity(city);
			address.setState(state);
			address.setZipCode(zipCode);
			address.setCountry(country);
			
			String hashedPswd = BCrypt.hashpw(password, BCrypt.gensalt());
			
			String userType = req.getParameter("usertype");
			
			if (userType.equalsIgnoreCase("Owner"))
			{
				Owner owner = new Owner();
				owner.setFirstName(firstName);
				owner.setLastName(lastName);
				owner.setEmailId(emailId);
				owner.setOwnerUserName("own"+username);
				owner.setOwnerAddress(address);
				owner.setPassword(hashedPswd);
				
				int flag = userDao.registerOwner(owner);
				try {
					SendEmail("mitkatwala95@gmail.com",owner.getOwnerUserName());
				} catch (EmailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (flag==1)
				mv = new ModelAndView("home");
			}else if (userType.equalsIgnoreCase("Client")) 
				{
					Client client = new Client();
					client.setFirstName(firstName);
					client.setLastName(lastName);
					client.setEmailId(emailId);
					client.setClientUserName("client"+username);
					client.setClientAddress(address);
					client.setPassword(hashedPswd);
					//client.setVisitCount(0);
					
					int flag = userDao.registerClient(client);
					try {
						SendEmail("mitkatwala95@gmail.com",client.getClientUserName());
					} catch (EmailException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (flag==1)
						mv = new ModelAndView("home");
				}
			

		}else {
			mv = new ModelAndView("errorRegister");
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/registerNew", method = RequestMethod.GET)
	public ModelAndView newUserRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
		return new ModelAndView("registerUser");
		
	}
	
	public void SendEmail(String emailID,String username) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		//User your gmail username and password
		email.setAuthenticator(new DefaultAuthenticator("mitwebtools@gmail.com", "webTools"));
		email.setSSLOnConnect(true);
		email.setFrom("no-reply@msis.neu.edu");
		email.setSubject("TestMail");
		email.setMsg("Registered successfully	"+"/n Your username is	"+ username);
		email.addTo(emailID);
		email.send();
	}
	
	@RequestMapping(value = "/logout.htm", method = RequestMethod.POST)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return new ModelAndView("home");
	}
}
