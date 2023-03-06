package com.practice.jpainsert.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.api.client.util.Value;
import com.practice.jpainsert.model.MemberInput;
import com.practice.jpainsert.model.TeamInput;
import com.practice.jpainsert.persist.MemberRepository;
import com.practice.jpainsert.persist.entity.Member;
import com.practice.jpainsert.persist.entity.Team;
import com.practice.jpainsert.service.MemberService;
import com.practice.jpainsert.service.TeamService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {


	private final MemberService memberService;
	private final TeamService teamService;
	private final MemberRepository memberRepository;
	@Value("${spring.test.name}")
	String name;

	@GetMapping("/bomi")
	public ResponseEntity<?> test() {
		System.out.println(">>>>>>>>>> 이곳은 main브랜치2 <<<<<<<<<<<<<<<<<");
		System.out.println("test controller에 진입 > " + name);
		return ResponseEntity.ok().build();
	}


	@PostMapping("/teamRegister")
	public ResponseEntity<?> teamRegist(@RequestBody TeamInput input) {

		System.out.println(input.getColor());
		System.out.println(input.getTeamName());

		Team team = teamService.registerTeam(Team.builder()
			.teamName(input.getTeamName())
			.color(input.getColor())
			.build());

		return ResponseEntity.ok().body(team);
	}

	@PostMapping("/memberRegister")
	public ResponseEntity<?> memberRegister(@RequestBody MemberInput input) {

		Team team = teamService.getTeam(2L);

		Member member = memberService.register(Member.builder()
			.name(input.getMemberName())
			.gender(input.getGender())
			.age(input.getAge())
			.team(team)
			.build());

		String teamName = member.getTeam().getTeamName();
		System.out.println(teamName);

		return ResponseEntity.ok().body(member);
	}

	@GetMapping("/member")
	public ResponseEntity<?> findMember() {

		List<Member> members = memberRepository.findAll();

		System.out.println("============== " + members.get(0).getTeam().getTeamName());
		System.out.println("============== " + members.get(1).getTeam().getTeamName());

		return ResponseEntity.ok().body(members);
	}
}
