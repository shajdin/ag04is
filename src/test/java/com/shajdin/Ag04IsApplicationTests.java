package com.shajdin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.shajdin.model.Sequence;
import com.shajdin.model.User;
import com.shajdin.repository.SequenceRepository;
import com.shajdin.service.SequenceService;
import com.shajdin.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Ag04IsApplication.class)
@WebAppConfiguration
public class Ag04IsApplicationTests {

//	@Autowired
//	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Autowired
	private SequenceRepository sequenceRepository;
	
	@Test
	public void testUserRepository() {
		User u = new User("user", "pass");
		userService.createUser(u);
		assertEquals(u, userService.getUserByUsername("user"));
		assertEquals(true, userService.usernameExists("user"));
	}
	
	@Test
	public void testSequenceRepository(){
		User u = new User("user2", "pass");
		userService.createUser(u);
		Sequence s1 = new Sequence(new Date(), "bzvz", u);
		Long num1 = sequenceService.findNextSequenceNumber();
		assertEquals(0L,num1.longValue());
		s1.setId(num1);
		sequenceRepository.save(s1);
		Sequence s2 = new Sequence(new Date(), "bzvz2", u);
		Long num2 = sequenceService.findNextSequenceNumber();
		assertEquals(1L,num2.longValue());
		s2.setId(num2);
		sequenceRepository.save(s2);
		
		List<Sequence> list = sequenceService.findSequencesByUserId(u.getId());
		assertNotNull(list);
		assertEquals(2, list.size());
		
	}

}
