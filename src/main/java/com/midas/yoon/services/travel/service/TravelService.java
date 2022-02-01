package com.midas.yoon.services.travel.service;

import com.midas.yoon.config.web.SimplePageRequest;
import com.midas.yoon.entity.travel.TravelGallery;
import com.midas.yoon.entity.travel.TravelGalleryImage;
import com.midas.yoon.errors.NotFoundException;
import com.midas.yoon.services.travel.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class TravelService {
    @Autowired
    TravelRepository travelRepository;

    public PageImpl<TravelGallery> getTravelList(SimplePageRequest pageRequest, TravelGallery travelGallery) {
        return travelRepository.getTravelList(pageRequest, travelGallery);
    }

    public List<TravelGallery> getTravelPointList(TravelGallery travelGallery) {
        return travelRepository.getTravelPointList(travelGallery);
    }

    public TravelGallery getTravelData(Long id) {
        TravelGallery gallery = travelRepository.findById(id).orElse(null);
        if (ObjectUtils.isEmpty(gallery) || gallery.getDelYn().equals("Y")) {
            throw new NotFoundException("존재하지 않거나 삭제된 정보입니다.");
        }

        List<TravelGalleryImage> images = travelRepository.getTravelImages(id, gallery.getMaxModifiedVersion());
        gallery.setImages(images);

        return gallery;
    }
}
