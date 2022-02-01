package com.midas.yoon.services.board.repository;

import com.midas.yoon.config.web.SimplePageRequest;
import com.midas.yoon.entity.board.BoardNotice;
import com.midas.yoon.entity.board.BoardNoticeFile;
import org.springframework.data.domain.PageImpl;

public interface Board {
    PageImpl<BoardNotice> getBoardList(SimplePageRequest pageRequest, BoardNotice boardNotice);
    BoardNoticeFile getBoardFile(Long fileId);
}
