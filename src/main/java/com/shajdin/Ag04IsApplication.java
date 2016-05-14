package com.shajdin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.shajdin.model.Sequence;
import com.shajdin.model.User;
import com.shajdin.service.SequenceService;
import com.shajdin.service.UserService;

@SpringBootApplication
public class Ag04IsApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Ag04IsApplication.class, args);
	}
	
	@Autowired
	private SequenceService sequenceService;
	
	@Autowired
	private UserService userService;
	
	@Override
    public void run(String... strings) throws Exception {
		User u1 = new User("a", "b");
		User u2 = new User("a2", "b2");
		
		User ivo = new User("ivo", "pivo");
		
		userService.createUser(u1);
		userService.createUser(u2);
		userService.createUser(ivo);

		
		Sequence s1 = new Sequence(new Date(), "tekst1", u1);
		Sequence s2 = new Sequence(new Date(), "tekst2", u1);
		Sequence s3 = new Sequence(new Date(), "tekst3", u1);
		Sequence s4 = new Sequence(new Date(), "tekst4", u1);
		Sequence s5 = new Sequence(new Date(), "tekst5", u2);
		Sequence s6 = new Sequence(new Date(), "tekst6", u2);
		Sequence s7 = new Sequence(new Date(), "tekst7", u2);
		
		s1.setId(1L);
		sequenceService.saveSequence(s1);
		s2.setId(2L);
		sequenceService.saveSequence(s2);
		s3.setId(sequenceService.findNextSequenceNumber());
		s4.setId(4L);
		s5.setId(5L);
		s6.setId(6L);
		s7.setId(7L);
		
		
		
		sequenceService.saveSequence(s3);
		sequenceService.saveSequence(s4);
		sequenceService.saveSequence(s5);
		sequenceService.saveSequence(s6);
		sequenceService.saveSequence(s7);
		
	}
}
