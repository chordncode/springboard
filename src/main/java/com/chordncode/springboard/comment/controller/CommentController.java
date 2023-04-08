package com.chordncode.springboard.comment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chordncode.springboard.comment.service.CommentService;
import com.chordncode.springboard.data.dto.CommentDto;

@RestController
@RequestMapping("/boards/{boardSn}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    
    @PostMapping("")
    public ResponseEntity<?> insertComment(@PathVariable Long boardSn, @RequestBody CommentDto commentDto){
        System.out.println(boardSn);
        try {
            return ResponseEntity.ok().body(commentService.insertComment(boardSn, commentDto));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
