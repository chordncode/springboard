package com.chordncode.springboard.comment.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public List<CommentDto> listComment(Long boardSn) {
        return commentRepository.findByBoardSn(boardSn).orElse(null).stream().map(comment -> {
            return new CommentDto(comment);
        }).collect(Collectors.toList());
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
        
        return new CommentDto(commentRepository.save(comment));
    }

    @Override
    public CommentDto insertCommentToComment(Long boardSn, Long targetCommentSn, CommentDto commentDto) throws Exception {
        Long commentSn = commentRepository.findMaxCommentSnByBoardSn(boardSn) + 1;
        Comment comment = Comment.builder()
                .boardSn(boardSn)
                .commentSn(commentSn)
                .commentContent(commentDto.getCommentContent())
                .commentWriter(commentDto.getCommentWriter())
                .commentPw(commentDto.getCommentPw())
                .targetCommentSn(targetCommentSn)
                .createdAt(LocalDateTime.now())
                .build();

        return new CommentDto(commentRepository.save(comment));
    }

    @Override
    public CommentDto updateComment(Long boardSn, Long commentSn, CommentDto commentDto) throws ResponseStatusException {
        Comment selectedComment = commentRepository.findByBoardSnAndCommentSn(boardSn, commentSn).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다."));
        if(!selectedComment.getCommentPw().equals(commentDto.getCommentPw())) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 틀렸습니다.");

        selectedComment.setCommentContent(commentDto.getCommentContent());
        selectedComment.setUpdatedAt(LocalDateTime.now());
        return new CommentDto(commentRepository.save(selectedComment));
    }

    @Transactional
    @Override
    public void deleteComment(Long boardSn, Long commentSn) throws ResponseStatusException {
        commentRepository.findByBoardSnAndCommentSn(boardSn, commentSn).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다."));
        commentRepository.deleteByBoardSnAndCommentSn(boardSn, commentSn);
    }

}
