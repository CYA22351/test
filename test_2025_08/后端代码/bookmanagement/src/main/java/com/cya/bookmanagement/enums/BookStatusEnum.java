package com.cya.bookmanagement.enums;

public enum BookStatusEnum {
    DELETED(0,"删除"),
    NORMAL(1,"正常"),
    FORBIDDEN(2,"不允许借阅");

    private Integer code;
    private String desc;

    BookStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
public static BookStatusEnum getStatusByCode(Integer code){
        switch (code){
            case 0:return BookStatusEnum.DELETED;
            case 1:return BookStatusEnum.NORMAL;
            case 2:return BookStatusEnum.FORBIDDEN;
            default:return null;
        }
}
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
