package com.midas.yoon.services.board.repository;

import com.midas.yoon.entity.board.BoardNotice;
import com.midas.yoon.entity.board.QBoardNotice;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.util.ObjectUtils;

public interface BoardRepository extends CrudRepository<BoardNotice, Long>, QuerydslPredicateExecutor<BoardNotice>, Board {
    default Predicate makePredicate(BoardNotice boardNotice) {
        String title = boardNotice.getTitle();
        QBoardNotice qBoardNotice = QBoardNotice.boardNotice;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qBoardNotice.delYn.eq("N"));
        if (! ObjectUtils.isEmpty(title)) {
            builder.and(qBoardNotice.title.contains(title));
        }

        return builder;
    }
}
