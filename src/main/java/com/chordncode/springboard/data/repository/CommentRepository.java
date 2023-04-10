package com.chordncode.springboard.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chordncode.springboard.data.entity.Comment;
import com.chordncode.springboard.data.entity.CommentKey;

public interface CommentRepository extends JpaRepository<Comment, CommentKey> {

    Optional<List<Comment>> findByBoardSn(Long boardSn);

    @Query("SELECT COALESCE(MAX(commentSn), 0) " + 
             "FROM Comment " +
             "WHERE boardSn = :boardSn")
    Long findMaxCommentSnByBoardSn(Long boardSn);

    Optional<Comment> findByBoardSnAndCommentSn(Long boardSn, Long commentSn);

    void deleteByBoardSnAndCommentSn(Long boardSn, Long commentSn);

}