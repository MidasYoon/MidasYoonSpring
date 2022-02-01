package com.midas.yoon.services.travel.repository;

import com.midas.yoon.entity.travel.TravelGallery;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface TravelRepository extends CrudRepository<TravelGallery, Long>, QuerydslPredicateExecutor<TravelGallery>, Travel {
}
