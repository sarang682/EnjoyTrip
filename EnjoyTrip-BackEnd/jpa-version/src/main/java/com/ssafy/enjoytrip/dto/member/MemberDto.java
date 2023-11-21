package com.ssafy.enjoytrip.dto.member;

import com.ssafy.enjoytrip.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberDto {
    String id;
    String password;
    String name;
    String emailId;
    String emailDomain;

    public static MemberDto fromEntity (Member member) {
        return new MemberDto(
                member.getId(),
                member.getPassword(),
                member.getName(),
                member.getEmailId(),
                member.getEmailDomain()
        );
    }
}
