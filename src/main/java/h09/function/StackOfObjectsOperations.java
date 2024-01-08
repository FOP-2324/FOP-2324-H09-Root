package h09.function;

import h09.stack.StackOfObjects;

import java.util.function.Function;
import java.util.function.Predicate;

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
    public static <Y, X extends Y> StackOfObjects<Y> filter(
        StackOfObjects<X> in, // Alternatively: StackOfObjects<I>
        Predicate<X> filter // Alternatively: Predicate<I>
    ) {
        // TODO H2.1 - generic method, type parameter return type, parameter type, type of objects
        StackOfObjects<Y> out = new StackOfObjects<>();
        while (in.numberOfObjects() > 0) {
            X element = in.pop();
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
    public static <Y, X> StackOfObjects<Y> map(
        StackOfObjects<? extends X> in, // Alternatively: StackOfObjects<I>
        Function<? super X, ? extends Y> function // Alternatively: Function<I, O>
    ) {
        // TODO H2.2 - generic method, type parameter return type, parameter type, type of objects
        StackOfObjects<Y> out = new StackOfObjects<>();
        while (in.numberOfObjects() > 0) {
            X from = in.pop();
            Y to = function.apply(from);
            out.push(to);
        }
        return out;
    }
}
