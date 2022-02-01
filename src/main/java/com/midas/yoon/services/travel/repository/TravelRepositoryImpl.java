package com.midas.yoon.services.travel.repository;

import com.midas.yoon.config.web.SimplePageRequest;
import com.midas.yoon.entity.travel.QTravelGallery;
import com.midas.yoon.entity.travel.QTravelGalleryImage;
import com.midas.yoon.entity.travel.TravelGallery;
import com.midas.yoon.entity.travel.TravelGalleryImage;
import com.midas.yoon.entity.user.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class TravelRepositoryImpl  extends QuerydslRepositorySupport implements Travel {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    public TravelRepositoryImpl(EntityManager entityManager) {
        super(TravelGallery.class);
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public PageImpl<TravelGallery> getTravelList(SimplePageRequest pageRequest, TravelGallery travelGallery) {
        String title = travelGallery.getTitle();
        String placeName = travelGallery.getPlaceName();
        String address = travelGallery.getAddress();
        String nickname = travelGallery.getNickname();

        QTravelGallery qTravelGallery = QTravelGallery.travelGallery;
        QTravelGalleryImage qTravelGalleryImage = QTravelGalleryImage.travelGalleryImage;
        QUser qUser = QUser.user;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qTravelGallery.delYn.eq("N"));
        if (! ObjectUtils.isEmpty(title)) {
            builder.and(qTravelGallery.title.contains(title));
        }
        if (! ObjectUtils.isEmpty(placeName)) {
            builder.and(qTravelGallery.placeName.contains(placeName));
        }
        if (! ObjectUtils.isEmpty(address)) {
            builder.and(qTravelGallery.address.contains(address));
        }
        if (! ObjectUtils.isEmpty(nickname)) {
            builder.and(qUser.nickname.contains(nickname));
        }

        JPAQuery<TravelGallery> query = jpaQueryFactory
            .select(
                Projections.fields(
                    TravelGallery.class,
                    qTravelGallery.id,
                    qTravelGallery.title,
                    qTravelGallery.placeName,
                    qTravelGalleryImage.path.as("thumbnail"),
                    qUser.nickname,
                    qTravelGallery.createdAt
                )
            )
            .from(qTravelGallery)
            .leftJoin(qTravelGalleryImage).on(
                qTravelGalleryImage.travelGalleryId.eq(qTravelGallery.id)
                    .and(qTravelGalleryImage.modifyVersion.eq(qTravelGallery.maxModifiedVersion))
                    .and(qTravelGalleryImage.isThumbnail.eq(1))
                    .and(qTravelGalleryImage.delYn.eq("N"))
            )
            .leftJoin(qUser).on(
                qUser.id.eq(qTravelGallery.user.id)
            )
            .where(builder)
            .orderBy(qTravelGallery.id.desc());

        Pageable page = pageRequest.makePageable("id");
        long total = query.fetch().size();
        List<TravelGallery> results = getQuerydsl().applyPagination(page, query).fetch();

        return new PageImpl<>(results, page, total);
    }

    @Override
    public List<TravelGallery> getTravelPointList(TravelGallery travelGallery) {
        Double latitude = travelGallery.getLatitude();
        Double longitude = travelGallery.getLongitude();
        Double dLat = travelGallery.getDLat();
        Double dLon = travelGallery.getDLon();

        QTravelGallery qTravelGallery = QTravelGallery.travelGallery;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qTravelGallery.delYn.eq("N"));
        builder.and(qTravelGallery.latitude.between(latitude - dLat, latitude + dLat));
        builder.and(qTravelGallery.longitude.between(longitude - dLon, longitude + dLon));

        return jpaQueryFactory.select(
                Projections.fields(
                    TravelGallery.class,
                    qTravelGallery.placeName,
                    qTravelGallery.latitude,
                    qTravelGallery.longitude
                )
            ).from(qTravelGallery)
            .where(builder)
            .fetch();
    }

    @Override
    public List<TravelGalleryImage> getTravelImages(Long id, int modifiedVersion) {
        QTravelGalleryImage qTravelGalleryImage = QTravelGalleryImage.travelGalleryImage;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qTravelGalleryImage.delYn.eq("N"));
        builder.and(qTravelGalleryImage.travelGalleryId.eq(id));
        builder.and(qTravelGalleryImage.modifyVersion.eq(modifiedVersion));

        return jpaQueryFactory.select(
                Projections.fields(
                    TravelGalleryImage.class,
                    qTravelGalleryImage.path,
                    qTravelGalleryImage.fileName
                )
            )
            .from(qTravelGalleryImage)
            .where(builder)
            .orderBy(qTravelGalleryImage.galleryIndex.asc())
            .fetch();
    }
}
