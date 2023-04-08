package com.chordncode.springboard.board.service;

import java.util.List;

import org.springframework.web.server.ResponseStatusException;

import com.chordncode.springboard.data.dto.BoardDto;

public interface BoardService {

    /**
     * 게시글 목록 조회
     * 
     * @return 게시글 DTO 리스트
     */
    List<BoardDto> listBoard();
    
    /**
     * 게시글 조회
     * 
     * @param boardSn 게시글 번호
     * @return 게시글 DTO
     * @throws ResponseStatusException
     */
    BoardDto selectBoard(Long boardSn) throws ResponseStatusException;

    /**
     * 게시글 작성
     * 
     * @param boardDto 작성한 게시글의 정보가 담긴 DTO
     * @return 작성된 게시글의 정보가 담긴 DTO
     * @throws Exception
     */
    BoardDto insertBoard(BoardDto boardDto) throws Exception;

    /**
     * 게시글 수정
     * 
     * @param boardSn 수정할 게시글의 번호
     * @param boardDto 수정할 게시글의 정보가 담긴 DTO
     * @return 수정된 게시글의 정보가 담긴 DTO
     * @throws ResponseStatusException
     */
    BoardDto updateBoard(Long boardSn, BoardDto boardDto) throws ResponseStatusException;

    /**
     * 게시글 삭제
     * 
     * @param boardSn 삭제할 게시글 번호
     * @throws ResponseStatusException
     */
    void deleteBoard(Long boardSn) throws ResponseStatusException;

}
