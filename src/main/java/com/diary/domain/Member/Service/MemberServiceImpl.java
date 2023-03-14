package com.diary.domain.Member.Service;

import com.diary.domain.Member.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;
}
