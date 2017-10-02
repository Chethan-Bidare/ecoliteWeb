package com.Ecolite_Web.Listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import com.Ecolite_Web.TestBase.TestBase;

public class RetryListeners extends TestBase implements IAnnotationTransformer{

	public void transform(ITestAnnotation arg0, Class arg1, Constructor arg2, Method arg3) {
		
		IRetryAnalyzer retry = arg0.getRetryAnalyzer();
		
		if(retry == null){
			arg0.setRetryAnalyzer(Retry.class);
			
		}
		
	}

}
