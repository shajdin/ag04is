package com.shajdin.service;

import java.util.List;

import com.shajdin.dto.SequenceDTO;
import com.shajdin.model.Sequence;
import com.shajdin.model.User;

public interface SequenceService {
	
	public List<Sequence> getAllSequence();
	
	public boolean addNewSequence(Sequence sequence);
	
	public Sequence findSequenceById(Long id);
	
	public Long findNextSequenceNumber();
	
	public List<Sequence> findSequencesByUserId(Long id);
	
	public List<Sequence> findBySequencePurposeContains(String purpose);
	
	public void addUserToSequence(User user, Sequence sequence);
	
	public void saveSequence(Sequence sequence);
	
	public List<SequenceDTO> findSequences();

}
