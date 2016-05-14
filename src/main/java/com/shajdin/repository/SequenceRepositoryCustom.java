package com.shajdin.repository;

import java.util.List;

import com.shajdin.dto.SequenceDTO;

public interface SequenceRepositoryCustom {

	public Long findMaxSequenceNumber();
	
	public List<SequenceDTO> findSequences();
	
	public SequenceDTO findSequenceById(long id);
}
