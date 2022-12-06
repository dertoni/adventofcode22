package info.mignogna.day;

import info.mignogna.util.Utils;

import java.io.IOException;
import java.util.List;

public class D6 {
    public static void main(String[] args) throws IOException {
        d6(Utils.readFile("day06_example1.txt"), "example1_part1", 4);
        d6(Utils.readFile("day06_example2.txt"), "example2_part1", 4);
        d6(Utils.readFile("day06_example3.txt"), "example3_part1", 4);
        d6(Utils.readFile("day06_example4.txt"), "example4_part1", 4);
        d6(Utils.readFile("day06_example5.txt"), "example5_part1", 4);
        d6(Utils.readFile("day06_1.txt"), "original_part1", 4);

        d6(Utils.readFile("day06_example1.txt"), "example1_part2", 14);
        d6(Utils.readFile("day06_example2.txt"), "example2_part2", 14);
        d6(Utils.readFile("day06_example3.txt"), "example3_part2", 14);
        d6(Utils.readFile("day06_example4.txt"), "example4_part2", 14);
        d6(Utils.readFile("day06_example5.txt"), "example5_part2", 14);
        d6(Utils.readFile("day06_1.txt"), "original_part2", 14);
    }

    private static void d6(List<String> input, String qualifier, int maxItems) {
        // input has just 1 line
        String dataBuffer = input.get(0);

        Queue charSequence = new Queue(maxItems);

        for (int i = 0; i < dataBuffer.length(); i++) {
            char character = dataBuffer.charAt(i);
            final boolean found = charSequence.push(character);
            if (found) {
                System.out.println(qualifier + ">>> The sequence is found. Index: " + (i + 1));
                return;
            }
        }
    }

    public static class QueueItem {
        private QueueItem prev;
        private QueueItem next;

        private char item;

        public QueueItem(char item, QueueItem next) {
            this.item = item;
            this.next = next;
            this.prev = null;
        }

        public QueueItem next() {
            return next;
        }

        public void setNext(QueueItem next) {
            this.next = next;
        }

        public QueueItem prev() {
            return prev;
        }

        public void setPrev(QueueItem prev) {
            this.prev = prev;
        }

        public char getItem() {
            return item;
        }
    }

    public static class Queue {
        private final int maxItems;
        private QueueItem first;
        private QueueItem last;
        private int itemCounter = 0;
        private int redundantScore = 0;

        public Queue(int maxItems) {
            this.maxItems = maxItems;
        }

        public boolean push(char character) {
            if (itemCounter == 0) {
                insertFirstItem(character);
            } else if (itemCounter < maxItems) {
                insertAdditionalItem(character);
            } else if (itemCounter == maxItems) {
                insertAndRemoveItem(character);
            }

            return (itemCounter == maxItems) && (redundantScore == 0);
        }

        private void insertFirstItem(char character) {
            first = new QueueItem(character, null);
            last = first;
            itemCounter++;
        }

        private void insertAdditionalItem(char character) {
            redundantScore += foundInData(character, first) ? 1 : 0;
            QueueItem newItem = new QueueItem(character, first);
            first.setPrev(newItem);
            first = newItem;
            itemCounter++;
        }

        private void insertAndRemoveItem(char character) {
            QueueItem oldItem = last;
            last = oldItem.prev();
            last.setNext(null);
            redundantScore -= foundInData(oldItem.getItem(), first) ? 1 : 0;

            redundantScore += foundInData(character, first) ? 1 : 0;
            QueueItem newItem = new QueueItem(character, first);
            first.setPrev(newItem);
            first = newItem;
        }

        private boolean foundInData(char character, QueueItem current) {
            if (current.next() == null) {
                return current.getItem() == character;
            }
            return (current.getItem() == character) || foundInData(character, current.next());
        }

        private int calcRedundantScore(char character, QueueItem current, int acc) {
            if (current.next() == null) {
                return acc + (current.getItem() == character ? 1 : 0);
            } else {
                return acc + (current.getItem() == character ? 1 : 0) + calcRedundantScore(character, current.next(), 0);
            }
        }
    }

}
