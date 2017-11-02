package com.halfstory.forkchapter.mapper;

import com.halfstory.forkchapter.entity.BaseChapter;
import com.halfstory.forkchapter.entity.Chapter;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 对chapter表操作的mapper
 *
 * @author xu
 * @date 2017/10/11
 */
@Mapper
public interface ChapterMapper {

    /**
     * 查询书籍的所有章节
     *
     * @param bookId 书号
     * @return 所有章节列表
     */
    @Select("select * from chapter where book_id=#{book_id}")
    List<Chapter> listAllBookChapter(@Param("book_id") Integer bookId);

    /**
     * 插入章节
     *
     * @param chapter 章节内容
     */
    @Insert("insert into chapter(book_id,name,father_id,writer,content) " +
            "values " +
            "(#{C.bookId},#{C.name},#{C.fatherId},#{C.writer},#{C.content})")
    void insertChapter(@Param("C") Chapter chapter);


    /**
     * 查询书籍的所有Master章节
     *
     * @param bookId 书号
     * @return 所有章节列表
     */
    @Select("select book_id,chapter_id,name,writer,fork,star,over from chapter where book_id=book_id and statues=1")
    List<BaseChapter> listMasterChapter(@Param("book_id") Integer bookId);

    /**
     * 查询指定章节内容
     *
     * @param bookId    书号
     * @param chapterId 章节号
     * @return 章节内容
     */
    @Select("select * from chapter where book_id=#{book_id} and chapter_id=#{chapter_id}")
    Chapter selectChapter(@Param("book_id") Integer bookId, @Param("chapter_id") Integer chapterId);

    /**
     * 查询最终的master章节
     *
     * @param bookId    书号
     * @param chapterId 章节号
     * @return 章节id
     */
    @Select("select max(chapter_id) from chapter where book_id=#{book_id} and chapter_id=#{chapter_id}")
    Integer selectFinalMaster(@Param("book_id") Integer bookId, @Param("chapter_id") Integer chapterId);

    /**
     * 章节fork数加一
     *
     * @param bookId    书号
     * @param chapterId 被fork章节号
     */
    @Update("update chapter set fork=fork+1 where book_id=#{book_id} and chapter_id=#{chapter_id}")
    void updateFork(@Param("book_id") Integer bookId, @Param("chapter_id") Integer chapterId);

    /**
     * 增加章节总点赞数
     *
     * @param chapterId 章节号
     */
    @Update("update chapter set star=star+1  where chapter_id = #{book_id}")
    void addBookStar(@Param("chapter_id") Integer chapterId);

    /**
     * 减少章节总点赞数
     *
     * @param chapterId 章节号
     */
    @Update("update chapter set star=star-1  where chapter_id = #{book_id}")
    void reduceBookStar(@Param("chapter_id") Integer chapterId);
}
