package com.chordncode.springboard.comment.service;

import java.util.List;

import org.springframework.web.server.ResponseStatusException;

import com.chordncode.springboard.data.dto.CommentDto;

public interface CommentService {
    
    /**
     * 게시글의 댓글 조회
     * 
     * @param boardSn 조회할 댓글 목록의 게시글 번호
     * @return 해당 게시글의 댓글 목록
     */
    List<CommentDto> listComment(Long boardSn);

    /**
     * 댓글 작성
     * 
     * @param boardSn 댓글을 작성할 게시글의 번호
     * @param commentDto 작성한 댓글의 정보가 담긴 DTO
     * @return 작성된 댓글의 정보가 담긴 DTO
     * @throws Exception
     */
    CommentDto insertComment(Long boardSn, CommentDto commentDto) throws Exception;

    /**
     * 댓글에 대댓글 작성
     * 
     * @param boardSn 댓글을 작성할 게시글의 번호
     * @param targetCommentSn 대댓글을 작성할 댓글의 번호
     * @param commentDto 작성한 댓글의 정보가 담긴 DTO
     * @return 작성된 댓글의 정보가 담긴 DTO
     * @throws Exception
     */
    CommentDto insertCommentToComment(Long boardSn, Long targetCommentSn, CommentDto commentDto) throws Exception;

    /**
     * 댓글 수정
     * 
     * @param boardSn 수정할 댓글의 게시글의 번호
     * @param commentSn 수정할 댓글의 번호
     * @param commentDto 수정한 댓글의 정보가 담긴 DTO
     * @return 수정한 댓글의 정보가 담긴 DTO
     * @throws ResponseStatusException
     */
    CommentDto updateComment(Long boardSn, Long commentSn, CommentDto commentDto) throws ResponseStatusException;

    /**
     * 댓글 삭제
     * 
     * @param boardSn 삭제할 댓글이 있는 게시글 번호
     * @param commentSn 삭제할 댓글의 댓글 번호
     * @throws ResponseStatusException
     */
    void deleteComment(Long boardSn, Long commentSn) throws ResponseStatusException;

}
