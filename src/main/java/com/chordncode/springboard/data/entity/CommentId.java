package com.chordncode.springboard.data.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class CommentId implements Serializable{
    
    private Long commentSn;
    private Long boardSn;

}