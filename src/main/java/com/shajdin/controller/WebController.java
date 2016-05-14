package com.shajdin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.http.HttpStatus;

import com.shajdin.dto.SequenceDTO;
import com.shajdin.model.Sequence;
import com.shajdin.model.User;
import com.shajdin.repository.UserRepository;
import com.shajdin.service.SequenceService;
import com.shajdin.service.UserService;

@Controller
public class WebController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SequenceService sequenceService;
		
	@RequestMapping("/")
	public String helllo(){
		return "redirect:/home";
	}
	
	@RequestMapping("/home")
	public ModelAndView user(){
		ModelAndView model = new ModelAndView("home");
		List<SequenceDTO> sequences = findSequences();
		model.addObject("sequences", sequences);
		return model;
	}
	
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public void createUser(@RequestBody User user){
		userService.createUser(user);
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.GET)
	@ResponseBody
	public User getUser(@PathVariable long id){
		return userService.getUserById(id);
	}
	
	@RequestMapping(value="/users/{id}/sequences", method=RequestMethod.GET)
	@ResponseBody
	public List<Sequence> getSequencesByUser(@PathVariable long id){
		return sequenceService.findSequencesByUserId(id);
	}
	
	@RequestMapping("/sequences")
	@ResponseBody
	public List<Sequence> getAllSequences(@RequestParam(name="search", required=false) String search){
		return sequenceService.getAllSequence();
	}
	
	@RequestMapping(value="/sequences/{id}", produces = "text/html")
	@ResponseBody
	public ModelAndView getSequencePage(@PathVariable long id){
		ModelAndView model = new ModelAndView("sequence");
		Sequence sequences = getSequence(id);
		model.addObject("sequences", sequences);
		return model;
	}
	
	@RequestMapping(value="/sequences/{id}", produces = "application/json")
	@ResponseBody
	public Sequence getSequence(@PathVariable long id){
		return sequenceService.findSequenceById(id);
	}
	
	@RequestMapping(value="/sequences", method=RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public void createSequence(@RequestBody Sequence sequence){
		sequenceService.addNewSequence(sequence);
	}
	
	@RequestMapping(value="/nextSequenceNumber")
	@ResponseBody
	public Long findNextSequenceNumber(){
		return sequenceService.findNextSequenceNumber();
	}
	
	
	@RequestMapping(value="/createSequence", method=RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public void createSequence(){
		Sequence sequence = new Sequence();
		sequence.setPurpose("purpose");
		sequence.setId(8L);
		sequenceService.addNewSequence(sequence);
	}
	
	@RequestMapping(value="/findSequences", method=RequestMethod.GET)
	@ResponseBody
	public List<SequenceDTO> findSequences(){
		return sequenceService.findSequences();
	}
	

}
