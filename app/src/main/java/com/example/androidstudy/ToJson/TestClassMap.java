package com.example.androidstudy.ToJson;

import java.util.HashMap;
import java.util.Map;

public class TestClassMap extends ToJson {
    HashMap hashMap;
    HashMap<String ,Integer> hmap;
    Map<Integer,String> map;

    public TestClassMap(HashMap hashMap, HashMap<String, Integer> hmap, Map<Integer, String> map) {
        this.hashMap = hashMap;
        this.hmap = hmap;
        this.map = map;
    }
}
