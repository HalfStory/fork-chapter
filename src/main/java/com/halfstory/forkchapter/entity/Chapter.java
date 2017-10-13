package com.halfstory.forkchapter.entity;

import lombok.Data;

@Data
public class Chapter {
    private Integer bookId;

    private Integer chapterId;

    private String name;

    private Integer fatherId;

    private String content;
}