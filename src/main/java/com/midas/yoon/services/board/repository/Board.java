package com.midas.yoon.services.board.repository;

import com.midas.yoon.config.web.SimplePageRequest;
import com.midas.yoon.entity.board.BoardNotice;

import java.util.List;

public interface Board {
    List<BoardNotice> getBoardList(SimplePageRequest pageRequest, BoardNotice boardNotice);
}
