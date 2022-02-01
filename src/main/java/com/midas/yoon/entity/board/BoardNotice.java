package com.midas.yoon.entity.board;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.midas.yoon.entity.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "board_notices")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoardNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String content;

    private String imageContent;

    private Integer readCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String delYn;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Transient private List<BoardNoticeComment> comments;
    @Transient private List<BoardNoticeFile> files;

    @Transient private Long commentCount;
    @Transient private Long fileCount;
    @Transient private String nickname;
}
