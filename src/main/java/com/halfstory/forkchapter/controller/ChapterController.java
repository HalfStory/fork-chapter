package com.halfstory.forkchapter.controller;

import com.alibaba.fastjson.JSONObject;
import com.halfstory.forkchapter.common.bean.ExceptionType;
import com.halfstory.forkchapter.common.bean.ResultBody;
import com.halfstory.forkchapter.common.exception.BusinessException;
import com.halfstory.forkchapter.entity.BaseChapter;
import com.halfstory.forkchapter.entity.Chapter;
import com.halfstory.forkchapter.mapper.BookMapper;
import com.halfstory.forkchapter.mapper.ChapterMapper;
import com.halfstory.forkchapter.mapper.StarMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author xu
 * @date 2017/11/1
 */
@RestController()
@RequestMapping(value = "/chapter")
public class ChapterController {

    private final BookMapper bookMapper;

    private final ChapterMapper chapterMapper;

    private final StarMapper starMapper;

    @Autowired
    public ChapterController(BookMapper bookMapper, ChapterMapper chapterMapper, StarMapper starMapper) {
        this.bookMapper = bookMapper;
        this.chapterMapper = chapterMapper;
        this.starMapper = starMapper;
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

    @ApiOperation(value = "发布new章节")
    @PostMapping("/new_chapter")
    public JSONObject newChapter(@RequestBody JSONObject data) {
        Integer bookId = data.getInteger("book_id");
        Integer fatherId = data.getInteger("chapter_id");
        String chapterName = data.getString("chapter_name");
        String content = data.getString("content");
        String username = "xu";
        int uid = 1;
        //发布新章节
        Chapter chapter = new Chapter();
        chapter.setBookId(bookId);
        chapter.setFatherId(fatherId);
        chapter.setName(chapterName);
        chapter.setWriter(username);
        chapter.setContent(content);
        chapter.setUid(uid);
        chapterMapper.insertChapter(chapter);
        return ResultBody.json("发布成功");
    }

    @ApiOperation(value = "给章节点赞")
    @PostMapping("/star")
    public JSONObject star(@RequestBody JSONObject data) throws BusinessException {
        Integer bookId = data.getInteger("chapter_id");
        Integer isStar = data.getInteger("is_star");
        int uid = 1;
        if (isStar == 1) {
            starMapper.insertChapterStar(uid, bookId);
            bookMapper.addBookStar(bookId);
        } else if (isStar == 0) {
            starMapper.cancelChapterStar(uid, bookId);
            bookMapper.reduceBookStar(bookId);
        } else {
            throw new BusinessException(ExceptionType.STAR_STATUS_ERROR);
        }
        return ResultBody.json("成功");
    }
}
