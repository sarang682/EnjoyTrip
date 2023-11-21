package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.common.exception.*;
import com.ssafy.enjoytrip.domain.Bookmark;
import com.ssafy.enjoytrip.dto.bookmark.*;
import com.ssafy.enjoytrip.util.JwtUtil;
import com.ssafy.enjoytrip.common.exception.AttractionException;
import com.ssafy.enjoytrip.common.exception.DatabaseException;
import com.ssafy.enjoytrip.common.exception.MemberException;
import com.ssafy.enjoytrip.common.response.ExceptionStatus;
import com.ssafy.enjoytrip.domain.AttractionInfo;
import com.ssafy.enjoytrip.domain.Member;
import com.ssafy.enjoytrip.repository.attraction.InfoRepository;
import com.ssafy.enjoytrip.repository.bookmark.BookmarkRepository;
import com.ssafy.enjoytrip.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;
    private final InfoRepository infoRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public BookmarkResponse postBookmark(String token, PostBookmarkRequest request) {
        // 멤버
        Member member = getMemberByToken(token);

        // 관광지
        AttractionInfo attractionInfo = findAttractionById(request.getAttractionId());

        // 유효성 검사
        if (existsByMemberAndAttractionInfo(member, attractionInfo)) {
            throw new BookmarkException(ExceptionStatus.DUPLICATE_BOOKMARK);
        }

        // 즐겨찾기 생성
        Bookmark bookmark = new Bookmark(member, attractionInfo);

        // 즐겨찾기 추가
        if (bookmarkRepository.save(bookmark) == null) {
            throw new DatabaseException(ExceptionStatus.DATABASE_ERROR);
        }

        return new BookmarkResponse(bookmark);
    }

    public GetBookmarkResponse getBookmark(String token) {
        // 멤버
        Member member = getMemberByToken(token);

        // 즐겨찾기
        List<Bookmark> bookmarks = bookmarkRepository.findAllByMember(member);

        List<BookmarkDto> res = new ArrayList<>();
        for (Bookmark bookmark: bookmarks) {
            res.add(new BookmarkDto(bookmark));
        }

        return new GetBookmarkResponse(member.getId(), res);
    }

    @Transactional
    public BookmarkResponse deleteBookmark(String token, int bookmarkId) {
        // 멤버 유효성 검사
        if (!existsMemberByToken(token)) {
            throw new MemberException(ExceptionStatus.MEMBER_NOT_FOUND);
        }

        // 즐겨찾기
        Bookmark bookmark = findBookmarkById(bookmarkId);

        // 삭제
        try {
            bookmarkRepository.deleteById(bookmarkId);
        } catch (Exception e) {
            throw new DatabaseException(ExceptionStatus.DATABASE_ERROR);
        }

        return new BookmarkResponse(bookmark);
    }

    private Member getMemberByToken(String token) {
        // 멤버 아이디
        String memberId = getMemberIdByToken(token);
        return findMemberById(memberId);
    }

    private String getMemberIdByToken(String token) {
        // 유효성 검사
        jwtUtil.validateToken(token);
        return jwtUtil.getUserId(token);
    }

    private Member findMemberById(String memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(ExceptionStatus.MEMBER_NOT_FOUND));
        return member;
    }

    private boolean existsMemberByToken(String token) {
        String memberId = getMemberIdByToken(token);
        if (memberRepository.existsById(memberId)) {
            return true;
        }
        return false;
    }

    private AttractionInfo findAttractionById(int attractionId) {
        AttractionInfo attractionInfo = infoRepository.findById(attractionId)
                .orElseThrow(() -> new AttractionException(ExceptionStatus.ATTRACTION_NOT_FOUND));
        return attractionInfo;
    }

    private boolean existsByMemberAndAttractionInfo(Member member, AttractionInfo attractionInfo) {
        if (bookmarkRepository.existsByMemberAndAttractionInfo(member, attractionInfo)) {
            return true;
        }
        return false;
    }

    private Bookmark findBookmarkById(int bookmarkId) {
        return bookmarkRepository.findById(bookmarkId)
                .orElseThrow(() -> new BookmarkException(ExceptionStatus.BOOKMARK_NOT_FOUND));
    }

}
