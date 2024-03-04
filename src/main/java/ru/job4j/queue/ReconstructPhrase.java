package ru.job4j.queue;

import java.util.Deque;
import java.util.Iterator;

public class ReconstructPhrase {
    private final Deque<Character> descendingElements;

    private final Deque<Character> evenElements;

    public ReconstructPhrase(Deque<Character> descendingElements, Deque<Character> evenElements) {
        this.descendingElements = descendingElements;
        this.evenElements = evenElements;
    }

    private String getEvenElements() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < evenElements.size(); i++) {
            Character c = evenElements.poll();
            if (i % 2 == 0) {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    private String getDescendingElements() {
        StringBuilder builder = new StringBuilder();
        Iterator<Character> iterator = descendingElements.descendingIterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next());
        }
        return builder.toString();
    }

    public String getReconstructPhrase() {
        return getEvenElements() + getDescendingElements();
    }
}
