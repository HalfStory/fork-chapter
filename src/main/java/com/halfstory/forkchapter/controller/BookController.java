package com.halfstory.forkchapter.controller;

import com.halfstory.forkchapter.entity.Book;
import com.halfstory.forkchapter.mapper.BookMapper;
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

    @Autowired
    public BookController(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @ApiOperation(value = "当前书列表", notes = "当前所有书列表")
    @GetMapping("/book_list")
    public List<Book> listBook() {
        return bookMapper.listAllBook();
    }
}
