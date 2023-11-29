package h09.function;

import java.util.function.Function;
import java.util.function.Predicate;

import h09.stack.StackOfObjects;

public class StackOfObjectsOperations {
    public static <O, I extends O> StackOfObjects<O> filter(
        StackOfObjects<? extends I> in, // alternatively StackOfObjects<I>
        Predicate<? super I> filter
    ) {
        StackOfObjects<O> out = new StackOfObjects<>();
        while (in.numberOfObjects() > 0) {
            I element = in.pop();
            if (filter.test(element)) {
                out.push(element);
            }
        }
        return out;
    }

    public static <O, I> StackOfObjects<O> map(
        StackOfObjects<? extends I> in, // alternatively StackOfObjects<I>
        Function<? super I, ? extends O> function
    ) {
        StackOfObjects<O> out = new StackOfObjects<>();
        while (in.numberOfObjects() > 0) {
            I from = in.pop();
            O to = function.apply(from);
            out.push(to);
        }
        return out;
    }
}
