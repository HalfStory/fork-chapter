package com.halfstory.forkchapter.entity;

import lombok.Data;

/**
 * 对于书的点赞记录
 * @author xu
 * @date 2017/11/1
 */
@Data
public class StarBook {
    /**
     * 书号
     */
    private Integer bookId;

    /**
     * 点赞id
     */
    private Integer uid;
}
