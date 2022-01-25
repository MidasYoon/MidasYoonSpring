package com.midas.yoon.services.board.controller;

import com.midas.yoon.config.web.SimplePageRequest;
import com.midas.yoon.entity.board.BoardNotice;
import com.midas.yoon.services.board.service.BoardService;
import com.midas.yoon.utils.ApiUtils;
import com.midas.yoon.utils.ApiUtils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ApiResult<List<BoardNotice>> getBoardList(
            @ModelAttribute("SimplePageRequest")SimplePageRequest pageRequest,
            BoardNotice boardNotice
    ) {
        return ApiUtils.success(boardService.getBoardList(pageRequest, boardNotice));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResult<BoardNotice> getBoard(@PathVariable Long id) {
        return ApiUtils.success(boardService.getBoard(id));
    }
}
