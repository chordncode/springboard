package com.chordncode.springboard.data.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BoardDto {

    private Long boardSn;
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private Long boardHit;
    private String boardPw;

    private String createdAt;
    private String updatedAt;

}
