package com.slawomirlasik.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.slawomirlasik.springdemo.entity.Customer;
import com.slawomirlasik.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// need to inject our customer service
	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomer(Model theModel) {

		// get customers from the service
		List<Customer> theCustomers = customerService.getCustomers();

		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		System.out.println("Got from database: " + theCustomers);

		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		return "customer-form";
	}

}


















