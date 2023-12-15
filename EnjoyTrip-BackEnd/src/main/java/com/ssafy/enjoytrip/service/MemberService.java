package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.common.exception.JwtUnauthorizedException;
import com.ssafy.enjoytrip.common.exception.MemberException;
import com.ssafy.enjoytrip.common.response.ExceptionStatus;
import com.ssafy.enjoytrip.config.auth.PrincipalDetails;
import com.ssafy.enjoytrip.config.jwt.JwtUtil;
import com.ssafy.enjoytrip.domain.Member;
import com.ssafy.enjoytrip.domain.Role;
import com.ssafy.enjoytrip.dto.member.JoinRequest;
import com.ssafy.enjoytrip.dto.member.MemberDto;
import com.ssafy.enjoytrip.repository.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access-token.expiretime}")
    private Long expiredTimeMs;

    public PrincipalDetails loadUserByUserName(String username) {
        return memberRepository.findById(username).map(PrincipalDetails::fromEntity)
                .orElseThrow(()->new JwtUnauthorizedException(ExceptionStatus.UNAUTHORIZED));
    }

    @Transactional
    public void join(JoinRequest request) {
        memberRepository.findById(request.getMemberId()).ifPresent(it -> {
            throw new MemberException(ExceptionStatus.DUPLICATE_ID);
        });
        memberRepository.save(
               new Member(request.getMemberId(),
                       bCryptPasswordEncoder.encode(request.getPassword()),
                       request.getName(),
                       request.getEmailId(),
                       request.getEmailDomain(),
                       Role.USER));
    }

    public String login(String memberId, String password) {
        // 회원가입 여부 체크
        Member member=memberRepository.findById(memberId).orElseThrow(()-> new MemberException(ExceptionStatus.MEMBER_NOT_FOUND));

        // 비밀번호 체크
        if(!bCryptPasswordEncoder.matches(password, member.getPassword())){
            throw new MemberException(ExceptionStatus.PASSWORD_NO_MATCH);
        }
        // 토큰생성
        return JwtUtil.generateToken(memberId, secret, expiredTimeMs);
    }

    public MemberDto getMember(String memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(ExceptionStatus.MEMBER_NOT_FOUND));
        return MemberDto.fromEntity(member);
    }

    public Member userInfo(String memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(()->new MemberException(ExceptionStatus.MEMBER_NOT_FOUND));
    }

    @Transactional
    public int modify(String memberId, String name, String password, String emailId, String emailDomain) {
        // 수정할 회원이 존재하는지 검사
        Member member= memberRepository.findById(memberId)
                .orElseThrow(()->new MemberException(ExceptionStatus.MEMBER_NOT_FOUND));
        return memberRepository.updateMember(memberId,name,password,emailId,emailDomain);
    }

    @Transactional
    public void delete(String memberId) {
        // 삭제할 회원이 존재하는지 검사
        Member member= memberRepository.findById(memberId)
                .orElseThrow(()->new MemberException(ExceptionStatus.MEMBER_NOT_FOUND));
        memberRepository.delete(member);
    }
}
