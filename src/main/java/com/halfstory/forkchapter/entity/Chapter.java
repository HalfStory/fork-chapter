package com.halfstory.forkchapter.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 每个分支章节属性
 *
 * @author xu
 * @date 2017/10/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Chapter extends BaseChapter {

    /**
     * fork的父章节id
     */
    private Integer fatherId;

    /**
     * 章节内容
     */
    private String content;

    /**
     * 章节状态 1-master
     */
    private Integer statues;

    /**
     * 作者id
     */
    private Integer uid;
}