package com.shajdin.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.shajdin.dto.SequenceDTO;
import com.shajdin.model.Sequence;
import com.shajdin.model.User;

public interface SequenceService {
	
	public List<Sequence> getAllSequence();
	
	public Map<String, Object> addNewSequence(SequenceDTO sequence);
	
	public Sequence findSequenceById(Long id);
	
	public Long findNextSequenceNumber();
	
	public List<Sequence> findSequencesByUserId(Long id);
	
	public List<Sequence> findBySequencePurposeContains(String purpose);
	
	public void addUserToSequence(User user, Sequence sequence);
	
	public void saveSequence(Sequence sequence);
	
	public Page<Sequence> findSequences(PageRequest pageReq, String search);
	
	public SequenceDTO findSequenceDTOById(long id);
	
	public SequenceDTO createNew();

}
