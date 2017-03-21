package com.sf.viewdemo;

/**
 * ListView数据实体类
 * Created by form
 * on 2017/2/23.
 * Desc
 */

public class ListData {
    private String name;
    private boolean checkboxFlag;

    public ListData(String name, boolean checkboxFlag) {
        this.name = name;
        this.checkboxFlag = checkboxFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheckboxFlag() {
        return checkboxFlag;
    }

    public void setCheckboxFlag(boolean checkboxFlag) {
        this.checkboxFlag = checkboxFlag;
    }

}
