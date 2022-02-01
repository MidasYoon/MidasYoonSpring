package com.midas.yoon.services.board.repository;

import com.midas.yoon.entity.board.BoardNotice;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<BoardNotice, Long>, QuerydslPredicateExecutor<BoardNotice>, Board {
}
