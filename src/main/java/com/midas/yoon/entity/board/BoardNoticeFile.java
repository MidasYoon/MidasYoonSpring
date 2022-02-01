package com.midas.yoon.entity.board;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "board_notice_files")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoardNoticeFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long boardId;

    private Integer fileIndex;

    private String clientFileName;

    private String fileName;

    private Integer modifyVersion;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String delYn;

    @Transient private String path;
}
