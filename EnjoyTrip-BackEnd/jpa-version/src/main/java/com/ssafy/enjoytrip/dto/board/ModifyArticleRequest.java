package com.ssafy.enjoytrip.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModifyArticleRequest {

    private int articleId;
    private String title;
    private String content;

}
