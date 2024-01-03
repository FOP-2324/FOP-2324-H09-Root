package h09.function;

import java.util.function.Function;
import java.util.function.Predicate;

import h09.stack.StackOfObjects;

/**
 * A collection of operations usable for rooms.
 */
public class StackOfObjectsOperations {

        /**
     * Returns a stack containing all objects of the given stack in reversed order for which the given filter returns true.
     *
     * @param in     the input stack
     * @param filter the filter
     * @return the output stack
     */
    public static <O, I extends O> StackOfObjects<O> filter(
        StackOfObjects<? extends I> in, // Alternatively: StackOfObjects<I>
        Predicate<? super I> filter
    ) {
        // TODO H2.1 - generic method, type parameter return type, parameter type, type of objects
        StackOfObjects<O> out = new StackOfObjects<>();
        while (in.numberOfObjects() > 0) {
            I element = in.pop();
            if (filter.test(element)) {
                out.push(element);
            }
        }
        return out;
    }

        /**
     * Returns a stack containing all objects of the given stack mapped using the given function.
     *
     * @param in       the input stack
     * @param function the function
     * @return the output stack
     */
    public static <O, I> StackOfObjects<O> map(
        StackOfObjects<? extends I> in, // Alternatively: StackOfObjects<I>
        Function<? super I, ? extends O> function // Alternatively: Function<I, O>
    ) {
        // TODO H2.2 - generic method, type parameter return type, parameter type, type of objects
        StackOfObjects<O> out = new StackOfObjects<>();
        while (in.numberOfObjects() > 0) {
            I from = in.pop();
            O to = function.apply(from);
            out.push(to);
        }
        return out;
    }
}
