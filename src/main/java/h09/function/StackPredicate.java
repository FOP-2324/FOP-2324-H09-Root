package h09.function;

import h09.stack.StackOfObjects;

import java.util.function.Predicate;

public interface StackPredicate<T> extends Predicate<StackOfObjects<? super T>> {

}
