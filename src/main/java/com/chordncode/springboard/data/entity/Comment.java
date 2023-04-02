package com.chordncode.springboard.data.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
public class Comment {

    @EmbeddedId
    private CommentKey commentKey;

    @Column(nullable = false)
    private String commentContent;

    @Column(nullable = false)
    private String commentWriter;

    @Column(nullable = false)
    private String commentPw;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
