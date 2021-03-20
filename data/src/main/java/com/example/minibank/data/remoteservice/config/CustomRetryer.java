package com.example.minibank.data.remoteservice.config;

import feign.RetryableException;
import feign.Retryer;
import org.springframework.stereotype.Component;

@Component
public class CustomRetryer implements Retryer {

	private int retryMaxAttempt;

	private long retryInterval;

	private int attempt = 1;

	public CustomRetryer(int retryMaxAttempt, Long retryInterval) {
		this.retryMaxAttempt = retryMaxAttempt;
		this.retryInterval = retryInterval;
	}

	@Override
	public void continueOrPropagate(RetryableException e) {

		if (attempt++ == retryMaxAttempt) {
			throw e;
		}

		try {
			Thread.sleep(retryInterval);

		} catch (InterruptedException ignored) {

			Thread.currentThread().interrupt();
		}

	}

	@Override
	public Retryer clone() {
		return new CustomRetryer(1, 1000L);
	}

	public CustomRetryer() {
		super();
	}

}