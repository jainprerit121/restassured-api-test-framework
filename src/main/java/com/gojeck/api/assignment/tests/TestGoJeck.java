package com.gojeck.api.assignment.tests;

public class TestGoJeck {

	
	public static void main(String[] args) {
		for(int i =1; i<=100; i++){
			
			if(i%3==0 && i%5==0){
				System.out.println("Phase & Buzz");
			}else if(i%3==0){
				System.out.println("Phase");
			}else if(i%5==0){
				System.out.println("Buzz");
			}else{
				System.out.println(i);
			}
		}
	}
}
