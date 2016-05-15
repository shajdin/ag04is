package com.shajdin.repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import com.shajdin.dto.SequenceDTO;

@Repository
public class SequenceRepositoryImpl implements SequenceRepositoryCustom {

	private final String maxSeqNumSql = "select max(s.id) from Sequence s";
	private final String findSequencesSql = "select s.id as id, u.username as user, s.purpose as purpose, s.created as date from Sequence s, User u where s.USER_ID=u.id";
	private final String findSequenceByIdSql = "select s.id as id, u.username as user, s.purpose as purpose, s.created as date from Sequence s, User u where s.USER_ID=u.id and s.id = ?";

	@Autowired
	private JdbcOperations jdbcOperations;

	public Long findMaxSequenceNumber() {
		return jdbcOperations.queryForObject(maxSeqNumSql, Long.class);
	}

	@Override
	public List<SequenceDTO> findSequences() {
		return jdbcOperations.query(findSequencesSql, (rs, rowNum) -> {
			SequenceDTO dto = new SequenceDTO();
			dto.setId(rs.getLong("id"));
			dto.setPurpose(rs.getString("purpose"));
			dto.setUser(rs.getString("user"));
			Timestamp t = rs.getTimestamp("date");
			dto.setDate(new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss").format(t));
			return dto;
		});
	}

	@Override
	public SequenceDTO findSequenceById(long id) {
		try {
			return jdbcOperations.queryForObject(findSequenceByIdSql, (rs, rowNum) -> {
				SequenceDTO dto = new SequenceDTO();
				dto.setId(rs.getLong("id"));
				dto.setPurpose(rs.getString("purpose"));
				dto.setUser(rs.getString("user"));
				Timestamp t = rs.getTimestamp("date");
				dto.setDate(new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss").format(t));
				return dto;
			}, id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
