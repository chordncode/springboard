package com.chordncode.springboard.board.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.chordncode.springboard.data.dto.BoardDto;
import com.chordncode.springboard.data.entity.Board;
import com.chordncode.springboard.data.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public List<BoardDto> listBoard() {
        return boardRepository.findAll().stream().map(board -> {
            return new BoardDto(board);
        }).collect(Collectors.toList());
    }

    @Override
    public BoardDto selectBoard(Long boardSn) throws ResponseStatusException {
        Board board = boardRepository.findById(boardSn).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new BoardDto(board);
    }

    @Override
    public BoardDto insertBoard(BoardDto boardDto) throws Exception {
        Board board = Board.builder()
                .boardSn(boardDto.getBoardSn())
                .boardTitle(boardDto.getBoardTitle())
                .boardContent(boardDto.getBoardContent())
                .boardWriter(boardDto.getBoardWriter())
                .boardHit(0l)
                .boardPw(boardDto.getBoardPw())
                .createdAt(LocalDateTime.now())
                .build();

        return new BoardDto(boardRepository.save(board));
    }

    @Override
    public BoardDto updateBoard(Long boardSn, BoardDto boardDto) throws ResponseStatusException {

        Board selectedBoard = boardRepository.findById(boardSn).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다."));
        if(!selectedBoard.getBoardPw().equals(boardDto.getBoardPw())) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 틀렸습니다.");

        selectedBoard.setBoardTitle(boardDto.getBoardTitle());
        selectedBoard.setBoardContent(boardDto.getBoardContent());
        selectedBoard.setBoardWriter(boardDto.getBoardWriter());
        selectedBoard.setUpdatedAt(LocalDateTime.now());
        
        return new BoardDto(boardRepository.save(selectedBoard));
    }

    @Override
    public void deleteBoard(Long boardSn) {
        boardRepository.findById(boardSn).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다."));
        boardRepository.deleteById(boardSn);
    }

}
