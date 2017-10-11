package com.halfstory.forkchapter.mapper;

import com.halfstory.forkchapter.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {

    @Select("select * from book")
    List<Book> listAllBook();

    @Select("select * from book where book_id = #{bookId}")
    Book getBookById(Integer bookId);
}
