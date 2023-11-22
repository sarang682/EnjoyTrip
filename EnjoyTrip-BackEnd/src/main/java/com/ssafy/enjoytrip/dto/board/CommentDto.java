package com.ssafy.enjoytrip.dto.board;

import com.ssafy.enjoytrip.domain.Comment;
import com.ssafy.enjoytrip.dto.member.MemberDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommentDto {

    private int id;
    private String content;
    private MemberDto member;
    private String createdAt;
    private String updatedAt;

    public static CommentDto fromEntity (Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getContent(),
                MemberDto.fromEntity(comment.getMember()),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }

}
