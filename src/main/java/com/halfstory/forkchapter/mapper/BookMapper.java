package com.halfstory.forkchapter.mapper;

import com.halfstory.forkchapter.entity.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {

    @Select("select * from book")
    List<Book> listAllBook();

    @Select("select * from book where book_id = #{book_id}")
    Book getBookById(@Param("book_id") Integer bookId);

    @Select("select count(1) from book where name = #{name}")
    Integer getCountByName(@Param("name") String name);

    @Insert("insert into book(name,desc,starter,starter_id)" +
            "values" +
            "(#{B.name},#{B.desc},#{B.starter},#{B.starterId})")
    @Options(useGeneratedKeys = true, keyProperty = "B.bookId", keyColumn = "book_id")
    Integer insertBook(@Param("B") Book book);
}
