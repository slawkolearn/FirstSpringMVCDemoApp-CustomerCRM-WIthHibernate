package com.slawomirlasik.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.slawomirlasik.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {

		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query<Customer> theQuery = session.createQuery("FROM Customer ORDER BY lastName", Customer.class);

		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();

		// return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save the customer.. finally lol :)
		// both saves if no cusomer already in database by the given id
		// .. or updates if customer already exists
		currentSession.saveOrUpdate(theCustomer);

	}

	@Override
	public Customer getCustomer(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using the primary key
		Customer customer = currentSession.get(Customer.class, theId);

		return customer;
	}

	@Override
	public void deleteCustomer(int theId) {

		// get current session from session factory
		Session currentSession = sessionFactory.getCurrentSession();

		// delete the customer
		Query theQuery = currentSession.createQuery("DELETE from Customer where id=:customerId");

		theQuery.setParameter("customerId", theId);

		theQuery.executeUpdate();
	}

	@Override
	public List<Customer> getCustomers(String theSearchName) {

		// get the current session
		Session currentSession = sessionFactory.getCurrentSession();

		// get customers that have the name like theSearchName (last name, or first
		// name)
		// if theSearchName is empty or has zero length return all customers
		Query theQuery = currentSession.createQuery(
				(theSearchName != null && theSearchName.trim().length() > 0
				? "from Customer  "
						+ "where lower(lastName) LIKE :theName "
						+ "OR lower(firstName) LIKE :theName"
				: "from Customer"),
				Customer.class);
		theQuery.setParameter("theName", String.format("%%%s%%", theSearchName));
		List<Customer> theCustomers = theQuery.getResultList();

		return theCustomers;
	}

}
