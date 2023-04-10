package com.chordncode.springboard.data.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "comment")
@IdClass(CommentKey.class)
public class Comment {

    @Id
    @Column(name = "board_sn")
    private Long boardSn;

    @Id
    @Column(name = "comment_sn")
    private Long commentSn;

    @Column(nullable = false, name="comment_content")
    private String commentContent;

    @Column(nullable = false, name="comment_writer")
    private String commentWriter;

    @Column(nullable = false, name="comment_pw")
    private String commentPw;

    @Column(name="target_comment_sn")
    private Long targetCommentSn;

    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "board_sn", insertable = false, updatable = false)
    private Board board;

}
