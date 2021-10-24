package com.luxoft.bankapp.queue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Queue<T> {
    private final List<T> storage = Collections.synchronizedList(new LinkedList<>());

    public void add(T object) {
        storage.add(object);
    }

    public T get() {
        if (storage.size() > 0) {
            return storage.remove(0);
        } else {
            return null;
        }
    }
}
