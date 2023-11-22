package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.common.exception.AttractionException;
import com.ssafy.enjoytrip.common.exception.MemberException;
import com.ssafy.enjoytrip.common.exception.PlanException;
import com.ssafy.enjoytrip.common.response.ExceptionStatus;
import com.ssafy.enjoytrip.domain.AttractionInfo;
import com.ssafy.enjoytrip.domain.Member;
import com.ssafy.enjoytrip.domain.Plan;
import com.ssafy.enjoytrip.domain.PlanAttractionInfo;
import com.ssafy.enjoytrip.repository.attraction.InfoRepository;
import com.ssafy.enjoytrip.repository.member.MemberRepository;
import com.ssafy.enjoytrip.repository.plan.PlanAttractionRepository;
import com.ssafy.enjoytrip.repository.plan.PlanRepository;
import com.ssafy.enjoytrip.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;
    private final MemberRepository memberRepository;
    private final PlanAttractionRepository planAttractionRepository;
    private final InfoRepository infoRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public Integer plan(String title, String token) {
        String memberId = jwtUtil.getUserId(token);
        Member member=getMemberOrException(memberId);
        return planRepository.save(new Plan(title,member)).getId();
    }

    @Transactional
    public Integer planAttraction(Integer planId, Integer attractionId) {
        Plan plan=getPlanOrException(planId);
        AttractionInfo attractionInfo=getAttractionOrException(attractionId);
        return planAttractionRepository.save(new PlanAttractionInfo(plan,attractionInfo)).getId();
    }


    // *** 메소드 ***
    private Member getMemberOrException(String memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(()-> new MemberException(ExceptionStatus.MEMBER_NOT_FOUND));
    }

    private Plan getPlanOrException(Integer planId) {
        return planRepository.findById(planId)
                .orElseThrow(()-> new PlanException(ExceptionStatus.PLAN_NOT_FOUND));
    }

    private AttractionInfo getAttractionOrException(Integer attractionId) {
        return infoRepository.findById(attractionId)
                .orElseThrow(()->new AttractionException(ExceptionStatus.ATTRACTION_NOT_FOUND));
    }
}
