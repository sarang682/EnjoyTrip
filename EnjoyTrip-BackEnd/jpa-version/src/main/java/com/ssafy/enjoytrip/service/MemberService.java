package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.util.JwtUtil;
import com.ssafy.enjoytrip.common.exception.MemberException;
import com.ssafy.enjoytrip.common.response.ExceptionStatus;
import com.ssafy.enjoytrip.domain.Member;
import com.ssafy.enjoytrip.dto.member.JoinRequest;
import com.ssafy.enjoytrip.repository.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public void join(JoinRequest request) {
        memberRepository.findById(request.getMemberId()).ifPresent(it -> {
            throw new MemberException(ExceptionStatus.DUPLICATE_ID);
        });
        memberRepository.save(
               new Member(request.getMemberId(),
                       request.getPassword(),
                       request.getName(),
                       request.getEmailId(),
                       request.getEmailDomain()));
    }

    public String login(String memberId, String password) {
        // 회원가입 여부 체크
        Member member=memberRepository.findById(memberId).orElseThrow(()-> new MemberException(ExceptionStatus.MEMBER_NOT_FOUND));

        // 비밀번호 체크
        if(!member.getPassword().equals(password)){
            throw new MemberException(ExceptionStatus.PASSWORD_NO_MATCH);
        }
        // 토큰생성
        return jwtUtil.createAccessToken(member.getId());
    }

    public Member userInfo(HttpServletRequest request, String memberId) {
        // 토큰 가져오기
        String token = jwtUtil.resolveToken(request);
        // 유효성 검사
        jwtUtil.validateToken(token);

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
