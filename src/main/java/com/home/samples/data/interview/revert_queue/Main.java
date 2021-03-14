package com.home.samples.data.interview.revert_queue;

import java.util.LinkedList;
import java.util.Random;
import java.util.function.Consumer;

/**
 * Created by konstantin on 21.03.2020.
 */
public class Main {

    public static void main(String[] args) {
        int size = 10;
       /* Queue<Integer> queue = null;
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            queue = new Queue<> (random.nextInt(10), queue);
        }
        printFromTail(queue, Main::function);*/

        java.util.Queue<Integer> q = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            q.add(random.nextInt(10));
        }
        System.out.println(q);
        printFromTail2(q, Main::function);
    }

    private static <E> void printFromTail2(java.util.Queue<E> queue, Consumer<E> function) {
        int lastIndex = 0;
        java.util.Queue<E> q2 = new LinkedList<>(queue);
        E next = q2.poll();
        while (next != null)
        {
            next = q2.poll();
            ++lastIndex;
        }
        while (lastIndex != 0)
        {
            q2 = new LinkedList<>(queue);
            for (int i = 0; i < lastIndex; i++)
            {
                next = q2.poll();
            }
            function.accept(next);
            --lastIndex;
        }
    }

    private static <E> void printFromTail(Queue<E> queue, Consumer<E> function) {
        int lastIndex = 0;
        Queue<E> next = queue.next;
        while (next != null) {
            if (next.next == null) {
                function.accept(next.value);
            }
            next = next.next;
            ++lastIndex;
        }
        int a = 1;
    }

    private static <E> void function(E element) {
        System.out.print(String.format("%s ", element.toString()));
    }
}
