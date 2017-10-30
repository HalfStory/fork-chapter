package com.halfstory.forkchapter.controller;

import com.alibaba.fastjson.JSONObject;
import com.halfstory.forkchapter.common.bean.ExceptionType;
import com.halfstory.forkchapter.common.bean.ResultBody;
import com.halfstory.forkchapter.common.exception.BusinessException;
import com.halfstory.forkchapter.entity.BaseChapter;
import com.halfstory.forkchapter.entity.Book;
import com.halfstory.forkchapter.entity.Chapter;
import com.halfstory.forkchapter.mapper.BookMapper;
import com.halfstory.forkchapter.mapper.ChapterMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * web接口
 *
 * @author xu
 * @date 2017/10/11
 */
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
    @PostMapping("/create_book")
    public JSONObject createBook(JSONObject data) throws BusinessException {
        String name = data.getString("name");
        String desc = data.getString("desc");
        String firstChapterName = data.getString("firstChapterName");
        String firstChapter = data.getString("firstChapter");
        String username = "xu";
        int uid = 1;
        Integer count = bookMapper.getCountByName(name);
        if (count > 0) {
            throw new BusinessException(ExceptionType.BOOK_EXIST);
        }
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

    @ApiOperation(value = "当前章节列表", notes = "当前所有章节列表")
    @PostMapping("/chapter_list")
    public List<BaseChapter> listChapter(@RequestBody JSONObject data) {
        Integer bookId = data.getInteger("book_id");
        return chapterMapper.listMasterChapter(bookId);
    }

    @ApiOperation(value = "章节详情", notes = "当前章节详情")
    @PostMapping("/chapter_detail")
    public Chapter chapterDetail(@RequestBody JSONObject data) {
        Integer bookId = data.getInteger("book_id");
        Integer chapterId = data.getInteger("chapter_id");
        return chapterMapper.selectChapter(bookId, chapterId);
    }

    @ApiOperation(value = "fork章节")
    @PostMapping("/fork_chapter")
    public JSONObject forkChapter(@RequestBody JSONObject data) {
        Integer bookId = data.getInteger("book_id");
        Integer chapterId = data.getInteger("chapter_id");
        chapterMapper.updateFork(bookId, chapterId);
        return ResultBody.json("fork成功");
    }

    @ApiOperation(value = "new章节")
    @PostMapping("/new_chapter")
    public JSONObject newChapter(@RequestBody JSONObject data) {
        Integer bookId = data.getInteger("book_id");
        Integer fatherId = data.getInteger("chapter_id");
        String chapterName=data.getString("chapter_name");
        String content=data.getString("content");
        String username = "xu";
        int uid = 1;
        //发布新章节
        Chapter chapter=new Chapter();
        chapter.setBookId(bookId);
        chapter.setFatherId(fatherId);
        chapter.setName(chapterName);
        chapter.setWriter(username);
        chapter.setContent(content);
        chapter.setUid(uid);
        chapterMapper.insertChapter(chapter);
        return ResultBody.json("发布成功");
    }


    @ApiOperation(value = "点赞")
    @PostMapping("")
    public JSONObject star(@RequestBody JSONObject data) {
        Integer bookId = data.getInteger("book_id");
        Integer chapterId = data.getInteger("chapter_id");
        String username = "xu";
        int uid = 1;

        return null;
    }


}
