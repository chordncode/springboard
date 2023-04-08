package com.chordncode.springboard.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chordncode.springboard.data.entity.Comment;
import com.chordncode.springboard.data.entity.CommentKey;

public interface CommentRepository extends JpaRepository<Comment, CommentKey> {

    @Query("SELECT COALESCE(MAX(commentSn), 0) FROM Comment WHERE boardSn = :boardSn")
    Long findMaxCommentSnByBoardSn(Long boardSn);

}