package com.chordncode.springboard.board.controller;

import java.util.List;

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
import org.springframework.web.server.ResponseStatusException;

import com.chordncode.springboard.board.service.BoardService;
import com.chordncode.springboard.board.service.BoardServiceImpl;
import com.chordncode.springboard.data.dto.BoardDto;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;
    
    public BoardController(BoardServiceImpl boardServiceImpl) {
        this.boardService = boardServiceImpl;
    }
    
    @GetMapping("")
    public ResponseEntity<List<BoardDto>> listBoard(){
        return ResponseEntity.ok(boardService.listBoard());
    }

    @GetMapping("/{boardSn}")
    public ResponseEntity<BoardDto> selectBoard(@PathVariable Long boardSn){
        try {
            return ResponseEntity.ok(boardService.selectBoard(boardSn));
        } catch (ResponseStatusException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<?> insertBoard(@RequestBody BoardDto boardDto){
        try{
            return ResponseEntity.ok(boardService.insertBoard(boardDto));
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{boardSn}")
    public ResponseEntity<?> updateBoard(@PathVariable Long boardSn, @RequestBody BoardDto boardDto){
        try{
            return ResponseEntity.ok(boardService.updateBoard(boardSn, boardDto));
        } catch(ResponseStatusException e){
            if(e.getStatus() == HttpStatus.NOT_FOUND) return ResponseEntity.notFound().build();
            if(e.getStatus() == HttpStatus.UNAUTHORIZED) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 틀렸습니다.");
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{boardSn}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long boardSn){
        try{
            boardService.deleteBoard(boardSn);
            return ResponseEntity.ok().build();
        } catch (ResponseStatusException e){
            if(e.getStatus() == HttpStatus.NOT_FOUND) return ResponseEntity.notFound().build();
            return ResponseEntity.badRequest().build();
        }
    }

}
