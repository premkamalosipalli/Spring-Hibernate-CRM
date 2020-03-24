package com.spring.hibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.hibernate.entity.Customer;
import com.spring.hibernate.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// nned to inject customerSerice
	@Autowired
	private CustomerService customerService;

	@GetMapping(path = "/list")
	public String listCustomers(Model theModel) {

		// get customers from the dao
		List<Customer> theCustomers = customerService.getCustomers();

		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);

		return "list-customers";
	}
	
	@GetMapping(path = "/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//create the model attribute to bind the new data
		Customer theCustomer=new Customer(); 
		
		theModel.addAttribute("customer",theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping(path = "/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer){
		
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping(path="/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,Model theModel) {
		//get customer from database
		Customer theCustomer=customerService.getCustomer(theId);
		//set customer as amodel attribute to pre-populate the form
		theModel.addAttribute("customer",theCustomer);
		//save over to our form
		
		return "customer-form";
	}
	
	@GetMapping(path="/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int theId,Model theModel) {
		
		//delete the customer
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping(path = "/search")
	public String search(@RequestParam("theSearchName") String theSerachName,Model theModel) {
		
		//search customers from the seervice
		List<Customer> theCustomer=customerService.searchCustomer(theSerachName);
		
		//add customer to the model
		theModel.addAttribute("customers",theCustomer);
		
		return "list-customers";
	}
	

}
