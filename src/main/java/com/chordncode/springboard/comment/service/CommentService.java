package com.chordncode.springboard.comment.service;

import com.chordncode.springboard.data.dto.CommentDto;

public interface CommentService {
    
    /**
     * 댓글 작성
     * 
     * @param boardSn 댓글을 작성할 게시글의 번호
     * @param commentDto 작성한 댓글의 정보가 담긴 DTO
     * @return 작성된 댓글의 정보가 담긴 DTO
     * @throws Exception
     */
    CommentDto insertComment(Long boardSn, CommentDto commentDto) throws Exception;

}
