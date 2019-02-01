package com.serverless.runner;

import java.util.Arrays;
import java.util.List;

import com.serverless.invoker.LambdaAsyncInvoker;

public class Runner {
	public static void main(String[] args) {
		LambdaAsyncInvoker invoker = new LambdaAsyncInvoker();
		List<String> someStrings = Arrays.asList("String1", "string2", "stRiNg3");
		invoker.invoke(someStrings);
	}

}
