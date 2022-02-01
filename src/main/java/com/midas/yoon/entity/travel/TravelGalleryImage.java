package com.midas.yoon.entity.travel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "travel_gallery_images")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TravelGalleryImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long travelGalleryId;

    private int galleryIndex;

    private String path;

    private String fileName;

    private String clientFileName;

    private int isThumbnail;

    private int modifyVersion;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String delYn;
}
