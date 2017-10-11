package com.halfstory.forkchapter.mapper;

import com.halfstory.forkchapter.entity.Chapter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChapterMapper {
    @Select("select * from chapter where book_id=book_id")
    List<Chapter> listAllBookChapter(@Param("book_id") Integer bookId);
}
