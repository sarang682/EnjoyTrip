package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.common.exception.*;
import com.ssafy.enjoytrip.config.jwt.JwtUtil;
import com.ssafy.enjoytrip.domain.Bookmark;
import com.ssafy.enjoytrip.dto.attraction.GetDescriptionResponse;
import com.ssafy.enjoytrip.dto.attraction.GetInfoResponse;
import com.ssafy.enjoytrip.dto.bookmark.*;
import com.ssafy.enjoytrip.common.exception.AttractionException;
import com.ssafy.enjoytrip.common.exception.DatabaseException;
import com.ssafy.enjoytrip.common.exception.MemberException;
import com.ssafy.enjoytrip.common.response.ExceptionStatus;
import com.ssafy.enjoytrip.domain.AttractionInfo;
import com.ssafy.enjoytrip.domain.Member;
import com.ssafy.enjoytrip.repository.attraction.InfoRepository;
import com.ssafy.enjoytrip.repository.bookmark.BookmarkRepository;
import com.ssafy.enjoytrip.repository.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkService {

//    private final BookmarkRepository bookmarkRepository;
//    private final MemberRepository memberRepository;
//    private final InfoRepository infoRepository;
//    private final JwtUtil jwtUtil;
//
//    @Transactional
//    public ChangeBookmarkResponse changeBookmark(HttpServletRequest httpServletRequest, PostBookmarkRequest request) {
//        // 멤버
//        Member member = getMemberByRequest(httpServletRequest);
//
//        // 관광지
//        AttractionInfo info = findAttractionById(request.getAttractionId());
//
//        // 즐겨찾기
//        Bookmark bookmark = findBookmark(member, info);
//
//        /**
//         * 즐겨찾기가 존재하는 경우
//         * 데이터 삭제
//         */
//        if (findBookmark(member, info) != null) {
//            return deleteBookmark(member, bookmark);
//        }
//
//        /**
//         * 즐겨찾기가 존재하지 않는 경우
//         * 데이터 삽입
//         */
//        return postBookmark(member, info);
//
//    }
//
//    public ChangeBookmarkResponse postBookmark(Member member, AttractionInfo info) {
//        // 즐겨찾기 생성
//        Bookmark bookmark = new Bookmark(member, info);
//
//        // 즐겨찾기 추가
//        if (bookmarkRepository.save(bookmark) == null) {
//            throw new DatabaseException(ExceptionStatus.DATABASE_ERROR);
//        }
//
//        return new ChangeBookmarkResponse(bookmark, "insert");
//    }
//
//    private ChangeBookmarkResponse deleteBookmark(Member member, Bookmark bookmark) {
//        // 토큰에 들어있는 멤버 정보와 즐겨찾기의 멤버 정보가 일치하는지 확인
//        String tId = member.getId();
//        String bId = bookmark.getMember().getId();
//        if (!tId.equals(bId)) {
//            throw new JwtBadRequestException(ExceptionStatus.TOKEN_MISMATCH);
//        }
//
//        // 삭제
//        try {
//            bookmarkRepository.deleteById(bookmark.getId());
//        } catch (Exception e) {
//            throw new DatabaseException(ExceptionStatus.DATABASE_ERROR);
//        }
//
//        return new ChangeBookmarkResponse(bookmark, "delete");
//    }
//
//    public GetBookmarkResponse getBookmarkList(HttpServletRequest httpServletRequest) {
//        // 멤버
//        Member member = getMemberByRequest(httpServletRequest);
//
//        // 즐겨찾기
//        List<Bookmark> bookmarks = bookmarkRepository.findAllByMember(member);
//
//        // 관광지
//        List<GetInfoResponse> attractions = new ArrayList<>();
//        for (Bookmark bookmark: bookmarks) {
//            AttractionInfo info = bookmark.getAttractionInfo();
//            attractions.add(new GetInfoResponse(info, true, true));
//        }
//
//        return new GetBookmarkResponse(member.getId(), attractions);
//    }
//
//    private Member getMemberByRequest(HttpServletRequest request) {
//        // 멤버 아이디
//        String memberId = getMemberIdByRequest(request);
//        return findMemberById(memberId);
//    }
//
//    private String getMemberIdByRequest(HttpServletRequest request) {
//        // 토큰
//        String token = getToken(request);
//        // 유효성 검사
////        jwtUtil.validateToken(token);
//        return jwtUtil.getUsernameFromToken(token);
//    }
//
//    private String getToken(HttpServletRequest request) {
////        return jwtUtil.resolveToken(request);
//        return "";
//    }
//
//    private Member findMemberById(String memberId) {
//        Member member = memberRepository.findById(memberId)
//                .orElseThrow(() -> new MemberException(ExceptionStatus.MEMBER_NOT_FOUND));
//        return member;
//    }
//
//    private AttractionInfo findAttractionById(int attractionId) {
//        AttractionInfo attractionInfo = infoRepository.findById(attractionId)
//                .orElseThrow(() -> new AttractionException(ExceptionStatus.ATTRACTION_NOT_FOUND));
//        return attractionInfo;
//    }
//
//    private Bookmark findBookmark(Member member, AttractionInfo info) {
//        return bookmarkRepository.findByMemberAndAttractionInfo(member, info);
//    }

}
