package h09.function;

import h09.WithSeats;
import h09.room.Room;
import h09.stack.StackOfObjects;

import java.util.function.Function;
import java.util.function.Predicate;

@SuppressWarnings("Convert2MethodRef")
public class Functions {

    public static Predicate<Object> IS_NULL_PREDICATE = object -> object == null;

    public static <T extends Room> Predicate<T> isIn(char area) {
        // return crash();
        return room -> room.name().charAt(0) == area;
    }

    public static <T extends Room & WithSeats> Predicate<T> isInAreaAndHasMinimumNumberOfSeats(char area, int number) {
        Predicate<T> isInArea = isIn(area);
        Predicate<T> hasAsManyOrMoreSeats = room -> room.numberOfSeats() >= number;
        return isInArea.and(hasAsManyOrMoreSeats);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Room> Function<Room, T> toRoomTypeOrNull(Class<T> type) {
        return room -> type.isInstance(room) ? (T) room : null;
    }

    private Functions() {
    }

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
