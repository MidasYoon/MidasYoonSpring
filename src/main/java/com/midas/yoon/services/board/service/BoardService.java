package com.midas.yoon.services.board.service;

import com.midas.yoon.config.web.SimplePageRequest;
import com.midas.yoon.entity.board.BoardNotice;
import com.midas.yoon.entity.board.BoardNoticeFile;
import com.midas.yoon.errors.NotFoundException;
import com.midas.yoon.services.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class BoardService {

    private static String BOARD_NOTICE_FILE_ROOT_DIR = "/files/efd2af60c8501931cb9c736b5ad74f65/";

    @Autowired
    private BoardRepository boardRepository;

    public PageImpl<BoardNotice> getBoardList(SimplePageRequest pageRequest, BoardNotice boardNotice) {
        return boardRepository.getBoardList(pageRequest, boardNotice);
    }

    public BoardNotice getBoard(Long id) {
        BoardNotice board = boardRepository.findById(id).orElse(null);
        if (ObjectUtils.isEmpty(board) || board.getDelYn().equals("Y")) {
            throw new NotFoundException("존재하지 않거나 삭제된 정보입니다.");
        }

        return board;
    }

    public BoardNoticeFile getFile(Long fileId) {
        BoardNoticeFile file = boardRepository.getBoardFile(fileId);
        if (ObjectUtils.isEmpty(file)) {
            throw new NotFoundException("존재하지 않거나 삭제된 정보입니다.");
        }

        file.setPath(BOARD_NOTICE_FILE_ROOT_DIR);

        return file;
    }
}
