package com.serverless.invoker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;

public class LambdaAsyncHandler implements AsyncHandler<InvokeRequest, InvokeResult> {

	private static final Logger logger = LogManager.getLogger(LambdaAsyncHandler.class);

	@Override
	public void onError(Exception exception) {
		logger.error("An error happened on lambda. " + exception);
	}

	@Override
	public void onSuccess(InvokeRequest request, InvokeResult invokeResult) {
		logger.info("original: " + new String(request.getPayload().array()) + " | hash: " + new String(invokeResult.getPayload().array()));
	}
}
