package com.shajdin.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.shajdin.dto.SequenceDTO;
import com.shajdin.model.Sequence;
import com.shajdin.model.User;
import com.shajdin.repository.SequenceRepository;
import com.shajdin.security.CurrentUser;

@Transactional
public class SequenceServiceImpl implements SequenceService{
	
	@Autowired
	private SequenceRepository sequenceRepository;

	@Override
	public List<Sequence> getAllSequence() {
		return sequenceRepository.findAll();
	}

	@Override
	public Map<String, Object> addNewSequence(Sequence sequence) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(findNextSequenceNumber() > sequence.getId()){
			map.put("error", "Sequence value has already been taken!");
			sequence.setId(findNextSequenceNumber());
			map.put("sequence", sequence);
			return map;
		}
		User currentUser = CurrentUser.getUser();
		Assert.notNull(currentUser);
		
		sequence.setCreated(new Date());
		sequence.setUser(currentUser);		
		saveSequence(sequence);
		map.put("sequence", sequence);
		map.put("success", true);
		return map;
	}

	@Override
	public Sequence findSequenceById(Long id) {
		return sequenceRepository.findOne(id);
	}

	@Override
	public Long findNextSequenceNumber() {
		Long maxSequenceNumber = sequenceRepository.findMaxSequenceNumber();
		return maxSequenceNumber != null ? ++maxSequenceNumber : 0L;
	}

	@Override
	public List<Sequence> findSequencesByUserId(Long id) {
		return sequenceRepository.findByUserId(id);
	}

	@Override
	public List<Sequence> findBySequencePurposeContains(String purpose) {
		return sequenceRepository.findByPurposeContains(purpose);
	}

	@Override
	public void addUserToSequence(User user, Sequence sequence) {
		sequence.setUser(user);
		sequenceRepository.save(sequence);
	}

	@Override
	public void saveSequence(Sequence sequence) {
		sequenceRepository.save(sequence);		
	}

	@Override
	public List<SequenceDTO> findSequences() {
		return sequenceRepository.findSequences();
	}

}
