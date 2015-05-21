package com.dwbook.phonebook;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dwbook.phonebook.resources.ContactResource;

public class App extends Application<PhonebookConfiguration> {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	@Override
	public void initialize(Bootstrap<PhonebookConfiguration> b) {
	}

	@Override
	public void run(PhonebookConfiguration c, Environment e) throws Exception {
		LOGGER.info("Method App#run() called");
		for (int i = 0; i < c.getMessageRepetitions(); i++) {
			String s = c.getMessage();
			System.out.println(s);
		}
		System.out.println(c.getAdditionalMessage());
		
		// Create a DBI factory and build a JDBI instance
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(e, c.getDataSourceFactory(), "mysql");
		
		// Add the resource to the environment
		e.jersey().register(new ContactResource(jdbi, e.getValidator()));
	}

	public static void main(String[] args) throws Exception {
		new App().run(args);
	}
}
