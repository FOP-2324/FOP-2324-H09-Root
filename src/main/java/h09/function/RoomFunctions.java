package h09.function;

import h09.WithSeats;
import h09.room.Room;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A collection of functions usable for rooms.
 */
@SuppressWarnings("Convert2MethodRef")
public class RoomFunctions {

    /**
     * A predicate that returns {@code true} iff the given object is {@code null}.
     */
    // TODO H3.1
    public static Predicate<Object> IS_NULL_PREDICATE = object -> object == null;

    /**
     * Returns a predicate that returns {@code true} iff the name of the given room starts with the given location prefix.
     *
     * @param locationPrefix the location prefix
     * @return the predicate
     */
    public static <T extends Room> Predicate<T> isInArea(char locationPrefix) {
        // TODO H3.2 - generic method
        return room -> room.name().charAt(0) == locationPrefix;
    }

    public static <T extends Room & WithSeats> Predicate<T> isInAreaAndHasMinimumNumberOfSeats(char area, int number) {
        Predicate<T> isInArea = isInArea(area);
        // TODO H3.3 - generic method
        Predicate<T> hasAsManyOrMoreSeats = room -> room.numberOfSeats() >= number;
        return isInArea.and(hasAsManyOrMoreSeats);
    }

    /**
     * Returns a function that casts the given object to an object of the given type or
     * {@code null} if the given object is not of the given type.
     *
     * @param type the type
     * @return the function
     */
    @SuppressWarnings("unchecked")
    public static <T extends Room> Function<Room, T> toRoomTypeOrNull(Class<T> type) {
        // TODO H3.4 - generic method
        return room -> type.isInstance(room) ? (T) room : null;
    }

    private RoomFunctions() {
    }

}
