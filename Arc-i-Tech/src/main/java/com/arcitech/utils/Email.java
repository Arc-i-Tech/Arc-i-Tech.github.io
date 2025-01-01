/**
 * Copyright (c) 2025, Arc-i-Tech. All rights reserved.
 * visit www.arc-i-tech.in
 *
 */
package com.arcitech.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ajay G
 * 
 */
public class Email {
	public static final Email DEFAULT_ARC_I_TECH_SENDER = new Builder().withSender("do-not-reply@arc-i-tech.in")
			.build();
	private String sender;
	private Set<String> recipients = new HashSet<>();
	private String subject;
	private String body;

	public static class Builder {
		private Email email = new Email();

		public Builder() {
			this.email = new Email();
		}

		public Builder(Email email) {
			this.email = email;
		}

		public Builder withSender(String sender) {
			this.email.setSender(sender);
			return this;
		}

		public Builder withRecipients(Set<String> recipients) {
			this.email.setRecipients(recipients);
			return this;
		}

		public Builder withRecipient(String recipient) {
			this.email.setRecipient(recipient);
			return this;
		}

		public Builder withSubject(String subject) {
			this.email.setSubject(subject);
			return this;
		}

		public Builder withBody(String body) {
			this.email.setBody(body);
			return this;
		}

		public Email build() {
			return this.email;
		}
	}

	/**
	 * 
	 */
	public Email() {
		super();
	}

	/**
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * @param sender the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * @return the recipient
	 */
	public Set<String> getRecipients() {
		return recipients;
	}

	/**
	 * @param recipient the recipient to set
	 */
	public void setRecipients(Set<String> recipients) {
		this.recipients = recipients;
	}

	/**
	 * @param recipient
	 */
	public void setRecipient(String recipient) {
		if (this.recipients == null) {
			this.recipients = new HashSet<>();
		}
		this.recipients.add(recipient);
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

}
