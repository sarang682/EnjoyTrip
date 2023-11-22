package com.ssafy.enjoytrip.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class JoinRequest {
    private String memberId;
    private String name;
    private String password;
    private String emailId;
    private String emailDomain;
}
