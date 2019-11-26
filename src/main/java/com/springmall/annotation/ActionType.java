package com.springmall.annotation;

public enum ActionType {
    LOGIN(1),LOGOUT(2),QUERY(3),UPDATE(4),DELETE(5);
    private int type;
    ActionType(int type){
        this.type=type;
    }

    public int getType() {
        return type;
    }
}
