package com.midas.yoon.services.board.repository;

import com.midas.yoon.config.web.SimplePageRequest;
import com.midas.yoon.entity.board.BoardNotice;
import com.midas.yoon.entity.board.QBoardNotice;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Repository
public class BoardRepositoryImpl extends QuerydslRepositorySupport implements Board {

    public BoardRepositoryImpl() {
        super(BoardNotice.class);
    }

    @Override
    public List<BoardNotice> getBoardList(SimplePageRequest pageRequest, BoardNotice boardNotice) {
        Pageable page = pageRequest.makePageable("id");

        String title = boardNotice.getTitle();
        QBoardNotice qBoardNotice = QBoardNotice.boardNotice;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qBoardNotice.delYn.eq("N"));
        if (! ObjectUtils.isEmpty(title)) {
            builder.and(qBoardNotice.title.contains(title));
        }
        
        return from(qBoardNotice)
            .select(
                Projections.fields(
                    BoardNotice.class, qBoardNotice.id,
                    qBoardNotice.title,
                    qBoardNotice.userId,
                    qBoardNotice.createdAt,
                    qBoardNotice.readCount
                )
            )
            .where(builder)
            .orderBy(qBoardNotice.id.desc())
            .offset(page.getOffset())
            .limit(page.getPageSize())
            .fetch();
    }
}
