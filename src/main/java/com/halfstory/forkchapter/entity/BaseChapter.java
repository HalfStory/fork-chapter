package com.halfstory.forkchapter.entity;

import lombok.Data;

/**
 * @author xu
 * @date 2017/10/27
 */
@Data
public class BaseChapter {
    /**
     * 书号
     */
    private Integer bookId;

    /**
     * 章节号
     */
    private Integer chapterId;

    /**
     * 章节名称
     */
    private String name;

    /**
     * 章节作者
     */
    private String writer;

    /**
     * 分支数
     */
    private Integer fork;

    /**
     * 点赞数
     */
    private Integer star;

    /**
     * 是否结束的章节 1-结束
     */
    private Integer over;
}
