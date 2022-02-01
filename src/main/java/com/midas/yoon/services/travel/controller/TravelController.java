package com.midas.yoon.services.travel.controller;

import com.midas.yoon.config.web.SimplePageRequest;
import com.midas.yoon.entity.travel.TravelGallery;
import com.midas.yoon.services.travel.service.TravelService;
import com.midas.yoon.utils.ApiUtils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.midas.yoon.utils.ApiUtils.success;

@RestController
@RequestMapping("/api/travel")
public class TravelController {
    @Autowired
    TravelService travelService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ApiResult<PageImpl<TravelGallery>> getTravelList(
            @ModelAttribute("SimplePageRequest") SimplePageRequest pageRequest,
            TravelGallery travelGallery
    ) {
        return success(travelService.getTravelList(pageRequest, travelGallery));
    }

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public ApiResult<List<TravelGallery>> getTravelPointList(TravelGallery travelGallery) {
        return success(travelService.getTravelPointList(travelGallery));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResult<TravelGallery> getTravelData(@PathVariable Long id) {
        return success(travelService.getTravelData(id));
    }
}
