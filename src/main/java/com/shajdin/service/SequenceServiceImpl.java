package com.shajdin.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AuthorizationServiceException;
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
	public Map<String, Object> addNewSequence(SequenceDTO sequence) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("success", false);
		
		if(findNextSequenceNumber() > sequence.getId()){
			map.put("error", "Sequence value has already been taken!");
			sequence.setId(findNextSequenceNumber());
			map.put("sequence", sequence);
			return map;
		}
		User currentUser = CurrentUser.getUser();
		Assert.notNull(currentUser);
		if(!currentUser.getUsername().equals(sequence.getUser())){
			throw new AuthorizationServiceException("Not authorised");
		}
		
		Sequence newSequence = new Sequence(sequence.getId(), new Date(), sequence.getPurpose(), currentUser);		
		saveSequence(newSequence);
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
	public Page<Sequence> findSequences(PageRequest pageReq, String search) {
		Page<Sequence> page = null;
		if(search == null){
			page =  sequenceRepository.findAll(pageReq);
		}
		else{
			Long id = null;
			try{
				id = Long.valueOf(search);
			}
			catch(NumberFormatException e){}
			
			if(id != null){
				page = sequenceRepository.findByIdOrPurposeContainsIgnoreCaseOrUserUsernameContainsIgnoreCase(id, search, search, pageReq);
			}
			else{
				page = sequenceRepository.findByPurposeContainsIgnoreCaseOrUserUsernameContainsIgnoreCase(search, search, pageReq);
			}
			
		}
		return page;
	}

	@Override
	public SequenceDTO findSequenceDTOById(long id) {
		return sequenceRepository.findSequenceById(id);
	}

	@Override
	public SequenceDTO createNew() {
		SequenceDTO dto = new SequenceDTO();
		dto.setId(findNextSequenceNumber());
		dto.setUser(CurrentUser.getUser().getUsername());
		return dto;
	}

}
