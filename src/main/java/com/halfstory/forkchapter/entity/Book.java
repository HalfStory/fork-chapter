package com.halfstory.forkchapter.entity;

import lombok.Data;

/**
 * 每本书基本属性
 *
 * @author xu
 * @date 2017/10/11
 */
@Data
public class Book {
    private Integer bookId;

    private String name;

    private String desc;

    private String starter;

    private Integer starterId;

    private Integer totalChapter;

    private Integer masterChapter;

    /**
     * 点赞数
     */
    private Integer star;
}