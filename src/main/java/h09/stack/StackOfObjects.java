package h09.stack;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings("unchecked")
public class StackOfObjects<T> {

    private int lastIndex = -1;

    private Object[] objects = new Object[1];

    public void push(T object) {
        increaseIfFull();
        objects[++lastIndex] = object;
    }

    public T get(int index) {
        checkIfValid(index);
        return (T) objects[index];
    }

    public T pop() {
        checkIfEmpty();
        return get(lastIndex--);
    }

    public static <O, I extends O> StackOfObjects<O> of(I[] objects) {
        StackOfObjects<O> stack = new StackOfObjects<>();
        for (I object : objects) {
            stack.push(object);
        }
        return stack;
    }

    public int numberOfObjects() {
        return lastIndex + 1;
    }

    private void checkIfValid(int index) {
        if (index < 0 || index > lastIndex) {
            throw new IllegalArgumentException("invalid index for stack of size %d: %d".formatted(numberOfObjects(), index));
        }
    }

    private void checkIfEmpty() {
        if (numberOfObjects() <= 0) {
            throw new IllegalStateException("stack is empty");
        }
    }

    private void increaseIfFull() {
        if (numberOfObjects() >= objects.length) {
            objects = Arrays.copyOf(objects, 2 * objects.length);
        }
    }

    @Override
    public String toString() {
        return IntStream.range(0, lastIndex + 1)
            .mapToObj(i -> objects[i] != null ? objects[i].toString() : "null")
            .collect(Collectors.joining(",\n\t", "[\n\t", "\n]"));
    }
}
