package com.chordncode.springboard.data.dto;


import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.chordncode.springboard.data.entity.Board;

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

    private List<CommentDto> commentList;

    private String createdAt;
    private String updatedAt;

    public BoardDto(Board board){
        this.boardSn = board.getBoardSn();
        this.boardTitle = board.getBoardTitle();
        this.boardContent = board.getBoardContent();
        this.boardWriter = board.getBoardWriter();
        this.boardHit = board.getBoardHit();
        this.boardPw = board.getBoardPw();
        if(board.getCreatedAt() != null) {
            this.createdAt = board.getCreatedAt()
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        if(board.getUpdatedAt() != null){
            this.updatedAt = board.getUpdatedAt()
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        this.commentList = board.getComments().stream().map(commentEntity -> {
            CommentDto commentDto = CommentDto.builder()
                .commentSn(commentEntity.getCommentSn())
                .boardSn(commentEntity.getBoardSn())
                .commentContent(commentEntity.getCommentContent())
                .commentWriter(commentEntity.getCommentWriter())
                .commentPw(commentEntity.getCommentPw())
                .createdAt(commentEntity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();

                return commentDto;
        }).collect(Collectors.toList());
    }

}
