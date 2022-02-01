package com.midas.yoon.services.travel.repository;

import com.midas.yoon.config.web.SimplePageRequest;
import com.midas.yoon.entity.travel.TravelGallery;
import com.midas.yoon.entity.travel.TravelGalleryImage;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public interface Travel {
    PageImpl<TravelGallery> getTravelList(SimplePageRequest pageRequest, TravelGallery travelGallery);
    List<TravelGallery> getTravelPointList(TravelGallery travelGallery);
    List<TravelGalleryImage> getTravelImages(Long id, int modifiedVersion);
}
