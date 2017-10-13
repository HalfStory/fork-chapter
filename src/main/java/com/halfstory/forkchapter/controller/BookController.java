package com.halfstory.forkchapter.controller;

import com.alibaba.fastjson.JSONObject;
import com.halfstory.forkchapter.common.bean.ExceptionType;
import com.halfstory.forkchapter.common.bean.ResultBody;
import com.halfstory.forkchapter.common.exception.BusinessException;
import com.halfstory.forkchapter.entity.Book;
import com.halfstory.forkchapter.entity.Chapter;
import com.halfstory.forkchapter.mapper.BookMapper;
import com.halfstory.forkchapter.mapper.ChapterMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping(value = "/book")
public class BookController {

    private final BookMapper bookMapper;

    private final ChapterMapper chapterMapper;

    @Autowired
    public BookController(BookMapper bookMapper, ChapterMapper chapterMapper) {
        this.bookMapper = bookMapper;
        this.chapterMapper = chapterMapper;
    }

    @ApiOperation(value = "当前书列表", notes = "当前所有书列表")
    @GetMapping("/book_list")
    public List<Book> listBook() {
        return bookMapper.listAllBook();
    }

    @ApiOperation(value = "创建新书", notes = "当前所有书列表")
    @GetMapping("/create_book")
    public JSONObject createBook(JSONObject data) throws BusinessException {
        String name = data.getString("name");
        String desc = data.getString("desc");
        String firstChapterName = data.getString("firstChapterName");
        String firstChapter = data.getString("firstChapter");
        String username = "xu";
        int uid = 1;
        Integer count = bookMapper.getCountByName(name);
        if (count > 0)
            throw new BusinessException(ExceptionType.BOOK_EXIST);
        Book book = new Book();
        book.setDesc(desc);
        book.setName(name);
        book.setStarter(username);
        book.setStarterId(uid);
        bookMapper.insertBook(book);
        Chapter chapter = new Chapter();
        chapter.setBookId(book.getBookId());
        chapter.setName(firstChapterName);
        chapter.setFatherId(0);
        chapter.setContent(firstChapter);
        chapterMapper.insertChapter(chapter);
        return ResultBody.json("创建成功");
    }
}
