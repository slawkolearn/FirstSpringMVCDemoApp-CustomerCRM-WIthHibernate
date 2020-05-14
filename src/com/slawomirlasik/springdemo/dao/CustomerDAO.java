package com.slawomirlasik.springdemo.dao;

import java.util.List;

import com.slawomirlasik.springdemo.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);
}
