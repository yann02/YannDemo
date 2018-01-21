package com.yann.yanndemo.entity;

/**
 * Created by wyy on 2018/1/20.
 */

public class SimpleListItemEntity {
    private String id;
    private String name;
    private boolean isSelector = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelector() {
        return isSelector;
    }

    public void setSelector(boolean selector) {
        isSelector = selector;
    }
}
