package com.halfstory.forkchapter.controller;

import com.alibaba.fastjson.JSONObject;
import com.halfstory.forkchapter.common.bean.ExceptionType;
import com.halfstory.forkchapter.common.bean.ResultBody;
import com.halfstory.forkchapter.common.exception.BusinessException;
import com.halfstory.forkchapter.entity.Book;
import com.halfstory.forkchapter.mapper.BookMapper;
import com.halfstory.forkchapter.mapper.StarMapper;
import com.halfstory.forkchapter.service.BookService;
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

    private final BookService bookService;

    private final StarMapper starMapper;

    @Autowired
    public BookController(BookMapper bookMapper, BookService bookService, StarMapper starMapper) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
        this.starMapper = starMapper;
    }

    @ApiOperation(value = "当前书列表", notes = "当前所有书列表")
    @GetMapping("/book_list")
    public List<Book> listBook() {
        return bookMapper.listAllBook();
    }

    @ApiOperation(value = "创建新书", notes = "当前所有书列表")
    @PostMapping("/create_book")
    public JSONObject createBook(JSONObject data) throws BusinessException {
        //书名校验
        String name = data.getString("name");
        Integer count = bookMapper.getCountByName(name);
        if (count > 0) {
            throw new BusinessException(ExceptionType.BOOK_EXIST);
        }
        //记录书名
        String desc = data.getString("desc");
        String username = "xu";
        int uid = 1;
        Book book = bookService.newBook(name, desc, username, uid);
        //记录章节
        String firstChapterName = data.getString("firstChapterName");
        String firstChapter = data.getString("firstChapter");
        bookService.saveFirstChapter(firstChapterName, firstChapter, book.getBookId(), 0);
        return ResultBody.json("创建成功");
    }

    @ApiOperation(value = "给书点赞")
    @PostMapping("/star")
    public JSONObject star(@RequestBody JSONObject data) throws BusinessException {
        Integer bookId = data.getInteger("book_id");
        Integer isStar = data.getInteger("is_star");
        int uid = 1;
        if (isStar == 1) {
            starMapper.insertBookStar(uid, bookId);
            bookMapper.addBookStar(bookId);
        } else if (isStar == 0) {
            starMapper.cancelBookStar(uid, bookId);
            bookMapper.reduceBookStar(bookId);
        } else {
            throw new BusinessException(ExceptionType.STAR_STATUS_ERROR);
        }
        return ResultBody.json("成功");
    }


}
