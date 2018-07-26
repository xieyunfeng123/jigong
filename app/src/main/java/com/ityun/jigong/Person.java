package com.ityun.jigong;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/7/18 0018.
 */

public class Person extends BmobObject {

    private String name;

    private String pinyin;

    private List<DataBind> dataBindList;


    public List<DataBind> getDataBindList() {
        return dataBindList;
    }

    public void setDataBindList(List<DataBind> dataBindList) {
        this.dataBindList = dataBindList;
    }

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
