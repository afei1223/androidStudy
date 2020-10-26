package com.lb.wheel;

public class FirstWheel {
    public int wheel(){
        return 1;
    }

    //原型模式，完全克隆。
    public FirstWheel clone(){
        return this;
    }
}
