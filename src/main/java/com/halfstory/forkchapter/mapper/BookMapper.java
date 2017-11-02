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

    /**
     * 通过书名获取是否存在该书
     *
     * @param name 书名
     * @return 存在为1
     */
    @Select("select 1 from book where name = #{name} limit 1")
    Integer getCountByName(@Param("name") String name);

    /**
     * 插入一本新书
     *
     * @param book 书籍信息
     */
    @Insert("insert into book(name,desc,starter,starter_id)" +
            "values" +
            "(#{B.name},#{B.desc},#{B.starter},#{B.starterId})")
    @Options(useGeneratedKeys = true, keyProperty = "B.bookId", keyColumn = "book_id")
    void insertBook(@Param("B") Book book);

    /**
     * 增加书籍总点赞数
     *
     * @param bookId 书号
     */
    @Update("update book set star=star+1  where book_id = #{book_id}")
    void addBookStar(@Param("book_id") Integer bookId);

    /**
     * 减少书籍总点赞数
     *
     * @param bookId 书号
     */
    @Update("update book set star=star-1  where book_id = #{book_id}")
    void reduceBookStar(@Param("book_id") Integer bookId);
}
