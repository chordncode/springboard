package com.chordncode.springboard.comment.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.chordncode.springboard.data.dto.CommentDto;
import com.chordncode.springboard.data.entity.Comment;
import com.chordncode.springboard.data.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto insertComment(Long boardSn, CommentDto commentDto) throws Exception {
        Long commentSn = commentRepository.findMaxCommentSnByBoardSn(boardSn) + 1;
        Comment comment = Comment.builder()
                .boardSn(boardSn)
                .commentSn(commentSn)
                .commentContent(commentDto.getCommentContent())
                .commentWriter(commentDto.getCommentWriter())
                .commentPw(commentDto.getCommentPw())
                .createdAt(LocalDateTime.now())
                .build();
        
        Comment savedComment = commentRepository.save(comment);
        CommentDto savedCommentDto = CommentDto.builder()
                .boardSn(boardSn)
                .commentSn(savedComment.getCommentSn())
                .commentContent(savedComment.getCommentContent())
                .commentWriter(savedComment.getCommentWriter())
                .commentPw(savedComment.getCommentPw())
                .createdAt(savedComment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();

        return savedCommentDto;
    }

}
