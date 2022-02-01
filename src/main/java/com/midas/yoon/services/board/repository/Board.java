package com.midas.yoon.services.board.repository;

import com.midas.yoon.config.web.SimplePageRequest;
import com.midas.yoon.entity.board.BoardNotice;
import com.midas.yoon.entity.board.BoardNoticeComment;
import com.midas.yoon.entity.board.BoardNoticeFile;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public interface Board {
    PageImpl<BoardNotice> getBoardList(SimplePageRequest pageRequest, BoardNotice boardNotice);
    List<BoardNoticeFile> getBoardFiles(Long id);
    BoardNoticeFile getBoardFile(Long fileId);
    List<BoardNoticeComment> getComments(Long id);
}
