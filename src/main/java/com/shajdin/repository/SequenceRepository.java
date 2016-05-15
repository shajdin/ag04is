package com.shajdin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shajdin.model.Sequence;

public interface SequenceRepository extends JpaRepository<Sequence, Long>, SequenceRepositoryCustom {
	public List<Sequence> findByUserId(Long id);
	
	public List<Sequence> findByPurposeContains(String purpose);
	
	public Page<Sequence> findByPurposeContainsIgnoreCaseOrUserUsernameContainsIgnoreCase(String purpose, String username, Pageable pageRequest);
	public Page<Sequence> findByIdOrPurposeContainsIgnoreCaseOrUserUsernameContainsIgnoreCase(Long id, String purpose, String username, Pageable pageRequest);

}
