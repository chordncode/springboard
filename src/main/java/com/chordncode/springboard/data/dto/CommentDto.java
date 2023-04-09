package com.chordncode.springboard.data.dto;

import java.time.format.DateTimeFormatter;

import com.chordncode.springboard.data.entity.Comment;

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
public class CommentDto {
    
    private Long commentSn;
    private Long boardSn;

    private String commentContent;
    private String commentWriter;
    private String commentPw;
    
    private String createdAt;
    private String updatedAt;

    public CommentDto(Comment comment){
        this.commentSn = comment.getCommentSn();
        this.boardSn = comment.getBoardSn();
        this.commentContent = comment.getCommentContent();
        this.commentWriter = comment.getCommentWriter();
        this.commentPw = comment.getCommentPw();
        if(comment.getCreatedAt()!= null){
            this.createdAt = comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        if(comment.getUpdatedAt()!= null){
            this.updatedAt = comment.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }

}
