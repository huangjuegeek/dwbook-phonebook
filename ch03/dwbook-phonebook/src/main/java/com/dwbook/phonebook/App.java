package com.dwbook.phonebook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
	}

	public static void main(String[] args) throws Exception {
		new App().run(args);
	}
}
