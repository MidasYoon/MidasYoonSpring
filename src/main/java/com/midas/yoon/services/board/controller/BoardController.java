package com.midas.yoon.services.board.controller;

import com.midas.yoon.config.web.SimplePageRequest;
import com.midas.yoon.entity.board.BoardNotice;
import com.midas.yoon.entity.board.BoardNoticeFile;
import com.midas.yoon.services.board.service.BoardService;
import com.midas.yoon.utils.ApiUtils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import static com.midas.yoon.utils.ApiUtils.success;

@RestController
@RequestMapping("/api/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ApiResult<PageImpl<BoardNotice>> getBoardList(
            @ModelAttribute("SimplePageRequest") SimplePageRequest pageRequest,
            BoardNotice boardNotice
    ) {
        return success(boardService.getBoardList(pageRequest, boardNotice));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResult<BoardNotice> getBoard(@PathVariable Long id) {
        return success(boardService.getBoard(id));
    }

    @RequestMapping(value = "/{id}/file/{fileId}", method = RequestMethod.GET)
    public ApiResult<BoardNoticeFile> getFile(@PathVariable Long id, @PathVariable Long fileId) {
        return success(boardService.getFile(fileId));
    }
}
