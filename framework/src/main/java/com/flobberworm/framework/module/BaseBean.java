package com.flobberworm.framework.module;

/**
 * flobberworm team
 * Created by kornan on 2018/1/1.
 */

public class BaseBean {
    private Object object;
    private int itemType;

    public BaseBean() {
    }

    public BaseBean(Object object) {
        this.object = object;
    }

    public BaseBean(int itemType) {
        this.itemType = itemType;
    }

    public BaseBean(Object object, int itemType) {
        this.object = object;
        this.itemType = itemType;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }


    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getItemType() {
        return itemType;
    }
}
