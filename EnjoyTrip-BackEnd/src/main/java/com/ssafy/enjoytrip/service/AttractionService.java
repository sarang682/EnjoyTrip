package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.common.exception.AttractionException;
import com.ssafy.enjoytrip.common.exception.JwtBadRequestException;
import com.ssafy.enjoytrip.common.exception.MemberException;
import com.ssafy.enjoytrip.common.response.ExceptionStatus;
import com.ssafy.enjoytrip.domain.*;
import com.ssafy.enjoytrip.dto.attraction.*;
import com.ssafy.enjoytrip.repository.attraction.*;
import com.ssafy.enjoytrip.repository.bookmark.BookmarkRepository;
import com.ssafy.enjoytrip.repository.member.MemberRepository;
import com.ssafy.enjoytrip.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ssafy.enjoytrip.common.response.ExceptionStatus.*;

@Service
@RequiredArgsConstructor
public class AttractionService {

    private final SidoRepository sidoRepository;
    private final GugunRepository gugunRepository;
    private final TypeRepository typeRepository;
    private final InfoRepository infoRepository;
    private final DescriptionRepository descriptionRepository;
    private final MemberRepository memberRepository;
    private final BookmarkRepository bookmarkRepository;
    private final JwtUtil jwtUtil;

    public List<GetSidoResponse> getSidoList() {
        List<Sido> sidos = sidoRepository.findAll();

        List<GetSidoResponse> result = new ArrayList<>();
        for (Sido sido: sidos) {
            result.add(new GetSidoResponse(sido));
        }

        return result;
    }

    public List<GetGugunResponse> getGugunList(int sidoCode) {
        // 시도 코드 유효성 검사
        validateSido(sidoCode);

        List<Gugun> guguns = gugunRepository.findAllBySidoCode(sidoCode);

        List<GetGugunResponse> result = new ArrayList<>();
        for (Gugun gugun: guguns) {
            result.add(new GetGugunResponse(gugun));
        }

        return result;
    }

    public List<GetTypeResponse> getTypeList() {
        List<AttractionType> types = typeRepository.findAll();

        List<GetTypeResponse> result = new ArrayList<>();
        for (AttractionType type: types) {
            result.add(new GetTypeResponse(type));
        }

        return result;
    }

    public List<GetInfoResponse> getInfoList(Integer sidoCode, Integer gugunCode, Integer attractionTypeId) {
        // 시도코드 유효성 검사
        if (sidoCode != null) {
            validateSido(sidoCode);
        }

        // 구군코드 유효성 검사
        if (gugunCode != null) {
            if (sidoCode == null) {
                throw new AttractionException(INVALID_ATTRACTION_VALUE);
            }
            validateGugun(sidoCode, gugunCode);
        }

        // 관광지 유형 유효성 검사
        if (attractionTypeId != null) {
            validateType(attractionTypeId);
        }

        List<AttractionInfo> infos;
        // 전체
        if (sidoCode == null && gugunCode == null && attractionTypeId == null) {
            infos = infoRepository.findAll();
        }
        // 시도
        else if (gugunCode == null && attractionTypeId == null) {
            infos = infoRepository.findAllBySido(sidoCode);
        }
        // 관광지 유형
        else if (sidoCode == null && gugunCode == null) {
            infos = infoRepository.findAllByType(attractionTypeId);
        }
        // 시도 + 구군
        else if (attractionTypeId == null) {
            infos = infoRepository.findAllBySidoAndGugun(sidoCode, gugunCode);
        }
        // 시도 + 관광지 유형
        else if (gugunCode == null) {
            infos = infoRepository.findAllBySidoAndType(sidoCode, attractionTypeId);
        }
        // 시도 + 구군 + 관광지 유형
        else {
            infos = infoRepository.findAllBySidoAndGugunAndType(sidoCode, gugunCode, attractionTypeId);
        }

        List<GetInfoResponse> result = new ArrayList<>();
        for (AttractionInfo info: infos) {
            result.add(new GetInfoResponse(info));
        }
        return result;
    }

    public GetDescriptionResponse findDescriptionById(HttpServletRequest request, int attractionId) {
        AttractionDescription description = descriptionRepository.findById(attractionId)
                .orElseThrow(() -> new AttractionException(ATTRACTION_NOT_FOUND));

        Member member = findMemberByRequest(request);

        /**
         * 올바르지 않은 토큰일 경우
         * isLogined = false,
         * isBookmarked = false
         */
        if (member == null) {
            return new GetDescriptionResponse(description, false, false);
        }

        /**
         * 올바른 토큰일 경우 (로그인 O)
         * isLogined = true
         */
        // 관광지
        AttractionInfo info = findInfoById(attractionId);
        // 즐겨찾기 여부
        return new GetDescriptionResponse(description, true, isBookmarked(member, info));
    }

    private void validateSido(int sidoCode) {
        if (!sidoRepository.existsByCode(sidoCode)) {
            throw new AttractionException(SIDO_NOT_FOUND);
        }
    }

    private void validateGugun(int sidoCode, int gugunCode) {
        if (!gugunRepository.existsByGugunId(new GugunId(sidoCode, gugunCode))) {
            throw new AttractionException(GUGUN_NOT_FOUND);
        }
    }

    private void validateType(int id) {
        if (!typeRepository.existsById(id)) {
            throw new AttractionException(ATTRACTION_TYPE_NOT_FOUND);
        }
    }

    private Member findMemberByRequest(HttpServletRequest request) {
        // 토큰 얻기
        String token = request.getHeader("Authorization");

        // 토큰이 없는 경우
        if (token == null) {
            return null;
        }

        String memberId;

        // 토큰 정보가 올바르지 않은 경우
        try {
            memberId = jwtUtil.getUserId(token);
        } catch (JwtBadRequestException e) {
            return null;
        }

        // 토큰 정보에 해당하는 멤버 찾기
        return memberRepository.findById(memberId)
                .orElse(null);
    }

    private AttractionInfo findInfoById(int attractionId) {
        return infoRepository.findById(attractionId)
                .orElseThrow(() -> new AttractionException(ATTRACTION_NOT_FOUND));
    }

    private boolean isBookmarked(Member member, AttractionInfo info) {
        if (bookmarkRepository.existsByMemberAndAttractionInfo(member, info)) {
            return true;
        }
        return false;
    }

}
