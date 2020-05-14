package com.slawomirlasik.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slawomirlasik.springdemo.dao.CustomerDAO;
import com.slawomirlasik.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	//inject customer dao
	@Autowired
	private CustomerDAO customerDAO;

	@Override
	@Transactional
	public List<Customer> getCustomers() {
		// in this example we just simply delegate the call to customerDAO
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {

		customerDAO.saveCustomer(theCustomer);
	}

}
