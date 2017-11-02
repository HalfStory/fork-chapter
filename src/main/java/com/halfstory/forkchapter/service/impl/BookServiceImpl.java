package com.halfstory.forkchapter.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.halfstory.forkchapter.entity.Book;
import com.halfstory.forkchapter.entity.Chapter;
import com.halfstory.forkchapter.mapper.BookMapper;
import com.halfstory.forkchapter.mapper.ChapterMapper;
import com.halfstory.forkchapter.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xu
 * @date 2017/11/1
 */
@Service
public class BookServiceImpl implements BookService{

    private final BookMapper bookMapper;

    private final ChapterMapper chapterMapper;

    @Autowired
    public BookServiceImpl(BookMapper bookMapper, ChapterMapper chapterMapper) {
        this.bookMapper = bookMapper;
        this.chapterMapper = chapterMapper;
    }

    @Override
    public void saveFirstChapter(String chapterName,String content, Integer bookId,Integer fatherId) {
        Chapter chapter = new Chapter();
        chapter.setBookId(bookId);
        chapter.setName(chapterName);
        chapter.setFatherId(fatherId);
        chapter.setContent(content);
        chapterMapper.insertChapter(chapter);
    }

    @Override
    public Book newBook(String name,String desc,String username,int uid) {
        Book book = new Book();
        book.setDesc(desc);
        book.setName(name);
        book.setStarter(username);
        book.setStarterId(uid);
        bookMapper.insertBook(book);
        return book;
    }
}
