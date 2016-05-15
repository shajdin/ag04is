package com.shajdin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
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
	
	private static final int defaultPage = 0;
	private static final int defaultPageSize = 10;
		
	@RequestMapping("/")
	public String hello(){
		return "redirect:/home";
	}
	
	@RequestMapping("/home")
	public ModelAndView home(@RequestParam(name="search", required=false) String search,
			@RequestParam(name="page", required=false, defaultValue="0") int pageNum, 
			@RequestParam(name="pageSize", required=false, defaultValue="10") int pageSize){
		ModelAndView model = new ModelAndView("home");
		PageRequest pageReq = new PageRequest(pageNum, pageSize, Direction.ASC, "id"); 
		Page<Sequence> page = sequenceService.findSequences(pageReq, search);
		model.addObject("page", page);
		if(search == null){
			model.addObject("search", "");
		}
		else{
			model.addObject("search", "&search=" + search);
		}
		return model;
	}
	
	private ModelAndView home(){
		return home(null, defaultPage, defaultPageSize);
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
		SequenceDTO sequence = sequenceService.findSequenceDTOById(id);
		model.addObject("sequence", sequence);
		return model;
	}
	
	
	@RequestMapping(value="/sequences/{id}", produces = "application/json")
	@ResponseBody
	public Sequence getSequence(@PathVariable long id){
		return sequenceService.findSequenceById(id);
	}
	
	@RequestMapping(value="/sequences", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ModelAndView createSequence(@ModelAttribute SequenceDTO sequence){
		Map<String, Object> map = sequenceService.addNewSequence(sequence);
		if((boolean) map.get("success")){
			return home();
		}
		ModelAndView model = new ModelAndView("claimSequence");
		model.addAllObjects(map);
		return model;
	}
	
	@RequestMapping(value="/sequence/claim", method=RequestMethod.GET)
	public ModelAndView claimSequence(){
		ModelAndView model = new ModelAndView("claimSequence");
		SequenceDTO sequenceDTO = sequenceService.createNew();
		model.addObject("sequence", sequenceDTO);
		return model;
	}
	
	@RequestMapping(value="/nextSequenceNumber")
	@ResponseBody
	public Long findNextSequenceNumber(){
		return sequenceService.findNextSequenceNumber();
	}
	
	
//	@RequestMapping(value="/createSequence", method=RequestMethod.GET)
//	@ResponseBody
//	@ResponseStatus(HttpStatus.CREATED)
//	public void createSequence(){
//		Sequence sequence = new Sequence();
//		sequence.setPurpose("purpose");
//		sequence.setId(8L);
//		sequenceService.addNewSequence(sequence);
//	}
	
//	@RequestMapping(value="/findSequences", method=RequestMethod.GET)
//	@ResponseBody
//	public List<SequenceDTO> findSequences(){
//		return sequenceService.findSequences();
//	}
	

}
