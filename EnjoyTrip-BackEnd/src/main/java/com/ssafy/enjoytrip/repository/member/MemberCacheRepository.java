package com.ssafy.enjoytrip.repository.member;

import com.ssafy.enjoytrip.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberCacheRepository {

    private final RedisTemplate<String, PrincipalDetails> redisTemplate;
    private final static Duration MEMBER_CACHE_TTL = Duration.ofDays(3);
    public void setMember(PrincipalDetails member) {
        redisTemplate.opsForValue().set(getKey(member.getId()),member,MEMBER_CACHE_TTL);
    }

    public Optional<PrincipalDetails> getMember(String username) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(getKey(username)));
    }

    private String getKey(String username) {
        return "USER:"+username;
    }
}
