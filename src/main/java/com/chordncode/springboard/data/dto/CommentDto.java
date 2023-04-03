package com.chordncode.springboard.data.dto;

import java.time.LocalDateTime;

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
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
