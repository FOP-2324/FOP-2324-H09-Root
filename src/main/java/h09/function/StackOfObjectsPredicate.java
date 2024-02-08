package h09.function;

import h09.stack.StackOfObjects;

import java.util.function.Predicate;

/**
 * An object of a class implementing {@link StackOfObjectsPredicate} is a variant of a predicate usable for stacks of objects.
 */
// TODO H2.3 - generic type parameter
public interface StackOfObjectsPredicate<T> extends Predicate<StackOfObjects<? super T>> {

}
