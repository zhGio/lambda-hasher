package com.serverless.invoker;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambdaAsync;
import com.amazonaws.services.lambda.AWSLambdaAsyncClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;

public class LambdaAsyncInvoker {

	private static final int MAX_CONCURRENCY = 900; // programmatic function `max concurrency` setting

	private static final Logger logger = LogManager.getLogger(LambdaAsyncInvoker.class);
	private static final String FUNCTION_NAME = "lambda-function-name"; // needs to match the serverless.yml value

	public void invoke(List<String> rawStrings) {
		ClientConfiguration config = new ClientConfiguration();
		config.setMaxConnections(MAX_CONCURRENCY);
		AWSLambdaAsync lambda = AWSLambdaAsyncClientBuilder.standard().withRegion(Regions.EU_CENTRAL_1).withClientConfiguration(config).build();
		logger.info("started with " + rawStrings.size() + " of raw rawStrings at " + new Date());

		List<InvokeRequest> invokeRequestsList = rawStrings.stream().map(raw -> new InvokeRequest().withFunctionName(FUNCTION_NAME).withPayload("\"" + raw + "\"")).collect(Collectors.toList());
		LambdaAsyncHandler asyncLambdaHandler = new LambdaAsyncHandler();
		invokeRequestsList.forEach(request -> lambda.invokeAsync(request, asyncLambdaHandler));
	}
}
