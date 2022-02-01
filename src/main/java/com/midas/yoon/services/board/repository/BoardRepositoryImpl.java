package com.midas.yoon.services.board.repository;

import com.midas.yoon.config.web.SimplePageRequest;
import com.midas.yoon.entity.board.*;
import com.midas.yoon.entity.user.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class BoardRepositoryImpl extends QuerydslRepositorySupport implements Board {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    public BoardRepositoryImpl(EntityManager entityManager) {
        super(BoardNotice.class);
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public PageImpl<BoardNotice> getBoardList(SimplePageRequest pageRequest, BoardNotice boardNotice) {
        String title = boardNotice.getTitle();

        QBoardNotice qBoardNotice = QBoardNotice.boardNotice;
        QBoardNoticeComment qBoardNoticeComment = QBoardNoticeComment.boardNoticeComment;
        QBoardNoticeFile qBoardNoticeFile = QBoardNoticeFile.boardNoticeFile;
        QUser qUser = QUser.user;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qBoardNotice.delYn.eq("N"));
        if (! ObjectUtils.isEmpty(title)) {
            builder.and(qBoardNotice.title.contains(title));
        }

        JPAQuery<BoardNotice> query = jpaQueryFactory
            .select(
                Projections.fields(
                    BoardNotice.class,
                    qBoardNotice.id,
                    qBoardNotice.title,
                    qUser.nickname,
                    qBoardNotice.createdAt,
                    qBoardNotice.readCount,
                    qBoardNoticeComment.id.count().as("commentCount"),
                    qBoardNoticeFile.id.count().as("fileCount")
                )
            )
            .from(qBoardNotice)
            .leftJoin(qBoardNoticeComment).on(
                qBoardNoticeComment.boardId.eq(qBoardNotice.id)
                    .and(qBoardNoticeComment.delYn.eq("N"))
            )
            .leftJoin(qBoardNoticeFile).on(
                qBoardNoticeFile.boardId.eq(qBoardNotice.id)
                    .and(qBoardNoticeFile.delYn.eq("N"))
            )
            .leftJoin(qUser).on(
                qUser.id.eq(qBoardNotice.user.id)
            )
            .where(builder)
            .groupBy(qBoardNotice.id)
            .orderBy(qBoardNotice.id.desc());

        Pageable page = pageRequest.makePageable("id");
        long total = query.fetch().size();
        List<BoardNotice> results = getQuerydsl().applyPagination(page, query).fetch();

        return new PageImpl<>(results, page, total);
    }

    @Override
    public BoardNoticeFile getBoardFile(Long fileId) {
        QBoardNotice qBoardNotice = QBoardNotice.boardNotice;
        QBoardNoticeFile qBoardNoticeFile = QBoardNoticeFile.boardNoticeFile;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qBoardNoticeFile.id.eq(fileId));
        builder.and(qBoardNoticeFile.delYn.eq("N"));
        builder.and(qBoardNotice.delYn.eq("N"));

        return jpaQueryFactory
            .select(
                Projections.fields(
                    BoardNoticeFile.class,
                    qBoardNoticeFile.fileName,
                    qBoardNoticeFile.clientFileName
                )
            )
            .from(qBoardNoticeFile)
            .leftJoin(qBoardNotice).on(qBoardNoticeFile.boardId.eq(qBoardNotice.id))
            .where(builder)
            .fetchFirst();
    }
}
