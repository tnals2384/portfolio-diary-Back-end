package com.diary.domain.member.Service;

import com.diary.domain.member.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;
}
