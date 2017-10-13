package com.halfstory.forkchapter.entity;

import lombok.Data;

@Data
public class Book {
    private Integer bookId;

    private String name;

    private String desc;

    private String starter;

    private Integer starterId;
}