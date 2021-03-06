package com.midas.yoon.entity.board;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.midas.yoon.entity.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "board_notice_comments")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoardNoticeComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long boardId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String delYn;

    @Transient private String nickname;
}
