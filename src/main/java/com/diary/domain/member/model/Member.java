package com.diary.domain.member.model;

import com.diary.common.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "profile_img_url")
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Column(name = "provider_type")
    private String provider;

    @Column(name = "provider_id")
    private String providerId;

    @Builder(access = PRIVATE)
    private Member(String nickname, String email, String profileImageUrl, String provider, String providerId){
        this.nickname = nickname;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.role = MemberRole.USER;
        this.provider = provider;
        this.providerId = providerId;
    }
    public static Member of(String nickname, String email, String profileImageUrl, String provider, String providerId){
        return Member.builder()
                .nickname(nickname)
                .email(email)
                .profileImageUrl(profileImageUrl)
                .provider(provider)
                .providerId(providerId)
                .build();
    }
    public Member updateNicknameAndEmailAndProfileImg(String nickname, String email, String profileImageUrl){
        this.nickname = nickname;
        this.email = email;
        this.profileImageUrl = profileImageUrl;

        return this;
    }

    public Member updateProvider(String provider){
        this.provider = provider;

        return this;
    }
}
