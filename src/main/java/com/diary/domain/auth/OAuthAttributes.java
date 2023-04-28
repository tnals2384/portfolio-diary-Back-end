package com.diary.domain.auth;

import com.diary.domain.member.model.Member;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public enum OAuthAttributes {

    GOOGLE("google", (attributes) -> {
        return Member.of(
                (String) attributes.get("name"),
                (String) attributes.get("email"),
                (String) attributes.get("picture"),
                "google",
                "google_"+ attributes.get("sub")
        );
    });

    private final String registrationId;
    private final Function<Map<String, Object>, Member> of;

    OAuthAttributes(String registrationId, Function<Map<String, Object>, Member> of) {
        this.registrationId = registrationId;
        this.of = of;
    }

    // provider가 일치하는 경우에만 apply를 호출하여 (google,kakao) member를 반환
    public static Member extract(String registrationId, Map<String, Object> attributes) {
        return Arrays.stream(values())
                .filter(provider -> registrationId.equals(provider.registrationId))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .of.apply(attributes);
    }
}