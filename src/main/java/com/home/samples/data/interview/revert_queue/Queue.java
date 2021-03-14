package com.home.samples.data.interview.revert_queue;

/**
 * Created by konstantin on 21.03.2020.
 */
public class Queue <E> {
    public E value;
    public Queue<E> next;

    public Queue(E value, Queue<E> next) {
        this.value = value;
        this.next = next;
    }

    public boolean hasNext(Queue<E> node) {
        return node.next != null;
    }

    public Queue<E> getHead() {
        Queue<E> node = this.next;
        while (node.next != null) {
            node = node.next;
            if (!node.hasNext(node)) return node;
        }
        return null;
    }
}
