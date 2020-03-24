package com.spring.hibernate.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.hibernate.dao.CustomerDAO;
import com.spring.hibernate.entity.Customer;

@Service
public class CustomerServiceImp implements CustomerService {

	//need to inject dao
	@Autowired
	private CustomerDAO customerDAO;
	
	//define trasactional at the service layer
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {

		customerDAO.saveCustomer(theCustomer);
		
	}

	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		
		return customerDAO.getCustomer(theId);
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) {

		customerDAO.deleteCustomer(theId);
		
	}

	@Override
	@Transactional
	public List<Customer> searchCustomer(String theSerachName) {
		
		return customerDAO.searchCustomer(theSerachName);
	}

}
