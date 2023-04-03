package com.chordncode.springboard.data.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "board")
@SequenceGenerator(
    name = "SEQ_BOARD",
    sequenceName = "SEQ_BOARD",
    initialValue = 1,
    allocationSize = 1
)
public class Board {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "SEQ_BOARD")
    private Long boardSn;

    @Column(nullable = false, length = 300)
    private String boardTitle;

    @Lob
    @Column(nullable = false)
    private String boardContent;

    @Column(nullable = false, length = 50)
    private String boardWriter;

    @Column(nullable = false)
    private Long boardHit;

    @Column(nullable = false)
    private String boardPw;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany
    private List<Comment> comments;

}