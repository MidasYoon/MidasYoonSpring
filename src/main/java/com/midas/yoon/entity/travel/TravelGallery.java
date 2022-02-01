package com.midas.yoon.entity.travel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.midas.yoon.entity.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "travel_galleries")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TravelGallery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String placeName;

    private String address;

    private Integer maxModifiedVersion;

    private Double latitude;

    private Double longitude;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String delYn;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Transient private List<TravelGalleryImage> images;

    @Transient private String nickname;
    @Transient private String thumbnail;

    @Transient private Double dLat;
    @Transient private Double dLon;
}
