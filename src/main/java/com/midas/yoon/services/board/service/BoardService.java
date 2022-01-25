package com.midas.yoon.services.board.service;

import com.midas.yoon.config.web.SimplePageRequest;
import com.midas.yoon.entity.board.BoardNotice;
import com.midas.yoon.errors.NotFoundException;
import com.midas.yoon.services.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public List<BoardNotice> getBoardList(SimplePageRequest pageRequest, BoardNotice boardNotice) {
        return boardRepository.getBoardList(pageRequest, boardNotice);
    }

    public BoardNotice getBoard(Long id) {
        BoardNotice board = boardRepository.findById(id).orElse(null);

        if (ObjectUtils.isEmpty(board) || board.getDelYn().equals("Y")) {
            throw new NotFoundException("존재하지 않거나 삭제된 정보입니다.");
        }

        return board;
    }
}
