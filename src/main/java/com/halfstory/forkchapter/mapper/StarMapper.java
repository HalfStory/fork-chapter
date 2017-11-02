package com.halfstory.forkchapter.mapper;

import org.apache.ibatis.annotations.*;

/**
 * @author xu
 * @date 2017/11/1
 */
@Mapper
public interface StarMapper {

    /**
     * 查找对应uid的book点赞记录
     *
     * @param uid    uid
     * @param bookId bookId
     * @return 点赞次数
     */
    @Select("select count(1) from star_book where uid = #{uid} and book_id = #{book_id}")
    Integer countStar(@Param("uid") Integer uid, @Param("book_id") Integer bookId);

    /**
     * 插入用户对书的点赞记录
     *
     * @param uid    uid
     * @param bookId bookId
     */
    @Insert("insert into star_book(book_id,uid,is_star) values (#{book_id},#{uid},1) ON DUPLICATE KEY UPDATE is_star=1")
    void insertBookStar(@Param("uid") Integer uid, @Param("book_id") Integer bookId);

    /**
     * 取消用户对书点赞记录
     *
     * @param uid    uid
     * @param bookId bookId
     */
    @Insert("insert into star_book(book_id,uid,is_star) values (#{book_id},#{uid},0) ON DUPLICATE KEY UPDATE is_star=0")
    void cancelBookStar(@Param("uid") Integer uid, @Param("book_id") Integer bookId);

    /**
     * 更新用户点赞状态
     *
     * @param uid    uid
     * @param bookId bookId
     * @param isStar 是否点赞
     */
    @Update("update star_book set is_star=#{is_star} where uid = #{uid} and book_id = #{book_id}")
    void updateStar(@Param("uid") Integer uid, @Param("book_id") Integer bookId, @Param("is_star") Integer isStar);

    /**
     * 插入用户对章节的点赞记录
     *
     * @param uid       uid
     * @param chapterId chapterId
     */
    @Insert("insert into star_chapter(chapter_id,uid,is_star) values (#{chapter_id},#{uid},1) ON DUPLICATE KEY UPDATE is_star=1")
    void insertChapterStar(@Param("uid") Integer uid, @Param("chapter_id") Integer chapterId);

    /**
     * 取消用户对章节的点赞记录
     *
     * @param uid       uid
     * @param chapterId chapterId
     */
    @Insert("insert into star_chapter(chapter_id,uid,is_star) values (#{chapter_id},#{uid},0) ON DUPLICATE KEY UPDATE is_star=0")
    void cancelChapterStar(@Param("uid") Integer uid, @Param("chapter_id") Integer chapterId);
}
