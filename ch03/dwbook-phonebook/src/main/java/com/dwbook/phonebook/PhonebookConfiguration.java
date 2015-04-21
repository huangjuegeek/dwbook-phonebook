package com.dwbook.phonebook;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import javax.validation.constraints.Max;
import org.hibernate.validator.constraints.NotEmpty;

public class PhonebookConfiguration extends Configuration {
	@JsonProperty
	@NotEmpty
	private String message;

	@JsonProperty
	private String additionalMessage = "This is optional";

	@JsonProperty
	@Max(10)
	private int messageRepetitions;

	public String getMessage() {
		return message;
	}

	public String getAdditionalMessage() {
		return additionalMessage;
	}

	public int getMessageRepetitions() {
		return messageRepetitions;
	}
}