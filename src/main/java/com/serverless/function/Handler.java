package com.serverless.function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Handler implements RequestHandler<String, String> {

	private static final Logger LOG = LogManager.getLogger(Handler.class);

	public String getSaltedHashedPassword(String password) {
		String salt = BCrypt.gensalt(12);
		return BCrypt.hashpw(password, salt);
	}

	@Override
	public String handleRequest(String input, Context context) {
		return getSaltedHashedPassword(input);
	}
}
