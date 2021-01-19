package com.example.androidstudy.HttpStudy;

import java.util.HashMap;
import java.util.List;

public interface ParamString {
    HashMap toMap() ;
    //获取变量的名字，用于存入map
    List<String> getNames();
}
