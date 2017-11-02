package com.halfstory.forkchapter.entity;

import lombok.Data;

/**
 * @author xu
 * @date 2017/11/1
 */
@Data
public class StarChapter {
    /**
     * 章节号
     */
    private Integer chapterId;

    /**
     * 点赞id
     */
    private Integer uid;
}
