package h09.stack;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * An object of class {@link StackOfObjects} represents a data structure of type stack.
 */
@SuppressWarnings("unchecked")
public class StackOfObjects<T> {

    /**
     * The index of the top object in this stack.
     */
    private int lastIndex = -1;

    /**
     * The objects in this stack.
     */
    private Object[] objects = new Object[1]; // Alternatively: private T[] objects = (T[]) new Object[1];

    /**
     * Pushes the given object on this stack.
     *
     * @param object the object
     */
    public void push(T object) {
        // TODO H1.1 - parameter type from Object to T
        increaseIfFull();
        objects[++lastIndex] = object;
    }

    /**
     * Removes and returns the top object of this stack.
     *
     * @return the top object
     */
    public T pop() {
        // TODO H1.1 - return type type from Object to T
        checkIfEmpty();
        var e = get(lastIndex);
        lastIndex--;
        return e;
    }

    /**
     * Returns the object at the given index in this stack.
     *
     * @param index the index
     * @return the object
     */
    public T get(int index) {
        // TODO H1.1 - return type from Object to T
        checkIfValid(index);
        return (T) objects[index];
    }

    /**
     * Constructs and returns a stack with the given objects.
     * The last object is the top object.
     *
     * @param objects the objects
     * @return the stack
     */
    public static <O, I extends O> StackOfObjects<O> of(I... objects) {
        // TODO H1.1 - generic method, type parameter return type, parameter type, type of objects
        StackOfObjects<O> stack = new StackOfObjects<>();
        for (I object : objects) {
            stack.push(object);
        }
        return stack;
    }

    /**
     * Returns the number of objects in this stack.
     *
     * @return the number of objects
     */
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
