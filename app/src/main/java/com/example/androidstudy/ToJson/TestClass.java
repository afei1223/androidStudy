package com.example.androidstudy.ToJson;

import org.json.JSONObject;

public class TestClass extends ToJson {
    int[] ints;
    String[] strings;
    String string;
    Boolean aBoolean;
    Integer integer;
    Double aDouble;
    Long aLong;
    Byte aByte;

    public TestClass(String string, int anInt) {
        this.string = string;
        this.anInt = anInt;
    }

    Short aShort;
    Float aFloat;
    int anInt;
    char aChar;
    byte aBytes;
    short aShorts;
    long aLongs;
    float aFloats;
    double aDoubles;
    boolean aBooleans;

    TestClassMap testClassMap;

    public TestClass(int[] ints, String[] strings, String string, Boolean aBoolean, Integer integer, Double aDouble, Long aLong, Byte aByte, Short aShort, Float aFloat, int anInt, char aChar, byte aBytes, short aShorts, long aLongs, float aFloats, double aDoubles, boolean aBooleans, TestClassMap testClassMap) {
        this.ints = ints;
        this.strings = strings;
        this.string = string;
        this.aBoolean = aBoolean;
        this.integer = integer;
        this.aDouble = aDouble;
        this.aLong = aLong;
        this.aByte = aByte;
        this.aShort = aShort;
        this.aFloat = aFloat;
        this.anInt = anInt;
        this.aChar = aChar;
        this.aBytes = aBytes;
        this.aShorts = aShorts;
        this.aLongs = aLongs;
        this.aFloats = aFloats;
        this.aDoubles = aDoubles;
        this.aBooleans = aBooleans;
        this.testClassMap = testClassMap;
    }

}
