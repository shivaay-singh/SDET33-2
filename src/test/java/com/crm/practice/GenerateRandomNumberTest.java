package com.crm.practice;

import java.util.Random;

public class GenerateRandomNumberTest {

	public static void main(String[] args) {
    // Generate Random number
		//step 1: create object for Random class
		Random ran=new Random();
		//Step 2: generate the random nubmer by calling nextInt() method
		int randomNumber = ran.nextInt(1000);
		System.out.println("Test_"+randomNumber);
	}

}
