package com.chordncode.springboard.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chordncode.springboard.board.service.BoardService;
import com.chordncode.springboard.board.service.BoardServiceImpl;
import com.chordncode.springboard.data.dto.BoardDto;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    
    public BoardController(BoardServiceImpl boardServiceImpl) {
        this.boardService = boardServiceImpl;
    }
    
    @GetMapping("")
    public ResponseEntity<Iterable<BoardDto>> listBoard(){
        return ResponseEntity.status(HttpStatus.OK).body(boardService.listBoard());
    }

    @GetMapping("/{boardSn}")
    public ResponseEntity<BoardDto> selectBoard(@PathVariable Long boardSn){
        return ResponseEntity.status(HttpStatus.OK).body(boardService.selectBoard(boardSn));
    }

    @PostMapping("")
    public ResponseEntity<BoardDto> insertBoard(@RequestBody BoardDto boardDto){
        return ResponseEntity.status(HttpStatus.OK).body(boardService.insertBoard(boardDto));
    }

    @PutMapping("/{boardSn}")
    public ResponseEntity<BoardDto> updateBoard(@PathVariable Long boardSn, @RequestBody BoardDto boardDto){
        return ResponseEntity.status(HttpStatus.OK).body(boardService.updateBoard(boardSn, boardDto));
    }

    @DeleteMapping("/{boardSn}")
        public ResponseEntity<Void> deleteBoard(@PathVariable Long boardSn){
            boardService.deleteBoard(boardSn);
            return ResponseEntity.status(HttpStatus.OK).build();
        }

}
