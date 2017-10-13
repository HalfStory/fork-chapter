package com.halfstory.forkchapter.common.bean;

/**
 * Enum 响应类型
 */
public enum ExceptionType {
    SUCCESS("00", "success", 0),
    SYSTEM_ERROR("400001", "系统错误", 5),
    BOOK_EXIST("400002", "本书名已存在", 3);

    private String code;
    private String message;
    private int level;

    ExceptionType(String code, String message, int level) {
        this.code = code;
        this.message = message;
        this.level = level;
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


