package com.practice.jpainsert.service;

import com.google.api.client.util.Value;
import com.practice.jpainsert.persist.MemberRepository;
import com.practice.jpainsert.persist.entity.Member;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Setter
public class MemberService {

	private final MemberRepository memberRepository;

	public Member register(Member member) {
		return memberRepository.save(member);
	}
}
