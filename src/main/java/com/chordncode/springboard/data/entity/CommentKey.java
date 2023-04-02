package com.chordncode.springboard.data.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class CommentKey implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long commentSn;
    private Long boardSn;

}