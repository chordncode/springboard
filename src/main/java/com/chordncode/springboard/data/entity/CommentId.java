package com.chordncode.springboard.data.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentId implements Serializable{
    
    private Long commentSn;
    private Long boardSn;

}