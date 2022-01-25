package com.midas.yoon.entity.board;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "board_notices")
public class BoardNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String content;

    private String imageContent;

    private Long userId;

    private int readCount;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    private String delYn;

    @OneToMany
    @JoinColumn(name = "boardId")
    private List<BoardNoticeComment> comments;
}
