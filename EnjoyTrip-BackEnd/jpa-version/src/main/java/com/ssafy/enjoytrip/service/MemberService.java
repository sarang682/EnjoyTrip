package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.Util.JWTUtil;
import com.ssafy.enjoytrip.common.exception.BadRequestException;
import com.ssafy.enjoytrip.common.exception.MemberException;
import com.ssafy.enjoytrip.common.exception.UnAuthorizedException;
import com.ssafy.enjoytrip.common.response.ExceptionStatus;
import com.ssafy.enjoytrip.domain.Member;
import com.ssafy.enjoytrip.dto.member.JoinRequest;
import com.ssafy.enjoytrip.dto.member.LoginRequest;
import com.ssafy.enjoytrip.dto.member.MemberDto;
import com.ssafy.enjoytrip.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JWTUtil jwtUtil;

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

    public MemberDto getMember(String token) {
        if(!jwtUtil.checkToken(token)) throw new MemberException(ExceptionStatus.INVALID_TOKEN);
        String memberId=jwtUtil.getUserId(token);
        if(memberId==null) throw new MemberException(ExceptionStatus.INVALID_TOKEN);
        Member member= memberRepository.findById(memberId)
                .orElseThrow(()->new MemberException(ExceptionStatus.MEMBER_NOT_FOUND));
        return MemberDto.fromEntity(member);
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
