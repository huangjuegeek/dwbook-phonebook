package com.dwbook.phonebook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dwbook.phonebook.health.NewContactHealthCheck;
import com.dwbook.phonebook.resources.ContactResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.jdbi.DBIFactory;

import org.skife.jdbi.v2.DBI;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.dwbook.phonebook.resources.ClientResource;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheBuilderSpec;

import io.dropwizard.auth.CachingAuthenticator;
import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.auth.basic.BasicCredentials;

public class App extends Application<PhonebookConfiguration> {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws Exception {
		new App().run(args);
	}

	@Override
	public void initialize(Bootstrap<PhonebookConfiguration> b) {

	}

	@Override
	public void run(PhonebookConfiguration c, Environment e) throws Exception {
		LOGGER.info("Method App#run() called");
		for (int i = 0; i < c.getMessageRepetitions(); i++) {
			System.out.println(c.getMessage());
		}
		System.out.println(c.getAdditionalMessage());

		// Create a DBI factory and build a JDBI instance
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(e, c.getDataSourceFactory(), "mysql");

		// build the client and add the resource to the environment
		final Client client = new JerseyClientBuilder(e).build("REST Client");
		client.addFilter(new HTTPBasicAuthFilter("wsuser", "wspassword"));

		// Authenticator, with caching support (CachingAuthenticator)
		CachingAuthenticator<BasicCredentials, Boolean> authenticator = new CachingAuthenticator<BasicCredentials, Boolean>(
				e.metrics(), new PhonebookAuthenticator(jdbi),
				CacheBuilderSpec
						.parse("maximumSize=10000, expireAfterAccess=10m"));

		// Add the resource to the environment
		e.jersey().register(new ContactResource(jdbi, e.getValidator()));
		e.jersey().register(new ClientResource(client));
		e.jersey().register(
				new BasicAuthProvider<Boolean>(authenticator,
						"Web Service Realm"));

		// Add health checks
		e.healthChecks().register("New Contact health check",
				new NewContactHealthCheck(client));
	}
}
