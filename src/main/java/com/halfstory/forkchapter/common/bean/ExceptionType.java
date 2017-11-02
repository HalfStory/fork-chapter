package com.halfstory.forkchapter.common.bean;

/**
 * Enum 响应类型
 *
 * @author xu
 * @date 2017/10/16
 */
public enum ExceptionType {
    //异常
    SUCCESS("00", "success", Level.ignore),
    SYSTEM_ERROR("400001", "系统错误", Level.high),
    BOOK_EXIST("400002", "本书名已存在", Level.ignore),
    STAR_STATUS_ERROR("400003", "点赞状态错误", Level.ignore);

    private String code;
    private String message;
    private int level;

    /**
     * @param code    异常码
     * @param message 对外输出信息
     * @param level   异常等级
     */
    ExceptionType(String code, String message, Level level) {
        this.code = code;
        this.message = message;
        this.level = level.getLevel();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}


