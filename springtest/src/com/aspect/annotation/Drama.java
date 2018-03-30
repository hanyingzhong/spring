package com.aspect.annotation;

public class Drama implements Performer {

    @Override
    public void perform() {
        for (int i = 0; i < 1; i++) {
            System.out.println("话剧正在进行中——");
        }
    }
}