package com.chordncode.springboard.data.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentKey implements Serializable{
    
    private Long commentSn;
    private Long boardSn;

}