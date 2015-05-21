package com.dwbook.phonebook;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PhonebookConfiguration extends Configuration {
	@JsonProperty
	@NotEmpty
	private String message;

	@JsonProperty
	private String additionalMessage = "This is optional";

	@JsonProperty
	@Max(10)
	private int messageRepetitions;
	
	@JsonProperty
	private DataSourceFactory database = new DataSourceFactory();

	public String getMessage() {
		return message;
	}

	public String getAdditionalMessage() {
		return additionalMessage;
	}

	public int getMessageRepetitions() {
		return messageRepetitions;
	}

	public DataSourceFactory getDataSourceFactory() {
		return database;
	}
	
}