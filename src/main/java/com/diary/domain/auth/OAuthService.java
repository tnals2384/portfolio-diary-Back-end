package com.diary.domain.auth;

import com.diary.domain.member.model.Member;
import com.diary.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class OAuthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService(); //DefaultOAuth2User 서비스를 통해 User 정보를 가져와야 하기 때문에 대리자 생성
        OAuth2User oAuth2User = delegate.loadUser(userRequest); // OAuth 서비스(kakao, google, naver)에서 가져온 유저 정보를 담고있음

        String registrationId = userRequest.getClientRegistration()
                .getRegistrationId(); // OAuth 서비스 이름(ex. kakao, naver, google)
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName(); //OAuth2 로그인 진행시 키가 되는 필드값 (pk)
        Map<String, Object> attributes = oAuth2User.getAttributes(); // OAuth 서비스의 유저 정보들

        Member member = OAuthAttributes.extract(registrationId, attributes); // registrationId에 따라 유저 정보를 통해 공통된 Member 객체로 만들어 줌
        member.updateProvider(registrationId);
        member = saveOrUpdate(member);

        httpSession.setAttribute("user", member.getId());

        Map<String, Object> customAttribute = customAttribute(attributes, userNameAttributeName, member, registrationId);

        // 로그인 유저 리턴
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(member.getRoleValue())),
                customAttribute,
                userNameAttributeName);
    }
    private Map customAttribute(Map attributes, String userNameAttributeName, Member member, String registrationId) {
        Map<String, Object> customAttribute = new LinkedHashMap<>();
        customAttribute.put(userNameAttributeName, attributes.get(userNameAttributeName));
        customAttribute.put("provider", registrationId);
        customAttribute.put("nickname", member.getNickname());
        customAttribute.put("email", member.getEmail());
        customAttribute.put("picture", member.getProfileImageUrl());
        return customAttribute;

    }

    private Member saveOrUpdate(Member member) {
        Member newMember = memberRepository.findByEmailAndProvider(member.getEmail(), member.getProvider())
                .map(m -> m.updateNicknameAndEmailAndProfileImg(member.getNickname(), member.getEmail(), member.getProfileImageUrl())) // OAuth 서비스 사이트에서의 유저 정보 변경사항 update
                .orElse(Member.of(member.getNickname(), member.getEmail(), member.getProfileImageUrl(),
                        member.getProvider(), member.getProviderId()));

        return memberRepository.save(newMember);
    }
}