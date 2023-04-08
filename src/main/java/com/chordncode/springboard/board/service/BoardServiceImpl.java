package com.chordncode.springboard.board.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.ValidationException;

import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.chordncode.springboard.data.dto.BoardDto;
import com.chordncode.springboard.data.dto.CommentDto;
import com.chordncode.springboard.data.entity.Board;
import com.chordncode.springboard.data.entity.Comment;
import com.chordncode.springboard.data.repository.BoardRepository;
import com.chordncode.springboard.data.repository.CommentRepository;

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
    public BoardDto selectBoard(Long boardSn) throws ResponseStatusException {
        Board board = boardRepository.findById(boardSn).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

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
    public BoardDto updateBoard(Long boardSn, BoardDto boardDto) throws ResponseStatusException {

        Board selectedBoard = boardRepository.findById(boardSn).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다."));
        if(!selectedBoard.getBoardPw().equals(boardDto.getBoardPw())) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 틀렸습니다.");

        selectedBoard.setBoardTitle(boardDto.getBoardTitle());
        selectedBoard.setBoardContent(boardDto.getBoardContent());
        selectedBoard.setBoardWriter(boardDto.getBoardWriter());
        selectedBoard.setUpdatedAt(LocalDateTime.now());
        Board updatedBoard = boardRepository.save(selectedBoard);

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
        boardRepository.findById(boardSn).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다."));
        boardRepository.deleteById(boardSn);
    }

}
