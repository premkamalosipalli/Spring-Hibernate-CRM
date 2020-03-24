package com.spring.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.hibernate.entity.Customer;



@Repository
public class CustomerDAOImp implements CustomerDAO {

	// need to inject the session factory
		@Autowired
		private SessionFactory sessionFactory;
				
		@Override
		public List<Customer> getCustomers() {
			
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
					
			// create a query.. and sort it by last name
			Query<Customer> theQuery = 
					currentSession.createQuery("from Customer order by last_name", Customer.class);
			
			// execute query and get result list
			List<Customer> customers = theQuery.getResultList();
					
			// return the results		
			return customers;
		}

		@Override
		public void saveCustomer(Customer theCustomer) {
			
			//get the current hibernate session
			Session currentSession=sessionFactory.getCurrentSession();
			
			//save/update our new customer using hibernate
			currentSession.saveOrUpdate(theCustomer);
			
		}

		@Override
		public Customer getCustomer(int theId) {
			//get the current hibernate Session
			Session currentSession=sessionFactory.getCurrentSession();
			
			//retrive the data from database using the primary key
			Customer theCustomer=currentSession.get(Customer.class, theId);
			
			return theCustomer;
		}

		@Override
		public void deleteCustomer(int theId) {
			
			//get the current hibernate Session
			Session currentSession=sessionFactory.getCurrentSession();
			
			// delete object with primary key
			@SuppressWarnings("rawtypes")
			Query theQuery = 
					currentSession.createQuery("delete from Customer where id=:customerId");
			theQuery.setParameter("customerId", theId);
			
			theQuery.executeUpdate();
		}

		@Override
		public List<Customer> searchCustomer(String theSerachName) {
			//get the current hibernate Session
			Session currentSession=sessionFactory.getCurrentSession();
			
			Query<Customer> theQuery=null;
			
			if(theSerachName!=null&& theSerachName.trim().length()>0) {
				theQuery=currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like:theName",Customer.class);
				theQuery.setParameter("theName", "%"+theSerachName.toLowerCase()+"%");
			}else {
				theQuery=currentSession.createQuery("from Customer",Customer.class);
			}
			
			List<Customer> customers=theQuery.getResultList();
			return customers;
		}

}
