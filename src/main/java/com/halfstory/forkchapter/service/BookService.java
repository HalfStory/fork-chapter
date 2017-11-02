package com.halfstory.forkchapter.service;

import com.halfstory.forkchapter.entity.Book;

/**
 * @author xu
 * @date 2017/11/1
 */
public interface BookService {

    /**
     * 章节存储
     *
     * @param chapterName 章节名
     * @param content     内容
     * @param bookId      书名
     * @param fatherId    父章节id
     */
    void saveFirstChapter(String chapterName, String content, Integer bookId, Integer fatherId);

    /**
     * 存储新的书籍
     *
     * @param name     书名
     * @param desc     描述
     * @param username 作者名
     * @param uid      作者id
     * @return Book
     */
    Book newBook(String name, String desc, String username, int uid);
}
