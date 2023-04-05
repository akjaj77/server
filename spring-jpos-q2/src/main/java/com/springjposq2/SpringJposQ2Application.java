package com.springjposq2;

import org.jpos.q2.Q2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJposQ2Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringJposQ2Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Q2 q2 = new Q2();
		Thread thread = new Thread(q2);
		thread.start();
	}

}
