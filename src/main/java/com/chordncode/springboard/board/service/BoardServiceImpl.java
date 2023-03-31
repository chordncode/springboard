package com.chordncode.springboard.board.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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
            BoardDto boardDto = BoardDto.builder()
                  .boardSn(board.getBoardSn())
                  .boardTitle(board.getBoardTitle())
                  .boardContent(board.getBoardContent())
                  .boardWriter(board.getBoardWriter())
                  .boardHit(board.getBoardHit())
                  .boardPw(board.getBoardPw())
                  .createdAt(board.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                  .build();
                  
            if(board.getUpdatedAt() != null){
                boardDto.setUpdatedAt(board.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
            return boardDto;
        }).collect(Collectors.toList());
    }

    @Override
    public BoardDto selectBoard(Long boardSn) {
        Board board = boardRepository.findById(boardSn).orElse(null);

        if (board == null)
            return null;

        BoardDto boardDto = BoardDto.builder()
                .boardSn(board.getBoardSn())
                .boardTitle(board.getBoardTitle())
                .boardContent(board.getBoardContent())
                .boardWriter(board.getBoardWriter())
                .boardPw(board.getBoardPw())
                .createdAt(board.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();

        return boardDto;
    }

    @Override
    public BoardDto insertBoard(BoardDto boardDto) {
        Board board = Board.builder()
                .boardSn(boardDto.getBoardSn())
                .boardTitle(boardDto.getBoardTitle())
                .boardContent(boardDto.getBoardContent())
                .boardWriter(boardDto.getBoardWriter())
                .boardHit(0l)
                .boardPw(boardDto.getBoardPw())
                .createdAt(LocalDateTime.now())
                .build();

        Board savedBoard = boardRepository.save(board);

        BoardDto savedBoardDto = BoardDto.builder()
                .boardSn(savedBoard.getBoardSn())
                .boardTitle(savedBoard.getBoardTitle())
                .boardContent(savedBoard.getBoardContent())
                .boardWriter(savedBoard.getBoardWriter())
                .boardHit(savedBoard.getBoardHit())
                .boardPw(savedBoard.getBoardPw())
                .createdAt(savedBoard.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();

        return savedBoardDto;
    }

    @Override
    public BoardDto updateBoard(Long boardSn, BoardDto boardDto) {

        Optional<Board> selectedBoard = boardRepository.findById(boardSn);
        if(!selectedBoard.isPresent()) return null;

        Board board = selectedBoard.get();
        board.setBoardTitle(boardDto.getBoardTitle());
        board.setBoardContent(boardDto.getBoardContent());
        board.setBoardWriter(boardDto.getBoardWriter());
        board.setUpdatedAt(LocalDateTime.now());
        Board updatedBoard = boardRepository.save(board);

        BoardDto updatedBoardDto = BoardDto.builder()
                .boardSn(updatedBoard.getBoardSn())
                .boardTitle(updatedBoard.getBoardTitle())
                .boardContent(updatedBoard.getBoardContent())
                .boardWriter(updatedBoard.getBoardWriter())
                .boardHit(updatedBoard.getBoardHit())
                .boardPw(updatedBoard.getBoardPw())
                .createdAt(updatedBoard.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .updatedAt(updatedBoard.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();

        return updatedBoardDto;
    }

    @Override
    public void deleteBoard(Long boardSn) {
        boardRepository.deleteById(boardSn);
    }
}