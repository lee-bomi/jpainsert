package com.practice.jpainsert.service;

import com.practice.jpainsert.persist.entity.Team;
import com.practice.jpainsert.persist.entity.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeamService {

	private final TeamRepository teamRepository;

	public Team registerTeam(Team team) {
		return teamRepository.save(team);
	}

	public Team getTeam(Long id) {
		return teamRepository.findById(id).get();
	}
}
