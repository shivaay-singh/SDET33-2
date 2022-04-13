package com.crm.genericutility;

	import java.lang.reflect.Constructor;
	import java.lang.reflect.Method;

	import org.testng.IAnnotationTransformer;
	import org.testng.annotations.ITestAnnotation;
	public class RetryListenerImplementation implements IAnnotationTransformer {


		public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
	annotation.setRetryAnalyzer(com.crm.genericutility.RetryAnalyzerImplementation.class);		
		}

		
		
		

	

}
