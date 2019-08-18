package com.me.hotel.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.mindrot.jbcrypt.BCrypt;

import com.me.hotel.pojo.*;
import com.me.hotel.dao.DAO;
import com.me.hotel.exception.UserException;

public class UserDAO extends DAO{
	
	public UserDAO() {}
	
	//Moderator registration
		public int registerModerator(Moderator moderator) throws UserException {
			int result = 0;
			try {
				begin();
				getSession().save(moderator);
				commit();
				result =1;
				return result;
			} catch (HibernateException e) {
				rollback();
				throw new UserException("Exception while creating user: " + e.getMessage());
			}
		}
		
		//Client registration
		public int registerClient(Client client) throws UserException {
			int result = 0;
			try {
				begin();
				getSession().save(client);
				commit();
				result =1;
				return result;
			} catch (HibernateException e) {
				rollback();
				throw new UserException("Exception while creating user: " + e.getMessage());
			}
		}
		
		//Owner registration
				public int registerOwner(Owner owner) throws UserException {
					int result = 0;
					try {
						begin();
						getSession().save(owner);
						commit();
						result =1;
						return result;
					} catch (HibernateException e) {
						rollback();
						throw new UserException("Exception while creating user: " + e.getMessage());
					}
				}
				
				//Authenticate User
				
				public String authenticateUser(String username, String password) throws UserException{
					String result = "notAUser";
					
					begin();
					if (username.startsWith("own")) {
						Query query = getSession().getNamedQuery("authenticateOwner");
						query.setParameter("username", username);
						//query.setParameter("password", password);
						Owner own = (Owner) query.uniqueResult();
						if (own == null || !BCrypt.checkpw(password, own.getPassword()))
							return result;
						else {
							result = username;
						}
							
					}
					
					else if (username.startsWith("client")) {
						Query query = getSession().getNamedQuery("authenticateClient");
						query.setParameter("username", username);
						//query.setParameter("password", password);
						Client client = (Client) query.uniqueResult();
						
						if (client == null || !BCrypt.checkpw(password, client.getPassword()))
							return result;
						else {
							result = username;
						}
							
					}
					
					else {
						Query query = getSession().getNamedQuery("authenticateModerator");
						query.setParameter("username", username);
						query.setParameter("password", password);
						Moderator moderator = (Moderator) query.uniqueResult();
						
						if (moderator == null)
							return result;
						else {
							result = username;
						}
					}
					
					commit();
					return result;
					
				}
				
				//Get all list of clients
				public List<Client> clientList() throws UserException {
					try {
						begin();
						Query q = getSession().createQuery("from Client");
						List<Client> clientList = q.list();
						commit();
						return clientList;
					} catch (HibernateException e) {
						rollback();
						throw new UserException("Could not list the Client", e);
					}
				}
				
				//Get all list owners
				public List<Owner> ownerList() throws UserException {
					try {
						begin();
						Query q = getSession().createQuery("from Owner");
						List<Owner> ownerList = q.list();
						commit();
						return ownerList;
					} catch (HibernateException e) {
						rollback();
						throw new UserException("Could not list Owner", e);
					}
			}
				
				//Get details of a Owner
				public List<Owner> ownerDetails(String ownerName) throws UserException {
					try {
						begin();
						Criteria ownerDetail = getSession().createCriteria(Owner.class);
						Criterion ownerFirstName = Restrictions.like("firstName",ownerName);
						Criterion ownerLastName = Restrictions.like("lastName",ownerName);
						Criterion ownerUserName = Restrictions.like("ownerUserName",ownerName);
						Disjunction orExp = Restrictions.disjunction();
						orExp.add(ownerFirstName);orExp.add(ownerLastName);orExp.add(ownerUserName);
						ownerDetail.add(orExp);
						List<Owner> ownerList = ownerDetail.list();
						commit();
						return ownerList;
					} catch (HibernateException e) {
						rollback();
						throw new UserException("Could not list the Owner", e);
					}
			}
				
				//Get detail of a users //REVIEW
				public List<Client> userDetails(String userName) throws UserException {
					try {
						begin();
						Criteria userDetail = getSession().createCriteria(Client.class);
						Criterion clientFirstName = Restrictions.like("firstName",userName);
						Criterion clientLastName = Restrictions.like("lastName",userName);
						Criterion clientUserName = Restrictions.like("clientUserName",userName);
						Disjunction orExp = Restrictions.disjunction();
						orExp.add(clientFirstName);orExp.add(clientLastName);orExp.add(clientUserName);
						userDetail.add(orExp);
						List<Client> list = userDetail.list();
						commit();
						return list;
					} catch (HibernateException e) {
						rollback();
						throw new UserException("Could not list the Client", e);
					}
			}
				
				//Get client details
				public Client getClientDetail(String clientName) throws UserException {
					try {
						begin();
						Criteria crit = getSession().createCriteria(Client.class);
						
						Criterion userName = Restrictions.eq("clientUserName",clientName);
						crit.add(userName);
						Client client = (Client) crit.uniqueResult();
						commit();
						return client;
					} catch (HibernateException e) {
						rollback();
						throw new UserException("Could not list the client", e);
					}
				}	
		

}
