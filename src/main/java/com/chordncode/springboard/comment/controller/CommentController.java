package com.chordncode.springboard.comment.controller;

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

import com.chordncode.springboard.comment.service.CommentService;
import com.chordncode.springboard.data.dto.CommentDto;

@RestController
@RequestMapping("/boards/{boardSn}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("")
    public ResponseEntity<?> listComment(@PathVariable Long boardSn) {
        return ResponseEntity.ok().body(commentService.listComment(boardSn));
    }
    
    @PostMapping("")
    public ResponseEntity<?> insertComment(@PathVariable Long boardSn, @RequestBody CommentDto commentDto){
        try {
            return ResponseEntity.ok().body(commentService.insertComment(boardSn, commentDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{commentSn}")
    public ResponseEntity<?> updateComment(@PathVariable Long boardSn, @PathVariable Long commentSn, @RequestBody CommentDto commentDto){
        try{
            return ResponseEntity.ok().body(commentService.updateComment(boardSn, commentSn, commentDto));
        } catch (ResponseStatusException e){
            if(e.getStatus() == HttpStatus.NOT_FOUND) return ResponseEntity.notFound().build();
            if(e.getStatus() == HttpStatus.UNAUTHORIZED) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 틀렸습니다.");
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{commentSn}")
    public ResponseEntity<?> deleteComment(@PathVariable Long boardSn, @PathVariable Long commentSn){
        try{
            commentService.deleteComment(boardSn, commentSn);
            return ResponseEntity.ok().build();
        } catch(ResponseStatusException e){
            return ResponseEntity.notFound().build();
        }
    }

}
