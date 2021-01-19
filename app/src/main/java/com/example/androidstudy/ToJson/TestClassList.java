package com.example.androidstudy.ToJson;

import java.util.ArrayList;
import java.util.List;

public class TestClassList extends ToJson{
    int[] ints;
    String[] strings;
    List<Integer> list = new ArrayList<>();
    List<String> lists = new ArrayList<>();
    Boolean[] aBoolean;
    Integer[] integer;
    Double[] aDouble;
    Long[] aLong;
    Byte[] aByte;
    Short[] aShort;
    Float[] aFloat;
    char[] aChar;
    byte[] aBytes;
    short[] aShorts;
    long[] aLongs;
    float[] aFloats;
    double[] aDoubles;
    boolean[] aBooleans;
    ArrayList<Integer> arrayList;

    public TestClassList(int[] ints, String[] strings, List<Integer> list, List<String> lists, Boolean[] aBoolean, Integer[] integer, Double[] aDouble, Long[] aLong, Byte[] aByte, Short[] aShort, Float[] aFloat, char[] aChar, byte[] aBytes, short[] aShorts, long[] aLongs, float[] aFloats, double[] aDoubles, boolean[] aBooleans) {
        this.ints = ints;
        this.strings = strings;
        this.list = list;
        this.lists = lists;
        this.aBoolean = aBoolean;
        this.integer = integer;
        this.aDouble = aDouble;
        this.aLong = aLong;
        this.aByte = aByte;
        this.aShort = aShort;
        this.aFloat = aFloat;
        this.aChar = aChar;
        this.aBytes = aBytes;
        this.aShorts = aShorts;
        this.aLongs = aLongs;
        this.aFloats = aFloats;
        this.aDoubles = aDoubles;
        this.aBooleans = aBooleans;
    }

    public TestClassList(int[] ints, String[] strings, List<Integer> list, List<String> lists, ArrayList<Integer> arrayList) {
        this.ints = ints;
        this.strings = strings;
        this.list = list;
        this.lists = lists;
        this.arrayList = arrayList;
    }
}
