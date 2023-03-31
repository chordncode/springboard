package com.chordncode.springboard.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chordncode.springboard.data.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {}