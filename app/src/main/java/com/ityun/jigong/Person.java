package com.ityun.jigong;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/7/18 0018.
 */

public class Person extends BmobObject {

    private String name;

    private String pinyin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
