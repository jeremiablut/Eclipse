package com.eclipse.client;

import java.util.ArrayDeque;
import java.util.Deque;


public class CPSCounter {
    private final Deque<Long> clicks = new ArrayDeque<>();

    public void register() {
        clicks.addLast(System.currentTimeMillis());
    }

    private void clearOut() {
        long now = System.currentTimeMillis();

        while (!clicks.isEmpty() && now - clicks.peekFirst() > 1000) {
            clicks.pollFirst();
        }
    }

    public int getCPS() {
        clearOut();
        return clicks.size();
    }
}
