package com.example.androidstudy.ToJson;

import android.webkit.WebView;

import java.util.LinkedList;
import java.util.Queue;

public class TestClassOther extends ToJson {
    Exception exception;
    Queue<Integer> queue;
    LinkedList<Integer> linkedList;

    public TestClassOther(Exception exception, Queue<Integer> queue, LinkedList<Integer> linkedList) {
        this.exception = exception;
        this.queue = queue;
        this.linkedList = linkedList;
    }
}
